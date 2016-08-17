package morpheus;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import morpheus.controller.KeyInput;
import morpheus.controller.MouseInput;
import morpheus.model.Model;
import morpheus.model.ModelImpl;
import morpheus.view.state.DeathState;
import morpheus.view.state.GameState;
import morpheus.view.state.MenuState;
import morpheus.view.state.RankedState;
import morpheus.view.state.SettingsState;
import morpheus.view.state.StateManager;

/*
 * PREAMBOLO SULLA STRUTTURA GENERALE(INFO)
 * Nel loop principale del gioco si parla di TICKS e FRAMES, la differenza e minima ma sostanziale;
 * sono due unitá di misura che misurano cose diverse (come metro e litro per capirci).
 * I FRAMES misurano quante volte viene aggiornato a livello GRAFICO il gioco (rendering) al secondo,
 * i TICKS misurano invece quante volte viene aggiornato a livello LOGICO il gioco.
 * Per livello GRAFICO si intende tutto ció che compare a schermo e deve quindi essere renderizzato;
 * per livello LOGICO si intende invece tutto ció che deve essere processato fisicamente (input dei comandi, collisioni, gravitá... ecc).
 * In alcuni casi si potrebbe separare la parte LOGICA(tick) e la parte GRAFICA(frame) in due Thread e loop distinti e quindi non avere
 * per forza una quantita di ticks uguale a quella dei frames, ma in questo caso siccome vogliamo lockare(scelta mia che si puó modificare) 
 * il frame-rate a 60 possiamo unire tutto in un singolo Thread e sincronizzare meglio ticks e frames.
 * P.S. É possibile suddividere in piú Thread distinti anche cose come per esempio gli input da mouse e tastiera, ma considerando
 * le dimensioni ridotte del progetto conviene utilizzare un solo Thread, poi per la questione dell`audio avevo sinceramente pensato
 * ad un Thread separato ma é ancora troppo presto per concretizzare l`idea. Vedremo. 
 */

/**
 * . Classe principale con gestione tramite Thread
 */
public class Morpheus extends Canvas implements Runnable {

	//Generato numero seriale
	private static final long serialVersionUID = 4143231894678455397L;
	
	/**
	 * Frame di gioco principale
	 */
	private final JFrame frame;
	
	/**
	 * Titolo del Frame di gioco principale
	 */
	private static final String TITLE = "MORPHEUS";
	
	/**
	 * Larghezza del Frame di gioco principale
	 */
	public static final int WIDTH = 800;
	
	/**
	 * Altezza del Frame di gioco principale
	 */
	public static final int HEIGHT = 500;

	// Costante (di semplice utility) che indica il numero di millisecondi in un
	// secondo
	private static final int MS_IN_A_SECONDS = 1000;
	
	// Utilizzo di volatile per evitare problemi di caching sul valore
	private volatile boolean running;
	
	//Da eliminare se non serve
	public static boolean DEBUG = false;
	
	// numero frame e tick al secondo che vogliamo ottenere
	private static final double TARGET = 60.0;
	
	// numero di ns utilizzati per eseguire un singolo tick
	private static final double NS_PER_TICK = 1000000000.0 / TARGET;
	
	// Indica la quantitá di tick che non é stata ancora processata e ci da un
	// metro di quando dovremo fare il prossimo tick
	private double unprocessed = 0.0;
	
	// Tiene conto dei frame al secondo
	private int fps = 0;
	
	// Tiene conto dei tick per secondo
	private int tps = 0;
	
	// Serve per indicare il momento in cui é possibile renderizzare la scena
	private boolean canRender = false;

	//Dichiaro lo stateManager della classe view
	private StateManager stateManager;
	
	//Creo un oggetto di tipo model
	private Model model = new ModelImpl();
	
