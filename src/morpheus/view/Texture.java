package morpheus.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * Questa classe racchiude la gestione di texture e sprite e permette di
 * alleggerire il carico della memoria caricando solo una volta una texture
 * necessaria più volte
 */
public class Texture {

	// Mappa utile per memorizzare le Texture al suo interno ed evitare di
	// caricare un oggetto ogni volta che si ha bisogno di una texture(quando
	// viene richiesta si prende direttamente da qui essendo precaricata)
	private final static Map<String, TextureManager> texMap = new HashMap<>();
	private TextureManager manager;
	private String fileName;

	public Texture(String fileName) {

		this.fileName = fileName;
		TextureManager oldTexture = texMap.get(fileName);
		if (oldTexture != null) {

			manager = oldTexture;
		} else {

			try {

				System.out.println("loading textures: " + fileName);
				// Carico le Texture ex novo dalle risorse
				manager = new TextureManager(ImageIO.read(new File(fileName)));
				texMap.put(fileName, manager);
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

	public void render(Graphics2D g, double x, double y) {

		g.drawImage(manager.getImage(), (int) x, (int) y, null);
	}


	public BufferedImage getImage() {

		return manager.getImage();
	}

	public int getWidth() {

		return manager.getWidth();
	}

	public int getHeight() {

		return manager.getHeight();
	}

}
