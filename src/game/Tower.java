/**
 * Superclass for all tower objects.
 * 
 * Gunnar and Kate
 */
package game;

import java.awt.Point;

abstract public class Tower implements Animatable{

	//Fields
	protected GameState state;
	protected Point location;
	
	/**
	 * Constructor for tower objects. Stores the state, most of the work is done in the individual tower subclasses.
	 * @param state the GameState
	 */
	public Tower(GameState state) {
		this.state = state;
		location = null;
	}
	
	public Tower(GameState state, int x, int y) {
		this.state = state;
		location = new Point(x,y);
	}
	
	public Point getLocation() {
		return location;
	}
	
}
