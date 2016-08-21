package morpheus.controller;

import morpheus.model.AbstractDrawable;
import morpheus.model.ModelImpl;
import morpheus.view.state.GameState;

/**
 * Manage all the Assets of the game but not Tiles
 * 
 * @author matteo
 *
 */
public class Asset {
	private double x;
	private double y;
	private ModelImpl model;
	private AbstractDrawable asset;

	/**
	 * Main Constructor
	 */
	public Asset() {
		this.x = 0;
		this.y = 0;
		this.model = new ModelImpl();
		this.asset = null;
	}

	/**
	 * Method that load the wright kind of asset according to the iD passed by
	 * parameter
	 * 
	 * @param IdEnemy
	 * @param state
	 */
	public void load(int IdEnemy, GameState state) {

		switch (IdEnemy) {
		case 3:
			asset = model.getGhost(x, y, state);
			break;
		case 4:
			asset = model.getPenguin(x, y, state);
			break;
		case 5:
			asset = model.getTree(x, y, state);
			break;
		case 6:
			asset = model.getNormalCoin(x, y, state);
			break;
		case 7:
			asset = model.getDoubleCoin(x, y, state);
			break;
		case 8:
			asset = model.getSpecialCoin(x, y, state);
			break;
		case 9:
			asset = model.getRedPill(x, y, state);
			break;
		case 10:
			asset = model.getBluePill(x, y, state);
			break;
		case 11:
			asset = model.getSpikes(x, y, state);
			break;

		}
	}

	/**
	 * Change the location of the asset converting the bitMap values in pixel
	 * coordinates
	 * 
	 * @param x
	 * @param y
	 * @param offset
	 *            .Necessary cause we have to move the TileMaps during the game
	 */
	public void setLocation(int x, int y, int offset) {
		asset.setX(x * BitMap.TILE_WIDTH + offset);
		asset.setY(y * BitMap.TILE_HEIGHT);

	}

}
