package morpheus.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;

import morpheus.Morpheus;
import morpheus.view.Sprite;
import morpheus.view.SpriteSheet;
import morpheus.view.Texture;

/**
 * Manage and load all the tiles of the game
 * 
 * @author matteo
 *
 */
public class RandomTile {
	private double x;
	private double y;
	private Sprite sprite;
	private int offset;

	/**
	 * Main Constructor that inzitialize the kinf of texture of the tile
	 * according to and iD parameter
	 * 
	 * @param idSprite
	 */
	public RandomTile(int idSprite) {
		this.x = 0;
		this.y = 0;
		this.offset = 0;

		switch (idSprite) {
		case 1:
			this.sprite = new Sprite(new SpriteSheet(new Texture("res/Terreno.png"), 64), 1, 1);
			break;
		case 2:
			break;

		}
	}

	/**
	 * Method that render all the textures to the screen according to and offset
	 * cause we have to move them towards the game
	 * 
	 * @param g
	 * @param offset
	 */
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

	/**
	 * Get the x coordinate of the Tile's position
	 * 
	 * @return the x Tile's coordinate
	 */
	public double getX() {
		return x;
	}

	/**
	 * Get the y coordinate of the Tile's position
	 * 
	 * @return the y Tile's coordinate
	 */
	public double getY() {
		return y;
	}

	/**
	 * Gets the Sprite of the Tile
	 * 
	 * @return the Sprite of the Tile
	 */
	public Sprite getSprite() {
		return sprite;
	}

	/**
	 * Get a rectangle to manage the collision
	 * 
	 * @return the rectangle that surround all the Tile
	 */
	public Rectangle getBounds() {
		return new Rectangle((int) x + offset, (int) y, sprite.getWidth(), sprite.getHeight());
	}

	/**
	 * Get a rectangle to manage the top collision
	 * 
	 * @return the rectangle that surround only the top side of the Tile
	 */
	public Area getTop() {
		return new Area (new Rectangle((int) x + 1 + offset, (int) y, sprite.getWidth() - 4, 4));
	}

	/**
	 * Get a rectangle to manage the bottom collision
	 * 
	 * @return the rectangle that surround only the bottom side of the Tile
	 */
	public Rectangle getBottom() {
		return new Rectangle((int) x + 6 + offset, (int) y + sprite.getHeight() - 4, sprite.getWidth() - 6, 4);
	}

	/**
	 * Get a rectangle to manage the right collision
	 * 
	 * @return the rectangle that surround only the right side of the Tile
	 */
	public Rectangle getRight() {
		return new Rectangle((int) x + sprite.getWidth() - 4 + offset, (int) y + 6, 4, sprite.getHeight() - 6);
	}

	/**
	 * Get a rectangle to manage the left collision
	 * 
	 * @return the rectangle that surround only the left side of the Tile
	 */
	public Rectangle getLeft() {
		return new Rectangle((int) x + offset, (int) y + 6, 4, sprite.getHeight() - 6);
	}

	/**
	 * Allows to change the position of the Tile according to the given x and y
	 * Bitmap coordinates
	 * 
	 * @param x
	 * @param y
	 */
	public void setLocation(int x, int y) {
		if (sprite != null) {
			this.x = x * BitMap.TILE_WIDTH;
			this.y = y * BitMap.TILE_HEIGHT;
		}
	}

	/**
	 * Allows to set the offset for rendering Tiles and calculated the right
	 * collisions
	 * 
	 * @param offset
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

}
