package edu.ucalgary.oop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// TODO: Create more Tests for functions that implement database access
// and input and output (printing), and those that interface with the
// stored locations. Phew!

public class MainApplication {

    public static String workerType;
    public static HashSet<Location> storedLocations = new HashSet<>();
    public static String genderOptionsFilePath = "src/main/resources/GenderOptions.txt";  // TODO: Add file path to README documentation
    public static HashSet<String> validGenders = ApplicationUtils.getValidGenders();
    public static HashSet<Inquirer> storedInquirers = new HashSet<>();
    public static Location locationWorkerLocation;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read command-line flag
        workerType = "central"; // default value

        for (int i = 0; i < args.length; i++) {
            if ("--worker-type".equals(args[i])) {
                i++;
                if (i < args.length) {
                    workerType = args[i];
                } else {
                    System.err.println("--worker-type requires a parameter");
                    return;
                }
            }
        }

        // Initializing my locations
        Location a = new Location("Calgary", "123 Location lane");
        Location b = new Location("Edmonton", "456 Location lane");
        Location c = new Location("Toronto", "789 Location lane");
        Location d = new Location("New York", "101 Location lane");

        // Storing the locations
        storedLocations = new HashSet<>(Arrays.asList(a, b, c, d));

        // Get Location of Local Worker
        if (workerType.equals("location")) {
            System.out.println("Please enter your location:\nChoose a number from the following options: ");

            // Creating location hashmap
            HashMap<Integer, Location> locationMap = new HashMap<>();

            int i = 1;
            for (Location location : storedLocations) {
                System.out.println(i + ". " + location.getName());
                locationMap.put(i, location);
                i++;
            }

            // Get the user's choice
            int choice;
            while (true) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    while (choice < 1 || choice > storedLocations.size()) {
                        System.out.println("Invalid choice provided");
                        System.out.println("Please choose a valid option: ");
                        choice = Integer.parseInt(scanner.nextLine());
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input provided.\n" + e.getMessage());
                }
            }
            locationWorkerLocation = locationMap.get(choice);
        }

        // Introduction
        System.out.println("Welcome to the Relief Management System");
        System.out.println(
                "Select what action you'd like to perform\n" +
                        "1. Launch Disaster Victim Interface.\nHere you can enter information about disaster victims, their" + "relationships and their medical records\n" +
                        "2. Launch Inquirer Query Interface.\nHere you can log inquirer queries, and search blah\n" +
                        "3. Quit\n\n" +
                        "Enter here: "
        );

        // Create a HashMap to store the choices
        HashMap<Integer, Object> choiceMap = new HashMap<>();
        choiceMap.put(1, new DisasterVictimCLI());
        choiceMap.put(2, new InquirerQueryCLI());
        choiceMap.put(3, null);

        // Get the user's choice
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                while (choice < 1 || choice > 3) {
                    System.out.println("Invalid choice provided");
                    System.out.println("Please choose a valid option: ");
                    choice = Integer.parseInt(scanner.nextLine());
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input provided.\n" + e.getMessage());
            }
        }

        // Launch the selected interface
        if (choice == 3) {
            System.out.println("Exiting...");
            System.exit(0);
        } else if (choice == 2) {
            ((InquirerQueryCLI) choiceMap.get(choice)).run();
        } else {
            ((DisasterVictimCLI) choiceMap.get(choice)).run();
        }
    }
}