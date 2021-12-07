/**
 * Makes ornament projectiles that travel from a provided point to another provided point.
 * delete themselves and interact with enemies if they collide.
 * delte themselves if they leave the visible are of the screen
 * 
 *@author Gunnar and Kate
 * @version 12/6/21
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class EffectOrnament extends Effect implements Animatable
{
	//fields
	private int x,y,dx,dy;
	
	/**
	 * Constructor for spawning ornament object at the specified tower.
	 * 
	 * @param state GameState object
	 * @param position where to load the ornament object 
	 */
	public EffectOrnament(GameState state, Point origin, Point target) 
	{
		super(state);
		
		//current position of ornament
		
		x = (int) origin.getX();
		y = (int) origin.getY();
		
		//difference of the target and origin x and y's
		
		dx = (int) ((target.getX()) - origin.getX());
		dy = (int) ((target.getY()) - origin.getY());
	}

	/**
	 * Detects if the ornament projectile hit an enemy or left the screen. 
	 * Takes the nearest enemies location coordinates on an x,y axis and if 
	 * it hits the enemy or leaves the screen than the ornament projectile 
	 * deletes itself along with deleting an enemy if they interact. 
	 * 
	 * @param timeElapsed a double integer that is the time since the last 
	 * 					  update call originated in GameControl
	 */
	public void update(double timeElapsed) 
	{
		//time elapsed since the last update call
		
		x += dx * timeElapsed * 15;
		y += dy * timeElapsed * 15;
		
		//find the enemy's location
		if(state.findNearestEnemy(new Point (x,y)) == null)
			return;
		
		//deletes itself and enemy if enemy and object interact
		
		if(Math.abs((state.findNearestEnemy(new Point(x, y)).getLocation().getX() - x)) < 20 &&
				Math.abs((state.findNearestEnemy(new Point(x, y)).getLocation().getY() - y)) < 20) 
		{
			//damage enemy
			
			state.findNearestEnemy(new Point(x, y)).hit(5);
			//delete effect
			
			state.removeGameObject(this);
		}
		
		//if the ornament object leaves the screen, it will be deleted
		
		if(x >= 600 || x <= 0 || y <= 0 || y >= 600)
			state.removeGameObject(this);
	}

	/**
	 * Takes the "ornament.png" and uploads it into GameView to allow a visual 
	 * image for the ornament projectiles.
	 * 
	 * @param g Graphics object
	 * @param view GameView object
	 */
	public void draw(Graphics g, GameView view) 
	{
		//draw ornament projectile
		
		view.drawCenteredImage(g, "ornament.png", x, y);
	}

}
