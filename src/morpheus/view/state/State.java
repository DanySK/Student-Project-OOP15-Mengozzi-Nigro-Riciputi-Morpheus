package morpheus.view.state;

import java.awt.Graphics2D;

import morpheus.controller.AudioPlayer;

/**
 * 		
 * Interfaccia generale di tutte le schermate con i metodi necessari per il funzionamento di base
 * 
 * @author Luca Mengozzi
 * 		 
 */
public interface State {

	/**
	 * 		
	 * Metodo eseguito una sola volta durante l'inizializzazione
	 * 
	 * @author Luca Mengozzi
	 * 		 
	 */
	public void init();

	/**
	 * 		
	 * Metodo eseguito ogni volta che lo state viene selezionato
	 * 
	 * @author Luca Mengozzi
	 * 		 
	 */
	public void enter(StateManager stateManager);

	/**
	 * 		
	 * Metodo eseguito continuamente riguardante la parte logica(tick)
	 * 
	 * @author Luca Mengozzi
	 * 		 
	 */
	public void tick(StateManager stateManager);

	/**
	 * 		
	 * Metodo eseguito continuamente riguardante la parte grafica(render)
	 * 
	 * @author Luca Mengozzi
	 * 		 
	 */
	public void render(Graphics2D g);
	
	/**
	 * 		
	 * Metodo eseguito all'uscita dello state
	 * 
	 * @author Luca Mengozzi
	 * 		 
	 */
	public void exit();

	/**
	 * 		
	 * Metodo che restituisce il nome dello state selezionato
	 * 
	 * @author Luca Mengozzi
	 * 		 
	 */
	public String getName();

	/**
	 * 		
	 * Metodo che ritorna l'audio dello state, in questo modo ogni schermata 
	 * potrà avere una parte dedicata a se
	 * 
	 * @author Luca Mengozzi
	 * 		 
	 */
	public AudioPlayer getMusic();
}

