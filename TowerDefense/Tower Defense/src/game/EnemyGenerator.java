/**
 * Class that generates enemies on the path based on the time passed since the beginning of the program.
 * Which enemies and when they are spawned are based on a text file.
 * 
 * Gunnar and Kate
 * 11/23/21
 */
package game;

import java.awt.Graphics;
import java.util.Scanner;

public class EnemyGenerator implements Animatable{
	
	//Fields
	private double nextCheck;
	private Scanner list;
	private GameState state;
	
	/**
	 * Constructor for the enemy generator class. Makes a scanner to read through the list of enemies to be spawned.
	 * 
	 * @param state the GameState object
	 * @param fileName the name of the file that contains the list of enemies to be spawned 
	 */
	public EnemyGenerator(GameState state, String fileName){
		ClassLoader loader = this.getClass().getClassLoader();
    	list = new Scanner(loader.getResourceAsStream("resources/" + fileName));    	

		nextCheck = 0.25;
		this.state = state;
	}

	/**
	 * Runs every update. Checks to see if enough time has passed to trigger another spawn event.
	 * If the file has another entry then we check the entry.
	 * Spawn types are determined by numeric values.
	 * The switch case statement determines which enemy will be spawned, if the file contains a 0 nothing is spawned.
	 */
	public void update(double timeElapsed) {
		if(state.getTotalTime() >= nextCheck) {
			nextCheck += 0.25;
			if(list.hasNextInt()) {
				switch (list.nextInt()) {
				case 2:
					state.addGameObject(new EnemySanta(state, 0.0));
					return;
				case 1:
					state.addGameObject(new EnemyElf(state, 0.0));
					return;
				case 0:
				}
			}
		}
	}

	/**
	 * Required to be animatable. Unused in this class.
	 */
	public void draw(Graphics g, GameView view) {
		//your mom
	}

}
