package morpheus.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * 
 * @author jacopo
 *
 */

public class Image {

    private final BufferedImage img;
    private final int height;
    private final int width;

    /**
     * Create an image.
     * 
     * @param image
     *            the image
     * @param width
     *            his width
     * @param height
     *            his height
     */
    public Image(final BufferedImage image, final int width, final int height) {
        this.img = image;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the image.
     * 
     * @return the image
     */
    public BufferedImage getImage() {
        return img;
    }

    /**
     * Returns the image's height.
     * 
     * @return the image's height
     */
    public int getHeigth() {
        return this.height;
    }

    /**
     * Returns the image's width.
     * 
     * @return the image's width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Draw the image in the graphics object.
     * 
     * @param g
     *            the graphics object
     * @param x
     *            the x position
     * @param y
     *            the y position
     */
    public void render(final Graphics2D g, final double x, final double y) {
        g.drawImage(img, (int) x, (int) y, null);
    }

}
