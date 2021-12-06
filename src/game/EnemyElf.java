/**
 * Constructor for enemy elves. Base level enemies, lowest health.
 * 
 * Gunnar and Kate
 * 11/23/21
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class EnemyElf extends Enemy implements Animatable
{
	
	//Fields
	private double position;
	private int health;
	
	/**
	 * Constructor for enemy elves, loads the elf on a specific percent of the path
	 * @param state GameState object
	 * @param position where to load the elf on the path
	 */
	public EnemyElf(GameState state, Double position) 
	{
		super(state);
		health = 5;
	}
	
	/**
	 * Runs every update. Progresses the enemy elves down the path a set amount per time.
	 * Removes the elf and decreases the life value if the elf goes past the end point of the path.
	 */
	public void update(double timeElapsed) 
	{
		if(health <= 0) {
			state.removeGameObject(this);
			state.setCredits(state.getCredits() + 10);
			state.setScore(state.getScore() + 100);
			return;
		}
		
		position = position + 0.060 * timeElapsed;	
		if (position > 1) 
		{
			state.removeGameObject(this);
			state.setLife(state.getLife() - 1);
		}
	}

	/**
	 * Draws the elf image at the current point on the path.
	 */
	public void draw(Graphics g, GameView view) 
	{
		view.drawCenteredImage(g,"maleElf.png", (int)getLocation().getX(), (int)getLocation().getY());
	}

	@Override
	public Point getLocation() {
		return ResourceLoader.getLoader().getPath("path.txt").getPathPosition(position);
	}

	public void hit(int damage) {
		health -= damage;
	}

}
