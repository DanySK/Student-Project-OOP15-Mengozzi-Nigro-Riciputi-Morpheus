package morpheus.view.state;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JPanel;

/**
 * 
 * @author Luca Mengozzi
 *
 */
public class BackgroundDeathState extends JPanel{
	
	private static final long serialVersionUID = 6698084179763723970L;
	private final Image img;
	
	/**
	 * 
	 * L'oggetto inizializza l'immagine e controlla che venga effettivamente caricata
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 * */
	public BackgroundDeathState(){
			
		img = Toolkit.getDefaultToolkit().createImage("res/gameoverp.jpeg");
		check();
	}
		
	/**
	 * 
	 * Controlla che l'immagine venga caricata
	 * 
	 * @author Luca Mengozzi
	 * 		 
	 */
	private void check(){
			
		MediaTracker track = new MediaTracker(this);
		track.addImage(img, 0);
		try{
				
			track.waitForID(0);
		} catch (InterruptedException e){
				
			e.printStackTrace();
			}
		}
		
	/**
	 * 		
	 * Disegna sul pannello
	 * 
	 * @author Luca Mengozzi
	 * 		 
	 */
	protected void paintComponent(Graphics g) {
			  
	   setOpaque(false);
	   g.drawImage(img, 0, 0, null);
	   super.paintComponent(g);
	}	  
}
