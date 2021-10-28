package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import service.ClearService;
import service.result.ClearResult;

import java.io.*;

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

                Utility.writeSuccessfulResult(result, httpExchange);
            } else {
                Utility.handleBadMethod(httpExchange);
            }
        } catch (IOException | DataAccessException e) {
            Utility.handleServerError(httpExchange);
        }
    }
}
