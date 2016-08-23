package morpheus;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import morpheus.controller.KeyInput;
import morpheus.controller.MouseInput;
import morpheus.view.state.DeathState;
import morpheus.view.state.GameState;
import morpheus.view.state.MenuState;
import morpheus.view.state.RankedState;
import morpheus.view.state.SettingsState;
import morpheus.view.state.StateManager;

/**
 * Main class with thread management
 */
public class Morpheus extends Canvas implements Runnable {

    // Generato numero seriale
    private static final long serialVersionUID = 4143231894678455397L;

    /**
     * Main frame
     */
    private final JFrame frame;

    /**
     * Title of the main frame
     */
    private static final String TITLE = "MORPHEUS";

    /**
     * Width of the main frame
     */
    public static final int WIDTH = 800;

    /**
     * Heighet of the main frame
     */
    public static final int HEIGHT = 500;

    private static final int MS_IN_A_SECONDS = 1000;

    private volatile boolean running;

    private static final double TARGET = 60.0;

    private static final double NS_PER_TICK = 1000000000.0 / TARGET;

    private double unprocessed = 0.0;

    private int fps = 0;

    private int tps = 0;

    private boolean canRender = false;

    private StateManager stateManager;

    public Morpheus() {

        addKeyListener(new KeyInput());
        MouseInput mi = new MouseInput();
        addMouseListener(mi);
        addMouseMotionListener(mi);

        stateManager = new StateManager();
        stateManager.addState(new MenuState());
        stateManager.addState(new GameState());
        stateManager.addState(new RankedState());
        stateManager.addState(new SettingsState());
        stateManager.addState(new DeathState());

        frame = new JFrame(TITLE);
        final URL url = Morpheus.class.getResource("/morpheus.png");
        final ImageIcon img = new ImageIcon(url);
        frame.setIconImage(img.getImage());
        frame.getContentPane().add(this);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {

                System.err.println("Exiting game");
                stop();
            }
        });
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        start();
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2D = (Graphics2D) g;

        g.translate(0, -30);
        
        stateManager.render(g2D);
        
        g.dispose();
        
        bs.show();
    }

    private void tick() {
    	
        stateManager.tick();
    }

    /**
     * Start method
     */
    public void start() {
        if (running) {
            return;
        }
        running = true;
        new Thread(this).start();
    }

    /**
     * Stop method
     */
    public void stop() {
        if (!running) {
            return;
        }
        running = false;
    }

    @Override
    public void run() {
        requestFocus();
        System.out.println("Running");
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        while (running) {
            long now = System.nanoTime();
            unprocessed += (now - lastTime) / NS_PER_TICK;
            lastTime = now;
            if (unprocessed >= 1.0) {
                tick();
                KeyInput.update();
                MouseInput.update();
                unprocessed--;
                tps++;
                canRender = true;
            } else {
                canRender = false;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (canRender) {
                render();
                fps++;
            }
            if (System.currentTimeMillis() - MS_IN_A_SECONDS > timer) {
                timer += MS_IN_A_SECONDS;
                System.out.println("FPS: " + fps + " | TPS: " + tps);
                fps = 0;
                tps = 0;
            }
        }
        System.exit(0);
    }

    /**
     * Main method
     * 
     * @param args
     *            
     */
    public static void main(final String[] args) {

        new Morpheus();
    }
}
