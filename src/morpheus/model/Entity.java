package morpheus.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import morpheus.Morpheus;
import morpheus.view.Sprite;
import morpheus.view.GameState;

public abstract class Entity {
	protected double x, y;
	protected Sprite sprite;
	protected GameState state;

	public Entity(Sprite Sprite, double x, double y, GameState state) {
		this.sprite = Sprite;
		this.x = x;
		this.y = y;
		this.state = state;
		this.state.addEntity(this);
	}

	// Ogni entit� (personaggio, nemici, ecc) avr� una logica diversa quindi
	// bisogna implementarlo volta per volta
	public abstract void tick();

	public void render(Graphics2D g) {
		sprite.render(g, x, y);
		if (Morpheus.DEBUG) {
			g.setColor(Color.RED);
			g.draw(getTop());
			g.setColor(Color.BLUE);
			g.draw(getBottom());
			g.setColor(Color.MAGENTA);
			g.draw(getLeft());
			g.setColor(Color.ORANGE);
			g.draw(getRight());
		}

	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, sprite.getWidth(), sprite.getHeight());
	}

	public Rectangle getTop() {
		return new Rectangle((int) x + 6, (int) y, sprite.getWidth() - 6, 4);
	}

	public Rectangle getBottom() {
		return new Rectangle((int) x + 6, (int) y + sprite.getHeight() - 4, sprite.getWidth() - 6, 4);
	}

	public Rectangle getRight() {
		return new Rectangle((int) x + sprite.getWidth() - 4, (int) y + 6, 4, sprite.getHeight() - 6);
	}

	public Rectangle getLeft() {
		return new Rectangle((int) x, (int) y + 6, 4, sprite.getHeight() - 6);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getWidth() {
		return sprite.getWidth();
	}

	public int getHeight() {
		return sprite.getHeight();
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

}
