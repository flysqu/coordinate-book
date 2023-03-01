package net.lop01.coordkeeper.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.lop01.coordkeeper.encryptdecrypt.encryptdecrypt;

import java.io.FileWriter;
import java.io.IOException;

import java.util.concurrent.atomic.AtomicReference;
public class write {

    public void writeFunctWithGson(AtomicReference<Boolean> isencrypted, String password, int pagenum, String pagename, String coord1, String coord2, String coord3, String coord4, String coord5, String coord6, String coord7, String coord8) {
        JsonObject coords = new JsonObject();

        if (isencrypted.get()) {
            coord1 = encryptdecrypt.RIBase64C(coord1);
            coord2 = encryptdecrypt.RIBase64C(coord2);
            coord3 = encryptdecrypt.RIBase64C(coord3);
            coord4 = encryptdecrypt.RIBase64C(coord4);
            coord5 = encryptdecrypt.RIBase64C(coord5);
            coord6 = encryptdecrypt.RIBase64C(coord6);
            coord7 = encryptdecrypt.RIBase64C(coord7);
            coord8 = encryptdecrypt.RIBase64C(coord8);

            coord1 = encryptdecrypt.encrypt(password, coord1);
            coord2 = encryptdecrypt.encrypt(password, coord2);
            coord3 = encryptdecrypt.encrypt(password, coord3);
            coord4 = encryptdecrypt.encrypt(password, coord4);
            coord5 = encryptdecrypt.encrypt(password, coord5);
            coord6 = encryptdecrypt.encrypt(password, coord6);
            coord7 = encryptdecrypt.encrypt(password, coord7);
            coord8 = encryptdecrypt.encrypt(password, coord8);

            pagename = encryptdecrypt.encrypt(password, pagename);
        }


        // Hotfix for , problem
        if (coord1.contains(",")) {
            coord1 = coord1.replace(",", "Q");
        }
        if (coord2.contains(",")) {
            coord2 = coord2.replace(",", "Q");
        }
        if (coord3.contains(",")) {
            coord3 = coord3.replace(",", "Q");
        }
        if (coord4.contains(",")) {
            coord4 = coord4.replace(",", "Q");
        }
        if (coord5.contains(",")) {
            coord5 = coord5.replace(",", "Q");
        }
        if (coord6.contains(",")) {
            coord6 = coord6.replace(",", "Q");
        }
        if (coord7.contains(",")) {
            coord7 = coord7.replace(",", "Q");
        }
        if (coord8.contains(",")) {
            coord8 = coord8.replace(",", "Q");
        }



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
