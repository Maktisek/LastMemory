package Modes;

import AroundPlayer.Player;
import Game.Important;

import java.io.Serializable;

/**
 * A mode focused on game introduction.
 * @author Matěj Pospíšil
 */
public class IntroMode implements Mode, Serializable {
    @Override
    public String executeInfo(Player player) {
        String ascii = Important.changeText("bold", Important.changeText("pink", Important.readTxtFiles("/TextFiles/introText.txt", 0)));
        ascii += "Made by Maktis";
        ascii += "\nPokud hraješ poprvé, bude se ti hodit příkaz \"jak hrát\".";
        ascii += "\nPokud neznáš příkazy napiš \"pomoc\".";
        ascii += "\nAby sis hru zahrál napiš to konzole příkaz \"spustit hru\".";
        return ascii;
    }

    @Override
    public String executeHelp() {
        return Important.readTxtFiles("/TextFiles/introHelp.txt", 0);
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
