package morpheus.view;

import java.awt.Graphics2D;

/**
 * 
 * @author Luca Mengozzi		 
 * 
 * */
public class GraphicLifes {

	private Texture life = new Texture("/cuorep.png");
	
	/**
	 * 
	 * Classic render method
	 * 
	 * @author Luca Mengozzi		 
	 * 
	 * */
	public void render(Graphics2D g, int nLife){
		
		if (nLife>=1){
			
			life.render(g, 750, 50);
		}
		if (nLife>=2){
			
			life.render(g, 720, 50);
		}
		if (nLife>=3){
			
			life.render(g, 690, 50);
		}
	}
}
