/**
 * Advanced Software Engineering - Stage 1 of Taxi Service Application 
 * @author Sreesha Damodaran, Vidhya Krishna, Zulqarnain Mehdi
 * This class describe methods for Destination File Operations
 */
package applicationLogic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import customExceptions.InvalidFormatException;

import dataClasses.Destination;
import dataClasses.Taxi;

public class DestinationFileOps extends FileOps 
{
	private List<Destination> destinations;
	private List<String> lines;
	
	private String lineCopy;
	private int lineNumber = 0;
	
	/**
	 * Constructor method of DestinationFileOps Class
	 * @param fileName
	 */
	public DestinationFileOps(String fileName) throws FileNotFoundException, IOException, IllegalStateException, Exception
	{
		super(fileName);
		
		destinations = new ArrayList<Destination>();
		
		try {
			lines = readLinesFromFile();
		} catch(FileNotFoundException fileEx) {
			throw fileEx;
		} catch(IOException ioEx) {
			throw ioEx;
		} catch(Exception ex) {
			throw ex;
		}
	}

	/**
	 * Deserializes" a List of Destinations from the List of lines read from the file
	 * @return List<Destination>
	 * @throws InvalidFormatException
	 * @throws FileNotFoundException
	 * @throws IllegalStateException
	 * @throws IndexOutOfBoundsException
	 * @throws Exception
	 */
	public List<Destination> getDestinations() throws InvalidFormatException, FileNotFoundException, IllegalStateException, IndexOutOfBoundsException, Exception {
		while(lineNumber < lines.size()) {
			String line = lines.get(lineNumber);
			lineCopy = line;
			lineNumber++;
			
			String destinationName = "";
			String distance = "";
			
			if(line.length() > 0) {
				String[] words = line.split(";");
				try {
					destinationName = words[0];
					distance = words[1];
					
					if(destinationName.isEmpty() || distance.isEmpty()) {
						throw new InvalidFormatException(Helpers.DESTINATIONS_FILE_NAME + ": Invalid file format.\nLine number: " + lineNumber + " (" + lineCopy + ")\nPossible cause: missing data.\n");
					}
					
					if(!Helpers.isDouble(distance)) {
						throw new InvalidFormatException(Helpers.DESTINATIONS_FILE_NAME + ": Invalid registration number.\nLine number: " + lineNumber + " (" + lineCopy + ")\nPossible cause: distance is not a (double) number");
					}
					
					Destination destination = new Destination(destinationName, Double.parseDouble(distance));
					
					destinations.add(destination);
				} catch(IndexOutOfBoundsException indexEx) {
					Helpers.println(Helpers.DESTINATIONS_FILE_NAME + ": Index out of bounds.\nLine number: " + lineNumber + " (" + lineCopy + ")\nPossible cause: missing data.\n");
				} catch(InvalidFormatException invalidEx) {
					Helpers.println(invalidEx.getMessage());
				} catch(Exception ex) {
					throw new Exception("Unknown error while reading " + Helpers.DESTINATIONS_FILE_NAME + ".\nMore info: " + ex.getMessage() + "\n");
				}
			}
		}
		
		sortDestinationsDesc();
		
		return destinations;	
	}
	
	/**
	 *  Method to sort destination in ascending order
	 */
	public void sortDestinationsAsc() 
	{
		Comparator<Destination> comparator = new Comparator<Destination>() 
				{
			@Override
			public int compare(Destination destination1, Destination destination2) 
			{
				if(destination1.getDistance() > destination2.getDistance()) 
				{
					return 1;
				} else if(destination1.getDistance() < destination2.getDistance()) 
				{
					return -1;
				} else {
					return 0;
				}
			}
		};
		
		Collections.sort(destinations, comparator);
	}
	
	/** 
	 * Method to sort destination in descending order
	 */
	public void sortDestinationsDesc() 
	{
		Comparator<Destination> comparator = new Comparator<Destination>() 
				{
			@Override
			public int compare(Destination destination1, Destination destination2) 
			{
				if(destination1.getDistance() < destination2.getDistance()) 
				{
					return 1;
				} else if(destination1.getDistance() > destination2.getDistance()) 
				{
					return -1;
				} else {
					return 0;
				}
			}
		};
		
		Collections.sort(destinations, comparator);
	}
}
