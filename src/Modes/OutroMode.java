package Modes;

import AroundPlayer.Player;
import Game.Important;

public class OutroMode implements Mode{

    @Override
    public String executeInfo(Player player) {
        String ascii = "\n"+Important.changeText("bold", Important.changeText("pink", Important.readTxtFiles("res\\outroText.txt"))) + "\n";
        ascii += "Za dohrání hry dostáváš citát: "+Important.changeText("bold", Important.randomLineReader("res\\endQuotes.txt"));
        ascii += "\nDej mi vědět jaký citát si dostal/a a taky (pokud chceš) mi dej vědět, co si o hře myslíš.";
        ascii += "\nNapsat mi to můžeš sem: https://secretnote.me/msg/5FHW?utm_source=native";
        ascii += "\nZpráva je anonymní, nemusíš se bát.";
        ascii += "\nDěkuji, že jsi hru dohrál/a, znamená to pro mě hodně:)";
        ascii += "\nHru teď můžeš vypnout pomocí příkazu \"opustit\" a nebo si přečíst více informací o hře pomocí \"informace\"";
        return ascii;
    }

    @Override
    public String executeHelp() {
        return Important.readTxtFiles("res\\outroHelp.txt");
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
