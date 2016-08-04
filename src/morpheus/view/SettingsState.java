package morpheus.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SettingsState implements State {

	private final JFrame frame = new JFrame("Options");
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	private JPanel p4 = new JPanel();
	private final JLabel player = new JLabel("PLAYER:");
	private final JLabel jump = new JLabel("JUMP:");
	private final JLabel shoot = new JLabel("SHOOT:");
	private JComboBox<String> j;
	private JComboBox<String> s;
	private final JButton blonde = new JButton("Valpiani");
	private final JButton violet = new JButton("Gulizia");
	private final JButton save = new JButton("Save");
	private final String listj[] = new String[3];
	private final String lists[] = new String[3];
	
	public SettingsState(){
		
	}

	@Override
	public void init() {
		
		listj[0] = "Q";
		listj[1] = "W";
		listj[2] = "E";
		
		j = new JComboBox<>(listj);
		
		lists[0] = "D";
		lists[1] = "F";
		lists[2] = "G";
		
		s = new JComboBox<>(lists);
	}

	@Override
	public void enter() {
		
		
	frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
	
	p1.setLayout(new FlowLayout());
	player.setFont(new Font("TimesRoman", Font.BOLD, 24));
	//player.setHorizontalAlignment(JLabel.LEFT);

	p1.add(player);
	p1.add(blonde);
	p1.add(violet);
	blonde.setEnabled(false);
	
	frame.getContentPane().add(p1);
	
	jump.setFont(new Font("TimesRoman", Font.BOLD, 24));
	p2.add(jump);
	p2.add(j);
	
	
	frame.getContentPane().add(p2);
	
	shoot.setFont(new Font("TimesRoman", Font.BOLD, 24));
	p3.add(shoot);
	p3.add(s);
	
	
	frame.getContentPane().add(p3);
	
	p4.add(save);
	
	frame.getContentPane().add(p4);
	
	
	
		
		
		
		
		
		
		
		
		
		
		
		

		
		
		
	   
	    
	    frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void render(Graphics2D g) {
		
		
	}

	@Override
	public void exit() {
		
		
	}

	@Override
	public String getName() {
		
		return "settings";
	}

	@Override
	public void tick(StateManager stateManager) {
		// TODO Auto-generated method stub
		
	}
}
