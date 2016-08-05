 package morpheus.controller;

import java.util.ArrayList;
import java.util.List;

import morpheus.Morpheus;
import morpheus.view.RandomTile;

public class BitMap {
	// Semplicemente le dimensioni delle Tile (DEVONO ESSERE QUESTE, ALTRIMENTI � CAMBIARE TUTTO)
	public static double TILE_WIDTH = 64;
	public static double TILE_HEIGHT = 64;
	// La BitMap � composta da 4 mappe che si susseguono tra loro e ripetendosi formano un paesaggio infinito,
	// ho scelto 4 mappe per fare in modo che ci sia una maggiore randomicita del sistema e
	// soprattutto perch� per fare in modo che il mondo di gioco sia 'infinito' dovevo creare un sorta di coda di mappe appunto
	private int map[];
	private int map1[];
	private int map2[];
	private int map3[];
	// Esprimono in numeri le dimensioni degli array relativamente alla dimensione che vogliamo dare alle mappe
	private int width;
	private int height;
	// Array di RandomTile che servono per poter avere un contenitore per il valore di ritorno dell funzioni build() 
	// ed inviare il tutto al GameState
	List<RandomTile> tiles = new ArrayList<>();
	List<RandomTile> tiles1 = new ArrayList<>();
	List<RandomTile> tiles2 = new ArrayList<>();
	List<RandomTile> tiles3 = new ArrayList<>();
	

	public void init() {
		// Per calcolare la dimensione degli array basta dividere le dimensioni dello schermo 
		// (assumendo che le mappe siano grandi quanto lo schermo stesso) e dividerle per le dimensioni delle Tile.
		// Ho aggiunto un offset alla larghezza perche� ho assunto che le mappe sarebbero state larghe il 50% in pi� dello schermo
		// per far si che fosse possibile inserire in quello spazio in pi� il punto in cui cambiare
		// posizione alle mappe stesse in modo che non sia visibile a schermo tale operazione
		width = (int) Math.ceil((Morpheus.WIDTH + 400) / TILE_WIDTH);
		height = (int) Math.ceil(Morpheus.HEIGHT / TILE_HEIGHT);
		// riempimento delle mappe: notare come gli zeri equivalgano a delle non-Tile (ossia degli spazi vuoti) e gli 1 invece indichino le posizioni delle Tile.
		// l�inizializzazione delle mappe � formattata in questo modo e non su un unica riga non tanto per risparmiare spazio quanto per facilitare l�inserimento dei valori, 
		// infatti si pu� notare come le righe e le colonne siano lo stesso numero del valore delle variabili width e height e quindi tale formattazione rispecchia esattamente 
		//ci� che verr� visionato a schermo. Consiglio di mantenere questa formattazione sempre.(19 colonne e 8 righe)
		 map = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				  		 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				  		 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				  		 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				  		 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				  		 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				  		 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0,
				  		 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,};
		
		map1 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
				  		 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				  		 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				  		 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				  		 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				  		 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				  		 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0,
				  		 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		
		map2 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				  		 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				  		 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				  		 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				  		 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				  		 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				  		 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				  		 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,};
		
		map3 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		  		 		 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		  		 		 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		  		 		 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		  		 		 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		  		 		 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		  		 		 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		  		 		 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,};
	}
	// Metodi che servono per convertire i valori degli Array soprastanti in Tile da renderizzare a schermo
	public List<RandomTile> build() {
		for (int i = 0; i < map.length; i++) {
			// � un po' difficile da spiegare a parole comunque numera prima le righe e poi le colonne
			// e quei numeri saranno le coordinate in cui verr� renderizzata la tale Tile nella BitMap
			int x = i % width;
			int y = i / width;
			// Crea la la Tile
			RandomTile tile = new RandomTile(map[i]);
			// Imposta la posizione della Tile secondo il calcolo precedente
			tile.setLocation(x, y);
			// Questo controllo serve per evitare che di un NullPointerException 
			// nel caso in cui il valore della BitMap sia 0
			if (tile.getSprite() != null) {
				// Aggiunge la Tile alla lista
				tiles.add(tile);
			}
		}
		// Ritorna la lista
		return tiles;
	}
	// I metodi successivi fanno la stessa cosa ma per le altre 3 BitMap
	public List<RandomTile> build1() {
		for (int i = 0; i < map1.length; i++) {
			int x = i % width;
			int y = i / width;
			RandomTile tile = new RandomTile(map1[i]);
			tile.setLocation(x, y);
			if (tile.getSprite() != null) {
				tiles1.add(tile);
			}
		}
		return tiles1;
	}
	
	public List<RandomTile> build2() {
		for (int i = 0; i < map2.length; i++) {
			int x = i % width;
			int y = i / width;
			RandomTile tile = new RandomTile(map2[i]);
			tile.setLocation(x, y);
			if (tile.getSprite() != null) {
				tiles2.add(tile);
			}
		}
		return tiles2;
	}
	
	public List<RandomTile> build3() {
		for (int i = 0; i < map3.length; i++) {
			int x = i % width;
			int y = i / width;
			RandomTile tile = new RandomTile(map3[i]);
			tile.setLocation(x, y);
			if (tile.getSprite() != null) {
				tiles3.add(tile);
			}
		}
		return tiles3;
	}

}
