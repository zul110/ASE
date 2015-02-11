package applicationLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import dataClasses.Taxi;

public class TaxiFileOps extends FileOps {
	private List<Taxi> taxis;
	
	public TaxiFileOps(String fileName) {
		super(fileName);
		
		taxis = new ArrayList<Taxi>();
	}
	
	public List<Taxi> getTaxis() {
		List<String> lines = readLinesFromFile();
		
		for(String line : lines) {
			String[] words = line.split(";");
			
			Taxi taxi = new Taxi(words[1], words[0]);
			
			taxis.add(taxi);
		}
		
		return taxis;
	}
	
	public Set<Taxi> getSortedTaxisAsc() {
		TreeSet<Taxi> taxis = new TreeSet<Taxi>();
		
		for(Taxi taxi : getTaxis()) {
			taxis.add(taxi);
		}
		
		return taxis;
	}
}
