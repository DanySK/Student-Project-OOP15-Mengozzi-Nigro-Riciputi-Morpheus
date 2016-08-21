package morpheus.model.monster;

import java.awt.Graphics2D;

import morpheus.model.Image;
import morpheus.model.Animation;
import morpheus.view.state.GameState;

/**
 * 
 * @author jacopo
 *
 */
public class Ghost extends AbstractMonster {

    private static final int DIMENSION64 = 64;
    private static final int GHOSTOFFSET = 50;
    
    /**
     * Create a Ghost monster.
     * 
     * @param x
     *            X position
     * @param y
     *            Y position
     * @param game
     *            GameState
     * @param i
     *            the animation's images
     */
    public Ghost(final double x, final double y, final GameState game, final Image... i) {
        super(x, y, game, i);
        setAnime(new Animation(2, i) {
            
            @Override
            public void run() {
                nextFrame();
            }
        });
    }

    @Override
    public void tick() {
        if (isDirection()) {
            decY(1);
            if (getY() <= getInitialY() - GHOSTOFFSET) {
                getAnimation().run();

                changeDirection();
            }
        } else {
            incY(1);
            if (getY() >= getInitialY() + GHOSTOFFSET) {
                getAnimation().run();

                changeDirection();
            }
        }
    }
    
    /**
     * Set a new initialY for the monster.
     * @param y
     *          new y
     */
    public void setCentralY(final double y) {
        this.setInitialY(y + DIMENSION64 - getHeight());
    }
    
    @Override
    public void render(final Graphics2D g) {
        this.getAnimation().run();
        this.tick();
        if (getAnimation() == null) {
            super.render(g);
        } else {
            getAnimation().render(g, getX(), getY());
        }
    }

}
