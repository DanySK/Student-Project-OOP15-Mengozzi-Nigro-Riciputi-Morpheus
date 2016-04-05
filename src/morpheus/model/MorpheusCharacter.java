package morpheus.model;

import java.awt.Graphics2D;

import morpheus.view.Texture;
/**
 * 
 * @author jacopo
 *
 */
public class MorpheusCharacter extends Drawable {
    /**
     * velocità iniziale.
     */
    public static final int INITIAL_VEL = 7;
    /**
     * velocità iniziale volo.
     */
    public static final int INITIAL_VEL_FLY = 5;
    /**
     * Gravità di partenza.
     */
    public static final int INITIAL_GRAVITY = 1;
    private volatile boolean run;
    private int velRun = INITIAL_VEL;
    private int velFly = INITIAL_VEL_FLY;
    private int vel = 100;
    private int jmp = 10;
    private int gravity = INITIAL_GRAVITY;

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
    public MorpheusCharacter(final Texture t, final int x, final int y, final Graphics2D g) {
        super(t, x, y, g);
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
        this.decY(gravity);
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
    
    /**
     * Permette all'oggetto Morpheus di saltare.
     */
    public void jump() {
        this.incX(jmp);
    }  
    
}
