package AudioSystem;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AudioLibrary {

    private ArrayList<Audio> audios;
    private ObjectMapper mapper;

    public AudioLibrary() {
        this.audios = new ArrayList<>();
        this.mapper = new ObjectMapper();
        loadAudios();
    }

    private void loadAudios() {
        try (InputStream input = new FileInputStream("res\\audios.json")) {
            Audio[] sounds = mapper.readValue(input, Audio[].class);
            audios.addAll(List.of(sounds));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void playAudio(String name) {
        for (Audio audio : audios) {
            if (audio.getTitle().equalsIgnoreCase(name)) {
                audio.startAudio();
            }
        }
    }

    public void stopAudio(String name) {
        for (Audio audio : audios) {
            if (audio.getTitle().equalsIgnoreCase(name)) {
                audio.stopMusic();
            }
        }
    }
}
