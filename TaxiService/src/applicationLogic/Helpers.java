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
	
	/*Print data*/
	public static void println(String s) 
	{
		System.out.println(s);
	}
	
	/*Display Taxis*/
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
	
	/*Display destinations*/
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
	/*Display unique records of drivers' names, and the unique destinations they have visited*/
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
	
	/*Display least expensive journeys*/
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
	
	/* Display most expensive journeys */
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
	/*Display journeys*/
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
	
	/*Display unique journeys*/
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
	/* Display table header */
	public static String getTableHead() {
		String head = "";
		head += "-----------------------------------------------------------------------------------------\n";
		head += "Taxi        	  Driver     		  Destination   	     Distance Passengers Cost\n";
		head += "-----------------------------------------------------------------------------------------";
		
		return head;
	}
	/* Display table footer */
	public static String getTableFoot() {
		String foot = "";
		foot += "-----------------------------------------------------------------------------------------\n\n";
		
		return foot;
	}
	
	/* Calculate taxi fare */
	public static double calculateFare(double distance, int numberOfPassengers)
	{
		double totalFare = 0.0;
		double pricePerFifthMile = 0.50;
		double dropOffCharge = 2.50;
		double overTwoPassengerCharge = 2.00;
		//double perLuggagePiece = 1.00;
	
		if (numberOfPassengers > 2) {
			totalFare = overTwoPassengerCharge * (numberOfPassengers - 2);
		}
		
		totalFare += dropOffCharge + (distance * pricePerFifthMile);
		
		return totalFare;
	}
	
	/* Test double data type validation */
	public static boolean isDouble(String string) {
		try {
			double d = Double.parseDouble(string);
		} catch(NumberFormatException numEx) {
			return false;
		}
		
		return true;
	}
	
	/* Test double number validation */
	public static boolean isInteger(String string) {
		try {
			double d = Integer.parseInt(string);
		} catch(NumberFormatException numEx) {
			return false;
		}
		
		return true;
	}
	
	/* Test taxi registration number format validation */
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
	
	/* Store country continent codes in a hash table  */
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
