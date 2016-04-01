package morpheus.view;

import java.awt.Graphics2D;

//Interfaccia generale di tutte le schermate con i metodi necessari per il funzionamento di base

public interface State {

	public void init();

	public void enter();

	//public void tick(StateManager stateManager);

	public void render(Graphics2D g);

	public void exit();

	public String getName();
}

