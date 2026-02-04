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

public class Player {


    private Inventory inventory;
    private ArrayList<Memory> collectedMemories;
    private ArrayList<Task> doneTasks;
    private Task currentTask;
    private Location currentLocation;
    private Location previousLocation;
    private Mode mode;
    private CutsceneLoader cutscenes;

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
                return Important.writeSpace(40) + memory.getDescription();
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
        return "There is a mistake in the game";
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
     * Switches the current location. The current location is set to previous location before changing.
     * If the new current location has an enemyNPC, then the mode is switched into question mode.
     *
     * @param location The location to be changed
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
     * Switches current location with previous location if it's possible. Then it sets the previous location to null.
     */
    public void runAway() {
        if (previousLocation != null) {
            currentLocation = previousLocation;
            previousLocation = null;
            switchMode(new LocationMode());
        }
    }

    /**
     * Scans whole array list of memories in order to find new locations, which can be added into current location. It goes through
     * collected memories and if the gift location isn't null and the code of the memory matches with the code of the location, then
     * new location will be added into current location possible locations list.
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

    /**
     * Switches player's mode.
     *
     * @param mode The node to be changed
     */
    public void switchMode(Mode mode) {
        if (mode != null) {
            this.mode = mode;
        }
    }

    /**
     * Writes all collected memories names.
     *
     * @return All the names of collected memories
     */
    public String writeMemories() {
        ArrayList<String> names = new ArrayList<>();
        sortMemories();
        for (Memory memory : collectedMemories) {
            names.add(Important.changeText("underline", memory.writeName()));
        }
        if (names.isEmpty()) {
            return "Doposud nebyly posbírané žádné vzpomínky";
        }
        return String.join(", ", names);
    }

    /**
     * Writes all write done tasks names.
     *
     * @return All the names of done tasks
     */
    public String writeDoneTasks() {
        ArrayList<String> names = new ArrayList<>();
        for (Task task : doneTasks) {
            names.add(task.getName());
        }
        if (names.isEmpty()) {
            return "Doposud nebyly splněny žádné úkoly";
        }
        return String.join(", ", names);
    }

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
         * Checks if the item could be added.
         *
         * @param item The item that could be added.
         * @return false if the item could not be added, false if the item could not be added.
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
         * Checks if the item could be dropped.
         *
         * @param item The item that could be dropped.
         * @return false if the item could not be dropped, false if the item could not be dropped.
         */
        public boolean checkDropCapacity(Item item) {
            if (item != null) {
                double temp = weight - item.getWeight();
                temp = Math.round(temp * 10.0) / 10.0;
                if (temp < 0) {
                    return false;
                } else {
                    this.weight = temp;
                }
                return true;
            }
            return false;
        }

        /**
         * Adds item to items. It checks if there is any array list on the item code in the map.
         * If yes then it adds the item into that array list, if not then it creates new array list on the item code in the map.
         *
         * @param item the item to be added.
         * @return true if the item was successfully added, false if not (mostly because of the capacity check).
         */
        public boolean addItem(Item item) {
            if (items.containsKey(item.getCode())) {
                return items.get(item.getCode()).add(item);
            } else {
                ArrayList<Item> temp = new ArrayList<>();
                temp.add(item);
                items.put(item.getCode(), temp);
                return true;
            }
        }

        /**
         * Drops item from the items. It iterates through the items, when there is an array list and the name matches the index 0 items name
         * , then the item is deleted from the array list.
         *
         * @param name the item to be dropped.
         * @return the dropped item.
         */
        public Item dropItem(String name) {
            for (String key : items.keySet()) {
                if (items.get(key).get(0).getName().equalsIgnoreCase(name)) {
                    Item result = items.get(key).get(0);
                    if (checkDropCapacity(result)) {
                        items.get(key).remove(0);
                        if (items.get(key).isEmpty()) {
                            items.remove(key);
                        }
                        return result;
                    }
                }
            }
            return null;
        }

        /**
         * Writes names and numbers of items in the items map.
         *
         * @return names and numbers of all items.
         */
        public String writeItems() {
            ArrayList<String> names = new ArrayList<>(10);
            for (String key : items.keySet()) {
                ArrayList<Item> temp = items.get(key);
                names.add(temp.size() + "x " + Important.changeText("underline", temp.get(0).getName()));
            }
            if (names.isEmpty()) {
                return "Batoh je prázdný";
            }
            return String.join(", ", names);
        }

        public boolean isEmpty() {
            return weight == 0;
        }

        public boolean isItem(String name) {
            for (String key : items.keySet()) {
                if (!items.get(key).isEmpty() && items.get(key).get(0).getName().equalsIgnoreCase(name)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * Returns description of needed item.
         *
         * @param name the item to be found
         * @return the description of the found item
         */
        public String descriptionItem(String name) {
            for (String key : items.keySet()) {
                if (!items.get(key).isEmpty() && items.get(key).get(0).getName().equalsIgnoreCase(name)) {
                    return items.get(key).get(0).getDescription();
                }
            }
            return "There is a mistake in the game";
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
