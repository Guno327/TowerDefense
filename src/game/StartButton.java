/*
 * Class for the menu icon for the tree tower.
 * 
 * @author Gunnar and Kate
 * @version 12/6/21
 */
package game;

import java.awt.Graphics;

public class StartButton extends Tower{

	//Fields
	private int x;
	private int y;
	
	/**
	 * Constructor for the menu icon of the tower.
	 * 
	 * @param state the GameState
	 * @param x x-position to create the icon
	 * @param y y-position to create the icon
	 */
	public StartButton(GameState state, int x, int y) 
	{
		//x and y coordinates of the start button
		
		super(state,x,y);
		this.x = x;
		this.y = y;
	}

	/**
	 * Runs every update. Checks to see if the menu icon was clicked.
	 * If the menu icon was clicked it creates a moving icon underneath 
	 * the users cursor.
	 * 
	 * @param timeElapsed a double integer that is the time since the last 
	 * 					  update call originated in GameControl
	 */
	public void update(double timeElapsed) 
	{
		//starts the enemy wave if start button is pressed
		
		if(state.isInWave())
			return;
		
		//records if the start button has been clicked within its point coordinates
		
		if((state.getMouseX() >= x - 60) && (state.getMouseX() <= x + 60) &&
				(state.getMouseY() >= y - 30) && (state.getMouseY() <= y + 30) &&
				state.isMouseClicked()) 
			{
				//starts the wave
			
				state.startWave();
			}
	}

	/**
	 * Draws the icon on the menu.
	 * 
	 * @param g Graphics object
	 * @param view GameView object
	 */
	public void draw(Graphics g, GameView view) 
	{
		//draws the start button
		
		if(!state.isInWave()) 
		{
		view.drawCenteredImage(g,"startButton.png", x, y);
		}
	}
}
