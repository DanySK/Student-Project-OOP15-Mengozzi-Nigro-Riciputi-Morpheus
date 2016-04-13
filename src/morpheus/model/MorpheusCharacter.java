package morpheus.model;

import java.awt.Graphics2D;

import morpheus.controller.KeyInput;
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
    /**
     * Per animazione morte.
     */
    public static final int SLOWLY_DEATH = 4;
    /**
     * Pavimento.
     */
    public static final int FLOOR = 40;
    private volatile boolean run;
    private int velRun = INITIAL_VEL;
    private int velFly = INITIAL_VEL_FLY;
    private int vel = 100;
    private int jmp = 10;
    private int gravity = INITIAL_GRAVITY;
    private final Statistics s;
    private Status status;

    /**
     * 
     * L'oggetto prende in input l'altezza e la larghezza dell'immagine a schermo, le sue cordinate 
     * e un BufferedImage che sarà l'immagine che si andrà a disegnare sull'oggetto graphics passato al costruttore.
     * @param t
     *          have all the information of the image 
     * @param x
     *          posizione sull'asse x
     * @param y
     *          posizione sull'asse y
     * @param g
     *          Elemento graphics su cui si andrà a disegnare l'immagine
     */
    public MorpheusCharacter(final Texture t, final int x, final int y, final Graphics2D g) {
        super(t, x, y, g);
        s = new Statistics();
        status = Status.FLY;
        this.run = true;
    }
    
    /**
     * Move the character.
     */
    public void tick() {
        if (status == Status.DEATH) {
            death();
        } else {
            if (KeyInput.isDown(s.getKeyJump())) {
                if (status == Status.FLY) {
                    goUp();
                } else {
                    jump();
                }
            }
        }
        
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
     * Permette all'oggetto Morpheus di alzarsi.
     * 
     */
    public void goUp() {
        this.incY(velFly);
        
    }
    
    /**
     * Permette all'oggetto Morpheus di abbassarsi.
     */
    public void goDown() {
        this.decY(velFly);
    }
    
    /**
     * Permette all'oggetto Morpheus di saltare.
     */
    public void jump() {
        this.incY(jmp);
    }  
    
    /**
     * Set the run on true, so the character start his running.
     */
    public void startRun() {
        this.run = true;
    }
    
    /**
     * Set the run on false, so stop the character's run.
     */
    public void stopRun() {
        this.run = false;
    }
    
    /**
     * Set the field status.
     * @param s
     *          new value of status
     */
    public void setStatus(final Status s) {
        status = s;
    }
    
    /**
     * Return the character's status.
     * @return
     *          the character's status
     *  
     */
    public Status getStatus() {
        return status;
    }
    
    /**
     * Fa muovere l'immagine di Morpheus sull'asse orrizzontale.
     */
    private void goOn() {
        this.incX(velRun);
        this.decY(gravity);
    }
    
    private void death() {
        if (getY() == FLOOR) { 
            velRun = 0;
        } else {
            incY(SLOWLY_DEATH);
        }
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
