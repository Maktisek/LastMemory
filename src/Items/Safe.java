package Items;

import Exceptions.WrongSafeCodeException;

import java.util.ArrayList;

public class Safe {


    private ArrayList<Item> items;
    private String code;
    private int startPointer;
    private int currentPointer;

    public Safe(String code, int startPointer) {
        this.items = new ArrayList<>();
        this.code = code;
        this.startPointer = startPointer;
        this.currentPointer = startPointer;
    }

    public Safe() {
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

    public boolean openSafe(String code) throws WrongSafeCodeException {
        code = code.replaceAll(" ", "");
        String[] data = code.split(";");
        int password = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i].contains("R")) {
                data[i] = data[i].replaceAll("R", "");
                try {
                    currentPointer += Integer.parseInt(data[i]);
                }catch (NumberFormatException e){
                    throw new WrongSafeCodeException("Kód " + code + " není správný, či srávně zapsaný");
                }
            } else {
                data[i] = data[i].replaceAll("L", "");
                try {
                    currentPointer -= Integer.parseInt(data[i]);
                }catch (NumberFormatException e){
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
}
