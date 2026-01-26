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
            setVolumeToAllAudios();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setVolumeToAllAudios(){
        for (Audio audio: audios) {
            audio.setVolume(audio.getVolume());
        }
    }

    public void playMusic(String name) {
        for (Audio audio : audios) {
            if (audio.getTitle().equalsIgnoreCase(name)) {
                audio.startMusic();
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

    public void stop(String name) {
        for (Audio audio : audios) {
            if (audio.getTitle().equalsIgnoreCase(name)) {
                audio.stopMusic();
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


    public void changeTitle(String previousTitle, String newTitle){
        for (Audio audio: audios){
            if(audio.getTitle().equalsIgnoreCase(previousTitle)){
                audio.setTitle(newTitle);
            }
        }
    }
}
