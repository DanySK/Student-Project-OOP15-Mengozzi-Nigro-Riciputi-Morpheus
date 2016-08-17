package morpheus.model;

import morpheus.view.state.GameState;

public class Spikes extends AbstractDrawable {

    public Spikes(double x, double y, GameState game, Image i) {
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
    public void reaction(Player p) {
        p.getItem().decHP();
    }

}
