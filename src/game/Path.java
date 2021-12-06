/*
 * Creates an object to store information pertaining to the game path. It is stored as a collection of points in
 * parallel arrays.
 * 
 * Gunnar and Kate
 * 11/23/21
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Scanner;

public class Path {
	
	//Fields
	private int numPoints;
	private int[] x;
	private int[] y;
	
	 /** Constructor for the path class. Reads from the scanner provided and store all of the 
	  * points in parallel arrays to be used by other class functions.
     * @param s  a Scanner set up by the caller to provide a list of coordinates
     */
	public Path(Scanner s) {
		numPoints = s.nextInt();
		x = new int[numPoints];
		y = new int[numPoints];
		int current = 0;
		
		while(s.hasNext()) {
			x[current] = s.nextInt();
			y[current] = s.nextInt();
			current++;
		}
	}
	
    /**
      * Draws the current path to the screen (to wherever the graphics object points)
      * using a highly-visible color.
      * 
      * @param g   a graphics object
      */  
	public void drawPath(Graphics g) {
		g.setColor(Color.red);
		
		for(int countLines = 0; countLines < numPoints - 1; countLines++) {
			g.drawLine(x[countLines], y[countLines], x[countLines + 1], y[countLines + 1]);
		}
	}
	
	/** 
	 * Returns the total length of the path. Since the path
	 * is specified using screen coordinates, the length is
	 * in pixel units (by default).
	 * 
	 * @return the length of the path
	 */
	 public double getPathLength() {
		 double length = 0;
		 
		 for(int currentP = 0; currentP < numPoints - 1; currentP++) {
			 length += distanceBetween(x[currentP + 1], x[currentP], y[currentP + 1], y[currentP]);	
		 }
		 
		 return length;
	 }
	 
	 /** 
	  * Returns a Point object containing the location of a percent distance down the path.
	  * @param percentTraveled a distance along the path
	  * @return the screen coordinate of this position along the path
	  */
	  public Point getPathPosition (double percentTraveled) {
		  int ansX;
		  int ansY;
		  
		  if(percentTraveled >= 0 && percentTraveled <= 1) {
			  double distanceNeeded = (percentTraveled * getPathLength());
			  int point = 0;
			  boolean found = false;
			  
			  while(!found) {
				  double distance = distanceBetween(x[point], x[point + 1], y[point], y[point + 1]);
				  if(distanceNeeded > distance) {
					  distanceNeeded -= distance;
					  point++;
				  }
				  
				  else {
					  found = true;
				  }
			  }
			  
			  double percent = distanceNeeded / distanceBetween(x[point], x[point + 1], y[point], y[point + 1]);
			  
			  ansX =(int) (x[point] * (1-percent) + x[point + 1] * percent);
			  ansY = (int) (y[point] * (1-percent) + y[point + 1] * percent);
		  }
		  
		  else if(percentTraveled < 0){
			  ansX = x[0];
			  ansY = y[0];
		  }
		  
		  else {
			  ansX = x[numPoints - 1];
			  ansY = y[numPoints -1];
		  }
		  
		  return new Point(ansX, ansY);
		  
	  }
	  
	  /**
	   * Calculated the distance between two points in space.
	   * @param xOne x-value of the first point
	   * @param xTwo x-value of the second point
	   * @param yOne y-value of the first point
	   * @param yTwo y-value of the second point
	   * @return a double value containing the calculated distance between the two points.
	   */
	  private static double distanceBetween(int xOne, int xTwo, int yOne, int yTwo) {
		  double distance = Math.sqrt((xTwo - xOne) * (xTwo - xOne) + 
					 (yTwo - yOne) * (yTwo - yOne));
		  return distance;
	  }
	  
	  public double distanceToPath(int clickX, int clickY) {
		  //Initialize starting points
		  double distance = distanceBetween(clickX, (int)getPathPosition(0).getX(), clickY, (int)getPathPosition(0).getY());
		  
		  //find the nearest point
		  for(double i = 0.001; i <= 1.0; i += 0.001) {
			  double currentDistance = distanceBetween(clickX, (int)getPathPosition(i).getX(), clickY, (int)getPathPosition(i).getY());
			  if(distance > currentDistance) {
				  distance = currentDistance;
			  }
		  }
		  
		  return distance;
	  }
}
