package morpheus.model;

import java.awt.Graphics2D;

import morpheus.controller.KeyInput;
import morpheus.model.Exceptions.NoBulletException;
import morpheus.model.Exceptions.OverRoofException;
import morpheus.view.GameState;
import morpheus.view.Texture;

/**
 * 
 * @author jacopo
 *      
 * Create the main Character.
 */
public class MainPlayer extends AbstractDrawable {
    
    
        private static MainPlayer player = null;
        private static final int PLAYERHEIGTH = 80;
        private static final int PLAYERWIDTH = 55;
	/**
	 * velocità iniziale.
	 */
	public static final int INITIAL_VEL = 7;
	/**
	 * velocità iniziale volo.
	 */
	public static final int INITIAL_VEL_FLY = 5;
	/**
	 * Gravità di partenza.
	 */
	public static final int INITIAL_GRAVITY = 1;
	/**
	 * Per animazione morte.
	 */
	public static final int SLOWLY_DEATH = 4;
	/**
	 * Pavimento.
	 */
	public static final int FLOOR = 40;
	/**
	 * Tetto.
	 */
	public static final int ROOF = 5;
	private static final int BULLETSIZE = 24;
	private volatile boolean runGO;
	private int velRun = INITIAL_VEL;
	private int velFly = INITIAL_VEL_FLY;
	private int jmp = 10;
	private int gravity = INITIAL_GRAVITY;
	private final Statistics s;
	private Status status;
	private final ModelAnimation animation;
	private final Item item;
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
	public MainPlayer(final double x, final double y, final GameState state, final Image... i) {
		super(x, y, state, i);
		s = new Statistics();
		status = Status.RUN;
		this.runGO = true;
		animation = new ModelAnimation(2, i);
		item = new Item();
	}
	
	/**
	 * Create the player if doesn't exist. And return it.
	 * @param x
	 *             x position
	 * @param y
	 *             y position
	 * @param state
	 *             the state of game
	 * @return
	 *             the main player
	 */
	public static MainPlayer getPlayer(final double x, final double y, final GameState state) { 
	    if (player == null) {
	        synchronized (MainPlayer.class) {
	            if (player == null) {
	                player = new MainPlayer(x, y, state, 
	                                new Sprite(new SpriteSheet(new Texture("res/sayan.png"), PLAYERWIDTH, PLAYERHEIGTH), 4, 1, 4).getFramesAsList());
	            }
	        }
	    }
	    return player;
	}
	
	/**
	 * 
         * Returns to the main player , it has not been initialized return null.
	 * @return
	 *          the main player , it has not been initialized return null.   
	 */
	public static MainPlayer getPlayer() {
	    return player;
	}

	/**
	 * Move the character.
	 */
	public void tick() {
		goOn();
		animation.run();
		if (status == Status.DEATH) {
			death();
		} else {
			if (KeyInput.isDown(s.getKeyJump())) {
				jump();
			}
		}

	}

	/**
	 * Permette all'oggetto Morpheus di alzarsi.
	 * 
	 * @throws OverRoofException
	 *             if the value of y is less then roof
	 */
	public void goUp() throws OverRoofException {
		if (getY() - velFly < ROOF) {
			final RuntimeException overRoof = new OverRoofException(ROOF - (int) this.getY() - velFly);
			throw overRoof;
		}

		this.incY(velFly);

	}

	/**
	 * Permette all'oggetto Morpheus di abbassarsi.
	 */
	public void goDown() {
		if (getY() - velFly < ROOF) {
			final RuntimeException overRoof = new OverRoofException(ROOF - (int) this.getY() - velFly);
			throw overRoof;
		}
		this.decY(velFly);
	}

	/**
	 * Permette all'oggetto Morpheus di saltare.
	 */
	public void jump() {
		this.decY(jmp);
	}
	
	/**
	 * Create a bullet object and returns it.
	 * @throws NoBulletException
	 * 		if the player try to shoot but he haven't bullet.
	 * @return
	 * 		a bullet
	 */
	public Bullet shoot() throws NoBulletException {
		if (getBullet() <= 0) {
			throw new NoBulletException();
		}
		return new Bullet(getX(), getY(), getState(), 
						new Sprite(new SpriteSheet(new Texture("res/bullet.png"), BULLETSIZE), 2, 1, 2).getFramesAsList());
	}

	/**
	 * Set the high of the jump.
	 * 
	 * @param x
	 *            the new valor of jump
	 */
	public void setJump(final int x) {
		this.jmp = x;
	}

	/**
	 * Set the Fly velocity.
	 * 
	 * @param x
	 *            the new valor
	 */
	public void setFlyVelocity(final int x) {
		this.velFly = x;
	}

	/**
	 * Set the gravity.
	 * 
	 * @param x
	 *            the new valor
	 */
	public void setGravity(final int x) {
		this.gravity = x;
	}

	/**
	 * Set the run on true, so the character start his running.
	 */
	public void startRun() {
		this.runGO = true;
	}

	/**
	 * Set the run on false, so stop the character's run.
	 */
	public void stopRun() {
		this.runGO = false;
	}

	/**
	 * Return true if the characters is moving. Else otherwise.
	 * 
	 * @return true if the characters is moving, else otherwise
	 */
	public boolean isRunning() {
		return this.runGO;
	}

	/**
	 * Set the field status.
	 * 
	 * @param s
	 *            new value of status
	 */
	public void setStatus(final Status s) {
		status = s;
	}

	/**
	 * Return the character's status.
	 * 
	 * @return the character's status
	 * 
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Fa muovere l'immagine di Morpheus sull'asse orrizzontale.
	 */
	private void goOn() {
		this.incX(velRun);
		this.incY(gravity);
	}

	private void death() {
		if (getY() == FLOOR) {
			velRun = 0;
		} else {
			incY(SLOWLY_DEATH);
		}
	}
	
	@Override
	public void render(final Graphics2D g) {
		if (status.equals(Status.RUN)) {
			animation.render(g, getX(), getY());
		} else {
			super.render(g);
		}
	}
	
	/**
	 * Increments life value of 1.
	 */
	public void incLife() {
		item.incHP();
	}
	
	/**
	 * Increments bullet value of 1.
	 */
	public void incBullet() {
		item.incBullet();
	}
	
	/**
	 * Set doubleJump value at true.
	 */
	public void canDoubleJump() {
		item.canDoubleJump();
	}
	
	/**
	 * Returns the lifes.
	 * @return
	 * 		the lifes
	 */
	public int getLife() {
		return item.getHP();
	}
	
	/**
	 * Returns the bullets.
	 * @return
	 * 		the bullets
	 */
	public int getBullet() {
		return item.getBullet();
	}
	
	/**
	 * Return true if the doubleJump is available.
	 * @return
	 * 		true if the doubleJump is available, false otherwise
	 */
	public boolean isDoubleJump() {
		return item.isDoubleJump();
	}
	
	/**
	 * Decrements life value of 1.
	 */
	public void decLife() {
		item.decHP();
	}
	
	/**
	 * Decrements bullet value of 1.
	 */
	public void decBullet() {
		item.decBullet();
	}
	
	/**
	 * Set the doubleJump at false.
	 */
	public void stopDoubleJump() {
		item.stopDoubleJump();
	}
	

	/**
	 * 
	 * @author jacopo
	 *
	 */
	public enum Status {
		/**
		 * 
		 */
		FLY, RUN, DEATH;
	}



	
}
