package morpheus.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class RankedState implements State {

	private Image background;
	private final JFrame frame = new JFrame("Leaderboard");
	private final JPanel panel = new JPanel();
	private final JTextArea text = new JTextArea();
	private final JLabel l1 = new JLabel("1");
	private final JLabel l2 = new JLabel("2");
	
	public RankedState(){
		
		
	}

	@Override
	public void init() {

		
	}

	@Override
	public void enter() {
		
		
		BackgroundRankedState background = new BackgroundRankedState();
		background.setLayout(new FlowLayout());
	    frame.getContentPane().add(background);

		background.add(l1);
		background.add(l2);
		
		
		frame.setSize(500, 300);
		frame.setLocationRelativeTo(null);
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
