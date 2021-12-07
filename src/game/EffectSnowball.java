/**
 * Makes snowball effect that travel from a provided point to another point
 * and delete themselves after interacting with enemies and spawns a snowCloud effect at the point of collision.
 * 
 * @author Gunnar and Kate
 * @version 12/6/21
 */
package game;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class EffectSnowball extends Effect implements Animatable
{
	//fields
	private int x,y,dx,dy;
	
	/**
	 * Constructor for spawning snowball effect at specified point.
	 * 
	 * @param state GameState object
	 * @param position where to load the snowball effect 
	 */
	public EffectSnowball(GameState state, Point origin, Point target) 
	{
		super(state);
		
		//current position of snowball effect
		
		x = (int) origin.getX();
		y = (int) origin.getY();
		
		//difference of the target and origin x and y's
		
		dx = (int) ((target.getX()) - origin.getX());
		dy = (int) ((target.getY()) - origin.getY());
	}

	/**
	 * Detects if the snowball effect hits enemies or leaves the screen. 
	 * Takes the nearest enemies location coordinates on an x,y axis and 
	 * if it hits the enemies or leaves the screen than the snowball effect 
	 * deletes itself along with deleting enemies if they interact. 
	 * 
	 * @param timeElapsed a double integer that is the time since the last 
	 * 					  update call originated in GameControl
	 */
	public void update(double timeElapsed) 
	{
		//time elapsed since the last update call
		
		x += dx * timeElapsed * 15;
		y += dy * timeElapsed * 15;
		
		//finds the nearest enemy's location
		
		if(state.findNearestEnemy(new Point (x,y)) == null)
			return;
		
		//deletes itself and the enemies if the snowball effect and enemies interact
		
		if(Math.abs((state.findNearestEnemy(new Point(x, y)).getLocation().getX() - x)) < 20 &&
				Math.abs((state.findNearestEnemy(new Point(x, y)).getLocation().getY() - y)) < 20) 
		{
			//Damage all enemies in range of the explosion
			
			ArrayList<Enemy> allEnemies = state.findAllEnemies();
			
			//finds location for snowman tower to target and launch snowball effect 
			
			for(Enemy e : allEnemies) 
			{
				if(Math.abs(e.getLocation().getX() - x) < 100 && Math.abs(e.getLocation().getY() - y) < 100) 
				{
					e.hit(25);
				}
			}
			//delete effect
			
			state.removeGameObject(this);
			state.addGameObject(new EffectSnowCloud(state, x, y));
		}
		
		//delete effect if it leaves the screen
		
		if(x >= 600 || x <= 0 || y <= 0 || y >= 600)
			state.removeGameObject(this);
	}

	/**
	 * Takes the "snowball.png" and uploads it into GameView to allow a visual 
	 * image for the snowball effect.
	 * 
	 * @param g Graphics object
	 * @param view GameView object
	 */
	public void draw(Graphics g, GameView view) 
	{
		//draws snowball effect
		
		view.drawCenteredImage(g, "snowball.png", x, y);
	}

}
