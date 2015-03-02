package gui;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import applicationLogic.Helpers;
import applicationLogic.JourneyFileOps;
import applicationLogic.TaxiFileOps;

public class MainMenu extends JFrame implements ActionListener {
	JPanel panel = new JPanel();
	JButton driversAndDestinationsButton = new JButton("Generate \"Drivers and Destinations\" report");
	JButton uniqueAndCommonJourneysButton = new JButton("Generate \"Unique and Common Destinations\" report");
	JButton cheapestAndMostExpensiveJourneysButton = new JButton("Generate \"Cheapest and Most Expensive Journeys\" report");
	JButton allReportsButton = new JButton("Generate all reports");
	
	private static JourneyFileOps journeyFile2014;
	private static JourneyFileOps journeyFile2015;
	
	int topMargin = -40;
	
	/**
	 * Constructor for the Main Menu GUI
	 * Configures the window
	 * Initializes the container
	 * Initializes the panel
	 * Gets 2014 and 2015 journeys from input files 
	 */
	public MainMenu() {
		super("Taxi Service");
		
		initMainMenu();
		
		initContainer();
		
		populatePanel();
		
		get2014Journeys();
		get2015Journeys();
		
		setVisible(true);
	}
	
	/**
	 * Initializes the Main Menu window
	 */
	private void initMainMenu() {
		int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		setBounds((screenWidth / 2) - (200), (screenHeight / 2) - (125), 400, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Initializes the container, and adds the panel to it
	 */
	private void initContainer() {
		Container container = this.getContentPane();
		container.add(panel);
	}
	
	/**
	 * Resets the panel's layout
	 * Adds buttons bu calling the "addButton(JButton)" method
	 */
	private void populatePanel() {
		panel.setLayout(null);
		
		addButton(driversAndDestinationsButton);
		addButton(uniqueAndCommonJourneysButton);
		addButton(cheapestAndMostExpensiveJourneysButton);
		addButton(allReportsButton);
	}

	/**
	 * Configures and adds a button to the panel
	 * @param button
	 */
	private void addButton(JButton button) {
		button.setSize(300, 30);
		button.setLocation((200) - (150), topMargin += 50);
		button.addActionListener(this);
		panel.add(button);
	}

	/**
	 * Handles the click event on buttons
	 * Generates reports according to which button has been clicked
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == driversAndDestinationsButton) {
			generateDriversAndDestinationsReport();
		} else if(event.getSource() == uniqueAndCommonJourneysButton) {
			generateTopFiveAndCheapestFiveJourneysReport();
		} else if(event.getSource() == cheapestAndMostExpensiveJourneysButton) {
			generateUniqueAndCommonDestinationsReport();
		} else {
			generateTaxiReports();
		}
	}
	
	/**
	 * Method to get journeys in 2014 from an input file
	 * In case of unexpected error, the error message is printed  
	 */
	private static void get2014Journeys() {
		try {
			journeyFile2014 = new JourneyFileOps(Helpers.DESTINATIONS_2014_FILE_NAME);
		} catch(Exception ex) {
			Helpers.println(ex.getMessage() + "\n");
		}
	}
	
	/**
	 * Method to get journeys in 2015 from an input file
	 * In case of unexpected error, the error message is printed  
	 */
	private static void get2015Journeys() {
		try {
			journeyFile2015 = new JourneyFileOps(Helpers.JOURNEYS_2015_FILE_NAME);
		} catch(Exception ex) {
			Helpers.println(ex.getMessage() + "\n");
		}
	}
	
	/**
	 * Method to display drivers and destination reports for year 2015
	 * In case of unexpected error, print error message
	 */
	private static void generateDriversAndDestinationsReport() {
		try {
			TaxiFileOps.writeDriversAndDestinationsToFile(journeyFile2015.getDriversAndVisitedPlaces(), "DriversAndDestinations");
		} catch (Exception ex) {
			Helpers.println(ex.getMessage() + "\nPossible cause: Journeys list(s) empty.");
		}
	}

	/**
	 * Method to display Top five and cheapest five journeys for 2015
	 * In case of unexpected error, print the error message
	 */
	private static void generateTopFiveAndCheapestFiveJourneysReport() {
		try {
		JourneyFileOps.writeTopFiveAndCheapestJourneysToFile(
				JourneyFileOps.getFirstFiveJourneys(
						journeyFile2015.getMostExpensiveJourneys(),
						"Top 5 Journeys\n" + Helpers.getTableHead() + ""
						),
				JourneyFileOps.getFirstFiveJourneys(
						journeyFile2015.getLeastExpensiveJourneys(),
						Helpers.getTableFoot() + "Cheapest 5 Journeys\n" + Helpers.getTableHead() + ""
						)
				);
		} catch (Exception ex) {
			Helpers.println(ex.getMessage() + "\nPossible cause: Journeys list(s) empty.");
		}
	}
	
	/**
	 * Method to generate unique and common destinations report
	 * In case of an unexpected error during execution, 
	 * Print error message
	 */
	private static void generateUniqueAndCommonDestinationsReport() {
		try {
		JourneyFileOps.writeUniqueAndCommonDestinations(
				journeyFile2015.getUniqueJourneySet(),
				journeyFile2014.getUniqueJourneySet()
				);
		}  catch (Exception ex) {
			Helpers.println(ex.getMessage() + "\nPossible cause: Journeys list(s) empty.");
		}
	}
	
	/**
	 * Method to display taxi reports
	 * Drivers and Destinations visited 
	 * Most and Least Expensive journeys 
	 * Unique and Common destinations visited in both 2014 and 2015
	 */
	private static void generateTaxiReports() {
			generateDriversAndDestinationsReport();
			
			generateTopFiveAndCheapestFiveJourneysReport();
			
			generateUniqueAndCommonDestinationsReport();
	}
}
