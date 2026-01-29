package Cutscenes;

import Game.Important;

public class Cutscene {

    private String name;
    private String scene;
    private int requiredMemories;

    public Cutscene() {
    }

    private String writeName(){
        String[] data = name.split("\n");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            sb.append(Important.writeBlank(15)).append(data[i]).append("\n");
        }
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return Important.changeText("bold", Important.changeText("pink", writeName()))+ "\n" + Important.writeLongTexts(scene);
    }
}
