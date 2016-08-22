package morpheus.model;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

/**
 * 
 * @author jacopo
 *
 */
public final class Option extends Storable {

    private static Option statistics;
    /**
     * 
     */
    private static final long serialVersionUID = 3751161991209059959L;

    private static final String FILE_NAME = "res/Option.dat";
    /**
     * Default Jump Key.
     */
    public static final transient int DEFAULT_JUMP_KEY = KeyEvent.VK_W;
    /**
     * Default Shoot Key.
     */
    public static final transient int DEFAULT_SHOOT_KEY = KeyEvent.VK_SPACE;
    /**
     * Default volume.
     */
    public static final transient double DEFAULT_VOLUME = 0.75;

    private final boolean firstOpen;

    private int jumpKey;
    private int shootKey;
    private double volume;
    private boolean mainPlayer;
    private boolean sidePlayer;

    /**
     * Reads Option to the selected file and load it in memory. If the file
     * doesn't exist it will create a new File.
     */
    private Option() {
        super(FILE_NAME);
        boolean app = false;
        try {
            final Option stat = (Option) readObject();
            mainPlayer = stat.isMainPlayerOpen();
            sidePlayer = stat.isSidePlayerOpen();
            shootKey = stat.getKeyShoot();
            jumpKey = stat.getKeyJump();
            volume = stat.getVolume();
        } catch (IOException e) {
            new File(FILE_NAME);
            app = true;
            mainPlayer = false;
            sidePlayer = false;
            jumpKey = KeyEvent.VK_W;
            shootKey = KeyEvent.VK_SPACE;
            volume = DEFAULT_VOLUME;
        }
        firstOpen = app;
    }

    /**
     * Returns the Option object.
     * 
     * @return the Option object
     */
    public static Option getOption() {
        synchronized (Option.class) {
            if (statistics == null) {
                statistics = new Option();
            }
        }
        return statistics;
    }

    /**
     * Returns true if the player is selected, false otherwise.
     * 
     * @return true if the player is selected, false otherwise.
     */
    public boolean isMainPlayerOpen() {
        return this.mainPlayer;
    }

    /**
     * Set the player selection.
     * 
     * @param status
     *            true this is the selected player, false otherwise.
     */
    public void setMainPlayerOpening(final boolean status) {
        this.mainPlayer = status;
    }

    /**
     * Returns true if the player is selected, false otherwise.
     * 
     * @return true if the player is selected, false otherwise.
     */
    public boolean isSidePlayerOpen() {
        return this.sidePlayer;
    }

    /**
     * Set the player selection.
     * 
     * @param status
     *            true this is the selected player, false otherwise.
     */
    public void setSidePlayerOpening(final boolean status) {
        this.sidePlayer = status;
    }

    /**
     * Sets the key to jump.
     * 
     * @param key
     *            key int value
     */
    public void setKeyJump(final int key) {
        jumpKey = key;
    }

    /**
     * Sets the key to shoot.
     * 
     * @param key
     *            key int value
     */
    public void setKeyShoot(final int key) {
        shootKey = key;
    }

    /**
     * Returns int value of the jump key.
     * 
     * @return int value of the jump key.
     */
    public int getKeyJump() {
        return jumpKey;
    }

    /**
     * Returns int value of the shoot key.
     * 
     * @return int value of the shoot key.
     */
    public int getKeyShoot() {
        return shootKey;
    }

    /**
     * Save the file.
     */
    public void close() {
        this.writeObject(this);
    }

    /**
     * @return the firstOpen
     */
    public boolean isFirstOpen() {
        return firstOpen;
    }

    /**
     * Returns the volume.
     * 
     * @return the volume
     */
    public double getVolume() {
        return volume;
    }

    /**
     * Sets the volume.
     * 
     * @param volume
     *            new value for volume
     */
    public void setVolume(final double volume) {
        this.volume = volume;
    }

}
