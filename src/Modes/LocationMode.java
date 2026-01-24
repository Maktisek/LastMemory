package Modes;

import AroundPlayer.Player;
import Game.Important;

/**
 * Mode designed to operate in the area of location
 */
public class LocationMode implements Mode {

    @Override
    public String executeInfo(Player player) {
        return player.getCurrentLocation().toString();
    }

    @Override
    public String executeHelp() {
        return Important.readTxtFiles("res\\locationHelp.txt");
    }

    @Override
    public String getInfo() {
        return "location";
    }

}
