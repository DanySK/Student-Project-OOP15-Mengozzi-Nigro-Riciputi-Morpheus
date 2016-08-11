package morpheus.view.state;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import morpheus.controller.AudioPlayer;
import morpheus.controller.KeyInput;
import morpheus.controller.MouseInput;
import morpheus.model.Animation;
import morpheus.view.Button;
import morpheus.view.Sprite;
import morpheus.view.SpriteSheet;
import morpheus.view.Texture;

/**
 * 		
 * @author Luca Mengozzi
 * 		 
 */
public class MenuState implements State{
	
	/**
	 * 		
	 * Variabile globale indicante quale personaggio si vuole utilizzare,
	 * se è true viene selezionato il biondo
	 * 
	 * @author Luca Mengozzi
	 * 		 
	 */
	public static boolean DEFAULT_ANIMATION = true;
	//Da implementare con anche nessuna musica
	private AudioPlayer BGMusic = new AudioPlayer("res/BGMusic.wav");;
	private Texture background;
	private Animation player1;
	private Animation player2;
	private Button[] options;
	private int currentSelection;
	private static final int HITBOX_OFFSET = 15;

	@Override
	public void init() {
		
		background = new Texture("res/matrix_blu.jpg");
		player1 = new Animation(5, new Sprite(new SpriteSheet(new Texture("res/sayan2.png"), 83, 120), 4, 1, 4).getFramesAsList());
		player2 = new Animation(5, new Sprite(new SpriteSheet(new Texture("res/violet2.png"), 83, 120), 4, 1, 4).getFramesAsList());
		
		options = new Button[4];
		
		//Imposto i bottoni
		options[0] = new Button(175, "res/play_bianco.png", "res/play_nero.png");
		options[1] = new Button(225, "res/ranking_bianco.png", "res/ranking_nero.png");
		options[2] = new Button(275, "res/settings_bianco.png", "res/settings_nero.png");
		options[3] = new Button(325, "res/exit_bianco.png", "res/exit_nero.png");
	}

	@Override
	public void enter(StateManager stateManager) {
		
	}

	@Override
	public void tick(StateManager stateManager){
		
		if (KeyInput.isPressed(KeyEvent.VK_UP) || (KeyInput.isPressed(KeyEvent.VK_W))) {
			
			currentSelection--;
			if (currentSelection < 0) {
				currentSelection = options.length - 1;
			}
		}

		if (KeyInput.isPressed(KeyEvent.VK_DOWN) || (KeyInput.isPressed(KeyEvent.VK_S))) {
			
			currentSelection++;
			if (currentSelection > options.length - 1) {
				currentSelection = 0;
			}
		}
		// Utilizzo mouse per navigazione menu, va per forza sotto la
		// configurazione da tastiera per dare precedenza al mouse e non
		// permettere ai tasti di funzionare quando il mouse sta sopra un
		// opzione del menu
		boolean clicked = false;
		for (int i = 0; i < options.length; i++) {
			
			if (options[i].intersects(
					new Rectangle(MouseInput.getX() + HITBOX_OFFSET, MouseInput.getY() + HITBOX_OFFSET, 1, 1))) {
				
				currentSelection = i;
				clicked = MouseInput.isPressed(MouseEvent.BUTTON1);
			}
		}

		if (clicked || KeyInput.isPressed(KeyEvent.VK_ENTER)) {
			
			select(stateManager);
		}
		//Tick dell'animazione
		if (DEFAULT_ANIMATION){
			
			player1.run();
		}
		else{
			
			player2.run();
		}
	}
	
	@Override
	public void render(Graphics2D g) {
		
		background.render(g, 0, 0);
		if (DEFAULT_ANIMATION){
			
			player1.render(g, 500, 175);
		}
		else{
			
			player2.render(g, 500, 175);
		}
		
		//Renderizzo il bottone selezionato
		for (int i=0; i<options.length; i++) {
			
			if (i == currentSelection) {
				
				options[i].setSelected(true);
			} else {
				
				options[i].setSelected(false);
			}
			options[i].render(g);
		}
	}

	@Override
	public void exit() {
		
	}
	
	@Override
	public String getName() {
		
		return "Menu";
	}
	
	private void select(StateManager stateManager) {
		
		switch (currentSelection) {
		
		case 0:
			
			stateManager.setState("Game");
			break;
		case 1:
			
			stateManager.setState("Ranked");
			break;
		case 2:
			
			stateManager.setState("Settings");
			break;
		case 3:
			
			System.exit(0);
			break;
		}
	}

	@Override
	public AudioPlayer getMusic() {
		
		return BGMusic;
	}
}
