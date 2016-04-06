package morpheus.view;

import java.awt.Graphics2D;

public class Animation {
	private int count;
	private int index;
	private int speed;
	private int numFrames;
	private Sprite currentFrame;
	private Sprite[] frames;

	public Animation(int speed, Sprite... frames) {
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
	public void run() {
		count++;
		if (count > speed) {
			count = 0;
			nextFrame();
		}
	}

	public void render(Graphics2D g, double x, double y) {
		if (currentFrame != null) {
			currentFrame.render(g, x, y);
		}
	}

}
