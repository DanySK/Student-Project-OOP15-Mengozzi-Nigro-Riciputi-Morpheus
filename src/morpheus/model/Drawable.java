package morpheus.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

/**
 * 
 * @author jacopo
 *
 */
public interface Drawable {
    /**
     * Logic implementation for the object move.
     */
    void tick();
    /**
     * Incrementa la posizione sull'asse X.
     * @param add
     *          Valore da aggiungere
     */

    /**
     * L'area sotto forma di rettangolo che delimita l'immagine.
     * @return
     *          L'area sotto forma di rettangolo che delimita l'immagine
     */
    Area getArea();
    
    /**
     * Un rettangolo che rappresenta il lato superiore.
     * @return
     *          Un rettangolo che rappresenta il lato superiore
     */
    Rectangle getUpperSide();
    
    /**
     * Un rettangolo che rappresenta il lato di sinistra.
     * @return
     *          Un rettangolo che rappresenta il lato di sinistra
     */
    Rectangle getLeftSide();
    
    /**
     * Un rettangolo che rappresenta il lato di destra.
     * @return
     *          Un rettangolo che rappresenta il lato di destra
     */
    Rectangle getRightSide();
    
    /**
     * Un rettangolo che rappresenta il lato inferiore.
     * @return
     *          Un rettangolo che rappresenta il lato inferiore
     */
    Rectangle getLowerSide();
    
    /**
     * Disegna l'immagine sull'elemento Graphics.
     * @param g
     * 		the graphics object
     */
    void render(final Graphics2D g);
    
    /**
     * Setter della posizione sull'asse X dell'immagine.
     * @param x
     *          posizione sull'asse X
     */
    void setX(final double x);
    
    /**
     * Setter della posizione sull'asse Y dell'immagine.
     * @param y
     *          posizione sull'asse Y
     */
    void setY(final double y);
    
    
    
    /**
     * 
     * @return
     *          La posizione sull'asse X
     */
    double getX();
    
    /**
     * 
     * @return
     *          La posizione sull'asse Y
     */
    double getY();
    
    /**
     * 
     * @return
     *          Il BufferedImage relativo a Morpheus
     */
    BufferedImage getMainImage();
    
    /**
     * 
     * @return
     *          L'altezza dell'immagine
     */
    int getHeight();
    
    /**
     * 
     * @return
     *          La larghezza dell'immagine
     */
    int getWidth();
    
  
}