	//Costruttore che inizializza il Frame principale
	public Morpheus(){
		
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

		///////////// FRAME PRINCIPALE/////////////
		frame = new JFrame(TITLE);
		frame.getContentPane().add(this);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		// Fa in modo che si possa fare focus sul frame
		frame.setFocusable(true);
		// Ferma il Thread se si chiude dalla x della finestra
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				
				System.err.println("Exiting game");
				stop();
			}
		});
		// Fa comparire la finestra al centro dello schermo
	
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		start();
	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		// Probabilmente non sará impostata di default una bufferstrategy
		// inizialmente quindi se é cosí la creiamo noi
		if (bs == null) {
			// Il numero 3 sta ad indicare che sará presente un triplo buffer
			// che risulta essere il mix perfetto tra prestazioni e fluiditá
			// delle animazioni
			createBufferStrategy(3);
			return;
		}
		// Setta la BufferStrategy sulla variabile Graphics che poi useremo per
		// renderizzare tutto
		Graphics g = bs.getDrawGraphics();
		// Questo é solo un esempio (DEBUG) per capire se tutto funziona
		// correttamente
		Graphics2D g2D = (Graphics2D) g;

		g.translate(0, -30);//Mettere -30

		// /////////////////////////////////////////////////////////////////
		stateManager.render(g2D);
		// ////////////////////////////////////////////////////////////////

		// Rilascia ogni risorsa utilizzata fino ad ora e prepara il buffer per
		// accogliere nuove risorse
		g.dispose();
		// Mostra ció che é stato processato
		bs.show();
	}

	private void tick() {
		// Qui andrá inserita tutta la parte logica proveniente per lo piú dal
		// controller
		stateManager.tick();
		//menustate.tick(stateManager);
	}

	/**
	 * . Metodo che fa partire il Thread
	 */
	public void start() {
		// Per evitare che il Thread venga avviato due voote consecutive
		if (running) {
			return;
		}
		running = true;
		new Thread(this).start();
	}

	/**
	 * . Metodo che ferma il Thread
	 */
	public void stop() {
		// Per evitare chiusure a vuoto
		if (!running) {
			return;
		}
		running = false;

	}

	@Override
	public void run() {
		// Diretta conseguenza del metodo setFocusable() e permette di mantenere
		// il focus non solo sul frame ma sull'intero gioco
		requestFocus();
		// Giusto per debugging e per verificare che il Thread parta
		// correttamente
		System.out.println("Running");
		// Mi da il tempo dell`ultimo tick processato e mi serve per calcolare
		// quanto tempo é passato dall`ultimo tick
		long lastTime = System.nanoTime();
		// TIMER serve solo per poter visualizzare i frames e i ticks al secondo
		// nella console
		long timer = System.currentTimeMillis();
		while (running) {
			// Mi da il tempo del tick corrente
			long now = System.nanoTime();
			// (now - lastTime) mi indica quanto tempo é passato dall`ultimo
			// tick che diviso per NSPERTICK (durata di un singolo tick) ci da
			// numero di ticks non effettuati e quindi non ancora processati in
			// quel lasso di tempo
			unprocessed += (now - lastTime) / NS_PER_TICK;
			// Aggiorna la variabile lastTime settando il tempo del tick
			// corrente come ultimo tick effettuato per poter ricalcolare poi i
			// ticks non processati al loop successivo
			lastTime = now;
			// Se ci sono ticks non ancora processati provvedo
			if (unprocessed >= 1.0) {
				// Fase di processing dei ticks
				tick();
				// Aggiornamento dei tasti della tastiera premuti e non
				KeyInput.update();
				// Aggiornamento dei tasti del mouse premuti e non oltre al
				// tracking della posizione dello stesso
				MouseInput.update();
				// Scaliamo i ticks che processiamo
				unprocessed--;
				// Manteniamo il calcolo dei ticks al secondo processati
				tps++;
				// Una volta processato il tick possiamo renderizzzare tutto
				canRender = true;
			} else {
				// Serve per evitare che al loop successivo canRender sia giá
				// settata su true e quindi renderizzi in maniera non
				// sincronizzata con i ticks e continui a renderizzare
				// totalmente
				// a caso
				canRender = false;
			}

			// Serve per aggiungere un minimo di delay tra il processing dei
			// ticks e il rendering vero e proprio onde evitare un passaggio
			// troppo brusco
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Qui inizia il rendering della scena
			if (canRender) {
				// Fase di rendering
				render();
				// Manteniamo aggiornato a il frame-rate
				fps++;
			}
			// La condizione serve per far si che la visualizzazione su console
			// dei tps e dei fps sia sincronizzata con il processing degli
			// stessi e non mostri valori al di fuori del range preposto come
			// target di 60; il motivo per cui il tempo corrente si diminuisce
			// di un secondo é semplicemente per evitare di mostrare la prima
			// stampa dove fps e tps sono a 0
			if (System.currentTimeMillis() - MS_IN_A_SECONDS > timer) {
				// Aggiorniamo il timer iniziale ogni volta di un secondo per
				// evitare che il tempo corrente superi quello nella variabile
				// timer in modo da mantenere un certo ritmo
				timer += MS_IN_A_SECONDS;
				System.out.println("FPS: " + fps + " | TPS: " + tps);
				// Resettiamo sia gli fps sia i tps per evitare che superino il
				// valore del nostro target (60); a livello di performance non
				// cambierebbe nulla ma ci serve per visualizzarli correntamente
				fps = 0;
				tps = 0;
			}

		}
		System.exit(0);
	}

	/**
	 * . Metodo Main
	 * 
	 * @param args
	 *            .
	 */
	public static void main(final String[] args){
		
		new Morpheus();
	}
}
