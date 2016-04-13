package morpheus.model;

import java.awt.Graphics2D;

import morpheus.model.MorpheusCharacter.Status;
import morpheus.view.Texture;

/**
 * 
 * @author jacopo
 *
 */
public class Laser extends Drawable {

    /**
     * Create a laser.
     * @param t
     *          Texture, with the BufferdImage, the width and the height
     * @param x
     *          posizione sull'asse x
     * @param y
     *          posizione sull'asse y
     * @param g 
     *          il Graphics su cui disegnare l'immagine
     */
    public Laser(final Texture t, final int x, final int y, final Graphics2D g) {
        super(t, x, y, g);
        
    }
    
    /**
     * Create a laser.
     * @param x
     *          posizione sull'asse x
     * @param y
     *          posizione sull'asse y
     * @param g 
     *          il Graphics su cui disegnare l'immagine
     */
    public Laser(final int x, final int y, final Graphics2D g) {
        super(new Texture("res/Laser.png"), x, y, g);
    }
    /**
     * The reaction at the intersection with the main character.
     * Leads the death of him.
     * @param m
     *          the main character
     */
    public void reaction(final MorpheusCharacter m) {
        m.setStatus(Status.DEATH);
    }

}
