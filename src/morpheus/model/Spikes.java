package morpheus.model;

import java.awt.Rectangle;

import morpheus.view.state.GameState;

/**
 * 
 * @author jacopo
 *
 */
public class Spikes extends AbstractDrawable {

    private static final int OFFSET = 16;
    /**
     * Spikes.
     * @param x
     *          X position
     * @param y
     *          y position
     * @param game
     *          game state
     * @param i
     *          image
     */
    public Spikes(final double x, final double y, final GameState game, final Image i) {
        super(x, y, game, i);
    }

    @Override
    public void tick() {
        
    }
    
    /**
     * Decrements the HP of the player.
     * @param p
     *          the current player
     */
    public void reaction(final Player p) {
        p.hit();
    }
    
    public Rectangle getTop() {
        return new Rectangle((int) this.getX(), (int) this.getY() + OFFSET, this.getWidth(), 1);
    }
    
    public Rectangle getLeft() {
        return new Rectangle((int) this.getX(), (int) this.getY() + OFFSET, 1, this.getHeight() - OFFSET);
    }

    public Rectangle getRight() {
        return new Rectangle((int) this.getX() + this.getWidth(), (int) this.getY() + OFFSET, 1, this.getHeight() - OFFSET);
    }

}
