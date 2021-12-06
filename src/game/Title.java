
package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Title implements Animatable
{
	//Fields
	BufferedImage image;
	GameState state;
	

	public Title(GameState state) {
		image = ResourceLoader.getLoader().getImage("titleScreen.png");
		this.state = state;
	}


	public void draw(Graphics g, GameView view) {
		g.drawImage(image, 0, 0, null);
		
	}


	@Override
	public void update(double timeElapsed) {
		if((state.getMouseX() >= 150) && (state.getMouseX() <= 400) &&
				(state.getMouseY() >= 480) && (state.getMouseY() <= 575) &&
				state.isMouseClicked()) 
		{
			state.addGameObject(new Backdrop("path.png"));
	    	state.addGameObject(new Menu("menuBackground.png", state));
	    	state.start();
			}
	}

}
