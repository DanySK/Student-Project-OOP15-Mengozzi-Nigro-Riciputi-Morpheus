package morpheus.model;

import java.awt.Graphics2D;

import morpheus.view.Texture;

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
    public Obstacle(final Texture t, final int x, final int y, final Graphics2D g) {
        super(t, x, y, g);
    }
}
