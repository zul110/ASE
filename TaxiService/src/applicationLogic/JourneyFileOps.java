package applicationLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dataClasses.Destination;
import dataClasses.Journey;
import dataClasses.Taxi;

public class JourneyFileOps extends FileOps {
	private List<Journey> journeys;
	
	public JourneyFileOps(String fileName) {
		super(fileName);
		
		journeys = new ArrayList<Journey>();
	}
	
	public List<Journey> getJournys() {
		List<String> lines = readLinesFromFile();
		
		for(String line : lines) {
			String[] words = line.split(";");
			
			Taxi taxi = new Taxi(words[1], words[0]);
			Destination destination = new Destination(words[2], Double.parseDouble(words[3]));
			
			Journey journey = new Journey(2015, destination, taxi, Integer.parseInt(words[4]));
			
			journeys.add(journey);
		}
		
		return journeys;
	}
}