package dataClasses;

public class Destination {
	private String name;
	private double distance;
	
	public Destination(String name, double distance) {
		super();
		
		this.name = name;
		this.distance = distance;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	@Override
	public String toString() {
		String s = "";
		s += "Destination: " + name + "\n";
		s += "Distance: " + distance + "\n";
		
		return s;
	}
}
