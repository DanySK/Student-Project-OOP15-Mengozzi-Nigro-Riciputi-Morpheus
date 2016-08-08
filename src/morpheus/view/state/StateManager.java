package morpheus.view.state;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

/**
 * 		
 * Classe per la gestione delle schermate per quanto riguarda l'aggiunta e lo switch delle stesse 
 * inoltre permette di avere un solo metodo rispettivamente per tick e render nella main class
 * 
 * @author Luca Mengozzi
 * 		 
 */
public class StateManager{
	
	/**
	 * 
	 * Mappa utlizzata per mantenere in memoria le schermate
	 * 
	 * @author Luca Mengozzi
	 */
	private Map<String, State> map;
	
	/**
	 * 
	 * Variabile utlizzata per identificare la schermata corrente
	 * 
	 * @author Luca Mengozzi
	 */
	private State currentState;

	/**
	 * 
	 * Costruttore con cui viene inizializzata la mappa
	 * 
	 * @author Luca Mengozzi
	 */
	public StateManager(){
		
		map = new HashMap<String, State>();
	}

	/**
	 * 
	 * Aggiunge una schermata e la rende disponibile
	 * 
	 * @author Luca Mengozzi
	 */
	public void addState(State state){
		
		//Inserisce nella mappa il nome della schermata creata
		map.put(state.getName().toUpperCase(), state);
		
		//Inizializza la schermata (se necessario)
		state.init();
		
		//Se non è impostata nessuna schermata corrente
		if (currentState == null){
			
			//Si entra direttamente in quella
			state.enter();
			
			//E si setta come corrente
			currentState = state;
		}
	}

	/**
	 * 
	 * Setta come currentState una precisa schermata
	 * 
	 * @author Luca Mengozzi
	 */
	public void setState(String name){
		
		State state = map.get(name.toUpperCase());//Togliere toUpperCase
		if (state == null){
			
			System.err.println("State <" + name + "> does not exist!");
			return;
		}
		
		//Esce dalla schermata vecchia che risulta ancora la corrente
		currentState.exit();
		
		//Entra nella schermata successiva
		state.enter();
		
		//Setta come schermata corrente quella in cui si sta entrando
		currentState = state;
	}

	/**
	 * 
	 * Classico metodo tick
	 * 
	 * @author Luca Mengozzi
	 */
	public void tick(){
		
		currentState.tick(this);
	}

	/**
	 * 
	 * Classico metodo render
	 * 
	 * @author Luca Mengozzi
	 */
	public void render(Graphics2D g){
		
		currentState.render(g);
	}
}
