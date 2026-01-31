package Locations;


import AudioSystem.Audio;
import Game.Important;
import Items.Item;
import Items.Safe;
import NPCS.EnemyNPC;
import NPCS.FriendlyNPC;

import java.util.ArrayList;


public class Location {

    //Basic information
    private String name;
    private String code;
    private String description;
    private Type type;

    //NPCs
    private EnemyNPC enemyNPC;
    private FriendlyNPC friendlyNPC;

    //Items
    private ArrayList<Item> items;
    private Safe safe;

    //Locations
    private ArrayList<Location> possibleLocations;

    //Audio
    private Audio song;

    public Location() {
    }

    /**
     * Adds item into items list
     *
     * @param item the item to be added
     * @return true if the action was successful, false if not
     */
    public boolean addItem(Item item) {
        if (item != null) {
            return items.add(item);
        }
        return false;
    }


    public void removeItem(Item item) {
        items.remove(item);
    }

    public Item hasItem(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }


    public String openSafe() {
            ArrayList<Item> temp = safe.dropItems();
            items.addAll(temp);
            ArrayList<String> names = new ArrayList<>();
            for (Item item : temp) {
                names.add(item.getName());
            }
            return "V trezoru se nachází: " + Important.writeStringArrays(names);
        }

    public boolean availableSafe() {
        return safe != null && safe.isLocked();
    }


    public void addPossibleLocation(Location location) {
        possibleLocations.add(location);
    }

    /**
     * Iterates through possible location list. Searches the right location.
     *
     * @param name the name of the location to be found
     * @return null if the location was not found and the instance of Location if the location was found.
     */
    public Location findLocation(String name) {
        for (Location location : possibleLocations) {
            if (location.getName().equalsIgnoreCase(name)) {
                return location;
            }
        }
        return null;
    }

    /**
     * Evaluates and enemyNPC's question. It
     *
     * @param answer the answer to be evaluated
     * @return true if the answer was correct and false if not
     */
    public boolean answerNPC(String answer) {
        if (enemyNPC.evaluateQuestion(answer)) {
            this.enemyNPC = null;
            return true;
        }
        return false;
    }

    /**
     * Writes all names of items in items
     *
     * @return names of all items if there are any.
     * If there are no items, then it returns information about that.
     */
    public String writeItemsNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Item item : items) {
            names.add(item.getName());
        }
        if (names.isEmpty()) {
            return Important.changeText("red", "Místnost je prázdná");
        } else {
            return String.join(", ", names);
        }
    }

    /**
     * Writes all names of locations in possibleLocations
     *
     * @return names of all locations
     */
    public String writeAllPossibleLocations() {
        ArrayList<String> names = new ArrayList<>(25);
        for (Location location : possibleLocations) {
            names.add(Important.changeText("underline", location.getName()));
        }
        return String.join(",", names);
    }


    public String writeFriendlyNPCName() {
        if (friendlyNPC != null) {
            return Important.changeText("green", this.friendlyNPC.writeName());
        } else {
            return Important.changeText("red", "Nikdo se zde nenachází");
        }
    }

    public String writeSafe() {
        if (safe != null && safe.isLocked()) {
            return Important.changeText("green", "Přítomný");
        }
        return Important.changeText("red", "Nepřítomný");
    }


    public boolean isFree() {
        return enemyNPC == null;
    }

    public void pauseMusic() {
        if (song != null) {
            song.pause();
        }
    }

    public void resumeMusic() {
        if (song != null) {
            song.resume();
        }
    }

    public void playMusic(long startPosition) {
        if (song != null) {
            song.startMusic(startPosition);
        }
    }

    public void stopMusic() {
        if (song != null) {
            song.stopMusic();
        }
    }


    private String forToString(){
        return Important.changeText("bold", "Postava: ") + writeFriendlyNPCName() + "\n" +
                Important.changeText("bold", "Předměty: ") + writeItemsNames() + "\n" +
                Important.changeText("bold", "Safe: ") + writeSafe();
    }

    @Override
    public String toString() {
        return Important.dashToString(forToString(), name);

    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Location> getPossibleLocations() {
        return possibleLocations;
    }

    public void setPossibleLocations(ArrayList<Location> possibleLocations) {
        this.possibleLocations = possibleLocations;
    }

    public Safe getSafe() {
        return safe;
    }

    public void setSafe(Safe safe) {
        this.safe = safe;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public EnemyNPC getEnemyNPC() {
        return enemyNPC;
    }

    public void setEnemyNPC(EnemyNPC enemyNPC) {
        this.enemyNPC = enemyNPC;
    }

    public FriendlyNPC getFriendlyNPC() {
        return friendlyNPC;
    }

    public void setFriendlyNPC(FriendlyNPC friendlyNPC) {
        this.friendlyNPC = friendlyNPC;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Audio getSong() {
        return song;
    }

    public void setSong(Audio song) {
        this.song = song;
    }
}
