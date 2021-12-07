/*
 * Superclass for all effect objects. Effects are any animatable entity on the screen that are not an enemy or tower.
 * 
 * @author Gunnar and Kate
 * @version 12/6/21
 */
package game;

abstract public class Effect implements Animatable{
	
	//Fields
	protected GameState state;

	
	/**
	 * Constructor for all effect objects. Stores the GameState and location (as a point object)
	 * 
	 * @param x, x value of the location to be initalized
	 * @param y, y value of the location to be initalized
	 * @param state, the GameState object
	 */
	public Effect(GameState state) 
	{
		this.state = state;
	}
}
