package morpheus.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import morpheus.Morpheus;
import morpheus.view.Texture;

public class Button extends Rectangle {

	private static final long serialVersionUID = -4663253695375762967L;
	//Dice se è selezionata
	private boolean selected;
	//Ancora non so cosa sia
	private int yPosition;
	//La texture normale e da selezionata
	private Texture mainTexture, textureSelected;

	public Button(int yPosition, String mainTexture, String textureSelected) {
		
		this.yPosition = yPosition;
		this.mainTexture = new Texture(mainTexture);
		this.textureSelected = new Texture(textureSelected);
	}

	//Seleziona
	public void setSelected(boolean selected) {
		
		this.selected = selected;
	}

	public void render(Graphics2D g) {
		
		this.x = (300 - mainTexture.getImage().getWidth()) / 2;
		//this.x = (Morpheus.WIDTH - mainTexture.getImage().getWidth()) / 2;
		this.y = yPosition - mainTexture.getImage().getHeight();
		width = mainTexture.getImage().getWidth();
		height = mainTexture.getImage().getHeight();
		mainTexture.render(g, x, y);
		g.setColor(Color.BLACK);
		//Togliere commento sotto per rettangoli
		//g.drawRect(x, y, width, height);

		if (selected) {
			
			textureSelected.render(g, x, y);
		} else {
			
			mainTexture.render(g, x, y);
		}

	}

}
