package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import service.LoginService;
import service.request.LoginRequest;
import service.result.LoginResult;

import java.io.*;

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
            if (HttpUtil.usedMethod(httpExchange, "post")) {
                InputStream reqBody = httpExchange.getRequestBody();

                String reqData = HttpUtil.readString(reqBody);
                LoginRequest request = (LoginRequest) JSONHandler.jsonToObject(reqData, LoginRequest.class);
                LoginResult result = new LoginService().login(request);

                HttpUtil.writeSuccessfulResult(result, httpExchange);

            } else {
                HttpUtil.handleBadMethod(httpExchange);
            }
        } catch (IOException | DataAccessException e) {
            e.printStackTrace(); // TODO: Logger
            HttpUtil.handleServerError(httpExchange);
        }
    }
}
