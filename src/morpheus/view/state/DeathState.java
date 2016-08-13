package morpheus.view.state;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import morpheus.controller.AudioPlayer;

/**
 *
 * @author Luca Mengozzi
 * 		 
 */
public class DeathState implements State{
	
	private JFrame mainFrame, recordFrame = new JFrame();
	private JPanel panelRecord = new JPanel();
	private JLabel labelScore;
	private JTextField champion = new JTextField();
	private int score = 1200;
	/**
	 * Quando è true si esce dallo state		
	 * 
	 * @author Luca Mengozzi
	 * 		 
	 */
	private boolean exit;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enter(StateManager stateManager) {
		
		//Imposto a false la variabile di uscita
		exit = false;
		
		//Creo il panel personalizzato
		labelScore = new JLabel("SCORE: " + Integer.toString(score));
		labelScore.setHorizontalAlignment( JLabel.CENTER );
		labelScore.setFont(new Font("TimesNewRoman", Font.BOLD, 30));
		BackgroundDeathState background = new BackgroundDeathState();
		background.setLayout(new BorderLayout());
		background.add(labelScore, BorderLayout.SOUTH);
		
		mainFrame = new JFrame("GAME OVER");
		mainFrame.getContentPane().add(background);
		
		recordFrame.getContentPane().add(panelRecord);
		panelRecord.add(new JLabel("Wow! You have scored a new RECORD!"));
		panelRecord.add(new JLabel("Champion, tell us what is your name"));
		champion.setSize(300, 30);
		panelRecord.add(champion);
		panelRecord.add(new JButton("Ok"));
		
		
		//Personalizzo la finestra(il frame)
		mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mainFrame.addWindowListener(new WindowListener() {
						
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
			
	   mainFrame.setSize(500, 300);
	   mainFrame.setResizable(false);
	   mainFrame.setLocationRelativeTo(null);
	   mainFrame.setVisible(true);
	   //Se è il record appare la finestra che fa inserire il nome
	   if (this.score==1200){
		   
		   recordFrame.setSize(300, 120);
		   recordFrame.setLocationRelativeTo(null);
		   recordFrame.setVisible(true);
	   }
	}

	@Override
	public void tick(StateManager stateManager) {
		
		if (exit == true){
			
			stateManager.setState("MENU");
		}
	}

	@Override
	public void render(Graphics2D g) {
		
	}

	@Override
	public void exit() {
		
		mainFrame.dispose();
	}

	@Override
	public String getName() {
		
		return "Death";
	}

	@Override
	public AudioPlayer getMusic() {
		
		return null;
	}

}
