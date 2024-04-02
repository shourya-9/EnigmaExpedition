import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * A simple audio player class for playing sound files using Java's Sound API.
 */
public class AudioPlayer {
    private Clip clip; // Represents the audio clip to be played
    private FloatControl volumeControl; // Control for adjusting the volume

    /**
     * Constructs an AudioPlayer object with the specified audio file.
     * @param filePath The path to the audio file to be played.
     */
    public AudioPlayer(String filePath) {
        try {
            // Get an AudioInputStream from the specified file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            // Create a Clip from the AudioInputStream
            clip = AudioSystem.getClip();
            clip.open(audioInputStream); // Open the Clip
            // Get the volume control for the Clip
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace(); // Print any exceptions that occur during initialization
        }
    }

    /**
     * Plays the audio clip from the beginning.
     */
    public void play() {
        // Check if the clip is not null and not already running
        if (clip != null && !clip.isRunning()) {
            clip.setFramePosition(0); // Set the frame position to the beginning
            clip.start(); // Start playing the clip
        }
    }

    /**
     * Loops the audio clip continuously.
     */
    public void loop() {
        // Check if the clip is not null and not already running
        if (clip != null && !clip.isRunning()) {
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the clip continuously
        }
    }

    /**
     * Stops the audio clip if it is currently playing.
     */
    public void stop() {
        // Check if the clip is not null and currently running
        if (clip != null && clip.isRunning()) {
            clip.stop(); // Stop playing the clip
        }
    }

    /**
     * Sets the volume level of the audio clip.
     * @param volume The desired volume level (0.0 - 1.0).
     * @throws IllegalArgumentException if the volume is not within the valid range.
     */
    public void setVolume(float volume) {
        // Check if the volume is within the valid range
        if (volume < 0f || volume > 1f) {
            throw new IllegalArgumentException("Volume not valid: " + volume);
        }
        // Get the minimum and maximum volume values
        float min = volumeControl.getMinimum();
        float max = volumeControl.getMaximum();
        // Set the volume control value based on the desired volume level
        volumeControl.setValue((max - min) * volume + min);
    }
}
