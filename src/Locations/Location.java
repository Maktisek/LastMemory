package Locations;


import AudioSystem.Audio;
import Game.Important;
import Items.Item;
import Items.Safe;
import NPCS.EnemyNPC;
import NPCS.FriendlyNPC;

import javax.sound.sampled.Clip;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a location in the game.
 * <p>
 * The location may optionally include a {@link #friendlyNPC}, {@link #enemyNPC},
 * a {@link #safe}, and a {@link #song}.
 * </p>
 * <p>
 * Each location has a {@link LocationType} and must be connected to other locations
 * through {@link #possibleLocations}.
 * </p>
 * <p>
 * This class implements {@link Comparable} to allow sorting based on
 * {@link #isEmpty()}.
 * </p>
 *
 * @author Matěj Pospíšil
 */
public class Location implements Comparable<Location> {


    private String name;
    private String code;
    private String description;
    private LocationType locationType;

    private EnemyNPC enemyNPC;
    private FriendlyNPC friendlyNPC;

    private ArrayList<Item> items;
    private Safe safe;

    private ArrayList<Location> possibleLocations;

    private Audio song;

    public Location() {
    }

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

    /**
     * This method transfers all items from {@link #safe} to {@link #items}.
     * <p>
     *     It is called only after the safe is opened in {@link Commands.ItemsCommands.OpenSafeCommand}
     * </p>
     * @return a formatted string listing the items found in the safe
     */
    public String openSafe() {
        ArrayList<Item> temp = safe.dropItems();
        items.addAll(temp);
        ArrayList<String> names = new ArrayList<>();
        for (Item item : temp) {
            names.add(Important.changeText("underline", item.getName()));
        }
        return "V trezoru se nachází: " + Important.writeStringArrays(names);
    }

    public boolean isSafeAvailable() {
        return safe != null && safe.isLocked();
    }


    public void addPossibleLocation(Location location) {
        possibleLocations.add(location);
    }

    public Location findLocation(String name) {
        for (Location location : possibleLocations) {
            if (location.getName().equalsIgnoreCase(name)) {
                return location;
            }
        }
        return null;
    }


    /**
     * Evaluates the player's answer for the enemy NPC's question.
     *
     * @param answer the player's answer
     * @return true if the answer was correct and the enemy NPC is removed, false otherwise
     */
    public boolean answerNPC(String answer) {
        if (enemyNPC.evaluateQuestion(answer)) {
            this.enemyNPC = null;
            return true;
        }
        return false;
    }


    public String writeItemsNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Item item : items) {
            names.add(Important.changeText("underline", item.getName()));
        }
        if (names.isEmpty()) {
            return Important.changeText("red", "Místnost je prázdná");
        } else {
            return String.join(", ", names);
        }
    }

    /**
     * Returns a formatted string of all possible location names.
     * <p>
     * Locations are divided into:
     * <ul>
     *     <li>normal locations (stored in {@code normals})</li>
     *     <li>hallway locations (stored in {@code hallways})</li>
     * </ul>
     * </p>
     * All normal locations are then appended to {@code hallways} to follow the
     * set format where hallway locations are listed first, followed by the others.
     * </p>
     *
     * @return a formatted string listing the names of all possible locations
     */
    public String writeAllPossibleLocations() {
        sortPossibleLocations();
        ArrayList<String> normals = new ArrayList<>(8);
        ArrayList<String> hallways = new ArrayList<>(3);
        for (Location location : possibleLocations) {
            if (location.getType() == LocationType.HALLWAY) {
                hallways.add(Important.changeText("underline", location.writeName()));
            } else {
                normals.add(Important.changeText("underline", location.writeName()));
            }
        }
        hallways.addAll(normals);
        return String.join(",", hallways);
    }

    private boolean isEmpty(){
        if(!items.isEmpty()){
            return false;
        }
        if(safe != null && safe.isLocked()){
            return false;
        }
        if(friendlyNPC != null && friendlyNPC.getTask() != null){
            return false;
        }
        return true;
    }

    /**
     * Returns the location name, optionally colored depending on its type.
     * <ul>
     *     <li>Blue if the location is a hallway</li>
     *     <li>Green if the location is not empty or has an active friendly NPC task</li>
     *     <li>Default color otherwise</li>
     * </ul>
     *
     * @return the formatted location name
     */
    public String writeName(){
        if(locationType == LocationType.HALLWAY){
            return Important.changeText("blue", name);
        }
        if(!isEmpty() || (friendlyNPC != null && friendlyNPC.getTask() != null)){
            return Important.changeText("green", name);
        }
        return name;
    }

    private void sortPossibleLocations(){
        Collections.sort(possibleLocations);
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

    /**
     * Prepares the task to be written through {@link Important#dashToString(String, String)}
     * @return the toString to be written through {@link Important#dashToString(String, String)}
     */
    private String forToString() {
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

    public LocationType getType() {
        return locationType;
    }

    public void setType(LocationType locationType) {
        this.locationType = locationType;
    }

    public Audio getSong() {
        return song;
    }

    public Clip getSongClip() {
        if (song != null) {
            return song.getClip();
        }
        return null;
    }

    public void setSong(Audio song) {
        this.song = song;
    }

    @Override
    public int compareTo(Location o) {
        return Boolean.compare(this.isEmpty(), o.isEmpty());
    }
}
