package morpheus.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

import morpheus.view.Texture;
/**
 * 
 * @author jacopo
 *
 */
public class Drawable extends Thread {
   
    private final Texture texture;
    private int x;
    private int y;
    
    private Graphics2D g;
    //aggiungere metodo render e tick 
    /**
     * 
     * L'oggetto prende in input l'altezza e la larghezza dell'immagine a schermo, le sue cordinate 
     * e un BufferedImage che sarà l'immagine che si andrà a disegnare sull'oggetto graphics passato al costruttore.
     * 
     * @param t
     *          Texture, with the BufferdImage, the width and the height
     * @param x
     *          posizione sull'asse x
     * @param y
     *          posizione sull'asse y
     * @param g
     *          Elemento graphics su cui si andrà a disegnare l'immagine
     */
    public Drawable(final Texture t, final int x, final int y, final Graphics2D g) {
        texture = t;
        this.x = x;
        this.y = y;
        this.g = g;
        
        this.g.drawImage(this.texture.getImage(), this.x, this.y, texture.getWidth(), this.getHeight(), null);
    }
    
    /**
     * Incrementa la posizione sull'asse X.
     * @param add
     *          Valore da aggiungere
     */
    protected void incX(final double add) {
        this.x += add;
    }
    
    /**
     * Incrementa la posizione sull'asse Y.
     * @param add
     *          Valore da aggiungere
     */
    protected void incY(final int add) {
        this.y += add;
    }
    
    /**
     * Decrementa la posizione sull'asse X.
     * @param rem
     *          Valore da togliere
     */
    protected void decX(final int rem) {
        this.x -= rem;
    }
    
    /**
     * Decrementa la posizione sull'asse Y.
     * @param rem
     *          Valore da togliere
     */
    protected void decY(final int rem) {
        this.y -= rem;
    }
    
    /**
     * L'area sotto forma di rettangolo che delimita l'immagine.
     * @return
     *          L'area sotto forma di rettangolo che delimita l'immagine
     */
    public Area getArea() {
        return new Area(new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight()));
    }
    
    /**
     * Un rettangolo che rappresenta il lato superiore.
     * @return
     *          Un rettangolo che rappresenta il lato superiore
     */
    public Rectangle getUpperSide() {
        return new Rectangle(this.getX(), this.getY() - this.getHeight(), this.getWidth(), 1);
    }
    
    /**
     * Un rettangolo che rappresenta il lato di sinistra.
     * @return
     *          Un rettangolo che rappresenta il lato di sinistra
     */
    public Rectangle getLeftSide() {
        return new Rectangle(this.getX(), this.getY(), 1, this.getHeight());
    }
    
    /**
     * Un rettangolo che rappresenta il lato di destra.
     * @return
     *          Un rettangolo che rappresenta il lato di destra
     */
    public Rectangle getRightSide() {
        return new Rectangle(this.getX() + this.getWidth(), this.getY(), 1, this.getHeight());
    }
    
    /**
     * Un rettangolo che rappresenta il lato inferiore.
     * @return
     *          Un rettangolo che rappresenta il lato inferiore
     */
    public Rectangle getLowerSide() {
        return new Rectangle(this.getX(), this.getY(), this.getHeight(), 1);
    }
    
    /**
     * Disegna l'immagine sull'elemento Graphics.
     */
    public void render() {
        texture.render(g, (double) x, (double) y);
    }
    
    /**
     * Setter della posizione sull'asse X dell'immagine.
     * @param x
     *          posizione sull'asse X
     */
    public void setX(final int x) {
        this.x = x;
    }
    
    /**
     * Setter della posizione sull'asse Y dell'immagine.
     * @param y
     *          posizione sull'asse Y
     */
    public void setY(final int y) {
        this.y = y;
    }
    /**
     * Setter del BufferedImage che rapressenta l'immagine.
     * @param image
     *          immagine rappresentativa di Morpheus
     */
    public void setImage(final BufferedImage image) {
        //da implementare
    }
    
    /**
     * 
     * @return
     *          La posizione sull'asse X
     */
    public int getX() {
        return this.x;
    }
    
    /**
     * 
     * @return
     *          La posizione sull'asse Y
     */
    public int getY() {
        return this.y;
    }
    
    /**
     * 
     * @return
     *          Il BufferedImage relativo a Morpheus
     */
    public BufferedImage getImage() {
        return texture.getImage();
    }
    
    /**
     * 
     * @return
     *          L'altezza dell'immagine
     */
    public int getHeight() {
        return texture.getHeight();
    }
    
    /**
     * 
     * @return
     *          La larghezza dell'immagine
     */
    public int getWidth() {
        return texture.getWidth();
    }
    
    /**
     * Cambia l'elemento Graphics su cui disegnare l'immagine.
     * @param g
     *          il nuovo Graphics
     */
    public void setGraphics(final Graphics2D g) {
        this.g = g;
    }
    
    /**
     * Ritorna l'elemento Graphics su cui si sta disegnando.
     * @return
     *          L'elemento Graphics su cui si sta disegnando
     */
    public Graphics2D getGraphics() {
        return this.g;
    }

    @Override
    public void run() {
    }
}
