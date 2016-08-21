package morpheus.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * . Manage the keys from the keyboard
 */
public class KeyInput extends KeyAdapter {
	private static final int NUM_KEYS = 256;
	private static final boolean[] KEYS = new boolean[NUM_KEYS];
	private static final boolean[] LAST_KEYS = new boolean[NUM_KEYS];

	@Override
	public void keyPressed(final KeyEvent e) {
		KEYS[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(final KeyEvent e) {
		KEYS[e.getKeyCode()] = false;
	}

	/**
	 * . Update the key pressed array in a way that the class knows which key
	 * was pressed last time
	 */
	public static void update() {
		for (int i = 0; i < NUM_KEYS; i++) {
			LAST_KEYS[i] = KEYS[i];
		}
	}

	/**
	 * .Manage when the user keep pressing a key
	 * 
	 * @param keyCode
	 * @return true if pressing, false if not pressing
	 */
	public static boolean isDown(final int keyCode) {
		return KEYS[keyCode];
	}

	/**
	 * Manage when the user stop pressing a key that was continuously pressed
	 * for an amount of time
	 * 
	 * @param keyCode
	 * @return true if not pressing, false if pressing
	 */
	public static boolean isUP(final int keyCode) {
		return !KEYS[keyCode];
	}

	/**
	 * . Allows to manage the precise moment of the act of pressing the key
	 * 
	 * @param keyCode
	 * @return true if pressing and before not pressed, false if not pressing
	 *         and before pressed
	 */
	public static boolean isPressed(final int keyCode) {
		return isDown(keyCode) && !LAST_KEYS[keyCode];
	}

	/**
	 * Allows to manage the precise moment of the act of releasing the key
	 * 
	 * @param keyCode
	 * @return true if not pressing and before pressed, false if pressing and
	 *         before not pressed
	 */
	public static boolean isRealeased(final int keyCode) {
		return !isDown(keyCode) && LAST_KEYS[keyCode];
	}

}
