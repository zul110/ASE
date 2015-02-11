package applicationLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dataClasses.Destination;

public class DestinationFileOps extends FileOps {
	private List<Destination> destinations;
	
	public DestinationFileOps(String fileName) {
		super(fileName);
		
		destinations = new ArrayList<Destination>();
	}
	
	public List<Destination> getDestinations() {
		List<String> lines = readLinesFromFile();
		
		for(String line : lines) {
			String[] words = line.split(";");
			
			Destination destination = new Destination(words[0], Double.parseDouble(words[1]));
			
			destinations.add(destination);
		}
		
		sortDestinationsDesc();
		
		return destinations;
	}
	
	public void sortDestinationsAsc() {
		Comparator<Destination> comparator = new Comparator<Destination>() {
			@Override
			public int compare(Destination destination1, Destination destination2) {
				if(destination1.getDistance() > destination2.getDistance()) {
					return 1;
				} else if(destination1.getDistance() < destination2.getDistance()) {
					return -1;
				} else {
					return 0;
				}
			}
		};
		
		Collections.sort(destinations, comparator);
	}
	
	public void sortDestinationsDesc() {
		Comparator<Destination> comparator = new Comparator<Destination>() {
			@Override
			public int compare(Destination destination1, Destination destination2) {
				if(destination1.getDistance() < destination2.getDistance()) {
					return 1;
				} else if(destination1.getDistance() > destination2.getDistance()) {
					return -1;
				} else {
					return 0;
				}
			}
		};
		
		Collections.sort(destinations, comparator);
	}
}
