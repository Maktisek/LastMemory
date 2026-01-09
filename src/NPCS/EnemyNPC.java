package NPCS;

import java.util.HashMap;

public class EnemyNPC extends NPC{

    private String question;
    private HashMap<String, Boolean> answers;

    public EnemyNPC(String name, String position, String age, String question) {
        super(name, position, age);
        this.question = question;
        this.answers = new HashMap<>();
    }



}
