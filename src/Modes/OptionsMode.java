package Modes;

import AroundPlayer.Player;
import Game.Important;
/**
 * A mode focused on options of the game - in current version the options are only about saves management.
 * @author Matěj Pospíšil
 */
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
        return Important.readTxtFiles("/TextFiles/optionsHelp.txt", 0);
    }

    @Override
    public ModeType getInfo() {
        return ModeType.options;
    }

}
