/**
 * Advanced Software Engineering - Stage 1 of Taxi Service Application 
 * @author Sreesha Damodaran, Vidhya Krishna, Zulqarnain Mehdi
 * This class describe custom exception - Invalid Format Exception
 * which checks the input text file details against the expected format
 */
package customExceptions;

public class InvalidFormatException extends Exception {
	public InvalidFormatException(String message) {
        super(message);
    }
}
