package net.fabricmc.example.json;

import com.google.gson.Gson;
import org.json.simple.JSONArray;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class read {
    public static String readfunct(int num) throws FileNotFoundException {
            // Get coordinates from json file
            BufferedReader reader = new BufferedReader(new FileReader("config//coords.json"));
        
            JSONArray array = new Gson().fromJson(reader, JSONArray.class);

            String[] ar = array.toString().split("[,]", 0);
            //.substring(, ar[0].indexOf(":"))

            String coord = ar[num].substring(ar[num].indexOf(":") + 1).replaceAll("\"", "").replace("}]", "").toString();
    
            return coord;
    }
        
}
