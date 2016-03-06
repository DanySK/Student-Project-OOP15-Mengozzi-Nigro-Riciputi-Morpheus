package gioco.View;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class PanelDiProva extends JPanel{

	private Image img = Toolkit.getDefaultToolkit().createImage("res/sfondo.png");
	private Image img2 = Toolkit.getDefaultToolkit().createImage("res/morpheus.png");
	
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		graphics.drawImage(img, 0, 0, null);
		graphics.drawImage(img2, 300, 250, null);
	}
}
