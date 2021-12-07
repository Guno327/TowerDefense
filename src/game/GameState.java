/*
 * Contains all functions related to keeping track of and manipulating the state of the game.
 * 
 * @author Gunnar and Kate
 * @version 12/6/21
 */
package game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

public class GameState {
	
	// Array lists that contain animatable objects to be added/removed and objects
	// that are currently being animated
	
	private List<Animatable> gameObjects;
	private List<Animatable> objectsToRemove;
	private List<Animatable> objectsToAdd;

	// Fields
	
	private int credits;
	private int life;
	private int score;
	private int mouseX, mouseY;
	private boolean mouseClicked;
	private double totalTime;
	private boolean started;
	private boolean inWave;
	private int wave;
	private boolean wavesStarted;
	private EnemyGenerator currentGenerator;

	/**
	 * Constructor for GameState. Sets up the lists for animatable objects, sets up
	 * mouse input, begins to track important values pertaining to the state of the
	 * game.
	 */
	public GameState() 
	{
		gameObjects = new ArrayList<Animatable>();
		objectsToRemove = new ArrayList<Animatable>();
		objectsToAdd = new ArrayList<Animatable>();

		mouseX = mouseY = 0;
		mouseClicked = false;

		credits = 200;
		life = 10;
		score = 0;
		started = false;
		inWave = false;
		wave = 1;
		wavesStarted = false;

		totalTime = 0.0;
		currentGenerator = null;
	}

	/**
	 * Adds an animatable object to the list of animatable objects to be added in
	 * the next update.
	 * 
	 * @param a object to be added to the list
	 */
	public void addGameObject(Animatable a) 
	{
		objectsToAdd.add(a);
	}

	/**
	 * Adds an animatable object to the list of animatable objects to be removed in
	 * the next update.
	 * 
	 * @param a object to be added to the list
	 */
	public void removeGameObject(Animatable a) 
	{
		objectsToRemove.add(a);
	}

	/**
	 * Accessor method used for accessing the score.
	 * 
	 * @return score integer value that sets the score.
	 */
	public int getScore() 
	{
		return score;
	}

	/**
	 * Accessor method used for accessing the life.
	 * 
	 * @return life integer value that sets the life.
	 */
	public int getLife() 
	{
		return life;
	}

	/**
	 * Accessor method used for accessing the credits.
	 * 
	 * @return credits integer value that sets the credits.
	 */
	public int getCredits() 
	{
		return credits;
	}

	/**
	 * Accessor method to determine whether its true that an enemy 
	 * wave is taking place.
	 * 
	 * @return inWave if the game is in the middle of an enemy wave.
	 */
	public boolean getWaveOver() 
	{
		return inWave;
	}

	/**
	 * Accessor method to access totalTime.
	 * 
	 * @return totalTime double value that records the total time 
	 * 					 since the program started.
	 */
	public double getTotalTime() 
	{
		return totalTime;
	}

	/**
	 * Mutator method that sets the score to a given value.
	 * 
	 * @param score integer value that sets the score.
	 */
	public void setScore(int score) 
	{
		this.score = score;
	}

	/**
	 * Mutator method that sets the life to a given value.
	 * 
	 * @param life integer value that sets the life.
	 */
	public void setLife(int life) 
	{
		this.life = life;
	}

	/**
	 * Mutator method that sets the credits to a given value.
	 * 
	 * @param credits integer value that sets the credits.
	 */
	public void setCredits(int credits) 
	{
		this.credits = credits;
	}

	/**
	 * Mutator method that sets the totalTime to a given value.
	 * 
	 * @param time double value that records the total time since the program
	 *             started.
	 */
	public void setTotalTime(double time) 
	{
		totalTime = time;
	}

	/**
	 * Set the location of the mouse to a specified location in the frame.
	 * 
	 * @param x x-coordinate to be set.
	 * @param y y-coordinate to be set.
	 */
	public void setMouseLocation(int x, int y) 
	{
		this.mouseX = x;
		this.mouseY = y;
	}

	/**
	 * Determines whether the enemy wave has started.
	 */
	public void waveOver() 
	{
			inWave = false; 
			wavesStarted = false;
			wave++;
	}

	/**
	 * Gets the current x position of the mouse.
	 * 
	 * @return mouseX integer value of the x position.
	 */
	public int getMouseX() 
	{
		return mouseX;
	}

	/**
	 * Gets the current y position of the mouse.
	 * 
	 * @return mouseY integer value of the y position.
	 */
	public int getMouseY() 
	{
		return mouseY;
	}

	/**
	 * Gets whether or not the mouse is currently clicked.
	 * 
	 * @return mouseClicked boolean specifying if the mouse is clicked.
	 */
	public boolean isMouseClicked() 
	{
		return mouseClicked;
	}

