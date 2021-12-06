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

	
	
	/**
	 * Constructor for the enemy Santa class. Generates a Santa enemy at a given position down the path.
	 * @param state the GameState object
	 * @param position where to spawn the enemy, given as a percent traveled down the path.
	 */
	public EnemySanta(GameState state, Double position) 
	{
		super(state, 25, position);
	}
	
	/**
	 * Runs every update. Progressed the distance down the path by a set amount based on how much time has passed since the last update.
	 */
	public void update(double timeElapsed) 
	{
		timeSinceBurn += timeElapsed;
		
		if(health <= 0) {
			state.removeGameObject(this);
			state.setCredits(state.getCredits() + 100);
			state.setScore(state.getScore() + 1000);
			return;
		}
		
		if(isBurning) {
			if(timeSinceBurn > 0.25) {
				health -= 1;
				timeSinceBurn = 0;
			}
		}
		
		position = position + 0.030 * timeElapsed;	
		if (position > 1) 
		{
			state.removeGameObject(this);
			state.setLife(state.getLife() - 5);
		}
	}

	/**
	 * Draw method that displays the enemy santa at the specified distance down the path.
	 */
	public void draw(Graphics g, GameView view) 
	{
		if(isBurning) {
			view.drawCenteredImage(g,"santaBurning.png", (int)getLocation().getX(), (int)getLocation().getY());
			return;
		}
		
    	view.drawCenteredImage(g,"santa.png", (int)getLocation().getX(), (int)getLocation().getY());
	}

	
	
	


	

}
