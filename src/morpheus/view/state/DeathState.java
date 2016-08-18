package morpheus.view.state;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import morpheus.controller.AudioPlayer;
import morpheus.model.Element;
import morpheus.model.Model;
import morpheus.model.ModelImpl;
import morpheus.model.exceptions.IllegalNameException;
import morpheus.model.exceptions.NoElementsException;

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
	private JButton confirm = new JButton("Ok");
	private Model model = new ModelImpl();
	/**
	 * Quando è true si esce dal recordFrame		
	 * 
	 * @author Luca Mengozzi
	 * 		 
	 */
	private boolean exitRecord;
	/**
	 * Quando è true si esce dallo state		
	 * 
	 * @author Luca Mengozzi
	 * 		 
	 */
	private boolean exit;

	@Override
	public void init() {
		
		//Creo il panel personalizzato
		labelScore = new JLabel("SCORE: " + Integer.toString(GameState.score));
		labelScore.setHorizontalAlignment( JLabel.CENTER );
		labelScore.setFont(new Font("TimesNewRoman", Font.BOLD, 30));
		BackgroundDeathState background = new BackgroundDeathState();
		background.setLayout(new BorderLayout());
		background.add(labelScore, BorderLayout.SOUTH);
				
		mainFrame = new JFrame("GAME OVER");
		mainFrame.getContentPane().add(background);
				
		//Personalizzo il recordFrame
		recordFrame.getContentPane().add(panelRecord);
		panelRecord.add(new JLabel("Wow! You have scored a new RECORD!"));
		panelRecord.add(new JLabel("Champion, tell us what is your name"));
		champion.setColumns(23);
		panelRecord.add(champion);
		panelRecord.add(confirm);
	}

	@Override
	public void enter(StateManager stateManager) {
		
		//Imposto a false la variabile di uscita dallo state e di uscita dal recordFrame
		exit = false;
		exitRecord = false;
		
		labelScore.setText("SCORE: " + Integer.toString(GameState.score));
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try{
					
					model.getRanking().add(new Element(champion.getText(), GameState.score));
					exitRecord = true;
				}catch(IllegalNameException e1){
					
					JDialog confirm2 = new JDialog();
					JPanel panel = new JPanel();
					JButton yes = new JButton("Yes");
					JButton no = new JButton("No");
					panel.add(new JLabel("This name is used yet. Are you sure do you want continue?"));
					panel.add(new JLabel("\"Yes\" will delete old result with the same name"));
					panel.add(yes);
					panel.add(no);
					confirm2.getContentPane().add(panel);
					yes.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							
							model.getRanking().forceAdd(new Element(champion.getText(), GameState.score));
							confirm2.dispose();
							exitRecord = true;
						}
					});
					no.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							
							confirm2.dispose();
						}
					});
					confirm2.setSize(700, 75);
					confirm2.setLocationRelativeTo(null);
					confirm2.setVisible(true);
				} catch (IllegalArgumentException e1) {
                    
                    e1.printStackTrace();
                }
				
				try {
					
					model.getRanking().close();
				} catch (IOException e2) {
					
					e2.printStackTrace();
				}
				if(exitRecord){
					
					recordFrame.dispose();
				}
			}
		});
		
		
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
	   try {
		   
		   if (GameState.score>model.getRanking().getPosition(5).getScore()){
        	   
        	   recordFrame.setSize(300, 121);
        	   recordFrame.setLocationRelativeTo(null);
        	   recordFrame.setVisible(true);
           }
	   } catch (NoElementsException e1) {
        
		   e1.printStackTrace();
	   }
	}

	@Override
	public void tick(StateManager stateManager) {
		
		if (exit == true){
			
			stateManager.setState("MENU");
		}
		//Interrompe la musica
		stateManager.getState("GAME").getMusic().setVolume(0);
	}

	@Override
	public void render(Graphics2D g) {
		
	}

	@Override
	public void exit() {
		
		mainFrame.dispose();
		recordFrame.dispose();
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
