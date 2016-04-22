package morpheus.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

//Da lavorarci e guardare
public class Sprite {

	private BufferedImage image;

	public Sprite(SpriteSheet spriteSheet, int x, int y) {
		
		this.image = spriteSheet.getTexture().getImage().getSubimage(
				x * spriteSheet.getWidth() - spriteSheet.getWidth(),
				y * spriteSheet.getHeight() - spriteSheet.getHeight(), spriteSheet.getHeight(),
				spriteSheet.getHeight());
	}
	
	public Sprite(String textName) {
		
		Texture tex = new Texture(textName);
		image = tex.getImage();
	}

	public void render(Graphics2D g, double x, double y) {
		
		g.drawImage(image, (int) x, (int) y, null);
	}
	
	public int getWidth() {
		
		return image.getWidth();
	}
	
	public int getHeight(){
		
		return image.getHeight();
	}
}
