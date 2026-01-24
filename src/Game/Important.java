package Game;

import AudioSystem.AudioLibrary;
import Locations.Location;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Important {



    private static final Scanner sc = new Scanner(System.in);
    private static final AudioLibrary audioLibrary = new AudioLibrary();


    public static String changeText(String update, String text) {
        return colourMap(update.toLowerCase()) + text + colourMap("default");
    }

    /**
     * It finds text update ANSI escape code in a hashMap.
     *
     * @param update the text update to be found
     * @return the ANSI escape code of the wanted update. If no update was found then it returns white colour as a default.
     */
    public static String colourMap(String update) {
        HashMap<String, String> textUpdate = loadTextUpdater();
        if (textUpdate.containsKey(update)) {
            return textUpdate.get(update.toLowerCase());
        } else {
            return "\u001B[0m";
        }
    }

    /**
     * It creates hashMap and load colours into it. The colours are loaded from a specific .csv file
     *
     * @return the made hashMap
     */
    private static HashMap<String, String> loadTextUpdater() {
        try (BufferedReader br = new BufferedReader(new FileReader("res\\textUpdate.csv"))) {
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

    /**
     * Helps to write longer texts. Every 10 words are seperated into individual lines which are added into an array list and then
     * the method uses static method String.join to return the lines.
     *
     * @param text the text to be written
     * @return the text seperated into individual lines
     */
    public static String writeLongTexts(String text) {
        ArrayList<String> temp = new ArrayList<>();
        String[] data = text.split(" ");
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            line.append(data[i]).append(" ");
            if (i % 10 == 0 && i != 0) {
                temp.add(line.toString());
                line = new StringBuilder();
            }
        }
        temp.add(line.toString());
        return String.join("\n", temp);
    }

    public static String writeStringArrays(ArrayList<String> input) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < input.size() - 1; i++) {
            line.append(input.get(i)).append(", ");
        }
        line.append(input.get(input.size() - 1));
        return line.toString();
    }

    public static void playLocationSong(Location location) {
        if (location.isFree()) {
            audioLibrary.playAudio(location.getName());
            return;
        }
        audioLibrary.playAudio("test");
    }


    public static void playAudio(String name){
        audioLibrary.playAudio(name);
    }

    public static void stopAudio(String name){
        audioLibrary.stopAudio(name);
    }

    public static String loadText(){
        String result = sc.nextLine();
        audioLibrary.playAudio("keyboard click");
        return result;
    }
}

