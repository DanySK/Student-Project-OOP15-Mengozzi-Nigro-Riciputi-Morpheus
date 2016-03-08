package gioco.Model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ModelImpl implements Model {
    
    public BufferedImage loader(String file) {
       
        try {
            return ImageIO.read(getClass().getResource(file));
        } catch (IOException e) {
            return null;
        }
        
    }

    @Override
    public void setKeyJump() {
        
    }

    @Override
    public String getKeyJump() {
        return null;
    }
}
