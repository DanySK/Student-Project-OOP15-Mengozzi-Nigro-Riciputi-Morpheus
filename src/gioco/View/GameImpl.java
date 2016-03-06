package gioco.View;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameImpl {

	private final JFrame game = new JFrame("MORPHEUS");
	private final PanelDiProva panel = new PanelDiProva();
	
	//Cambiare riga sotto con qualcosa di meglio
	private Image img = Toolkit.getDefaultToolkit().createImage("res/morpheus.png");
	
	public GameImpl(){
		
		game.setSize(961, 481);
		game.setResizable(false);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.getContentPane().add(panel);
		
		
		
		
		
		game.setVisible(true);
	}
}
