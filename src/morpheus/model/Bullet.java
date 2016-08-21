package morpheus.model;

import java.awt.Graphics2D;

import morpheus.view.state.GameState;

/**
 * 
 * @author jacopo
 *
 */
public class Bullet extends AbstractDrawable {

    private static final double YOFFSET = 30;
    private static final double XOFFSET = 15;
    private static final double BULLETOFFSET = 750;
    private static final int NCHECK = 5;
    private boolean explosion;
    private int vel = 10;
    private final double initialX;
    private final BulletAnimation animation;
    private int check;

    /**
     * Create a bullet, he get shooted by the player and if it hits something it
     * explode.
     * 
     * @param x
     *            its x position
     * @param y
     *            its y position
     * @param game
     *            the state of game
     * @param i
     *            image of the bullet
     */
    public Bullet(final double x, final double y, final GameState game, final Image i) {
        super(x + XOFFSET, y + YOFFSET, game, i);
        initialX = x + XOFFSET;
        animation = null;
    }

    /**
     * Create a bullet, he get shooted by the player and if it hits something it
     * explode.
     * 
     * @param x
     *            its x position
     * @param y
     *            its y position
     * @param game
     *            the state of game
     * @param i
     *            images of the bullet
     */
    public Bullet(final double x, final double y, final GameState game, final Image... i) {
        super(x + XOFFSET, y + YOFFSET, game, i);
        initialX = x + XOFFSET;
        animation = new BulletAnimation(2, i);
    }

    @Override
    public void tick() {
        if (check == 0) {
            if (getX() >= initialX + BULLETOFFSET) {
                explode();
            }
            this.incX(vel);
        }
    }

    /**
     * 
     */
    public void explode() {
        animation.run();
        vel = 0;
        check = 1;
    }

    
    /**
     * Set the bullet velocity.
     * 
     * @param vel
     *            new velocity
     */
    public void setBulletVelocity(final int vel) {
        this.vel = vel;
    }

    /**
     * Returns the bullet velocity.
     * 
     * @return the bullet velocity
     */
    public int getBulletVelocity() {
        return vel;
    }

    @Override
    public void render(final Graphics2D g) {
        if (check > 0) {
            check++;
        }
        if (check >= NCHECK) {
            setExplosion();
        }
        if (!explosion) {
            if (animation == null) {
                super.render(g);
            } else {
                animation.render(g, getX(), getY());
            }
        } 
       
    }

    /**
     * Set the explosion at true.
     */
    private void setExplosion() {
        explosion = true;
        setRemove();
    }

    /**
     * Returns true if the bullet is exploded, false otherwise.
     * 
     * @return true if the bullet is exploded, false otherwise.
     */
    public boolean isExplosion() {
        return explosion;
    }

    private static class BulletAnimation extends Animation {

        /**
         * 
         * @param speed
         * @param frames
         */
        BulletAnimation(final int speed, final Image... frames) {
            super(speed, frames);

        }

        @Override
        /**
         * The explosion of the bullet.
         */
        public void run() {
            setCurrentFrame(getFrames()[1]);
        }
    }

}
