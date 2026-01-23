package Items;

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

    public boolean openSafe(String code) {
        String[] data = code.split(";");
        int password = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i].contains("R")) {
                data[i] = data[i].replaceAll("R", "");
                currentPointer += Integer.parseInt(data[i]);
            } else {
                data[i] = data[i].replaceAll("L", "");
                currentPointer -= Integer.parseInt(data[i]);
            }
            currentPointer = currentPointer % 100;
            if (currentPointer == 0) {
                password++;
            }
        }
        return isDone(password);
    }

    public boolean isDone(int password) {
        return password == Integer.parseInt(code);
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
