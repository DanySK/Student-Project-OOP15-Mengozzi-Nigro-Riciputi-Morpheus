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
    private static final int WAIT = 10;
    private int counter;
    private final Animation anime;

    /**
     * Create a object with animation.
     * 
     * @param i
     *            array of Image
     * @param x
     *            X position
     * @param y
     *            Y position
     * @param state
     *            game state
     */
    public AbstractPill(final double x, final double y, final GameState state, final Image... i) {
        super(x, y, state, i);
        anime = new Animation(2, i);
    }

    /**
     * Create a obstacle without animation.
     * 
     * @param x
     *            X position
     * @param y
     *            Y position
     * @param state
     *            game state
     * @param i
     *            Image
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
        counter++;
        if (counter == WAIT) {
            anime.run();
            counter = 0;
        }
    }

    @Override
    /**
     * Render the image on screen.
     * 
     * @param g
     *            the graphics
     */
    public void render(final Graphics2D g) {
        if (anime == null) {
            super.render(g);
        } else {
            anime.render(g, getX(), getY());
        }
    }

}
