package applicationLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

import dataClasses.Destination;
import dataClasses.Journey;
import dataClasses.Taxi;

public class Main {
	private static TreeSet<Taxi> taxis;
	private static ArrayList<Destination> destinations;
	private static ArrayList<Journey> journeys;

	public static void main(String[] args) {
		taxis = new TreeSet<Taxi>();
		destinations = new ArrayList<Destination>();
		journeys = new ArrayList<Journey>();
		
		addTaxis();
		
		addJourneys();
		
		addDestinations();
		
//		displayTaxisAndVisitedPlaces();
		
		displayMostExpensiveJourneys();
		
//		displayLeastExpensiveJourneys();

//		displayDestinations();
		
//		displayJourneys();
	}

	private static void displayLeastExpensiveJourneys() {
		Comparator<Journey> comparator = new Comparator<Journey>() {

			@Override
			public int compare(Journey journey1, Journey journey2) {
				return journey1.getDestination().getDistance() - journey2.getDestination().getDistance();
			}
			
		};
		
		Collections.sort(journeys, comparator);
		
		displayJourneys();
		
	}

	private static void displayMostExpensiveJourneys() {
		Comparator<Journey> comparator = new Comparator<Journey>() {

			@Override
			public int compare(Journey journey1, Journey journey2) {
				return journey2.getDestination().getDistance() - journey1.getDestination().getDistance();
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

	private static void addJourneys() {
		addJourney(2015, addDestination("Sharjah", 25), addTaxi("Zul", "E 25239"), 2);
		addJourney(2015, addDestination("Sharjah", 25), addTaxi("Zul", "E 25239"), 2);
		addJourney(2015, addDestination("Ras Al Khaimah", 150), addTaxi("Zul", "E 25239"), 3);
		addJourney(2015, addDestination("Abu Dhabi", 100), addTaxi("Mehdi", "C 29122"), 1);
		addJourney(2015, addDestination("Umm Al Quwain", 140), addTaxi("Mehdi", "C 29122"), 1);
		addJourney(2015, addDestination("Sharjah", 25), addTaxi("Mehdi", "E 25239"), 2);
		addJourney(2015, addDestination("Sharjah", 25), addTaxi("Zxc", "E 25239"), 2);
		addJourney(2015, addDestination("Ajman", 30), addTaxi("Zxc", "E 25239"), 2);
		addJourney(2015, addDestination("Sharjah", 25), addTaxi("Zxc", "E 25239"), 2);
	}

	private static void addJourney(int year, Destination destination, Taxi taxi, int numPassengers) {
		Journey journey = new Journey(year, destination, taxi, numPassengers);
		
		journeys.add(journey);
	}

	private static void addDestinations() {
		addDestination("Sharjah", 25);
		addDestination("Abu Dhabi", 100);
		addDestination("Ras Al Khaimah", 150);
		addDestination("Abu Dhabi", 110);
		addDestination("Ajman", 30);
		addDestination("Umm Al Quwain", 140);
		addDestination("Al Ain", 150);
		addDestination("Fujeira", 200);
	}
	
	private static Destination addDestination(String name, int distance) {
		Destination destination = new Destination(name, distance);
		
		for(Taxi taxi : taxis) {
			for(Journey journey : journeys) {
				if(taxi.getDriver() == journey.getTaxi().getDriver()) {
					taxi.setDestinations(journey.getDestination());
				}
			}
		}
		
		destinations.add(destination);
		
		return destination;
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

	private static void addTaxis() {
		addTaxi("Zul", "E 25239");
		addTaxi("Zul", "E 25239");
		addTaxi("Zul", "E 25239");
		addTaxi("Zul", "E 25239");
		addTaxi("Zul", "E 25239");
		
		addTaxi("Mehdi", "C 29122");
		addTaxi("Mehdi", "C 29122");
		addTaxi("Mehdi", "C 29122");
		addTaxi("Mehdi", "C 29122");
		
//		addTaxi("Zul", "E 25239", "Abu Dhabi", 100, 1);
//		addTaxi("Zul", "E 25239", "Ras Al Khaimah", 150, 3);
//		addTaxi("Zul", "E 25239", "Abu Dhabi", 110, 2);
//		addTaxi("Zul", "E 25239", "Ajman", 30, 4);
//		
//		addTaxi("Mehdi", "C 29122", "Sharjah", 20, 1);
//		addTaxi("Mehdi", "C 29122", "Umm Al Quwain", 140, 1);
//		addTaxi("Mehdi", "C 29122", "Al Ain", 150, 4);
//		addTaxi("Mehdi", "C 29122", "Fujeira", 200, 2);
	}

	private static Taxi addTaxi(String driverName, String regNumber) {
		Taxi taxi = new Taxi(driverName, regNumber);
		
		taxis.add(taxi);
		
		return taxi;
	}

}
