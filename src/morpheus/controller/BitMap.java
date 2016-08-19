package morpheus.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import morpheus.Morpheus;
import morpheus.view.RandomTile;
import morpheus.view.state.GameState;

/**
 * Set the Tile position in scene
 * 
 * @author matteo
 *
 */
public class BitMap {
	// Semplicemente le dimensioni delle Tile (DEVONO ESSERE QUESTE, ALTRIMENTI
	// � CAMBIARE TUTTO)
	public static double TILE_WIDTH = 64;
	public static double TILE_HEIGHT = 64;

	private boolean first;
	private GameState state;

	// Esprimono in numeri le dimensioni degli array relativamente alla
	// dimensione che vogliamo dare alle mappe
	private int width;
	// Array di RandomTile che servono per poter avere un contenitore per il
	// valore di ritorno dell funzioni build()
	// ed inviare il tutto al GameState
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
		// Per calcolare la dimensione degli array basta dividere le dimensioni
		// dello schermo
		// (assumendo che le mappe siano grandi quanto lo schermo stesso) e
		// dividerle per le dimensioni delle Tile.
		// Ho aggiunto un offset alla larghezza perche� ho assunto che le mappe
		// sarebbero state larghe il 50% in pi� dello schermo
		// per far si che fosse possibile inserire in quello spazio in pi� il
		// punto in cui cambiare
		// posizione alle mappe stesse in modo che non sia visibile a schermo
		// tale operazione
		this.width = (int) Math.ceil((Morpheus.WIDTH + 400) / TILE_WIDTH);
		this.first = true;
		this.random = new Random();
		TileMaps.init();
	}

	// Metodi che servono per convertire i valori degli Array soprastanti in
	// Tile da renderizzare a schermo

	/**
	 * Build the list of tiles from the first map
	 * 
	 * @return List of Tiles
	 */
	public List<RandomTile> build() {
		int[] randMap;
		if (first) {
			randMap = TileMaps.getFirstMap();
		} else {
			randMap = getRandomTileMap();
		}
		for (int i = 0; i < randMap.length; i++) {
			// E' un po' difficile da spiegare a parole comunque numera prima le
			// righe e poi le colonne
			// e quei numeri saranno le coordinate in cui verrà renderizzata la
			// tale Tile nella BitMap
			int x = i % width;
			int y = i / width;
			// Crea la la Tile
			RandomTile tile = new RandomTile(randMap[i]);
			// Imposta la posizione della Tile secondo il calcolo precedente
			tile.setLocation(x, y);
			// Questo controllo serve per evitare che di un NullPointerException
			// nel caso in cui il valore della BitMap sia 0
			if (randMap[i] != 0 && randMap[i] != 1 && randMap[i] != 2) {
				Asset asset = new Asset();
				asset.load(randMap[i], state);
				asset.setLocation(x, y);
			}
			if (tile.getSprite() != null) {
				// Aggiunge la Tile alla lista
				tiles.add(tile);
			}
		}
		first = false;
		// Ritorna la lista
		return tiles;
	}

	// I metodi successivi fanno la stessa cosa ma per le altre 3 BitMap
	/**
	 * Build the list of tiles from the second map
	 * 
	 * @return List of Tiles
	 */
	public List<RandomTile> build1() {
		int[] randMap = getRandomTileMap();
		for (int i = 0; i < randMap.length; i++) {
			int x = i % width;
			int y = i / width;
			RandomTile tile = new RandomTile(randMap[i]);
			tile.setLocation(x, y);
			if (randMap[i] != 0 && randMap[i] != 1 && randMap[i] != 2) {
				Asset asset = new Asset();
				asset.load(randMap[i], state);
				asset.setLocation(x, y);
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
	public List<RandomTile> build2() {
		int[] randMap = getRandomTileMap();
		for (int i = 0; i < randMap.length; i++) {
			int x = i % width;
			int y = i / width;
			RandomTile tile = new RandomTile(randMap[i]);
			tile.setLocation(x, y);
			if (randMap[i] != 0 && randMap[i] != 1 && randMap[i] != 2) {
				Asset asset = new Asset();
				asset.load(randMap[i], state);
				asset.setLocation(x, y);
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
	public List<RandomTile> build3() {
		int[] randMap = getRandomTileMap();
		for (int i = 0; i < randMap.length; i++) {
			int x = i % width;
			int y = i / width;
			RandomTile tile = new RandomTile(randMap[i]);
			tile.setLocation(x, y);
			if (randMap[i] != 0 && randMap[i] != 1 && randMap[i] != 2) {
				Asset asset = new Asset();
				asset.load(randMap[i], state);
				asset.setLocation(x, y);
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