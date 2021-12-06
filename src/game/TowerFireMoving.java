/**
 * Class that draws a fireplace tower icon under the users cursors once it has been selected form the menu.
 */
package game;

import java.awt.Graphics;

public class TowerFireMoving extends Tower{
	
	/**
	 * Constructor for the moving tower icon.
	 * @param state the GameState
	 */
	public TowerFireMoving(GameState state) {
		super(state);
	}

	/**
	 * Runs every update. Checks to see if the user has clicked on a valid area to place a tower.
	 * Iff they have && they have sufficient credits then the moving tower is deleted and a functioning
	 * tower is created at the clicked location.
	 */
	public void update(double timeElapsed) {
		if(state.getCredits() >= 500 && 
				state.getMouseX() <= 600 &&
				ResourceLoader.getLoader().getPath("path.txt").distanceToPath(state.getMouseX(), state.getMouseY()) > 45) 
		{
			if(state.isMouseClicked()) {
				state.removeGameObject(this);
				state.addGameObject(new TowerFire(state, state.getMouseX(), state.getMouseY()));
				state.setCredits(state.getCredits() - 500);
			}
		}
		else {
			if(state.isMouseClicked()) {
				state.removeGameObject(this);
			}
		}
		
			
	}
	
	/**
	 * Draws the tower icon underneath the users cursor, centered on the image.
	 */
	public void draw(Graphics g, GameView view) {
		view.drawCenteredImage(g,"fireplace.png", state.getMouseX(), state.getMouseY());
	}
}
