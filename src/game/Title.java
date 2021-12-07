/**
 * Class for title page to display the title image and start page to start the game.
 * 
 * @author Gunnar and Kate
 * @version 12/6/21
 */
package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Title implements Animatable
{
	//Fields
	
	BufferedImage image;
	GameState state;

	/**
	 * Loads the title screen image into the resource loader and game state.
	 * 
	 * @param state GameState object
	 */
	public Title(GameState state) 
	{
		image = ResourceLoader.getLoader().getImage("titleScreen.png");
		this.state = state;
	}

	/**
	 * Draws title screen image to cover the entire screen.
	 */
	public void draw(Graphics g, GameView view) 
	{
		g.drawImage(image, 0, 0, null);
		
	}
	
	/**
	 * Updates as the mouse clicks the start button on the title page.
	 * Once the start button is clicked then the backdrop of the game is
	 * loaded and drawn.
	 * 
	 * @param timeElapsed a double integer that is the time since the last 
	 * 					  update call originated in GameControl
	 */
	public void update(double timeElapsed) 
	{
		//if the start button is clicked within its specified coordinates
		//then the menu is drawn
		
		if((state.getMouseX() >= 150) && (state.getMouseX() <= 400) &&
				(state.getMouseY() >= 480) && (state.getMouseY() <= 575) &&
				state.isMouseClicked()) 
		{
			//backdrop is drawn
			
			state.addGameObject(new Backdrop("path.png"));
	    	state.addGameObject(new Menu("menuBackground.png", state));
	    	state.start();
		}
	}

}
