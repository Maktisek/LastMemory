package AroundPlayer;

import Locations.Location;

public class Memory {


    private String name;
    private String description;
    private boolean opened;
    private Location locationGift;
    private String code;


    public Memory() {
    }

    public String writeName(){
        //TODO writeName metoda chybi
        //Vypise jmeno vypominky odlisnymi barvami podle boolean opened
        return null;
    }

    public void switchOpened(){
        //TODO switchOpened metoda chybi
        //Jednoducha metoda na prepnuti boolean opened na true
    }

    public Location giveLocation(){
        //TODO giveLocation metoda chybi
        //Odevzda lokaci a used se nastavi na true.
        return null;
    }

    @Override
    public String toString() {
        //TODO toString metoda chybi
        return null;
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
}
