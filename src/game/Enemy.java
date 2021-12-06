/**
 * Superclass for all enemy type objects. Any entity that moves down the path and can be targeted are considered enemies.
 * 
 * Gunnar and Kate
 * 11/23/21
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
	 * Constructor for all enemy objects. Loads the location as a Point object from a percentage of path progression.
	 * @param state The GameState object
	 * @param location percent of progression down the path
	 */
	public Enemy(GameState state, int health, double position) {
		this.state = state;
		isBurning = false;
		this.health = health;
		this.position = position;
		timeSinceBurn = 0;
	}
	
	public Point getLocation() {
		return ResourceLoader.getLoader().getPath("path.txt").getPathPosition(position);
	}
	
	public void burn() {
		isBurning = true;
	}
	
	public boolean getBurning() {
		return isBurning;
	}
	
	public void hit(int damage) {
		health -= damage;
		
	}
	
	public int getHealth() {
		return health;
	}
	
}
