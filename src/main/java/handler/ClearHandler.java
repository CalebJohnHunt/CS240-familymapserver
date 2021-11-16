package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import service.ClearService;
import result.ClearResult;

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
            if (HttpUtil.usedMethod(httpExchange, "post")) {
                ClearResult result = new ClearService().clear();
                HttpUtil.writeSuccessfulResult(result, httpExchange);
            } else {
                HttpUtil.handleBadMethod(httpExchange);
            }
        } catch (IOException | DataAccessException e) {
            HttpUtil.handleServerError(httpExchange);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
