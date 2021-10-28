package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import service.RegisterService;
import service.request.RegisterRequest;
import service.result.RegisterResult;

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
            if (Utility.usedMethod(httpExchange, "post")) {
                InputStream reqBody = httpExchange.getRequestBody();

                String reqData = Utility.readString(reqBody);
                RegisterRequest request = (RegisterRequest) JSONHandler.jsonToObject(reqData, RegisterRequest.class);
                RegisterResult result = new RegisterService().register(request);

                Utility.writeSuccessfulResult(result, httpExchange);


            } else {
                Utility.handleBadMethod(httpExchange);
            }
        } catch (IOException | DataAccessException e) {
            e.printStackTrace(); // TODO: Logger
            Utility.handleServerError(httpExchange);
        }
    }

}
