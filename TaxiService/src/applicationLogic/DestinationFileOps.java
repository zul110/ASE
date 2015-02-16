package applicationLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dataClasses.Destination;

public class DestinationFileOps extends FileOps 
{
	private List<Destination> destinations;
	
	public DestinationFileOps(String fileName) 
	{
		super(fileName);
		
		destinations = new ArrayList<Destination>();
	}
	
	/*Store destinations*/
	public List<Destination> getDestinations() 
	{
		try
		{	
		List<String> lines = readLinesFromFile();
		for(String line : lines) {
			String[] words = line.split(";");
			Destination destination = new Destination(words[0], Double.parseDouble(words[1]));
			
			destinations.add(destination);
		}
		}
		//Exception -message 
		catch(Exception exc)
		{
			Helpers.println(exc.getMessage());
		}
		
		sortDestinationsDesc();
		
		return destinations;	
	}
	
	/*Sort destination in ascending order*/
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
	
	/*Sort destination in descending order*/
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
