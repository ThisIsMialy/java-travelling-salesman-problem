/**
 * City.java
 * 
 * Used for: Depth First Search, Heuristic Nearest Neighbour
 * 
 * @author Mialy Andrianarivony
 * 
 */

package tsp;

import java.util.List;

public class City {
	int cityId;
	double xCoords;
	double yCoords;
	
	/**
	 * Default constructor
	 * Assign 0 to all variables
	 */
	public City() {
		this.cityId = 0;
		this.xCoords = 0.0;
		this.yCoords = 0.0;
	}
	
	/**
	 * Constructor
	 * Assign cityId, x coordinate and y coordinate to a City Object
	 * 
	 * @param list - type: List<Integer> where list(id, xCoordinates, yCoordinates)
	 */
	public City(List<Integer> list) {
		super();
		this.cityId = list.get(0);
		this.xCoords = list.get(1);
		this.yCoords = list.get(2);
	}

	/**
	 * Get city id 
	 * @return cityId
	 */
	public int getCityID() {
		return cityId;
	}

	public void setCityID(int cityID) {
		cityId = cityID;
	}

	public double getXCoords() {
		return xCoords;
	}

	public void setXCoords(double xCoords) {
		this.xCoords = xCoords;
	}

	public double getYCoords() {
		return yCoords;
	}

	public void setYCoords(double yCoords) {
		this.yCoords = yCoords;
	}
	
	/**
	 * Calculate distance difference two cities
	 * @param city - other city 
	 * @return distance 
	 */
	public double getDistanceFrom(City city) {
		double diffX = Math.pow((city.getXCoords() - this.getXCoords()), 2);
		double diffY = Math.pow((city.getYCoords() - this.getYCoords()), 2);
		double distance = Math.sqrt(Math.abs(diffX + diffY));
		return distance;
		}
	
	@Override
	public String toString() {
		return "City [CityID=" + cityId + ", XCoords=" + xCoords + ", YCoords=" + yCoords + "]";
	}

}
