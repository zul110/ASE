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
	private static List<Journey> journeys2014;
	private static List<Journey> journeys2015;

	public static void main(String[] args) {
		taxis = new TreeSet<Taxi>();
		destinations = new ArrayList<Destination>();
		journeys2015 = new ArrayList<Journey>();
		journeys2014 = new ArrayList<Journey>();
		
		DestinationFileOps destinationFile = new DestinationFileOps(Helpers.DESTINATIONS_FILE_NAME);
		destinations = destinationFile.getDestinations();
		
		TaxiFileOps taxiFile = new TaxiFileOps(Helpers.TAXIS_FILE_NAME);
		taxis = taxiFile.getSortedTaxisAsc();
		
		JourneyFileOps journeyFile2014 = new JourneyFileOps(Helpers.DESTINATIONS_2014_FILE_NAME);
		journeys2014 = journeyFile2014.getJourneys();
		
		JourneyFileOps journeyFile2015 = new JourneyFileOps(Helpers.JOURNEYS_2015_FILE_NAME);
		journeys2015 = journeyFile2015.getJourneys();
		
		
		TaxiFileOps.writeDriversAndDestinationsToFile(journeyFile2015.getDriversAndVisitedPlaces(), "DriversAndDestinations");
		
		
		JourneyFileOps.writeTopFiveAndCheapestJourneysToFile(JourneyFileOps.getFirstFiveJourneys(
				journeyFile2015.getMostExpensiveJourneys(), "Top 5 Journeys"),
				JourneyFileOps.getFirstFiveJourneys(journeyFile2015.getLeastExpensiveJourneys(), "Cheapest 5 Journeys"));
		
		JourneyFileOps.writeUniqueAndCommonDestinations(journeyFile2015.getUniqueJourneySet(), journeyFile2014.getUniqueJourneySet());
	}
}
