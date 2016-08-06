package morpheus.model;

import java.awt.Graphics2D;

import morpheus.view.GameState;

/**
 * 
 * @author jacopo
 *
 */
public class Bullet extends AbstractDrawable {
    private int vel = 10;
    private final BulletAnimation animation;

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
        super(x, y, game, i);
        animation = null;

        // TODO Auto-generated constructor stub
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
        super(x, y, game, i);
        animation = new BulletAnimation(2, i);
    }

    @Override
    public void tick() {
        this.incX(vel);

    }

    /**
     * 
     */
    public void explode() {
        animation.run();
        vel = 0;
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
        if (animation == null) {
            super.render(g);
        } else {
            animation.render(g, getX(), getY());
        }
    }

    private static class BulletAnimation extends ModelAnimation {

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
