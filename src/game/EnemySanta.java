/**
 * Class for enemy Santa objects. Large enemies. Have more health and do more damage that elves.
 * 
 * Gunnar and Kate
 * 11/23/21
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class EnemySanta extends Enemy implements Animatable
{
	
	//Fields
	private double position;
	private int health;
	
	/**
	 * Constructor for the enemy Santa class. Generates a Santa enemy at a given position down the path.
	 * @param state the GameState object
	 * @param position where to spawn the enemy, given as a percent traveled down the path.
	 */
	public EnemySanta(GameState state, Double position) 
	{
		super(state);
		health = 25;
	}
	
	/**
	 * Runs every update. Progressed the distance down the path by a set amount based on how much time has passed since the last update.
	 */
	public void update(double timeElapsed) 
	{
		if(health <= 0) {
			state.removeGameObject(this);
			state.setCredits(state.getCredits() + 100);
			state.setScore(state.getScore() + 1000);
			return;
		}
		
		position = position + 0.030 * timeElapsed;	
		if (position > 1) 
		{
			state.removeGameObject(this);
			state.setLife(state.getLife() - 5);
		}
	}
	
	public Point getLocation() {
		return ResourceLoader.getLoader().getPath("path.txt").getPathPosition(position);
	}

	/**
	 * Draw method that displays the enemy santa at the specified distance down the path.
	 */
	public void draw(Graphics g, GameView view) 
	{
    	view.drawCenteredImage(g,"santa.png", (int)getLocation().getX(), (int)getLocation().getY());
	}

	public void hit(int damage) {
		health -= damage;
		
	}


	

}
