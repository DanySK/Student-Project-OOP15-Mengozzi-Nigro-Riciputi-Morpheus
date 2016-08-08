package morpheus.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import morpheus.Morpheus;
import morpheus.controller.AudioPlayer;
import morpheus.controller.BitMap;
import morpheus.controller.Camera;
import morpheus.controller.Collision;
import morpheus.model.AbstractDrawable;
import morpheus.model.MainPlayer;

public class GameState implements State {

	// Punto esatto in cui il background comincia si comincia a renderizzare
	// realtivamente alla dimensione della finestra di gioco
	private static int LINK = Morpheus.WIDTH - 5;
	// Punto esatto in cui termina la prima immagine di background
	private static int FIRSTEDGE = 304;
	// Punto esatto in cui termina la seconda immagine di background
	private static int SECONDEDGE = 1304;
	// Distanza a cui verranno renderizzate le prime 3 BitMap
	private static int DISTANCE1 = 4000;
	// Distaza a cui verr� renderizzata la terza BitMap
	private static int DISTANCE2 = 5600;
	// Valore di cui la posizione delle RandomTile verr� incrementata per poter
	// traslare da una posizione all�altra nello spazioe e dare l�impressione di
	// infinito
	private static int MOVEINCR = 4800;
	// Valore incrementativo dell�offset della posizione dell RandomTile e delle
	// loro collisioni
	private static int OFFSETINCR = 800;

	private ArrayList<Tile> tiles;
	private ArrayList<AbstractDrawable> entities;
	private Camera camera;
	private MainPlayer player;
	private BufferedImage background;
	private BufferedImage background2;
	// Variabili per la gestione dello scrolling dei background e permettono
	// allo sfondo effetivamente di muoversi
	private int parallaxMove1 = 0;
	private int parallaxMove2 = 795;
	private int parallaxCloud1 = 0;
	private int parallaxCloud2 = 795;
	// Serve per mantenere aggiornate le coordinate delle collisioni rispetto
	// alla posizione delle Tile all�interno dell�algoritmo procedurale
	private int Offset1 = 0;
	private int Offset2 = 0;
	// Serve per la verifica della condizione che scandisce la successione di
	// ogni BitMap successiva all�altra
	private int check = 1;
	// Indicano i punti esatti in cui le BitMap vengono renderizzate
	private int point = 0;
	private int point1 = 1200;
	private int point2 = 2400;
	private int point3 = 3600;
	// Il movimento dei background e' legato a queste due variabili che
	// scandiscono il tempo di rendering e partono dalla posizione iniziale del
	// giocatore
	private long speedX1 = 100;
	private long speedX2 = 100;
	// Creiamo una BitMap per costruire la mappa di gioco
	private BitMap bitMap;
	// Ho utilizzato 4 Array di RandomTile e non uno solo per cercare rendere il
	// pi� randomico possibile sistema e per fare si che sia possibile una
	// proceduralit� delle BitMap stesse(sistema randomico non ancora
	// implementato)
	private ArrayList<RandomTile> randomTiles;
	private ArrayList<RandomTile> randomTiles1;
	private ArrayList<RandomTile> randomTiles2;
	private ArrayList<RandomTile> randomTiles3;
	private ArrayList<RandomTile> allRandomTiles;
	private Collision coll;
	AudioPlayer BGMusic;

	@Override
	public void init() {
		randomTiles = new ArrayList<>();
		randomTiles1 = new ArrayList<>();
		randomTiles2 = new ArrayList<>();
		randomTiles3 = new ArrayList<>();
		allRandomTiles = new ArrayList<>();
		bitMap = new BitMap();
		// Inizializzo le BitMap
		bitMap.init();

		try {
			background = ImageIO.read(new File("res/ultimo.png"));// Ci sono
																	// anche
																	// wow.png e
																	// wow2.png
			background2 = ImageIO.read(new File("res/nuvole_buone.png"));
		} catch (IOException e) {
			System.out.println("Failed to load background image");
		}

		this.camera = new Camera(0, 0);
		this.tiles = new ArrayList<>();
		this.entities = new ArrayList<>();
		this.player = MainPlayer.getPlayer(150, 200, this);
		coll = new Collision(this);
		// Utilizzo il metodo build() delle BitMap per convertire i valori delle
		// BitMap in Tile da renderizzare nella scena e le aggiungo alla lista
		// di Tile da renderizzare nel metodo render()
		randomTiles.addAll(bitMap.build());
		randomTiles1.addAll(bitMap.build1());
		randomTiles2.addAll(bitMap.build2());
		randomTiles3.addAll(bitMap.build3());
		// Successivamente aggiungo ongi lista di RandomTile ad una singola
		// grande lista per poterle poi prelevare tutte insieme e calcolarne le
		// collisioni nella classe Mob
		allRandomTiles.addAll(randomTiles);
		allRandomTiles.addAll(randomTiles1);
		allRandomTiles.addAll(randomTiles2);
		allRandomTiles.addAll(randomTiles3);
		BGMusic = new AudioPlayer("res/BGMusic.wav");

	}

