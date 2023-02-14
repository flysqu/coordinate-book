package net.fabricmc.example.json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

public class read {
    public static String readfunct(int pagenum, int num) throws FileNotFoundException {
            // Get coordinates from json file
            FileReader reader = new FileReader("config//coordpage" + pagenum + ".json");
        
            JsonArray array = new Gson().fromJson(reader, JsonArray.class);

            String[] ar = array.toString().split("[,]", 0);
            //.substring(, ar[0].indexOf(":"))

            System.out.println(Arrays.toString(ar));

            String coord = ar[num].substring(ar[num].indexOf(":") + 1).replaceAll("\"", "").replace("}]", "").toString();

            return coord;
    }
        
}
