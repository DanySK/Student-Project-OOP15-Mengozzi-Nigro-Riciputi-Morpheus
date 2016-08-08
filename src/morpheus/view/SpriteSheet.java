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
	 * Costruttore nel caso le sprite siano quadrata
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	public SpriteSheet(Texture texture, int size) {
		
		this(texture, size, size);
	}

	/**
	 * 
	 * Costruttore nel caso le sprite non siano quadrate ma abbiano altezze
	 * e larghezze diverse
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
	 * Ritorna la texture contenente la spritesheet
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	public Texture getTexture() {
		
		return texture;
	}

	/**
	 * 
	 * Ritorna la larghezza di ogni singola sprite all'interno della spritesheet
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	public int getWidth() {
		
		return width;
	}

	/**
	 * 
	 * Ritorna l'altezza di ogni singola sprite all'interno della spritesheet
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	public int getHeight() {
		
		return height;
	}
}
