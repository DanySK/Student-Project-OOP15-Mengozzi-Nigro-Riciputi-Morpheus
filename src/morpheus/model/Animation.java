package morpheus.model;

import java.awt.Graphics2D;
import java.util.Arrays;

/**
 * 
 * @author jacopo
 *
 */
public class Animation {
	private int count;
	private int index;
	private final int speed;
	private final int numFrames;
	/**
	 * current frame.
	 */
	private Image currentFrame;
	/**
	 * All the frames.
	 */
	private final Image[] frames;

	/**
	 * Create an animation.
	 * @param speed
	 * 		speed of frame update
	 * @param frames
	 * 		all the frames
	 */
	public Animation(final int speed, final Image... frames) {
		this.speed = speed;
		this.frames = frames;
		this.numFrames = frames.length;
		currentFrame = frames[0];
	}

	/**
	 * Change to the next frame.
	 */
	protected void nextFrame() {
		currentFrame = frames[index];
		index++;
		if (index >= numFrames) {
			index = 0;
		}
	}

	// Essenzialmente si tratta del metodo tick() classico
	/**
	 * Update the animation.
	 */
	public void run() {
		count++;
		if (count > speed) {
			count = 0;
			nextFrame();
		}
	}

	/**
	 * Draw the animation in the graphics object.
	 * @param g
	 * 		the graphics object.
	 * @param x
	 * 		 x position
	 * @param y
	 * 		 y position
	 */
	public void render(final Graphics2D g, final double x, final double y) {
		if (currentFrame != null) {
			currentFrame.render(g, x, y);
		}
	}
	
	/**
	 * Set the current frame.
	 * @param i
	 *             the new current frame
	 */
	protected void setCurrentFrame(final Image i) {
	    currentFrame = i;
	}
	
	/**
	 * Returns the animation's images.
	 * @return
	 *             the animation's images
	 */
	protected Image[] getFrames() {
	    return Arrays.copyOf(frames, frames.length);
	}
	
}
