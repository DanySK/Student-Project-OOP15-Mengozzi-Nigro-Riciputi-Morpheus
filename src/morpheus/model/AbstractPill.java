package morpheus.model;

import java.awt.Graphics2D;

import morpheus.view.state.GameState;

/**
 * 
 * @author jacopo
 *
 */
public abstract class AbstractPill extends AbstractDrawable {

    private static final double DIMENSION64 = 64;
    private final Animation anime;

    /**
     * Create a object with animation.
     * 
     * @param i
     *            have all the information of the images
     * @param x
     *            posizione sull'asse x
     * @param y
     *            posizione sull'asse y
     * @param state
     *            state of game
     */
    public AbstractPill(final double x, final double y, final GameState state, final Image... i) {
        super(x, y, state, i);
        anime = new Animation(2, i);
    }

    /**
     * Create a obstacle without animation.
     * 
     * @param x
     *            posizione sull'asse x
     * @param y
     *            posizione sull'asse y
     * @param state
     *            state of game
     * @param i
     *            have all the information of the image
     */
    public AbstractPill(final double x, final double y, final GameState state, final Image i) {
        super(x, y, state, i);
        anime = null;
    }

    /**
     * The reaction at the intersection with the main character. Leads the death
     * of him.
     * 
     */
    public abstract void reaction();
    
    @Override
    public void setX(final double x) {
        super.setX(x + DIMENSION64 - getWidth());
    }
    
    @Override
    public void setY(final double y) {
        super.setY(y + DIMENSION64 - getHeight());
    }

    @Override
    public void tick() {
       anime.run();
    }

    @Override
    /**
     * Render the image on screen.
     * @param g
     *          the graphics
     */
    public void render(final Graphics2D g) {
        if (anime == null) {
            super.render(g);
        } else {
            anime.render(g, getX(), getY());
        }
    }

}
