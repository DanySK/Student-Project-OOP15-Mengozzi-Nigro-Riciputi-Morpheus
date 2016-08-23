package morpheus.model;

/**
 * 
 * @author jacopo
 *
 */
public class Item {

    private static final int BULLETS = 3;
    private static final int MAXHP = 3;
    private static final int INCBULLET = 6;
    
    private int hp;
    private int bullet;

    /**
     * Default item: 3 life; 5 bullet.
     */
    public Item() {
        hp = 3;
        bullet = BULLETS;
    }

    /**
     * Increments life value of 1. If life are >= 3 don't do nothing.
     */
    public void incHP() {
        if (getHP() < MAXHP) {
            hp++;
        }
    }

    /**
     * Increments bullet value of 1.
     */
    public void incBullet() {
        bullet = INCBULLET;
    }

    /**
     * Decrements life value of 1.
     */
    public void decHP() {
        hp--;
    }

    /**
     * Decrements bullet value of 1.
     */
    public void decBullet() {
        bullet--;
    }

    /**
     * Returns the life.
     * 
     * @return the life
     */
    public int getHP() {
        return hp;
    }

    /**
     * Returns the bullets.
     * 
     * @return the bullets
     */
    public int getBullet() {
        return bullet;
    }

    /**
     * Reset the item.
     */
    public void reset() {
        hp = 3;
        bullet = BULLETS;
    }
}
