package edu.ucalgary.oop;

import java.util.*;

/**
 * MainApplication class that contains the main method to run the application
 *
 * @author Olamikun Aluko
 * <a href="mailto:lammyaluko@gmail.com">Email me</a> for any comments
 * @version 1.1
 * @since 1.0
 */
public class MainApplication {

    public static String workerType = "central"; // default value
    public static HashSet<Location> storedLocations = new HashSet<>();
    public static String genderOptionsFilePath = "src/main/resources/GenderOptions.txt";
    public static HashSet<String> validGenders = ApplicationUtils.getValidGenders();
    public static Location locationWorkerLocation;


    /**
     * Main method to run the application
     *
     * @param args command-line flag to determine the type of worker
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Print command-line flag
        System.out.println("args: " + Arrays.toString(args));

        // Read command-line flag
        for (String arg : args) {
            String[] tokens = arg.split("="); // Split the argument by the equals sign
            if (tokens[0].equals("--worker-type")) {
                workerType = tokens[1];
            }
        }

        // Initializing my locations
        populateLocations();

        if (workerType.equals("central")) {
            new InquirerQueryCLI().run();
        } else {
            // Ask for location
            System.out.println("Hello Local Worker.\nEnter the name of the location you are working at.\nChoose from the following: ");

            // Create HashMap to store integers and locations
            HashMap<Integer, Location> locationMap = new HashMap<>();
            int i = 1;
            for (Location location : storedLocations) {
                System.out.println(i + ". " + location.getName());
                locationMap.put(i, location);
                i++;
            }
            // Get user input
            while (true) {
                try {
                    int locationChoice = Integer.parseInt(scanner.nextLine());
                    if (locationChoice < 1 || locationChoice > storedLocations.size()) {
                        System.out.println("Invalid choice. Please choose a number between 1 and " + storedLocations.size());
                    } else {
                        locationWorkerLocation = locationMap.get(locationChoice);
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }
            new DisasterVictimCLI().run();
        }
    }

    /**
     * Populates the storedLocations HashSet with Location objects
     */
    private static void populateLocations() {
        // Initializing my locations
        Location a = new Location("New York", "123 Location lane");
        Location b = new Location("Edmonton", "456 Location lane");
        Location c = new Location("Toronto", "789 Location lane");
        Location d = new Location("Calgary", "101 Location lane");

        storedLocations = new HashSet<>(Arrays.asList(a, b, c, d));
    }
}