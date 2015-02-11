package applicationLogic;

public class Main {
	public static void main(String[] args) {
		
		JourneyFileOps journeyFile2014 = new JourneyFileOps(Helpers.DESTINATIONS_2014_FILE_NAME);
		
		JourneyFileOps journeyFile2015 = new JourneyFileOps(Helpers.JOURNEYS_2015_FILE_NAME);
		
		
		TaxiFileOps.writeDriversAndDestinationsToFile(journeyFile2015.getDriversAndVisitedPlaces(), "DriversAndDestinations");
		
		
		JourneyFileOps.writeTopFiveAndCheapestJourneysToFile(
			JourneyFileOps.getFirstFiveJourneys(journeyFile2015.getMostExpensiveJourneys(), "Top 5 Journeys"),
			JourneyFileOps.getFirstFiveJourneys(journeyFile2015.getLeastExpensiveJourneys(), "Cheapest 5 Journeys")
		);
		
		JourneyFileOps.writeUniqueAndCommonDestinations(journeyFile2015.getUniqueJourneySet(), journeyFile2014.getUniqueJourneySet());
	}
}
