package dataClasses;

import java.util.TreeSet;

public class Taxi implements Comparable<Taxi> {
	private String driver;
	private String registrationNumber;
	private TreeSet<Destination> destinations;
	
	public Taxi(String driver, String registrationNumber) {
		super();
		
		this.driver = driver;
		this.registrationNumber = registrationNumber;
		
		destinations = new TreeSet<Destination>();
	}

	public String getDriver() {
		return driver;
	}
	
	public void setDriver(String driver) {
		this.driver = driver;
	}
	
	public TreeSet<Destination> getDestinations() {
		return destinations;
	}

	public void setDestinations(Destination destination) {
		destinations.add(destination);
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}
	
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	
	@Override
	public int compareTo(Taxi other) {
		return driver.compareTo(other.driver);
	}
	
	@Override
	public String toString() {
		String s = "";
		s += "Name: " + driver + "\n";
		s += "Registration number: " + registrationNumber + "\n";
//		s += "Number of passengers: " + numberOfPassengers + "\n";
		for(Destination destination : destinations) {
			s += "Destination:" + destination.getName() + "\n";
			s += "Distance:" + destination.getDistance() + "\n\n";
		}
		
		return s;
	}
}
