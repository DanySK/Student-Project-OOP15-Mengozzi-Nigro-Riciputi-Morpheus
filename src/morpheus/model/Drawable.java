package morpheus.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * 
 * @author jacopo
 *
 */
public interface Drawable {
    /**
     * Logic implementation for the object move.
     */
    void tick();

    /**
     * Returns a rectangle that representing the image's bounds.
     * 
     * @return a rectangle that representing the image's bounds.
     */
    Rectangle getBounds();

    /**
     * Returns a rectangle that representing the image's top bound.
     * 
     * @return a rectangle that representing the image's top bound.
     */
    Rectangle getTop();

    /**
     * Returns a rectangle that representing the image's left bound.
     * 
     * @return a rectangle that representing the image's left bound.
     */
    Rectangle getLeft();

    /**
     * Returns a rectangle that representing the image's right bound.
     * 
     * @return a rectangle that representing the image's right bound.
     */
    Rectangle getRight();

    /**
     * Returns a rectangle that representing the image's bottom bound.
     * 
     * @return a rectangle that representing the image's bottom bound.
     */
    Rectangle getBottom();

    /**
     * Take in input a graphics element and draw the image in the window.
     * 
     * @param g
     *            the graphic element
     */
    void render(final Graphics2D g);

    /**
     * Set the X position.
     * 
     * @param x
     *            new X position
     */
    void setX(final double x);

    /**
     * Set the Y position.
     * 
     * @param y
     *            new Y position
     */
    void setY(final double y);

    /**
     * Returns the X position.
     * 
     * @return the X position
     */
    double getX();

    /**
     * Returns the Y position.
     * 
     * @return the Y position
     */
    double getY();

    /**
     * Return the main image of the Drawable object.
     * 
     * @return the main image
     */
    BufferedImage getMainImage();

    /**
     * Image's height.
     * 
     * @return image's height
     */
    int getHeight();

    /**
     * Image's width.
     * 
     * @return image's width
     */
    int getWidth();

    /**
     * Set at true the remove of a Drawable.
     */
    void setRemove();

    /**
     * Returns true if the Drawable is to remove, false otherwise.
     * 
     * @return true if the Drawable is to remove, false otherwise.
     */
    boolean isRemove();

}
