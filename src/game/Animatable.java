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
    public void update(double timeElapsed);
    
    public void draw(Graphics g, GameView view);
}
