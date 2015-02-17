package applicationLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import dataClasses.Destination;
import dataClasses.Journey;
import dataClasses.Taxi;

public class JourneyFileOps extends FileOps 
{
	private List<Taxi> taxis;
	private List<Destination> destinations;
	private List<Journey> journeys;
	
	private int year;
	
	public JourneyFileOps(String fileName) 
	{
		super(fileName);
		
		this.year = fileName == Helpers.JOURNEYS_2015_FILE_NAME ? 2015 : 2014;
		
		taxis = getTaxis();
		destinations = getDestinations();
		journeys = new ArrayList<Journey>();
		
		getJourneys();
	}
	
	/*Compare and return Journeys*/
	public void getJourneys() 
	{
		try
		{
			List<String> lines = readLinesFromFile();
		
			if(year == 2015) 
			{
				get2015Journeys(lines);
			} 
			else 
			{
				get2014Journeys(lines);
			}
		}
			catch(Exception exc)
			{
				Helpers.println(exc.getMessage());
			}
	}
	
	/*Return most expensive journeys*/
	public List<Journey> getMostExpensiveJourneys() 
	{
		List<Journey> journeys = this.journeys;
		Comparator<Journey> comparator = new Comparator<Journey>() 
				{
	
			@Override
			public int compare(Journey journey1, Journey journey2) 
			{
				if(journey2.getDestination().getDistance() > journey1.getDestination().getDistance()) 
				{
					return 1;
				} else if(journey2.getDestination().getDistance() < journey1.getDestination().getDistance()) 
				{
					return -1;
				} else {
					return 0;
				}
			}
			
		};
		
		Collections.sort(journeys, comparator);
		
		return journeys;
	}
	/*Return least expensive journeys*/
	public List<Journey> getLeastExpensiveJourneys() 
	{
		List<Journey> journeys = this.journeys;
		Comparator<Journey> comparator = new Comparator<Journey>() 
				{
	
			@Override
			public int compare(Journey journey1, Journey journey2) 
			{
				if(journey2.getDestination().getDistance() < journey1.getDestination().getDistance()) 
				{
					return 1;
				} else if(journey2.getDestination().getDistance() > journey1.getDestination().getDistance()) 
				{
					return -1;
				} else {
					return 0;
				}
			}
			
		};
		
		Collections.sort(journeys, comparator);
		
		return journeys;
	}
	/*Return unique records of drivers' names, and the unique destinations they have visited*/
	public TreeSet<Taxi> getDriversAndVisitedPlaces() 
	{
		TreeSet<Taxi> taxis = getUniqueTaxiSet();
		
		for(Taxi taxi : taxis) {
			for(Journey journey : journeys) 
			{
				if(taxi.getDriver().equals(journey.getTaxi().getDriver())) 
				{
					taxi.setDestinations(journey.getDestination());
				}
			}
		}
		
		return taxis;
	}
	
	/*Return unique set of journeys*/
	public TreeSet<Journey> getUniqueJourneySet() 
	{
		TreeSet<Journey> journeys = new TreeSet<Journey>();
		
		for(Journey journey : this.journeys) 
		{
			journeys.add(journey);
		}
		
		return journeys;
	}
	
	/*Return first 5 journeys*/
	public static String getFirstFiveJourneys(List<Journey> journeys, String title) 
	{
		String s = "";
		s += title;
		s += ":\n";
		
		for(Journey journey : journeys.subList(0, 5)) 
		{
			s += journey.toString();
		}
		
		s += "\n";
		
		return s;
	}
	