	@Override
	public void enter() {
		BGMusic.setVolume(0.50);
		BGMusic.playAndLoop();
	}

	@Override
	public void render(Graphics2D g) {
		// Lo sfondo e'staccato sia dal movimento del giocatore sia dal
		// movimento di camera
		this.renderBG(g);

		g.translate(camera.getX(), camera.getY());
		// ENTITA'
		for (AbstractDrawable e : entities) {
			e.render(g);
		}
		// Renderizzo le RandomTile
		this.renderWorld(g);

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

		for (AbstractDrawable e : entities) {
			if (e instanceof MainPlayer) {
				coll.tick();
			}
			e.tick();
		}
		camera.tick(player);
	}

	public ArrayList<Tile> getTiles() {

		return tiles;
	}

	// Creo dei getter sia per le singole liste di RadomTile sia per la lista
	// che le contiene tutte
	public ArrayList<RandomTile> getRandomTiles() {
		return randomTiles;
	}

	public ArrayList<RandomTile> getRandomTiles1() {
		return randomTiles1;
	}

	public ArrayList<RandomTile> getRandomTiles2() {
		return randomTiles2;
	}

	public ArrayList<RandomTile> getRandomTiles3() {
		return randomTiles3;
	}

	public ArrayList<RandomTile> getAllRandomTiles() {
		return allRandomTiles;
	}

	public void addEntity(AbstractDrawable entity) {

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

	public void renderWorld(Graphics2D g) {
		// Condizione per evitare che le BitMap si posizionino a dei valori
		// errati
		if (player.getTileSynch() != MOVEINCR * check) {
			// Questa if decide che le bitmap vengono renderizzate ad una
			// distanza di 4000 l�una dall�altra, l�offset qui serve per fare in
			// modo che vengano renderizzate prima e non si veda nella scena la
			// loro effetiva comparsa
			if ((player.getTileSynch() - (DISTANCE1 + Offset1)) % DISTANCE1 == 0) {
				// Incremento le variabili per "spostare" le BitMap ogni 4000 +
				// offset punti
				point += MOVEINCR;
				point1 += MOVEINCR;
				point2 += MOVEINCR;
				Offset1 += OFFSETINCR;
			}
		}
		// Faccio la stessa cosa con la terza BitMap che rimane separata dalle
		// altre perch� se le spostassi tutte insieme sparirebbe il terreno
		// sotto il giocatore non permettendogli di avanzare
		if ((player.getTileSynch() - (DISTANCE2 - Offset2)) % DISTANCE2 == 0) {
			point3 += MOVEINCR;
			// Incremento la variabile del check qui perch� il valore del
			// controllo in questione viene poco dopo i valori in cui verranno
			// renderizzate le prime tre BitMap e se lo incrementassi prima il
			// valore non sarebbe aggioranto per il check successivo sballando
			// il tutto
			check++;
			Offset2 += OFFSETINCR;
		}
		// Rendereizzo effettivamente tutte le RandomTile realativamente alla
		// posizione precedentemente calcolata
		for (RandomTile t : randomTiles) {
			t.render(g, point);
		}
		for (RandomTile t : randomTiles1) {
			t.render(g, point1);
		}

		for (RandomTile t : randomTiles2) {
			t.render(g, point2);
		}

		for (RandomTile t : randomTiles3) {
			t.render(g, point3);
		}
	}
}
