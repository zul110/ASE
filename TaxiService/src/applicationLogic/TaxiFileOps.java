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
	
	/*Return Taxis*/
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

	/*Return sorted list of Taxis in ascending order*/
	public Set<Taxi> getSortedTaxisAsc() throws Exception 
	{
		TreeSet<Taxi> taxis = new TreeSet<Taxi>();
		
		for(Taxi taxi : getTaxis()) {
			taxis.add(taxi);
		}
		
		return taxis;
	}
	
	/*Write drivers and destinations to a file*/
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
