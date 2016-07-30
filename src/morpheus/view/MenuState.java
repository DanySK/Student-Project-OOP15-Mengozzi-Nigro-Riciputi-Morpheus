package morpheus.view;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import morpheus.controller.KeyInput;
import morpheus.controller.MouseInput;

public class MenuState implements State{
	
	private Texture background;
	private Texture morpheus;
	private Texture titolo;
	private Button[] options;
	private int currentSelection;
	private static final int HITBOX_OFFSET = 15;

	@Override
	public void init() {
		
		background = new Texture("res/matrix_blu.jpg");
		morpheus = new Texture("res/morpheus_giga.png");
		titolo = new Texture("res/titolo.png");
		
		options = new Button[4];
		
		//Capire cos'e il primo numero
		options[0] = new Button(150, "res/play_bianco.png", "res/play_nero.png");
		options[1] = new Button(200, "res/ranking_bianco.png", "res/ranking_nero.png");
		options[2] = new Button(250, "res/settings_bianco.png", "res/settings_nero.png");
		options[3] = new Button(300, "res/exit_bianco.png", "res/exit_nero.png");
	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub
		
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
	}
	
	@Override
	public void render(Graphics2D g) {
		
		background.render(g, 0, 0);
		//Rimettere morpheus e titolo più avanti
		//morpheus.render(g, 475, 250);
		//titolo.render(g, 375, 50);
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		
		return "menu";
	}
	
	private void select(StateManager stateManager) {
		
		switch (currentSelection) {
		
		case 0:
			//Magari cambiarlo
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
}
