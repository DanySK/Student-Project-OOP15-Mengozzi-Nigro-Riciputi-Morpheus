package morpheus.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import morpheus.Morpheus;
import morpheus.view.state.GameState;

/**
 * Set the Tile position in scene
 * 
 * @author matteo
 *
 */
public class BitMap {
	public static double TILE_WIDTH = 64;
	public static double TILE_HEIGHT = 64;

	private boolean first;
	private GameState state;

	private int width;
	private List<RandomTile> tiles = new ArrayList<>();
	private List<RandomTile> tiles1 = new ArrayList<>();
	private List<RandomTile> tiles2 = new ArrayList<>();
	private List<RandomTile> tiles3 = new ArrayList<>();
	private Random random;

	public BitMap(GameState state) {
		this.state = state;
	}

	/**
	 * Initialize the class
	 */
	public void init() {
		this.width = (int) Math.ceil((Morpheus.WIDTH + 400) / TILE_WIDTH);
		this.first = true;
		this.random = new Random();
		TileMaps.init();
	}

	/**
	 * Build the list of tiles from the first map
	 * 
	 * @return List of Tiles
	 */
	public List<RandomTile> build(int offset) {
		int[] randMap;
		if (first) {
			randMap = TileMaps.getFirstMap();
		} else {
			randMap = getRandomTileMap();
		}
		for (int i = 0; i < randMap.length; i++) {
			int x = i % width;
			int y = i / width;
			RandomTile tile = new RandomTile(randMap[i]);
			tile.setLocation(x, y);
			if (randMap[i] != 0 && randMap[i] != 1 && randMap[i] != 2) {
				Asset asset = new Asset();
				asset.load(randMap[i], state);
				asset.setLocation(x, y, offset);
			}
			if (tile.getSprite() != null) {
				tiles.add(tile);
			}
		}
		first = false;
		return tiles;
	}

	/**
	 * Build the list of tiles from the second map
	 * 
	 * @return List of Tiles
	 */
	public List<RandomTile> build1(int offset) {
		int[] randMap = getRandomTileMap();
		for (int i = 0; i < randMap.length; i++) {
			int x = i % width;
			int y = i / width;
			RandomTile tile = new RandomTile(randMap[i]);
			tile.setLocation(x, y);
			if (randMap[i] != 0 && randMap[i] != 1 && randMap[i] != 2) {
				Asset asset = new Asset();
				asset.load(randMap[i], state);
				asset.setLocation(x, y, offset);
			}
			if (tile.getSprite() != null) {
				tiles1.add(tile);
			}
		}
		return tiles1;
	}

	/**
	 * Build the list of tiles from the third map
	 * 
	 * @return List of Tiles
	 */
	public List<RandomTile> build2(int offset) {
		int[] randMap = getRandomTileMap();
		for (int i = 0; i < randMap.length; i++) {
			int x = i % width;
			int y = i / width;
			RandomTile tile = new RandomTile(randMap[i]);
			tile.setLocation(x, y);
			if (randMap[i] != 0 && randMap[i] != 1 && randMap[i] != 2) {
				Asset asset = new Asset();
				asset.load(randMap[i], state);
				asset.setLocation(x, y, offset);
			}
			if (tile.getSprite() != null) {
				tiles2.add(tile);
			}
		}
		return tiles2;
	}

	/**
	 * Build the list of tiles from the fourth map
	 * 
	 * @return List of Tiles
	 */
	public List<RandomTile> build3(int offset) {
		int[] randMap = getRandomTileMap();
		for (int i = 0; i < randMap.length; i++) {
			int x = i % width;
			int y = i / width;
			RandomTile tile = new RandomTile(randMap[i]);
			tile.setLocation(x, y);
			if (randMap[i] != 0 && randMap[i] != 1 && randMap[i] != 2) {
				Asset asset = new Asset();
				asset.load(randMap[i], state);
				asset.setLocation(x, y, offset);
			}
			if (tile.getSprite() != null) {
				tiles3.add(tile);
			}
		}
		return tiles3;
	}

	/**
	 * Get a random TileMap from the static class TileMaps
	 * 
	 * @return Array of integer
	 */
	public int[] getRandomTileMap() {
		int mapID = random.nextInt(10);
		System.out.println(mapID);

		switch (mapID) {
		case 0:
			return TileMaps.getMAP();
		case 1:
			return TileMaps.getMAP1();
		case 2:
			return TileMaps.getMAP2();
		case 3:
			return TileMaps.getMAP3();
		case 4:
			return TileMaps.getMAP4();
		case 5:
			return TileMaps.getMAP5();
		case 6:
			return TileMaps.getMAP6();
		case 7:
			return TileMaps.getMAP7();
		case 8:
			return TileMaps.getMAP8();
		case 9:
			return TileMaps.getMAP9();
		case 10:
			return TileMaps.getMAP10();

		}
		return null;

	}

}