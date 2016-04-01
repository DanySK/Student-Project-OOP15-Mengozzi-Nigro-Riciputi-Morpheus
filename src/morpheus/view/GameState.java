package morpheus.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameState implements State {

	private final JFrame game = new JFrame("MORPHEUS");
	private final PanelDiProva panel = new PanelDiProva();
	
	//Cambiare riga sotto con qualcosa di meglio
	private Image img = Toolkit.getDefaultToolkit().createImage("res/morpheus.png");
	//this.tiledirt = ImageIO.read(this.getClass().getClassLoader().getResource("data/MudBlockTer.png"));
	
	public GameState(){
		
		game.setSize(961, 481);
		game.setResizable(false);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.getContentPane().add(panel);
		
		game.setVisible(true);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
