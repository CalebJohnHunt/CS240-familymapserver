package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import service.FillService;
import service.request.FillRequest;
import service.result.FillResult;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Handlers /fill/[username]/{generations}
 */
public class FillHandler implements HttpHandler {
    /**
     * Fills in fake data for the username for the given number of generations.
     * Generations: 0: person, 1: user + parents, 2: user + parents + grandparents, etc.
     */
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String[] URIParameters = httpExchange.getRequestURI().getRawPath().split("/");
        if (URIParameters.length < 3) {
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            httpExchange.getResponseBody().close();
            return;
        }

        try {
            if (HttpUtil.usedMethod(httpExchange, "post")) {
                String username = URIParameters[2];
                int generations = 4;
                if (URIParameters.length > 3) {
                    generations = Integer.parseInt(URIParameters[3]);
                }

                FillRequest request = new FillRequest(username, generations);
                FillResult result = new FillService().fill(request);

                HttpUtil.writeSuccessfulResult(result, httpExchange);
            } else {
                HttpUtil.handleBadMethod(httpExchange);
            }
        } catch (NumberFormatException e) {
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            FillResult result = new FillResult(false, "Error: Invalid number of generations");
            HttpUtil.writeSuccessfulResult(result, httpExchange);
        } catch (IOException | DataAccessException e) {
            e.printStackTrace(); // TODO: Logger
            HttpUtil.handleServerError(httpExchange);
        }
    }
}
