package AudioSystem;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import java.io.File;

public class Audio {

    private String filePath;
    private Clip clip;
    private boolean infiniteLoop;
    private long pausePosition;
    private String title;

    public Audio() {
    }

    /**
     * Method which starts the audio.
     */
    public void startAudio() {
        final Thread playThread = new Thread(() -> {
            try {
                final AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(this.filePath));
                this.clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
                loop(infiniteLoop);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        });
        playThread.start();
    }

    /**
     * Method which stops the audio.
     */
    public void stopMusic() {
        if (clip != null) {
            this.clip.close();
        }
    }

    /**
     * Method which pauses music through clip.getMicrosecondPosition.
     * ChatGPT assisted with the microsecond usage idea.
     */
    public void pauseMusic(){
        if(clip != null){
            pausePosition = clip.getMicrosecondPosition();
            clip.stop();
        }
    }

    /**
     * Method which resumes music through clip.getMicrosecondPosition.
     */
    public void resumeMusic(){
        if(clip != null){
            clip.setMicrosecondPosition(pausePosition);
            clip.start();
        }
    }

    /**
     * Method for looping sounds.
     * @param loop = true if the audio should be looped.
     * @author ChatGPT
     */
    public void loop(boolean loop) {
        if (loop) {
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    if (clip.getMicrosecondPosition() >= clip.getMicrosecondLength()) {
                        clip.setMicrosecondPosition(0);
                        clip.start();
                    }
                }
            });
            clip.start();
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPausePosition() {
        return pausePosition;
    }

    public void setPausePosition(long pausePosition) {
        this.pausePosition = pausePosition;
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public boolean isInfiniteLoop() {
        return infiniteLoop;
    }
}
