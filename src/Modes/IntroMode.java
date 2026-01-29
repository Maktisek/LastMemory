package Modes;

import AroundPlayer.Player;
import Game.Important;

public class IntroMode implements Mode{
    @Override
    public String executeInfo(Player player) {
        String ascii = Important.changeText("bold", Important.changeText("pink", Important.readTxtFiles("res\\introText.txt", 0)));
        ascii += "                                                                                                                    Made by Maktis";
        ascii += "\nAbyste si hru zahráli napište to konzole příkaz \"spustit hru\".";
        ascii += "\nUžijte si hru!";
        return ascii;
    }

    @Override
    public String executeHelp() {
        return Important.readTxtFiles("res\\introHelp.txt", 0);
    }

    @Override
    public String getInfo() {
        return "intro";
    }

    @Override
    public boolean special() {
        return true;
    }
}
