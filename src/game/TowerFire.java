/**
 * Specific class for all fireplace towers. Controls their behavior.
 * 
 * Gunnar and Kate
 * 11/23/21
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class TowerFire extends Tower{
	
	//Fields
	
	private int x;
	private int y;
	private double timeSinceLastBurn;
	
	/**
	 * Constructor for fireplace towers. Sends the state to the superclass 
	 * and stores the location of which to instantiate the object.
	 * 
	 * @param state in the GameState
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public TowerFire(GameState state, int x, int y) 
	{
		super(state, x, y);
		this.x = x;
		this.y = y;
		timeSinceLastBurn = 0;
	}
	
	/**
	 * Finds the nearest enemy and implements the burn effect which then
	 * depletes the enemy's health.
	 * 
	 * @param timeElapsed a double integer that is the time since the last 
	 * 					  update call originated in GameControl
	 */
	public void update(double timeElapsed) 
	{
		//record time since enemy started burning
		
		timeSinceLastBurn += timeElapsed;

		//stop fireplace from burning multiple enemies and stacking burns
		//on same enemy within a second of the first burn
		
		if (timeSinceLastBurn < 1)
			return;

		//find the nearest enemy's x and y coordinates
		
		Enemy targeted = state.findNearestEnemy(new Point(x, y));
		if(targeted == null) 
			return;
		
		//target the nearest enemy
		
		if (Math.abs(targeted.getLocation().getX() - x) < 100 && Math.abs(targeted.getLocation().getY() - y) < 100) {
			targeted.burn();
			timeSinceLastBurn = 0;
		}
	}

	/**
	 * Draws the fireplace tower at the given location, centered on the image.
	 * 
	 * @param g Graphics object
	 * @param view GameView object
	 */
	public void draw(Graphics g, GameView view) 
	{
		//draw fireplace image tower
		
		view.drawCenteredImage(g,"fireplace.png", x, y);
	}
}
