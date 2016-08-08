package morpheus.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.sun.org.apache.xpath.internal.compiler.Keywords;

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
    /**
     * velocità iniziale.
     */
    public static final int INITIAL_VEL = 5;

    /**
     * Gravità di partenza.
     */
    public static final double INITIAL_GRAVITY = 1.3;
    /**
     * Per animazione morte.
     */
    public static final int SLOWLY_DEATH = 4;
    /**
     * Pavimento.
     */
    public static final int FLOOR = 40;

    private static final int BULLETSIZE = 24;
    private volatile boolean runGO;

    private int jmp = 10;
    private double gravity = INITIAL_GRAVITY;
    private final Statistics s;
    private Status status;
    private final ModelAnimation animation;
    private final Item item;
    private int tileSynch;
    private boolean inFall;
    private boolean verticalCollision;
    private boolean doubleJump;
    private int velRun = INITIAL_VEL;
    private boolean canJump = true;
    private double velY = INITIAL_GRAVITY;
    private boolean inJump;
    private int timeJump;
    private int counterJump;
    private final int timeFall;
    private int counterFall;
    
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
    private MainPlayer(final double x, final double y, final GameState state, final Image... i) {
        super(x, y, state, i);
        s = new Statistics();
        status = Status.RUN;
        this.runGO = true;
        animation = new ModelAnimation(4, i);
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
     * @return the main player
     */
    public static MainPlayer getPlayer(final double x, final double y, final GameState state) {

        synchronized (MainPlayer.class) {
            if (player == null) {
                player = new MainPlayer(x, y, state,
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

        if (status == Status.DEATH) {
            death();
        } else {
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
            if (KeyInput.isPressed(KeyEvent.VK_SPACE)) {
                
                shoot();
            }

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
     * Set the high of the jump.
     * 
     * @param x
     *            the new valor of jump
     */
    public void setJump(final int x) {
        this.jmp = x;
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
     * Set the gravity.
     * 
     * @param x
     *            the new valor
     */
    public void setGravity(final int x) {
        this.gravity = x;
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
     * Set the field status.
     * 
     * @param s
     *            new value of status
     */
    public void setStatus(final Status s) {
        status = s;
    }

    /**
     * Return the character's status.
     * 
     * @return the character's status
     * 
     */
    public Status getStatus() {
        return status;
    }

    private void death() {
        if (getY() == FLOOR) {
            velRun = 0;
        } else {
            incY(SLOWLY_DEATH);
        }
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
        if (status.equals(Status.RUN) && isRunning()) {
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

    public Rectangle getBottom() {
        return new Rectangle((int) getX() + 5, (int) getY() + getHeight() - 3, getWidth() - 10, 1);
    }

    /**
     * 
     * @author jacopo
     *
     */
    public enum Status {
        /**
         * 
         */
        FLY, RUN, DEATH;
    }

}
