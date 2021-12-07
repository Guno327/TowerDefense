/*
 * Interface required for objects to be animated on the screen. Requires a draw and update function
 * 
 * @author Gunnar and Kate 
 * @version 12/6/21
 */
package game;

import java.awt.Graphics;

public interface Animatable 
{
	/**
	 * runs every tick, meant to allow functionality of various objects
	 * @param timeElapsed time since this function was last called as a double in seconds
	 */
    public void update(double timeElapsed);
    
    /**
     * function to draw an Animatable object to the screen
     * @param g graphics object to be used for drawing
     * @param view the GameView that controls all drawing of the game
     */
    public void draw(Graphics g, GameView view);
}
