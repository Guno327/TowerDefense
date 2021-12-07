/*
 * Class to create moving tree tower icon underneath the user cursor.
 * 
 * @author Gunnar and Kate
 * @version 12/6/21
 */
package game;

import java.awt.Graphics;

public class TowerTreeMoving extends Tower{
	
	/**
	 * Constructor for the moving tree icon.
	 * 
	 * @param state the GameState
	 */
	public TowerTreeMoving(GameState state) 
	{
		super(state);
	}

	/**
	 * Runs every update. Checks to see if the mouse was clicked in a valid 
	 * area to place a tower. If it is && the user has sufficient credits then
	 * a tower is created at the clicked location and the cost of the tower is 
	 * subtracted from the user credit balance.
	 * 
	 * @param timeElapsed a double integer that is the time since the last 
	 * 					  update call originated in GameControl
	 */
	public void update(double timeElapsed) 
	{
		//if there are enough credits then the user can pick up tree tower
		
		if(state.getCredits() >= 100 && 
				state.getMouseX() <= 600 &&
				ResourceLoader.getLoader().getPath("path.txt").distanceToPath(state.getMouseX(), state.getMouseY()) > 45)
		{
			//the tree tower icon will followed the user's mouse once clicked on 
			//and credits will decrease by the amount the tower is worth
			
			if(state.isMouseClicked()) 
			{
				state.removeGameObject(this);
				state.addGameObject(new TowerTree(state, state.getMouseX(), state.getMouseY()));
				state.setCredits(state.getCredits() - 100);
			}
		}
		else {
			if(state.isMouseClicked()) 
			{
				state.removeGameObject(this);
			}
		}	
	}
		
	/**
	 * Draws the tree tower at the specified location, centered on the image.
	 * 
	 * @param g Graphics object
	 * @param view GameView object
	 */
	public void draw(Graphics g, GameView view) 
	{
		//draws tree tower image at location user clicks on that is not the menu or path
		
		view.drawCenteredImage(g, "tree.png", state.getMouseX(), state.getMouseY());
		g.drawOval(state.getMouseX() - 250, state.getMouseY() - 250, 500, 500);
	}
}
