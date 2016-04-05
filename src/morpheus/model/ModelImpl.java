package morpheus.model;


import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
/**
 * 
 * @author jacopo
 *
 */
public class ModelImpl implements Model {
    
    private final Map<String, BufferedImage> textureMap;
    
    /**
     * 
     * @param map
     *          una mappa con tutte le path delle texture da caricare
     */
    public ModelImpl(final Map<String, String> map) {
        this.textureMap = new HashMap<>();
        associate(map);
    }
    
    /**
     * 
     */
    public ModelImpl() { 
        this.textureMap = new HashMap<>();
    }
    
    @Override
    public BufferedImage loader(final String file) {
       
        try {
            return ImageIO.read(new File(file));
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

    @Override
    public void addTexture(final Pair<String, String> p) {
        if (textureMap.containsKey(p.getKey())) {
            throw new IllegalArgumentException();
        }
        textureMap.put(p.getKey(), this.loader(p.getValue()));
    }
    
    @Override
    public BufferedImage getTexture(final String name) {
        return textureMap.get(name);
    }
    
    private void associate(final Map<String, String> map) {
        for (Entry<String, String> e : map.entrySet()) {
            textureMap.put(e.getKey(), this.loader(e.getValue()));
        }
    }

    
   
}
