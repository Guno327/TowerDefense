/**
 * Specific class for all fireplace towers. Controls their behavior.
 * 
 * Gunnar and Kate
 * 11/23/21
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class TowerFire extends Tower{
	
	//Fields
	private int x;
	private int y;
	
	/**
	 * Constructor for fireplace towers. Sends the state to the superclass and stores the location of which to instantiate the object.
	 * @param state
	 * @param x
	 * @param y
	 */
	public TowerFire(GameState state, int x, int y) {
		super(state, x, y);
		this.x = x;
		this.y = y;
	}
	
	

	/**
	 * Required to implement animatable. Unused by this class
	 */
	public void update(double timeElapsed) {
	}

	/**
	 * Draws the tower at the given location, Centered on the image
	 */
	public void draw(Graphics g, GameView view) {
		view.drawCenteredImage(g,"fireplace.png", x, y);
	}
}
