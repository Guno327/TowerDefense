/*
 * Loads path and image files and stores them in maps to be used again to avoid reloading files every time an assets is needed.
 * 
 * Gunnar and Kate
 * 
 * 11/23/21
 */
package game;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ResourceLoader {
	
	//Maps to store loaded assets
	Map<String, BufferedImage> images;
	Map<String, Path> paths;
	
	//Required to created a singleton instance
	static private ResourceLoader instance;
	
	/**
	 * private constructor. Will only execute one time and setup the maps for storing game assets.
	 */
	private ResourceLoader() {
		images = new HashMap<String, BufferedImage>();
		paths = new HashMap<String, Path>();
	}
	
	/**
	 * If the requested image file has not been loaded yet then the image is loaded from resources and stored in
	 * a map before being returned. If the image has already been loaded then it is retrieved from the map
	 * and returned
	 * @param key the file name of the image to be returned or loaded.
	 * @return Buffered Image containing the loaded version of the file requested
	 */
	public BufferedImage getImage(String key) {
		try {
			BufferedImage curImage;
			
			if(images.containsKey(key)) {
				curImage = images.get(key);
			}
			
			else {
			    	ClassLoader loader = this.getClass().getClassLoader();
			    	InputStream is = loader.getResourceAsStream("resources/" + key);
			    	curImage = javax.imageio.ImageIO.read(is);
			}
			return curImage;
		}
		catch(IOException e) {
			System.out.println("Unable to load image.");
			return null;
		}
	}
	
	/**
	 * If the requested path object has not been created yet then the path is created from the given text file name and stored in
	 * a map before being returned. If the path has already been created then it is retrieved from the map
	 * and returned
	 * @param key the file name of the text file to be used to create the path, or return a corresponding path
	 * @return Path object created using the text file name provided
	 */
	public Path getPath(String key) {
		Path curPath;
		
		if(paths.containsKey(key)) {
			curPath = paths.get(key);
		}
		
		else {
			ClassLoader loader = this.getClass().getClassLoader();
	    	Scanner pathScanner = new Scanner(loader.getResourceAsStream("resources/" + key));    	
	    	curPath = new Path(pathScanner);
		}
		
		return curPath;
	}
	
	/**
	 * gets the one created resource loader for use with other functions.
	 * @return resource loader object
	 */
	static public ResourceLoader getLoader()
    {
        if (instance == null)
          instance = new ResourceLoader ();

        return instance;
    }
}
