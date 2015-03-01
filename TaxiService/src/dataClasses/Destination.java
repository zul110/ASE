/**
 * Advanced Software Engineering - Stage 1 of Taxi Service Application 
 * @author Sreesha Damodaran, Vidhya Krishna, Zulqarnain Mehdi
 * This class defines Destination Class
 */
package dataClasses;

import applicationLogic.Helpers;

public class Destination implements Comparable<Destination> {
	private String name;
	private double distance;
	
	/**
	 * Create a Destination Object as specified in the parameters
	 * @param name		Name of Destinations
	 * @param distance	Distance to Destination
	 */
	public Destination(String name, double distance) 
	{
		super();
		try
		{
			this.name = name;
			this.distance = distance;
		}
		catch(Exception exc)
		{
			Helpers.println(exc.getMessage());
		}
	}
	
	/*------------------------------------------------------------------------------------------------------------------------------
	 * 			 								GETTER AND SETTER METHODS FOR DESTINATION CLASS
	 * -----------------------------------------------------------------------------------------------------------------------------
	 */
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public void setDistance(double distance) {
		this.distance = distance;
	}
	/*------------------------------------------------------------------------------------------------------------------------------
	 * 			 								END OF GETTER AND SETTER METHODS FOR DESTINATION CLASS
	 * -----------------------------------------------------------------------------------------------------------------------------
	 */
	
	@Override
	public int compareTo(Destination other) {
		return name.compareTo(other.getName());
	}
	
	/**
	 * Method to format the output 
	 */
	@Override
	public String toString() {
		String s = "";
		s += "Destination: " + name + "\n";
		s += "Distance: " + distance + "\n";
		
		return s;
	}
}
