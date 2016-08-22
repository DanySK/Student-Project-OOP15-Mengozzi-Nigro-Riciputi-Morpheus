package morpheus.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;

import morpheus.Morpheus;
import morpheus.view.state.GameState;

/**
 * 
 * @author jacopo
 *
 */
public class CameraOperator extends AbstractDrawable {

    private static final int PLAYEROFFSET = 350;
    private static final int XOFFSET = 110;
    private final Player player;

    /**
     * The Operator, this class will move the camera.
     * 
     * @param x
     *            X position
     * @param y
     *            Y position
     * @param game
     *            game state
     * @param p
     *            current player
     */
    public CameraOperator(final double x, final double y, final GameState game, final Player p) {
        super(x, y, game);
        player = p;
    }

    @Override
    public void tick() {
        double velX;
        if ((player.getX() - getX()) <= PLAYEROFFSET) {
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

    /**
     * Return the Area.
     * 
     * @return the area
     */
    public Area getArea() {
        return new Area(new Rectangle((int) getX() - XOFFSET, (int) getY(), 10, Morpheus.HEIGHT));
    }

    @Override
    public void render(final Graphics2D g) {

    }
}
