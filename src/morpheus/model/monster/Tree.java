package morpheus.model.monster;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import morpheus.model.Bullet;
import morpheus.model.Image;
import morpheus.model.ModelAnimation;
import morpheus.model.Sprite;
import morpheus.model.SpriteSheet;
import morpheus.view.GameState;
import morpheus.view.Texture;

public class Tree extends Monster {

    private final List<TreeBullet> bullets;
    private final Thread th;
    private volatile boolean stop;
    public Tree(double x, double y, GameState game, Image[] i) {
        super(x, y, game, i);
        final TreeAnimation anime = new TreeAnimation(i, this);
        setAnime(anime);
        bullets = new ArrayList<>();
        stop = false;
        th = new Thread(anime);
        th.start();
    }

    @Override
    public void tick() {
        for(TreeBullet tb : bullets) {
            tb.tick();
        }
    }
    
    public void render(final Graphics2D g) {    
       getAnimation().render(g, getX(), getY());
     
       
       //da togliere
       tick();
      
       
       for(Bullet b : bullets) {
          
           b.render(g);
       }
    }

    public void addBullet(final TreeBullet b) {
        bullets.add(b);
    }
    
    public void stop() {
        stop = true;
    }
    
    private static class TreeAnimation extends ModelAnimation implements Runnable {

        
        private final Tree tree;
        public TreeAnimation( Image[] frames, final Tree tree) {
            super(2, frames);
            tree.stop = false;
            this.tree = tree;
            this.currentFrame = frames[0];
        }
        
        public void run() {
            
            while(!tree.stop) {
                super.run();
                tree.addBullet(shoot());
               
                
                
                try {
                  
                    Thread.sleep(100);
                    super.run();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(100);
                    super.run();
                  
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
                try {
                    Thread.sleep(1500);
                    super.run();
                    
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
                
            }
        }
        
        
        private TreeBullet shoot() {
                  return new TreeBullet(tree.getX(), tree.getY(), tree.getState(), 
                                  new Image(new Texture("res/zucca.png").getImage(), 18, 18));     
        }
       
        
    }
    
    public static class TreeBullet extends Bullet {

        public TreeBullet(double x, double y, GameState game, Image i) {
            super(x, y, game, i);
           
        }
        
        @Override
        public void tick() {
            this.decX(this.getBulletVelocity());
        }
        
    }
}
