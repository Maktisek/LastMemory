package Cutscenes;

public class Cutscene {

    private String name;
    private String scene;
    private int requiredMemories;

    public Cutscene() {
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
        return name + ": " + scene;
    }
}
