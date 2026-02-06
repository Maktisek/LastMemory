package AroundPlayer;

import Game.Important;
import Locations.Location;

/**
 * This class represents a memory, which works like a story giver.
 * The player can read the description in order to understand the story.
 * <p>
 * {@link #opened} is false until the player decides to read the description. <p>
 * {@link #code} stands for a code, which has to match with any of the locations in the game. <p>
 * {@link #locationGift} represents the location which can be obtained through the memory.
 * <p>
 * The class implements Comparable in order to sort the memories via {@link #opened}
 *  @author Matěj Popíšil
 */
public class Memory implements Comparable<Memory>{

    private String name;
    private String description;
    private String code;

    private boolean opened;

    private Location locationGift;



    public Memory() {
    }

    public String writeName(){
        if(!opened){
            return Important.changeText("green", name);
        }
        return name;
    }

    public void switchOpened(){
        this.opened = true;
    }

    public Location giveLocation(){
        Location location = locationGift;
        this.locationGift = null;
        return location;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Location getLocationGift() {
        return locationGift;
    }

    public void setLocationGift(Location locationGift) {
        this.locationGift = locationGift;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public int compareTo(Memory o) {
        return Boolean.compare(this.isOpened(), o.isOpened());
    }
}
