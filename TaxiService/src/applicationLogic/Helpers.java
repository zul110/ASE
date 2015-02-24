package applicationLogic;

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
	
	/*Display most expensive journeys*/
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
	
	public static String getTableHead() {
		String head = "";
		head += "-----------------------------------------------------------------------------------------\n";
		head += "Taxi        	  Driver     		  Destination   	     Distance Passengers Cost\n";
		head += "-----------------------------------------------------------------------------------------";
		
		return head;
	}
	
	public static String getTableFoot() {
		String foot = "";
		foot += "-----------------------------------------------------------------------------------------\n\n";
		
		return foot;
	}
	
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
}
