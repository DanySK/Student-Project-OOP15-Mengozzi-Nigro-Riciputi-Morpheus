package morpheus.view;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

// Classe per la gestione delle schermate per quanto riguarda l'aggiunta e lo switch
// delle stesse; inoltre permette di avere un solo metodo rispettivamente per tick e render nella main class
public class StateManager{
	
	// Si utilizza una mappa per mantenere in memoria le schermate
	private Map<String, State> map;
	
	//Variabile per identificare la schermata corrente
	private State currentState;

	//Costruttore in cui viene inizializzata la mappa
	public StateManager(){
		
		map = new HashMap<String, State>();
	}

	//Metodo per aggiungere una schermata e renderla disponibile
	public void addState(State state){
		
		//Inserisce nella mappa il nome della schermata creata
		map.put(state.getName().toUpperCase(), state);//Magari togliere toUpperCase
		
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

	//Metodo per settare come currentState una precisa schermata
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

	//Classico metodo tick
	public void tick(){
		
		currentState.tick(this);
	}

	//Classico metodo render
	public void render(Graphics2D g){
		
		currentState.render(g);
	}
}
