/**
 * Superclass for all tower objects.
 * 
 * @author Gunnar and Kate
 * @version 12/6/21
 */
package game;

import java.awt.Point;

abstract public class Tower implements Animatable{

	//Fields
	protected GameState state;
	protected Point location;
	
	/**
	 * Constructor for tower objects. Stores the state, most of the 
	 * work is done in the individual tower subclasses.
	 * 
	 * @param state the GameState
	 */
	public Tower(GameState state) 
	{
		this.state = state;
		location = null;
	}
	
	/**
	 * Locates where the towers are placed around the path.
	 * 
	 * @param state the GameState 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public Tower(GameState state, int x, int y) 
	{
		this.state = state;
		location = new Point(x,y);
	}
	
	/**
	 * Produces the location of the tower around the path.
	 * 
	 * @return location of the tower
	 */
	public Point getLocation() 
	{
		return location;
	}
}