	/*Return top  5 and cheapest 5 journeys*/
	public static void writeTopFiveAndCheapestJourneysToFile(String top5Journeys, String cheapest5Journeys) 
	{
		try
		{
			String s = "";
			s += top5Journeys;
			s += cheapest5Journeys;
		
			writeToFile("TopFiveAndCheapestFiveJourneys", s);
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
	
	/*Return unique and common destinations*/
	public static void writeUniqueAndCommonDestinations(TreeSet<Journey> journeys1, TreeSet<Journey> journeys2) 
	{
		try
		{
			TreeSet<Journey> uniqueJourneys1 = getUniqueJourneys(journeys1, journeys2);
			TreeSet<Journey> uniqueJourneys2 = getUniqueJourneys(journeys2, journeys1);
			TreeSet<Journey> commonJourneys = getCommonJourneys(journeys1, journeys2);
		
			String s = "";
			s += getUniqueAndCommonDestinations(
				uniqueJourneys1, uniqueJourneys1.size(),
				uniqueJourneys2, uniqueJourneys2.size(),
				commonJourneys, commonJourneys.size()
				);
		
			writeToFile("UniqueAndCommonJourneys", s);
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
	
	/*Display 2015 Journeys*/
	private List<Journey> get2015Journeys(List<String> lines) 
	{
		for(String line : lines) {
			String[] words = line.split(";");
			
			Taxi taxi = new Taxi(getDriverName(words[0]), words[0]);
			Destination destination = new Destination(words[1], getDistance(words[1]));
			
			Journey journey = new Journey(year, destination, taxi, Integer.parseInt(words[2]));
			
			journeys.add(journey);
		}
		
		return journeys;
	}
	
	/*Display 2014 Journeys*/
	private List<Journey> get2014Journeys(List<String> lines) 
	{
		for(String line : lines) 
		{
			String[] words = line.split(";");
			
			Destination destination = new Destination(words[0], getDistance(words[0]));
			
			Journey journey = new Journey(year, destination, null, 0);
			
			journeys.add(journey);
		}
		
		return journeys;
	}
	
	/*Display Taxis*/
	private List<Taxi> getTaxis() 
	{
		return new TaxiFileOps(Helpers.TAXIS_FILE_NAME).getTaxis();
	}
	
	/*Display unique set of taxis*/
	private TreeSet<Taxi> getUniqueTaxiSet() 
	{
		TreeSet<Taxi> taxis = new TreeSet<Taxi>();
		
		for(Taxi taxi : this.taxis) 
		{
			taxis.add(taxi);
		}
		
		return taxis;
	}
	
	/*Display destinations*/
	private List<Destination> getDestinations() 
	{
		return new DestinationFileOps(Helpers.DESTINATIONS_FILE_NAME).getDestinations();
	}
	
	/*Display distance*/
	private double getDistance(String name) 
	{
		double distance = -1;
		
		for(Destination destination : destinations) 
		{
			if(destination.getName().equals(name)) 
			{
				distance = destination.getDistance();
			}
		}
		
		return distance;
	}
	
	/*Display driver details*/
	private String getDriverName(String regNumber) 
	{
		String name = "NOT FOUND";
		
		for(Taxi taxi : taxis) 
		{
			if(taxi.getRegistrationNumber().equals(regNumber)) 
			{
				name = taxi.getDriver();
			}
		}
		
		return name;
	}
	
	/*Display common Journeys*/
	private static TreeSet<Journey> getCommonJourneys(TreeSet<Journey> journeys1, TreeSet<Journey> journeys2) 
	{
		TreeSet<Journey> commonJourneys = journeys1;
		commonJourneys.retainAll(journeys2);
		
		return commonJourneys;
	}
	
	/*Display unique Journeys*/
	private static TreeSet<Journey> getUniqueJourneys(TreeSet<Journey> journeys1, TreeSet<Journey> journeys2) 
	
	{
		TreeSet<Journey> journeys1Only = new TreeSet<Journey>();
		for(Journey journey : journeys1) 
		{
			journeys1Only.add(journey);
		}
		
		journeys1Only.removeAll(journeys2);
		
		return journeys1Only;
	}
	
	/*Display unique  and common Destinations*/
	private static String getUniqueAndCommonDestinations(
			TreeSet<Journey> uniqueJourneys1, int uniqueJourney1Count,
			TreeSet<Journey> uniqueJourneys2, int uniqueJourney2Count,
			TreeSet<Journey> commonJourneys, int commonJourneyCount) 
	{
		String s = "";
		s += "---------------------------------------------------" + "\n";
		s += uniqueJourney1Count + " NEW PLACES IN 2015\n";
		s += "---------------------------------------------------" + "\n";
		for(Journey journey : uniqueJourneys1) 
		{
			s += journey.getDestination().getName() + "\n";
		}
		s += "\n";
		
		s += "---------------------------------------------------" + "\n";
		s += uniqueJourney2Count + " PLACES VISITED IN 2014 ONLY\n";
		s += "---------------------------------------------------" + "\n";
		for(Journey journey : uniqueJourneys2) 
		{
			s += journey.getDestination().getName() + "\n";
		}
		s += "\n";
		
		s += "---------------------------------------------------" + "\n";
		s += commonJourneyCount + " PLACES VISITED IN BOTH 2014 AND 2015\n";
		s += "---------------------------------------------------" + "\n";
		for(Journey journey : commonJourneys) 
		{
			s += journey.getDestination().getName() + "\n";
		}
		s += "\n";
		
		return s;
	}
	
	

	//
	//
	// GET FIRST FIVE JOURNEYS FROM LISTS, SORT THEM IN ASC DESC ORDER, AND WRITE THEM TO A FILE
	//
	//

}