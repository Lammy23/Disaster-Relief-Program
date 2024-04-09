package edu.ucalgary.oop;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

// TODO: Create more Tests for functions that implement database access
// and input and output (printing), and those that interface with the
// stored locations. Phew!

public class MainApplication {

    public static String workerType;
    public static HashSet<Location> storedLocations;

    public static void main(String[] args) {

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

        // TODO: Get gender options and delete respective function in DisasterVictim
        // Testing I/O
        Scanner scanner = new Scanner(System.in);
        String testInput = scanner.nextLine();
        System.out.println(testInput);

        if (workerType.equals("central")) {
            // Introduction
            System.out.println("Welcome to the HeadQuarters Relief Management System");
            System.out.println("Select what action you'd like to perform\n" +
                    "1. Enter disaster victim information\n" +
                    "2. Blah Blah Blah\n" +
                    "3. Blah Blah Blah");

            if (Integer.parseInt(scanner.nextLine()) == 1) {
                System.out.println("Please enter the entry date of the victim.\nValid Formats are: " +
                        "\nYYYY-MM-DD\nYYYY/MM/DD\nYYYY.MM.DD\n\nEnter here: ");
                String entryDate = scanner.nextLine();
                DisasterVictim disasterVictim = new DisasterVictim(entryDate);

                // Calling interface function with location list and worker-flag
                disasterVictim.enterDisasterVictimInfo();

            }
        }


    }
}
