package Modes;

import AroundPlayer.Player;
import Game.Important;
/**
 * A mode responsible for the game outro.
 *
 * @author Matěj Pospíšil
 */
public class OutroMode implements Mode{

    private final String quote;

    public OutroMode() {
        this.quote = Important.changeText("bold", Important.randomLineReader("/TextFiles/endQuotes.txt"));
    }

    @Override
    public String executeInfo(Player player) {
        String ascii = "\n"+Important.changeText("bold", Important.changeText("pink", Important.readTxtFiles("/TextFiles/outroText.txt", 0))) + "\n";
        ascii += "Za dohrání hry dostáváš citát: "+quote;
        ascii += "\nDej mi vědět jaký citát si dostal/a a taky (pokud chceš) mi dej vědět, co si o hře myslíš.";
        ascii += "\nNapsat mi to můžeš sem: https://secretnote.me/msg/5FHW?utm_source=native";
        ascii += "\nZpráva je anonymní, nemusíš se bát.";
        ascii += "\nDěkuji, že jsi hru dohrál/a, znamená to pro mě hodně:)";
        ascii += "\nHru teď můžeš vypnout pomocí příkazu \"opustit\" a nebo si přečíst více informací o hře pomocí \"informace\"";
        return ascii;
    }

    @Override
    public String executeHelp() {
        return Important.readTxtFiles("/TextFiles/outroHelp.txt", 0);
    }

    @Override
    public ModeType getInfo() {
        return ModeType.outro;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
