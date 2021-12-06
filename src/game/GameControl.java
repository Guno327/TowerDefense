/**
 *GameControl class. Contains all operations that pertain to controlling the game.
 *
 *Gunnar and Kate
 *11/23/21
 */
package game;

import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameControl implements Runnable, ActionListener
{
	
	GameView view;
	GameState state;
	
	long previousTime;
	
    public GameControl ()
    {
    	// All Code executes in the run function
    }
    
    
    public void run ()
    {    	
    	// Build the game state.
    	
    	state = new GameState ();
    	
    	// Build a view.
    	
    	view = new GameView (state);
    	
    	//Add objects required to begin the game. Backdrop, menu, and the enemy generator
    	
    	state.addGameObject(new Backdrop("path.png"));
    	state.addGameObject(new Menu("menuBackground.png", state));
    	try {
    	state.addGameObject(new EnemyGenerator(state, "enemyList.txt"));
    	}
    	catch (NullPointerException e) {
    		System.out.println("Cannot load enemy list");
    	}
    	
    	//Sets up and starts the timer that controls the game ticks
    	Timer timer;
    	timer = new Timer(16, this);
    	timer.start();
    	
    	//keep track of start time of the first tick.
    	previousTime = System.nanoTime();
    }
    
    /*
     * Called whenever our time declared above executes. Controls all actions that must be performed every tick.
     */
    public void actionPerformed (ActionEvent e) 
    {
    
    	//calculate elapsed time since last update (tick)
    	
    	long currentTime = System.nanoTime();
    	double elapsedTime = (currentTime - previousTime) / 1_000_000_000.0;
    	state.setTotalTime(state.getTotalTime() + elapsedTime);
    	previousTime = currentTime;
  
    	//update the game objects
    	try { 
    		state.updateAll(elapsedTime);
    		}
    	catch (NullPointerException z) {
    		System.out.println("No enemies were found, aborting tick.");
    		state.setWaveOver(true);
    	}
    	
    	
    	//Ensures there are no hanging mouse clicks
    	state.consumeMouseClick();
    	
    	//triggers a flag if the life falls below 0
    	if (state.getLife() <= 0) {
    		state.setGameOver(true);
    	}
    	
    	//redraws the frame
    	view.repaint ();
    }
}
