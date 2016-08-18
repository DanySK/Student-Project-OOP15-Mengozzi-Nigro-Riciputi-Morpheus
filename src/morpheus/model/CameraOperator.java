package morpheus.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;

import morpheus.Morpheus;
import morpheus.view.state.GameState;

public class CameraOperator extends AbstractDrawable {

    private Player player;

    public CameraOperator(final double x, final double y, final GameState game, final Player p) {
        super(x, y, game);
        player = p;
    }

    @Override
    public void tick() {
        int velX;
        if ((player.getX() - getX()) <= 350) {
            velX = player.getVelRun() - 1;
            this.incX(velX);
        } else {
            velX = player.getVelRun();
            this.incX(velX);
        }
        if (getArea().intersects(player.getRight())) {
            player.dead();
        }
    }

    public Area getArea() {
        return new Area(new Rectangle((int) getX() - 110, (int) getY(), 10, Morpheus.HEIGHT));
    }

    @Override
    public void render(Graphics2D g) {
      
        if (Morpheus.DEBUG) {
            g.setColor(Color.BLACK);
            g.draw(this.getArea());
        }
    }
}
