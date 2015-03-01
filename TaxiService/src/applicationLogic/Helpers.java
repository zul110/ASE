/**
 * Advanced Software Engineering - Stage 1 of Taxi Service Application 
 * @author Sreesha Damodaran, Vidhya Krishna, Zulqarnain Mehdi
 * This class describes methods of the Helpers Class
 */
package applicationLogic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import dataClasses.Destination;
import dataClasses.Journey;
import dataClasses.Taxi;

public class Helpers 
{
	public static final String TAXIS_FILE_NAME = "Taxis.txt";
	public static final String DESTINATIONS_FILE_NAME = "Destinations.txt";
	public static final String JOURNEYS_2015_FILE_NAME = "Journeys2015.txt";
	public static final String DESTINATIONS_2014_FILE_NAME = "Destinations2014.txt";
	
	/**
	 * Method to print output
	 * @param s
	 */
	public static void println(String s) 
	{
		System.out.println(s);
	}
	
	/**
	 * Method to print the taxi details 
	 * In case of an unexpected error in execution,
	 * Print error message
	 * @param taxis
	 */
	public static void displayTaxis(Set<Taxi> taxis) 
	{
		try
		{
			for(Taxi taxi : taxis) 
			{
				println(taxi.toString());
			}
		}
		catch(Exception exc)
		{
			Helpers.println(exc.getMessage());
		}
	}
	
	/**
	 * Method to display list of Destinations
	 * @param destinations
	 * In case of an unexpected error in execution, 
	 * Error message is printed
	 */
	public static void displayDestinations(List<Destination> destinations) 
	{
		try
		{
			for(Destination destination : destinations) 
			{
				println(destination.toString());
			}
		}
		catch(Exception exc)
		{
			Helpers.println(exc.getMessage());
		}
		catch(Throwable e) 
		{ 
			Helpers.println(e.getMessage());
		}
	}
	
	/**
	 * Method to display details of taxi drivers and destinations they have visited
	 * @param taxis
	 * In case of an unexpected error, error message is displayed
	 */
	public static void displayDriversAndVisitedPlaces(Set<Taxi> taxis) 
	{
		try
		{
			for(Taxi taxi : taxis) 
			{
				println(taxi.toString());
			}
		}
		catch(Exception exc)
		{
			Helpers.println(exc.getMessage());
		}
		catch(Throwable e) 
		{ 
			Helpers.println(e.getMessage());
		}
	}
	
	/**
	 * Method to display the least expensive journeys 
	 * @param journeys - List of journeys is passed as parameter
	 * In case of an unexpected error, error message is displayed
	 */
	public static void displayLeastExpensiveJourneys(List<Journey> journeys) 
	{
		try
		{
			displayJourneys(journeys);
		}
		catch(Exception exc)
		{
			Helpers.println(exc.getMessage());
		}
		catch(Throwable e) 
		{ 
			Helpers.println(e.getMessage());
		}
	}
	
	/**
	 * Method to display the most expensive journeys 
	 * @param journeys - List of journeys is passed as parameter
	 * In case of an unexpected error, error message is displayed
	 */
	public static void displayMostExpensiveJourneys(List<Journey> journeys) 
	{
		try
		{
			displayJourneys(journeys);
		}
		catch(Exception exc)
		{
			Helpers.println(exc.getMessage());
		}
		catch(Throwable e) 
		{ 
			Helpers.println(e.getMessage());
		}
	}

	/**
	 * Method to display journeys 
	 * @param journeys - List of journeys is passed as parameter
	 * In case of an unexpected error, error message is displayed
	 */
	public static void displayJourneys(List<Journey> journeys) 
	{
		try
		{
			for(Journey journey : journeys) 
			{
				println(journey.toString());
			}
		}
		catch(Exception exc)
		{
			Helpers.println(exc.getMessage());
		}
		catch(Throwable e) 
		{ 
			Helpers.println(e.getMessage());
		}
	}
	
	/**
	 * Method to display unique destinations visited 
	 * And corresponding taxi driver name
	 * @param journeys
	 * In case of an unexpected error, error message is displayed
	 */
	public static void displayUniqueJourneys(TreeSet<Journey> journeys) 
	{
		try
		{
			for(Journey journey : journeys) 
			{
				println(journey.getDestination().getName());
			}
		}
		catch(Exception exc)
		{
			Helpers.println(exc.getMessage());
		}
		catch(Throwable e) 
		{ 
			Helpers.println(e.getMessage());
		}
		
	}
	/**
	 * Method to set header for table display 
	 * @return head 
	 */
	public static String getTableHead() {
		String head = "";
		head += "-----------------------------------------------------------------------------------------\n";
		head += "Taxi        	  Driver     		  Destination   	     Distance Passengers Cost\n";
		head += "-----------------------------------------------------------------------------------------";
		
		return head;
	}
	/**
	 * Method to set footer for table display 
	 * @return foot  
	 */
	public static String getTableFoot() {
		String foot = "";
		foot += "-----------------------------------------------------------------------------------------\n\n";
		
		return foot;
	}
	
	/**
	 * Method to calculate taxi fare for a journey
	 * @param distance 
	 * @param numberOfPassengers
	 * @return totalFare
	 */
	public static double calculateFare(double distance, int numberOfPassengers)
	{
		double totalFare = 0.0;
		double pricePerFifthMile = 0.50;
		double dropOffCharge = 2.50;
		double overTwoPassengerCharge = 2.00;
		
		if (numberOfPassengers > 2) {
			totalFare = overTwoPassengerCharge * (numberOfPassengers - 2);
		}
		
		totalFare += dropOffCharge + (distance * pricePerFifthMile);
		
		return totalFare;
	}
	
	/**
	 * Method to test double data type validation
	 * @param string
	 * @return true - boolean
	 * In case of a number format exception, 
	 * function returns false
	 */
	public static boolean isDouble(String string) {
		try {
			double d = Double.parseDouble(string);
		} catch(NumberFormatException numEx) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Method to test double number validation 
	 * @param string
	 * @return true - boolean
	 * In case of a number format exception, 
	 * function returns false
	 */
	public static boolean isInteger(String string) {
		try {
			double d = Integer.parseInt(string);
		} catch(NumberFormatException numEx) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Method to test taxi registration number format validation 
	 * @param registrationNumber
	 * @return true - if number format is valid 
	 * else return false
	 */
	public static boolean isRegistrationNumberValid(String registrationNumber) {
		String[] reg = registrationNumber.split("-");
		if(reg.length == 3) {
			if(reg[0].toUpperCase().equals("SWISS")
					&& getCodes().contains(reg[1].toUpperCase())
					&& reg[2].length() == 6
					&& Helpers.isInteger(reg[2])) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Method to store Canton Codes in hash table
	 * HashSet called codes is defined 
	 * And Canton codes are added to it
	 * @return codes
	 */
	public static HashSet<String> getCodes() {
		HashSet<String> codes = new HashSet<String>();
		codes.add("AG");
		codes.add("AI");
		codes.add("AR");
		codes.add("BE");
		codes.add("BL");
		codes.add("BS");
		codes.add("FR");
		codes.add("GE");
		codes.add("GL");
		codes.add("GR");
		codes.add("JU");
		codes.add("LU");
		codes.add("NE");
		codes.add("NW");
		codes.add("OW");
		codes.add("SG");
		codes.add("SH");
		codes.add("SO");
		codes.add("SZ");
		codes.add("TG");
		codes.add("TI");
		codes.add("UR");
		codes.add("VD");
		codes.add("VS");
		codes.add("ZG");
		codes.add("ZH");
		
		return codes;
	}
}
