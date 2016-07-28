package morpheus.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * 
 * @author jacopo
 *
 */
public class Image {
	private final BufferedImage image;
	/**
	 * Create an image.
	 * @param image
	 * 		the image
	 */
	public Image(final BufferedImage image) {
		this.image = image;
	}
	
	/**
	 * Returns the image.
	 * @return
	 * 		the image
	 */
	public BufferedImage getImage() {
		return image;
	}
	
	/**
	 * Returns the image's height.
	 * @return
	 * 		the image's height
	 */
	public int getHeigth() {
		return image.getHeight();
	}
	
	/**
	 * Returns the image's width.
	 * @return
	 * 		the image's width
	 */
	public int getWidth() {
		return image.getWidth();
	}
	
	/**
	 * Draw the image in the graphics object.
	 * @param g
	 * 		the graphics object
	 * @param x
	 * 		the x position
	 * @param y
	 * 		the y position
	 */
	public void render(final Graphics2D g, final double x, final double y) {
		g.drawImage(image, (int) x, (int) y, null);
	}
	
}
