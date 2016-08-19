package morpheus.model;

import morpheus.view.state.GameState;

/**
 * 
 * @author jacopo
 *
 */
public class Spikes extends AbstractDrawable {

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

}
