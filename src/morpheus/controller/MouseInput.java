package morpheus.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Manage the Mouse buttons
 */
public class MouseInput extends MouseAdapter {
	// Stessa strategia di KeyInput
	private static final int NUM_BUTTONS = 10;
	private static final boolean[] BUTTONS = new boolean[NUM_BUTTONS];
	private static final boolean[] LAST_BUTTONS = new boolean[NUM_BUTTONS];
	// Posizione corrente
	private static int X = -1, Y = -1;
	// Posizione nel precedente tick
	private static int lastX = X, lastY = Y;
	private static boolean moving;

	@Override
	public void mousePressed(final MouseEvent e) {
		BUTTONS[e.getButton()] = true;
	}

	@Override
	public void mouseReleased(final MouseEvent e) {
		BUTTONS[e.getButton()] = false;
	}

	@Override
	public void mouseMoved(final MouseEvent e) {
		// Tengo tracciata la posizione del mouse nello schermo
		X = e.getX();
		Y = e.getY();
		// se le coordinate presenti con quelle passate non combaciano allora il
		// mouse si sta muovendo(il motivo dell�utilizzo di || � che sia
		// possibile anche muoversi su due assi contemporaneamente)
		moving = true;
		

	}

	/**
	 * . Update the button pressed array in a way that the class knows which
	 * button was pressed last time
	 */
	public static void update() {
		for (int i = 0; i < NUM_BUTTONS; i++) {
			LAST_BUTTONS[i] = BUTTONS[i];
		}
		// Controlliamo se il mouse � fermo e se lo � lo segnaliamo
		if (X == lastX && Y == lastY) {
			moving = false;
		}
		// Aggiorno la posizione precedente
		lastX = X;
		lastY = Y;
	}

	/**
	 * .Manage when the user keep pressing a button
	 * 
	 * @param keyCode
	 * @return true if pressing, false if not pressing
	 */
	public static boolean isDown(final int button) {
		return BUTTONS[button];
	}

	/**
	 * Manage when the user stop pressing a button that was continuously pressed
	 * for an amount of time
	 * 
	 * @param keyCode
	 * @return true if not pressing, false if pressing
	 */
	public static boolean isUp(final int button) {
		return BUTTONS[button];
	}

	/**
	 * . Allows to manage the precise moment of the act of pressing the button
	 * 
	 * @param keyCode
	 * @return true if pressing and before not pressed, false if not pressing
	 *         and before pressed
	 */
	public static boolean isPressed(final int button) {
		return isDown(button) && !LAST_BUTTONS[button];
	}

	/**
	 * Allows to manage the precise moment of the act of releasing the button
	 * 
	 * @param keyCode
	 * @return true if not pressing and before pressed, false if pressing and
	 *         before not pressed
	 */
	public static boolean isReleased(final int button) {
		return !isDown(button) && LAST_BUTTONS[button];
	}

	/**
	 * Get the value of the x position of the mouse cursor
	 * 
	 * @return x coordinate of the cursor
	 */
	public static int getX() {
		return X;
	}

	/**
	 * Get the value of the y position of the mouse cursor
	 * 
	 * @return y coordinate of the cursor
	 */
	public static int getY() {
		return Y;
	}

	/**
	 * . Allows to know if the cursor is moving or not
	 * 
	 * @return true if it is moving, false if it is not
	 */
	public static boolean isMoving() {
		return moving;
	}

	/**
	 * Allows to know if the user is dragging
	 * 
	 * @param button
	 * @return true if the user is dragging, false if not
	 */
	public static boolean isDragging(final int button) {
		return isMoving() && isDown(button);
	}

}
