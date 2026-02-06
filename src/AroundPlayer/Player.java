package AroundPlayer;

import Cutscenes.CutsceneLoader;
import Game.Important;
import Items.Item;
import Items.Task;
import Locations.Location;
import Modes.IntroMode;
import Modes.LocationMode;
import Modes.Mode;
import Modes.QuestionMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * This class represents a player, who is the most essential part of the whole game.
 * All the important information are being stored here. <p> Such as: <p>
 * {@link #currentLocation} represents a location, in which the player currently is. <p>
 * {@link #previousLocation} represents a location, in which the player was one movement ago. <p>
 * {@link #collectedMemories} represents an ArrayList of already collected memories. <p>
 * {@link #doneTasks} represents an ArrayList of already done tasks. <p>
 * {@link #mode} stands for currently selected mode. <p>
 * {@link #cutscenes} represents a storage of all possible cutscenes, which can be seen through the gameplay.
 *
 * @author Matěj Pospíšil
 */
public class Player {

    private Inventory inventory;
    private ArrayList<Memory> collectedMemories;
    private ArrayList<Task> doneTasks;
    private Task currentTask;
    private Location currentLocation;
    private Location previousLocation;
    private Mode mode;
    private CutsceneLoader cutscenes;

    /**
     * Special constructor, which prepares the player for the start of the game.
     * @param startLocation the location, in which the player will start.
     */
    public Player(Location startLocation) {
        this.inventory = new Inventory();
        this.collectedMemories = new ArrayList<>();
        this.doneTasks = new ArrayList<>();
        this.currentTask = null;
        this.currentLocation = startLocation;
        this.previousLocation = null;
        this.mode = new IntroMode();
        this.cutscenes = CutsceneLoader.loadCutscenes();
    }

    public Player() {
    }

    public boolean addMemory(Memory memory) {
        return collectedMemories.add(memory);
    }

    private void sortMemories() {
        Collections.sort(collectedMemories);
    }

    public boolean hasCollectedMemory(String name) {
        for (Memory memory : collectedMemories) {
            if (memory.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }


    public String writeMemory(String name) {
        for (Memory memory : collectedMemories) {
            if (memory.getName().equalsIgnoreCase(name)) {
                memory.switchOpened();
                String ascii = Important.readTxtFiles("res\\TextFiles\\asciiMemory.txt", 0);
                String headText = Important.asciiHeadTextHelper(memory.getDescription(), ascii);
                return Important.writeSpace(40) + Important.changeText("bold", Important.changeText("pink", headText) + "\n" + memory.getDescription());
            }
        }
        return Important.changeText("red", "Vzpomínka " + Important.changeText("underline", name) + " neexistuje");
    }

    public boolean hasOldTask(String name) {
        for (Task task : doneTasks) {
            if (task.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }


    public String writeOldTask(String name) {
        for (Task task : doneTasks) {
            if (task.getName().equalsIgnoreCase(name)) {
                return task.getName() + "\n" + task.getDescription();
            }
        }
        return "";
    }

    public boolean addCurrentTask(Task task) {
        if (currentTask == null) {
            this.currentTask = task;
            return true;
        }
        return false;
    }

    public void deleteTask() {
        this.doneTasks.add(currentTask);
        currentTask = null;
    }

    /**
     * Represents the change of current location and stands for the most important part of the whole movement system.
     * {@link #currentLocation} is moved to {@link #previousLocation} before changing.
     * If the new current location has an enemyNPC in, then the mode is switched into question mode.
     * This is checked using the {@link #currentLocation}'s isFree() method
     *
     * @param location The location to be changed into.
     * @return true if the operation went successful, false if not (happens only if the param location is null)
     */
    public boolean switchLocation(Location location) {
        if (location != null) {
            previousLocation = currentLocation;
            currentLocation = location;
            if (!currentLocation.isFree()) {
                switchMode(new QuestionMode());
                return true;
            }
            return true;
        }
        return false;
    }


    /**
     * Represents a switch between {@link #currentLocation} and {@link #previousLocation}. <p>
     * It has to change {@link #mode} to {@link LocationMode}, when the {@link #previousLocation} is not null.
     * @return true if the {@link #previousLocation} was not null, false if {@link #previousLocation} was null.
     */
    public boolean runAway() {
        if (previousLocation != null) {
            currentLocation = previousLocation;
            previousLocation = null;
            switchMode(new LocationMode());
            return true;
        }
        return false;
    }

    /**
     * Represents a scan of {@link #collectedMemories} in order to find new locations, which can be then added into current location.
     * It goes sequentially through {@link #collectedMemories} and if the giftLocation is not null and the code of the memory matches with the code of {@link #currentLocation}, then
     * new location is added into {@link #currentLocation}'s possible locations list.
     *
     * @return Names of added location names.
     */
    public String scanAndAddPossibleLocations() {
        ArrayList<String> names = new ArrayList<>();
        for (Memory memory : collectedMemories) {
            if (memory.getLocationGift() != null && memory.getCode().equalsIgnoreCase(currentLocation.getCode())) {
                Location temp = memory.giveLocation();
                currentLocation.addPossibleLocation(temp);
                names.add(Important.changeText("underline", temp.getName()));
            }
            if (!names.isEmpty()) {
                return "Nové odemklé lokace: " + String.join(", ", names);
            }
        }
        return null;
    }

    public boolean canEnd() {
        return collectedMemories.size() == 10;
    }

    public void switchMode(Mode mode) {
        if (mode != null) {
            this.mode = mode;
        }
    }

    public String writeMemories() {
        ArrayList<String> names = new ArrayList<>();
        sortMemories();
        for (Memory memory : collectedMemories) {
            names.add(Important.changeText("underline", memory.writeName()));
        }
        if (names.isEmpty()) {
            return Important.changeText("red", "Doposud nebyly posbírané žádné vzpomínky");
        }
        return String.join(", ", names);
    }

    public String writeDoneTasks() {
        ArrayList<String> names = new ArrayList<>();
        for (Task task : doneTasks) {
            names.add(task.getName());
        }
        if (names.isEmpty()) {
            return Important.changeText("red", "Doposud nebyly splněny žádné úkoly");
        }
        return String.join(", ", names);
    }

    /**
     * This method represents a system, which decides if the cutscene can be played.
     * @return true if canned, false if not.
     */
    public boolean canPlayCutscene() {
        if (cutscenes.peekCutscene() == null) {
            return false;
        }
        return this.collectedMemories.size() == cutscenes.peekCutscene().getRequiredMemories();
    }


    @Override
    public String toString() {
        return mode.executeInfo(this);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Mode getMode() {
        return mode;
    }

    public Mode specialGetMode() {
        if (!mode.special()) {
            return mode;
        }
        return new LocationMode();
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Location getPreviousLocation() {
        return previousLocation;
    }

    public void setPreviousLocation(Location previousLocation) {
        this.previousLocation = previousLocation;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
    }

    public ArrayList<Memory> getCollectedMemories() {
        return collectedMemories;
    }

    public void setCollectedMemories(ArrayList<Memory> collectedMemories) {
        this.collectedMemories = collectedMemories;
    }

    public ArrayList<Task> getDoneTasks() {
        return doneTasks;
    }

    public void setDoneTasks(ArrayList<Task> doneTasks) {
        this.doneTasks = doneTasks;
    }

    public CutsceneLoader getCutscenes() {
        return cutscenes;
    }

    public void setCutscenes(CutsceneLoader cutscenes) {
        this.cutscenes = cutscenes;
    }

    /**
     * This class represents an inventory system.
     * @author Matěj Pospíšil
     */
    public static class Inventory {
        private final double capacity;
        private double weight;
        private HashMap<String, ArrayList<Item>> items;

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
                    return items.get(key).get(0).getDescription();
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
}
