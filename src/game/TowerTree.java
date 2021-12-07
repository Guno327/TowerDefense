/**
 * Class for tree towers. Controls all of their behavior where their projectiles target
 * and tracks the coordinates of target.
 * 
 * @author Gunnar and Kate
 * @version 12/6/21
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class TowerTree extends Tower{
	
	//Fields
	private int x;
	private int y;
	private double timeSinceLastShot;
	
	/**
	 * Constructor for the tree towers. Sets up the location for them to be created.
	 * 
	 * @param state the GameState
	 * @param x x-position of the tower
	 * @param y y-position of the tower
	 */
	public TowerTree(GameState state, int x, int y) 
	{
		super(state, x, y);
		this.x = x;
		this.y = y;
		timeSinceLastShot = 0;
	}
	
	/**
	 * Get the location of the point where the player wants to place
	 * the tree tower using x and y coordinates.
	 * 
	 * @return x and y coordinates of tree tower location
	 */
	public Point getLocation() 
	{
		return new Point(x,y);
	}

	/**
	 * Find the nearest enemy and hits them with a ornament effect, targeting
	 * one enemy at a time and locating the targeted enemy's position.
	 * 
	 * @param timeElapsed a double integer that is the time since the last 
	 * 					  update call originated in GameControl
	 */
	public void update(double timeElapsed) 
	{
		//time recorded since the last shot of the ornament projectile was fired
		
		timeSinceLastShot += timeElapsed;
		
		//if the time recorded is less than one then the tree tower will not shoot
		//more ornament projectiles
		
		if(timeSinceLastShot < 1)
			return;
		
		//finds the nearest enemy
		
		Enemy targeted = state.findNearestEnemy(new Point(x, y));
		if(targeted == null) 
			return;
		
		//target the nearest enemy within a certain radius
		
		if(Math.abs(targeted.getLocation().getX() - x) < 250 &&
				Math.abs(targeted.getLocation().getY() - y) < 250) 
		{
			//target enemy and shoot projectile
			
			state.addGameObject(new EffectOrnament(state, new Point(x, y), targeted.getLocation()));
			timeSinceLastShot = 0;
		}
		
	}

	/**
	 * Draws the tree tower at the given x,y coordinate.
	 * 
	 * @param g Graphics object
	 * @param view GameView object
	 */
	public void draw(Graphics g, GameView view) 
	{
		//draws image of tree tower
		
		view.drawCenteredImage(g,"tree.png", x, y);
	}
}
