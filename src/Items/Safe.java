package Items;

import Exceptions.WrongSafeCodeException;

import java.util.ArrayList;

/**
 * Represents a safe, which acts as a container for {@link Item} instances.
 * <p>
 * Fields:
 * <ul>
 *     <li>{@link #code} – the code of the safe</li>
 *     <li>{@link #startPointer} – the starting position of the pointer (0–99)</li>
 *     <li>{@link #currentPointer} – the current position of the pointer (0–99)</li>
 * </ul>
 * </p>
 * <p>
 * Player can open the safe and access the content.
 * </p>
 *
 * @author Matěj Pospíšil
 */
public class Safe {


    private ArrayList<Item> items;
    private String code;
    private int startPointer;
    private int currentPointer;

    public Safe(String code, int startPointer, ArrayList<Item> items) {
        this.items = items;
        this.code = code;
        this.startPointer = startPointer;
        this.currentPointer = startPointer;
    }

    public Safe() {
    }

    /**
     * Attempts to open the safe using a given code.
     * <p>
     * The code must be in the format: L/RX;L/RY;L/RZ... (e.g., "L10;R20;L5").
     * Each part of the code consists of a letter and a number:
     * <ul>
     *     <li>R – move the pointer to the right by the given amount</li>
     *     <li>L – move the pointer to the left by the given amount</li>
     * </ul>
     * </p>
     * <p>
     * The number indicates the amount by which the {@link #currentPointer} is moved.
     * After each move, if {@link #currentPointer} falls outside the interval 0–99,
     * modulo 100 is applied.
     * </p>
     * <p>
     * After every move, if the {@link #currentPointer} points to 0, a counter
     * ({@code password}) is incremented. The code is correct if the final counter
     * equals the safe's code.
     * </p>
     *
     * @param code the code to try opening the safe
     * @return true if the code is correct, false otherwise
     * @throws WrongSafeCodeException if the code format does not match L/RX;L/RY;L/RZ...
     */
    public boolean openSafe(String code) throws WrongSafeCodeException {
        code = code.replaceAll(" ", "");
        String[] data = code.split(";");
        int password = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i].contains("R")) {
                data[i] = data[i].replaceAll("R", "");
                try {
                    currentPointer += Integer.parseInt(data[i]);
                } catch (NumberFormatException e) {
                    throw new WrongSafeCodeException("Kód " + code + " není správný, či srávně zapsaný");
                }
            } else {
                data[i] = data[i].replaceAll("L", "");
                try {
                    currentPointer -= Integer.parseInt(data[i]);
                } catch (NumberFormatException e) {
                    throw new WrongSafeCodeException("Kód " + code + " není správný, či srávně zapsaný");
                }
            }
            currentPointer = currentPointer % 100;
            if (currentPointer == 0) {
                password++;
            }
        }
        return password == Integer.parseInt(this.code);
    }

    /**
     * Returns all items from the safe and clears its contents.
     */
    public ArrayList<Item> dropItems() {
        ArrayList<Item> temp = new ArrayList<>(items);
        this.items.clear();
        return temp;
    }


    public void resetCurrentPointer() {
        currentPointer = startPointer;
    }

    public boolean isLocked() {
        return !items.isEmpty();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public int getCurrentPointer() {
        return currentPointer;
    }

    public void setCurrentPointer(int currentPointer) {
        this.currentPointer = currentPointer;
    }

    public int getStartPointer() {
        return startPointer;
    }

    public void setStartPointer(int startPointer) {
        this.startPointer = startPointer;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
