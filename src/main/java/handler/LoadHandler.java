package handler;

import Util.JSONHandler;
import Util.ReadString;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import service.LoadService;
import request.LoadRequest;
import result.LoadResult;

import java.io.IOException;
import java.io.InputStream;

/**
 * Handles /load
 */
public class LoadHandler implements HttpHandler {
    /**
     * Clears the database and loads it with new data.
     */
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            if (HttpUtil.usedMethod(httpExchange, "post")) {
                InputStream reqBody = httpExchange.getRequestBody();
                String reqData = ReadString.read(reqBody);


                LoadRequest request = (LoadRequest) JSONHandler.jsonToObject(reqData, LoadRequest.class);
                LoadResult result = new LoadService().load(request);

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
