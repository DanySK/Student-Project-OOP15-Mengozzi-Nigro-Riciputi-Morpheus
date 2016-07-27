package morpheus.model;

/**
 * 
 * @author jacopo
 *
 */
public class Item {
	private int hp;
	private int bullet;
	private boolean doubleJump;
	
	/**
	 * Default item:
	 * 3 life;
	 * 1 bullet;
	 * No doubleJump.
	 */
	public Item() {
		hp = 3;
		bullet = 1;
		doubleJump = false;
	}
	
	/**
	 * Increments life value of 1.
	 */
	public void incHP() {
		hp++;
	}
	
	/**
	 * Increments bullet value of 1.
	 */
	public void incBullet() {
		bullet++;
	}
	
	/**
	 * Set the doubleJump at true.
	 */
	public void canDoubleJump() {
		doubleJump = true;
	}
	
	/**
	 * Decrements life value of 1.
	 */
	public void decHP() {
		hp--;
	}
	
	/**
	 * Decrements bullet value of 1.
	 */
	public void decBullet() {
		bullet--;
	}
	
	/**
	 * Set the doubleJump at false.
	 */
	public void stopDoubleJump() {
		doubleJump = false;
	}
	
	/**
	 * Returns the lifes.
	 * @return
	 * 		the lifes
	 */
	public int getHP() {
		return hp;
	}
	
	/**
	 * Returns the bullets.
	 * @return
	 * 		the bullets
	 */
	public int getBullet() {
		return bullet;
	}
	
	/**
	 * Return true if the doubleJump is available.
	 * @return
	 * 		true if the doubleJump is available, false otherwise
	 */
	public boolean isDoubleJump() {
		return doubleJump;
	}
}
