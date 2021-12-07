/*
 * Class for the menu icons for the tree tower.
 * 
 * @author Gunnar and Kate
 * @version 12/6/21
 */
package game;

import java.awt.Graphics;

public class TowerTreeMenu extends Tower{

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
	public TowerTreeMenu(GameState state, int x, int y) 
	{
		super(state,x,y);
		this.x = x;
		this.y = y;
	}

	/**
	 * Runs every update. Checks to see if the menu icon was clicked.
	 * If the menu icon was clicked it created a moving icon underneath the users cursor.
	 * 
	 * @param timeElapsed a double integer that is the time since the last 
	 * 					  update call originated in GameControl
	 */
	public void update(double timeElapsed) 
	{
		//user clicks on the tree tower coordinates in the menu and tree tower
		//follows user curser
		
		if((state.getMouseX() >= x - 30) && (state.getMouseX() <= x + 30) &&
				(state.getMouseY() >= y - 30) && (state.getMouseY() <= y + 30) &&
				state.isMouseClicked()) 
			{
				state.addGameObject(new TowerTreeMoving(state));
			}
	}

	/**
	 * Draws the tree tower icon on the menu.
	 * 
	 * @param g Graphics object
	 * @param view GameView object
	 */
	public void draw(Graphics g, GameView view) 
	{
		//draws tree tower image in menu
		
		view.drawCenteredImage(g,"tree.png", x, y);
	}
}
