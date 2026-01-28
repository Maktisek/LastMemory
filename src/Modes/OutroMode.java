package Modes;

import AroundPlayer.Player;
import Game.Important;

public class OutroMode implements Mode{

    @Override
    public String executeInfo(Player player) {
        String ascii = Important.changeText("bold", Important.changeText("pink", Important.readTxtFiles("res\\outroText.txt")));
        return ascii;
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
