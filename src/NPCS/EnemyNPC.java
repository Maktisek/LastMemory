package NPCS;

import java.util.ArrayList;
import java.util.List;

public class EnemyNPC extends NPC{

    private String question;
    private ArrayList<String> possibleAnswers;

    public EnemyNPC(String name, String position, String age) {
        super(name, position, age);
    }

    public EnemyNPC() {
    }


    public boolean addPossibleAnswer(String answer){
        //TODO addPossibleAnswer metoda chybi
        return true;
    }

    public boolean evaluateQuestion(String answer){
        //TODO evaluateQuestion metoda chybi
        //Vyhodnoti, zda je dana odpoved spravne
        return true;
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
