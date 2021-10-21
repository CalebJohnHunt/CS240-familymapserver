package handler;

import com.google.gson.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;

public class JSONHandler {

    static Gson gson = new Gson();

    public static String objectToJson(Object obj) {
        return gson.toJson(obj);
    }

    public static Object jsonToObject(String json, Type c) {
        return gson.fromJson(json, c);
    }

    public static void objectToJsonWriter(Object obj, Writer os) throws IOException {
        String json = objectToJson(obj);
        os.write(json);
    }
}
