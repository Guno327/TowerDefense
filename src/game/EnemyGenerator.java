/**
 * Class that generates enemies on the path based on the time passed since the beginning of the program.
 * Which enemies and when they are spawned are based on a text file.
 * 
 * @author Gunnar and Kate
 * @version 12/6/21
 */
package game;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Scanner;

public class EnemyGenerator implements Animatable {

	// Fields
	
	private double sinceCheck;
	private Scanner list;
	private GameState state;

	/**
	 * Constructor for the enemy generator class. Makes a scanner to read through
	 * the list of enemies to be spawned.
	 * 
	 * @param state    the GameState object
	 * @param fileName the name of the file that contains the list of enemies to be
	 *                 spawned
	 */
	public EnemyGenerator(GameState state, String fileName) 
	{
		//takes text file to implement when enemies are spawned
		
		ClassLoader loader = this.getClass().getClassLoader();
		list = new Scanner(loader.getResourceAsStream("resources/" + fileName));

		sinceCheck = 0;
		this.state = state;
	}

	/**
	 * Runs every update. Checks to see if enough time has passed to trigger another
	 * spawn event. If the file has another entry then we check the entry. Spawn
	 * types are determined by numeric values. The switch case statement determines
	 * which enemy will be spawned, if the file contains a 0 nothing is spawned.
	 * 
	 * @param timeElapsed a double integer that is the time since the last update 
	 * 					  call originated in GameControl
	 */
	public void update(double timeElapsed) 
	{
		//add time to the last time the text file was read 
		
		sinceCheck += timeElapsed;
		
		//read the next number in the text file every 0.25 seconds
		
		if (sinceCheck >= 0.25) 
		{
			//read next number in text file
			
			sinceCheck = 0;
			if (list.hasNextInt()) 
			{
				//switch case for spawning enemy elf or enemy santa depending on 
				//the number read in the text file
				
				switch (list.nextInt()) 
				{
				
				//if number = 2 then spawn an enemy santa
				
				case 2:
					state.addGameObject(new EnemySanta(state, 0.0));
					return;
					
				//if number = 1 then spawn an enemy elf
					
				case 1:
					state.addGameObject(new EnemyElf(state, 0.0));
					return;
					
				//if number = 0, don't spawn an enemy
					
				case 0:
					return;
				}
			}
			//if there are no text files left than the enemy wave is over and the enemy
			//generator stops
			
			else if(state.findNearestEnemy(new Point(0,0)) == null){
				state.waveOver();
				state.removeGenerator();
				state.setGenerator(new EnemyGenerator(state, "enemyList" + state.getWave() + ".txt"));
			}
		}
	}

	/**
	 * Required to be animatable. Unused in this class.
	 */
	public void draw(Graphics g, GameView view) {}

}
