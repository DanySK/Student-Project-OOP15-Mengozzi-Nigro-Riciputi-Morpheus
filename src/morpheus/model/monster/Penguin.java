package morpheus.model.monster;

import java.awt.Graphics2D;

import morpheus.model.Image;
import morpheus.model.ModelAnimation;
import morpheus.view.GameState;

/**
 * 
 * @author jacopo
 *
 */
public class Penguin extends Monster {

    private boolean step = false;

    /**
     * 
     * @param x
     * @param y
     * @param game
     * @param i
     */
    public Penguin(final double x, final double y, final GameState game, final Image[] i) {
        super(x, y, game, i);
        setAnime(new PenguinAnimation(2, i));
    }

    @Override
    public void tick() {

        if (getDirection()) {
            
            incX(1);
            if (getX() >= getInitialX() + 30) {
                getAnimation().run();
                
                changeDirection();
            }
        } else {

           
            decX(1);
            if (getX() <= getInitialX() - 30) {
                getAnimation().run();
                
                changeDirection();
            }
        }
    }
    
    @Override
    public void render(final Graphics2D g) {
        this.tick();
        if (getAnimation() != null) {

            getAnimation().render(g, getX(), getY());
        } else {
            super.render(g);

        }
    }

    private static class PenguinAnimation extends ModelAnimation {

        private int index;
      
        /**
         * 
         * @param speed
         * @param frames
         */
        PenguinAnimation(final int speed, final Image[] frames) {
            super(speed, frames);
            index = 1;
            this.currentFrame = frames[index];
           
        }

        
       public void run() {
            nextFrame();    
        }

    }

}
