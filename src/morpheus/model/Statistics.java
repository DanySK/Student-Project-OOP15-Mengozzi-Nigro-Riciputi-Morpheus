package morpheus.model;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

/**
 * 
 * @author jacopo
 *
 */
public class Statistics extends Storable {

        
    /**
     * 
     */
    private static final long serialVersionUID = 3751161991209059959L;
    private static final String FILE_NAME = "res/Statistics.dat"; 
    private int coin;
    private int jumpKey;
    private int downKey;
    private boolean blueMorpheus;
    private boolean redMorpheus;
    /**
     * Legge statistiche e la carica in memoria.
     * 
     * @param fileName
     *            path del file
     * 
     */
    public Statistics(final String fileName) {
            super(fileName);
            try {
                if (readObject() == null) {
                    coin = 0;
                    blueMorpheus = false;
                    redMorpheus = false;
                } else {
                    final Statistics stat = (Statistics) readObject();
                    coin = stat.getCoins();
                    blueMorpheus = stat.isBlueMorpheusOpen();
                    redMorpheus = stat.isRedMorpheusOpen();
                    downKey = stat.getKeyDown();
                    jumpKey = stat.getKeyJump();
                }
            
            } catch (IOException e) {
                new File(FILE_NAME);
                coin = 0;
                blueMorpheus = false;
                redMorpheus = false;
                jumpKey = KeyEvent.VK_W;
                downKey = KeyEvent.VK_S;
            }
    }
    
    /**
     * Legge le Statistiche dal file predefinito e le carica in memoria.
     */
    public Statistics() {
        super(FILE_NAME);
        try {
            if (readObject() == null) {
                coin = 0;
                blueMorpheus = false;
                redMorpheus = false;
            } else {
                final Statistics stat = (Statistics) readObject();
                coin = stat.getCoins();
                blueMorpheus = stat.isBlueMorpheusOpen();
                redMorpheus = stat.isRedMorpheusOpen();
                downKey = stat.getKeyDown();
                jumpKey = stat.getKeyJump();
            }
        } catch (IOException e) {
           new File(FILE_NAME);
           coin = 0;
           blueMorpheus = false;
           redMorpheus = false;
           jumpKey = KeyEvent.VK_W;
           downKey = KeyEvent.VK_S;
           
        }
    }
    
    /**
     * Increase the value of coin of one.
     */
    public void incCoins() {
        coin++;
    }
    
    /**
     * Increase the value of coin of x.
     * @param x
     *          value to add
     */
    public void incCoins(final int x) {
        coin += x;
    }
    /**
     * Il totale dei soldi a disposizione del giocatore.
     * @return
     *          Il totale dei soldi a disposizione del giocatore.
     */
    public int getCoins() {
        return this.coin;
    }
    
    /**
     * Dice se il pesonaggio BlueMorpheus è sbloccato.
     * @return
     *          True -> Sbloccato;
     *          False -> Bloccato;
     */
    public boolean isBlueMorpheusOpen() {
        return this.blueMorpheus;
    }
    
    /**
     * Dice se il pesonaggio RedMorpheus è sbloccato.
     * @return
     *          True -> Sbloccato;
     *          False -> Bloccato;
     */
    public boolean isRedMorpheusOpen() {
        return this.redMorpheus;
    }
    
    /**
     * Setta il tasto per alzarsi.
     * 
     * @param key
     *          Valore del tasto da settare in formato intero
     */
    public void setKeyJump(final int key) {
        jumpKey = key;
    }
    /**
     * Setta il tasto per abbassarsi.
     * 
     * @param key
     *          Valore del tasto da settare in formato intero
     */
    public void setKeyDown(final int key) {
        downKey = key;
    }
    
    /**
     * 
     * @return
     *          il valore in formato stringa del tasto per alzarsi
     */
    public int getKeyJump() {
        return jumpKey;
    }
    /**
     * @return          
     *          il valore in formato stringa del tasto per abbassarsi
     */
    public int getKeyDown() {
        return downKey;
    }
   
}
