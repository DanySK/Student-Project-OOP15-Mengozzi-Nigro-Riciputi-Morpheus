package morpheus.model;

//import morpheus.view.GameState;
//import morpheus.view.Texture;
//
///**
// * 
// * @author jacopo
// *
// */
//public class Coin extends AbstractDrawable {
//
//	private final TypeCoin type;
//
//	/**
//	 * Create a coin.
//	 * 
//	 * @param i
//	 *            coin's images
//	 * @param x
//	 *            posizione sull'asse x
//	 * @param y
//	 *            posizione sull'asse y
//	 * @param state
//	 *            the game state
//	 * @param type
//	 *            the type of coin
//	 */
//	public Coin(final Image[] i, final double x, final double y, final TypeCoin type, final GameState state) {
//		super(x, y, state, i);
//		this.type = type;
//
//	}
//
//	/**
//	 * Create a normal coin.
//	 * 
//	 * @param x
//	 *            posizione sull'asse x
//	 * @param y
//	 *            posizione sull'asse y
//	 * @param state
//	 *            the gameState
//	 */
//	public Coin(final double x, final double y, final GameState state) {
//		super(x, y, state, new Image(new Texture("res/NormalCoin.png").getImage()));
//		this.type = TypeCoin.NORMAL;
//	}
//
//	/**
//	 * The reaction at the intersection with the main character. Leads the
//	 * increase of the total coin's value.
//	 * 
//	 * @param s
//	 *            the total value of coins
//	 */
//	public void reaction(final Statistics s) {
//		switch (type) {
//		case NORMAL:
//			s.incCoins();
//			break;
//		case SPECIAL:
//			s.incCoins(10);
//			break;
//		case X2:
//			s.incCoins(2);
//			break;
//		default:
//			break;
//		}
//	}
//
//	/**
//	 * @return the type
//	 */
//	public TypeCoin getType() {
//		return type;
//	}
//
//	/**
//	 * 
//	 * @author jacopo
//	 *
//	 */
//	public enum TypeCoin {
//		/**
//		 * NORMAL -> it's a normal coin, increase the total value of 1; SPECIAL
//		 * -> it's a important coin, increase the total value of 10; X2 -> it's
//		 * a particular coin, increase the total value of 2.
//		 */
//		NORMAL, SPECIAL, X2;
//	}
//
//	@Override
//	public void tick() {
//		// TODO Auto-generated method stub
//
//	}
//
//}
