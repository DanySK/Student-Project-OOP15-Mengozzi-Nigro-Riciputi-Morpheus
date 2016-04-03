package morpheus.view;

import java.awt.Graphics2D;

import morpheus.view.Texture;

public class MenuState implements State{
	
	private Texture background;
	private Texture play;
	private Texture play2;

	@Override
	public void init() {
		
		background = new Texture("res/matrix_blu3.jpg");
		play = new Texture("res/pre.jpg");
		play2 = new Texture("res/nonpre.jpg");
	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g) {
		
		background.render(g, 0, 0);
		play.render(g, 300, 100);
		play2.render(g, 300, 200);
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		
		return "menu";
	}
}
