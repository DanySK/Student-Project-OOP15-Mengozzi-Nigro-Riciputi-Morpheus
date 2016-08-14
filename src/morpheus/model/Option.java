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
    public static final transient int DEFAULT_JUMP_KEY = KeyEvent.VK_W;
    public static final transient int DEFAULT_SHOOT_KEY = KeyEvent.VK_SPACE;
    public static final transient double DEFAULT_VOLUME = 0.75;
    private final boolean firstOpen;

    private int jumpKey;
    private int shootKey;
    private double volume;
    private boolean mainPlayer;
    private boolean sidePlayer;

    /**
     * Legge le Statistiche dal file predefinito e le carica in memoria.
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
    
    public static Option getOption() {
        synchronized (Option.class) {
            if (statistics == null) {
                statistics = new Option();
            }
        }
        return statistics;
    }

    /**
     * Dice se il personaggio MainPlayer è selezionato.
     * 
     * @return True -> Selezionato; False -> Bloccato;
     */
    public boolean isMainPlayerOpen() {
        return this.mainPlayer;
    }
    
    /**
     * Set the player selection.
     * @param status
     *          true this is the selected player, false otherwise.
     */
    public void setMainPlayerOpening(final boolean status) {
        this.mainPlayer = status;
    }

    /**
     * Dice se il personaggio SidePlayer è selezionato.
     * 
     * @return True -> Selezionato; False -> Bloccato;
     */
    public boolean isSidePlayerOpen() {
        return this.sidePlayer;
    }
    
    /**
     * Set the player selection.
     * @param status
     *          true this is the selected player, false otherwise.
     */
    public void setSidePlayerOpening(final boolean status) {
        this.sidePlayer = status;
    }

    /**
     * Setta il tasto per alzarsi.
     * 
     * @param key
     *            Valore del tasto da settare in formato intero
     */
    public void setKeyJump(final int key) {
        jumpKey = key;
    }

    /**
     * Setta il tasto per abbassarsi.
     * 
     * @param key
     *            Valore del tasto da settare in formato intero
     */
    public void setKeyShoot(final int key) {
        shootKey = key;
    }

    /**
     * 
     * @return il valore in formato stringa del tasto per alzarsi
     */
    public int getKeyJump() {
        return jumpKey;
    }

    /**
     * @return il valore in formato stringa del tasto per abbassarsi
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
     * @return the volume
     */
    public double getVolume() {
        return volume;
    }

    /**
     * @param volume the volume to set
     */
    public void setVolume(final double volume) {
        this.volume = volume;
    }

}
