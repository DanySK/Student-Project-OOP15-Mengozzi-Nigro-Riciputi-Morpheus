package gioco.Model;


import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * 
 * @author jacopo
 *
 */
public class ModelImpl implements Model {
    
    /**
     * @param file
     *          path
     * @return 
     *          A BufferedImage
     */
    public BufferedImage loader(final String file) {
       
        try {
            return ImageIO.read(getClass().getResource(file));
        } catch (IOException e) {
            return null;
        }
        
    }

    @Override
    public void setKeyJump(final String key) {
        
    }
    
    @Override
    public void setKeyDown(final String key) {   
    }
    
    @Override
    public String getKeyJump() {
        return null;
    }

   

    @Override
    public String getKeyDown() {
        return null;
    }

    @Override
    public boolean intersects(final Area a, final Area b) {
        return a.intersects(b.getBounds());
    }

   
}
