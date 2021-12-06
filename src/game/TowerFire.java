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
	private double timeSinceLastBurn;
	
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
		timeSinceLastBurn = 0;
	}
	
	

	/**
	 * Required to implement animatable. Unused by this class
	 */
	public void update(double timeElapsed) {
		timeSinceLastBurn += timeElapsed;

		if (timeSinceLastBurn < 1)
			return;

		Enemy targeted = state.findNearestEnemy(new Point(x, y));

		if (Math.abs(targeted.getLocation().getX() - x) < 100 && Math.abs(targeted.getLocation().getY() - y) < 100) {
			targeted.burn();
			timeSinceLastBurn = 0;
		}
	}

	/**
	 * Draws the tower at the given location, Centered on the image
	 */
	public void draw(Graphics g, GameView view) {
		view.drawCenteredImage(g,"fireplace.png", x, y);
	}
}
