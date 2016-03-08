package gioco.View;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class PanelDiProva extends JPanel{

	
	private static final long serialVersionUID = 3672923201853552159L;
	
	//get image o createimage? funzionano entrmbi
	private final Image img = Toolkit.getDefaultToolkit().getImage("res/sfondo.png");
	private final Image img2 = Toolkit.getDefaultToolkit().createImage("res/morpheus.png");
	
	public PanelDiProva() {
	
		loadImage(img, 0);
		loadImage(img2, 1);
	}
	

	
	private void loadImage(Image img, int id) {
	    try {
	      MediaTracker track = new MediaTracker(this);
	      track.addImage(img, id);
	      track.waitForID(id);
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	  }

	
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		graphics.drawImage(img, 0, 0, null);
		graphics.drawImage(img2, 300, 250, null);
	}
}
