package morpheus.model;

import java.awt.Graphics2D;

import morpheus.view.Animation;
import morpheus.view.GameState;
import morpheus.view.RandomTile;
import morpheus.view.Sprite;
import morpheus.view.Tile;

public abstract class Mob extends Entity {
	protected double dX;
	protected double dY;
	protected double maxDY;
	protected double gravity;
	protected boolean falling;
	protected boolean canJump;
	protected boolean moving;
	protected Animation anime;
	// Variabile parte dalla stessa posizione del player (vedere
	// inizializzazione sotto) e venendo incementata (vedere metodo move())
	// registra la posizione del giocatore nella scena e attraverso i controlli
	// peresenti nella classe GameState scandisce i tempi di render delle BitMap
	public int tileSynch;

	public Mob(Sprite sprite, double x, double y, GameState state, Animation anime) {
		super(sprite, x, y, state);
		this.anime = anime;
		gravity = 0.5;
		falling = true;
		maxDY = 7;
		this.tileSynch = 200;
	}

	@Override
	public void tick() {
		move();
		fall();
		if (dX != 0) {
			moving = true;
		} else {
			moving = false;
		}
		if (moving) {
			anime.run();
		}

	}

	@Override
	public void render(Graphics2D g) {
		if (!moving) {
			super.render(g);
		} else {
			anime.render(g, X, Y);
		}
	}

	public void move() {

		if (!hasHorizontalCollision()) {
			X += dX;
			// Incremento la varibile dello stesso valore di movimento del
			// giocatore. Viene fatto qui l´incrememento perché sia il piú
			// preciso possibile dato che viene incrementata praticamente nello
			// stesso instante della posizione del del giocatore
			tileSynch += dX;

		}
		if (!hasVerticalCollision()) {
			Y += dY;
		}
	}

	public boolean hasVerticalCollision() {
		// NORMAL TILES
		for (int i = 0; i < state.getTiles().size(); i++) {
			Tile t = state.getTiles().get(i);
			if (this.getBounds().intersects(t.getTop()) && dY > 0) {
				canJump = true;
				falling = false;
				dY = 0;
				return true;
			} else {
				falling = true;
			}

			if (this.getBounds().intersects(t.getBottom()) && dY < 0) {
				dY = 0;
				return true;
			}
		}
		// Aggiunto il calcolo delle collisioni sia verticali sia orizzontali
		// (vedi metodo sotto) delle RandomTile prendendole dal metodo
		// getAllRandomTiles() del GameState in cui é contenuto un Array con
		// tutte le RandomTile calcolate dalle BitMap

		// RANDOM TILES
		for (int i = 0; i < state.getAllRandomTiles().size(); i++) {
			RandomTile rt1 = state.getAllRandomTiles().get(i);
			if (this.getBounds().intersects(rt1.getTop()) && dY > 0) {
				canJump = true;
				falling = false;
				dY = 0;
				return true;
			} else {
				falling = true;
			}

			if (this.getBounds().intersects(rt1.getBottom()) && dY < 0) {
				dY = 0;
				return true;
			}
		}
		return false;
	}

	public boolean hasHorizontalCollision() {
		// NORMAL TILES
		for (int i = 0; i < state.getTiles().size(); i++) {
			Tile t = state.getTiles().get(i);
			if (this.getBounds().intersects(t.getRight()) && dX < 0) {
				dX = 0;
				return true;
			}
			if (this.getBounds().intersects(t.getLeft()) && dX > 0) {
				dX = 0;
				return true;
			}
		}
		// RANDOM TILES
		for (int i = 0; i < state.getAllRandomTiles().size(); i++) {
			RandomTile rt1 = state.getAllRandomTiles().get(i);
			if (this.getBounds().intersects(rt1.getRight()) && dX < 0) {
				dX = 0;
				return true;
			}
			if (this.getBounds().intersects(rt1.getLeft()) && dX > 0) {
				dX = 0;
				return true;
			}
		}
		return false;
	}

	protected void fall() {
		if (falling) {
			dY += gravity;
			if (dY > maxDY) {
				dY = maxDY;
			}
		}
	}

	protected void jump(double jumpHeight) {
		if (canJump) {
			dY -= jumpHeight;
			canJump = false;
		}
	}

	public void setVelocityY(double dY) {
		this.dY = dY;
	}

	public boolean isMovingLeft() {
		return dX < 0;
	}

	public boolean isMovingRight() {
		return dX > 0;
	}

	public boolean isMoving() {
		return moving;
	}

	public double getdX() {
		return dX;
	}

}
