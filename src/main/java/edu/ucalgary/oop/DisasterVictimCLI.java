package edu.ucalgary.oop;

import java.util.HashMap;
import java.util.Scanner;


public class DisasterVictimCLI {
    private final Scanner scanner;

    public DisasterVictimCLI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Run the DisasterVictimCLI
     */
    public void run() {
        while (true) {
            displayMenu();
            int choice;
            while (true) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    while (choice < 1 || choice > 4) {
                        System.out.println("Invalid choice provided");
                        System.out.println("Please choose a valid option: ");
                        choice = Integer.parseInt(scanner.nextLine());
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input provided.\n" + e.getMessage());
                }
            }
            if (choice == 4) {
                System.out.println("Exiting...");
                System.exit(0);
            }
            // Run the choice
            HashMap<Integer, Runnable> choiceMap = new HashMap<>();
            choiceMap.put(1, this::createDisasterVictim);
            choiceMap.put(2, this::assignRelationships);
            choiceMap.put(3, this::addMedicalRecords);
            choiceMap.get(choice).run();
        }
    }

    private void displayMenu() {
        System.out.println("Welcome to the Disaster Victim Command Line Interface");
        System.out.println("Please select an option from the following: ");
        System.out.println("1. Create a disaster victim");
        System.out.println("2. Assign relationships to a disaster victim");
        System.out.println("3. Add medical records to a disaster victim");
        System.out.println("4. Exit");
    }

    public void createDisasterVictim() {
        // Instantiate common keywords in HashMap
        HashMap<String, Boolean> cliKeywords = new HashMap<>();
        cliKeywords.put("yes", true);
        cliKeywords.put("y", true);
        cliKeywords.put("no", false);
        cliKeywords.put("n", false);

        // Instantiate disaster victim
        DisasterVictim disasterVictim = new DisasterVictim();

        // Enter user data
        System.out.println("Enter the first name of the disaster victim: ");
        while (true) {
            try {
                disasterVictim.setFirstName(scanner.nextLine());
                while (disasterVictim.getFirstName().trim().isEmpty()) {
                    System.out.println("First name cannot be empty");
                    System.out.println("Enter the first name of the disaster victim: ");
                    disasterVictim.setFirstName(scanner.nextLine());
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input provided.\n" + e.getMessage());
            }
        }

        System.out.println("Enter the last name of the disaster victim: ");
        while (true) {
            try {
                disasterVictim.setLastName(scanner.nextLine());
                while (disasterVictim.getLastName().trim().isEmpty()) {
                    System.out.println("Last name cannot be empty");
                    System.out.println("Enter the last name of the disaster victim: ");
                    disasterVictim.setLastName(scanner.nextLine());
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input provided.\n" + e.getMessage());
            }
        }

        // Enter either approximate age or date of birth
        System.out.println("Do you know the date of birth of the disaster victim? (yes/no)");
        String dobChoice = scanner.nextLine().toLowerCase();
        while (!cliKeywords.containsKey(dobChoice)) {
            System.out.println("Invalid choice provided");
            System.out.println("Do you know the date of birth of the disaster victim? (yes/no)");
            dobChoice = scanner.nextLine().toLowerCase();
        }

        if (cliKeywords.get(dobChoice)) {
            System.out.println("Enter the date of birth of the disaster victim: ");
            while (true) {
                try {
                    disasterVictim.setDateOfBirth(scanner.nextLine());
                    while (disasterVictim.getDateOfBirth().trim().isEmpty()) {
                        System.out.println("Date of birth cannot be empty");
                        System.out.println("Enter the date of birth of the disaster victim: ");
                        disasterVictim.setDateOfBirth(scanner.nextLine());
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input provided.\n" + e.getMessage());
                }
            }
        } else {
            System.out.println("Enter the approximate age of the disaster victim: ");
            while (true) {
                try {
                    disasterVictim.setApproximateAge(Integer.parseInt(scanner.nextLine()));
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input provided.\n" + e.getMessage());
                }
            }
        }

        System.out.println("Enter comments about the disaster victim: ");
        while (true) {
            try {
                disasterVictim.setComments(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Invalid input provided.\n" + e.getMessage());
            }
        }

        // For later: familyConnections, dietaryRestrictions, medicalRecords, personalBelongings

        System.out.println("Enter the gender of the disaster victim:\nSelect a number from the following options: ");

        // Create gender HashMap
        HashMap<Integer, String> genderMap = new HashMap<>();

        int i = 1;
        // Loop through MainApplication.validGenders, print and add to genderMap
        for (String validGender : MainApplication.validGenders) {
            System.out.println(i + ". " + validGender);
            genderMap.put(i, validGender);
            i++;
        }

        // Get the user's choice
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                while (choice < 1 || choice > MainApplication.validGenders.size()) {
                    System.out.println("Invalid choice provided");
                    System.out.println("Please choose a valid option: ");
                    choice = Integer.parseInt(scanner.nextLine());
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input provided.\n" + e.getMessage());
            }
        }

        // Assign choice to disasterVictim
        disasterVictim.setGender(genderMap.get(choice));

        // Add disaster victim to a location
        if (MainApplication.workerType.equals("central")) {
            System.out.println("What location do you want to add the disaster victim to?\nSelect a number from the following options: ");

            // Create location HashMap
            HashMap<Integer, Location> locationMap = new HashMap<>();

            i = 1;
            // Loop through MainApplication.storedLocations, print and add to locationMap
            for (Location location : MainApplication.storedLocations) {
                System.out.println(i + ". " + location.getName());
                locationMap.put(i, location);
                i++;
            }

            // Get the user's choice
            while (true) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    while (choice < 1 || choice > MainApplication.storedLocations.size()) {
                        System.out.println("Invalid choice provided");
                        System.out.println("Please choose a valid option: ");
                        choice = Integer.parseInt(scanner.nextLine());
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input provided.\n" + e.getMessage());
                }
            }

            // Assign choice to disasterVictim
            locationMap.get(choice).addOccupant(disasterVictim);
        }
    }

    public void assignRelationships() {
    // Instantiate common keywords in HashMap
        HashMap<String, Boolean> cliKeywords = new HashMap<>();
        cliKeywords.put("yes", true);
        cliKeywords.put("y", true);
        cliKeywords.put("no", false);
        cliKeywords.put("n", false);

        // Enter family connections
        System.out.println("Does the disaster victim have any family connections? (yes/no)");

        String familyConnectionsChoice = scanner.nextLine().toLowerCase();
        while (!cliKeywords.containsKey(familyConnectionsChoice)) {
            System.out.println("Invalid choice provided");
            System.out.println("Does the disaster victim have any family connections? (yes/no)");
            familyConnectionsChoice = scanner.nextLine().toLowerCase();
        }
    }

    public void addMedicalRecords() {
        // Instantiate common keywords in HashMap
        HashMap<String, Boolean> cliKeywords = new HashMap<>();
        cliKeywords.put("yes", true);
        cliKeywords.put("y", true);
        cliKeywords.put("no", false);
        cliKeywords.put("n", false);

        // Enter medical records
        System.out.println("Does the disaster victim have any medical records? (yes/no)");

        String medicalRecordsChoice = scanner.nextLine().toLowerCase();
        while (!cliKeywords.containsKey(medicalRecordsChoice)) {
            System.out.println("Invalid choice provided");
            System.out.println("Does the disaster victim have any medical records? (yes/no)");
            medicalRecordsChoice = scanner.nextLine().toLowerCase();
        }
    }
}