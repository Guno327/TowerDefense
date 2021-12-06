/**
 * Class for tree towers. Controls all of their behavior
 * 
 * Gunnar and Kate
 * 11/23/21
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class TowerTree extends Tower{
	
	//Fields
	private int x;
	private int y;
	private double timeSinceLastShot;
	
	/**
	 * Constructor for the tree towers. Sets up the location for them to be created.
	 * @param state the GameState
	 * @param x x-position of the tower
	 * @param y y-position of the tower
	 */
	public TowerTree(GameState state, int x, int y) {
		super(state, x, y);
		this.x = x;
		this.y = y;
		timeSinceLastShot = 0;
	}
	
	public Point getLocation() {
		return new Point(x,y);
	}

	/**
	 * Required to implement animatable. Unused by this class
	 */
	public void update(double timeElapsed) {
		
		timeSinceLastShot += timeElapsed;
		
		if(timeSinceLastShot < 1)
			return;
		
		Enemy targeted = state.findNearestEnemy(new Point(x, y));
		
		if(Math.abs(targeted.getLocation().getX() - x) < 250 &&
				Math.abs(targeted.getLocation().getY() - y) < 250) {
			state.addGameObject(new EffectOrnament(state, new Point(x, y), targeted.getLocation()));
			timeSinceLastShot = 0;
		}
		
	}

	/**
	 * Draws the tower at the given x,y coordinate
	 */
	public void draw(Graphics g, GameView view) {
		view.drawCenteredImage(g,"tree.png", x, y);
	}
}