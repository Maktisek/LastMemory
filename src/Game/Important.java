package Game;

import AudioSystem.AudioLibrary;

import java.io.*;
import java.util.*;

/**
 * Utility class containing static methods used throughout the game.
 * <p>
 * Internally, it uses {@link #sc} as a {@link Scanner} for reading player input
 * and {@link #audioLibrary} as an {@link AudioLibrary} for managing game sounds.
 * </p>
 *
 * @author Matěj Pospíšil
 */
public class Important {


    private static final Scanner sc = new Scanner(System.in);
    private static final AudioLibrary audioLibrary = AudioLibrary.loadAudioLibrary();

    /**
     * Changes the way how the text is written into the console.
     *
     * @param update the desired update (all of them can be found in res\CsvFiles\textUpdate.csv)
     * @param text   the text to be updated
     * @return the changed text surrounded by ESC sequences
     */
    public static String changeText(String update, String text) {
        return colourMap(update.toLowerCase()) + text + colourMap("default");
    }

    /**
     * Returns the ANSI escape code corresponding to a text style or color.
     * <p>
     * The available styles are loaded via {@link #loadTextUpdater()}.
     * </p>
     *
     * @param update the name of the text style or color to look up
     * @return the ANSI escape code for the requested style; returns the default reset code if not found
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
     * Reads the CSV file "/CsvFiles/textUpdate.csv" and builds a map of text styles to their ANSI escape codes.
     * <p>
     * Each line in the CSV should have the format "style>code", where "style" is the name of the text style
     * and "code" is its corresponding ANSI escape sequence.
     * </p>
     *
     * @return a HashMap where keys are style names and values are their corresponding ANSI escape codes
     */
    private static HashMap<String, String> loadTextUpdater() {
        InputStream input = Important.class.getResourceAsStream("/CsvFiles/textUpdate.csv");
        if(input == null){
            throw new RuntimeException(Important.changeText("red", "The file"+ "/CsvFiles/textUpdate.csv" + "does not exist."));
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
            HashMap<String, String> temp = new HashMap<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(">");
                temp.put(data[0], data[1]);
            }
            return temp;
        } catch (Exception e) {
            throw new RuntimeException(Important.changeText("red", "There is a problem with the"+ "/CsvFiles/textUpdate.csv" + " file"));
        }
    }

    /**
     * Helps with displaying String ArrayLists in way of X, Y, Z.
     * <p>
     * For cycle loads Strings into {@link StringBuilder} in a way of: String + ", ". The cycle stops right before the last one,
     * then the last one is appended into {@link StringBuilder} without ", " right after.
     * </p>
     *
     * @param input the ArrayList to be written
     * @return the ArrayList content in way of X, Y, Z
     */
    public static String writeStringArrays(ArrayList<String> input) {
        if (!input.isEmpty()) {
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < input.size() - 1; i++) {
                line.append(Important.changeText("underline", input.get(i))).append(", ");
            }
            line.append(Important.changeText("underline", input.get(input.size() - 1)));
            return line.toString();
        }
        return null;
    }

    /**
     * Reads a text file and returns it as a single String with optional indentation for each line.
     *
     * @param pathName the path to the text file
     * @param space    the number of spaces to prepend to each line
     * @return the content of the file as a String, indented by {@code space} spaces; if the file is missing, returns an error message
     */
    public static String readTxtFiles(String pathName, int space) {
        InputStream input = Important.class.getResourceAsStream(pathName);
        if(input == null){
            throw new RuntimeException(Important.changeText("red", "The "+ pathName +" file does not exist."));
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
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
        } catch (Exception e) {
            return Important.changeText("red", "There is a problem with "+ pathName +"file");
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

    /**
     * Loads a String as input from the player.
     * <p>
     * Reads a line of text from the Scanner. If the Scanner is closed
     * or no more input is available, "Invalid input" is returned.
     * </p>
     *
     * @return the input String from the player, or "Invalid input" if reading fails
     */
    public static String loadText() {
        String result;
        try {
            result = sc.nextLine().toLowerCase();
        } catch (NoSuchElementException | IllegalStateException e) {
            return "Invalid input";
        }
        audioLibrary.playAudio("keyboard click");
        return result;
    }

    /**
     * Sleeps the thread for desired seconds.
     * <p>
     * The given seconds are converted into milliseconds for Thread.sleep().
     * </p>
     *
     * @param inputSeconds the amount of time in seconds the thread will wait
     */
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

    /**
     * Chooses and returns a random line from a text (.txt) or CSV (.csv) file located at {@code pathName}.
     *
     * @param pathName the path to the file to read from
     * @return a randomly chosen line from the file; if the file cannot be read,
     * a message indicating the problem is returned
     */
    public static String randomLineReader(String pathName) {
        InputStream input = Important.class.getResourceAsStream(pathName);
        if(input == null){
            throw new RuntimeException(Important.changeText("red", "The"+ pathName +"file does not exist."));
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
            ArrayList<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            if (lines.isEmpty()) {
                Important.changeText("red", "Soubor " + pathName + " je prázdný.");
            }
            return lines.get(new Random().nextInt(0, lines.size()));
        } catch (Exception e) {
            return Important.changeText("red", "There is a problem with "+ pathName +"file");
        }
    }

    public static String writeDashes(int amount) {
        String dash = "-";
        return changeText("bold", dash.repeat(amount));
    }

    /**
     * Finds the length of the longest line in a String array.
     * <p>
     * Each line is stripped of any ANSI escape sequences (e.g., color codes) using the regex "\\u001B\\[[;\\d]*m".
     * </p>
     *
     * @param input         the array of Strings to check
     * @param amountOfLines the number of lines from the array to check
     * @return the length of the longest line
     */
    public static int findLongestLine(String[] input, int amountOfLines) {
        int longest = 0;
        for (int i = 0; i < amountOfLines; i++) {
            input[i] = input[i].replaceAll("\\u001B\\[[;\\d]*m", "");
            if (input[i].length() > longest) {
                longest = input[i].length();
            }
        }
        return longest;
    }

    /**
     * Formats a string with a heading surrounded by dashes, like ------Head Text------.
     * <p>
     * The number of dashes on each side is calculated as
     * (the length of the longest line in {@code input} - length of {@code headText}) / 2.
     * ANSI escape sequences in {@code headText} are ignored for this calculation.
     * </p>
     *
     * @param input the input string to display below the heading
     * @param headText the heading text to display between the dashes
     * @return a formatted string with the heading surrounded by dashes, followed by the original input
     */
    public static String dashToString(String input, String headText) {
        String[] data = input.split("\n");
        int longest = findLongestLine(data, data.length);
        String plainHeadText = headText.replaceAll("\\u001B\\[[;\\d]*m", "");
        longest = longest - plainHeadText.length();
        return writeDashes(longest / 2) + changeText("bold", changeText("underline", headText)) + writeDashes(longest / 2) + "\n" + input;
    }

    /**
     * Positions an ASCII art heading text so that it is centered relative to a given text.
     * <p>
     * If {@code headText} is longer than {@code text}, it is returned unchanged.
     * Otherwise, {@code headText} is moved by {@link #moveAsciiText(String, int)}
     * by half of the difference between the longest line in {@code text} and {@code headText}.
     * </p>
     *
     * @param text the text to use as a reference for centering
     * @param headText the heading text to be centered
     * @return the centered heading text; if it is not moved, the original head text is returned
     */
    public static String asciiHeadTextHelper(String text, String headText) {
        String[] splitScene = text.split("\n");
        String[] splitHeadText = headText.split("\n");
        int longest = findLongestLine(splitScene, countLines(text));
        int headLength = findLongestLine(splitHeadText, splitHeadText.length);
        if (longest > headLength) {
            int move = (longest - headLength) / 2;
            return moveAsciiText(headText, move);
        }
        return headText;
    }

    /**
     * Moves ASCII art to the right by desired amount of space.
     * @param ascii the ASCII to be moved
     * @param move the amount of spaces for the ASCII art to be moved
     * @return the moved ASCII art
     */
    public static String moveAsciiText(String ascii, int move) {
        String[] data = ascii.split("\n");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            if (i != data.length - 1) {
                sb.append(writeBlank(move)).append(data[i]).append("\n");
            } else {
                sb.append(writeBlank(move)).append(data[i]);
            }
        }
        return sb.toString();
    }

    /**
     * Counts the number of lines in a given string.
     * <p>
     * If the string contains fewer than 5 lines, returns the actual number of lines.
     * Otherwise, returns 5.
     * </p>
     *
     * @param input the string whose lines will be counted
     * @return the number of lines, capped at 5
     */
    public static int countLines(String input) {
        String[] data = input.split("\n");
        return Math.min(data.length, 5);
    }
}

