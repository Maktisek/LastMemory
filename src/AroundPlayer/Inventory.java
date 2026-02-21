package AroundPlayer;

import Game.Important;
import Items.Item;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents an inventory system.
 * @author Matěj Pospíšil
 */
public class Inventory {
    private final double capacity;
    private double weight;
    private final HashMap<String, ArrayList<Item>> items;

    public Inventory() {
        this.capacity = 5.0;
        this.weight = 0.0;
        this.items = new HashMap<>();
    }

    /**
     * This method represents a check, which looks if it is possible to collect an item. <p>
     * If the final weight is bigger than {@link #capacity}, then the item cannot be added.
     * @param item The item that could be added.
     * @return false if the item could not be added, true if the item could be added.
     */
    public boolean checkAddCapacity(Item item) {
        if (item != null) {
            double temp = weight + item.getWeight();
            if (temp > capacity) {
                return false;
            } else {
                this.weight = temp;
                return true;
            }
        }
        return false;
    }

    /**
     * Represents a system, which adds an item into player's inventory.<p>
     * When the type of the item is already in the inventory, then the item is just added into the ArrayList on key as item's code in {@link #items}.
     * When not, then a new ArrayList, including the item, is added into {@link #items}.
     * @param item the item to be added.
     * @return True if the item was successfully added either way, false if the item was null.
     */
    public boolean addItem(Item item) {
        if(item != null) {
            if (items.containsKey(item.getCode())) {
                return items.get(item.getCode()).add(item);
            } else {
                ArrayList<Item> temp = new ArrayList<>();
                temp.add(item);
                items.put(item.getCode(), temp);
                return true;
            }
        }
        return false;
    }

    /**
     * Represents a system, which deletes items from {@link #items}.<p>
     * It iterates through {@link #items} and searches for the item of the same name as the parameter is.
     * If the names are matching, then the item is deleted from {@link #items} and the weight is updated.
     * <p>
     * There is no reason to check the capacity after deleting, since there is no way how to change the items weight through the gameplay.
     * @param name the name of the item to be dropped.
     * @return The item if it is found. If not, then null.
     */
    public Item dropItem(String name) {
        for (String key : items.keySet()) {
            if (items.get(key).get(0).getName().equalsIgnoreCase(name)) {
                Item item = items.get(key).get(0);
                double temp = weight - item.getWeight();
                weight = Math.round(temp * 10.0) / 10.0;
                items.get(key).remove(0);
                if (items.get(key).isEmpty()) {
                    items.remove(key);
                }
                return item;
            }
        }
        return null;
    }

    /**
     * Stands for writing all the names and numbers of all items in {@link #items} in a way of [(Y)x name of the item].
     *
     * @return Names and numbers of all items, if there are any. If {@link #items} is empty, then a message about is returned.
     */
    public String writeItems() {
        ArrayList<String> names = new ArrayList<>(10);
        for (String key : items.keySet()) {
            ArrayList<Item> temp = items.get(key);
            names.add(temp.size() + "x " + Important.changeText("underline", temp.get(0).getName()));
        }
        if (names.isEmpty()) {
            return Important.changeText("red", "Batoh je prázdný");
        }
        return String.join(", ", names);
    }

    public boolean isEmpty() {
        return weight == 0;
    }


    public String descriptionItem(String name) {
        for (String key : items.keySet()) {
            if (!items.get(key).isEmpty() && items.get(key).get(0).getName().equalsIgnoreCase(name)) {
                return Important.changeText("underline","Informace o předmětu "+ items.get(key).get(0).getName()+":")+"\nVáha: "+items.get(key).get(0).getWeight()+"\nPopis: "+items.get(key).get(0).getDescription();
            }
        }
        return null;
    }

    public void removeMore(ArrayList<Item> input) {
        for (Item item : input) {
            dropItem(item.getName());
        }
    }

    public double leftSpace() {
        double temp = capacity - ((double) (Math.round(weight * 10)) /10);
        return (double) Math.round((temp * 10)) /10;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getWeight() {
        return weight;
    }

    /**
     * Represents a system made for writing {@link #weight} in different colours based on how many
     * percent does the {@link #weight} makes from {@link #capacity}.
     * <p>
     * The weight is rounded to one decimal place for readability
     * @return The {@link #weight} as a colored string:
     *         <ul>
     *         <li>green if the percentage is ≤50%</li>
     *         <li>yellow if the percentage is >50% but <100%</li>
     *         <li>red if the percentage is 100%</li>
     *         </ul>
     */
    public String writeWeight(){
        double onePercent = capacity / 100;
        double percent = weight / onePercent;
        if(percent <= 50){
            return Important.changeText("green", Double.toString((double) Math.round((weight * 10)) /10));
        } else if(percent < 100){
            return Important.changeText("yellow", Double.toString((double) Math.round((weight * 10)) /10));
        }
        return Important.changeText("red", Double.toString((double) Math.round((weight * 10)) /10));
    }

    public HashMap<String, ArrayList<Item>> getItems() {
        return items;
    }
}