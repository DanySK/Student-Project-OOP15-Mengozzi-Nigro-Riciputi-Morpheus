package morpheus.model;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

/**
 * 
 * @author jacopo
 *
 */
public class Option extends Storable {

    /**
     * 
     */
    private static final long serialVersionUID = 3751161991209059959L;

    private static final String FILE_NAME = "res/Option.dat";
    private static final transient int DEFAULT_JUMP_KEY = KeyEvent.VK_W;
    private static final transient int DEFAULT_SHOOT_KEY = KeyEvent.VK_SPACE;
    private final boolean firstOpen;

    private int jumpKey;
    private int shootKey;
    private boolean blueMorpheus;
    private boolean redMorpheus;

    /**
     * Legge le Statistiche dal file predefinito e le carica in memoria.
     */
    public Option() {
        super(FILE_NAME);
        boolean app = false;
        try {
            final Option stat = (Option) readObject();
            blueMorpheus = stat.isBlueMorpheusOpen();
            redMorpheus = stat.isRedMorpheusOpen();
            shootKey = stat.getKeyShoot();
            jumpKey = stat.getKeyJump();
        } catch (IOException e) {
            new File(FILE_NAME);
            app = true;
            blueMorpheus = false;
            redMorpheus = false;
            jumpKey = KeyEvent.VK_W;
            shootKey = KeyEvent.VK_SPACE;
        }
        firstOpen = app;
    }

    /**
     * Dice se il pesonaggio BlueMorpheus è sbloccato.
     * 
     * @return True -> Sbloccato; False -> Bloccato;
     */
    public boolean isBlueMorpheusOpen() {
        return this.blueMorpheus;
    }

    /**
     * Dice se il pesonaggio RedMorpheus è sbloccato.
     * 
     * @return True -> Sbloccato; False -> Bloccato;
     */
    public boolean isRedMorpheusOpen() {
        return this.redMorpheus;
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
     * @return the defaultdownkey
     */
    public static int getDefaultShootKey() {
        return DEFAULT_SHOOT_KEY;
    }

    /**
     * @return the defaultjumpkey
     */
    public static int getDefaultJumpKey() {
        return DEFAULT_JUMP_KEY;
    }

}
