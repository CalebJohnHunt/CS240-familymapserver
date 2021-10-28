package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import service.LoadService;
import service.request.LoadRequest;
import service.result.LoadResult;

import java.io.IOException;
import java.io.InputStream;

public class LoadHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            if (HttpUtil.usedMethod(httpExchange, "post")) {
                InputStream reqBody = httpExchange.getRequestBody();
                String reqData = HttpUtil.readString(reqBody);


                LoadRequest request = (LoadRequest) JSONHandler.jsonToObject(reqData, LoadRequest.class);
                LoadResult result = new LoadService().load(request);
                System.out.println("hey");

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
