package morpheus.view.state;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import morpheus.controller.AudioPlayer;
import morpheus.controller.KeyInput;
import morpheus.controller.MouseInput;
import morpheus.model.Animation;
import morpheus.model.Model;
import morpheus.model.ModelImpl;
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
	
	private Texture background;
	private Animation player1;
	private Animation player2;
	private Button[] options;
	private int currentSelection;
	private static final int HITBOX_OFFSET = 15;
	private Model model = new ModelImpl();
	private AudioPlayer menuMusic;

	@Override
	public void init() {
		
		background = new Texture("/matrix_blu.jpg");
		player1 = new Animation(5, new Sprite(new SpriteSheet(new Texture("/sayan2.png"), 83, 120), 4, 1, 4).getFramesAsList());
		player2 = new Animation(5, new Sprite(new SpriteSheet(new Texture("/violet2.png"), 83, 120), 4, 1, 4).getFramesAsList());
		
		options = new Button[4];
		
		options[0] = new Button(175, "/play_bianco.png", "/play_nero.png");
		options[1] = new Button(225, "/ranking_bianco.png", "/ranking_nero.png");
		options[2] = new Button(275, "/settings_bianco.png", "/settings_nero.png");
		options[3] = new Button(325, "/exit_bianco.png", "/exit_nero.png");
		menuMusic = new AudioPlayer("/MenuMusic.wav");
	}

	@Override
	public void enter(StateManager stateManager) {
		
		menuMusic.setVolume(model.getVolume());
		menuMusic.playAndLoop();
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
		
		if (model.isMainPlayerOpen()){
			
			player1.run();
		}
		else{
			
			player2.run();
		}
	}
	
	@Override
	public void render(Graphics2D g) {
		
		background.render(g, 0, 0);
		if (model.isMainPlayerOpen()){
			
			player1.render(g, 500, 175);
		}
		else{
			
			player2.render(g, 500, 175);
		}
		
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
		
		menuMusic.stop();
	}
	
	@Override
	public String getName() {
		
		return "Menu";
	}
	
	/**
	 * 		
	 * Select the state from the state manager
	 * 
	 * @author Luca Mengozzi
	 * 		 
	 */
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
		
		return null;
	}
}
