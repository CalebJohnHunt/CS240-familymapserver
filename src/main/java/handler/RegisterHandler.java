package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import service.RegisterService;
import service.request.RegisterRequest;
import service.result.RegisterResult;

import java.io.*;
import java.net.HttpURLConnection;

/**
 * Handles /user/register http requests
 */
public class RegisterHandler implements HttpHandler {
    /**
     * registers a new user
     */
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        try {
            if (Utility.usedMethod(httpExchange, "post")) {
                InputStream reqBody = httpExchange.getRequestBody();

                String reqData = Utility.readString(reqBody);
                RegisterRequest request = (RegisterRequest) JSONHandler.jsonToObject(reqData, RegisterRequest.class);
                RegisterResult result = new RegisterService().register(request);

                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                Writer resBody = new OutputStreamWriter(httpExchange.getResponseBody());
                JSONHandler.objectToJsonWriter(result, resBody);

                resBody.close();

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
