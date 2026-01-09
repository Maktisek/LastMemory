package NPCS;

import Items.Task;

public class FriendlyNPC extends NPC{

    private String currentSpeech;
    private String anotherSpeech;
    private Task task;


    public FriendlyNPC(String name, String position, String age, String currentSpeech, String anotherSpeech, Task task) {
        super(name, position, age);
        this.currentSpeech = currentSpeech;
        this.anotherSpeech = anotherSpeech;
        this.task = task;
    }
}
