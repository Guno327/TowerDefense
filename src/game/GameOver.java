/*
 * Game over object class. Creates an object to display a game over screen.
 * 
 * @author Gunnar and Kate
 * @version 12/6/21
 */
package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class GameOver implements Animatable
{
	//Fields
	BufferedImage image;
	
	/**
	 * Constructor for game over objects. Makes an animatable object that 
	 * holds a game over image.
	 * 
	 * @param state The GameState
	 */
	public GameOver(GameState state) 
	{
		//draws game over image 
		
		image = ResourceLoader.getLoader().getImage("gameover.png");
	}

	/**
	 * Required to be animatable. Unimplemented in this class.
	 * 
	 * @param timeElapsed a double integer that is the time since the last 
	 * 					  update call originated in GameControl
	 */
	public void update(double timeElapsed) {}

	/**
	 * Draws the game over image to the screen
	 */
	public void draw(Graphics g, GameView view) 
	{
		g.drawImage(image, 0, 0, null);
		
	}
}
