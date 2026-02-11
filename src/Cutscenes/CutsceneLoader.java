package Cutscenes;

import Exceptions.WrongInitializationException;
import Game.Important;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;

/**
 * Manages all cutscenes in the game.
 * <p>
 * {@link #cutscenes} is a queue containing all loaded cutscenes.
 * This class acts as a "holder" from which cutscenes can be polled when needed.
 * </p>
 * @author Matěj Pospíšil
 */
public class CutsceneLoader {

    private Queue<Cutscene> cutscenes;

    /**
     * Initializes the {@link CutsceneLoader}.
     * <p>
     * Loads the {@link #cutscenes} queue from the file "res\\Jsons\\cutscenes.json"
     * using the Jackson library.
     * </p>
     * @return the initialized {@link CutsceneLoader} instance
     * @throws Exceptions.WrongInitializationException if there is an IOException during loading
     */
    public static CutsceneLoader loadCutscenes() throws WrongInitializationException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream input = CutsceneLoader.class.getResourceAsStream("/Jsons/cutscenes.json");
        if(input == null){
            throw new WrongInitializationException(Important.changeText("red", "The file is missing from the resources folder."));
        }
        try (input){
            return mapper.readValue(input, CutsceneLoader.class);
        } catch (IOException e) {
            throw new WrongInitializationException(Important.changeText("red", "There is a problem with the Json file"));
        }
    }


    public Cutscene pollCutscene(){
        return cutscenes.poll();
    }

    public Cutscene peekCutscene(){
        return cutscenes.peek();
    }

    public Queue<Cutscene> getCutscenes() {
        return cutscenes;
    }

    public void setCutscenes(Queue<Cutscene> cutscenes) {
        this.cutscenes = cutscenes;
    }


}
