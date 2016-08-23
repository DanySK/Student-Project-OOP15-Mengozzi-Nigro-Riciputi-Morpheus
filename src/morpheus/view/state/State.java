package morpheus.view.state;

import java.awt.Graphics2D;

import morpheus.controller.AudioPlayer;

/**
 * 		
 * General interface of all screens with the methods necessary for the basic functioning
 * 
 * @author Luca Mengozzi
 * 		 
 */
public interface State {

	/**
	 * 		
	 * Method performed only once during initialization
	 * 
	 * @author Luca Mengozzi
	 * 		 
	 */
	public void init();

	/**
	 * 		
	 * Method performed whenever the state is selected
	 * 
	 * @author Luca Mengozzi
	 * 		 
	 */
	public void enter(StateManager stateManager);

	/**
	 * 		
	 * Method continuously performed on the logical part (tick)
	 * 
	 * @author Luca Mengozzi
	 * 		 
	 */
	public void tick(StateManager stateManager);

	/**
	 * 		
	 * Method continuously performed on the graphical part (render)
	 * 
	 * @author Luca Mengozzi
	 * 		 
	 */
	public void render(Graphics2D g);
	
	/**
	 * 		
	 * A method performed at the exit of the state
	 * 
	 * @author Luca Mengozzi
	 * 		 
	 */
	public void exit();

	/**
	 * 		
	 * Method that returns the name of the state
	 * 
	 * @author Luca Mengozzi
	 * 		 
	 */
	public String getName();

	/**
	 * 		
	 * Method that returns the audio of the state, in this way each viewport 
	 * may have a part dedicated to itself
	 * 
	 * @author Luca Mengozzi
	 * 		 
	 */
	public AudioPlayer getMusic();
}

