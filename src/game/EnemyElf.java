/**
 * Creates enemy elves, keeps track of health of player if an enemy leaves the screen, records burning 
 * effect on enemy elf while it goes down the path, and records credits and score when an elf is killed.
 * 
 * @param Gunnar and Kate
 * @version 12/6/21
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class EnemyElf extends Enemy implements Animatable
{
	/**
	 * Constructor for enemy elves, loads the elf on a specific 
	 * percent of the path.
	 * 
	 * @param state GameState object
	 * @param position where to load the elf on the path
	 */
	public EnemyElf(GameState state, Double position) 
	{
		super(state, 5, position);
	}
	
	/**
	 * Runs every update. Progresses the enemy elves down the path 
	 * a set amount per time. Removes the elf and decreases the life 
	 * value if the elf goes past the end point of the path.
	 * 
	 * @param timeElapsed a double integer that is the time since the 
	 * 					  last update call originated in GameControl
	 */
	public void update(double timeElapsed) 
	{
		//keeps track of when it last burned an enemy, so not to burn 
		//too many enemies at once
		
		timeSinceBurn += timeElapsed;
		
		//if the health is less than or equal to zero than kill an enemy 
		//and add appropriate credits and score for player.
		
		if(health <= 0) 
		{
			//delete enemy
			
			state.removeGameObject(this);
			
			//add credits
			
			state.setCredits(state.getCredits() + 10);
			
			//add score
			
			state.setScore(state.getScore() + 100);
			return;
		}
		
		//if enemy is burning than set a specified amount of time for enemy 
		//to burn, burn effect will disappear after 0.25 seconds since the 
		//enemy first started to burn.
		
		if(isBurning) 
		{
			//stop burn effect after 0.25 seconds and add decrease enemy health
			
			if(timeSinceBurn > 0.25) 
			{
				health -= 1;
				timeSinceBurn = 0;
			}
		}
		
		//moving enemy down the path
		
		position = position + 0.060 * timeElapsed;	
		
		//if the enemy moves all the way down the path, off the screen, then 
		//the enemy is removed and damages player health by one.
		
		if (position > 1) 
		{
			//remove enemy
			
			state.removeGameObject(this);
			
			//remove one health from player
			
			state.setLife(state.getLife() - 1);
		}
	}

	/**
	 * Draws the elf image at the current point on the path. Draws the burning 
	 * effect on the elf if the fire tower interacts with the elf and follows the 
	 * elf down the path until the elf is finally deleted after fire depletes 
	 * its health.
	 * 
	 * @param g Graphics object
	 * @param view GameView object
	 */
	public void draw(Graphics g, GameView view) 
	{
		//if the elf is burning than the fire effect will follow the elf down the path for 0.25 seconds
		
		if(isBurning) 
		{
			view.drawCenteredImage(g,"maleElfBurning.png", (int)getLocation().getX(), (int)getLocation().getY());
			return;
		}
		
		//draws elf down the path
		
		view.drawCenteredImage(g,"maleElf.png", (int)getLocation().getX(), (int)getLocation().getY());
	}
}
