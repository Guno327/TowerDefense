/**
 * Constructor for enemy elves. Base level enemies, lowest health.
 * 
 * Gunnar and Kate
 * 11/23/21
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class EffectOrnament extends Effect implements Animatable
{
	//fields
	private int x,y,dx,dy;
	
	/**
	 * Constructor for enemy elves, loads the elf on a specific percent of the path
	 * @param state GameState object
	 * @param position where to load the elf on the path
	 */
	public EffectOrnament(GameState state, Point origin, Point target) 
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
			//damage enemy
			state.findNearestEnemy(new Point(x, y)).hit(5);
			//delete effect
			state.removeGameObject(this);
		}
		
		if(x >= 600 || x <= 0 || y <= 0 || y >= 600)
			state.removeGameObject(this);
	}

	public void draw(Graphics g, GameView view) 
	{
		view.drawCenteredImage(g, "ornament.png", x, y);
	}

}
