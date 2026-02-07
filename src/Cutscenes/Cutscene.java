package Cutscenes;

import Game.Important;

/**
 * Represents a cutscene in the game.
 * <p>
 * This is a simple POJO class.
 * <ul>
 *     <li>{@link #scene} contains the content of the cutscene as a String.</li>
 *     <li>{@link #requiredMemories} specifies the number of memories
 *         the player must have to play this cutscene.</li>
 * </ul>
 * </p>
 * @author Matěj Pospíšil
 */
public class Cutscene {

    private String scene;
    private int requiredMemories;

    public Cutscene() {
    }

    private String writeHeadText(){
        String result = Important.readTxtFiles("res\\TextFiles\\cutsceneAscii.txt", 0);
        return Important.asciiHeadTextHelper(scene, Important.changeText("bold", result));
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
        return Important.changeText("bold", Important.changeText("pink", writeHeadText()))+ "\n" + scene;
    }
}
