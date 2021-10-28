package handler;

import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.Locale;

/**
 * A class of useful functions for handlers
 */
public class Utility {
    /**
     * Read all the input from a stream and return it as a String.
     * @param is the input stream to be read.
     * @return the String with all the input from the input stream.
     */
    public static String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

    /**
     * Read all the input from a reader and return it as a String.
     * @param r the reader to be read.
     * @return the String with all the input from the input stream.
     */
    public static String readString(Reader r) throws IOException {
        StringBuilder sb = new StringBuilder();
        char[] buf = new char[1024];
        int len;
        while ((len = r.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

    public static boolean usedMethod(HttpExchange exchange, String method) {
        return exchange.getRequestMethod().toLowerCase(Locale.ROOT).equals(method.toLowerCase(Locale.ROOT));
    }

    public static void writeSuccessfulResult(Object result, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        Writer resBody = new OutputStreamWriter(exchange.getResponseBody());
        JSONHandler.objectToJsonWriter(result, resBody);
        resBody.close();
    }
}
