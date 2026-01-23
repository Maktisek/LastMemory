package NPCS;

import Items.Task;

public class FriendlyNPC extends NPC{

    private String currentSpeech;
    private String anotherSpeech;
    private Task task;

    public FriendlyNPC(String name, String position, String age) {
        super(name, position, age);
    }

    public FriendlyNPC() {
    }

    public void switchSpeeches(){
        this.currentSpeech = anotherSpeech;
        this.anotherSpeech = null;
    }


    public String getCurrentSpeech() {
        return currentSpeech;
    }

    public Task getTask() {
        return task;
    }

    public void setCurrentSpeech(String currentSpeech) {
        this.currentSpeech = currentSpeech;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getAnotherSpeech() {
        return anotherSpeech;
    }

    public void setAnotherSpeech(String anotherSpeech) {
        this.anotherSpeech = anotherSpeech;
    }
}
