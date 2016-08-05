package morpheus.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class RankedState implements State {

	private final JFrame frame = new JFrame("Leaderboard");
	private final JLabel num1 = new JLabel("1.");
	private final JLabel num2 = new JLabel("2.");
	private final JLabel num3 = new JLabel("3.");
	private final JLabel num4 = new JLabel("4.");
	private final JLabel num5 = new JLabel("5.");
	private final JLabel name1 = new JLabel("LUCA MENGOZZI");
	private final JLabel name2 = new JLabel("MATTEO NIGRO");
	private final JLabel name3 = new JLabel("JACOPO RICIPUTI");
	private final JLabel name4 = new JLabel("ROSSANO CODELUPPI");
	private final JLabel name5 = new JLabel("SIMONE ZAZA");
	private final JLabel res1 = new JLabel("700");
	private final JLabel res2 = new JLabel("550");
	private final JLabel res3 = new JLabel("400");
	private final JLabel res4 = new JLabel("398");
	private final JLabel res5 = new JLabel("5");
	private boolean exit;
	
	public RankedState(){
		
		
	}

	@Override
	public void init() {

		
	}

	@Override
	public void enter() {
		
		exit = false;
		BackgroundRankedState background = new BackgroundRankedState();
		background.setLayout(new GridLayout(5, 3));
	    frame.getContentPane().add(background);

	    num1.setHorizontalAlignment( JLabel.CENTER );
	    name1.setHorizontalAlignment( JLabel.CENTER );
	    res1.setHorizontalAlignment( JLabel.CENTER );
	    num2.setHorizontalAlignment( JLabel.CENTER );
	    name2.setHorizontalAlignment( JLabel.CENTER );
	    res2.setHorizontalAlignment( JLabel.CENTER );
	    num3.setHorizontalAlignment( JLabel.CENTER );
	    name3.setHorizontalAlignment( JLabel.CENTER );
	    res3.setHorizontalAlignment( JLabel.CENTER );
	    num4.setHorizontalAlignment( JLabel.CENTER );
	    name4.setHorizontalAlignment( JLabel.CENTER );
	    res4.setHorizontalAlignment( JLabel.CENTER );
	    num5.setHorizontalAlignment( JLabel.CENTER );
	    name5.setHorizontalAlignment( JLabel.CENTER );
	    res5.setHorizontalAlignment( JLabel.CENTER );
	    
	   
	    
	    num1.setFont(new Font("TimesNewRoman", Font.ITALIC, 18));
	    name1.setFont(new Font("TimesNewRoman", Font.ITALIC, 18));
	    res1.setFont(new Font("TimesNewRoman", Font.ITALIC, 18));
	    num2.setFont(new Font("TimesNewRoman", Font.ITALIC, 18));
	    name2.setFont(new Font("TimesNewRoman", Font.ITALIC, 18));
	    res2.setFont(new Font("TimesNewRoman", Font.ITALIC, 18));
	    num3.setFont(new Font("TimesNewRoman", Font.ITALIC, 18));
	    name3.setFont(new Font("TimesNewRoman", Font.ITALIC, 18));
	    res3.setFont(new Font("TimesNewRoman", Font.ITALIC, 18));
	    num4.setFont(new Font("TimesNewRoman", Font.ITALIC, 18));
	    name4.setFont(new Font("TimesNewRoman", Font.ITALIC, 18));
	    res4.setFont(new Font("TimesNewRoman", Font.ITALIC, 18));
	    num5.setFont(new Font("TimesNewRoman", Font.ITALIC, 18));
	    name5.setFont(new Font("TimesNewRoman", Font.ITALIC, 18));
	    res5.setFont(new Font("TimesNewRoman", Font.ITALIC, 18));
	    
	    
		background.add(num1);
		background.add(name1);
		background.add(res1);
		background.add(num2);
		background.add(name2);
		background.add(res2);
		background.add(num3);
		background.add(name3);
		background.add(res3);
		background.add(num4);
		background.add(name4);
		background.add(res4);
		background.add(num5);
		background.add(name5);
		background.add(res5);

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
		
		frame.setSize(500, 300);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit() {
		

	}

	@Override
	public String getName() {
		
		return "ranked";
	}

	@Override
	public void tick(StateManager stateManager) {
		
		if (exit == true){
			
			stateManager.setMenuState();
			frame.dispose();
		}
	}
}
