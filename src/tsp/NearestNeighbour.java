/**
 * NearestNeighbour.java
 * 
 * Dependencies: City.java
 * 
 * This is a heuristic nearest neighbour algorithm
 * @author Mialy Andrianarivony
 */

package tsp;
import java.util.*;


public class NearestNeighbour {
	
	static double[][] distances;
	static double shortestDistance = Double.MAX_VALUE;
	
	long startTime;
	long estimatedTime;

	/**
	 * Empty constructor
	 */
	public NearestNeighbour() {
		
	}
	
	/**
	 * Create a list of cities from the coordinates passed
	 * @param coords
	 * @return cities (type: List<City>)
	 */
	public static List<City> populate(List<List<Integer>> coords) {
		
		List<City> cities = new ArrayList<City>();
		
		// create an array of cities
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
	 * Calculate distances of each city from all other cities
	 * Store in a 2D array
	 * Therefore it has n amount of rows where n is number of cities
	 * n amount of columns
	 * @param cities
	 * @return distances (type: double[][])
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
	 * Apply the Heuristic Nearest neighbour
	 * Loop through all cities and for each city
	 * Start at this city and find a path
	 * If findPathFrom finds a better solution
	 * Shortest Distance is updated
	 * Else if during execution, the findPathFrom has a longer distance than current shortest distance
	 * Skip the starting city and findPathFrom next city in List<City>
	 * 
	 * Prints amount of time
	 * Prints optimal path
	 * Prints optimal distance
	 */
	public void apply(List<List<Integer>> coords) {
		long startTime =  System.nanoTime();
		
		List<City> cities = populate(coords);
	
		distances = calculateDistance(cities);
		double shortestDistance = Double.MAX_VALUE;
		List<String> currentPathDistance;
		String shortestPath = null;
		int start = 0;

		// Loop through every city in list
		for(City city: cities) {
			start = city.getCityID();
			// Find path 
			currentPathDistance= findPathFrom(start, cities, shortestDistance);
			if (Double.parseDouble(currentPathDistance.get(1)) < shortestDistance) {
				shortestDistance = Double.parseDouble(currentPathDistance.get(1));
				shortestPath = currentPathDistance.get(0);
			}
		}
		// Print best result
		System.out.println("Path: " + shortestPath);
		System.out.println("Distance: " + shortestDistance);
		
		long estimatedTime = System.nanoTime() - startTime;
		
		System.out.println("Total Time: " + estimatedTime + "  nanoseconds.");

		long pathSqTime = (long) (Math.pow(shortestDistance, 2)) * estimatedTime;
		System.out.println("Path length squared times time: " + pathSqTime);
		
		System.out.println(" ");
	}
	
	/**
	 * This will perform the algorithm where the starting city if different
	 * 
	 * Skip the starting city if function finds a path length longer than overall shortest distance found
	 * 
	 * @param start
	 * @param cities
	 * @param shortestDistance
	 * @return result (type: List<String>)
	 * 		result contains the path and the path distance as Strings
	 */
	private static List<String> findPathFrom(int start, List<City> cities, double shortestDistance) {

		List<String> result = new ArrayList<String>();
		List<Integer> path = new ArrayList<Integer>();
		boolean[] visited = new boolean[cities.size() + 1];

		int count = 1;
		path.add(start);
		visited[start] = true;
		double totalDistance = 0;
		
		// start is City Index, current is distances Index
		int current = start - 1;
		int next = 0;
		
		while (count < cities.size()) {
			
			if (totalDistance > shortestDistance) {
				break;
			}
			double shortest = Integer.MAX_VALUE;
			
			for (int i = 0; i < distances[current].length; i++) {

				// skip comparing current city or already Traversed cities
				if (i == current || visited[i + 1]) {
					continue;
				}
				
				else {
					if (distances[current][i] < shortest) {
						shortest = distances[current][i];
						next = i;
					}
				}
			}
			totalDistance += shortest;
			path.add(cities.get(next).getCityID());
			visited[next + 1] = true;
			current = next;
			count += 1;
		}
		path.add(start);
		totalDistance += distances[start - 1][next];
		result.add(path.toString());
		result.add(Double.toString(totalDistance));
		return result;
	}

}
