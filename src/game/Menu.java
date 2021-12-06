/**
 * Creates a menu object that contains all GUI elements for the game.
 * 
 * Gunnar and Kate
 * 11/23/21
 */
package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;

public class Menu implements Animatable{

	//Fields
	BufferedImage menuImage;
	GameState state;
	boolean undrawn;
	
	/**
	 * Constructor for the menu class. Gets the image for the menu from the provided image file name
	 * and sets up the menu to be drawn on the next update call
	 * @param name the file name of the image to be stored as the menu background
	 * @param state the GameState object
	 */
	public Menu(String name, GameState state) {
		menuImage = ResourceLoader.getLoader().getImage(name);
		this.state = state;
		undrawn = true;
	}

	/**
	 * Runs every update, should only execute code once. Adds all of the tower icons on the menu to be draw
	 * if they have not been draw yet.
	 */
	public void update(double timeElapsed) {
		if(undrawn) {
			state.addGameObject(new TowerTreeMenu(state, 640, 55));
			state.addGameObject(new TowerFireMenu(state, 845, 55));
			state.addGameObject(new StartButton(state , 900, 520));
			state.addGameObject(new TowerSnowMenu(state, 640, 165));
			undrawn = false;
		}
		
	}

	/**
	 * Draws the menu background and all text elements
	 */
	public void draw(Graphics g, GameView view) {
		g.drawImage(menuImage, 600, 0, null);
		
		//Draw menu texts
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman", Font.BOLD, 24)); 
		
		g.drawString("Credits: " + state.getCredits(), 620 , 475);
		g.drawString("Score: " + state.getScore(), 620 , 520);
		g.drawString("Life: " + state.getLife(), 620 , 565);
		
		g.setFont(new Font("TimesRoman", Font.BOLD, 12));
		g.drawString("100 Credits", 700, 65);
		g.drawString("500 Credits", 905, 65);
		g.drawString("1000 Credits", 700, 175);
	}

}
