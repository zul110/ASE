package testClasses;

import static org.junit.Assert.*;


import org.junit.*; 

import applicationLogic.Helpers;


public class JourneyTest{
	double distance = 10.5;
	int numberOfPassengers = 5;

	@Test
	public void testCalculateFare() {
		assertEquals(calculateFare(), Helpers.calculateFare(distance, numberOfPassengers), 0.1);
	}

	private double calculateFare() {
		double pricePerFifthMile = 0.50;
		double totalFare = 0.0;
		double overTwoPassengerCharge = 2.00;
		double dropOffCharge = 2.50;
		
		if (numberOfPassengers > 2) 
		{
			totalFare = overTwoPassengerCharge*(numberOfPassengers - 2);
		}
		
		totalFare+= dropOffCharge ;
		totalFare += (distance * pricePerFifthMile);
		
		return totalFare;
	}
}
