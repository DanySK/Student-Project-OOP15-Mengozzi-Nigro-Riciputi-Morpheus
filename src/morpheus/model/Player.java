package morpheus.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import morpheus.controller.KeyInput;
import morpheus.view.Texture;
import morpheus.view.state.GameState;

/**
 * @author jacopo
 */
public class Player extends AbstractDrawable {

    private static final int TYLE_SYNCH_START = 200;
    private static final int OFFSET_COLLISION = 5;
    private static final int BULLET_SIZE = 24;
    private static final int PLAYER_SPEED_ANIMATION = 6;
    private static final int OFFSET = 15;
    private static final double DEATH_SIZE = 450;
    
    
    /**
     * Initial velocity.
     */
    public static final double INITIAL_VEL = 5;
    private final Option s;
    private final PlayerAnimation animation;
    private final Item item;
    private final PlayerManager manager;
    private int tileSynch;
    private double velRun = INITIAL_VEL;
    private boolean runGO;
    private boolean death;
    private List<Bullet> bullets;
    /**
     * Create the player.
     * 
     * @param x
     *            x position
     * @param y
     *            y position
     * @param game
     *            game state
     * @param option
     *            Settings
     * @param i
     *            animation's images
     */
    public Player(final double x, final double y, final GameState game, final Option option, final Image... i) {
        super(x, y, game, i);
        s = option;
        this.runGO = true;
        animation = new PlayerAnimation(PLAYER_SPEED_ANIMATION, i);
        item = new Item();
        manager = new PlayerManager(animation);
        tileSynch = TYLE_SYNCH_START;
        bullets = new ArrayList<>();
    }

    @Override
    public void tick() {
        if (getY() >= DEATH_SIZE || getItem().getHP() <= 0) {
            this.dead();
        }
        if (!manager.isVerticalCollision()) {
            fall();
            super.incY(manager.getVelY());
        }
        if (isRunning()) {
            goOn();
        }
        if (manager.getCounterJump() >= PlayerManager.CHECKINJUMP) {
            super.decY(manager.jumping());
        }
        if (!manager.isInFall() && manager.getCounterJump() == 0) {
            animation.run();
        }
        checkBullets();
        if (manager.isKnocking()) {
            manager.knocking();
        }
        if (KeyInput.isPressed(s.getKeyJump())) {
            if (manager.isInFall() && manager.isDoubleJump()) {
                manager.setForDoubleJump();
                super.decY(manager.jump(s));
            } else {
                super.decY(manager.jump(s));
            }
        }
        if (KeyInput.isPressed(s.getKeyShoot())) {
            shoot();
        }
    }

    /**
     * Move the player on the horizontal axis. 
     */
    private void goOn() {
        this.incX(velRun);
        tileSynch += velRun;
    }

    private void fall() {
        manager.fall();
        super.incY(manager.getVelY());
    }

    /**
     * Set the fall a true.
     */
    public void falling() {
        manager.setInFall(true);
    }

    /**
     * Set all the variable for simulating the ground collision.
     */
    public void groundCollission() {
        manager.groundCollission();
    }

    /**
     * Doesn't allow the player to jump.
     */
    public void stopJumping() {
        manager.stopJumping();
    }

    /**
     * Allow the jump at the player.
     */
    public void jumpPermission() {
        manager.jumpPermission();
    }

    /**
     * Returns the run velocity.
     * @return the run velocity
     */
    public double getVelRun() {
        return velRun;
    }

    /**
     * Returns true if the player is dead. False otherwise.
     * @return true if the player is dead. False otherwise.
     */
    public boolean isDeath() {
        return death;
    }
    
    /**
     * Returns true if the champion have a collision, false otherwise.
     * @return
     *          true if the champion have a collision, false otherwise.
     */
    public boolean isKnocking() {
        return manager.isKnocking();
    }

    /**
     * Create a bullet object.
     */
    public void shoot() {
        if (item.getBullet() <= 0) {
            System.out.println("You can't shoot. No bullet");
        } else {
            getItem().decBullet();
            bullets.add(new Bullet(getX(), getY(), getState(),
                    new Sprite(new SpriteSheet(new Texture("res/bullet.png"), BULLET_SIZE), 2, 1, 2).getFramesAsList()));
        }
    }

    /**
     * Returns the list of not exploded bullet.
     * @return list of bullets.
     */
    public List<Bullet> getBullets() {
        return bullets;
    }

    /**
     * Set the Y change value.
     * @param x
     *            the new valor
     */
    public void setVelY(final double x) {
        manager.setVelY(x);
    }

    /**
     * Returns the Y change value.
     * @return the Y change value
     */
    public double getVelY() {
        return manager.getVelY();
    }

    /**
     * Set the run on true, so the character start his running.
     */
    public void startRun() {
        this.runGO = true;
    }

    /**
     * Set the run on false, so stop the character's run.
     */
    public void stopRun() {
        this.runGO = false;
    }

    /**
     * Return true if the characters is moving. Else otherwise.
     * @return true if the characters is moving, else otherwise
     */
    public boolean isRunning() {
        return this.runGO;
    }

    /**
     * Reset of the player for a new start.
     * @param x
     *            x start position
     * @param y
     *            y start position
     * @param state
     *            the game state
     */
    public void reset(final double x, final double y, final GameState state) {
        setX(x);
        setY(y);
        setState(state);
        manager.reset();
        runGO = true;
        item.reset();
        tileSynch = TYLE_SYNCH_START;
        bullets = new ArrayList<>();
        death = false;
    }

    /**
     * Death of the player, stop it in the current position.
     */
    public void dead() {
        this.stopRun();
        manager.dead();
        death = true;
    }

    /**
     * Set the run velocity.
     * @param vel
     *            the new velocity
     */
    public void setVelRun(final double vel) {
        this.velRun = vel;
    }

    @Override
    public void render(final Graphics2D g) {
        if (isRunning()) {
            animation.render(g, getX(), getY());
        } else {
            super.render(g);
        }
        stopRun();
    }
    
    /**
     * Dec life and give a immune status.
     */
    public void hit() {
        getItem().decHP();
        manager.hit();
    }

    /**
     * Returns the player's item.
     * @return the player's item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @return the tileSynch
     */
    public int getTileSynch() {
        return tileSynch;
    }

    /**
     * @return the inJump
     */
    public boolean isInJump() {
        return manager.isInJump();
    }

    /**
     * Set true if the player is in Jump, false otherwise.
     * 
     * @param inJump
     *            the inJump to set
     */
    public void setInJump(final boolean inJump) {
        manager.setInJump(inJump);
    }

    /**
     * Returns true if the player has vertical collision, false otherwise.
     * 
     * @return true if the player has vertical collision, false otherwise.
     */
    public boolean isVerticalCollision() {
        return manager.isVerticalCollision();
    }

    /**
     * 
     * Set true if the player has vertical collision, false otherwise.
     * 
     * @param verticalCollision
     *            true if the player has vertical collision, false otherwise.
     */
    public void setVerticalCollision(final boolean verticalCollision) {
        manager.setVerticalCollision(verticalCollision);
    }

    /**
     * Returns the bottom of the player.
     * @return 
     *          the bottom of the player
     */
    public Area getBottomArea() {
        return new Area(new Rectangle((int) getX() + OFFSET_COLLISION, (int) getY() + getHeight() - 3, getWidth() - OFFSET, 3));
    }

    private void checkBullets() {
        for (final ListIterator<Bullet> iter = bullets.listIterator(); iter.hasNext();) {
            final Bullet e = iter.next();
            if (e.isExplosion()) {
                iter.remove();
            }
        }
    }
}
