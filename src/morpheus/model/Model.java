package morpheus.model;

import java.awt.geom.Area;
import java.awt.image.BufferedImage;
/**
 * 
 * @author jacopo
 *
 */
public interface Model {
    /**
     * Carica l'immagine.
     * 
     * @param file
     *          prende come parametro il persorso del file da caricare
     * @return
     *          un BufferedImage con l'immagine passata
     */
     BufferedImage loader(final String file);
    /**
     * Setta il tasto per alzarsi.
     */
    void setKeyJump();
    /**
     * Setta il tasto per abbassarsi.
     */
    void setKeyDown();
    
    /**
     * 
     * @return
     *          il valore in formato stringa del tasto per alzarsi
     */
    String getKeyJump();
    /**
     * @return          
     *          il valore in formato stringa del tasto per abbassarsi
     */
    String getKeyDown();
    
    /**
     * Questo metodo sostitisce il metodo intersects già presente nelle classi che implementano Shape
     * Può essere usato perchè leggermente più comodo.
     * @param a
     *          area primo oggetto
     * @param b
     *          area secondo oggetto
     * @return
     *          true se collidono , false altrimenti
     */
    boolean intersects(final Area a, final Area b);
}
