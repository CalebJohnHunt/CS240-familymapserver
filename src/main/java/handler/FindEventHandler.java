package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import service.FindEventService;
import request.FindEventRequest;
import result.FindEventResult;

import java.io.IOException;

/**
 * Handles /event/[eventID]
 */
public class FindEventHandler implements HttpHandler {
    /**
     * Finds the specified event.
     */
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            if (HttpUtil.usedMethod(httpExchange, "get")) {
                String authTokenID = HttpUtil.getAuthorization(httpExchange);

                String[] URIParameters = httpExchange.getRequestURI().getRawPath().split("/");
                String eventID = URIParameters[2];

                FindEventRequest request = new FindEventRequest(eventID, authTokenID);
                FindEventResult result = new FindEventService().find(request);

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
