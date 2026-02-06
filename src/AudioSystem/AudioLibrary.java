package AudioSystem;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This method represents an audio library, which stores unconnected audios.
 * Those audios can be played, stopped, paused or even resumed.
 * @author Matěj Pospíšil
 */
public class AudioLibrary {

    private final ArrayList<Audio> audios;
    private final ObjectMapper mapper;

    public AudioLibrary() {
        this.audios = new ArrayList<>();
        this.mapper = new ObjectMapper();
    }

    /**
     * This method represents a {@link AudioLibrary} setup.
     * @return The loaded instance of {@link AudioLibrary}
     */
    public static AudioLibrary loadAudioLibrary(){
        AudioLibrary audioLibrary = new AudioLibrary();
        audioLibrary.loadAudios();
        return audioLibrary;
    }

    /**
     * Loads all audio files from res\Jsons\audios.json file path.
     * <p>
     * Uses external library jackson to load Json files.
     */
    private void loadAudios() {
        try (InputStream input = new FileInputStream("res\\Jsons\\audios.json")) {
            Audio[] sounds = mapper.readValue(input, Audio[].class);
            audios.addAll(List.of(sounds));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void playMusic(String name) {
        for (Audio audio : audios) {
            if (audio.getTitle().equalsIgnoreCase(name)) {
                audio.startMusic(0);
            }
        }
    }

    public void playAudio(String name) {
        for (Audio audio : audios) {
            if (audio.getTitle().equalsIgnoreCase(name)) {
                audio.startAudio();
            }
        }
    }

    public void stopMusic(String name) {
        for (Audio audio : audios) {
            if (audio.getTitle().equalsIgnoreCase(name)) {
                audio.stopMusic();
            }
        }
    }

    public void stopSound(String name) {
        for (Audio audio : audios) {
            if (audio.getTitle().equalsIgnoreCase(name)) {
                audio.stopSound();
            }
        }
    }

    public void pause(String name) {
        for (Audio audio : audios) {
            if (audio.getTitle().equalsIgnoreCase(name)) {
                audio.pause();
            }
        }
    }

    public void resume(String name) {
        for (Audio audio : audios) {
            if (audio.getTitle().equalsIgnoreCase(name)) {
                audio.resume();
            }
        }
    }
}
