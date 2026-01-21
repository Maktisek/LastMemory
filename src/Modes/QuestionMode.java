package Modes;

import AroundPlayer.Player;

public class QuestionMode implements Mode{

    @Override
    public String executeInfo(Player player) {
        return player.getCurrentLocation().getEnemyNPC().toString();
    }

    @Override
    public String executeHelp(Player player) {
        //TODO dodělat pomoc
        return "Pomoc bude dodělána";
    }

    @Override
    public String getInfo() {
        return "question";
    }

    @Override
    public boolean match(Mode mode) {
        return getInfo().equalsIgnoreCase(mode.getInfo());
    }
}
