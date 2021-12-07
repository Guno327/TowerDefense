/*
 * Class to create moving snowman tower icon underneath the user cursor.
 * 
 * @author Gunnar and Kate
 * @version 12/6/21
 */
package game;

import java.awt.Graphics;

public class TowerSnowMoving extends Tower{
	
	/**
	 * Constructor for the moving snowman tower icon.
	 * 
	 * @param state the GameState
	 */
	public TowerSnowMoving(GameState state) 
	{
		super(state);
	}

	/**
	 * Runs every update. Checks to see if the mouse was clicked in a valid area 
	 * to place the snowman tower. If it is && the user has sufficient credits then 
	 * a tower is created at the clicked location and the cost of the tower is subtracted 
	 * from the user credit balance.
	 * 
	 * @param timeElapsed a double integer that is the time since the last 
	 * 					  update call originated in GameControl
	 */
	public void update(double timeElapsed) 
	{
		//if the credits are the alotted amount to purchase the snowman tower than the user can
		//click and drag the mouse to preffered location around the path.
		
		if(state.getCredits() >= 100 && 
				state.getMouseX() <= 600 &&
				ResourceLoader.getLoader().getPath("path.txt").distanceToPath(state.getMouseX(), state.getMouseY()) > 45)
		{
			//deletes appropriate credits from user credits and moves the snowman tower with 
			//the user's cursor
			
			if(state.isMouseClicked()) 
			{
				state.removeGameObject(this);
				state.addGameObject(new TowerSnow(state, state.getMouseX(), state.getMouseY()));
				state.setCredits(state.getCredits() - 1000);
			}
		}
		else 
		{
			//if the user clicks the mouse around the path in preferred location ten the snowman
			//tower will be dropped there
			
			if(state.isMouseClicked()) 
			{
				state.removeGameObject(this);
			}
		}	
	}
		
	/**
	 * Draws the snowman tower at the specified location, centered on the image.
	 * 
	 * @param g Graphics object
	 * @param view GameView object
	 */
	public void draw(Graphics g, GameView view) 
	{
		//draw the snowman tower if its within the path screen's coordinates and not on the path
		//or menu
		
		view.drawCenteredImage(g, "snowman.png", state.getMouseX(), state.getMouseY());
		g.drawOval(state.getMouseX() - 200, state.getMouseY() - 200, 400, 400);
	}
}
