package morpheus.view.state;

import java.awt.Font;
import java.awt.Graphics2D;
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

/**
 * 		
 * @author Luca Mengozzi
 * 		 
 */
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
	private final JButton blonde = new JButton("Valpiani");
	private final JButton violet = new JButton("Gulizia");
	private final JButton menu = new JButton("Return to Menu");
	private JComboBox<String> j;
	private JComboBox<String> s;
	private JSlider m = new JSlider(0, 100);
	private final String listj[] = new String[3];
	private final String lists[] = new String[3];
	/**
	 * Quando è true si esce dallo state		
	 * 
	 * @author Luca Mengozzi
	 * 		 
	 */
	private boolean exit;

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
		
		//Imposto a false la variabile di uscita
		exit = false;
	
		//Creo il panel personalizzato
		BackgroundSettingsState background = new BackgroundSettingsState();
		background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
		frame  = new JFrame("Options");
		frame.getContentPane().add(background);
	
		//PANNELLO 1
		player.setFont(new Font("TimesRoman", Font.BOLD, 24));
		p1.add(player);
		p1.add(blonde);
		p1.add(violet);
	
		blonde.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
			
				blonde.setEnabled(false);
				violet.setEnabled(true);
				MenuState.DEFAULT_ANIMATION = true;
			}
		});
	
		violet.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
			
				blonde.setEnabled(true);
				violet.setEnabled(false);
				MenuState.DEFAULT_ANIMATION = false;
			}
		});
	
		//PANNELLO 2
	
		jump.setFont(new Font("TimesRoman", Font.BOLD, 24));
		p2.add(jump);
		p2.add(j);
	
		//PANNELLO 3
	
		shoot.setFont(new Font("TimesRoman", Font.BOLD, 24));
		p3.add(shoot);
		p3.add(s);
	
		//PANNELLO 4
	
		sound.setFont(new Font("TimesRoman", Font.BOLD, 24));
		p4.add(sound);
		p4.add(m);
	
		//PANNELLO 5
		
		p5.add(menu);
		menu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				exit = true;
			}
		});
	
		//Aggiungo i pannelli al pannello principale
		background.add(p1);
		background.add(p2);
		background.add(p3);
		background.add(p4);
		background.add(p5);
		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);
		p4.setOpaque(false);
		p5.setOpaque(false);
	
		//Personalizzo la finestra(il frame)
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowListener() {
				
			@Override
			public void windowOpened(WindowEvent e) {
				
			}			
			
			@Override
			public void windowIconified(WindowEvent e) {
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				
			}
				
			@Override
			public void windowDeactivated(WindowEvent e) {
					
			}
				
			@Override
			public void windowClosing(WindowEvent e) {
					
				exit = true;
			}
				
			@Override
			public void windowClosed(WindowEvent e) {
					
			}
				
			@Override
			public void windowActivated(WindowEvent e) {
					
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
		
		frame.dispose();
	}
	
	@Override
	public String getName() {
		
		return "Settings";
	}

	@Override
	public void tick(StateManager stateManager) {
		
		if (exit == true){
			
			stateManager.setState("MENU");
		}
	}
}
