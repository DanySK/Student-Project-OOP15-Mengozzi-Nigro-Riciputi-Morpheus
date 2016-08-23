package morpheus.controller;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * Class that can manage the audio system
 * 
 * @author matteo
 *
 */
public class AudioPlayer {
	private Clip clip;

	/**
	 * Constructor
	 * 
	 * @param fileName
	 */
	public AudioPlayer(String fileName) {
		try {
			final URL url = AudioPlayer.class.getResource(fileName);
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(url.getPath()));
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/**
	 * Play the track once from the beginning
	 */
	public void play() {
		if (clip != null) {
			clip.stop();
			clip.setFramePosition(0);
			clip.start();
		}
	}

	/**
	 * Play the track continuosly from the beginning
	 */
	public void playAndLoop() {
		if (clip != null) {
			clip.stop();
			clip.setFramePosition(0);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}

	/**
	 * Stop the track
	 */
	public void stop() {
		if (clip == null) {
			return;
		}
		clip.stop();
	}

	/**
	 * Check if the track is active of not
	 * 
	 * @return true if the clip is active, false if it's not active
	 */
	public boolean isActive() {
		return clip.isActive();
	}

	public void setVolume(double volume) {
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		double gain = volume;
		float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
		gainControl.setValue(dB);
	}
}
