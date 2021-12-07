/*
 * Class for the menu icons for the snowman tower.
 * 
 * @author Gunnar and Kate
 * @version 12/6/21
 */
package game;

import java.awt.Graphics;

public class TowerSnowMenu extends Tower{

	//Fields
	private int x;
	private int y;
	
	/**
	 * Constructor for the menu icon of the snowman tower.
	 * 
	 * @param state the GameState
	 * @param x x-position to create the icon
	 * @param y y-position to create the icon
	 */
	public TowerSnowMenu(GameState state, int x, int y) 
	{
		super(state,x,y);
		this.x = x;
		this.y = y;
	}

	/**
	 * Runs every update. Checks to see if the menu snowman icon was clicked.
	 * If the menu icon was clicked it created a moving snowman icon underneath 
	 * the users cursor.
	 * 
	 * @param timeElapsed a double integer that is the time since the last 
	 * 					  update call originated in GameControl
	 */
	public void update(double timeElapsed) 
	{
		//if the mouseclicked the x,y coordinates of the snowman tower then
		//the player can move the snowman tower icon to wanted placement on path
		
		if((state.getMouseX() >= x - 30) && (state.getMouseX() <= x + 30) &&
				(state.getMouseY() >= y - 30) && (state.getMouseY() <= y + 30) &&
				state.isMouseClicked()) 
			{
				state.addGameObject(new TowerSnowMoving(state));
			}
	}

	/**
	 * Draws the snowman tower icon on the menu.
	 * 
	 * @param g Graphics object
	 * @param view GameView object
	 */
	public void draw(Graphics g, GameView view) 
	{
		//draw snowman tower icon on menu
		
		view.drawCenteredImage(g,"snowman.png", x, y);
	}
}
