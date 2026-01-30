package NPCS;

import Game.Important;
import Items.Task;

public class FriendlyNPC extends NPC{

    private String currentSpeech;
    private String anotherSpeech;
    private Task task;
    private TypeNPC type;

    public FriendlyNPC(String name, String position, String age) {
        super(name, position, age);
    }

    public FriendlyNPC() {
    }

    public void switchSpeeches(){
        this.currentSpeech = anotherSpeech;
        this.anotherSpeech = null;
    }


    public String writeName(){
        return name + writeType();
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

    public TypeNPC getType() {
        return type;
    }

    private String writeType(){
        if(type == TypeNPC.AKTIVNÍ){
            return Important.changeText("green", " ("+type.name()+")");
        }
        return Important.changeText("yellow", " ("+type.name()+")");
    }

    public void setType(TypeNPC type) {
        this.type = type;
    }
}
