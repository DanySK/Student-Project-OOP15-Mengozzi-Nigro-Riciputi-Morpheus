package morpheus.model;

import java.awt.event.KeyEvent;

import morpheus.controller.KeyInput;
import morpheus.view.Animation;
import morpheus.view.Sprite;
import morpheus.view.SpriteSheet;
import morpheus.view.Texture;
import morpheus.view.GameState;

public class Player extends Mob {
	// Per modificare la velocit� di animaaione basta modificare il primo
	// parametro di new Animation (pi� alto sar� il numero pi� lenta andr�
	// l�animazione)
	public Player(double x, double y, GameState state) {
		super(new Sprite(new SpriteSheet(new Texture("res/player_sheet.png"), 64), 1, 1), x, y, state,
				new Animation(5, new Sprite(new SpriteSheet(new Texture("res/player_sheet.png"), 64), 1, 1),
						new Sprite(new SpriteSheet(new Texture("res/player_sheet.png"), 64), 2, 1),
						new Sprite(new SpriteSheet(new Texture("res/player_sheet.png"), 64), 3, 1),
						new Sprite(new SpriteSheet(new Texture("res/player_sheet.png"), 64), 4, 1),
						new Sprite(new SpriteSheet(new Texture("res/player_sheet.png"), 64), 1, 2),
						new Sprite(new SpriteSheet(new Texture("res/player_sheet.png"), 64), 2, 2)));
	}

	@Override
	public void tick() {
		if (KeyInput.isDown(KeyEvent.VK_W)) {
			jump(10);
		}
		/*
		 * if (KeyInput.isDown(KeyEvent.VK_S)) { dY = 2; }
		 */
		if (KeyInput.isDown(KeyEvent.VK_A)) {
			dX = -2;
		}
		if (KeyInput.isDown(KeyEvent.VK_D)) {
			dX = 2;
		}

		/*
		 * if(KeyInput.isRealeased(KeyEvent.VK_W) ||
		 * KeyInput.isRealeased(KeyEvent.VK_S)) { dY = 0; }
		 */
		if (KeyInput.isRealeased(KeyEvent.VK_A) || KeyInput.isRealeased(KeyEvent.VK_D)) {
			dX = 0;
		}
		super.tick();
	}

}
