package testClasses;

import static org.junit.Assert.*;

import org.junit.*; 

import applicationLogic.Helpers;
import applicationLogic.TaxiFileOps;


public class JourneyTest{
	double distance = 10.5;
	int numberOfPassengers = 5;

	@Test
	public void testCalculateFare() {
		assertEquals(calculateFare(), Helpers.calculateFare(distance, numberOfPassengers), 0.1);
	}
	
	@Test
	public void testRegistrationNumberValidity() {
		String registrationNumber = "SWISS-BE-237890";
		
		boolean expectedValue = true;
		boolean actualValue = Helpers.isRegistrationNumberValid(registrationNumber);
		
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void testIsDouble() {
		String number = "12345.1";
		
		boolean expectedValue = true;
		boolean actualValue = Helpers.isDouble(number);
		
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void testIsNumber() {
		String number = "12345";
		
		boolean expectedValue = true;
		boolean actualValue = Helpers.isInteger(number);
		
		assertEquals(expectedValue, actualValue);
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
