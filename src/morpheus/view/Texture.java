package morpheus.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * 
 * Questa classe racchiude la gestione di texture e sprite, permette di
 * alleggerire il carico della memoria caricando solo una volta una texture
 * necessaria più volte
 * 
 * @author Luca Mengozzi		 
 * 
 */
public class Texture {

	/**
	 * 
	 * Mappa utile per memorizzare le Texture al suo interno ed evitare di
	 * caricare un oggetto ogni volta che si ha bisogno di una texture(quando
	 * viene richiesta si prende direttamente da qui essendo precaricata)
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	private final static Map<String, TextureManager> texMap = new HashMap<>();
	private TextureManager manager;
	
	/**
	 * 
	 * Costruttore della texture che prende in input il percorso di essa
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	public Texture(String fileName) {

		//Cerco la texture nella mappa
		TextureManager oldTexture = texMap.get(fileName);
		//Se è già presente
		if (oldTexture != null) {

			manager = oldTexture;
			//Se non è presente la aggiungo alla mappa
		} else {

			try {

				System.out.println("loading textures: " + fileName);
				//Carico le Texture ex novo dalle risorse
				manager = new TextureManager(ImageIO.read(new File(fileName)));
				texMap.put(fileName, manager);
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * Classico metodo render
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	public void render(Graphics2D g, double x, double y) {

		g.drawImage(manager.getImage(), (int) x, (int) y, null);
	}

	/**
	 * 
	 * Ritorna l'immagine
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	public BufferedImage getImage() {

		return manager.getImage();
	}

	/**
	 * 
	 * Ritorna la larghezza della texture
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	public int getWidth() {

		return manager.getWidth();
	}

	/**
	 * 
	 * Ritorna l'altezza della texture
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	public int getHeight() {

		return manager.getHeight();
	}

}
