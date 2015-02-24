package applicationLogic;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import dataClasses.Taxi;

public class TaxiFileOps extends FileOps 
{
	private List<Taxi> taxis;
	
	public TaxiFileOps(String fileName) 
	{
		super(fileName);
		
		taxis = new ArrayList<Taxi>();
	}
	
	/*Return Taxis*/
	public List<Taxi> getTaxis() throws FileNotFoundException, IllegalStateException, IndexOutOfBoundsException, Exception {
		try {
			List<String> lines = readLinesFromFile();
		
			for(String line : lines) {
				if(line.length() > 0) {
					String[] words = line.split(";");
					String driver = words[1];
					String registrationNumber = words[0];
				
					Taxi taxi = new Taxi(driver, registrationNumber);
				
					taxis.add(taxi);
				}
			}
		} catch(FileNotFoundException fileEx) {
			throw fileEx;
		} catch(IllegalStateException illEx) {
			throw illEx;
		} catch(IndexOutOfBoundsException indexEx) {
			throw new IndexOutOfBoundsException(Helpers.TAXIS_FILE_NAME + ": Invalid file format.");
		} catch(Exception ex) {
			Helpers.println("Unknown error. More info:");
			Helpers.println(ex.getMessage());
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
