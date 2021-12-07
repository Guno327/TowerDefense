/**
 * Superclass for all enemy type objects. Any entity that moves down the path and can be targeted are considered enemies.
 * 
 * @author Gunnar and Kate
 * @version 12/6/21
 */
package game;

import java.awt.Point;

abstract public class Enemy implements Animatable{
	
	//Fields
	
	protected GameState state;
	protected boolean isBurning;
	protected int health;
	protected double position;
	protected double timeSinceBurn;
	
	/**
	 * Constructor for all enemy objects. Loads the location as a Point 
	 * object from a percentage of path progression.
	 * 
	 * @param state The GameState object
	 * @param location percent of progression down the path
	 */
	public Enemy(GameState state, int health, double position) 
	{
		this.state = state;
		isBurning = false;
		this.health = health;
		this.position = position;
		timeSinceBurn = 0;
	}
	
	/**
	 * Converts double position down the path to an x,y coordinate.
	 * 
	 * @return point object in an x,y coordinate format
	 */
	public Point getLocation() 
	{
		return ResourceLoader.getLoader().getPath("path.txt").getPathPosition(position);
	}
	
	/**
	 * Sets the enemy to be burning.
	 */
	public void burn() 
	{
		isBurning = true;
	}
	
	/**
	 * Detects if the enemy is burning or not.
	 * 
	 * @return if the enemy is burning
	 */
	public boolean getBurning() 
	{
		return isBurning;
	}
	
	/**
	 * Records damage inflicted on enemy through health bar.
	 * 
	 * @param damage recorded for enemy if enemy is hit with a 
	 * 		  projectile or effect.
	 */
	public void hit(int damage) 
	{
		health -= damage;
		
	}
	
	/**
	 * Records and returns the health of the enemy.
	 * 
	 * @return the health of the enemy
	 */
	public int getHealth() 
	{
		return health;
	}
}
