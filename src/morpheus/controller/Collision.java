package morpheus.controller;

import java.awt.geom.Area;
import java.util.ArrayList;

import morpheus.model.AbstractDrawable;
import morpheus.model.AbstractPill;
import morpheus.model.Bullet;
import morpheus.model.Coin;
import morpheus.model.Player;
import morpheus.model.Spikes;
import morpheus.model.monster.AbstractMonster;
import morpheus.model.monster.Tree;
import morpheus.model.monster.Tree.TreeBullet;
import morpheus.view.state.GameState;

/**
 * Class that manage all the collisions of the game
 * 
 * @author matteo
 *
 */
public class Collision {

	private final GameState state;
	private final Player player;
	private ArrayList<AbstractDrawable> playerBullets;

	/**
	 * Main constructor
	 * 
	 * @param state
	 * @param p
	 */
	public Collision(final GameState state, final Player p) {
		this.state = state;
		this.player = p;
		this.playerBullets = new ArrayList<>();
	}

	/**
	 * Manage the vertical collision of all tiles of the game
	 * 
	 * @return
	 */
	public boolean hasVerticalCollision() {
	        Area a = new Area();
		// RANDOM TILES
		for (int i = 0; i < state.getAllRandomTiles().size(); i++) {
			RandomTile rt1 = state.getAllRandomTiles().get(i);
			a = player.getBottomArea();
			a.intersect(rt1.getTop());
			if ( !a.isEmpty() && player.getVelY() >= 0) {

				player.groundCollission();
				return true;
			} else {
				player.falling();
			}

			if (player.getBounds().intersects(rt1.getBottom()) && player.isInJump()) {
				player.stopJumping();
				return true;
			}
		}

		// SPIKES VERTICAL

		for (int i = 0; i < state.getEntities().size(); i++) {
			if (state.getEntities().get(i) instanceof Spikes) {
				Spikes ad = (Spikes) state.getEntities().get(i);
				a = player.getBottomArea();
	                        a.intersect(ad.getTopArea());
	                        if ( !a.isEmpty() && player.getVelY() >= 0) {

					player.groundCollission();
					if (!player.isKnocking()) {
						ad.reaction(player);
					}
					return true;
				} else {
					player.falling();
				}

				if (player.getBounds().intersects(ad.getBottom()) && player.isInJump()) {
					player.stopJumping();
					return true;

				}
			}
		}

		return false;

	}

	/**
	 * Manage the Horiztal collision of all tiles of the game
	 * 
	 * @return
	 */
	public boolean hasHorizontalCollision() {
		// RANDOM TILES

		for (int i = 0; i < state.getAllRandomTiles().size(); i++) {
			RandomTile rt1 = state.getAllRandomTiles().get(i);
			if (player.getBounds().intersects(rt1.getRight()) && player.getVelRun() <= 0) {
				return true;
			}
			if (player.getBounds().intersects(rt1.getLeft()) && player.getVelRun() >= 0) {
				return true;
			}
		}

		// SPIKES HORIZONTAL
		if (player.isKnocking() == false) {
			for (int i = 0; i < state.getEntities().size(); i++) {
				if (state.getEntities().get(i) instanceof Spikes) {
					Spikes ad = (Spikes) state.getEntities().get(i);
					if (player.getBounds().intersects(ad.getRight()) && player.getVelRun() <= 0) {
						return true;
					}
					if (player.getBounds().intersects(ad.getLeft()) && player.getVelRun() >= 0) {
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * Set the Collision between the player's bullets and the enemy
	 */
	public void hittedEnemy() {
		// HIT
		for (int i = 0; i < state.getEntities().size(); i++) {
			if (state.getEntities().get(i) instanceof Bullet) {
				playerBullets.add(state.getEntities().get(i));
			}
		}
		for (int i = 0; i < player.getBullets().size(); i++) {
			for (int j = 0; j < state.getEntities().size(); j++) {
				if (state.getEntities().get(j) instanceof AbstractMonster) {
					if (player.getBullets().get(i).getBounds().intersects(state.getEntities().get(j).getBounds())) {
						state.getEntities().get(j).setRemove();
						player.getBullets().get(i).explode();
						if (state.getEntities().get(i) instanceof Tree) {
							((Tree) state.getEntities().get(i)).stop();
						}
					}
				}
			}
		}

	}

	/**
	 * Set the Collision between player and all kinds of Coins
	 */
	public void getCoinCollision() {
		// COINS
		for (int i = 0; i < state.getEntities().size(); i++) {
			if (state.getEntities().get(i) instanceof Coin) {
				AbstractDrawable ad = state.getEntities().get(i);
				if (player.getBounds().intersects(ad.getBounds())) {
					GameState.bonus += ((Coin) state.getEntities().get(i)).reaction();
					state.getEntities().get(i).setRemove();
				}
			}
		}
	}

	/**
	 * Set the collision between the player and all kinds of enemies
	 */
	public void getEnemyCollision() {
		// ENEMIES
		if (player.isKnocking() == false) {
			for (int i = 0; i < state.getEntities().size(); i++) {
				if (state.getEntities().get(i) instanceof AbstractMonster) {
					AbstractDrawable ad = state.getEntities().get(i);
					if (player.getBounds().intersects(ad.getBounds())) {
						player.hit();
					}
				}
			}
		}
	}

	/**
	 * Set the Collision between the player and all kinds of Pills
	 */
	public void getPillCollision() {
		// PILLS
		for (int i = 0; i < state.getEntities().size(); i++) {
			if (state.getEntities().get(i) instanceof AbstractPill) {
				AbstractDrawable ad = state.getEntities().get(i);
				if (player.getBounds().intersects(ad.getBounds())) {
					((AbstractPill) state.getEntities().get(i)).reaction();
					state.getEntities().get(i).setRemove();
				}
			}
		}
	}

	/**
	 * Set the Collision between the player and the enemie's bullets
	 */
	public void getBulletCollision() {
		// BULLETS
		if (player.isKnocking() == false) {
			for (int i = 0; i < state.getEntities().size(); i++) {
				if (state.getEntities().get(i) instanceof TreeBullet) {
					TreeBullet ad = (TreeBullet) state.getEntities().get(i);
					if (player.getBounds().intersects(ad.getBounds())) {
						state.getEntities().get(i).setRemove();
						player.hit();
					}
				}
			}
		}
	}

	/**
	 * Tick method for logical operations
	 */
	public void tick() {

		if (!hasHorizontalCollision()) {
			player.startRun();
		}

		if (!hasVerticalCollision()) {
			player.setVerticalCollision(false);
		}

		hittedEnemy();

		getCoinCollision();

		getPillCollision();

		getEnemyCollision();

		getBulletCollision();
	}
}
