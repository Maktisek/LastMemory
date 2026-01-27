package Modes;

import AroundPlayer.Player;

public class IntroMode implements Mode{
    @Override
    public String executeInfo(Player player) {
        return "Beta beta beta head text";
    }

    @Override
    public String executeHelp() {
        return "Textík";
    }

    @Override
    public String getInfo() {
        return "intro";
    }
}
