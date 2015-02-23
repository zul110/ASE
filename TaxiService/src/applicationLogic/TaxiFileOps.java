package applicationLogic;

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
	public List<Taxi> getTaxis() {
		try {
			List<String> lines = readLinesFromFile();
		
			for(String line : lines) {
				String[] words = line.split(";");
			
				Taxi taxi = new Taxi(words[1], words[0]);
			
				taxis.add(taxi);
			}
		} catch(IndexOutOfBoundsException indexEx) {
			throw indexEx;
		} catch(Exception ex) {
			Helpers.println("Unknown error. More info:");
			Helpers.println(ex.getMessage());
		}
		
		return taxis;
	}
	
	/*Return sorted list of Taxis in ascending order*/
	public Set<Taxi> getSortedTaxisAsc() 
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
