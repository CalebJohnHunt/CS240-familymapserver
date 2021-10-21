package Handlers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JSONHandler {
    public static void main(String[] args) {
        File file = new File("json/locations.json");

        try (FileReader fileReader = new FileReader(file)) {

            JsonObject root = (JsonObject) JsonParser.parseReader(fileReader);

            JsonArray names = (JsonArray) root.get("data");

            for (JsonElement j : names) {
                System.out.println(j);
            }

        } catch (IOException e) {
            System.out.println("File not found: " + file.toPath());
        }
    }
}
