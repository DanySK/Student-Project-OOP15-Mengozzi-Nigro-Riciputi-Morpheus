package gioco.View;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class OptionsImpl {

	private final JFrame game = new JFrame("MORPHEUS");
	private final JPanel panel = new JPanel();
	
	public OptionsImpl(){
		
		game.setSize(1000, 600);
		game.setResizable(false);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.getContentPane().add(panel);
		
		
		game.setVisible(true);
	}
}
