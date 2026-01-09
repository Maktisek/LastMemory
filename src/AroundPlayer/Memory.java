package AroundPlayer;

import Locations.Location;

public class Memory {


    private String name;
    private String description;
    private boolean opened;
    private Location locationGift;
    private String code;


    public Memory(String name, String description, boolean opened, Location locationGift, String code) {
        this.name = name;
        this.description = description;
        this.opened = opened;
        this.locationGift = locationGift;
        this.code = code;
    }
}
