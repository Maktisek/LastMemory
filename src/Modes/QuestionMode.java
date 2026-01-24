package Modes;

import AroundPlayer.Player;

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
        //TODO dodělat pomoc
        return "Pomoc bude dodělána";
    }

    @Override
    public String getInfo() {
        return "question";
    }

}
