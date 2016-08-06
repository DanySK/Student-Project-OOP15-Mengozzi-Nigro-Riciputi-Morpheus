package morpheus.model.monster;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import morpheus.model.Bullet;
import morpheus.model.Image;
import morpheus.model.ModelAnimation;
import morpheus.view.GameState;
import morpheus.view.Texture;

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
     * @param i
     *            the animation's images
     */
    public Tree(final double x, final double y, final GameState game, final Image... i) {
        super(x, y, game, i);
        final TreeAnimation anime = new TreeAnimation(this, i);
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

    private static class TreeAnimation extends ModelAnimation implements Runnable {

        private static final int THREADSLEEP = 1500;
        private static final int BULLETDIMENSION = 18;
        private final Tree tree;

        TreeAnimation(final Tree tree, final Image... frames) {
            super(2, frames);
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
            return new TreeBullet(tree.getX(), tree.getY(), tree.getState(),
                    new Image(new Texture("res/zucca.png").getImage(), BULLETDIMENSION, BULLETDIMENSION));
        }

    }

    /**
     * 
     * @author jacopo
     *
     */
    public static class TreeBullet extends Bullet {

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
        public TreeBullet(final double x, final double y, final GameState game, final Image i) {
            super(x, y, game, i);

        }

        @Override
        public void tick() {
            this.decX(this.getBulletVelocity());
        }

    }
}
