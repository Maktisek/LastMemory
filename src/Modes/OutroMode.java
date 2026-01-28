package Modes;

import AroundPlayer.Player;

public class OutroMode implements Mode{

    @Override
    public String executeInfo(Player player) {
        return "test";
    }

    @Override
    public String executeHelp() {
        return "test";
    }

    @Override
    public String getInfo() {
        return "outro";
    }

    @Override
    public boolean special() {
        return true;
    }
}
