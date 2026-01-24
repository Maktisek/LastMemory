package AroundPlayer;

import Game.Important;
import Locations.Location;
import Modes.BackpackMode;

public class Memory implements Comparable<Memory>{


    private String name;
    private String description;
    private boolean opened;
    private Location locationGift;
    private String code;


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
