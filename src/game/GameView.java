/*
 * Contains all functions related to drawing the frame.
 * 
 * @author Gunnar and Kate
 * @version 12/6/21
 */
package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameView extends JPanel implements MouseListener, MouseMotionListener
{
	// This constant is needed to get rid of a warning.  It won't matter to us.
	
	private static final long serialVersionUID = 1L;
	
	// Fields
	
	private Path myPath;
	private GameState state;

	
	/**
	 * Constructor for the game view. Sets up the frame parameters before making it visible. Also sets up the path object to prepare
	 * for animation of enemy objects.
	 */
    public GameView (GameState state)
    {
    	//Initialize fields
    	
    	this.state = state;
	    myPath = ResourceLoader.getLoader().getPath("path.txt");

    	// Build the frame.  The frame object represents the application 'window'.
	    
    	JFrame frame = new JFrame ("Tower Defense 2021");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    	// Add a drawing area to the frame (a panel).  Note that 'this' object IS the
    	// panel that we need, so we add it.
    	
    	JPanel p = this;
    	frame.setContentPane(p);
    	
    	// Set the size of 'this' panel to match the size of the backdrop and menu. 
    	
    	Dimension d = new Dimension(1000, 600);
    	this.setMinimumSize(d);
    	this.setPreferredSize(d);
    	this.setMaximumSize(d);
    	
    	// Allow the JFrame to layout the window (by 'packing' it) and make it visible.
    	
    	frame.pack();
    	frame.setVisible(true);
    	
    	//Sets up the mouse listener to send the event to this class
    	
    	this.addMouseListener(this);
    	this.addMouseMotionListener(this);
    	
    }
    
    /**
     * Draws our game.  This function will be called automatically when Java needs to
     * repaint our window.  Use the repaint() function call (on this object) to cause
     * this function to be executed.
     * 
     * @param Graphics g  the Graphics object to use for drawing
     */
    public void paint (Graphics g)
    {
    		state.drawAll(g, this);
    }
    
    public void drawCenteredImage(Graphics g, String name, int x, int y) {
    	BufferedImage image = ResourceLoader.getLoader().getImage(name);
    	g.drawImage(image, (x - (image.getWidth()/2)), ((y - (image.getHeight()/2))), null);
    }
	
	/**
	 * If a mouse click is detected it changes the boolean variable in the game state that keeps track of mouse clicks.
	 */
	public void mousePressed(MouseEvent e) 
	{
		state.setMouseClicked();
	}
	/**
	 * The following methods are required to implement mouse listened, however they are unused by this class.
	 */
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	/**
	 * if the mouse is dragged it updates the mouses position in the GameState.
	 */
	public void mouseDragged(MouseEvent e) 
	{
		state.setMouseLocation(e.getX(), e.getY());
	}

	/**
	 * If the mouse is moved then the position of the mouse is updated in the GameState
	 */
	public void mouseMoved(MouseEvent e) 
	{
		state.setMouseLocation(e.getX(), e.getY());	
	}	
}
