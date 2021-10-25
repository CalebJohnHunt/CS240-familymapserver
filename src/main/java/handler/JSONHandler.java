package handler;

import com.google.gson.*;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;

/**
 * Handles JSON encoding/decoding.
 */
public class JSONHandler {
    /**
     * The main instance of Gson.
     */
    static Gson gson = new Gson();

    /**
     * Converts an object into Json.
     * @param obj the object to convert to json.
     * @return Json as a string.
     */
    public static String objectToJson(Object obj) {
        return gson.toJson(obj);
    }

    /**
     * Converts a Json string to an object.
     * @param json the Json string to turn into an object.
     * @param type the class of the object to convert to.
     * @return the object after converting the json.
     */
    public static Object jsonToObject(String json, Type type) {
        return gson.fromJson(json, type);
    }

    /**
     * Converts an object to Json and writes it to a writer.
     * @param obj the object to convert and write.
     * @param writer the writer to write to.
     */
    public static void objectToJsonWriter(Object obj, Writer writer) throws IOException {
        String json = objectToJson(obj);
        writer.write(json);
    }
}
