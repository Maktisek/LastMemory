package Modes;

import AroundPlayer.Player;

public class OptionsMode implements Mode{

    @Override
    public String executeInfo(Player player) {
        return "Aktuální save: " + player.getSave();
    }

    @Override
    public String executeHelp() {
        return "";
    }

    @Override
    public ModeType getInfo() {
        return ModeType.options;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }
}
