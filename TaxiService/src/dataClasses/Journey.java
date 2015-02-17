package dataClasses;

import applicationLogic.Helpers;

public class Journey implements Comparable<Journey> {
	private int year;
	private Destination destination;
	private Taxi taxi;
	private int numberOfPassengers;
	
	public Journey(int year, Destination destination, Taxi taxi, int numberOfPassengers) {
		super();
		
		this.year = year;
		this.destination = destination;
		this.taxi = taxi;
		this.numberOfPassengers = numberOfPassengers;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public Taxi getTaxi() {
		return taxi;
	}

	public void setTaxi(Taxi taxi) {
		this.taxi = taxi;
	}

	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}

	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}

	@Override
	public String toString() 
	{
		String s = "";
		try
		{
		/*String s1 = "";
		s1 += "----------------------------------------------------------------------------------------------------------" + "\n";
		s1+= "\nTaxi       		    Driver     		    Destination   				Distance 	Number of Passengers\n";
		s1 += "----------------------------------------------------------------------------------------------------------" + "\n";*/
			if(taxi != null) 
			{
			
				s += String.format("%-18s", taxi.getRegistrationNumber());
				s += String.format("%-20s", taxi.getDriver());
			}
			s += String.format("%-25s", destination.getName());
			s += String.format("%-10s", destination.getDistance());
			s += String.format("%-8s",  numberOfPassengers);
			s += String.format("%-5s",  year);
			s += "\n";
		}
		catch (NullPointerException e) 
		{
			Helpers.println(e.getMessage());
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public int compareTo(Journey other) {
		if(destination.getDistance() > other.destination.getDistance()) {
			return 1;
		} else if(destination.getDistance() < other.destination.getDistance()) {
			return -1;
		} else {
			return 0;
		}
	}
}
