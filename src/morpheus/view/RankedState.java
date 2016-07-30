package morpheus.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class RankedState implements State {

	private Texture background;
	private final JFrame frame = new JFrame();
	private final JPanel panel = new JPanel();
	private final JTextArea text = new JTextArea();
	private final JLabel label = new JLabel("Leaderboard");
	
	public RankedState(){
		
		
		
		/*game.setSize(1000, 600);
		game.setResizable(false);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.getContentPane().add(panel);
		
		panel.add(text);
		text.setText("QUA CI SARANNO I PUNTEGGI DAL FILE DI TESTO FATTO DAL MODEL");
		text.setFont(new Font("NUOVO", Font.PLAIN, 10));
		panel.setBackground(Color.BLACK);*/
		
		
	}

	@Override
	public void init() {

		
	}

	@Override
	public void enter() {
		
		frame.setSize(400, 200);
		frame.setVisible(true);
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit() {
		

		//stateManager.setState("Menu");
		//Altrimenti si passa il numero di frame al menustate che nel suo tick() quando vede che
		//c'e solo il prìncipale si seleziona se stesso
	}

	@Override
	public String getName() {
		
		return "ranked";
	}

	@Override
	public void tick(StateManager stateManager) {
		// TODO Auto-generated method stub
		
	}
}
