/**
 * ReadFile.java
 * 
 * This reads file and 
 * 
 * @author Mialy Andrianarivony
 */

package tsp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * ReadFile - reads one file at a time and return 2D ArrayList of the cities 
 * 
 */
public class ReadFile {
	/**
	 * Called for every line in the file 
	 * Remove any tabs and whitespaces to make an array 
	 * Each item in the array is then converted to Integer and returned to getCoordinates
	 * 
	 * @param line
	 * @return cityArr (type: List<Integer>)
	 * 		where it contains (id, x , y)
	 */
	public static List<Integer> split(String line) {
		ArrayList<Integer> cityArr = new ArrayList<Integer>();
		
		// remove any tabs and whitespaces
		String[] arrOfLine = line.split("[\t\s]+"); 
		
		for(int i=0; i<arrOfLine.length; i++){
            if(arrOfLine[i].length() != 0){
            	cityArr.add(Integer.parseInt(arrOfLine[i]));
            }
        }
		return cityArr;
	}

	/**
	 * This is called by the main function to get coordinates from a file
	 * The function will try to open file and read the contents
	 * Else output FileNotFoundException and return empty list to Main
	 * 
	 * @param filename
	 * @return coords (type: List<List<Integer>>)
	 * 		Where List<Integer> contains (id of city, x coordinates, y coordinates)
	 */
	public static List<List<Integer>> getCoordinates(String filename) {
		List<List<Integer>> coords = new ArrayList<List<Integer>>(); 
		
		// if file exists and has next line
		// call split functions to split integer and add to coords
		try {
			Scanner fileScanner = new Scanner(new File(filename));
			while (fileScanner.hasNextLine()) {
				coords.add(split(fileScanner.nextLine()));
			
			}
			fileScanner.close();
			return coords;
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
			return coords;
		}
		
	
	}
}
