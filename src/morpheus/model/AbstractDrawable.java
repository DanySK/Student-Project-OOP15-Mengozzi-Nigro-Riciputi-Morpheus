package morpheus.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import morpheus.view.GameState;

/**
 * 
 * @author jacopo
 *
 */

public abstract class AbstractDrawable implements Drawable {

	private final Image[] image;
	private final Image mainFrame;
	private double x;
	private double y;
	private GameState state;
	

	/**
	 * 
	 * L'oggetto prende in input l'altezza e la larghezza dell'immagine a
	 * schermo, le sue cordinate e un BufferedImage che sarà l'immagine che si
	 * andrà a disegnare sull'oggetto graphics passato al costruttore.
	 * 
	 * @param i
	 *            Images relative to the object, with height and width
	 * @param x
	 *            posizione sull'asse x
	 * @param y
	 *            posizione sull'asse y
	 * @param game
	 * 			  state of game
	 */
	public AbstractDrawable(final double x, final double y, final GameState game, final Image... i) {
		this.x = x;
		this.y = y;
		this.state = game;
		this.state.addEntity(this);
		image = Arrays.copyOf(i, i.length);
		mainFrame = image[0];

	}

	/**
	 * 
	 * @param x
	 * 			posizione sull'asse x
	 * @param y
	 * 			posizione sull'asse y
	 * @param game
	 * 			state del gioco
	 * @param i
	 * 			immagine, con altezza e larghezza
	 */
	public AbstractDrawable(final double x, final double y, final GameState game, final Image i) {
		image = null;
		this.x = x;
		this.y = y;
		this.state = game;
		this.state.addEntity(this);
		mainFrame = i;

	}

	/**
	 * Logic implementation for the object move.
	 */
	public abstract void tick();

	/**
	 * Incrementa la posizione sull'asse X.
	 * 
	 * @param add
	 *            Valore da aggiungere
	 */
	protected void incX(final double add) {
		this.x += add;
	}

	/**
	 * Incrementa la posizione sull'asse Y.
	 * 
	 * @param add
	 *            Valore da aggiungere
	 */
	protected void incY(final double add) {
		this.y += add;
	}

	/**
	 * Decrementa la posizione sull'asse X.
	 * 
	 * @param rem
	 *            Valore da togliere
	 */
	protected void decX(final double rem) {
		this.x -= rem;
	}

	/**
	 * Decrementa la posizione sull'asse Y.
	 * 
	 * @param rem
	 *            Valore da togliere
	 */
	protected void decY(final double rem) {
		this.y -= rem;
	}

	/**
	 * L'area sotto forma di rettangolo che delimita l'immagine.
	 * 
	 * @return L'area sotto forma di rettangolo che delimita l'immagine
	 */
	public Rectangle getBounds() {
		return new Rectangle((int) this.getX(), (int) this.getY(), this.getWidth(), this.getHeight());
		
	}

	/**
	 * Un rettangolo che rappresenta il lato superiore.
	 * 
	 * @return Un rettangolo che rappresenta il lato superiore
	 */
	public Rectangle getUpperSide() {
		return new Rectangle((int) this.getX(), (int) this.getY() - this.getHeight(), this.getWidth(), 1);
	}

	/**
	 * Un rettangolo che rappresenta il lato di sinistra.
	 * 
	 * @return Un rettangolo che rappresenta il lato di sinistra
	 */
	public Rectangle getLeftSide() {
		return new Rectangle((int) this.getX(), (int) this.getY(), 1, this.getHeight());
	}

	/**
	 * Un rettangolo che rappresenta il lato di destra.
	 * 
	 * @return Un rettangolo che rappresenta il lato di destra
	 */
	public Rectangle getRightSide() {
		return new Rectangle((int) this.getX() + this.getWidth(), (int) this.getY(), 1, this.getHeight());
	}

	/**
	 * Un rettangolo che rappresenta il lato inferiore.
	 * 
	 * @return Un rettangolo che rappresenta il lato inferiore
	 */
	public Rectangle getLowerSide() {
		return new Rectangle((int) this.getX(), (int) this.getY(), this.getHeight(), 1);
	}

	/**
	 * Take in input a graphics element and draw the image in the window.
	 * @param g
	 * 		a graphic elemetn
	 */
	public void render(final Graphics2D g) {
		g.drawImage(mainFrame.getImage(), (int) x, (int) y, null);
	}

	/**
	 * Setter della posizione sull'asse X dell'immagine.
	 * 
	 * @param x
	 *            posizione sull'asse X
	 */
	public void setX(final double x) {
		this.x = x;
	}

	/**
	 * Setter della posizione sull'asse Y dell'immagine.
	 * 
	 * @param y
	 *            posizione sull'asse Y
	 */
	public void setY(final double y) {
		this.y = y;
	}

	/**
	 * 
	 * @return La posizione sull'asse X
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * 
	 * @return La posizione sull'asse Y
	 */
	public double getY() {
		return this.y;
	}

	/**
	 * 
	 * @return List of image, null if have a single frame
	 */
	public List<Image> getSprite() {
		if (image != null) {
			return new ArrayList<>(Arrays.asList(image));
		} 
		
		return null;
		
	}

	/**
	 * 
	 * @return L'altezza dell'immagine
	 */
	public int getHeight() {
	        
		return mainFrame.getHeigth();
	}

	/**
	 * 
	 * @return La larghezza dell'immagine
	 */
	public int getWidth() {
		return mainFrame.getWidth();
	}
	
	/**
	 * Return the main image of the Drawable object.
	 * @return
	 * 		the main image
	 */
	public BufferedImage getMainImage() {
		return mainFrame.getImage();
	}


	/**
	 * Return the state of the game.
	 * 
	 * @return the state of the game
	 */
	public GameState getState() {
		return state;
	}

}