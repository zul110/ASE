package dataClasses;

import static org.junit.Assert.*;


import org.junit.*; 


public class JourneyTest{


	@Test
	public void testCalculateFare() {
		
		
		double pricePerFifthMile = 0.50;
		double totalFare =0.0;
		double overTwoPassengerCharge=2.00;
		double dropOffCharge=2.50;
		double distance = 10.5;
		int numberOfPassengers = 5;
	

		if (numberOfPassengers>2) 
		{
			totalFare = overTwoPassengerCharge*(numberOfPassengers-2);
		}
		
		// calculate the price based on miles in
		totalFare+= dropOffCharge ;
		totalFare += (distance*pricePerFifthMile);
		
	
		assertEquals( 13.75, totalFare, 0.1);

	}


	
		
	

}
