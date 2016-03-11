package morpheus.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class RankedImpl {

	private final JFrame game = new JFrame("MORPHEUS");
	private final JPanel panel = new JPanel();
	private final JTextArea text = new JTextArea();
	
	public RankedImpl(){
		
		game.setSize(1000, 600);
		game.setResizable(false);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.getContentPane().add(panel);
		
		panel.add(text);
		text.setText("QUA CI SARANNO I PUNTEGGI DAL FILE DI TESTO FATTO DAL MODEL");
		text.setFont(new Font("NUOVO", Font.PLAIN, 10));
		panel.setBackground(Color.BLACK);
		
		game.setVisible(true);
	}
}
