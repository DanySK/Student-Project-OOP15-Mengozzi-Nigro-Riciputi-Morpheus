package morpheus.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import morpheus.Morpheus;
import morpheus.view.Sprite;

public class Tile {
	protected double x, y;
	protected Sprite sprite;
	protected boolean solid;

	public Tile(double x, double y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.solid = true;
	}

	public void render(Graphics2D g) {
		sprite.render(g, x, y);
		if (Morpheus.DEBUG) {
			g.setColor(Color.BLACK);
			g.draw(getTop());
			g.setColor(Color.BLUE);
			g.draw(getBottom());
			g.setColor(Color.MAGENTA);
			g.draw(getLeft());
			g.setColor(Color.ORANGE);
			g.draw(getRight());
		}

	}

	public boolean isSolid() {
		return solid;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
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

}
