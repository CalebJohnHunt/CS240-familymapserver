package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import service.FindPersonService;
import service.request.FindPersonRequest;
import service.result.FindPersonResult;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Locale;

/**
 * Handlers /person/[personID]
 */
public class FindPersonHandler implements HttpHandler {
    /**
     * Length of the non-parameter portion of the url.
     */
    private final int URL_LENGTH = "/person".length()+1;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        // TODO: Refactor these to use `try (Writer resBody...)` since it can close automatically.
        try {
            if (httpExchange.getRequestMethod().toLowerCase(Locale.ROOT).equals("get")) {
                String personID = httpExchange.getRequestURI().getRawPath().substring(URL_LENGTH);
                String authTokenID = httpExchange.getRequestHeaders().get("Authorization").get(0);

                FindPersonRequest request = new FindPersonRequest(personID, authTokenID);
                FindPersonResult result = new FindPersonService().find(request);

                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                Writer resBody = new OutputStreamWriter(httpExchange.getResponseBody());
                JSONHandler.objectToJsonWriter(result, resBody);

                resBody.close();

            } else {
                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                httpExchange.getResponseBody().close();
            }
        } catch (IOException | DataAccessException e) {
            e.printStackTrace(); // TODO: logger
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            httpExchange.getResponseBody().close();
        }
    }
}
