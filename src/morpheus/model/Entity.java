package morpheus.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import morpheus.Morpheus;
import morpheus.view.GameState;
import morpheus.view.Sprite;

public abstract class Entity {
	public static double X, Y;
	protected Sprite sprite;
	protected GameState state;

	public Entity(Sprite Sprite, double x, double y, GameState state) {
		this.sprite = Sprite;
		X = x;
		Y = y;
		this.state = state;
		this.state.addEntity(this);
	}

	// Ogni entit� (personaggio, nemici, ecc) avr� una logica diversa quindi
	// bisogna implementarlo volta per volta
	public abstract void tick();

	public void render(Graphics2D g) {
		sprite.render(g, X, Y);
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
		return new Rectangle((int) X, (int) Y, sprite.getWidth(), sprite.getHeight());
	}

	public Rectangle getTop() {
		return new Rectangle((int) X + 6, (int) Y, sprite.getWidth() - 6, 4);
	}

	public Rectangle getBottom() {
		return new Rectangle((int) X + 6, (int) Y + sprite.getHeight() - 4, sprite.getWidth() - 6, 4);
	}

	public Rectangle getRight() {
		return new Rectangle((int) X + sprite.getWidth() - 4, (int) Y + 6, 4, sprite.getHeight() - 6);
	}

	public Rectangle getLeft() {
		return new Rectangle((int) X, (int) Y + 6, 4, sprite.getHeight() - 6);
	}

	public double getX() {
		return X;
	}

	public double getY() {
		return Y;
	}

	public int getWidth() {
		return sprite.getWidth();
	}

	public int getHeight() {
		return sprite.getHeight();
	}

	public void setX(double x) {
		X = x;
	}

	public void setY(double y) {
		Y = y;
	}

}
