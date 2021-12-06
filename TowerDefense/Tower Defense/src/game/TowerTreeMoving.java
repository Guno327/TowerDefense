/*
 * Class to create moving tree tower icon underneath the user cursor
 * 
 * Gunnar and Kate
 * 11/23/21
 */
package game;

import java.awt.Graphics;

public class TowerTreeMoving extends Tower{
	
	/**
	 * Constructor for the moving tree icon.
	 * @param state the GameState
	 */
	public TowerTreeMoving(GameState state) {
		super(state);
	}

	/**
	 * Runs every update. Checks to see if the mouse was clicked in a valid area to place a tower.
	 * Iff it is && the user has sufficient credits then a tower is created at the clicked location
	 * and the cost of the tower is subtracted from the user credit balance.
	 */
	public void update(double timeElapsed) {
		if(state.getCredits() >= 100 && 
				state.getMouseX() <= 600 &&
				ResourceLoader.getLoader().getPath("path.txt").distanceToPath(state.getMouseX(), state.getMouseY()) > 45)
		{
			if(state.isMouseClicked()) {
				state.removeGameObject(this);
				state.addGameObject(new TowerTree(state, state.getMouseX(), state.getMouseY()));
				state.setCredits(state.getCredits() - 100);
			}
		}
		else {
			if(state.isMouseClicked()) {
				state.removeGameObject(this);
			}
		}
			
	}
		
	/**
	 * Draws the tower at the specified location, centered on the image
	 */
	public void draw(Graphics g, GameView view) {
		view.drawCenteredImage(g, "tree.png", state.getMouseX(), state.getMouseY());
		g.drawOval(state.getMouseX() - 250, state.getMouseY() - 250, 500, 500);
	}
}
