package Modes;

import AroundPlayer.Player;

public class LocationMode implements Mode{
    @Override
    public String executeInfo(Player player) {
        return player.getCurrentLocation().toString();
    }

    @Override
    public String executeHelp(Player player) {
        return "";
    }
}
