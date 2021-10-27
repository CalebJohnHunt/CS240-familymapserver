package handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

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
}
