/**
 * Advanced Software Engineering - Stage 1 of Taxi Service Application 
 * @author Sreesha Damodaran, Vidhya Krishna, Zulqarnain Mehdi
 * This class describe methods for JUnit Testing
 */
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
	
	/**
	 * Vehicle Registration Format of Switzerland-"SWISS-2 Letter Canton-6 Digits"
	 *
	 */
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

	/**
	 * Method to calculate total fare of a journey
	 * If the number of passengers are over 2, an extra 2 Fr(Francs) is charged 
	 * For every fifth mile, 0.50 Fr and Drop Off Charge of 2.50 Fr is also considered
	 * @return totalFare
	 */
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
