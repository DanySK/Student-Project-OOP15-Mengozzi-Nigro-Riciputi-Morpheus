package morpheus.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import morpheus.Morpheus;
import morpheus.controller.KeyInput;
import morpheus.model.Exceptions.NoBulletException;
import morpheus.view.Texture;
import morpheus.view.state.GameState;

/**
 * 
 * @author jacopo
 * 
 *         Create the main Character.
 */
public final class MainPlayer extends AbstractDrawable {

    private static MainPlayer player;

    private static final int PLAYERHEIGTH = 60;
    private static final int PLAYERWIDTH = 41;

    private static final int NJUMP = 11;
    private static final int NDOUBLEJUMP = 10;
    private static final int CHECKINJUMP = 1;
    private static final int TYLESYNCHSTART = 200;
    private static final int NFALL = 55;
    private static final double GRAVITYPLUS = 0.6;
    private static final int OFFSETCOLLISION = 5;
    private static final int BULLETSIZE = 24;
    private static final int NSCORE = 20;
    /**
     * velocità iniziale.
     */
    public static final int INITIAL_VEL = 5;

    /**
     * Gravità di partenza.
     */
    public static final double INITIAL_GRAVITY = 1.3;
    
    
    
    private final Option s;
    private final Animation animation;
    private final Item item;
    private final int timeFall;
    private final int jmp = 10;
    private final double gravity = INITIAL_GRAVITY;
    
    private double velY = INITIAL_GRAVITY;
   
    private int tileSynch;
    private int velRun = INITIAL_VEL;
    private int timeJump;
    private int counterJump;
    private int counterFall;
    
    private boolean inJump;
    private boolean canJump = true;
    private boolean inFall;
    private boolean verticalCollision;
    private boolean doubleJump;
    private boolean runGO;


    /**
     * 
     * L'oggetto prende in input l'altezza e la larghezza dell'immagine a
     * schermo, le sue cordinate e un BufferedImage che sarà l'immagine che si
     * andrà a disegnare sull'oggetto graphics passato al costruttore.
     * 
     * @param i
     *            have all the information of the images
     * @param x
     *            posizione sull'asse x
     * @param y
     *            posizione sull'asse y
     * @param state
     *            state of game
     */
    private MainPlayer(final double x, final double y, final GameState state, final Option stat, final Image... i) {
        super(x, y, state, i);
        s = stat;
        this.runGO = true;
        animation = new Animation(4, i);
        item = new Item();
        tileSynch = TYLESYNCHSTART;
        inFall = true;
        inJump = true;
        verticalCollision = false;
        timeJump = NJUMP;
        counterJump = 0;
        timeFall = NFALL;
        counterFall = 0;
    }

    /**
     * Create the player if doesn't exist. And return it.
     * 
     * @param x
     *            x position
     * @param y
     *            y position
     * @param state
     *            the state of game
     * @param stat
     *            option
     * @return the main player
     */
    public static MainPlayer getPlayer(final double x, final double y, final GameState state, final Option stat) {
        synchronized (MainPlayer.class) {
            if (player == null) {
                player = new MainPlayer(x, y, state, stat,
                        new Sprite(new SpriteSheet(new Texture("res/sayan60.png"), PLAYERWIDTH, PLAYERHEIGTH), 4, 1, 4)
                                .getFramesAsList());

            }
        }
        return player;
    }

    /**
     * 
     * Returns to the main player , it has not been initialized return null.
     * 
     * @return the main player , it has not been initialized return null.
     */
    public static MainPlayer getPlayer() {
        return player;
    }

    /**
     * Move the character.
     */
    public void tick() {
        if (!verticalCollision) {
            fall();
            super.incY(velY);
        }
        if (isRunning()) {
            goOn();
        }

        if (counterJump >= CHECKINJUMP) {
            jumping();
        }
        if (!inFall && counterJump == 0) {
            animation.run();
        }
        if (KeyInput.isPressed(s.getKeyJump())) {
            if (inFall && doubleJump) {
                counterJump = 0;
                doubleJump = false;
                canJump = true;
                timeJump = NDOUBLEJUMP;
                jump();
            } else {
                jump();
            }
        }
        if (KeyInput.isPressed(s.getKeyShoot())) {
            shoot();
        }

    }

