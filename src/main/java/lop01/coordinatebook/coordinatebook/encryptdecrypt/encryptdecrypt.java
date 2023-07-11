package lop01.coordinatebook.coordinatebook.encryptdecrypt;
import org.jasypt.util.text.*;

public class encryptdecrypt {
    public static String RIBase64C(String coord) {
        // Stands for Replace Illegal Base64 Chars
        if (coord.contains(" ")) {
            coord = coord.replace(" ", "+");
        }
        if (coord.contains("_")) {
            coord = coord.replace("_", "/");
        }
        if (coord.contains(",")) {
            coord = coord.replace(",", "Q");
        }
        return coord;
    }

    public static String CBTOI(String coord) {
        // Stands for Convert Back To Original Input
        if (coord.contains("+")) {
            coord = coord.replace("+", " ");
        }
        if (coord.contains("/")) {
            coord = coord.replace("/", "_");
        }
        if (coord.contains("Q")) {
            coord = coord.replace("Q", ",");
        }

        return coord;
    }

    public static String encrypt(String pass, String input) {
        BasicTextEncryptor encrypt = new BasicTextEncryptor();
        encrypt.setPassword(pass);

        String encrypted = encrypt.encrypt(input);

        return encrypted;
    }

    public static String decrypt(String pass, String input) {
        BasicTextEncryptor decrypt = new BasicTextEncryptor();
        decrypt.setPassword(pass);

        String decrypted = decrypt.decrypt(input);

        return decrypted;
    }
}