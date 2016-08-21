package morpheus.model.monster;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

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

    private final List<TreeBullet> bullets;
    private volatile boolean threadStop;

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
        final TreeAnimation anime = new TreeAnimation(this, p, i);
        setAnime(anime);
        bullets = new ArrayList<>();
        threadStop = false;
        new Thread(anime).start();
    }

    @Override
    public void tick() {
        for (final TreeBullet tb : bullets) {
            tb.tick();
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
        threadStop = true;
    }

    private static class TreeAnimation extends Animation implements Runnable {

        private static final int THREADSLEEP = 1500;
        private static final int BULLETDIMENSION = 18;
        private final Tree tree;
        private final Player p;

        TreeAnimation(final Tree tree, final Player p, final Image... frames) {
            super(2, frames);
            this.p = p;
            tree.threadStop = false;
            this.tree = tree;
            setCurrentFrame(frames[0]);
        }

        public void run() {

            while (!tree.threadStop) {
                super.run();
                tree.addBullet(shoot());

                try {

                    Thread.sleep(100);
                    super.run();
                } catch (InterruptedException e) {
                    System.out.println("ThreadError");
                }
                try {
                    Thread.sleep(100);
                    super.run();

                } catch (InterruptedException e) {
                    System.out.println("ThreadError");
                }

                try {
                    Thread.sleep(THREADSLEEP);
                    super.run();

                } catch (InterruptedException e) {
                    System.out.println("ThreadError");
                }

            }
        }

        private TreeBullet shoot() {
            return new TreeBullet(tree.getX(), tree.getY(), tree.getState(), p, 
                    new Image(new Texture("res/zucca.png").getImage(), BULLETDIMENSION, BULLETDIMENSION));
        }

    }

    /**
     * 
     * @author jacopo
     *
     */
    public static class TreeBullet extends Bullet {

        private static final double SCREENHEIGHT = 500;
        private static final double BULLETOFFSET = 750;
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
         * @param i
         *            the image
         */
        public TreeBullet(final double x, final double y, final GameState game, final Player p, final Image i) {
            super(x, y, game, i);
            initialX = x;
            if (p.getY() > y) {
                incY = 0;
            } else {
                incY = (SCREENHEIGHT - p.getY()) / ((x - p.getX()) / p.getVelRun());
            }
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
