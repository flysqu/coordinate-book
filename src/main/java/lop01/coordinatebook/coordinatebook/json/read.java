package lop01.coordinatebook.coordinatebook.json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import lop01.coordinatebook.coordinatebook.encryptdecrypt.encryptdecrypt ;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class read {
    public static String readfunct(boolean encrypt, String password, int pagenum, int num) throws FileNotFoundException {
        // Get coordinates from json file


        FileReader reader = new FileReader("config//coordkeeper//coordpage" + pagenum + ".json");

        JsonArray array = new Gson().fromJson(reader, JsonArray.class);

        String[] ar = array.toString().split("[,]", 0);

        String coord = ar[num].substring(ar[num].indexOf(":") + 1).replaceAll("\"", "").replace("}]", "");


        boolean wrongpass = false;
        // Encryption Stuff
        if (encrypt) {
            try {
                coord = encryptdecrypt.decrypt(password, coord);

                coord = encryptdecrypt.CBTOI(coord);
            } catch (EncryptionOperationNotPossibleException e) {
                System.out.println("Wrong Password Inputed Please Try Again");
                wrongpass = true;
            }
            System.out.println(wrongpass);
        }
        if (coord.contains("Q")) {
            coord = coord.replace("Q", ",");
        }

        // If you have the wrong password return empty string
        // If its something else ie false send decrypted text
        // This should only happen if the user is using encryption if its of do as normal
        if (encrypt) {
            if (wrongpass) {
                return "false";
            } else {
                return coord;
            }
        }else {
            return coord;
        }
    }
}