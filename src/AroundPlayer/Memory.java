package AroundPlayer;

import Locations.Location;

public class Memory {


    private String name;
    private String description;
    private boolean opened;
    private Location locationGift;
    private String code;
    private boolean used;


    public Memory(String name, String description, Location locationGift, String code) {
        this.name = name;
        this.description = description;
        this.opened = false;
        this.locationGift = locationGift;
        this.code = code;
        this.used = false;
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
}
