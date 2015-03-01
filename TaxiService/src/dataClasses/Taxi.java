/**
 * Advanced Software Engineering - Stage 1 of Taxi Service Application 
 * @author Sreesha Damodaran, Vidhya Krishna, Zulqarnain Mehdi
 * This class defines Taxi Class
 */
package dataClasses;

import java.util.TreeSet;

import applicationLogic.Helpers;

public class Taxi implements Comparable<Taxi> {
	private String driver;
	private String registrationNumber;
	private TreeSet<Destination> destinations;

	/**
	 * Creates a Taxi Object with values specified in the parameter
	 * @param driver				Taxi Driver
	 * @param registrationNumber	Vehicle Registration Number
	 */
	public Taxi(String driver, String registrationNumber) 
	{
		super();
		try
		{
			this.driver = driver;
			this.registrationNumber = registrationNumber;
		
			destinations = new TreeSet<Destination>();
		}
		catch(Exception exc)
		{
			Helpers.println(exc.getMessage());
		}
	}
	
	/*------------------------------------------------------------------------------------------------------------------------------
	 * 			 								GETTER AND SETTER METHODS FOR TAXI CLASS
	 * -----------------------------------------------------------------------------------------------------------------------------
	 */
	
	public String getDriver() {
		return driver;
	}
	
	public void setDriver(String driver) {
		this.driver = driver;
	}
	
	public TreeSet<Destination> getDestinations() {
		return destinations;
	}

	public void setDestinations(Destination destination) {
		destinations.add(destination);
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}
	
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	/*------------------------------------------------------------------------------------------------------------------------------
	 * 			 								END OF GETTER AND SETTER METHODS FOR TAXI CLASS
	 * -----------------------------------------------------------------------------------------------------------------------------
	 */
	
	@Override
	public int compareTo(Taxi other) {
		return driver.compareTo(other.driver);
	}

	/**
	 * Method to format output
	 */
	@Override
	public String toString() 
	{
		String s = "";
		try
		{
			s += "-----------------------------------------------------------------" + "\n";
			s += "Journeys details  of  Taxi number "  + registrationNumber +""            + "\n";
			s += "-----------------------------------------------------------------" + "\n";
			s += "Driver						: " + driver + "\n";
			s += "Taxi-Registration number	: " + registrationNumber + "\n";
			
			if(destinations != null && !destinations.isEmpty()) 
			{
				for(Destination destination : destinations) {
					s += "Destination					: " + destination.getName() + "\n";
					s += "Distance 					: " + destination.getDistance() + "\n";
				}
			}
			s += "\n";
		}
		catch (NullPointerException nullEx) {
			Helpers.println("Destinations not found");
		} catch(Exception ex) {
			throw ex;
		}
		return s;
	}
	
}
