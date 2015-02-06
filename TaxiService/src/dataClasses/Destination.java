package dataClasses;

public class Destination implements Comparable<Destination> {
	private String name;
	private int distance;
	
	public Destination(String name, int distance) {
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
	
	public int getDistance() {
		return distance;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	@Override
	public int compareTo(Destination other) {
		return name.compareTo(other.name);
	}
	
	@Override
	public String toString() {
		String s = "";
		s += "Destination: " + name + "\n";
		s += "Distance: " + distance + "\n";
		
		return s;
	}
}
