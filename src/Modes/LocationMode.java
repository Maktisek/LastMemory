package Modes;

import AroundPlayer.Player;
import Game.Important;

/**
 * A mode responsible for handling locations.
 *
 * @author Matěj Pospíšil
 */
public class LocationMode implements Mode {

    @Override
    public String executeInfo(Player player) {
        return player.getCurrentLocation().toString();
    }

    @Override
    public String executeHelp() {
        return Important.readTxtFiles("res\\TextFiles\\locationHelp.txt", 0);
    }

    @Override
    public String getInfo() {
        return "location";
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

}
