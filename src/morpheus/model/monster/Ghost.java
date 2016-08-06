package morpheus.model.monster;

import java.awt.Graphics2D;

import morpheus.model.Image;
import morpheus.model.ModelAnimation;
import morpheus.view.GameState;

public class Ghost extends AbstractMonster {

    
    private static final int GHOSTOFFSET = 50;
    public Ghost(double x, double y, GameState game, Image... i) {
        super(x, y, game, i);
        setAnime(new ModelAnimation(2, i) {
            
            @Override
            public void run() {
                nextFrame();
            }
        });
    }

    @Override
    public void tick() {
        if (isDirection()) {
            decY(1);
            if (getY() <= getInitialY() - GHOSTOFFSET) {
                getAnimation().run();

                changeDirection();
            }
        } else {
            incY(1);
            if (getY() >= getInitialY() + GHOSTOFFSET) {
                getAnimation().run();

                changeDirection();
            }
        }
    }

    @Override
    public void render(final Graphics2D g) {
        this.getAnimation().run();
        this.tick();
        if (getAnimation() == null) {
            super.render(g);
        } else {
            getAnimation().render(g, getX(), getY());
        }
    }

}
