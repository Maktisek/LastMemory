package Cutscenes;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;

public class CutsceneLoader {

    private Queue<Cutscene> cutscenes;

    public static CutsceneLoader loadCutscenes(){
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream input = new FileInputStream("res\\cutscenes.json")){
            CutsceneLoader temp = mapper.readValue(input, CutsceneLoader.class);
            return temp;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Queue<Cutscene> getCutscenes() {
        return cutscenes;
    }

    public void setCutscenes(Queue<Cutscene> cutscenes) {
        this.cutscenes = cutscenes;
    }


}
