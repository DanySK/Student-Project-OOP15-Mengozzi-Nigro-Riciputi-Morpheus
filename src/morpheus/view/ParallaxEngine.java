package morpheus.view;

import java.awt.Graphics2D;

public class ParallaxEngine {
	
	private ParallaxLayer[] layers;

	public ParallaxEngine(ParallaxLayer... layers) {
		
		this.layers = layers;
	}

	public void setRight() {
		
		for (int i = 0; i < layers.length; i++) {
			
			layers[i].setRight();
		}
	}

	public void setLeft() {
		
		for (int i = 0; i < layers.length; i++) {
			
			layers[i].setLeft();
		}
	}

	public void stop() {
		
		for (int i = 0; i < layers.length; i++) {
			
			layers[i].stop();
		}
	}

	public void move() {
		
		for (int i = 0; i < layers.length; i++) {
			
			layers[i].move();
		}
	}

	public void render(Graphics2D g) {
		
		for (int i = 0; i < layers.length; i++) {
			
			layers[i].render(g);
		}
	}
}
