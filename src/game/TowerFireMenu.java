/*
 * Class for drawing the menu icon for fireplace towers.
 * 
 * @author Gunnar and Kate
 * @version 12/6/21
 */
package game;

import java.awt.Graphics;

public class TowerFireMenu extends Tower{

	//Fields
	
	private int x;
	private int y;
	
	/**
	 * Constructor for the moving tower icons.
	 * 
	 * @param state the GameState
	 * @param x x-point of the tower icon
	 * @param y y-point of the tower icon
	 */
	public TowerFireMenu(GameState state, int x, int y) 
	{
		super(state,x,y);
		this.x = x;
		this.y = y;
	}

	/**
	 * Runs ever update. Checks to see if the tower icon has been clicked on.
	 * If it has then it created a moving tower icon under the users cursor.
	 */
	public void update(double timeElapsed) 
	{
		//finds the place where the player wants to put the fireplace tower
		
		if((state.getMouseX() >= x - 30) && (state.getMouseX() <= x + 30) &&
			(state.getMouseY() >= y - 30) && (state.getMouseY() <= y + 30) &&
			state.isMouseClicked()) 
		{
			state.addGameObject(new TowerFireMoving(state));
		}
	}

	/**
	 * Draws the icon of the tower on the menu.
	 */
	public void draw(Graphics g, GameView view)
	{
		//draws fireplace where the player places tower
		
		view.drawCenteredImage(g,"fireplace.png", x, y);
	}
}
