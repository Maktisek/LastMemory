package Modes;

import AroundPlayer.Player;
import Game.Important;

public class OptionsMode implements Mode{

    @Override
    public String executeInfo(Player player) {
        return "Aktuální save: " + player.getSave() + "\n" +
                "Dostupné savy: " + Important.writeNamesOfSavedSaves();
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
