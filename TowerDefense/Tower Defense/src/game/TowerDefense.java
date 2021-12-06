/**
 * The main class for the game. This is where the game is run from and sets up all of the classes that are required for the game to function
 * 
 * Gunnar and Kate
 * 11/23/21
 */
package game;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

public class TowerDefense 
{
	/*
	 * Main section of the class. Where everything is run.
	 */
	public static void main(String[] args) throws InvocationTargetException, InterruptedException 
	{
		//Build a new game control object
		GameControl gc = new GameControl();
		
		//Sets up GUI thread and automatically calls the run function in our game control object
		SwingUtilities.invokeAndWait(gc);
	}

}
