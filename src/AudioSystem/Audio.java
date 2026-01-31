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
    private float initialVolume;

    public Audio() {
    }

    /**
     * This method implements and audio. It creates a clip and loads it with .wav file from {@link #filePath}.
     * <p>
     * Then it uses {@link #loop(boolean)} to start looping the audio.
     * <p>
     * This system was originally taken from Matěj Chaloupka, but implemented in a different way.
     *
     * @param music true if the audio file is music. When true, then the audio will fade in via {@link #fadeIn(long)} method
     */
    private void implementAudio(boolean music, long startPosition) {
        try {
            final AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(this.filePath));
            this.clip = AudioSystem.getClip();
            clip.open(audioStream);
            setVolume(this.initialVolume - 15);
            if (music) {
                if (startPosition == 0) {
                    fadeIn(20);
                } else {
                    clip.setMicrosecondPosition(startPosition);
                }
            } else {
                setVolume(this.initialVolume);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        loop(infiniteLoop);
    }

    /**
     * Created just for playing background music.
     * <p>
     * Uses {@link #implementAudio(boolean, long)} method to play music.
     * <p>
     * It will only proceed further if the clip is null. This prevents some in game bugs where music is playing while another is playing too.
     */
    public void implementMusic(long startingPosition) {
        if (clip == null) {
            implementAudio(true, startingPosition);
        }
    }


    /**
     * Method which starts music via thread.
     * Uses {@link #implementMusic(long)} method to play the audio.
     */
    public void startMusic(long startingPosition) {
        final Thread playThread = new Thread(() -> implementMusic(startingPosition));
        playThread.start();
    }

    /**
     * Method which starts sound via thread.
     * The clip have not to be null to play the sound.
     * Created for basic one shot sounds.
     * Uses {@link #implementAudio(boolean, long)} method to play the audio.
     */
    public void startAudio() {
        final Thread playThread = new Thread(() -> implementAudio(false, 0));
        playThread.start();
    }

    /**
     * Method which stops the music.
     * Normal one shot sound don't have to be stopped, so this method is made just for music.
     * It sets the clip to null, so the music can be replayed in the future.
     */
    public void stopMusic() {
        if (clip != null) {
            Thread t = new Thread(() -> {
                this.clip.close();
                this.clip = null;
            });
            t.start();
        }
    }

    /**
     * Method which stops the sound by {@link #clip} method close()
     */
    public void stopSound() {
        if (clip != null) {
            this.clip.close();
        }
    }

    /**
     * Method which pauses the audio.
     * There is a value {@link #paused} which holds an information if the audio is paused.
     * This value has to be false in order to proceed. Also, the clip has to be initialized. If it's null then it can't be stopped.
     * <p>
     * Sets {@link #pausePosition} to current microsecond position, so the audio can be resumed later.
     */
    public void pause() {
        if (clip != null && !paused) {
            Thread t = new Thread(() -> {
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
                clip.setMicrosecondPosition(pausePosition);
                fadeIn(20);
                paused = false;
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                clip.start();
            });
            t.start();
        }
    }

    /**
     * Loops the audio if requested.
     * Uses {@link #clip} method addLineListener to attach new lineListener.
     * listener reacts to STOP events by restarting the audio.
     * <p>
     * If the event.getType() is LineEvent.Type.STOP then it checks if the STOP state was because of end of the audio file.
     * If yes, then it resets the audio via setting the microsecond position to 0.
     * <p>
     * Also, starts the audio initially, do not call clip.start() before this method.
     *
     * @param loop is true if the audio should be looped.
     * @author ChatGPT (originally made for my first game S.T.A.L.K.E.R. Echoes of Chernobyl in May 2025)
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
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        clip.start();
    }

    /**
     * Sets the desired volume in decibels.
     * <p>
     * Uses FloatControl.TYPE.MASTER_GAIN to control volume gain.
     * The clip has to be initialized and the clip has to support the MASTER_GAIN FloatControl.
     * Gets the clip’s MASTER_GAIN FloatControl and sets its value.
     *
     * @param db requested volume in decibels (-80.0 to 0.0 accepted)
     */
    public void setVolume(float db) {
        if (clip != null && clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gain.setValue(db);
        }
    }

    /**
     * Fades in audio from {@link #initialVolume} - 15 to {@link #initialVolume} decibels.
     * Uses {@link #setVolume(float)}} to set the current volume level.
     *
     * @param milliseconds the desired time that the thread will wait until updating the volume again.
     *                     More millisecond the more time the fade will take.
     */
    public void fadeIn(long milliseconds) {
        Thread t = new Thread(() -> {
            float start = this.initialVolume - 15;
            float end = this.initialVolume;
            float steps = 100;
            float stepSize = (end - start) / steps;


            for (float f = 0; f <= steps; f++) {
                setVolume(start + (stepSize * f));
                if (f == 0) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                try {
                    Thread.sleep(milliseconds);
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

    public float getInitialVolume() {
        return initialVolume;
    }

    public void setInitialVolume(float initialVolume) {
        this.initialVolume = initialVolume;
    }

    @Override
    public String toString() {
        return "Audio{" +
                "initialVolume=" + initialVolume +
                '}';
    }
}
