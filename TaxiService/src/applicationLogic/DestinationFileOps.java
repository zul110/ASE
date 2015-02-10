package applicationLogic;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import dataClasses.Destination;

public class DestinationFileOps extends FileOps {
	private List<Destination> destinations;
	
	public DestinationFileOps() {
		super(getPath());
		
		destinations = new ArrayList<Destination>();
	}
	
	public List<Destination> getDestinations() {
		List<String> lines = readLinesFromFile();
		
		for(String line : lines) {
			String[] words = line.split(";");
			
			Destination destination = new Destination(words[0], Double.parseDouble(words[1]));
			
			destinations.add(destination);
		}
		
		return destinations;
	}
	
	public static String getPath() {
		URL url = Main.class.getClassLoader().getResource("Destination.txt");
		return url.getPath().substring(1, url.getPath().length());
	}
}
