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

    public FriendlyNPC(String name, String position, String age, String currentSpeech, String anotherSpeech, Task task) {
        super(name, position, age);
        this.currentSpeech = currentSpeech;
        this.anotherSpeech = anotherSpeech;
        this.task = task;
    }

    public void switchSpeeches(){
        //TODO switchSpeeches metoda chybi
    }

    @Override
    public String toString() {
        //TODO toString metoda chybi
        return null;
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
