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
            System.out.println(1);
            if (HttpUtil.usedMethod(httpExchange, "post")) {
            System.out.println(2);
                ClearResult result = new ClearService().clear();
            System.out.println(3);
                HttpUtil.writeSuccessfulResult(result, httpExchange);
            System.out.println(4);
            } else {
            System.out.println(5);
                HttpUtil.handleBadMethod(httpExchange);
            System.out.println(6);
            }
            System.out.println(7);
        } catch (IOException | DataAccessException e) {
            System.out.println(8);
            HttpUtil.handleServerError(httpExchange);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
