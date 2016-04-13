package morpheus.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author jacopo
 *
 */
public class Sprite {
    
    private List<BufferedImage> frames;
    
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
    public Sprite(final SpriteSheet sheet, final int lines, final int columns, final int x) {
        frames = new ArrayList<>();
        int k = 0;
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                frames.add(sheet.getTexture().getImage().getSubimage(i * sheet.getWidth(),
                        j * sheet.getHeight(), 
                        sheet.getWidth(),
                        sheet.getHeight()));
                k++;
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
    
    /**
     * Return the list with all the image on the input sheet .
     * @return
     *          a list of BufferedImage
     */
    public List<BufferedImage> getFrames() {
        return new ArrayList<>(frames);
    }
    
    /**
     * Return an array of BufferedImage with all the frames for the animation.
     * @return
     *          an array of frames for the animation
     */
    public BufferedImage[] getFramesAsList() {
       BufferedImage[] images = new BufferedImage[]{ };
       int i = 0;
       for (BufferedImage b : frames) {
           images[i] = b;
           i++;
       }
       return images;
         
    }
    
   
    
}
