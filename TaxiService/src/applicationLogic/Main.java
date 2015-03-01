/**
 * Advanced Software Engineering - Stage 1 of Taxi Service Application 
 * @author Sreesha Damodaran, Vidhya Krishna, Zulqarnain Mehdi
 * This class describe Main Class Methods
 */
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
	
	/**
	 * Method to display journeys in 2014
	 * In case of unexpected error, the error message is printed  
	 */
	private static void get2014Journeys() {
		try {
			journeyFile2014 = new JourneyFileOps(Helpers.DESTINATIONS_2014_FILE_NAME);
		} catch(Exception ex) {
			Helpers.println(ex.getMessage() + "\n");
		}
	}
	
	/**
	 * Method to display journeys in 2015
	 * In case of unexpected error, the error message is printed  
	 */
	private static void get2015Journeys() {
		try {
			journeyFile2015 = new JourneyFileOps(Helpers.JOURNEYS_2015_FILE_NAME);
		} catch(Exception ex) {
			Helpers.println(ex.getMessage() + "\n");
		}
	}
	
	/**
	 * Method to display taxi reports
	 * Drivers and Destinations visited 
	 * Most and Least Expensive journeys 
	 * Unique and Common destinations visited in both 2014 and 2015
	 */
	private static void generateTaxiReports() {
			generateDriversAndDestinationsReport();
			
			generateTopFiveAndCheapestFiveJourneysReport();
			
			generateUniqueAndCommonDestinationsReport();
	}
	
	/**
	 * Method to display drivers and destination reports for year 2015
	 * In case of unexpected error, print error message
	 */
	private static void generateDriversAndDestinationsReport() {
		try {
			TaxiFileOps.writeDriversAndDestinationsToFile(journeyFile2015.getDriversAndVisitedPlaces(), "DriversAndDestinations");
		} catch (Exception ex) {
			Helpers.println(ex.getMessage() + "\n");
		}
	}
	
	/**
	 * Method to display Top five and cheapest five journeys for 2015
	 * In case of unexpected error, print the error message
	 */
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

	/**
	 * Method to generate unique and common destinations report
	 * In case of an unexpected error during execution, 
	 * Print error message
	 */
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
