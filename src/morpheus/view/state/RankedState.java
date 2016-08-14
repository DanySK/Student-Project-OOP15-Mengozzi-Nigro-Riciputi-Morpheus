package morpheus.view.state;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import morpheus.controller.AudioPlayer;
import morpheus.model.Model;
import morpheus.model.ModelImpl;
import morpheus.model.Element;

/**
 * 		
 * @author Luca Mengozzi
 * 		 
 */
public class RankedState implements State {

	private JFrame frame;
	private final JLabel num1 = new JLabel();
	private final JLabel num2 = new JLabel();
	private final JLabel num3 = new JLabel();
	private final JLabel num4 = new JLabel();
	private final JLabel num5 = new JLabel();
	private final JLabel name1 = new JLabel();
	private final JLabel name2 = new JLabel();
	private final JLabel name3 = new JLabel();
	private final JLabel name4 = new JLabel();
	private final JLabel name5 = new JLabel();
	private final JLabel res1 = new JLabel();
	private final JLabel res2 = new JLabel();
	private final JLabel res3 = new JLabel();
	private final JLabel res4 = new JLabel();
	private final JLabel res5 = new JLabel();
	/**
	 * Quando è true si esce dallo state		
	 * 
	 * @author Luca Mengozzi
	 * 		 
	 */
	private boolean exit;
	private List<Element> elements;
	private Model model = new ModelImpl();
	
	@Override
	public void init() {

	}

	@Override
	public void enter(StateManager stateManager) {
		
		//Imposto a false la variabile di uscita
		exit = false;
		
		//Riempio la lista di elementi inizializzando la lista
		elements = model.getRanking().getPartOfRanking(5);
		
		//Carico i dati dalla lista 
		if (elements.size()>=1){
			
			num1.setText("1.");
			name1.setText(elements.get(0).getName());
			res1.setText(Integer.toString(elements.get(0).getScore()));
		}
		if (elements.size()>=2){
			
			num2.setText("2.");
			name2.setText(elements.get(1).getName());
			res2.setText(Integer.toString(elements.get(1).getScore()));
		}
		if (elements.size()>=3){
			
			num3.setText("3.");
			name3.setText(elements.get(2).getName());
			res3.setText(Integer.toString(elements.get(2).getScore()));
		}
		if (elements.size()>=4){
			
			num4.setText("4.");
			name4.setText(elements.get(3).getName());
			res4.setText(Integer.toString(elements.get(3).getScore()));
		}
		if (elements.size()>=5){
			
			num5.setText("5.");
			name5.setText(elements.get(4).getName());
			res5.setText(Integer.toString(elements.get(4).getScore()));
		}
		
		//Creo il panel personalizzato
		BackgroundRankedState background = new BackgroundRankedState();
		background.setLayout(new GridLayout(5, 3));
		frame = new JFrame("Leaderboard");
		frame.getContentPane().add(background);

		//Imposto l'allineamento
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
	    
	    //Imposto il font
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
	    
	    //Aggiungo componenti al pannello
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
		
		//Imposto il frame
		frame.setSize(500, 300);
		frame.setResizable(false);
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
		
		return "Ranked";
	}

	@Override
	public void tick(StateManager stateManager) {
		
		if (exit == true){
			
			stateManager.setState("MENU");
		}
	}

	@Override
	public AudioPlayer getMusic() {
		
		return null;
	}
}
