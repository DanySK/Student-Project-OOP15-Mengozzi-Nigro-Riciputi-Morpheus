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
        private final TypeCoin type;
	private final Animation anime;
	
	/**
	 * Create a coin.
	 * 
	 * @param i
	 *            coin's images
	 * @param x
	 *            posizione sull'asse x
	 * @param y
	 *            posizione sull'asse y
	 * @param state
	 *            the game state
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
	 *            posizione sull'asse x
	 * @param y
	 *            posizione sull'asse y
	 * @param state
	 *            the gameState
	 */
	public Coin(final double x, final double y, final GameState state) {
		super(x, y, state, new Image(new Texture("res/NormalCoin.png").getImage(), NORMALCOINDIMENSION, NORMALCOINDIMENSION));
		this.type = TypeCoin.NORMAL;
		anime = null;
	}

	/**
	 * The reaction at the intersection with the main character. Leads the
	 * increase of the total coin's value.
	 * 
	 */
	public void reaction() {
		switch (type) {
		case NORMAL:
		    System.out.println("+1");
			break;
		case SPECIAL:
		    System.out.println("+5");
			break;
		case X2:
		    System.out.println("+2");
			break;
		default:
			break;
		}
	}

	/**
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
