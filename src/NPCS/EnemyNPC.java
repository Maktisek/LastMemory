package NPCS;

import java.util.ArrayList;

public class EnemyNPC extends NPC{

    private String question;
    private ArrayList<String> PossibleAnswers;

    public EnemyNPC(String name, String position, String age, String question) {
        super(name, position, age);
        this.question = question;
        this.PossibleAnswers = new ArrayList<>();
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
        //TODO toString metoda chybi
        return null;
    }

    public String getQuestion() {
        return question;
    }
}
