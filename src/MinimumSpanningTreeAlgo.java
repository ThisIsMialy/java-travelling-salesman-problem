/**
 * MinimumSpanningTreeAlgo.java
 * 
 * Dependencies: City.java
 * 
 * Implementation of Prim's Algorithm - Minimum Spanning Tree
 * Find a minimum spanning tree for the cities given
 * Construct a closed circuit based on the MST 
 * 
 * @author M00837954 Mialy
 */

package cst3170_M00837954_cw1;

import java.util.*;

public class MinimumSpanningTreeAlgo {
	static double[][] distances;
	static List<City> cities = new ArrayList<City>();

	static List<Integer> visited;
	static double totalDistance = 0;

	
	/**
	 * Empty constructor
	 */
	public MinimumSpanningTreeAlgo() {

	}

	/** 
	 * Clear variables for a new tree 
	 * Clear visited and set total Distance of tree to 0
	 */
	public static void clearVariables() {
		visited = new ArrayList<Integer>();
		totalDistance = 0;
	}
	
	/**
	 * Called by Main to create MST
	 * Prints out the resulting circuit
	 * Start at different index of cities to find the best possible tree
	 * @param coords - coordinates of cities from a file
	 */
	public static void apply(List<List<Integer>> coords) {
		// start time
		long startTime =  System.nanoTime();
		
		List<City> bestTree = new ArrayList<City>();
		double bestDistance = Integer.MAX_VALUE;
		
		cities = populate(coords);
		distances = calculateDistance(cities);
		clearVariables();

		for(int i = 0; i < cities.size(); i++) {
			int currentStart = i;
			clearVariables();
			List<City> resultTree = primAlgorithm(currentStart);
			double pathDistance = constructTour(resultTree);
			if (pathDistance < bestDistance) {
				bestDistance = pathDistance;
				bestTree = resultTree;	
			}
		}
		System.out.println("Path: ");
		for(City city: bestTree) {
			System.out.print(city.getCityID() + " ");
		}
		// print first city again to complete loop
		System.out.print(bestTree.get(0).getCityID());
		System.out.println(" ");

		System.out.println("Path Distance: " + bestDistance);
	
		long estimatedTime = System.nanoTime() - startTime;
		System.out.println("Total Time: " + estimatedTime + "  nanoseconds.");
		
		long pathSqTime = (long) (Math.pow(bestDistance, 2)) * estimatedTime;
		System.out.println("Path length squared times time: " + pathSqTime);
		
		System.out.println(" ");
	}
	
	/**
	 * Construct circuit based on the tree from Prim's Algorithm
	 * Prim's Algorithms returns a list of city as the tree
	 * Connect the cities based on the order they appear 
	 * Connect the first and last city to complete circuit 
	 * 
	 * @param bestTree - List of the cities in the tree 
	 * 			bestTree is ordered from start city to last added city
	 * @param bestDistance 
	 */
	private static double constructTour(List<City> bestTree) {
		double tourDistance = 0;
		int currentDistanceId;
		int nextDistanceId;
		
		for (int i = 0; i < bestTree.size() - 1; i++) {
			currentDistanceId = (int) bestTree.get(i).getCityID();
			nextDistanceId = (int) bestTree.get(i + 1).getCityID();
			tourDistance += distances[currentDistanceId - 1][nextDistanceId - 1];
		}

		// Loop back to the first added city 
		// Add distance from first to last to tour distance 
		int firstCityId = bestTree.get(0).getCityID();
		int lastCityId =  bestTree.get(bestTree.size() - 1).getCityID();
		tourDistance += distances[firstCityId - 1][lastCityId- 1];

		return tourDistance;

	}

	/**
	 * Populate List of City with City objects created from given coordinates
	 * Create City object from each List of Integers (id, x-coordinates, y-coordinates) in coords
	 * 
	 * @param coords
	 * @return cities (type: List<City>)
	 */
	public static List<City> populate(List<List<Integer>> coords) {
		// create a new list of cities
		List<City> cities = new ArrayList<City>();

		for (List<Integer> list : coords) {
			City city = new City(list);
			try {
				cities.add(city);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return cities;
	}


	/**
	 * Calculates all distances between every city and store them in a 2D array
	 * 
	 * @param cities
	 * @return distances (type: double[][])
	 * 		distances is a 2D array of all distances
	 */
	private static double[][] calculateDistance(List<City> cities) {
		double[][] distances;
		distances = new double[cities.size()][cities.size()];

		for (int i = 0; i < cities.size(); i++) {
			for (int j = 0; j < cities.size(); j++) {
				distances[i][j] = cities.get(i).getDistanceFrom(cities.get(j));
			}
		}
		return distances;
	}


	/**
	 * Prim's Algorithm - Create a minimum spanning tree on the cities given
	 * The algorithm first starts at the starting city in parameters
	 * Add this city to the MST and visited List
	 * Then add all neighbouring distances into possible edges that the Tree can go to
	 * 
	 * WHILE the MST doesn't have all cities:
	 * 		Find a potential minimum-cost city from the List of available edges
	 * 		Add this minimum-cost city to MST 
	 * 		Add this minimum-cost city to the List of visited cities
	 * 		Add the neighbours (list of distances) of this minimum-cost city to the possible edges
	 * 
	 * @param currentStart - index in cities 
	 * @return mst (type: List<City>)
	 * 			mst stores the cities in order
	 */
	public static List<City> primAlgorithm(int currentStart) {
		List<City> mst = new ArrayList<City>();

		int startIndex = currentStart;
		City startCity = cities.get(startIndex);
		visited.add(startIndex);
		mst.add(startCity);

		List<double[]> edges = new ArrayList<double[]>();
		edges.add(distances[startIndex]);

		while (mst.size() < cities.size()) {
			int minimum = findMinEdge(edges);
			mst.add(cities.get(minimum));
			visited.add(minimum);

			edges.add(distances[minimum]);

		}
		return mst;
	}

	/**
	 * Find the next minimum-cost city from the neighbouring cities of already visited cities
	 * Add this minimum cost to the totalDistance of the tree
	 * 
	 * @param edges
	 * @return minimumEdge (type: int)
	 */
	private static int findMinEdge(List<double[]> edges) {
		int minimumEdge = -1;
		double minDistance = Double.MAX_VALUE;

		for (int e = 0; e < edges.size(); e++) {
			for (int i = 0; i < edges.get(e).length; i++) {
				if (edges.get(e)[i] < minDistance) {
					if (!visited.contains(i)) {
						minimumEdge = i;
						minDistance = edges.get(e)[i];
					}
				}
			}
		}
		totalDistance += minDistance;
		return minimumEdge;
	}

}
