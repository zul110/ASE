package dataClasses;

import applicationLogic.Helpers;

public class Journey implements Comparable<Journey> {
	private int year;
	private Destination destination;
	private Taxi taxi;
	private int numberOfPassengers;
	private double cost;
	
	public Journey(int year, Destination destination, Taxi taxi, int numberOfPassengers) {
		super();
		
		this.year = year;
		this.destination = destination;
		this.taxi = taxi;
		this.numberOfPassengers = numberOfPassengers;
		
		cost = Helpers.calculateFare(destination.getDistance(), numberOfPassengers);
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
	
	public double getCost() {
		return cost;
	}

	@Override
	public String toString() 
	{
		String s = "";
		try {
			if(taxi != null) {
				s += String.format("%-18s", taxi.getRegistrationNumber());
				s += String.format("%-20s", taxi.getDriver());
			}
			s += String.format("%-25s", destination.getName());
			s += String.format("%-10s", destination.getDistance());
			s += String.format("%-8s",  numberOfPassengers);
			s += String.format("%-5s",  cost);
			s += "\n";
		} catch (NullPointerException nullEx) {
			Helpers.println("Taxis not found");
		} catch(Exception ex) {
			throw ex;
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
