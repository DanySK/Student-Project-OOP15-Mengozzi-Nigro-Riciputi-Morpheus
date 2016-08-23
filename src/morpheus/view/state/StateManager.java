package morpheus.view.state;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

/**
 * 		
 * Class for the management of the viewport, about the adding and the switching of them.
 * It also allows you to have only one method respectively tick and render the main class
 * 
 * @author Luca Mengozzi
 * 		 
 */
public class StateManager{
	
	/**
	 * 
	 * Map used for keep the viewport in memory
	 * 
	 * @author Luca Mengozzi
	 */
	private Map<String, State> map;
	
	/**
	 * 
	 * Variabil used for identify the corrent viewport
	 * 
	 * @author Luca Mengozzi
	 */
	private State currentState;

	/**
	 * 
	 * Costructor that can initialize the map
	 * 
	 * @author Luca Mengozzi
	 */
	public StateManager(){
		
		map = new HashMap<String, State>();
	}

	/**
	 * 
	 * Add a viewport e it makes available
	 * 
	 * @author Luca Mengozzi
	 */
	public void addState(State state){
		
		map.put(state.getName().toUpperCase(), state);
		state.init();
		
		if (currentState == null){
			
			state.enter(this);
			currentState = state;
		}
	}

	/**
	 * 
	 * Set like current state a selected viewport
	 * 
	 * @author Luca Mengozzi
	 */
	public void setState(String name){
		
		State state = map.get(name.toUpperCase());
		if (state == null){
			
			System.err.println("State <" + name + "> does not exist!");
			return;
		}
		
		currentState.exit();
		state.enter(this);
		currentState = state;
	}

	/**
	 * 
	 * Set like currentState a selected viewport
	 * 
	 * @author Luca Mengozzi
	 */
	public State getState(String name){
		
		return map.get(name);
	}
	
	/**
	 * 
	 * Classic tick method
	 * 
	 * @author Luca Mengozzi
	 */
	public void tick(){
		
		currentState.tick(this);
	}

	/**
	 * 
	 * Classic render method
	 * 
	 * @author Luca Mengozzi
	 */
	public void render(Graphics2D g){
		
		currentState.render(g);
	}
}
