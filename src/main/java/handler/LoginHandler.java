package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import service.LoginService;
import service.request.LoginRequest;
import service.result.LoginResult;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.Locale;

/**
 * Handles /user/login http requests.
 */
public class LoginHandler implements HttpHandler {
    /**
     * Logs in a user
     */
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            if (httpExchange.getRequestMethod().toLowerCase(Locale.ROOT).equals("post")) {
                InputStream reqBody = httpExchange.getRequestBody();

                String reqData = Utility.readString(reqBody);
                LoginRequest request = (LoginRequest) JSONHandler.jsonToObject(reqData, LoginRequest.class);
                LoginResult result = new LoginService().login(request);

                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                Writer resBody = new OutputStreamWriter(httpExchange.getResponseBody());
                JSONHandler.objectToJsonWriter(result, resBody);

                resBody.close();
            } else {
                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }
        } catch (IOException | DataAccessException e) {
            e.printStackTrace(); // TODO: Logger
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR,0);
            httpExchange.getResponseBody().close();
        }
    }
}
