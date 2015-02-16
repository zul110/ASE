package applicationLogic;

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
		
			String s1 = "";
			String s2 = "";
			s2 += "----------------------------------------------------------------------------------------------------------" + "\n";
			s1 += "----------------------------------------------------------------------------------------------------------" + "\n";
			s1+= "\nTaxi       		    Driver     		    Destination   				Distance 	Number of Passengers Year "+"\n";
			s1 += "----------------------------------------------------------------------------------------------------------" + "";
			JourneyFileOps.writeTopFiveAndCheapestJourneysToFile(
					
					
					JourneyFileOps.getFirstFiveJourneys(journeyFile2015.getMostExpensiveJourneys(), "Top 5 Journeys\n" + s1 + ""),
					JourneyFileOps.getFirstFiveJourneys(journeyFile2015.getLeastExpensiveJourneys(), s2  + "Cheapest 5 Journeys\n" + s1 + "")
					);
		
			JourneyFileOps.writeUniqueAndCommonDestinations(journeyFile2015.getUniqueJourneySet(), journeyFile2014.getUniqueJourneySet());
		}
		catch(Exception exc)
		{
			Helpers.println(exc.getMessage());
		}
	}
		
}
