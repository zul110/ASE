
package applicationLogic;

import java.io.FileNotFoundException;

public class Main {
	private static JourneyFileOps journeyFile2014;
	private static JourneyFileOps journeyFile2015;
	
	/*Write taxi service details in a specified format*/
	public static void main(String[] args) 
	{
		try
		{
			journeyFile2014 = new JourneyFileOps(Helpers.DESTINATIONS_2014_FILE_NAME);
			journeyFile2015 = new JourneyFileOps(Helpers.JOURNEYS_2015_FILE_NAME);
		
			TaxiFileOps.writeDriversAndDestinationsToFile(journeyFile2015.getDriversAndVisitedPlaces(), "DriversAndDestinations");
		
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
		
			JourneyFileOps.writeUniqueAndCommonDestinations(
					journeyFile2015.getUniqueJourneySet(),
					journeyFile2014.getUniqueJourneySet()
					);
		} catch(FileNotFoundException fileEx) {
			Helpers.println(fileEx.getMessage());
		} catch(IllegalStateException illEx) {
			Helpers.println(illEx.getMessage());
		} catch(IndexOutOfBoundsException indexEx) {
			Helpers.println(indexEx.getMessage());
		} catch(Exception ex) {
			Helpers.println(ex.getMessage());
		}
	}
		
}
