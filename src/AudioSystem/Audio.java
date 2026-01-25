package AudioSystem;

import javax.sound.sampled.*;
import java.io.File;

public class Audio {

    private String filePath;
    private Clip clip;
    private boolean infiniteLoop;
    private long pausePosition;
    private String title;
    boolean paused;

    public Audio() {
    }

    public void implementAudio(boolean music) {
        try {
            final AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(this.filePath));
            this.clip = AudioSystem.getClip();
            clip.open(audioStream);
            if(music){
                fadeIn();
            }
            clip.start();
            loop(infiniteLoop);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    public void implementMusic(){
        if(clip == null) {
            implementAudio(true);
        }
    }


    /**
     * Method which starts music.
     */
    public void startMusic() {
        final Thread playThread = new Thread(this::implementMusic);
        playThread.start();
    }

    public void startAudio() {
        final Thread playThread = new Thread(() -> implementAudio(false));
        playThread.start();
    }

    /**
     * Method which stops the audio.
     */
    public void stopMusic() {
        if (clip != null) {
            this.clip.close();
            this.clip = null;
        }
    }

    /**
     * Method which pauses music through clip.getMicrosecondPosition.
     * ChatGPT assisted with the microsecond usage idea.
     */
    public void pauseMusic(){
        if(clip != null && !paused){
            Thread t = new Thread(() ->{
                fadeOut();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                pausePosition = clip.getMicrosecondPosition();
                paused = true;
                clip.stop();
            });
            t.start();
        }
    }

    /**
     * Method which resumes music through clip.getMicrosecondPosition.
     */
    public void resumeMusic(){
        if(clip != null && paused){
            Thread t = new Thread(() ->{
                fadeIn();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                paused = false;
                clip.setMicrosecondPosition(pausePosition);
                clip.start();
            });
            t.start();
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

    public void setVolume(float db) {
        if (clip != null && clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gain.setValue(db);
        }
    }

    public void fadeIn(){
        Thread t = new Thread(() ->{
               for (float f = -10f; f < 0f; f++) {
                   setVolume(f);
                   try {
                       Thread.sleep(100);
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
               }
        });
        t.start();
    }

    public void fadeOut(){
        Thread t = new Thread(() ->{
            for (float f = 0f; f > -80f; f -= 0.5f) {
                setVolume(f);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t.start();
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

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setInfiniteLoop(boolean infiniteLoop) {
        this.infiniteLoop = infiniteLoop;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
