/*
 * Class for the menu icons for the tree tower.
 * 
 * Gunnar and Kate
 * 11/23/21
 */
package game;

import java.awt.Graphics;

public class TowerTreeMenu extends Tower{

	//Fields
	private int x;
	private int y;
	
	/**
	 * Constructor for the menu icon of the tower.
	 * @param state the GameState
	 * @param x x-position to create the icon
	 * @param y y-position to create the icon
	 */
	public TowerTreeMenu(GameState state, int x, int y) {
		super(state,x,y);
		this.x = x;
		this.y = y;
	}

	/**
	 * Runs every update. Checks to see if the menu icon was clicked.
	 * Iff the menu icon was clicked it created a moving icon underneath the users cursor.
	 */
	public void update(double timeElapsed) {
		if((state.getMouseX() >= x - 30) && (state.getMouseX() <= x + 30) &&
				(state.getMouseY() >= y - 30) && (state.getMouseY() <= y + 30) &&
				state.isMouseClicked()) 
			{
				state.addGameObject(new TowerTreeMoving(state));
			}
	}

	/**
	 * Draws the icon on the menu.
	 */
	public void draw(Graphics g, GameView view) {
		view.drawCenteredImage(g,"tree.png", x, y);
	}
}
