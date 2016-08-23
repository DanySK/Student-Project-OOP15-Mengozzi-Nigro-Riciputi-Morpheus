package morpheus.view;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import morpheus.Morpheus;
import morpheus.view.Texture;

/**
 *  
 * @author Luca Mengozzi		 
 * 
 */
public class Button extends Rectangle {

	private static final long serialVersionUID = -4663253695375762967L;
	/**
	 * 
	 * Check the selection of the button, if is true is selected
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 * */
	private boolean selected;
	private int yPosition;
	private Texture mainTexture, textureSelected;

	/**
	 * 
	 * Costructor that inizialize the button
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	public Button(int yPosition, String mainTexture, String textureSelected) {
		
		this.yPosition = yPosition;
		this.mainTexture = new Texture(mainTexture);
		this.textureSelected = new Texture(textureSelected);
	}

	/**
	 * 
	 * Set the button and select it
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	public void setSelected(boolean selected) {
		
		this.selected = selected;
	}

	/**
	 * 
	 * Classic render method
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 */
	public void render(Graphics2D g) {
		
		this.x = (Morpheus.WIDTH - mainTexture.getImage().getWidth()) / 4;
		this.y = yPosition - mainTexture.getImage().getHeight();
		width = mainTexture.getImage().getWidth();
		height = mainTexture.getImage().getHeight();
		mainTexture.render(g, x, y);
		
		if (selected) {
			
			textureSelected.render(g, x, y);
		} else {
			
			mainTexture.render(g, x, y);
		}
	}
}
