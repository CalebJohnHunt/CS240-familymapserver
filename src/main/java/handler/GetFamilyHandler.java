package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import service.GetFamilyService;
import service.request.GetFamilyRequest;
import service.result.GetFamilyResult;

import java.io.IOException;
import java.net.HttpURLConnection;

public class GetFamilyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            if (Utility.usedMethod(httpExchange, "get")) {
                String authTokenID = httpExchange.getRequestHeaders().get("authorization").get(0);

                GetFamilyRequest request = new GetFamilyRequest(authTokenID);
                GetFamilyResult result = new GetFamilyService().getFamily(request);

                Utility.writeSuccessfulResult(result, httpExchange);
            } else {
                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                httpExchange.getResponseBody().close();
            }
        } catch (IOException | DataAccessException e) {
            e.printStackTrace(); // TODO: Logger
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            httpExchange.getResponseBody().close();
        }
    }
}