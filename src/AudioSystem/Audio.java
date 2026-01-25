package AudioSystem;

import javax.sound.sampled.*;
import java.io.File;

/**
 * The concept of this whole class was taken from Matěj Chaloupka. But I made tons of changes during the time.
 * Used ChatGPT to help me understand whole Clip interface.
 * <p>
 * This class stands for playing individual .wav audio files. Those files have to be implemented into audios file.
 */
public class Audio {

    private String filePath;
    private String title;

    private Clip clip;
    private boolean infiniteLoop;
    private long pausePosition;
    boolean paused;

    public Audio() {
    }

    /**
     * This method implements and audio. It creates a clip and loads it with .wav file from {@link #filePath}.
     * <p>
     * Then it uses {@link #loop(boolean)} to start looping the audio.
     * <p>
     * This system was originally taken from Matěj Chaloupka, but implemented in a different way.
     * @param music true if the audio file is music. When true, then the audio will fade in via {@link #fadeIn(long)} method
     */
    private void implementAudio(boolean music) {
        try {
            final AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(this.filePath));
            this.clip = AudioSystem.getClip();
            clip.open(audioStream);
            if (music) {
                fadeIn(10);
            }
            clip.start();
            loop(infiniteLoop);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Created just for playing background music.
     * <p>
     * Uses {@link #implementAudio(boolean)} method to play music.
     * <p>
     * It will only proceed further if the clip is null. This prevents some in game bugs where music is playing while another is playing too.
     */
    public void implementMusic() {
        if (clip == null) {
            implementAudio(true);
        }
    }


    /**
     * Method which starts music via thread.
     * Uses {@link #implementMusic()} method to play the audio.
     */
    public void startMusic() {
        final Thread playThread = new Thread(this::implementMusic);
        playThread.start();
    }

    /**
     * Method which starts sound via thread.
     * The clip have not to be null to play the sound.
     * Created for basic one shot sounds.
     * Uses {@link #implementAudio(boolean)} method to play the audio.
     */
    public void startAudio() {
        final Thread playThread = new Thread(() -> implementAudio(false));
        playThread.start();
    }

    /**
     * Method which stops the music.
     * Normal one shot sound don't have to be stopped, so this method is made just for music.
     * It sets the clip to null, so the music can be replayed in the future.
     */
    public void stopMusic() {
        if (clip != null) {
            this.clip.close();
            this.clip = null;
        }
    }

    /**
     * Method which pauses the audio.
     * There is a value {@link #paused} which holds an information if the audio is paused.
     * This value has to be false in order to proceed. Also, the clip has to be initialized. If it's null then it can't be stopped.
     * <p>
     * Sets {@link #pausePosition} to current microsecond position, so the audio can be resumed later.
     * <p>
     * Uses {@link #fadeOut()} method for cleaner transition.
     */
    public void pause() {
        if (clip != null && !paused) {
            Thread t = new Thread(() -> {
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
     * Method which resumes the audio
     * There is a value {@link #paused} which holds an information if the audio is paused.
     * This value has to be true in order to proceed. Also, the clip has to be initialized. If it's null then it can't be resumed.
     * <p>
     * Uses {@link #pausePosition} to set current microsecond position, so the audio can be resumed, where it stopped.
     * <p>
     * Uses {@link #fadeIn(long)}} method for cleaner transition.
     */
    public void resume() {
        if (clip != null && paused) {
            Thread t = new Thread(() -> {
                fadeIn(100);
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
     * Method which can make the audio file to loop.
     *
     *
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

    public void fadeIn(long milliseconds) {
        Thread t = new Thread(() -> {
            for (float f = -10f; f < 0f; f++) {
                setVolume(f);
                try {
                    Thread.sleep(milliseconds);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t.start();
    }

    public void fadeOut() {
        Thread t = new Thread(() -> {
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
