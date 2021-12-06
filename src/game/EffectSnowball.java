/**
 * Constructor for enemy elves. Base level enemies, lowest health.
 * 
 * Gunnar and Kate
 * 11/23/21
 */
package game;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class EffectSnowball extends Effect implements Animatable
{
	//fields
	private int x,y,dx,dy;
	
	/**
	 * Constructor for enemy elves, loads the elf on a specific percent of the path
	 * @param state GameState object
	 * @param position where to load the elf on the path
	 */
	public EffectSnowball(GameState state, Point origin, Point target) 
	{
		super(state);
		x = (int) origin.getX();
		y = (int) origin.getY();
		dx = (int) ((target.getX()) - origin.getX());
		dy = (int) ((target.getY()) - origin.getY());
		
	}

	public void update(double timeElapsed) 
	{
		
		x += dx * timeElapsed * 15;
		y += dy * timeElapsed * 15;
		
		if(state.findNearestEnemy(new Point (x,y)) == null)
			return;
		
		if(Math.abs((state.findNearestEnemy(new Point(x, y)).getLocation().getX() - x)) < 20 &&
				Math.abs((state.findNearestEnemy(new Point(x, y)).getLocation().getY() - y)) < 20) {
			//Damage all enemies in range of the explosion
			ArrayList<Enemy> allEnemies = state.findAllEnemies();
			
			for(Enemy e : allEnemies) {
				if(Math.abs(e.getLocation().getX() - x) < 100 && Math.abs(e.getLocation().getY() - y) < 100) {
					e.hit(25);
				}
			}
			//delete effect
			state.removeGameObject(this);
			state.addGameObject(new EffectSnowCloud(state, x, y));
		}
		
		if(x >= 600 || x <= 0 || y <= 0 || y >= 600)
			state.removeGameObject(this);
	}

	public void draw(Graphics g, GameView view) 
	{
		view.drawCenteredImage(g, "snowball.png", x, y);
	}

}
