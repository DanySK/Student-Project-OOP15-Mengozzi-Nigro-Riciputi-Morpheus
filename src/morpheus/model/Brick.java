package morpheus.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
/**
 * 
 * @author jacopo
 *
 */
public class Brick extends Obstacle {
    
    /**
     * 
     * L'oggetto prende in input l'altezza e la larghezza dell'immagine a schermo, le sue cordinate 
     * e un BufferedImage che sarà l'immagine che si andrà a disegnare sull'oggetto graphics passato al costruttore.
     * @param height
     *          altezza
     * @param width
     *          larghezza
     * @param x
     *          posizione sull'asse x
     * @param y
     *          posizione sull'asse y
     * @param image
     *          Immagine di riferimento
     * @param g
     *          Elemento graphics su cui si andrà a disegnare l'immagine
     */
    public Brick(final int height, final int width, final int x, final int y, final BufferedImage image, final Graphics g) {
        super(height, width, x, y);
        this.setImage(image);
        g.drawImage(this.getImage(), this.getX(), this.getY(), null);
    }

}
