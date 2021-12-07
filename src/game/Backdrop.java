/*
 * An animatable object that stores and displays a background graphic.
 * 
 * @author Gunnar and Kate
 * @version 12/6/21
 */
package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Backdrop implements Animatable{
	
	//Fields
	BufferedImage image;
	
	/**
	 * Constructor for the backdrop class. Loads a buffered image with 
	 * the requested file name and stores it to be loaded in the future.
	 * 
	 * @param name File name of the background object to be loaded
	 */
	public Backdrop(String name) 
	{
		image = ResourceLoader.getLoader().getImage(name);
	}

	/**
	 * Required to implement animatable, but unneeded for this class.
	 */
	public void update(double timeElapsed) {}

	/**
	 * Draw function required to be animatable, just draws the stored image 
	 * to the screen.
	 */
	public void draw(Graphics g, GameView view) 
	{
		g.drawImage(image, 0, 0, null);
	}
	
	
}
