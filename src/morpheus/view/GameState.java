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
		
		ParallaxLayer back = new ParallaxLayer(new Texture("res/forsebuono.png"), (int) ((16 * 0.25) * -0.15));
		//Posso inserire infinite parallassi
		//ParallaxLayer mid = new ParallaxLayer(new Texture("res/cloud210.png"), (int) ((16 * 0.25) * -0.25));
		//ParallaxLayer mid = new ParallaxLayer(new Texture("res/cazzobuco.jpeg"), (int) ((16 * 0.25) * -0.25));
		ParallaxLayer front = new ParallaxLayer(new Texture("res/cloud210.png"), (int) ((16 * 0.25) * -0.5));
		this.parallaxEngine = new ParallaxEngine(back, /*mid,*/ front);
		this.camera = new Camera(0, 0);
		this.tiles = new ArrayList<>();
		this.entities = new ArrayList<>();
		this.player = new Player(100, 100, this);
		//E' il valore delle x dove si posizioneranno gli oggetti
		double x = 0;
		//E' il valore delle y dove si posizioneranno gli oggetti
		double y = Morpheus.HEIGHT - 35;
		for (int i = 0; i < 15; i++) {
			
			//Sto riprendendo sempre la stessa immagine
			tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("res/ground2.png"), 64), 5, 8)));
			//NON TOCCARE IL TERRENO SOPRA, PRIMA DI MODIFICARE SALVARE QUELLO COM'E FATTO ORA
			//Sto modificando solo le x e infatti le y rimangono uguali :)
			x += 64;
		}
		//Sto aggiungendo una volta sola nella posizione 300, 150 la texture grande 64 nella posizione 1,1
		//1,1 è il posto come in una matrice(C'e il calcolo nella sprite)
		tiles.add(new Tile(400, 400, new Sprite(new SpriteSheet(new Texture("res/ground2.png"), 64), 5, 8)));
		tiles.add(new Tile(600, 400, new Sprite(new SpriteSheet(new Texture("res/ground2.png"), 64), 5, 8)));
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
