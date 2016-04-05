package morpheus.model;

import java.awt.Graphics;

/**
 * 
 * @author jacopo
 *
 */
public class Obstacle extends Drawable {

    /**
     * 
     * @param height
     *          altezza
     * @param width
     *          larghezza
     * @param x
     *          posizione sull'asse x
     * @param y
     *          posizione sull'asse y
     * @param g 
     *          il Graphics su cui disegnare l'immagine
     */
    public Obstacle(final int height, final int width, final int x, final int y, final Graphics g) {
        super(height, width, x, y, g);
    }
}
