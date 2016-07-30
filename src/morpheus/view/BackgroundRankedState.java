package morpheus.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class BackgroundRankedState extends JPanel{
	
	

		private static final long serialVersionUID = 8773250085938571559L;
		private Image img = Toolkit.getDefaultToolkit().createImage("res/leader.png");
		
		
		  protected void paintComponent(Graphics g) {
			  
		    setOpaque(false);
		    g.drawImage(img, 90, 0, null);
		    super.paintComponent(g);
		    MediaTracker track = new MediaTracker(this);
			track.addImage(img, 0);
			try {
				track.waitForID(0);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		  }

		  
}
