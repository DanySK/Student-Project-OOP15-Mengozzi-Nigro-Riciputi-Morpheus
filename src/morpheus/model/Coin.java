package morpheus.model;

import java.awt.Graphics2D;

import morpheus.view.Texture;
import morpheus.view.state.GameState;

/**
 * 
 * @author jacopo
 *
 */
public class Coin extends AbstractDrawable {

    private static final int NORMALCOINDIMENSION = 24;
    private static final int COINVALUE = 5;
    private static final int SPECIALCOINVALUE = 25;

    private static final double DIMENSION64 = 64;

    private final TypeCoin type;
    private final Animation anime;

    /**
     * Create a coin.
     * 
     * @param i
     *            coin's images
     * @param x
     *            X position
     * @param y
     *            Y position
     * @param state
     *            game state
     * @param type
     *            the type of coin
     */
    public Coin(final double x, final double y, final TypeCoin type, final GameState state, final Image... i) {
        super(x, y, state, i);
        this.type = type;
        anime = new Animation(2, i);
    }

    /**
     * Create a normal coin.
     * 
     * @param x
     *            X position
     * @param y
     *            Y position
     * @param state
     *            game state
     */
    public Coin(final double x, final double y, final GameState state) {
        super(x, y, state,
                new Image(new Texture("/NormalCoin.png").getImage(), NORMALCOINDIMENSION, NORMALCOINDIMENSION));
        this.type = TypeCoin.NORMAL;
        anime = null;
    }

    /**
     * The reaction at the intersection with the main character. Leads the
     * increase of the total coin's value.
     * 
     * @return the value to add at the score
     */
    public int reaction() {
        switch (type) {
        case NORMAL:
            return COINVALUE;
        case SPECIAL:
            return SPECIALCOINVALUE;
        case X2:
            return 10;
        default:
            return 0;
        }
    }

    /**
     * Returns the type.
     * 
     * @return the type
     */
    public TypeCoin getType() {
        return type;
    }

    /**
     * 
     * @author jacopo
     *
     */
    public enum TypeCoin {
        /**
         * NORMAL -> it's a normal coin, increase the total value of 1; SPECIAL
         * -> it's a important coin, increase the total value of 10; X2 -> it's
         * a particular coin, increase the total value of 2.
         */
        NORMAL, SPECIAL, X2;
    }

    @Override
    public void setX(final double x) {
        super.setX(x + DIMENSION64 - getWidth());
    }

    @Override
    public void setY(final double y) {
        super.setY(y + DIMENSION64 - getHeight());
    }

    @Override
    public void tick() {
        if (anime != null) {
            anime.run();
        }
    }

    @Override
    public void render(final Graphics2D g) {
        if (anime == null) {
            super.render(g);
        } else {
            anime.render(g, getX(), getY());
        }
    }

}
