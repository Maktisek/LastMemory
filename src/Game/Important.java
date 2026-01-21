package Game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Important {

    public static String changeColourText(String colour, String text){
        return colourMap(colour) + text + colourMap("default");
    }

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

    public static String writeLongTexts(String text){
        ArrayList<String> temp = new ArrayList<>();
        String[] data = text.split(" ");
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            line.append(data[i]).append(" ");
            if(i % 10 == 0 && i != 0){
                temp.add(line.toString());
                line = new StringBuilder();
            }
        }
        temp.add(line.toString());
        return String.join("\n", temp);
    }



}

