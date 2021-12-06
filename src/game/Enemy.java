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
	
	/**
	 * Constructor for all enemy objects. Loads the location as a Point object from a percentage of path progression.
	 * @param state The GameState object
	 * @param location percent of progression down the path
	 */
	public Enemy(GameState state) {
		this.state = state;
	}
	
	abstract public Point getLocation();
	
	abstract public void hit(int damage);
	
}
