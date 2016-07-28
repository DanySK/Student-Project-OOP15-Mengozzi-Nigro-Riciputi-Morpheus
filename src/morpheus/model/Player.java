package morpheus.model;

import java.awt.event.KeyEvent;

import morpheus.controller.KeyInput;
import morpheus.view.Animation;
import morpheus.view.GameState;
import morpheus.view.Sprite;
import morpheus.view.SpriteSheet;
import morpheus.view.Texture;

public class Player extends Mob {

	// Per modificare la velocit� di animazione basta modificare il primo
	// parametro di new Animation (pi� alto sar� il numero pi� lenta
	// andr�
	// l�animazione)
	public Player(double x, double y, GameState state) {
		super(new Sprite(new SpriteSheet(new Texture("res/sayan.png"), 55/* largh */, 80/* alt */), 1, 2), x, y, state,
				new Animation(5, new Sprite(new SpriteSheet(new Texture("res/sayan.png"), 55, 80), 1, 1),
						new Sprite(new SpriteSheet(new Texture("res/sayan.png"), 55, 80), 2, 1),
						new Sprite(new SpriteSheet(new Texture("res/sayan.png"), 55, 80), 3, 1),
						new Sprite(new SpriteSheet(new Texture("res/sayan.png"), 55, 80), 4, 1)));

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
