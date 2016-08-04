package morpheus.view;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import morpheus.model.Image;

/**
 * 
 * @author Luca
 *
 */
public class Sprite {
    
	private BufferedImage image;
    private List<Image> frames;
    
    /**
     * Create a sub image of the Texture with position (x,y) in the matrix.
     * @param sheet
     *          Image and information about that
     * @param lines
     *          Number of lines in the matrix
     * @param columns
     *          Number of columns in the matrix
     * @param x
     *          Number of image in the sheet
     */
    public Sprite(final SpriteSheet sheet, final int columns, final int lines, final int x) {
        frames = new ArrayList<>();
        int k = 0;
        for (int i = 0; i < columns; i++) {
            System.out.println("Colonna");
            for (int j = 0; j < lines; j++) {
                frames.add(new Image(sheet.getTexture().getImage().getSubimage(i * sheet.getWidth(),
                        j * sheet.getHeight(), 
                        sheet.getWidth(),
                        sheet.getHeight()), sheet.getWidth(), sheet.getHeight()));
                k++;
                System.out.println("Linea");
                if (k == x) {
                    k = -1;
                    break;
                }
                    
            }
            if (k == -1) {
                break;
            }
        }
        
        
    }
    
	public Sprite(SpriteSheet spriteSheet, int x, int y) {
		
		this.image = spriteSheet.getTexture().getImage().getSubimage(
				x * spriteSheet.getWidth() - spriteSheet.getWidth(),
				y * spriteSheet.getHeight() - spriteSheet.getHeight(), spriteSheet.getWidth(),//C'era un errore bravo luca
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
    
    /**
     * Return the list with all the image on the input sheet .
     * @return
     *          a list of BufferedImage
     */
    public List<Image> getFrames() {
        return new ArrayList<>(frames);
    }
    
    /**
     * Return an array of BufferedImage with all the frames for the animation.
     * @return
     *          an array of frames for the animation
     */
    public Image[] getFramesAsList() {
       Image[] images = new Image[frames.size()];
       int i = 0;
       for (final Image b : frames) {
           images[i] = b;
           i++;
       }
       return images;
         
    }
    
    /**
     * Returns the main Frame.
     * @return
     * 		the main Frame
     */
    public Image getMainFrame() {
    	return frames.get(0);
    }
}
   
    
