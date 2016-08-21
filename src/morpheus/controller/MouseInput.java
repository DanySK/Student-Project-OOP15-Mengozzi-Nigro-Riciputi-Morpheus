package morpheus.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * . Permette la mappatura dei tasti provenienti dal mouse
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
     * . Aggiorna l`Array dei tasti premuti in precedenza in modo da
     * memorizzarli sempre ad ogni tick del loop
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
     * . Permette di eseguire l`azione in maniera continua durante la pressione
     * del relativo tasto
     * 
     * @param button
     *            Chiave relativa al tasto premuto
     * @return true se lo si sta premendo, false se non lo si sta premendo
     */
    public static boolean isDown(final int button) {
        return BUTTONS[button];
    }

    /**
     * . Permette di eseguire l`azione in maniera continua durante la fase in
     * cui il realtivo tasto non e premuto
     * 
     * @param button
     *            Chiave relativa al tasto premuto
     * @return true se non lo si sta premendo, false se lo si sta premendo
     */
    public static boolean isUp(final int button) {
        return BUTTONS[button];
    }

    /**
     * . Permette di eseguire l`azione solo al momento della pressione del
     * relativo tasto
     * 
     * @param button
     *            Chiave relativa al tasto premuto
     * @return true se lo si sta premendo e prima non lo si e premuto, false nel
     *         caso contrario
     */
    public static boolean isPressed(final int button) {
        return isDown(button) && !LAST_BUTTONS[button];
    }

    /**
     * . Permette di eseguire l`azione solo al momento del rilascio del relativo
     * tasto
     * 
     * @param button
     *            Chiave relativa al tasto premuto
     * @return true se non lo si sta premendo e prima lo si é premuto, false nel
     *         caso contrario
     */
    public static boolean isReleased(final int button) {
        return !isDown(button) && LAST_BUTTONS[button];
    }

    /**
     * . Getter della variabile X relativa alla posizione del cursore sullo
     * schermo
     * 
     * @return la coordinata X della posizione del cursore
     */
    public static int getX() {
        return X;
    }

    /**
     * . Getter della variabile Y relativa alla posizione del cursore sullo
     * schermo
     * 
     * @return la coordinata Y della posizione del cursore
     */
    public static int getY() {
        return Y;
    }

    /**
     * . Permette di capire se il cursore si sta muovendo o no
     * 
     * @return true se si sta muovendo, false se é fermo
     */
    public static boolean isMoving() {
        return moving;
    }

    /**
     * . Permette di capire se si sta Draggando(azione di premere un tasto e
     * muovere il cursore) con il mouse
     * 
     * @param button
     *            tasto che si vuole premere per draggare
     * @return true se lo si sta muovendo e si sta premendo il realtivo tasto,
     *         false nel caso contrario
     */
    public static boolean isDragging(final int button) {
        return isMoving() && isDown(button);
    }

}
