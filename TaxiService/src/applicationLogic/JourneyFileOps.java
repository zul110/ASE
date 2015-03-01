/**
 * Advanced Software Engineering - Stage 1 of Taxi Service Application 
 * @author Sreesha Damodaran, Vidhya Krishna, Zulqarnain Mehdi
 * This class describe methods for Journey File Operations
 */
package applicationLogic;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import customExceptions.InvalidFormatException;
import dataClasses.Destination;
import dataClasses.Journey;
import dataClasses.Taxi;

public class JourneyFileOps extends FileOps 
{
	private List<Taxi> taxis;
	private List<Destination> destinations;
	private List<Journey> journeys;
	
	private int year;
	
	private String lineCopy;
	private int lineNumber = 0;
	
	/**
	 * Constructor method for JourneyFileOps
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws IllegalStateException
	 * @throws Exception
	 */
	public JourneyFileOps(String fileName) throws FileNotFoundException, IllegalStateException,  Exception 
	{
		super(fileName);
		
		this.year = fileName == Helpers.JOURNEYS_2015_FILE_NAME ? 2015 : 2014;		
		this.journeys = new ArrayList<Journey>();
		
		try {
			this.destinations = getDestinations();
			
			getJourneys();
		} catch(FileNotFoundException fileEx) {
			throw fileEx;
		} catch(IllegalStateException illEx) {
			throw illEx;
		} catch(IndexOutOfBoundsException indexEx) {
			throw indexEx;
		} catch(Exception ex) {
			throw ex;
		}
	}
	
	/**
	 * Method to get list of journeys for 2014 and 2015
	 * @throws FileNotFoundException
	 * @throws IllegalStateException
	 * @throws IndexOutOfBoundsException
	 * @throws Exception
	 */
	public void getJourneys() throws FileNotFoundException, IllegalStateException, IndexOutOfBoundsException, Exception
	{
		try {
			List<String> lines = readLinesFromFile();
		
			if(year == 2015) {
				this.taxis = getTaxis();
				get2015Journeys(lines);
			} else {
				get2014Journeys(lines);
			}
		} catch(FileNotFoundException fileEx) {
			throw fileEx;
		} catch(IllegalStateException illEx) {
			throw illEx;
		} catch(Exception ex) {
			throw ex;
		}
	}
	
	private List<Taxi> getTaxis() throws Exception 
	{
		return new TaxiFileOps(Helpers.TAXIS_FILE_NAME).getTaxis();
	}

	private List<Destination> getDestinations() throws Exception 
	{
		return new DestinationFileOps(Helpers.DESTINATIONS_FILE_NAME).getDestinations();
	}

	/**
	 * Display 2015 Journeys
	 * In case of error during execution, error message is printed
	 */
	private List<Journey> get2015Journeys(List<String> lines) throws Exception
	{
			while(lineNumber < lines.size()) {
				String line = lines.get(lineNumber);
				lineCopy = line;
				lineNumber++;
				
				String registrationNumber = "";
				String destinationName = "";
				int numberOfPassengers = 0;
				
				if(line.length() > 0) {
					String[] words = line.split(";");
					try {
						if(words[0].isEmpty() || words[1].isEmpty() || words[2].isEmpty()) {
							throw new InvalidFormatException(Helpers.JOURNEYS_2015_FILE_NAME + ": Invalid file format.\nLine number: " + lineNumber + " (" + lineCopy + ")\nPossible cause: empty field.\n");
						}
						
						if(!Helpers.isRegistrationNumberValid(words[0])) {
							throw new InvalidFormatException(Helpers.JOURNEYS_2015_FILE_NAME + ": Invalid file format.\nLine number: " + lineNumber + " (" + lineCopy + ")\nInvalid taxi registration number.\n");
						}
						
						if(!Helpers.isInteger(words[2])) {
							throw new NumberFormatException(Helpers.JOURNEYS_2015_FILE_NAME + ": Invalid file format.\nLine number: " + lineNumber + " (" + lineCopy + ")\nNumber of passengers must be a decimal number.\n");
						}
						
						registrationNumber = words[0];
						destinationName = words[1];
						numberOfPassengers = Integer.parseInt(words[2]);
						
						
						Taxi taxi = new Taxi(getDriverName(registrationNumber), registrationNumber);
						Destination destination = new Destination(destinationName, getDistance(destinationName));
						
						Journey journey = new Journey(year, destination, taxi, numberOfPassengers);
						
						journeys.add(journey);
					} catch(IndexOutOfBoundsException indexEx) {
						Helpers.println(Helpers.JOURNEYS_2015_FILE_NAME + ": Invalid file format.\nLine number: " + lineNumber + " (" + lineCopy + ")\n");
					} catch(InvalidFormatException invalidEx) {
						Helpers.println(invalidEx.getMessage());
					} catch(NumberFormatException numEx) {
						Helpers.println(Helpers.JOURNEYS_2015_FILE_NAME + ": Invalid file format.\nLine number: " + lineNumber + " (" + lineCopy + ")\nNumber of passengers must be a decimal number.\n");
					} catch(Exception ex) {
						throw ex;
					}
				}
			}
		
		return journeys;
	}

