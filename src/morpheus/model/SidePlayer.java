package morpheus.model;

import morpheus.view.Texture;
import morpheus.view.state.GameState;

/**
 * 
 * @author jacopo
 *
 */
public final class SidePlayer extends Player {

    private static SidePlayer player;

    private static final int PLAYERHEIGTH = 59;
    private static final int PLAYERWIDTH = 38;
    private static final int FALLWIDTH = 52;
    private static final int FRAMES = 6;

    /**
     * 
     * Create the side Player.
     * 
     * @param i
     *            array of Image
     * @param x
     *            X position
     * @param y
     *            Y position
     * @param state
     *            game state
     */
    private SidePlayer(final double x, final double y, final GameState state, final Option stat, final Image... i) {
        super(x, y, state, stat, i);
    }

    /**
     * Create the side player if doesn't exist. And return it.
     * 
     * @param x
     *            x position
     * @param y
     *            y position
     * @param state
     *            the state of game
     * @param stat
     *            option
     * @return the side player
     */
    public static SidePlayer getPlayer(final double x, final double y, final GameState state, final Option stat) {
        synchronized (SidePlayer.class) {
            if (player == null) {
                final Image[] app = new Image[FRAMES];
                final Image[] app1 = new Sprite(
                        new SpriteSheet(new Texture("res/sidecorsa.png"), PLAYERWIDTH, PLAYERHEIGTH), 4, 1, 4)
                                .getFramesAsList();
                System.arraycopy(app1, 0, app, 0, app1.length);
                app[FRAMES - 2] = new Sprite(
                        new SpriteSheet(new Texture("res/sideCaduta.png"), FALLWIDTH, PLAYERHEIGTH), 1, 1, 1)
                                .getMainFrame();
                app[FRAMES - 1] = new Sprite(new SpriteSheet(new Texture("res/vuoto.png"), PLAYERWIDTH, PLAYERHEIGTH),
                        1, 1, 1).getMainFrame();
                player = new SidePlayer(x, y, state, stat, app);

            }
        }
        return player;
    }

    /**
     * 
     * Returns to the side player , it has not been initialized return null.
     * 
     * @return the side player , it has not been initialized return null.
     */
    public static SidePlayer getPlayer() {
        return player;
    }

}
