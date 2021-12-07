/**
 * Draws a snow cloud effect after a snowball effect hits the targeted enemies. The 
 * snow cloud lasts for 0.25 seconds then dissapears after 
 * 
 * @author Gunnar and Kate
 * @verson 12/6/21
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
	 * Constructor loads snow cloud effect on given position.
	 * 
	 * @param state GameState object
	 * @param position where to load the snow cloud effect on the path
	 */
	public EffectSnowCloud(GameState state, int x, int y) 
	{
		super(state);
		
		//current position of snow cloud effect
		
		this.x = x;
		this.y = y;
		
		lifeTime = 0;
	}

	/**
	 * Records the lifeTime of the snow cloud effect and removes it after 
	 * 0.25 seconds of being drawn and it deletes itself if it is created 
	 * outside of the screen boundaries.
	 * 
	 * @param timeElapsed a double integer that is the time since the last 
	 * 					  update call originated in GameControl
	 */
	public void update(double timeElapsed) 
	{
		lifeTime += timeElapsed;
		
		//remove snow cloud effect after 0.25 seconds preceding initial drawing
		
		if(lifeTime > 0.25) 
		{
			state.removeGameObject(this);
			return;
		}
		
		//remove snow cloud effect if drawn outside of screen boundaries
		
		if(x >= 600 || x <= 0 || y <= 0 || y >= 600)
			state.removeGameObject(this);
	}

	/**
	 * Takes the "snowcloud.png" and uploads it into GameView to allow a visual 
	 * image for the snow cloud effect.
	 * 
	 * @param g Graphics object
	 * @param view GameView object
	 */
	public void draw(Graphics g, GameView view) 
	{
		//draw snow cloud effect
		
		view.drawCenteredImage(g, "snowcloud.png", x, y);
	}
}
