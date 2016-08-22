package morpheus.view.state;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.imageio.ImageIO;

import morpheus.Morpheus;
import morpheus.controller.AudioPlayer;
import morpheus.controller.BitMap;
import morpheus.controller.Camera;
import morpheus.controller.Collision;
import morpheus.controller.RandomTile;
import morpheus.model.AbstractDrawable;
import morpheus.model.CameraOperator;
import morpheus.model.Model;
import morpheus.model.ModelImpl;
import morpheus.model.Player;
import morpheus.view.GraphicBullet;
import morpheus.view.GraphicLifes;
import morpheus.view.GraphicNumbers;

/**
 * 
 * @author Luca Mengozzi
 * 
 */
public class GameState implements State {

	/**
	 * 
	 * Punto esatto in cui il background comincia si comincia a renderizzare
	 * relativamente alla dimensione della finestra di gioco
	 * 
	 * @author Luca Mengozzi
	 * 
	 */
	private static int LINK = Morpheus.WIDTH - 5;

	/**
	 * 
	 * Punto esatto in cui termina la prima immagine di background
	 * 
	 * @author Luca Mengozzi
	 * 
	 */
	private static int FIRSTEDGE = 304;

	/**
	 * 
	 * Punto esatto in cui termina la seconda immagine di background
	 * 
	 * @author Luca Mengozzi
	 * 
	 */
	private static int SECONDEDGE = 1304;

	/**
	 * 
	 * Distanza a cui verranno renderizzate le prime 3 BitMap
	 * 
	 * @author Luca Mengozzi
	 * 
	 */
	private static int DISTANCE1 = 4000;

	/**
	 * 
	 * Distaza a cui verrà renderizzata la terza BitMap
	 * 
	 * @author Luca Mengozzi
	 * 
	 */
	private static int DISTANCE2 = 5600;

	/**
	 * 
	 * Valore di cui la posizione delle RandomTile verrà incrementata per poter
	 * traslare da una posizione all'altra nello spazio e dare l'impressione di
	 * infinito
	 * 
	 * @author Luca Mengozzi
	 * 
	 */
	private static int MOVEINCR = 4800;

	/**
	 * 
	 * Valore incrementativo dell�offset della posizione dell RandomTile e delle
	 * loro collisioni
	 * 
	 * @author Luca Mengozzi
	 * 
	 */
	private static int OFFSETINCR = 800;

	private ArrayList<AbstractDrawable> entities;
	private ArrayList<AbstractDrawable> appEntities;
	private Camera camera;
	private Player player;
	private BufferedImage background;
	private BufferedImage background2;
	private Model model = new ModelImpl();
	// Variabili per la gestione dello scrolling dei background che permettono
	// allo sfondo effetivamente di muoversi
	private int parallaxMove1 = 0;
	private int parallaxMove2 = 795;
	private int parallaxCloud1 = 0;
	private int parallaxCloud2 = 795;
	// Servono per mantenere aggiornate le coordinate delle collisioni rispetto
	// alla posizione delle Tile all'interno dell'algoritmo procedurale
	private int Offset1 = 0;
	private int Offset2 = 0;
	// Serve per la verifica della condizione che scandisce la successione di
	// ogni BitMap successiva all'altra
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
	// Si crea una BitMap per costruire la mappa di gioco
	private BitMap bitMap;
	// Ho utilizzato 4 Array di RandomTile e non uno solo per cercare di rendere
	// il
	// più randomico possibile il sistema e per fare si che sia possibile una
	// proceduralità delle BitMap stesse(sistema randomico non ancora
	// implementato)
	private ArrayList<RandomTile> randomTiles;
	private ArrayList<RandomTile> randomTiles1;
	private ArrayList<RandomTile> randomTiles2;
	private ArrayList<RandomTile> randomTiles3;

	private ArrayList<RandomTile> append;
	private ArrayList<RandomTile> append1;
	private ArrayList<RandomTile> append2;
	private ArrayList<RandomTile> append3;

	private ArrayList<RandomTile> allRandomTiles;
	private Collision coll;
	AudioPlayer BGMusic;
	private CameraOperator operator;

