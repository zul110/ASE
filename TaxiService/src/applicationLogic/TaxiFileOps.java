/**
 * Advanced Software Engineering - Stage 1 of Taxi Service Application 
 * @author Sreesha Damodaran, Vidhya Krishna, Zulqarnain Mehdi
 * This class describe methods for Taxi File Operations
 */
package applicationLogic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import customExceptions.InvalidFormatException;
import dataClasses.Taxi;

public class TaxiFileOps extends FileOps 
{
	private List<Taxi> taxis;
	private List<String> lines;
	
	private String lineCopy;
	private int lineNumber = 0;
	
	/**
	 * Constructor for TaxiFileOps
	 * Accepts the name of the file to read as a parameter
	 * Initializes the ArrayList of Taxis
	 * Reads lines from the file
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws Exception
	 */
	public TaxiFileOps(String fileName) throws FileNotFoundException, IOException, IllegalStateException, Exception
	{
		super(fileName);
		
		taxis = new ArrayList<Taxi>();
		
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
	 * "Deserializes" a List of Taxis from the List of lines read from the file
	 * @return List<Taxi>
	 * @throws InvalidFormatException
	 * @throws FileNotFoundException
	 * @throws IllegalStateException
	 * @throws IndexOutOfBoundsException
	 * @throws Exception
	 */
	public List<Taxi> getTaxis() throws InvalidFormatException, FileNotFoundException, IllegalStateException, IndexOutOfBoundsException, Exception {
			while(lineNumber < lines.size()) {
				String line = lines.get(lineNumber);
				lineCopy = line;
				lineNumber++;
				
				String driver = "";
				String registrationNumber = "";
				
				if(line.length() > 0) {
					String[] words = line.split(";");
					try {
						driver = words[1];
						registrationNumber = words[0];
						
						if(driver.isEmpty() || registrationNumber.isEmpty()) {
							throw new InvalidFormatException(Helpers.TAXIS_FILE_NAME + ": Invalid file format.\nLine number: " + lineNumber + " (" + lineCopy + ")\nPossible cause: missing data.\n");
						}
						
						if(!Helpers.isRegistrationNumberValid(registrationNumber)) {
							throw new InvalidFormatException(Helpers.TAXIS_FILE_NAME + ": Invalid registration number.\nLine number: " + lineNumber + " (" + lineCopy + ")\n");
						}
						
						Taxi taxi = new Taxi(driver, registrationNumber);
						
						taxis.add(taxi);
					} catch(IndexOutOfBoundsException indexEx) {
						Helpers.println(Helpers.TAXIS_FILE_NAME + ": Index out of bounds.\nLine number: " + lineNumber + " (" + lineCopy + ")\nPossible cause: missing data.\n");
					} catch(InvalidFormatException invalidEx) {
						Helpers.println(invalidEx.getMessage());
					} catch(Exception ex) {
						throw new Exception("Unknown error while reading " + Helpers.TAXIS_FILE_NAME + ".\nMore info: " + ex.getMessage() + "\n");
					}
				}
			}
		return taxis;
	}
	
	/**
	 * Method to return sorted list of taxis in ascending order
	 * @return Set<Taxi>
	 * @throws Exception
	 */
	public Set<Taxi> getSortedTaxisAsc() throws Exception 
	{
		TreeSet<Taxi> taxis = new TreeSet<Taxi>();
		
		for(Taxi taxi : getTaxis()) {
			taxis.add(taxi);
		}
		
		return taxis;
	}

	/**
	 * Write drivers and destinations to a file
	 * @param taxis
	 * @param fileName
	 * @throws Exception
	 */
	public static void writeDriversAndDestinationsToFile(Set<Taxi> taxis, String fileName) throws Exception 
	{
		try {
			String s = "";
		
			for(Taxi taxi : taxis) {
				s += taxi.toString();
			}
		
			writeToFile(fileName, s);
		} catch(Exception ex) {
			throw ex;
		}
	}
}
