package Modes;

import AroundPlayer.Player;
import Game.Important;

/**
 * Specific mode which is used only if the player stands in location where is friendlyNPC.
 */
public class QuestionMode implements Mode{

    @Override
    public String executeInfo(Player player) {
        return player.getCurrentLocation().getEnemyNPC().toString();
    }

    @Override
    public String executeHelp() {
        return Important.readTxtFiles("res\\questionHelp.txt", 0);
    }

    @Override
    public String getInfo() {
        return "question";
    }

    @Override
    public boolean special() {
        return false;
    }

}
