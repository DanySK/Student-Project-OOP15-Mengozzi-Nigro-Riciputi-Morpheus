package morpheus.model.monster;

import java.awt.Graphics2D;

import morpheus.model.AbstractDrawable;
import morpheus.model.Image;
import morpheus.model.ModelAnimation;
import morpheus.view.state.GameState;

/**
 * 
 * @author jacopo
 * 
 *         Create a monster.
 */
public abstract class Monster extends AbstractDrawable {

    private final double initialX;
    private final double initialY;
    private boolean direction = true;
    private ModelAnimation anime;

    /**
     * Create a static monster.
     * 
     * @param x
     *            X position
     * @param y
     *            Y position
     * @param game
     *            state of game
     * @param i
     *            have all the information on the image
     */
    public Monster(final double x, final double y, final GameState game, final Image i) {
        super(x, y, game, i);
        initialX = x;
        initialY = y;
        anime = null;
    }

    /**
     * Create an animated monster.
     * 
     * @param x
     *            X position
     * @param y
     *            Y position
     * @param game
     *            state of game
     * @param i
     *            have all the information on the images
     */
    public Monster(final double x, final double y, final GameState game, final Image[] i) {
        super(x, y, game, i);
        initialX = x;
        initialY = y;
        anime = new ModelAnimation(2, i);
    }

    
    protected void mytick() {
       anime.run();
        if (direction) {
            incX(1);
            if (getX() >= initialX + 10) {
                direction = false;
            }
        } else {
            decX(1);
            if (getX() <= initialX - 10) {
                direction = true;
            }
        }
    }

    @Override
    public void render(final Graphics2D g) {
        this.tick();
        if (anime != null) {

            anime.render(g, getX(), getY());
        } else {
            super.render(g);

        }
    }

    /**
     * Returns the initial X value.
     * 
     * @return the initial X value
     */
    protected double getInitialX() {
        return initialX;
    }

    /**
     * Returns the initial Y value.
     * 
     * @return the initial Y value.
     */
    protected double getiInitialY() {
        return initialY;
    }
    
    /**
     * Set the animation style.
     * @param anime
     *          the new animation
     */
    protected void setAnime(final ModelAnimation anime) {
        this.anime = anime;
    }
    
    protected ModelAnimation getAnimation() {
        return anime;
    }
    
    /**
     * Change the monster direction.
     */
    protected void changeDirection() {
        direction = !direction;
    }
    
    protected boolean getDirection() {
        return direction;
    }
    
   

}
