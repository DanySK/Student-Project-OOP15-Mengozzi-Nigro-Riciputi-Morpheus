package morpheus.model;

import morpheus.model.MainPlayer.Status;
import morpheus.view.state.GameState;

/**
 * 
 * @author jacopo
 *
 */
public class Obstacle extends AbstractDrawable {

    /**
     * Create a object with animation.
     * @param i
	 *            have all the information of the images
     * @param x
     *          posizione sull'asse x
     * @param y
     *          posizione sull'asse y
     * @param state 
     *          state of game
     */
    public Obstacle(final int x, final int y, final GameState state, final Image... i) {
        super(x, y, state, i);
        
    }
    
    /**
     * Create a obstacle without animation.
     
     * @param x
     *          posizione sull'asse x
     * @param y
     *          posizione sull'asse y
     * @param state
     *         state of game
     * @param i
	 *         have all the information of the image
     */
    public Obstacle(final int x, final int y, final GameState state, final Image i) {
        super(x, y, state, i);
    }
    /**
     * The reaction at the intersection with the main character.
     * Leads the death of him.
     * @param m
     *          the main character
     */
    public void reaction(final MainPlayer m) {
        m.setStatus(Status.DEATH);
  }

    @Override
    public void tick() {
         //TODO Auto-generated method stub
        
   }

	

}
