package morpheus.model;

import java.awt.Graphics2D;

import morpheus.view.Animation;
import morpheus.view.Sprite;
import morpheus.view.GameState;
import morpheus.view.Tile;

public abstract class Mob extends Entity {
	protected double dX, dY;
	protected double maxDY;
	protected double gravity;
	protected boolean falling;
	protected boolean canJump;
	protected boolean moving;
	protected Animation anime;

	public Mob(Sprite sprite, double x, double y, GameState state, Animation anime) {
		super(sprite, x, y, state);
		this.anime = anime;
		gravity = 0.5;
		falling = true;
		maxDY = 7;
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
			anime.render(g, x, y);
		}
	}

	public void move() {
		if (!hasHorizontalCollision()) {
			x += dX;
		}
		if (!hasVerticalCollision()) {
			y += dY;
		}
	}

	public boolean hasVerticalCollision() {
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
		return false;
	}

	public boolean hasHorizontalCollision() {
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

}