	/**
	 * Display 2014 Journeys
	 * In case of error during execution, print error message
	 */
	private List<Journey> get2014Journeys(List<String> lines) throws Exception
	{
		while(lineNumber < lines.size()) {
			String line = lines.get(lineNumber);
			lineCopy = line;
			lineNumber++;
			
				if(line.length() > 0) {
					String[] words = line.split(";");
					try {
						String destinationName = words[0];
						
						if(destinationName.isEmpty()) {
							throw new InvalidFormatException(Helpers.DESTINATIONS_2014_FILE_NAME + ": Invalid file format.\nLine number: " + lineNumber + " (" + lineCopy + ")\nPossible cause: empty field.\n");
						}
						
						Destination destination = new Destination(destinationName, getDistance(destinationName));
						
						Journey journey = new Journey(year, destination, null, 0);
						
						journeys.add(journey);
					} catch(IndexOutOfBoundsException indexEx) {
						Helpers.println(Helpers.DESTINATIONS_2014_FILE_NAME + ": Index out of bounds.\nLine number: " + lineNumber + " (" + lineCopy + ")\n");
					} catch(InvalidFormatException invalidEx) {
						Helpers.println(invalidEx.getMessage());
					} catch(NumberFormatException numEx) {
						Helpers.println(Helpers.DESTINATIONS_2014_FILE_NAME + ": Invalid file format.\nLine number: " + lineNumber + " (" + lineCopy + ")\nNumber of passengers must be a number\n");
					} catch(Exception ex) {
						throw ex;
					}
				}
			}
		return journeys;
	}

	/**
	 * Display driver names if regNumber is equal 
	 * To Registration number of taxi
	 * @param regNumber
	 * @return name 
	 */
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

	/**
	 * Display distance corresponding to the driver name
	 * If not found, display -1
	 * @param name
	 * @return distance
	 */
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

	/**
	 * Method to return unique records of drivers' names, 
	 * And the unique destinations they have visited 
	 * @return TreeSet<Taxis>
	 */
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

	/**
	 * Display unique set of Taxis
	 * @return TreeSet<Taxi>
	 */
	private TreeSet<Taxi> getUniqueTaxiSet() 
	{
		TreeSet<Taxi> taxis = new TreeSet<Taxi>();
		
		for(Taxi taxi : this.taxis) 
		{
			taxis.add(taxi);
		}
		
		return taxis;
	}

	/**
	 * Method to return unique and common destinations
	 * @param journeys1
	 * @param journeys2
	 */
	public static void writeUniqueAndCommonDestinations(TreeSet<Journey> journeys1, TreeSet<Journey> journeys2) 
	{
		try {
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
		} catch(Exception exc) {
			Helpers.println(exc.getMessage());
		}
	}

	/**
	 * Display unique  and common Destinations - table format
	 */
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

	/**
	 * Method to return unique set of journeys
	 * @return TreeSet<Journey>
	 */
	public TreeSet<Journey> getUniqueJourneySet() 
	{
		TreeSet<Journey> journeys = new TreeSet<Journey>();
		
		for(Journey journey : this.journeys) 
		{
			journeys.add(journey);
		}
		
		return journeys;
	}

	/**
	 * Display unique Journeys of only 2015
	 * @param journeys1
	 * @param journeys2
	 * @return
	 */
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

	/**
	 * Display common Journeys of both 2014 and 2015
	 * @param journeys1
	 * @param journeys2
	 * @return
	 */
	private static TreeSet<Journey> getCommonJourneys(TreeSet<Journey> journeys1, TreeSet<Journey> journeys2) 
	{
		TreeSet<Journey> commonJourneys = journeys1;
		commonJourneys.retainAll(journeys2);
		
		return commonJourneys;
	}

	/**
	 * Return first 5 journeys
	 * @param journeys
	 * @param title
	 * @return
	 */
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

	/**
	 * Method to return most expensive journeys
	 * @return
	 */
	public List<Journey> getMostExpensiveJourneys()
	{
		List<Journey> journeys = this.journeys;
		Comparator<Journey> comparator = new Comparator<Journey>() {
			@Override
			public int compare(Journey journey1, Journey journey2) 
			{
				if(journey2.getCost() > journey1.getCost()) 
				{
					return 1;
				} else if(journey2.getCost() < journey1.getCost()) 
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

	/**
	 * Method to return least expensive journeys
	 * @return
	 */
	public List<Journey> getLeastExpensiveJourneys() 
	{
		List<Journey> journeys = this.journeys;
		Comparator<Journey> comparator = new Comparator<Journey>() {
			@Override
			public int compare(Journey journey1, Journey journey2) 
			{
				if(journey2.getCost() < journey1.getCost()) 
				{
					return 1;
				} else if(journey2.getCost() > journey1.getCost()) 
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

	/**
	 * Method to return top five most and least expensive journeys
	 * @param top5Journeys
	 * @param cheapest5Journeys
	 * In case of error during exception, error message is printed
	 */
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
}