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
     * Costruttore che prende in input l'immagine
     * 
     * @author Luca Mengozzi
     * 
     */
    public TextureManager(Image image) {

        this.image = toBufferedImage(image);
    }

    /**
     * 
     * Ritorna l'immagine
     * 
     * @author Luca Mengozzi
     * 
     */
    public BufferedImage getImage() {

        return image;
    }

    /**
     * 
     * Ritorna la larghezza
     * 
     * @author Luca Mengozzi
     * 
     */
    public int getWidth() {

        return image.getWidth();
    }

    /**
     * 
     * Ritorna l'altezza
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

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
}
