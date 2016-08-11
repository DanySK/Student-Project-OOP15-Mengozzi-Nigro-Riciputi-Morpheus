package morpheus.view;

import java.awt.Graphics2D;

public class GraphicBullet {

	private Texture bullet = new Texture("res/bulletp.png");
	
	//Classico metodo render
	public void render(Graphics2D g, int nBullet){
			
		if (nBullet>=1){
			
			bullet.render(g, 630, 50);
		}
		if (nBullet>=2){
					
			bullet.render(g, 600, 50);
		}
		if (nBullet>=3){
				
			bullet.render(g, 570, 50);
		}
		if (nBullet>=4){
					
			bullet.render(g, 540, 50);
		}
		if (nBullet>=5){
			
			bullet.render(g, 510, 50);
		}
		if (nBullet>=6){
					
			bullet.render(g, 480, 50);
		}
		if (nBullet>=7){
				
			bullet.render(g, 450, 50);
		}
		if (nBullet>=8){
					
			bullet.render(g, 420, 50);
		}
	}
}
