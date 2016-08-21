package morpheus.controller;

import morpheus.Morpheus;
import morpheus.model.AbstractDrawable;

/**
 * Camera of the game that focus on player
 * 
 * @author matteo
 *
 */
public class Camera {

	private double x, y;

	/**
	 * Main Constructor
	 * 
	 * @param x
	 * @param y
	 */
	public Camera(double x, double y) {

		this.x = x;
		this.y = y;
	}

	/**
	 * Tick method for logic operation like fixing the camera on the player
	 * 
	 * @param operator
	 */
	public void tick(AbstractDrawable operator) {
		x = -operator.getX() + Morpheus.WIDTH / 8;
	}

	/**
	 * Set the x value of the camera
	 * 
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Set the y value of the camera
	 * 
	 * @param y
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Get the x value of the camera
	 * 
	 * @return
	 */
	public double getX() {
		return x;
	}

	/**
	 * Get the y value of the camera
	 * 
	 * @return
	 */
	public double getY() {
		return y;
	}
}
