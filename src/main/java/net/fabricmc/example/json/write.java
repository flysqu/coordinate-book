package net.fabricmc.example.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;

public class write {
    public void writeFunctWithGson(int pagenum, String pagename, String coord1, String coord2, String coord3, String coord4, String coord5, String coord6, String coord7, String coord8) {
        JsonObject coords = new JsonObject();
        coords.addProperty("coord1", coord1);
        coords.addProperty("coord2", coord2);
        coords.addProperty("coord3", coord3);
        coords.addProperty("coord4", coord4);
        coords.addProperty("coord5", coord5);
        coords.addProperty("coord6", coord6);
        coords.addProperty("coord7", coord7);
        coords.addProperty("coord8", coord8);
        coords.addProperty("pagename", pagename);

        JsonArray coordList = new JsonArray();
        coordList.add(coords);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter file = new FileWriter("config//coordpage" + pagenum + ".json")) {
            gson.toJson(coordList, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
