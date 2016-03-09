package gioco.Model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
/**
 * 
 * @author jacopo
 *
 */
public class Morpheus extends Drawable {
    /**
     * velocità iniziale.
     */
    public static final int INITIAL_VEL = 5;
    
    private volatile boolean run;
    private int velRun = INITIAL_VEL;
    private int velFly = 10;
    private int vel = 100;

    /**
     * 
     * L'oggetto prende in input l'altezza e la larghezza dell'immagine a schermo, le sue cordinate 
     * e un BufferedImage che sarà l'immagine che si andrà a disegnare sull'oggetto graphics passato al costruttore.
     * @param height
     *          altezza
     * @param width
     *          larghezza
     * @param x
     *          posizione sull'asse x
     * @param y
     *          posizione sull'asse y
     * @param image
     *          Immagine di riferimento
     * @param g
     *          Elemento graphics su cui si andrà a disegnare l'immagine
     */
    public Morpheus(final int height, final int width, final int x, final int y, final BufferedImage image, final Graphics g) {
        super(height, width, x, y, image, g);
        this.run = true;
    }
    
    /**
     * 
     */
    public void run() {
        while (run) {
            goOn();
            try {
                Thread.sleep(vel);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Fa muovere l'immagine di Morpheus sull'asse orrizzontale.
     */
    private void goOn() {
        this.incX(velRun);
    }

    /**
     * Permette all'oggetto Morpheus di alzarsi.
     * 
     */
    public void goUp() {
        this.incY(velFly);
    }
    
    /**
     * Permette all'oggetto Morpheus di abbssarsi.
     */
    public void goDown() {
        this.decY(velFly);
    }

    
    
}
