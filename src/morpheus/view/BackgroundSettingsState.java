package morpheus.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class BackgroundSettingsState extends JPanel{
	
	private static final long serialVersionUID = -9220481700953736467L;
	private Image img;

	public BackgroundSettingsState(){
		
		img = Toolkit.getDefaultToolkit().createImage("res/sett2.png");
		check();
		//this.setSize(500, 500);
	}
	
	private void check(){
		
		MediaTracker track = new MediaTracker(this);
		track.addImage(img, 0);
		try {
			track.waitForID(0);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	protected void paintComponent(Graphics g) {
		  
	   setOpaque(false);
	   g.drawImage(img, 0, 0, null);
	   super.paintComponent(g);
	}

}
