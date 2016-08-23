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
	 * Exact point where the background starts to render relatively to the dimension of the viewport
	 * 
	 * @author matteo
	 * 
	 */
	private static int LINK = Morpheus.WIDTH - 5;

	/**
	 * 
	 * Exact point where the first background image end
	 * @author matteo
	 * 
	 */
	private static int FIRSTEDGE = 304;

	/**
	 * 
	 * Exact point where second background image end
	 * @author matteo
	 * 
	 */
	private static int SECONDEDGE = 1304;

	/**
	 * 
	 * Point where the first three maps are rendered
	 * @author matteo
	 * 
	 */
	private static int DISTANCE1 = 4000;

	/**
	 * 
	 * Point where the last map is rendered
	 * @author matteo
	 * 
	 */
	private static int DISTANCE2 = 5600;

	/**
	 * 
	 * Value where the position of the randomTile will be incremented to translate from a position to another in space and give the feeling of infinite
	 * @author matteo
	 * 
	 */
	private static int MOVEINCR = 4800;

	/**
	 * 
	 * Offset value of the position of RandomTiles and their collisions
	 * 
	 * @author matteo
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
	private int parallaxMove1 = 0;
	private int parallaxMove2 = 795;
	private int parallaxCloud1 = 0;
	private int parallaxCloud2 = 795;
	private int Offset1 = 0;
	private int Offset2 = 0;
	private int check = 1;
	private int point = 0;
	private int point1 = 1200;
	private int point2 = 2400;
	private int point3 = 3600;
	private long speedX1 = 100;
	private long speedX2 = 100;
	private BitMap bitMap;
	
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
	private AudioPlayer BGMusic;
	private CameraOperator operator;

	private int points = 0;
	public static int score = 0;
	public static int bonus = 0;
	private GraphicNumbers num = new GraphicNumbers();
	private GraphicLifes life = new GraphicLifes();
	private GraphicBullet bullet = new GraphicBullet();

	@Override
	public void init() {

		BGMusic = new AudioPlayer("res/BGMusic.wav");
	}

	@Override
	public void enter(StateManager stateManager) {

		points = 0;
		bonus = 0;
		
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
		
		randomTiles.addAll(bitMap.build(point));
		randomTiles1.addAll(bitMap.build1(point1));
		randomTiles2.addAll(bitMap.build2(point2));
		randomTiles3.addAll(bitMap.build3(point3));
		
		allRandomTiles.addAll(randomTiles);
		allRandomTiles.addAll(randomTiles1);
		allRandomTiles.addAll(randomTiles2);
		allRandomTiles.addAll(randomTiles3);

		operator = new CameraOperator(0, 0, this, player);
		
		BGMusic.setVolume(model.getVolume());
		BGMusic.playAndLoop();
	}

	@Override
	public void render(Graphics2D g) {

		this.renderBG(g);
		g.translate(camera.getX(), camera.getY());
		this.renderWorld(g);
		for (AbstractDrawable e : entities) {
			e.render(g);
		}
		g.translate(-camera.getX(), -camera.getY());
		
		if (model.isMainPlayerOpen()) {

			life.render(g, model.getMainPlayer().getItem().getHP());
		} else {

			life.render(g, model.getSidePlayer().getItem().getHP());
		}
		
		if (model.isMainPlayerOpen()) {

			bullet.render(g, model.getMainPlayer().getItem().getBullet());
		} else {
			bullet.render(g, model.getSidePlayer().getItem().getBullet());
		}

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

		points++;
		if (model.isMainPlayerOpen()) {

			score = (points / 100) + bonus;
		} else {

			score = (points / 100) + bonus;
		}
	}

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
	
	/**
	 * Render all the parallax levels of the background
	 * @param g
	 * @author matteo
	 */
	public void renderBG(Graphics2D g) {

		if ((speedX1 - FIRSTEDGE) % (background.getWidth() * 2) == 0) {

			parallaxMove1 = 0;
		}
		if ((speedX1 - SECONDEDGE) % (background.getWidth() * 2) == 0) {

			parallaxMove2 = 0;
		}
		g.drawImage(background, LINK - parallaxMove2, 0, null);
		
		if (speedX1 > FIRSTEDGE) {

			g.drawImage(background, LINK - parallaxMove1, 0, null);
		}

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
	/**
	 * Render the whole assets of the world
	 * @param g
	 * @author matteo
	 */
	public void renderWorld(Graphics2D g) {

		if (player.getTileSynch() != MOVEINCR * check) {

			if ((player.getTileSynch() - (DISTANCE1 + Offset1)) % DISTANCE1 == 0) {

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
		
		if ((player.getTileSynch() - (DISTANCE2 - Offset2)) % DISTANCE2 == 0) {

			point3 += MOVEINCR;
			
			check++;
			
			Offset2 += OFFSETINCR;

			allRandomTiles.removeAll(randomTiles3);

			append3.addAll(randomTiles3);

			randomTiles3.removeAll(randomTiles3);

			randomTiles3.addAll(bitMap.build3(point3));

			randomTiles3.removeAll(append3);

			allRandomTiles.addAll(randomTiles3);
		}
		
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
}
