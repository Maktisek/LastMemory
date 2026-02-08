package NPCS;

import Game.Important;

import java.util.ArrayList;

/**
 * This class represents an enemy NPC.
 * <p>
 *     This class is a POJO.
 * </p>
 * It extends {@link NPC}.
 *
 * @author Matěj Pospíšil
 */
public class EnemyNPC extends NPC{

    private String question;
    private ArrayList<String> possibleAnswers;

    public EnemyNPC() {
    }

    public boolean evaluateQuestion(String answer){
        return possibleAnswers.contains(answer.toLowerCase());
    }

    private String forToString(){
        return "Oh ne " + Important.changeText("bold", Important.changeText("underline", this.name)) + " mi stojí v cestě! \nMusím odpovědět na otázku, abych mohla pokračovat dále." + "\n" +
                Important.changeText("bold", "Otázka: ") + this.question + "\n" +
                Important.changeText("bold", "Hra: ") + "Lokace se otevře až zodpovíš na otázku (příkaz: "+Important.changeText("underline", "\"odpovědět\"") + ")";
    }

    @Override
    public String toString() {
        return Important.dashToString(forToString(), "Čas na zkoušeníčko:)");
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(ArrayList<String> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }
}
