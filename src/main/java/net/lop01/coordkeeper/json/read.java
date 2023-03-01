package net.lop01.coordkeeper.json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import net.lop01.coordkeeper.encryptdecrypt.encryptdecrypt;

import java.io.FileNotFoundException;
import java.io.FileReader;


public class read {
    public static String readfunct(boolean encrypt, String password, int pagenum, int num) throws FileNotFoundException {
        // Get coordinates from json file
        FileReader reader = new FileReader("config//coordpage" + pagenum + ".json");

        JsonArray array = new Gson().fromJson(reader, JsonArray.class);

        String[] ar = array.toString().split("[,]", 0);

        String coord = ar[num].substring(ar[num].indexOf(":") + 1).replaceAll("\"", "").replace("}]", "");

        // Encryption Stuff
        if (encrypt) {
            coord = encryptdecrypt.decrypt(password, coord);

            coord = encryptdecrypt.CBTOI(coord);
        }

        if (coord.contains("Q")) {
            coord = coord.replace("Q", ",");
        }

        return coord;
    }
}
