package morpheus.view;

import java.awt.image.BufferedImage;

/**
 * 
 * @author Luca Mengozzi		 
 * 
 */
public class TextureManager {
	
	private BufferedImage image;
	
	/**
	 * 
	 * Costruttore che prende in input l'immagine
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	public TextureManager(BufferedImage image) {
		
		this.image = image;
	}
	
	/**
	 * 
	 * Ritorna l'immagine
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	public BufferedImage getImage() {
		
		return image;
	}
	
	/**
	 * 
	 * Ritorna la larghezza
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	public int getWidth() {
		
		return image.getWidth();
	}
	
	/**
	 * 
	 * Ritorna l'altezza
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	public int getHeight() {
		
		return image.getHeight();
	}
}
