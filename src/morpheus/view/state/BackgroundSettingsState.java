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
public class BackgroundSettingsState extends JPanel{
	
	private static final long serialVersionUID = -9220481700953736467L;
	private Image img;

	/**
	 * 
	 * Inizialize the image and check it
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 * */
	public BackgroundSettingsState(){
		
		img = Toolkit.getDefaultToolkit().createImage("res/sett2.png");
		check();
	}
	
	/**
	 * 
	 * Check the image
	 * 
	 * @author Luca Mengozzi
	 * 		 
	 */
	private void check(){
		
		MediaTracker track = new MediaTracker(this);
		track.addImage(img, 0);
		try {
			
			track.waitForID(0);
		} catch (InterruptedException e){
			
			e.printStackTrace();
		}
	}
	
	/**
	 * 		
	 * Paint the panel
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
