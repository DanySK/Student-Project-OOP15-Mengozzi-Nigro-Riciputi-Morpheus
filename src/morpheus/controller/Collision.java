package morpheus.controller;

import java.util.ArrayList;
import java.util.Iterator;

import morpheus.model.AbstractDrawable;
import morpheus.model.AbstractPill;
import morpheus.model.Bullet;
import morpheus.model.Coin;
import morpheus.model.Player;
import morpheus.model.monster.AbstractMonster;
import morpheus.model.monster.Tree.TreeBullet;
import morpheus.view.RandomTile;
import morpheus.view.Tile;
import morpheus.view.state.GameState;

public class Collision {

	private final GameState state;
	private final Player player;
	private ArrayList<AbstractDrawable> playerBullets;
	private ArrayList<AbstractDrawable> enemies;

	public Collision(final GameState state, final Player p) {
		this.state = state;
		this.player = p;
		this.playerBullets = new ArrayList<>();
		this.enemies = new ArrayList<>();
	}

	public boolean hasVerticalCollision() {
		// NORMAL TILES
		for (int i = 0; i < state.getTiles().size(); i++) {
			Tile t = state.getTiles().get(i);
			if (player.getBottom().intersects(t.getTop()) && player.getVelY() > 0) {
				player.groundCollission();
				return true;
			} else {
				player.falling();
			}

			if (player.getBounds().intersects(t.getBottom()) && player.getVelY() < 0) {
				player.setVelY(0);
				return true;
			}
		}
		// Aggiunto il calcolo delle collisioni sia verticali sia orizzontali
		// (vedi metodo sotto) delle RandomTile prendendole dal metodo
		// getAllRandomTiles() del GameState in cui � contenuto un Array con
		// tutte le RandomTile calcolate dalle BitMap

		// RANDOM TILES
		for (int i = 0; i < state.getAllRandomTiles().size(); i++) {
			RandomTile rt1 = state.getAllRandomTiles().get(i);
			if (player.getBottom().intersects(rt1.getTop()) && player.getVelY() >= 0) {

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
		return false;

	}

	public boolean hasHorizontalCollision() {
		// NORMAL TILES
		for (int i = 0; i < state.getTiles().size(); i++) {
			Tile t = state.getTiles().get(i);
			if (player.getBounds().intersects(t.getRight()) && player.getVelRun() < 0) {
				player.setVelRun(0);
				return true;
			}
			if (player.getBounds().intersects(t.getLeft()) && player.getVelRun() > 0) {
				player.setVelRun(0);
				return true;
			}
		}

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
		return false;
	}

	// public boolean hasSpikeCollsion() {
	// // SPIKES
	//
	// }

//	public boolean hasHittedEnemy() {
//		// HIT
//		for (int i = 0; i < state.getEntities().size(); i++) {
//			if (state.getEntities().get(i) instanceof Bullet) {
//				playerBullets.add(state.getEntities().get(i));
//			}
//			if (state.getEntities().get(i) instanceof AbstractMonster) {
//				enemies.add(state.getEntities().get(i));
//			}
//		}
//		if (playerBullets.size() != 0 && enemies.size() != 0) {
//			for (Iterator<AbstractDrawable> it = playerBullets.iterator(); it.hasNext();) {
//				for (Iterator<AbstractDrawable> it1 = enemies.iterator(); it1.hasNext();) {
//					AbstractDrawable ad = it.next();
//					if (it.next().getBounds().intersects(it1.next().getBounds())) {
//						state.getEntities().remove(ad);
//						return true;
//					}
//				}
//			}
//		}
//		return false;
//	}

	public boolean hasEnemyCollision() {
		// ENEMIES
		for (int i = 0; i < state.getEntities().size(); i++) {
			if (state.getEntities().get(i) instanceof AbstractMonster) {
				AbstractDrawable ad = state.getEntities().get(i);
				if (player.getBounds().intersects(ad.getBounds())) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean hasCoinCollision() {
		// COINS
		for (int i = 0; i < state.getEntities().size(); i++) {
			if (state.getEntities().get(i) instanceof Coin) {
				AbstractDrawable ad = state.getEntities().get(i);
				if (player.getBounds().intersects(ad.getBounds())) {
					state.getEntities().remove(ad);
					return true;
				}
			}
		}
		return false;
	}

	public boolean hasPillCollision() {
		// PILLS
		for (int i = 0; i < state.getEntities().size(); i++) {
			if (state.getEntities().get(i) instanceof AbstractPill) {
				AbstractDrawable ad = state.getEntities().get(i);
				if (player.getBounds().intersects(ad.getBounds()))
					return true;
			}
		}
		return false;
	}

	public boolean hasBulletCollision() {
		// BULLETS
		for (int i = 0; i < state.getEntities().size(); i++) {
			if (state.getEntities().get(i) instanceof TreeBullet) {
				AbstractDrawable ad = state.getEntities().get(i);
				if (player.getBounds().intersects(ad.getBounds())) {
					return true;
				}
			}
		}
		return false;
	}

	public void tick() {
		if (!hasHorizontalCollision()) {
			player.startRun();
		}

		if (!hasVerticalCollision()) {
			player.setVerticalCollision(false);
		}

//		if (hasHittedEnemy()) {
//
//		}

		if (hasCoinCollision()) {

		}

		if (hasPillCollision()) {

		}

		if (hasEnemyCollision()) {
			player.hit();
		}

		if (hasBulletCollision()) {
			player.hit();
		}
	}
}
