package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.file.Files;

/**
 * Default handler of http requests.
 */
public class FileHandler implements HttpHandler {

    /**
     * Serves relevant files. This includes index, css, favicon, and 404 error pages.
     */
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        if (Utility.usedMethod(httpExchange, "get")) {

            String urlPath = httpExchange.getRequestURI().toString();
            if (urlPath.equals("") || urlPath.equals("/")) {
                urlPath = "/index.html";
            }
            urlPath = "web/" + urlPath;

            File file = new File(urlPath);

            OutputStream respBody = httpExchange.getResponseBody();

            if (file.exists()) {
                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                Files.copy(file.toPath(), respBody);
            } else {
                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
                Files.copy(new File("web/HTML/404.html").toPath(), respBody);
            }
            respBody.close();
        } else {
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, 0);
            httpExchange.getResponseBody().close();
        }
    }
}
