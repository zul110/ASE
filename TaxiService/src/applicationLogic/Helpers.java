package applicationLogic;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import dataClasses.Destination;
import dataClasses.Journey;
import dataClasses.Taxi;

public class Helpers {
	public static final String TAXIS_FILE_NAME = "Taxis.txt";
	public static final String DESTINATIONS_FILE_NAME = "Destinations.txt";
	public static final String JOURNEYS_2015_FILE_NAME = "Journeys2015.txt";
	public static final String DESTINATIONS_2014_FILE_NAME = "Destinations2014.txt";
	
	public static void println(String s) {
		System.out.println(s);
	}
	
	public static void displayTaxis(Set<Taxi> taxis) {
		for(Taxi taxi : taxis) {
			println(taxi.toString());
		}
	}
	
	public static void displayDestinations(List<Destination> destinations) {
		for(Destination destination : destinations) {
			println(destination.toString());
		}
	}
	
	public static void displayDriversAndVisitedPlaces(Set<Taxi> taxis) {
		for(Taxi taxi : taxis) {
			println(taxi.toString());
		}
		
	}
	
	public static void displayLeastExpensiveJourneys(List<Journey> journeys) {
		displayJourneys(journeys);
		
	}

	public static void displayMostExpensiveJourneys(List<Journey> journeys) {
		displayJourneys(journeys);
	}

	public static void displayJourneys(List<Journey> journeys) {
		for(Journey journey : journeys) {
			println(journey.toString());
		}
	}

	public static void displayUniqueJourneys(TreeSet<Journey> journeys) {
		for(Journey journey : journeys) {
			println(journey.getDestination().getName());
		}
	}
}
