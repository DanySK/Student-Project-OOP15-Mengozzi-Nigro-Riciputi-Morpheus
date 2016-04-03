package morpheus.view;

import java.awt.image.BufferedImage;

public class TextureManager {
	private BufferedImage image;
	
	public TextureManager(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public int getWidth() {
		return image.getWidth();
	}
	
	public int getHeight() {
		return image.getHeight();
	}
}
