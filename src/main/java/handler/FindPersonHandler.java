package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import service.FindPersonService;
import service.request.FindPersonRequest;
import service.result.FindPersonResult;

import java.io.*;
import java.net.HttpURLConnection;

// TODO: Can only find those you are related to!
/**
 * Handlers /person/[personID]
 */
public class FindPersonHandler implements HttpHandler {
    /**
     * Find a person related to the user.
     */
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            String[] URIParameters = httpExchange.getRequestURI().getRawPath().split("/");
            if (URIParameters.length < 2) {
                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                httpExchange.getResponseBody().close();
                return;
            }


            if (Utility.usedMethod(httpExchange, "get")) {
                String authTokenID = httpExchange.getRequestHeaders().get("Authorization").get(0);

                String personID = URIParameters[2];
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
