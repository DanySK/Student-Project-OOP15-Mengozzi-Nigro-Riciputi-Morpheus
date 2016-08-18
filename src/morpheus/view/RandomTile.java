package morpheus.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import morpheus.Morpheus;
import morpheus.controller.BitMap;

public class RandomTile {
	private double x;
	private double y;
	private Sprite sprite;
	private boolean solid;
	// Offset di posizione per le collisioni
	private int offset;

	public RandomTile(int idSprite) {
		this.x = 0;
		this.y = 0;
		this.solid = true;
		this.offset = 0;
		// idSprite non � altro che il valore della BitMap della singola Tile che determina quale Tile verr� renderizzata.
		// Per ora ho inserito solo un tipo di tile ma ho gia fatto 
		// lo spazio per le altre quindi ci sar� modo e tempo per inserirle
		switch (idSprite) {
		case 1:
			this.sprite = new Sprite(new SpriteSheet(new Texture("res/provaTerreno.png"), 64), 1, 1);
		case 2:

		case 3:

		case 4:
		}
	}
	// Metodo render analogo a quello delle Tile normali se non per l�offset relativo alle collisioni
	public void render(Graphics2D g, int offset) {
		sprite.render(g, x + offset, y);
		setOffset(offset);
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

	public Sprite getSprite() {
		return sprite;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x + offset, (int) y, sprite.getWidth(), sprite.getHeight());
	}

	public Rectangle getTop() {
		return new Rectangle((int) x + 1 + offset, (int) y, sprite.getWidth() - 4, 4);
	}

	public Rectangle getBottom() {
		return new Rectangle((int) x + 6 + offset, (int) y + sprite.getHeight() - 4, sprite.getWidth() - 6, 4);
	}

	public Rectangle getRight() {
		return new Rectangle((int) x + sprite.getWidth() - 4 + offset, (int) y + 6, 4, sprite.getHeight() - 6);
	}

	public Rectangle getLeft() {
		return new Rectangle((int) x + offset, (int) y + 6, 4, sprite.getHeight() - 6);
	}

	// Metodo per spostare le tile all�interno della scena, riceve in input dei
	// valori interi e li trasforma in coordinate pixel moltiplicando tali
	// valori per la larghezza e l�altezza delle tile stesse
	public void setLocation(int x, int y) {
		if (sprite != null) {
			this.x = x * BitMap.TILE_WIDTH;
			this.y = y * BitMap.TILE_HEIGHT;
		}
	}
	// Metodo per impostare velocemente l�offset delle collisioni
	public void setOffset(int offset) {
		this.offset = offset;
	}

}