    /**
     * Fa muovere l'immagine di Morpheus sull'asse orrizzontale.
     */
    private void goOn() {
        this.incX(velRun);
        tileSynch += velRun;
    }

    private void fall() {
        if (inFall) {
            if (counterFall < timeFall) {
                velY = gravity;
                counterFall++;
            } else {
                velY = gravity + GRAVITYPLUS;
            }
        }
        super.incY(velY);
    }

    /**
     * Set the fall a true.
     */
    public void falling() {
        inFall = true;
    }

    /**
     * Permette all'oggetto Morpheus di saltare.
     */
    private void jump() {
        if (canJump) {
            setInJump(true);
            decY(jmp);
            canJump = false;
            counterJump = 1;
            inFall = false;
        }
    }

    private void jumping() {
        decY(jmp);
        counterJump++;
        if (counterJump >= timeJump) {
            counterJump = 0;
            setInJump(false);
        }
    }

    /**
     * Set all the variable for simulating the ground collision.
     */
    public void groundCollission() {
        canJump = true;
        inFall = false;
        velY = 0;
        counterFall = 0;
        jumpPermission();
    }

    /**
     * Doesn't allow the player to jump.
     */
    public void stopJumping() {
        counterJump = -1;
        velY = 0;
    }

    /**
     * Allow the jump at the player.
     */
    public void jumpPermission() {
        counterJump = 0;
        doubleJump = true;
        timeJump = NJUMP;
    }

    /**
     * Returns the run velocity.
     * 
     * @return the run velocity
     */
    public int getVelRun() {
        return this.velRun;
    }

    /**
     * Create a bullet object and returns it.
     * 
     * @throws NoBulletException
     *             if the player try to shoot but he haven't bullet.
     * @return a bullet
     */
    public Bullet shoot() throws NoBulletException {
        if (item.getBullet() <= 0) {
            throw new NoBulletException();
        }
        return new Bullet(getX(), getY(), getState(),
                new Sprite(new SpriteSheet(new Texture("res/bullet.png"), BULLETSIZE), 2, 1, 2).getFramesAsList());
    }
    


    /**
     * Set the Y change value.
     * 
     * @param x
     *            the new valor
     */
    public void setVelY(final double x) {
        velY = x;
    }

    /**
     * Returns the Y change value.
     * 
     * @return the Y change value
     */
    public double getVelY() {
        return velY;
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
     * 
     * @return true if the characters is moving, else otherwise
     */
    public boolean isRunning() {
        return this.runGO;
    }


    /**
     * Death of the player, stop it in the current position.
     */
    public void death() {
        this.stopRun();
        this.stopJumping();
        this.setVelY(0);
    }

    /**
     * Set the run velocity.
     * 
     * @param vel
     *            the new velocity
     */
    public void setVelRun(final int vel) {
        this.velRun = vel;
    }

    @Override
    public void render(final Graphics2D g) {
        if (isRunning()) {
            animation.render(g, getX(), getY());
        } else {
            super.render(g);
        }
        if (Morpheus.DEBUG) {
            g.setColor(Color.BLACK);
            g.draw(this.getTop());
            g.setColor(Color.BLUE);
            g.draw(getBottom());
            g.setColor(Color.MAGENTA);
            g.draw(getLeft());
            g.setColor(Color.ORANGE);
            g.draw(getRight());
        }
        stopRun();

    }

    /**
     * Returns the player's item.
     * 
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
        return inJump;
    }

    /**
     * Set true if the player is in Jump, false otherwise.
     * 
     * @param inJump
     *            the inJump to set
     */
    public void setInJump(final boolean inJump) {
        this.inJump = inJump;
    }

    /**
     * Returns true if the player has vertical collision, false otherwise.
     * 
     * @return true if the player has vertical collision, false otherwise.
     */
    public boolean isVerticalCollision() {
        return verticalCollision;
    }

    /**
     * 
     * Set true if the player has vertical collision, false otherwise.
     * 
     * @param verticalCollision
     *            true if the player has vertical collision, false otherwise.
     */
    public void setVerticalCollision(final boolean verticalCollision) {
        this.verticalCollision = verticalCollision;
    }

    /**
     * Returns the bottom of the player.
     * 
     * @return the bottom of the player
     */
    public Rectangle getBottom() {
        return new Rectangle((int) getX() + OFFSETCOLLISION, (int) getY() + getHeight() - 3, getWidth() - 10, 1);
    }
}
