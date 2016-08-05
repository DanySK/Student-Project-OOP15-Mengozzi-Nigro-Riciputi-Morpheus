package morpheus.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class BackgroundRankedState extends JPanel{
	
		private static final long serialVersionUID = 8773250085938571559L;
		private Image img;

		public BackgroundRankedState(){
			
			img = Toolkit.getDefaultToolkit().createImage("res/coppap2.png");
			check();
			//this.setSize(500, 300);
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
		   g.drawImage(img, 90, 0, null);
		   super.paintComponent(g);
		}

		  
}
