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
        return Important.readTxtFiles("/TextFiles/locationHelp.txt", 0);
    }

    @Override
    public ModeType getInfo() {
        return ModeType.location;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

}
