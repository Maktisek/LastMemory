package Modes;

import AroundPlayer.Player;

/**
 * Mode designed to operate in the area of location
 */
public class LocationMode implements Mode {

    @Override
    public String executeInfo(Player player) {
        return player.getCurrentLocation().toString();
    }

    @Override
    public String executeHelp(Player player) {
        //TODO dodělat pomoc
        return "Pomoc bude doděláná";
    }

    @Override
    public String getInfo() {
        return "location";
    }

}
