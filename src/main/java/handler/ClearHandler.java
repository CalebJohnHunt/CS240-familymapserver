package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import service.ClearService;
import service.result.ClearResult;

import java.io.*;
import java.net.HttpURLConnection;

/**
 * Handles /clear http request.
 */
public class ClearHandler implements HttpHandler {
    /**
     * Clears all the data from the database.
     */
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            if (Utility.usedMethod(httpExchange, "post")) {
                ClearResult result = new ClearService().clear();

                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                Writer resBody = new OutputStreamWriter(httpExchange.getResponseBody());
                JSONHandler.objectToJsonWriter(result, resBody);

                resBody.close();
            }
        } catch (IOException | DataAccessException e) {
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
        }
    }
}