	//Variabile che conterrà i punti secondo la difficoltà scelta
	private int points = 0;
	//Variabile globale che indicherà lo score
	public static int score;
	//Variabile globale che indicherà il bonus
	public static int bonus;
	private GraphicNumbers num = new GraphicNumbers();
	private GraphicLifes life = new GraphicLifes();
	private GraphicBullet bullet = new GraphicBullet();

	@Override
	public void init() {

		// Carico la canzone
		BGMusic = new AudioPlayer("res/BGMusic.wav");
	}

	@Override
	public void enter(StateManager stateManager) {

		randomTiles = new ArrayList<>();
		randomTiles1 = new ArrayList<>();
		randomTiles2 = new ArrayList<>();
		randomTiles3 = new ArrayList<>();

		append = new ArrayList<>();
		append1 = new ArrayList<>();
		append2 = new ArrayList<>();
		append3 = new ArrayList<>();

		allRandomTiles = new ArrayList<>();
		bitMap = new BitMap(this);
		// Inizializzo le BitMap
		bitMap.init();

		try {

			background = ImageIO.read(new File("res/ultimo.png"));
			background2 = ImageIO.read(new File("res/nuvole_buone.png"));
		} catch (IOException e) {

			System.out.println("Failed to load background image");
		}

		this.camera = new Camera(0, 0);
		this.entities = new ArrayList<>();
		appEntities = new ArrayList<>();
		// Carico il personaggio
		if (model.isMainPlayerOpen()) {

			if (model.getMainPlayer() == null) {

				this.player = model.getMainPlayer(100, 100, this);
			} else {

				player.reset(100, 100, this);
			}
		} else {

			if (model.getSidePlayer() == null) {

				this.player = model.getSidePlayer(100, 100, this);
			} else {

				player.reset(100, 100, this);
			}
		}
		coll = new Collision(this, player);
		// Utilizzo il metodo build() delle BitMap per convertire i valori delle
		// BitMap in Tile da renderizzare nella scena e le aggiungo alla lista
		// di Tile da renderizzare nel metodo render()
		randomTiles.addAll(bitMap.build(point));
		randomTiles1.addAll(bitMap.build1(point1));
		randomTiles2.addAll(bitMap.build2(point2));
		randomTiles3.addAll(bitMap.build3(point3));
		// Successivamente aggiungo ongi lista di RandomTile ad una singola
		// grande lista per poterle poi prelevare tutte insieme e calcolarne le
		// collisioni nella classe Collision
		allRandomTiles.addAll(randomTiles);
		allRandomTiles.addAll(randomTiles1);
		allRandomTiles.addAll(randomTiles2);
		allRandomTiles.addAll(randomTiles3);

		operator = new CameraOperator(0, 0, this, player);
		// Imposto il volume
		BGMusic.setVolume(model.getVolume());
		BGMusic.playAndLoop();
	}

	@Override
	public void render(Graphics2D g) {

		// Lo sfondo è staccato sia dal movimento del giocatore sia dal
		// movimento di camera
		this.renderBG(g);

		g.translate(camera.getX(), camera.getY());
		// Renderizzo le RandomTile
		this.renderWorld(g);
		// Entità
		for (AbstractDrawable e : entities) {
			e.render(g);
		}
		g.translate(-camera.getX(), -camera.getY());
		// Renderizzo le vite
		if (model.isMainPlayerOpen()) {

			life.render(g, model.getMainPlayer().getItem().getHP());
		} else {

			life.render(g, model.getSidePlayer().getItem().getHP());
		}

		// Renderizzo i proiettili
		if (model.isMainPlayerOpen()) {

			bullet.render(g, model.getMainPlayer().getItem().getBullet());
		} else {
			bullet.render(g, model.getSidePlayer().getItem().getBullet());
		}

		// Renderizzo il punteggio
		num.render(g);
	}

