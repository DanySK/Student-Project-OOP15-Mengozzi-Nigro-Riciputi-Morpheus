package morpheus.model.monster;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import morpheus.model.Animation;
import morpheus.model.Bullet;
import morpheus.model.Image;
import morpheus.model.Player;
import morpheus.view.Texture;
import morpheus.view.state.GameState;

/**
 * 
 * @author jacopo
 *
 */

public class Tree extends AbstractMonster {

    private static final int READY = 70;
    private static final int GO = 140;
    private static final int SLEEP = 180;
    private static final int BULLETDIMENSION = 18;
    private final List<TreeBullet> bullets;
    private final Player p;
    private int counter;
    private final TreeAnimation anime;

    /**
     * Create a Tree monster.
     * 
     * @param x
     *            X position
     * @param y
     *            Y position
     * @param game
     *            GameState
     * @param p
     *            player
     * @param i
     *            the animation's images
     */
    public Tree(final double x, final double y, final GameState game, final Player p, final Image... i) {
        super(x, y, game, i);
        anime = new TreeAnimation(i);
        this.p = p;
        setAnime(anime);
        bullets = new ArrayList<>();
    }

    @Override
    public void tick() {
        for (final ListIterator<TreeBullet> iter = bullets.listIterator(); iter.hasNext();) {
            final TreeBullet e = iter.next();
            e.tick();
            if (e.isRemove()) {
                iter.remove();
            }
        }
        counter++;
        switch (counter) {
        case READY:
            anime.animationReady();
            break;
        case GO:
            anime.animationShoot();
            shoot();
            break;
        case SLEEP:
            anime.animationWait();
            counter = 0;
            break;
        default:
            break;
        }
    }

    /**
     * Render on screen the tree.
     * 
     * @param g
     *            the graphics
     */
    public void render(final Graphics2D g) {
        getAnimation().render(g, getX(), getY());
        for (final Bullet b : bullets) {
            b.render(g);
        }
    }

    /**
     * Add a bullet on the tree bullet's list.
     * 
     * @param b
     *            the new bullet
     */
    public void addBullet(final TreeBullet b) {
        bullets.add(b);
    }

    /**
     * Stop the thread.
     */
    public void stop() {
        setRemove();
    }

    private TreeBullet shoot() {
        return new TreeBullet(getX(), getY(), getState(), p,
                new Image(new Texture("res/zucca.png").getImage(), BULLETDIMENSION, BULLETDIMENSION));
    }

    private static class TreeAnimation extends Animation {

        private static final int WAIT = 2;
        private static final int READY = 1;
        private static final int SHOOT = 0;

        TreeAnimation(final Image... frames) {
            super(2, frames);
            setCurrentFrame(frames[WAIT]);
        }

        public void animationWait() {
            setCurrentFrame(getFrames()[WAIT]);
        }

        public void animationReady() {
            setCurrentFrame(getFrames()[READY]);
        }

        public void animationShoot() {
            setCurrentFrame(getFrames()[SHOOT]);
        }

    }

    /**
     * 
     * @author jacopo
     *
     */
    public static class TreeBullet extends Bullet {

        private static final double BULLETOFFSET = 300;
        private static final int BULLETVEL = 3;
        private static final double MAXY = 5;
        private final double incY;

        private final double initialX;

        /**
         * Create a tree bullet.
         * 
         * @param x
         *            X position
         * @param y
         *            Y position
         * @param game
         *            GameState
         * @param p
         *            player
         * @param i
         *            the image
         */
        public TreeBullet(final double x, final double y, final GameState game, final Player p, final Image i) {
            super(x, y, game, i);
            initialX = x;
            double app;
            if (p.getY() > y) {
                app = -(p.getY() - y) / ((x - p.getX()));
                if (app < -MAXY) {
                    incY = -MAXY;
                } else {
                    incY = app;
                }
            } else {
                app = (p.getY() - y) / ((x - p.getX()));
                if (app < MAXY) {
                    incY = MAXY;
                } else {
                    incY = app;
                }
            }
            setBulletVelocity(BULLETVEL);
        }

        @Override
        public void tick() {
            if (getX() <= initialX - BULLETOFFSET) {
                setRemove();
            }
            this.decY(incY);
            this.decX(this.getBulletVelocity());
        }

    }
}
