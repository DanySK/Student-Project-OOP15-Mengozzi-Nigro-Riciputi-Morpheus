package morpheus.model.monster;

import java.awt.Graphics2D;

import morpheus.model.AbstractDrawable;
import morpheus.model.Image;
import morpheus.model.ModelAnimation;
import morpheus.view.GameState;

/**
 * 
 * @author jacopo
 * 
 *         Create a monster.
 */
public abstract class AbstractMonster extends AbstractDrawable {

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
    public AbstractMonster(final double x, final double y, final GameState game, final Image i) {
        super(x, y, game, i);
        initialX = x;
        initialY = y;
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
    public AbstractMonster(final double x, final double y, final GameState game, final Image... i) {
        super(x, y, game, i);
        initialX = x;
        initialY = y;
        anime = new ModelAnimation(2, i);
    }

    /**
     * 
     *   Monster behavior.
     */
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
        if (anime == null) {
            super.render(g);
        } else {
            anime.render(g, getX(), getY());
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
    protected double getInitialY() {
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
    
    /**
     * Returns the animation.
     * @return
     *          the animation
     */
    protected ModelAnimation getAnimation() {
        return anime;
    }
    
    /**
     * Change the monster direction.
     */
    protected void changeDirection() {
        direction = !direction;
    }
    
    /**
     * Returns the monster direction.
     * @return
     *          the monster direction
     */
    protected boolean isDirection() {
        return direction;
    }
    
   

}
