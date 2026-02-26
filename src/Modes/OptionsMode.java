package Modes;

import AroundPlayer.Player;
import Game.Important;

public class OptionsMode implements Mode{

    private String forToString(Player player) {
        return Important.changeText("bold" ,"Aktuální save: ") + player.writeSave() + "\n" +
                Important.changeText("bold", "Dostupné savy: ") + Important.writeNamesOfSavedSaves();
    }

    @Override
    public String executeInfo(Player player) {
        return Important.dashToString(forToString(player), "Nastavení");
    }

    @Override
    public String executeHelp() {
        return "";
    }

    @Override
    public ModeType getInfo() {
        return ModeType.options;
    }

}
