package morpheus.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class SettingsState implements State {

	private JFrame frame;
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	private JPanel p4 = new JPanel();
	private JPanel p5 = new JPanel();
	private final JLabel player = new JLabel("PLAYER:");
	private final JLabel jump = new JLabel("JUMP:");
	private final JLabel shoot = new JLabel("SHOOT:");
	private final JLabel sound = new JLabel("SOUND:");
	private JComboBox<String> j;
	private JComboBox<String> s;
	private JSlider m = new JSlider(0, 100);
	private final JButton blonde = new JButton("Valpiani");
	private final JButton violet = new JButton("Gulizia");
	private final JButton menu = new JButton("Return to Menu");//Posso anche toglierlo
	private final String listj[] = new String[3];
	private final String lists[] = new String[3];
	private boolean exit;
	
	public SettingsState(){
		
	}

	@Override
	public void init() {
		
		blonde.setEnabled(false);
		
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
		
	frame  = new JFrame("Options");
	exit = false;
	BackgroundSettingsState background = new BackgroundSettingsState();
	frame.getContentPane().add(background);
	background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
	
	
	p1.setOpaque(false);
	p2.setOpaque(false);
	p3.setOpaque(false);
	p4.setOpaque(false);
	p5.setOpaque(false);
	
	
	player.setFont(new Font("TimesRoman", Font.BOLD, 24));
	//player.setHorizontalAlignment(JLabel.LEFT);

	p1.add(player);
	p1.add(blonde);
	p1.add(violet);
	
	//Dico ai bottoni cosa devono fare
	blonde.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			blonde.setEnabled(false);
			violet.setEnabled(true);
			MenuState.defaultAnimation = true;
		}
	});

	violet.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			blonde.setEnabled(true);
			violet.setEnabled(false);
			MenuState.defaultAnimation = false;
		}
	});
	
	
	background.add(p1);
	
	jump.setFont(new Font("TimesRoman", Font.BOLD, 24));
	p2.add(jump);
	p2.add(j);
	
	
	background.add(p2);
	
	shoot.setFont(new Font("TimesRoman", Font.BOLD, 24));
	p3.add(shoot);
	p3.add(s);
	
	
	background.add(p3);
	
	sound.setFont(new Font("TimesRoman", Font.BOLD, 24));
	p4.add(sound);
	p4.add(m);
	
	
	background.add(p4);
	
	p5.add(menu);
	
	menu.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			exit = true;
		}
	});
	
	background.add(p5);
	
	//frame.pack();
	
	   
	    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				
				exit = true;
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
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
		
		if (exit == true){
			
			stateManager.setMenuState();
			frame.dispose();
		}
	}
}
