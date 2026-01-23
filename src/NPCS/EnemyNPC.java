package NPCS;

import java.util.ArrayList;

public class EnemyNPC extends NPC{

    private String question;
    private ArrayList<String> possibleAnswers;

    public EnemyNPC(String name, String position, String age) {
        super(name, position, age);
    }

    public EnemyNPC() {
    }


    public boolean evaluateQuestion(String answer){
        return possibleAnswers.contains(answer);
    }

    @Override
    public String toString() {
        return "------------------Protivník------------------" + "\n" +
                "Oh ne " + this.name + " mi stojí v cestě! \nMusím odpovědět na otázku, abych mohla pokračovat dále." + "\n" +
                "Otázka: " + this.question + "\n" +
                "Hra: " + "Lokace se otevře až zodpovíš na otázku (command unknown)";
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
