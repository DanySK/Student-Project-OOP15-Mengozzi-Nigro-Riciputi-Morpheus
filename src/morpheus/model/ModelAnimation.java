package morpheus.model;

import java.awt.Graphics2D;

/**
 * 
 * @author jacopo
 *
 */
public class ModelAnimation {
	private int count;
	private int index;
	private int speed;
	private int numFrames;
	/**
	 * current frame.
	 */
	protected Image currentFrame;
	/**
	 * All the frames.
	 */
	protected Image[] frames;

	/**
	 * Create an animation.
	 * @param speed
	 * 		speed of frame update
	 * @param frames
	 * 		all the frames
	 */
	public ModelAnimation(final int speed, final Image... frames) {
		this.speed = speed;
		this.frames = frames;
		this.numFrames = frames.length;
	}

	private void nextFrame() {
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
	
}
