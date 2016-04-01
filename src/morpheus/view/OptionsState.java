package morpheus.view;

import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class OptionsState implements State {

	private final JFrame game = new JFrame("MORPHEUS");
	private final JPanel panel = new JPanel();
	
	public OptionsState(){
		
		game.setSize(1000, 600);
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
