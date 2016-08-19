package morpheus.controller;

import morpheus.model.AbstractDrawable;
import morpheus.model.ModelImpl;
import morpheus.view.state.GameState;

public class Asset {
	private double x;
	private double y;
	private ModelImpl model;
	private AbstractDrawable asset;

	public Asset() {
		this.x = 0;
		this.y = 0;
		this.model = new ModelImpl();
		this.asset = null;
	}

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

	public void setLocation(int x, int y) {
		asset.setX(x * BitMap.TILE_WIDTH);
		asset.setY(y * BitMap.TILE_HEIGHT);
	}

}
