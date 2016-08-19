package morpheus.model;

/**
 * 
 * @author jacopo
 *
 */
public class PlayerManager {

    private static final int NJUMP = 11;
    private static final double JUMPVALUE = 10;
    private static final int NFALL = 55;
    private static final double GRAVITYPLUS = 0.6;
    private static final int NDOUBLEJUMP = 10;
    private static final double GRAVITY = 1.4;
    private static final int KNOCKSIZE = 120;
    /**
     * For checking if the player is in Jump.
     */
    public static final int CHECKINJUMP = 1;
    
    
    private final int timeFall;
    private final PlayerAnimation animation;
    
    private double velY = GRAVITY;
    
    private int timeJump;
    private int counterJump;
    private int counterFall;
    private int knock;
    
    private boolean check;
    private boolean inJump;
    private boolean canJump = true;
    private boolean inFall;
    private boolean verticalCollision;
    private boolean doubleJump;
    
    /**
     * This class handles the fall and the player's jump.
     * @param anime
     *          player animation
     */
    public PlayerManager(final PlayerAnimation anime) {
        animation = anime;
        inFall = true;
        inJump = true;
        verticalCollision = false;
        timeJump = NJUMP;
        counterJump = 0;
        timeFall = NFALL;
        counterFall = 0;
    }
    
    /**
     * Player fall.
     */
    public void fall() {
        if (inFall) {
            if (counterFall < timeFall) {
                velY = GRAVITY;
                counterFall++;
            } else {
                animation.fall();
                velY = GRAVITY + GRAVITYPLUS;
            }
        }
    }
    
    /**
     * Player initial jump.
     * @return          
     *          jump value
     */
    public double jump() {
        if (canJump) {
            setInJump(true);
            canJump = false;
            counterJump = 1;
            inFall = false;
            return JUMPVALUE;
        }
        return 0;
    }
    
    /**
     * Player jump.
     * @return
     *          jump value
     */
    public double jumping() {
        counterJump++;
        if (counterJump >= timeJump) {
            counterJump = 0;
            setInJump(false);
        }
        return JUMPVALUE;
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
     * Allow the jump at the player.
     */
    public void jumpPermission() {
        counterJump = 0;
        setDoubleJump(true);
        timeJump = NJUMP;
    }
    
    /**
     * Doesn't allow the player to jump.
     */
    public void stopJumping() {
        counterJump = -1;
        velY = 0;
    }
    
    /**
     * Setting all variable for a second jump.
     */
    public void setForDoubleJump() {
        counterJump = 0;
        doubleJump = false;
        canJump = true;
        timeJump = NDOUBLEJUMP;
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
     * @return the doubleJump
     */
    public boolean isDoubleJump() {
        return doubleJump;
    }

    /**
     * Set the doubleJump.
     * @param doubleJump 
     *          value, true if can doubleJump, false otherwise
     */
    public void setDoubleJump(final boolean doubleJump) {
        this.doubleJump = doubleJump;
    }
    
    /**
     * Returns the counter for the jump.
     * @return
     *          the counter for the jump
     */
    public int getCounterJump() {
        return counterJump;
    }
    
    /**
     * Set if the player is in fall or not.
     * @param fall
     *          true is in fall, false isn't in fall
     */
    public void setInFall(final boolean fall) {
        inFall = fall;
    }
    
    /**
     * Reset of the object.
     */
    public void reset() {
        inFall = true;
        inJump = true;
        verticalCollision = false;
        timeJump = NJUMP;
        counterJump = 0;
        counterFall = 0;
        knock = 0;
    }
    
    /**
     * Returns if the player is in fall.
     * @return
     *          true is in fall, false isn't in fall
     */
    public boolean isInFall() {
        return inFall;
    }
    
    /**
     * Death.
     */
    public void dead() {
        this.stopJumping();
        this.setVelY(0);
    }
    
    /**
     * Returns true if the champion have a collision, false otherwise.
     * @return
     *          true if the champion have a collision, false otherwise.
     */
    public boolean isKnocking() {
        return knock > 0 ? true : false;
    }
    
    /**
     * Animation when the player is safe.
     */
    public void knocking() {
        knock++;
        if (knock % 10 == 0) {
            check = !check;
        }
        if (check) {
            if (isInFall()) {
                animation.fall();
            } else {
                animation.run();
            }
        } else {
            animation.hit();
        }
        if (knock == KNOCKSIZE) {
            knock = 0;
        }
    }
    
    /**
     * Player hit.
     */
    public void hit() {
        knock++;
        animation.hit();
    }
}
