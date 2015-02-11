package applicationLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import dataClasses.Destination;
import dataClasses.Journey;
import dataClasses.Taxi;

public class Main {
	private static Set<Taxi> taxis;
	private static List<Destination> destinations;
	private static ArrayList<Journey> journeys;

	public static void main(String[] args) {
		taxis = new TreeSet<Taxi>();
		destinations = new ArrayList<Destination>();
		journeys = new ArrayList<Journey>();
		
		DestinationFileOps destinationFile = new DestinationFileOps("Destinations.txt");
		destinations = destinationFile.getDestinations();
		displayDestinations();
		
		TaxiFileOps taxiFile = new TaxiFileOps("Taxis.txt");
		taxis = taxiFile.getSortedTaxisAsc();
		displayTaxisAndVisitedPlaces();
		
		//displayMostExpensiveJourneys();
		
//		displayLeastExpensiveJourneys();
		
//		displayJourneys();
	}

	private static void displayLeastExpensiveJourneys() {
		Comparator<Journey> comparator = new Comparator<Journey>() {

			@Override
			public int compare(Journey journey1, Journey journey2) {
				if(journey2.getDestination().getDistance() > journey1.getDestination().getDistance()) {
					return 1;
				} else if(journey2.getDestination().getDistance() > journey1.getDestination().getDistance()) {
					return -1;
				} else {
					return 0;
				}
			}
			
		};
		
		Collections.sort(journeys, comparator);
		
		displayJourneys();
		
	}

	private static void displayMostExpensiveJourneys() {
		Comparator<Journey> comparator = new Comparator<Journey>() {

			@Override
			public int compare(Journey journey1, Journey journey2) {
				if(journey2.getDestination().getDistance() > journey1.getDestination().getDistance()) {
					return 1;
				} else if(journey2.getDestination().getDistance() > journey1.getDestination().getDistance()) {
					return -1;
				} else {
					return 0;
				}
			}
			
		};
		
		Collections.sort(journeys, comparator);
		
		displayJourneys();
	}

	private static void displayJourneys() {
		for(Journey journey : journeys) {
			Helpers.println(journey.toString());
		}
	}

	private static void displayDestinations() {
		for(Destination destination : destinations) {
			Helpers.println(destination.toString());
		}
	}

	private static void displayTaxisAndVisitedPlaces() {
		for(Taxi taxi : taxis) {
			Helpers.println(taxi.toString());
		}
	}
}
