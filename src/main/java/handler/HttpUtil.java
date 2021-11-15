package handler;

import Util.JSONHandler;
import com.sun.net.httpserver.HttpExchange;
import result.Result;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.Locale;

/**
 * A class of useful functions for handlers
 */
public final class HttpUtil {
    // Shouldn't be instantiated.
    private HttpUtil(){}

    /**
     * Checks whether the request used a certain method.
     * @param exchange HttpExchange to check.
     * @param method Http method to look for.
     * @return whether the exchange used the specified method.
     */
    public static boolean usedMethod(HttpExchange exchange, String method) {
        return exchange.getRequestMethod().toLowerCase(Locale.ROOT).equals(method.toLowerCase(Locale.ROOT));
    }

    /**
     * Writes an object to an HttpExchange response body.
     * @param result the object to write to the response body.
     * @param exchange the exchange to write to.
     */
    public static void writeSuccessfulResult(Result result, HttpExchange exchange) throws IOException {
        System.out.println(10);
        if (result.isSuccess()) {
            System.out.println(11);
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        }
        else {
            System.out.println(12);
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        }
            System.out.println(13);
        Writer resBody = new OutputStreamWriter(exchange.getResponseBody());
            System.out.println(14);
        JSONHandler.objectToJsonWriter(result, resBody);
            System.out.println(15);
        resBody.close();
    }

    /**
     * Responds to Http Bad Requests by sending the response headers and closing.
     * @param exchange the exchange to respond to.
     */
    public static void handleBadMethod(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        exchange.getResponseBody().close();
    }

    /**
     * Reports to the exchange there was a server error.
     * @param exchange the exchange to respond to.
     */
    public static void handleServerError(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
        exchange.getResponseBody().close();
    }

    /**
     * Gets the authorization ID from the exchange request headers.
     * @param exchange the exchange from which to grab the authorization ID.
     * @return the authorization ID.
     */
    public static String getAuthorization(HttpExchange exchange) throws IOException {
        return exchange.getRequestHeaders().get("authorization").get(0);
    }
}
