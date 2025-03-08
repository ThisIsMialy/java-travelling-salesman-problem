/**
 * Main.java
 * Dependencies: ReadFile.java 
 * 				City.java 
 * 				MinimumSpanningTree.java 
 *				NearestNeighbour.java
 * 
 * Runs the algorithms on files
 * 
 * 
 * @author M00837954 Mialy
 */

package cst3170_M00837954_cw1;

import java.util.*;


/**
 * Main class
 * 
 */

public class Main {

	/*
	 * Ask the user for a filename input
	 * 
	 * Try to find and read the file 
	 * If file is valid, run the algorithms on the file
	 * 
	 */
	public static void main(String[] args) {
		List<List<Integer>> coords = new ArrayList<List<Integer>>(); 

		String  inputFileName;
		System.out.println("Write your filename: ");

		try (Scanner user = new Scanner( System.in )) {
			inputFileName = user.nextLine().trim();
		} 

		System.out.println("Your file: "+ inputFileName);

		System.out.println("Reading file: " + inputFileName);

		coords = ReadFile.getCoordinates(inputFileName);

		if (coords.size() > 0) {
			applyAlgorithms(coords);
			System.out.println("End of: " + inputFileName);
		}
		else {
			System.out.println("Filename: "+ inputFileName + " NOT FOUND");
		}

		System.out.println("***********************************************************************************");
	}


	/**
	 * Manages all algorithms and execute each algorithm on the List<List<Integer>>
	 * Takes one file at a time and execute all algorithms
	 * 
	 * Each algorithm will create its own object 
	 * Each algorithm has its own output and will print out the result path and distance
	 * 
	 * @param coords - this is a list of list that has all the id, x and y coordinates from files
	 * 
	 */
	private static void applyAlgorithms(List<List<Integer>> coords) {
		System.out.println(" ");
		// call Heuristic Nearest Neighbour
		System.out.println("Heuristic Nearest Neighbour:");
		NearestNeighbour nn = new NearestNeighbour();
		nn.apply(coords);
		System.out.println(" ");

		//		
		//call Minimum Spanning Tree
		System.out.println("Minimum Spanning Tree (Prim's Algorithm):");
		MinimumSpanningTreeAlgo.apply(coords);
		System.out.println(" ");

	}




}
