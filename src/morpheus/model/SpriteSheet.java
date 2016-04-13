package morpheus.model;



import morpheus.view.Texture;

/**
 * 
 * @author jacopo
 *
 */
public class SpriteSheet {
    
    private final Texture image;
    private final int width;
    private final int height;
    
    /**
     * Sprite image initialization, contains the texture and the size information.
     * This constructor put the width and the height to the same size.
     * @param t
     *          Texture to split
     * @param size
     *          The size of the image
     */
    public SpriteSheet(final Texture t, final int size) {
        this(t, size, size);
    }
    /**
     * 
     * Sprite image initialization, contains the texture and the size information.
     * @param t
     *          Texture da cui prendere la immagine
     * @param width
     *          larghezza dell'immagine
     * @param height
     *          altezza dell'immagine
     */
    public SpriteSheet(final Texture t, final int width, final int height) {
        this.image = t;
        this.width = width;
        this.height = height;
    }
    
    /**
     * Return the image.
     * @return
     *          Return the image
     */
    public Texture getTexture() {
        return image;
    }
    
    /**
     * Return the width of the image.
     * @return
     *          Return the width of the image.
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Return the height of the image.
     * @return
     *          Return the height of the image
     */
    public int getHeight() {
        return height;
    }
    
}