	/**
	 * Sets the mouse to be clicked.
	 */
	public void setMouseClicked() 
	{
		mouseClicked = true;
	}

	/**
	 * Sets the mouse to not be clicked.
	 */
	public void consumeMouseClick() 
	{
		mouseClicked = false;
	}

	/** 
	 * Determines if the enemy wave is in progress.
	 */
	public void startWave() 
	{
		inWave = true;
	}
	
	/**
	 * Store and update enemy generator separately.
	 * @param generator an EnemyGnenerator that generates enemies.
	 */
	public void setGenerator(EnemyGenerator generator) 
	{
		currentGenerator = generator;
	}

	/**
	 * Finds the closest enemy to the provided tower.
	 * 
	 * @param theTower The tower that you are checking locations from.
	 * @return closest enemy to the tower.
	 */

	public Enemy findNearestEnemy(Point location) 
	{
		//determines closest enemy
		
		Enemy closest = null;
		double distance = 0;

		//tracks the location of the closest enemy
		
		for (Animatable a : gameObjects) 
		{
			if (a instanceof Enemy) 
			{
				if (closest == null) 
				{
					closest = (Enemy) a;
					distance = ((Enemy) a).getLocation().distance(location);
				}
				
				//tracks distance of other enemies to see which enemy is cloer in 
				//range
				
				else 
				{
					if (distance > ((Enemy) a).getLocation().distance(location)) 
					{
						closest = (Enemy) a;
						distance = ((Enemy) a).getLocation().distance(location);
					}
				}
			}
		}
		return closest;
	}

	/**
	 * Removing all of the objects in the game.
	 */
	public void removeAllObjects() 
	{
		for (int i = 0; i < gameObjects.size(); i++)
			gameObjects.remove(i);
	}

	/**
	 * Adds all queued objects to the list of currently animating objects , removes
	 * all objects queued to be removed, then clears both queues before checking if
	 * the game is over. Iff the game is over then it clears all currently animated
	 * objects and adds a single game over object to be displayed on the next
	 * update.
	 * 
	 * @param elapsedTime A double containing how much time has passed since the
	 *                    last update call was made.
	 */
	public void updateAll(double elapsedTime) 
	{

		// if we are supposed to be in a wave but the enemy spawning hasnt started,
		// start the spawning
		
		if (inWave && !wavesStarted) 
		{
			wavesStarted = true;
			currentGenerator = new EnemyGenerator(this, "enemyList" + wave + ".txt");
			
		}
				// if we are in a wave we want to update everything
		
				if (inWave && wavesStarted) 
				{
					currentGenerator.update(elapsedTime);
					
					for (Animatable a : gameObjects) 
					{
						a.update(elapsedTime);
					}
				}
				
				// if we are not in a wave we don't want to update towers, there are not enemies
				// to be found
				
				else 
				{
					for (Animatable a : gameObjects) 
					{
						if (!(a instanceof TowerFire) && !(a instanceof TowerTree) && !(a instanceof TowerSnow)) 
						{
							a.update(elapsedTime);
						}
					}
				}
				//adds to game objects to remove and removes them
				
				gameObjects.removeAll(objectsToRemove);
				objectsToRemove.clear();

				//adds to game objects to add and adds them
				
				gameObjects.addAll(objectsToAdd);
				objectsToAdd.clear();
	}

	/**
	 * Redraws all currently animated objects.
	 * 
	 * @param g the graphics object to be used to draw all the animatable objects.
	 */
	public void drawAll(Graphics g, GameView view) 
	{
		for (Animatable a : gameObjects) 
		{
			a.draw(g, view);
		}
	}

	/**
	 * Determines if the game has started.
	 * If the start menu has been passed.
	 * 
	 * @return started determines game start
	 */
	public boolean isStarted()
	{
		return started;
	}

	/**
	 * Determines that the game has started.
	 */
	public void start() 
	{
		started = true;
	}

	/**
	 * Determines if the enemy wave is in progress.
	 * 
	 * @return inWave stating that the wave is in progress
	 */
	public boolean isInWave() 
	{
		return inWave;
	}

	/**
	 * Accessor method used for accessing the enemy wave.
	 * 
	 * @return wave the enemy wave
	 */
	public int getWave()
	{
		return wave;
	}

	/**
	 * An arraylist to keep track of all the enemies on the path.
	 * 
	 * @return enemies the enemies on the path
	 */
	public ArrayList<Enemy> findAllEnemies() 
	{
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		
		for(Animatable a : gameObjects) 
		{
			if(a instanceof Enemy) 
			{
				enemies.add((Enemy)a);
			}
		}
		
		return enemies;
		
	}

	/**
	 * Removes the enemy generator, stops generating enemies.
	 */
	public void removeGenerator() 
	{
		currentGenerator = null;
	}
}
