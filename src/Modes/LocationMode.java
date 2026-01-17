package Modes;

import AroundPlayer.Player;

public class LocationMode implements Mode{
    @Override
    public String executeInfo(Player player) {
        if(player.getCurrentLocation().getEnemyNPC() == null) {
            return player.getCurrentLocation().toString();
        }else {
            return player.getCurrentLocation().getEnemyNPC().toString();
        }
    }

    @Override
    public String executeHelp(Player player) {
        return "";
    }
}
