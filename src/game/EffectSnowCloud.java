/**
 * Constructor for enemy elves. Base level enemies, lowest health.
 * 
 * Gunnar and Kate
 * 11/23/21
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class EffectSnowCloud extends Effect implements Animatable
{
	//fields
	private int x,y;
	private double lifeTime;
	
	/**
	 * Constructor for enemy elves, loads the elf on a specific percent of the path
	 * @param state GameState object
	 * @param position where to load the elf on the path
	 */
	public EffectSnowCloud(GameState state, int x, int y) 
	{
		super(state);
		this.x = x;
		this.y = y;
		
		lifeTime = 0;
	}

	public void update(double timeElapsed) 
	{
		lifeTime += timeElapsed;
		
		if(lifeTime > 0.25) {
			state.removeGameObject(this);
			return;
		}
		
		if(x >= 600 || x <= 0 || y <= 0 || y >= 600)
			state.removeGameObject(this);
	}

	public void draw(Graphics g, GameView view) 
	{
		view.drawCenteredImage(g, "snowcloud.png", x, y);
	}

}
