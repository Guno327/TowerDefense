/**
 * Class for snowman towers. Controls all of their behavior including projectiles and finding the
 * nearest enemies to hit with the projectiles. 
 * 
 * @author Gunnar and Kate
 * @version 12/6/21
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class TowerSnow extends Tower{
	
	//Fields
	
	private int x;
	private int y;
	private double timeSinceLastShot;
	
	/**
	 * Constructor for the snowman towers. Sets up the location for them to be created.
	 * 
	 * @param state the GameState
	 * @param x x-position of the tower
	 * @param y y-position of the tower
	 */
	public TowerSnow(GameState state, int x, int y) 
	{
		super(state, x, y);
		this.x = x;
		this.y = y;
		timeSinceLastShot = 0;
	}
	
	/**
	 * Get the location of the point where the player wants to place
	 * the snowman tower using x and y coordinates.
	 * 
	 * @return Point x and y coordinates
	 */
	public Point getLocation() 
	{
		return new Point(x,y);
	}

	/**
	 * Find the nearest enemy and hits them with a snowball effect, this can
	 * target multiple enemies.
	 * 
	 * @param timeElapsed a double integer that is the time since the last 
	 * 					  update call originated in GameControl
	 */
	public void update(double timeElapsed) 
	{
		//time recorded since the last time the tower shot
		
		timeSinceLastShot += timeElapsed;
		
		//if the time recorded since the last time the snowman tower shot a snow
		//ball is less than 3 then don't throw another snow ball
		
		if(timeSinceLastShot < 3)
			return;
		
		//targets the nearest enemy
		
		Enemy targeted = state.findNearestEnemy(new Point(x, y));
		if(targeted == null) 
			return;
		
		//targets and throws snowball at the nearest enemy's coordinates
		
		if(Math.abs(targeted.getLocation().getX() - x) < 200 &&
				Math.abs(targeted.getLocation().getY() - y) < 200) 
		{
			//hit the enemies with a snowball
			
			state.addGameObject(new EffectSnowball(state, new Point(x, y), targeted.getLocation()));
			timeSinceLastShot = 0;
		}
		
	}

	/**
	 * Draws the snowman tower at the given x,y coordinate.
	 * 
	 * @param g Graphics object
	 * @param view GameView object
	 */
	public void draw(Graphics g, GameView view) 
	{
		//draws snowman tower at given x, y coordinates 
		
		view.drawCenteredImage(g,"snowman.png", x, y);
	}
}
