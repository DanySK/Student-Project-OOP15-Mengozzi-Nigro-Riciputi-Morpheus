package morpheus.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import morpheus.Morpheus;
import morpheus.model.Entity;
import morpheus.model.Player;

public class GameState implements State {

	// Punto esatto in cui il background comincia si comincia a renderizzare
	// realtivamente alla dimensione della finestra di gioco
	private static int LINK = Morpheus.WIDTH - 5;
	// Punto esatto in cui termina la prima immagine di background
	private static int FIRSTEDGE = 304;
	// Punto esatto in cui termina la seconda immagine di background
	private static int SECONDEDGE = 1304;

	private ArrayList<Tile> tiles;
	private ArrayList<Entity> entities;
	private Camera camera;
	private Player player;
	private BufferedImage background;
	private BufferedImage background2;
	// Variabili per la gestione dello scrolling dei background e permettono
	// allo sfondo effetivamente di muoversi
	public int parallaxMove1 = 0;
	public int parallaxMove2 = 795;
	public int parallaxCloud1 = 0;
	public int parallaxCloud2 = 795;
	// Il movimento dei background e' legato a queste due variabili che
	// scandiscono il tempo di rendering
	private int speedX1 = 100;
	private int speedX2 = 100;

	@Override
	public void init() {

		try {
			background = ImageIO.read(new File("res/forsebuono.png"));
			background2 = ImageIO.read(new File("res/nuvole_buone.png"));
		} catch (IOException e) {
			System.out.println("Failed to load background image");			
		}

		this.camera = new Camera(0, 0);
		this.tiles = new ArrayList<>();
		this.entities = new ArrayList<>();
		this.player = new Player(100, 100, this);
		// E' il valore delle x dove si posizioneranno gli oggetti
		double x = 0;
		// E' il valore delle y dove si posizioneranno gli oggetti
		double y = Morpheus.HEIGHT - 35;
		for (int i = 0; i < 1000; i++) {
			// Sto riprendendo sempre la stessa immagine
			tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("res/ground2.png"), 64), 5, 8)));
			// NON TOCCARE IL TERRENO SOPRA, PRIMA DI MODIFICARE SALVARE QUELLO
			// COM'E FATTO ORA
			// Sto modificando solo le x e infatti le y rimangono uguali :)
			x += 64;
		}
		// Sto aggiungendo una volta sola nella posizione 300, 150 la texture
		// grande 64 nella posizione 1,1
		// 1,1 è il posto come in una matrice(C'e il calcolo nella sprite)
		tiles.add(new Tile(400, 400, new Sprite(new SpriteSheet(new Texture("res/ground2.png"), 64), 5, 8)));
		tiles.add(new Tile(600, 400, new Sprite(new SpriteSheet(new Texture("res/ground2.png"), 64), 5, 8)));
	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics2D g) {
		// Lo sfondo e'staccato sia dal movimento del giocatore sia dal
		// movimento di camera
		this.renderBG(g);

		g.translate(camera.getX(), camera.getY());
		// ENTITA'
		for (Entity e : entities) {
			e.render(g);
		}
		// TILE
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

		parallaxMove1 += 2;
		parallaxMove2 += 2;
		speedX1 += 2;
		parallaxCloud1 += 1;
		parallaxCloud2 += 1;
		speedX2 += 1;


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

	public void renderBG(Graphics2D g) {
		// BACKGROUND 1
		// Questi due if statement fanno in modo che le variabili che settano lo
		// spostamento, e quindi anche la posizione iniziale di rendering, di
		// ciascuna immagine di background si azzeri ogni volta che due
		// background si sono susseguiti
		if ((speedX1 - FIRSTEDGE) % (background.getWidth() * 2) == 0) {
			parallaxMove1 = 0;
		}
		if ((speedX1 - SECONDEDGE) % (background.getWidth() * 2) == 0) {
			parallaxMove2 = 0;
		}
		// Renderizza il primo background
		g.drawImage(background, LINK - parallaxMove2, 0, null);
		// Solo una volta che il primo background e' terminato si renderizza il
		// secondo
		if (speedX1 > FIRSTEDGE) {
			g.drawImage(background, LINK - parallaxMove1, 0, null);
		}

		// BACKGROUND 2
		if ((speedX2 - FIRSTEDGE) % (background2.getWidth() * 2) == 0) {
			parallaxCloud1 = 0;
		}
		if ((speedX2 - SECONDEDGE) % (background2.getWidth() * 2) == 0) {
			parallaxCloud2 = 0;
		}

		g.drawImage(background2, LINK - parallaxCloud2, 0, null);

		if (speedX2 > FIRSTEDGE) {
			g.drawImage(background2, LINK - parallaxCloud1, 0, null);
		}
	}
}
