package morpheus.controller;

import morpheus.Morpheus;
import morpheus.model.AbstractDrawable;
import morpheus.model.Player;

public class Camera {
	
	private double x, y;

	public Camera(double x, double y) {
		
		this.x = x;
		this.y = y;
	}

	public void tick(AbstractDrawable operator) {
		x = -operator.getX() + Morpheus.WIDTH / 8;//Rimettere /2 anziche /8
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
