package morpheus.model;

/**
 * 
 * @author jacopo
 *
 */
public class PlayerAnimation extends Animation {

    /**
     * Player Animation.
     * 
     * @param speed
     *            animation speed
     * @param frames
     *            animation's images
     */
    public PlayerAnimation(final int speed, final Image... frames) {
        super(speed, frames);
        setNumFrames(4);
    }

    /**
     * Set the fall's animation.
     */
    public void fall() {
        setCurrentFrame(getFrames()[4]);
    }
    
    public void hit() {
        setCurrentFrame(getFrames()[5]);
    }
}