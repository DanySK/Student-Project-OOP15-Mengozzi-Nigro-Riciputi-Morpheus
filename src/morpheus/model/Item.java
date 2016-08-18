package morpheus.model;

/**
 * 
 * @author jacopo
 *
 */
public class Item {

    private static final int BULLETS = 5;
    private int hp;
    private int bullet;

    /**
     * Default item: 3 life; 5 bullet; No doubleJump.
     */
    public Item() {
        hp = 3;
        bullet = BULLETS;
    }

    /**
     * Increments life value of 1.
     */
    public void incHP() {
        hp++;
    }

    /**
     * Increments bullet value of 1.
     */
    public void incBullet() {
        bullet++;
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
     * Returns the lifes.
     * 
     * @return the lifes
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
