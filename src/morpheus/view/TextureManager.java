package morpheus.view;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * 
 * @author Luca Mengozzi
 * 
 */
public class TextureManager {

    private BufferedImage image;

    /**
     * 
     * Constructor that takes an image as input
     * 
     * @author Luca Mengozzi
     * 
     */
    public TextureManager(Image image) {

        this.image = toBufferedImage(image);
    }

    /**
     * 
     * Returns the image
     * 
     * @author Luca Mengozzi
     * 
     */
    public BufferedImage getImage() {

        return image;
    }

    /**
     * 
     * Returns the width
     * 
     * @author Luca Mengozzi
     * 
     */
    public int getWidth() {

        return image.getWidth();
    }

    /**
     * 
     * Returns the height
     * 
     * @author Luca Mengozzi
     * 
     */
    public int getHeight() {

        return image.getHeight();
    }

    private BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        return bimage;
    }
}
