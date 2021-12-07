/**
 * Creates enemy santas, keeps track of health of player if an enemy leaves the screen, records burning 
 * effect on enemy santa while it goes down the path, and records credits and score when a santa is killed.
 * Santa object are larger enemies, have more health, and do more damage to player health than elves.
 * 
 * @author Gunnar and Kate
 * @version 12/6/21
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class EnemySanta extends Enemy implements Animatable
{
	/**
	 * Constructor for the enemy Santa class. Generates a Santa enemy 
	 * at a given position down the path.
	 * 
	 * @param state the GameState object
	 * @param position where to spawn the enemy, given as a percent 
	 * 		  traveled down the path.
	 */
	public EnemySanta(GameState state, Double position) 
	{
		super(state, 25, position);
	}
	
	/**
	 * Runs every update. Progressed the distance down the path by a set 
	 * amount based on how much time has passed since the last update.
	 * Records health of santa and credits scored when santa is killed
	 * by burning effect. Records health of player if santa reaches the 
	 * end of the path. 
	 * 
	 * @param timeElapsed a double integer that is the time since the 
	 * 					  last update call originated in GameControl
	 */
	public void update(double timeElapsed) 
	{
		//records time since santa started burning
		
		timeSinceBurn += timeElapsed;
		
		//if health of enemy santa is less than or equal to zero then 
		//enemy santa is removed and credits and score are updated.
		
		if(health <= 0) 
		{
			//enemy santa is deleted
			
			state.removeGameObject(this);
			
			//credits added
			
			state.setCredits(state.getCredits() + 100);
			
			//score added
			
			state.setScore(state.getScore() + 1000);
			return;
		}
		//the burning effect will only effect santa for 0.25 seconds and 
		//then will kill the enemy santa
		
		if(isBurning) 
		{
			//recording time since initially started burning, decreases enemy
			//health after 0.25 seconds since burning effect was placed on santa
			
			if(timeSinceBurn > 0.25) 
			{
				health -= 1;
				timeSinceBurn = 0;
			}
		}
		//records the position of santa moving down the path
		
		position = position + 0.030 * timeElapsed;	
		
		//if santa reaches the end of the path then the player will lose five life
		//and santa will be deleted from the game
		
		if (position > 1) 
		{
			//delete enemy santa
			
			state.removeGameObject(this);
			
			//deplete player's life score
			
			state.setLife(state.getLife() - 5);
		}
	}

	/**
	 * Draw method that displays the enemy santa and enemy santa burning at the 
	 * specified distance down the path.
	 * 
	 * @param g Graphics object
	 * @param view GameView object
	 */
	public void draw(Graphics g, GameView view) 
	{
		//image of santa burning, burning effect will follow santa downt he path for
		//0.25 seconds
		
		if(isBurning) 
		{
			view.drawCenteredImage(g,"santaBurning.png", (int)getLocation().getX(), (int)getLocation().getY());
			return;
		}
		
		//draw image of santa moving down the path.
		
    	view.drawCenteredImage(g,"santa.png", (int)getLocation().getX(), (int)getLocation().getY());
	}
}
