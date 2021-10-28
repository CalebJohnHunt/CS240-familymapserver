package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import service.GetFamilyEventsService;
import service.request.GetFamilyEventsRequest;
import service.result.GetFamilyEventsResult;

import java.io.IOException;

public class GetFamilyEventsHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            if (HttpUtil.usedMethod(httpExchange, "get")) {
                String authTokenID = HttpUtil.getAuthorization(httpExchange);

                GetFamilyEventsRequest request = new GetFamilyEventsRequest(authTokenID);
                GetFamilyEventsResult result = new GetFamilyEventsService().getFamilyEvents(request);

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
