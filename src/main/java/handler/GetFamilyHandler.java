package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import service.GetFamilyService;
import request.GetFamilyRequest;
import result.GetFamilyResult;

import java.io.IOException;

/**
 * Handles /person
 */
public class GetFamilyHandler implements HttpHandler {
    /**
     * Find all family members related to the user.
     */
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            if (HttpUtil.usedMethod(httpExchange, "get")) {
                String authTokenID = HttpUtil.getAuthorization(httpExchange);

                GetFamilyRequest request = new GetFamilyRequest(authTokenID);
                GetFamilyResult result = new GetFamilyService().getFamily(request);

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
