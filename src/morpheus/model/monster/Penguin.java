package morpheus.model.monster;

import java.awt.Color;
import java.awt.Graphics2D;

import morpheus.model.Image;
import morpheus.Morpheus;
import morpheus.model.Animation;
import morpheus.view.state.GameState;

/**
 * 
 * @author jacopo
 *
 */
public class Penguin extends AbstractMonster {

    private static final int DIMENSION64 = 64;
    private static final int PENGUINOFFSET = 30;

    /**
     * Create a Penguin monster.
     * 
     * @param x
     *            his X
     * @param y
     *            his Y
     * @param game
     *            the GameState
     * @param i
     *            the animation image
     */
    public Penguin(final double x, final double y, final GameState game, final Image... i) {
        super(x, y, game, i);
        setAnime(new PenguinAnimation(2, i));
    }

    @Override
    public void tick() {
        if (isDirection()) {

            incX(1);
            if (getX() >= getInitialX() + PENGUINOFFSET) {
                getAnimation().run();

                changeDirection();
            }
        } else {

            decX(1);
            if (getX() <= getInitialX() - PENGUINOFFSET) {
                getAnimation().run();

                changeDirection();
            }
        }
    }

    /**
     * Set a new initialX for the monster.
     * 
     * @param x
     *            new x
     */
    public void setCentralX(final double x) {
        this.setInitialX(x + DIMENSION64 - getHeight());
    }

    @Override
    public void render(final Graphics2D g) {
        if (getAnimation() == null) {
            super.render(g);
        } else {
            getAnimation().render(g, getX(), getY());
        }
        if (Morpheus.DEBUG) {
            g.setColor(Color.BLACK);
            g.draw(getTop());
            g.setColor(Color.BLUE);
            g.draw(getBottom());
            g.setColor(Color.MAGENTA);
            g.draw(getLeft());
            g.setColor(Color.ORANGE);
            g.draw(getRight());
        }
    }

    private static class PenguinAnimation extends Animation {

        /**
         * Penguin Animation.
         * @param speed
         *        speed animation.
         * @param frames
         *        array of Image
         */
        PenguinAnimation(final int speed, final Image... frames) {
            super(speed, frames);
            setCurrentFrame(frames[1]);
        }

        public void run() {
            nextFrame();
        }

    }

}
