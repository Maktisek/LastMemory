package Cutscenes;

import Game.Important;

public class Cutscene {

    private String scene;
    private int requiredMemories;

    public Cutscene() {
    }

    private String writeHeadText(){
        return Important.changeText("bold", Important.readTxtFiles("res\\cutsceneAscii.txt", 5));
    }


    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public int getRequiredMemories() {
        return requiredMemories;
    }

    public void setRequiredMemories(int requiredMemories) {
        this.requiredMemories = requiredMemories;
    }

    @Override
    public String toString() {
        return Important.changeText("bold", Important.changeText("pink", writeHeadText()))+ "\n" + Important.writeLongTexts(scene);
    }
}
