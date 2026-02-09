package Modes;

import AroundPlayer.Player;
import Game.Important;

/**
 * A mode focused on game introduction.
 * @author Matěj Pospíšil
 */
public class IntroMode implements Mode{
    @Override
    public String executeInfo(Player player) {
        String ascii = Important.changeText("bold", Important.changeText("pink", Important.readTxtFiles("res\\TextFiles\\introText.txt", 0)));
        ascii += "                                                                                                                    Made by Maktis";
        ascii += "\nAbyste si hru zahráli napište to konzole příkaz \"spustit hru\".";
        ascii += "\nUžijte si hru!";
        return ascii;
    }

    @Override
    public String executeHelp() {
        return Important.readTxtFiles("res\\TextFiles\\introHelp.txt", 0);
    }

    @Override
    public ModeType getInfo() {
        return ModeType.intro;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
