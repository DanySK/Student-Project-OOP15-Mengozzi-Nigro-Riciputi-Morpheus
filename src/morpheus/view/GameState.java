package morpheus.view;

import java.awt.Graphics2D;
import java.util.ArrayList;

import morpheus.Morpheus;
import morpheus.model.Entity;
import morpheus.model.Player;
import morpheus.view.ParallaxEngine;
import morpheus.view.ParallaxLayer;
import morpheus.view.Sprite;
import morpheus.view.SpriteSheet;
import morpheus.view.Texture;
import morpheus.view.Camera;
import morpheus.view.Tile;

public class GameState implements State {

	private ArrayList<Tile> tiles;
	private ArrayList<Entity> entities;
	private Camera camera;
	private Player player;
	private ParallaxEngine parallaxEngine;

	@Override
	public void init() {
		
		ParallaxLayer back = new ParallaxLayer(new Texture("res/sfondo.png"), (int) ((16 * 0.25) * -0.15));
		this.parallaxEngine = new ParallaxEngine(back);
		this.camera = new Camera(0, 0);
		this.tiles = new ArrayList<>();
		this.entities = new ArrayList<>();
		this.player = new Player(100, 100, this);
		double x = 0;
		double y = Morpheus.HEIGHT - 100;
		for (int i = 0; i < 10; i++) {
			
			tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("res/Terrain.png"), 64), 1, 1)));
			x += 64;
		}
		tiles.add(new Tile(300, 150, new Sprite(new SpriteSheet(new Texture("res/Terrain.png"), 64), 1, 1)));
	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g) {
		
		g.translate(camera.getX(), camera.getY());
		parallaxEngine.render(g);
		for (Entity e : entities) {
			e.render(g);
		}

		for (Tile t : tiles) {
			t.render(g);
		}

		g.translate(-camera.getX(), -camera.getY());
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		
		return "Game";
	}

	@Override
	public void tick(StateManager stateManager) {
	
		if (player.isMoving()) {
			parallaxEngine.move();
		}

		if (player.isMovingLeft()) {
			parallaxEngine.setLeft();
		}
		if (player.isMovingRight()) {
			parallaxEngine.setRight();
		}

		for (Entity e : entities) {
			e.tick();
		}
		camera.tick(player);
	}
	
	public ArrayList<Tile> getTiles() {
		
		return tiles;
	}

	public void addEntity(Entity entity) {
		
		entities.add(entity);
	}
}
