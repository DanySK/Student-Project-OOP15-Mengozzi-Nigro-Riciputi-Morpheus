package morpheus.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * . Permette la mappatura degli input provenienti dalla tastiera
 */
public class KeyInput extends KeyAdapter {
    // Array di boolean che permette di verificare i tasti premuti (256 scelta
    // arbitraria per poter comprendere il maggior numero di tasti possibili)
    private static final int NUM_KEYS = 256;
    private static final boolean[] KEYS = new boolean[NUM_KEYS];
    // Ulteriore Array di boolean che permette di tenere traccia dei tasti
    // precedentemente premuti(e quindi gia rilasciati)
    private static final boolean[] LAST_KEYS = new boolean[NUM_KEYS];

    // Dichiara quando un tasto é stato premuto settando a TRUE la rispettiva
    // cella nell`array
    @Override
    public void keyPressed(final KeyEvent e) {
        KEYS[e.getKeyCode()] = true;
    }

    // Dichiara quando un tasto é stato premuto settando a FALSE la rispettiva
    // cella nell�array
    @Override
    public void keyReleased(final KeyEvent e) {
        KEYS[e.getKeyCode()] = false;
    }

    /**
     * . Aggiorna l`Array dei tasti premuti in precedenza in modo da
     * memorizzarli sempre ad ogni tick del loop
     */
    public static void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            LAST_KEYS[i] = KEYS[i];
        }
    }

    /**
     * . Permette di eseguire l`azione in maniera continua durante la pressione
     * del relativo tasto
     * 
     * @param keyCode
     *            Chiave relativa al tasto premuto
     * @return true se lo si sta premendo, false se non lo si sta premendo
     */
    public static boolean isDown(final int keyCode) {
        return KEYS[keyCode];
    }

    /**
     * . Permette di eseguire l`azione in maniera continua durante la fase in
     * cui il realtivo tasto non e premuto
     * 
     * @param keyCode
     *            Chiave relativa al ttasto premuto
     * @return true se non lo si sta premendo, false se lo si sta premendo
     */
    public static boolean isUP(final int keyCode) {
        return !KEYS[keyCode];
    }

    /**
     * . Permette di eseguire l`azione solo al momento della pressione del
     * relativo tasto
     * 
     * @param keyCode
     *            Chiave relativa al tasto premuto
     * @return true se lo si sta premendo e prima non lo si é premuto, false nel
     *         caso contrario
     */
    public static boolean isPressed(final int keyCode) {
        return isDown(keyCode) && !LAST_KEYS[keyCode];
    }

    /**
     * . Permette di eseguire l`azione solo al momento del rilascio del relativo
     * tasto
     * 
     * @param keyCode
     *            Chiave relativa al tasto premuto
     * @return true se non lo si sta premendo e prima lo si é premuto
     */
    public static boolean isRealeased(final int keyCode) {
        return !isDown(keyCode) && LAST_KEYS[keyCode];
    }

}
