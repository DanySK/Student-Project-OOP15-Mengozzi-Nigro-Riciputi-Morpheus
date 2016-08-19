package morpheus.model;

import morpheus.view.Texture;
import morpheus.view.state.GameState;

/**
 * 
 * @author jacopo
 * 
 *         Create the main Character.
 */
public final class MainPlayer extends Player {

    private static MainPlayer player;

    private static final int PLAYERHEIGTH = 60;
    private static final int PLAYERWIDTH = 38;
    private static final int FALLWIDTH = 49;
    private static final int FRAMES = 6;

    /**
     * 
     * L'oggetto prende in input l'altezza e la larghezza dell'immagine a
     * schermo, le sue cordinate e un BufferedImage che sarà l'immagine che si
     * andrà a disegnare sull'oggetto graphics passato al costruttore.
     * 
     * @param i
     *            have all the information of the images
     * @param x
     *            posizione sull'asse x
     * @param y
     *            posizione sull'asse y
     * @param state
     *            state of game
     */
    private MainPlayer(final double x, final double y, final GameState state, final Option stat, final Image... i) {
        super(x, y, state, stat, i);

    }

    /**
     * Create the main player if doesn't exist. And return it.
     * 
     * @param x
     *            x position
     * @param y
     *            y position
     * @param state
     *            the state of game
     * @param stat
     *            option
     * @return the main player
     */
    public static MainPlayer getPlayer(final double x, final double y, final GameState state, final Option stat) {
        synchronized (MainPlayer.class) {
            if (player == null) {
                final Image[] app = new Image[FRAMES];
                final Image[] app1 = new Sprite(
                        new SpriteSheet(new Texture("res/sayancorsapulito.png"), PLAYERWIDTH, PLAYERHEIGTH), 4, 1,
                        4).getFramesAsList();
                System.arraycopy(app1, 0, app, 0, app1.length);
                app[FRAMES - 2] = new Sprite(new SpriteSheet(new Texture("res/sayancaduta.png"), FALLWIDTH, PLAYERHEIGTH), 1,
                        1, 1).getMainFrame();
                app[FRAMES - 1] = new Sprite(new SpriteSheet(new Texture("res/vuoto.png"), PLAYERWIDTH, PLAYERHEIGTH), 1,
                        1, 1).getMainFrame();
                player = new MainPlayer(x, y, state, stat, app);

            }
        }
        return player;
    }

    /**
     * 
     * Returns to the main player , it has not been initialized return null.
     * 
     * @return the main player , it has not been initialized return null.
     */
    public static MainPlayer getPlayer() {
        return player;
    }

}
