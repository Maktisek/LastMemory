package Game;

import AudioSystem.AudioLibrary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
        try (BufferedReader br = new BufferedReader(new FileReader("res\\CsvFiles\\textUpdate.csv"))) {
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
        if (!line.toString().equalsIgnoreCase("")) {
            temp.add(line.toString());
        }
        return String.join("\n", temp);
    }

    public static String writeStringArrays(ArrayList<String> input) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < input.size() - 1; i++) {
            line.append(Important.changeText("underline", input.get(i))).append(", ");
        }
        line.append(Important.changeText("underline", input.get(input.size() - 1)));
        return line.toString();
    }

    public static String readTxtFiles(String pathName, int space) {
        try (BufferedReader br = new BufferedReader(new FileReader(pathName))) {
            StringBuilder sb = new StringBuilder();
            ArrayList<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            for (int i = 0; i < lines.size(); i++) {
                if (i == lines.size() - 1) {
                    sb.append(writeBlank(space)).append(lines.get(i));
                } else {
                    sb.append(writeBlank(space)).append(lines.get(i)).append("\n");
                }
            }
            return sb.toString();
        } catch (IOException e) {
            return "soubor " + pathName + " ve hře chybí, zkus zkontrolovat herní soubory.";
        }
    }

    public static void playMusic(String name) {
        audioLibrary.playMusic(name);
    }

    public static void playSound(String name) {
        audioLibrary.playAudio(name);
    }

    public static void stopMusic(String name) {
        audioLibrary.stopMusic(name);
    }

    public static void stopSound(String name) {
        audioLibrary.stopSound(name);
    }

    public static void pause(String name) {
        audioLibrary.pause(name);
    }

    public static void resume(String name) {
        audioLibrary.resume(name);
    }

    public static String loadText() {
        String result = sc.nextLine();
        audioLibrary.playAudio("keyboard click");
        return result;
    }

    public static void waitConsole(double inputSeconds) {
        try {
            Thread.sleep((long) (inputSeconds * 1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String writeSpace(int lines) {
        return "\n".repeat(Math.max(0, lines));
    }

    public static String writeBlank(int amount) {
        return " ".repeat(amount);
    }


    public static String randomLineReader(String pathName) {
        try (BufferedReader br = new BufferedReader(new FileReader(pathName))) {
            ArrayList<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            return lines.get(new Random().nextInt(0, lines.size() - 1));
        } catch (IOException e) {
            return "soubor " + pathName + " ve hře chybí, zkus zkontrolovat herní soubory.";
        }
    }

    public static String writeDash(int amount) {
        String dash = "-";
        return changeText("bold", dash.repeat(amount));
    }


    public static int findLongestLine(String[] input, int amoutOfLines){
        int longest = 0;
        for (int i = 0; i < amoutOfLines; i++) {
            input[i] = input[i].replaceAll("\\u001B\\[[;\\d]*m", "");
            if (input[i].length() > longest) {
                longest = input[i].length();
            }
        }
        return longest;
    }

    public static String dashToString(String input, String headText) {
        String[] data = input.split("\n");
        int longest = findLongestLine(data, data.length);
        String plainHeadText = headText.replaceAll("\\u001B\\[[;\\d]*m", "");
        longest = longest - plainHeadText.length();
        return writeDash(longest / 2) + changeText("bold", changeText("underline", headText)) + writeDash(longest / 2) + "\n" + input;
    }

    public static String asciiHeadTextHelper(String scene, String headText) {
        String[] splitScene = scene.split("\n");
        String[] splitHeadText = headText.split("\n");
        int longest = findLongestLine(splitScene, 5);
        int headLength = findLongestLine(splitHeadText, splitHeadText.length);
        if(longest > headLength){
            int move = (longest - headLength) / 2;
            return moveAsciiText(headText, move);
        }
        return headText;
    }

    public static String moveAsciiText(String ascii, int move){
        String[] data = ascii.split("\n");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            if(i != data.length - 1){
                sb.append(writeBlank(move)).append(data[i]).append("\n");
            }else {
                sb.append(writeBlank(move)).append(data[i]);
            }
        }
        return sb.toString();
    }
}

