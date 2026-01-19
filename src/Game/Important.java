package Game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Important {


    public static String colourMap(String colour) {
        HashMap<String, String> colourMap = loadColourMap("res\\colours.csv");
        return colourMap.get(colour.toLowerCase());
    }

    private static HashMap<String, String> loadColourMap(String pathName) {
        try (BufferedReader br = new BufferedReader(new FileReader(pathName))) {
            HashMap<String, String> temp = new HashMap<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(">");
                temp.put(data[0], data[1]);
            }
            return temp;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

