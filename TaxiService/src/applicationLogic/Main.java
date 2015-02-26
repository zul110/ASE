
package applicationLogic;

public class Main {
	private static JourneyFileOps journeyFile2014;
	private static JourneyFileOps journeyFile2015;
	
	/**
	 * The starting point of TaxiService.
	 * Initiates the 2014 and 2015 journey file operations classes,
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		get2014Journeys();
		
		get2015Journeys();
		
		generateTaxiReports();
	}

	private static void get2014Journeys() {
		try {
			journeyFile2014 = new JourneyFileOps(Helpers.DESTINATIONS_2014_FILE_NAME);
		} catch(Exception ex) {
			Helpers.println(ex.getMessage() + "\n");
		}
	}

	private static void get2015Journeys() {
		try {
			journeyFile2015 = new JourneyFileOps(Helpers.JOURNEYS_2015_FILE_NAME);
		} catch(Exception ex) {
			Helpers.println(ex.getMessage() + "\n");
		}
	}

	private static void generateTaxiReports() {
			generateDriversAndDestinationsReport();
			
			generateTopFiveAndCheapestFiveJourneysReport();
			
			generateUniqueAndCommonDestinationsReport();
	}

	private static void generateDriversAndDestinationsReport() {
		try {
			TaxiFileOps.writeDriversAndDestinationsToFile(journeyFile2015.getDriversAndVisitedPlaces(), "DriversAndDestinations");
		} catch (Exception ex) {
			Helpers.println(ex.getMessage() + "\n");
		}
	}

	private static void generateTopFiveAndCheapestFiveJourneysReport() {
		try {
		JourneyFileOps.writeTopFiveAndCheapestJourneysToFile(
				JourneyFileOps.getFirstFiveJourneys(
						journeyFile2015.getMostExpensiveJourneys(),
						"Top 5 Journeys\n" + Helpers.getTableHead() + ""
						),
				JourneyFileOps.getFirstFiveJourneys(
						journeyFile2015.getLeastExpensiveJourneys(),
						Helpers.getTableFoot() + "Cheapest 5 Journeys\n" + Helpers.getTableHead() + ""
						)
				);
		} catch (Exception ex) {
				Helpers.println(ex.getMessage() + "\n");
		}
	}

	private static void generateUniqueAndCommonDestinationsReport() {
		try {
		JourneyFileOps.writeUniqueAndCommonDestinations(
				journeyFile2015.getUniqueJourneySet(),
				journeyFile2014.getUniqueJourneySet()
				);
		}  catch (Exception ex) {
			Helpers.println(ex.getMessage() + "\n");
		}
	}
}