	@Override
	public void exit() {

		parallaxMove1 = 0;
		parallaxMove2 = 795;
		parallaxCloud1 = 0;
		parallaxCloud2 = 795;

		Offset1 = 0;
		Offset2 = 0;

		check = 1;

		point = 0;
		point1 = 1200;
		point2 = 2400;
		point3 = 3600;

		speedX1 = 100;
		speedX2 = 100;

		points = 0;
		bonus = 0;
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

		for (ListIterator<AbstractDrawable> iter = entities.listIterator(); iter.hasNext();) {

			AbstractDrawable e = iter.next();
			if (e instanceof Player) {

				coll.tick();
			}
			e.tick();
			if (e.isRemove()) {
				iter.remove();
			}

			
		}

		camera.tick(operator);
		if (appEntities.size() != 0) {

			entities.addAll(appEntities);
			appEntities = new ArrayList<>();
		}

		if (model.isMainPlayerOpen()) {

			if (model.getMainPlayer().isDeath()) {

				stateManager.setState("Death");
			}
		} else {

			if (model.getSidePlayer().isDeath()) {

				stateManager.setState("Death");
			}
		}

		// Incremento in base al tempo visto che 
		// la telecamera si muove dipendentemente dal player

		points++;
		if (model.isMainPlayerOpen()) {

			score = points / 100 + bonus;
		} else {

			score = points / 100 + bonus;
		}
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

		appEntities.add(entity);
	}

	public ArrayList<AbstractDrawable> getEntities() {
		return entities;
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
		// // BACKGROUND 2 PER ORA COMMENTATO PER FARE LE PROVE DELLE VITE IN
		// ALTO
		// if ((speedX2 - FIRSTEDGE) % (background2.getWidth() * 2) == 0) {
		//
		// parallaxCloud1 = 0;
		// }
		// if ((speedX2 - SECONDEDGE) % (background2.getWidth() * 2) == 0) {
		//
		// parallaxCloud2 = 0;
		// }
		//
		// g.drawImage(background2, LINK - parallaxCloud2, 0, null);
		//
		// if (speedX2 > FIRSTEDGE) {
		//
		// g.drawImage(background2, LINK - parallaxCloud1, 0, null);
		// }
	}

	public void renderWorld(Graphics2D g) {

		// Condizioe
		if (player.getTileSynch() != MOVEINCR * check) {

			// Questa if decide che le bitmap vengono renderizzate ad una
			// distanza di 4000 l'una dall'altra, l'offset qui serve per fare in
			// modo che vengano renderizzate prima e non si veda nella scena la
			// loro effetiva comparsa
			if ((player.getTileSynch() - (DISTANCE1 + Offset1)) % DISTANCE1 == 0) {

				// Incremento le variabili per "spostare" le BitMap ogni 4000 +
				// offset punti
				point += MOVEINCR;
				point1 += MOVEINCR;
				point2 += MOVEINCR;
				Offset1 += OFFSETINCR;

				allRandomTiles.removeAll(randomTiles);
				allRandomTiles.removeAll(randomTiles1);
				allRandomTiles.removeAll(randomTiles2);

				append.addAll(randomTiles);
				append1.addAll(randomTiles1);
				append2.addAll(randomTiles2);

				randomTiles.removeAll(randomTiles);
				randomTiles1.removeAll(randomTiles1);
				randomTiles2.removeAll(randomTiles2);

				randomTiles.addAll(bitMap.build(point));
				randomTiles1.addAll(bitMap.build1(point1));
				randomTiles2.addAll(bitMap.build2(point2));

				randomTiles.removeAll(append);
				randomTiles1.removeAll(append1);
				randomTiles2.removeAll(append2);

				allRandomTiles.addAll(randomTiles);
				allRandomTiles.addAll(randomTiles1);
				allRandomTiles.addAll(randomTiles2);
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

			allRandomTiles.removeAll(randomTiles3);

			append3.addAll(randomTiles3);

			randomTiles3.removeAll(randomTiles3);

			randomTiles3.addAll(bitMap.build3(point3));

			randomTiles3.removeAll(append3);

			allRandomTiles.addAll(randomTiles3);
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

	@Override
	public AudioPlayer getMusic() {

		return BGMusic;
	}

	public static GameState getInstance() {
		return new GameState();
	}
}
