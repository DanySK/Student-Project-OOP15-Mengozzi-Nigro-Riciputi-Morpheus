package morpheus.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class RankedState implements State {

	private final JFrame game = new JFrame("MORPHEUS");
	private final JPanel panel = new JPanel();
	private final JTextArea text = new JTextArea();
	
	public RankedState(){
		
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
