package net.fabricmc.example.json;

import net.fabricmc.example.gui.CoordBookGUI;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class write {
    public void writefunct(String coord1, String coord2, String coord3, String coord4, String coord5, String coord6, String coord7, String coord8) {
        JSONObject coords = new JSONObject();

        coords.put("coord1", coord1);
        coords.put("coord2", coord2);
        coords.put("coord3", coord3);
        coords.put("coord4", coord4);
        coords.put("coord5", coord5);
        coords.put("coord6", coord6);
        coords.put("coord7", coord7);
        coords.put("coord8", coord8);

        JSONArray coordlist = new JSONArray();
        coordlist.add(coords);

        try (FileWriter file = new FileWriter("config//coords.json")) {
            file.write(coordlist.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
