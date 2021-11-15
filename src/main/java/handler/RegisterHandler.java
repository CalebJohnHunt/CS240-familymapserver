package handler;

import Util.JSONHandler;
import Util.ReadString;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import service.RegisterService;
import request.RegisterRequest;
import result.RegisterResult;

import java.io.*;

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
            if (HttpUtil.usedMethod(httpExchange, "post")) {
                InputStream reqBody = httpExchange.getRequestBody();

                String reqData = ReadString.readString(reqBody);
                RegisterRequest request = (RegisterRequest) JSONHandler.jsonToObject(reqData, RegisterRequest.class);
                RegisterResult result = new RegisterService().register(request);

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
