package morpheus.view;

/**
 * 
 * @author Luca Mengozzi		 
 * 
 */
public class SpriteSheet {
	
	private Texture texture;
	private int width, height;

	/**
	 * 
	 * Costructor if it is a square sprite
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	public SpriteSheet(Texture texture, int size) {
		
		this(texture, size, size);
	}

	/**
	 * 
	 * Costructor if it is not a square sprite
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	public SpriteSheet(Texture texture, int width, int height) {
		
		this.texture = texture;
		this.width = width;
		this.height = height;
	}

	/**
	 * 
	 * Return the texture contains the spritesheet
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	public Texture getTexture() {
		
		return texture;
	}

	/**
	 * 
	 * Return the width of each sprite in the spritesheet
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	public int getWidth() {
		
		return width;
	}

	/**
	 * 
	 * Return the height of each sprite in the spritesheet
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	public int getHeight() {
		
		return height;
	}
}
