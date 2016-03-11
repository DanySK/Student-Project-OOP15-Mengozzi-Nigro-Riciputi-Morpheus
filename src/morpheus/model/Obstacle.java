package morpheus.model;
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
     */
    public Obstacle(final int height, final int width, final int x, final int y) {
        super(height, width, x, y);
    }
}
