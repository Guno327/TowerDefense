/**
 *GameControl class. Contains all operations that pertain to controlling the game.
 *
 *@author Gunnar and Kate
 *@version 12/6/21
 */
package game;

import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameControl implements Runnable, ActionListener
{
	//fields
	GameView view;
	GameState state;
	long previousTime;
	
	/**
	 * Unused.
	 */
    public GameControl ()
    {
    	// All Code executes in the run function
    }
    
    /**
     * Builds the game state nad game view. Also sets a timer the records
     * game ticks and how long since the first game tick.
     */
    public void run ()
    {    	
    	// Build the game state.
    	
    	state = new GameState ();
    	
    	// Build a view.
    	
    	view = new GameView (state);

    	state.addGameObject(new Title(state));
    	
    	//Sets up and starts the timer that controls the game ticks
    	Timer timer;
    	timer = new Timer(16, this);
    	timer.start();
    	
    	//keep track of start time of the first tick.
    	previousTime = System.nanoTime();
    }
    
    /*
     * Called whenever our time declared above executes. Controls all actions 
     * that must be performed every tick.
     */
    public void actionPerformed (ActionEvent e) 
    {
    
    	//calculate elapsed time since last update (tick)
    	
    	long currentTime = System.nanoTime();
    	double elapsedTime = (currentTime - previousTime) / 1_000_000_000.0;
    	state.setTotalTime(state.getTotalTime() + elapsedTime);
    	previousTime = currentTime;
  
    	//update the game objects
    	
    	state.updateAll(elapsedTime);
    	
    	if(state.getWave() > 10) 
    	{
    		state.removeAllObjects();
    		state.addGameObject(new Backdrop("endScreen.png"));
    	}
    	//Ensures there are no hanging mouse clicks
    	
    	state.consumeMouseClick();
    	
    	//triggers a flag if the life falls below 0
    	
    	if (state.getLife() <= 0) 
    	{
    		state.removeAllObjects();
    		state.addGameObject(new GameOver(state));
    	}
    	
    	//redraws the frame
    	view.repaint ();
    }
}
