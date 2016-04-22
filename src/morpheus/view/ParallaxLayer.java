package morpheus.view;

import java.awt.Graphics2D;

import morpheus.Morpheus;
import morpheus.view.Texture;

public class ParallaxLayer {
	private Texture texture;
	private int x, y;
	private int width, height;
	private int dX;
	private int gap;
	private boolean left, right;

	public ParallaxLayer(Texture texture, int dX, int gap) {
		
		this.texture = texture;
		this.dX = dX;
		this.gap = gap;
		this.width = texture.getWidth();
		this.height = texture.getHeight();
		this.x = this.y = 0;
	}

	public ParallaxLayer(Texture texture, int dX) {
		
		this(texture, dX, 0);
	}

	public void setRight() {
		
		this.right = true;
		this.left = false;
	}

	public void setLeft() {
		
		this.left = true;
		this.right = false;
	}

	public void stop() {
		
		right = left = false;
	}

	public void move() {
		
		if (right) {
			
			x = (x + dX) % (width + gap);
		} else {
			
			x = (x - dX) % width;
		}
	}

	public void render(Graphics2D g) {
		
		if (x == 0) {
			
			// Renderizza la testa dell�immagine a 0,0
			texture.parallaxRender(g, 0, Morpheus.WIDTH, 0, Morpheus.WIDTH, y);
		} else if (x > 0 && x < Morpheus.WIDTH) {
			
			// Renderizza la coda dell�immagine a 0,0
			texture.parallaxRender(g, 0, x, width - x, width, y);
			// Renderizza la testa dell�immagine a x,0
			texture.parallaxRender(g, x, Morpheus.WIDTH, 0, Morpheus.WIDTH - x, y);
		} else if (x > Morpheus.WIDTH) {
			
			// Renderizza la coda dell�immagine a 0,0
			texture.parallaxRender(g, 0, Morpheus.WIDTH, width - x, width - x + Morpheus.WIDTH, y);
		} else if (x < 0 && x >= Morpheus.WIDTH - width) {
			
			// Renderizza il solo corpo dell�immagine
			texture.parallaxRender(g, 0, Morpheus.WIDTH, -x, Morpheus.WIDTH - x, y);
		} else if (x < Morpheus.WIDTH - width) {
			
			// Renderizza la coda dell�immagine a 0,0
			texture.parallaxRender(g, 0, width + x, -x, width, y);
			// Renderizza la testa dell�immagine a width+x,0
			texture.parallaxRender(g, gap + width + x, gap + Morpheus.WIDTH, 0, Morpheus.WIDTH - width - x, y);
		}
	}

}
