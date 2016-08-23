package morpheus.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

/**
 * 
 * This class includes the management of textures and sprites 
 * allow the memory load by loading only once a texture required multiple times
 * 
 * @author Luca Mengozzi
 * 
 */
public class Texture {

	/**
	 * 
	 * Map useful for storing texture inside
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	private final static Map<String, TextureManager> texMap = new HashMap<>();
	private TextureManager manager;
	
	/**
	 * 
	 * Costructor of texture that takes as input the path of it
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */   
    public Texture(String fileName) {

        TextureManager oldTexture = texMap.get(fileName);
        if (oldTexture != null) {

            manager = oldTexture;
        } else {

            final URL url = Texture.class.getResource(fileName);
            System.out.println("loading textures: " + url.getPath());
            final ImageIcon img = new ImageIcon(url);
            manager = new TextureManager(img.getImage());
            texMap.put(fileName, manager);
        }
    }

	/**
	 * 
	 * Classic render method
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	public void render(Graphics2D g, double x, double y) {

        g.drawImage(manager.getImage(), (int) x, (int) y, null);
    }

	/**
	 * 
	 * Return the image
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	public BufferedImage getImage() {

        return manager.getImage();
    }

	/**
	 * 
	 * Return the width of the texture
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	public int getWidth() {

        return manager.getWidth();
    }

	/**
	 * 
	 * Return the height of the texture
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	public int getHeight() {

		return manager.getHeight();
	}
}
