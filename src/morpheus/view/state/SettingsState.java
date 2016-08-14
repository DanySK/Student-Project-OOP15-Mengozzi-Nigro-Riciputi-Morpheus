package morpheus.view.state;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import morpheus.controller.AudioPlayer;
import morpheus.model.Model;
import morpheus.model.ModelImpl;

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
	private final JLabel player = new JLabel("PLAYER:");
	private final JLabel jump = new JLabel("JUMP:");
	private final JLabel shoot = new JLabel("SHOOT:");
	private final JLabel sound = new JLabel("SOUND:");
	private final JButton blonde = new JButton("Valpiani");
	private final JButton violet = new JButton("Gulizia");
	private JComboBox<String> j;
	private JComboBox<String> s;
	private JSlider m = new JSlider(0, 100, 75);
	//Da implementare con anche nessuna musica
	private final String listj[] = new String[3];
	private final Map<String, Integer> mapKey = new HashMap<>();
	private final String lists[] = new String[3];
	/**
	 * Quando è true si esce dallo state		
	 * 
	 * @author Luca Mengozzi
	 * 		 
	 */
	private boolean exit;
	private Model model = new ModelImpl();

	@Override
	public void init() {
		
		if(model.isMainPlayerOpen()){
			
			blonde.setEnabled(false);
		}
		else{
			
			violet.setEnabled(false);
		}
		
		
		listj[0] = "W";
		listj[1] = "Spacebar";
		listj[2] = "Up";
		j = new JComboBox<>(listj);
		
		lists[0] = "Spacebar";
		lists[1] = "D";
		lists[2] = "Right";
		s = new JComboBox<>(lists);
		
		mapKey.put("W", KeyEvent.VK_W);
		mapKey.put("D", KeyEvent.VK_D);
		mapKey.put("Spacebar", KeyEvent.VK_SPACE);
		mapKey.put("Up", KeyEvent.VK_UP);
		mapKey.put("Right", KeyEvent.VK_RIGHT);
	}

	@Override
	public void enter(StateManager stateManager) {
		
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
				model.setMainPlayerOpening(true);
				model.setSidePlayerOpening(false);
				
			}
		});
	
		violet.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
			
				blonde.setEnabled(true);
				violet.setEnabled(false);
				model.setMainPlayerOpening(false);
				model.setSidePlayerOpening(true);
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
	
		//Aggiungo i pannelli al pannello principale
		background.add(p1);
		background.add(p2);
		background.add(p3);
		background.add(p4);
		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);
		p4.setOpaque(false);
		
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
	
	    frame.setSize(500, 250);
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
		
		//Varibile che conterrà il valore del volume preso dalla JSlider
		double a = m.getValue();
		a = a / 100;
		//Il volume sarà a
		model.setVolume(a);
		
		//Setta la configurazione dei tasti
		model.setKeyJump(mapKey.get((String)j.getSelectedItem()));
		model.setKeyShoot(mapKey.get((String)s.getSelectedItem()));
		
		if (exit == true){
			
			stateManager.setState("MENU");
		}
	}

	@Override
	public AudioPlayer getMusic() {
		
		return null;
	}
}
