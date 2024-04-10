package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;


public class DisasterVictimCLI {
    private final Scanner scanner;
    private final HashMap<Integer, String> relationshipMap = new HashMap<>();

    public DisasterVictimCLI() {
        scanner = new Scanner(System.in);

        // Assign relationships
        relationshipMap.put(1, "parent");
        relationshipMap.put(2, "child");
        relationshipMap.put(3, "sibling");
    }

    private <T> HashMap<Integer, T> hashMapArrayList(ArrayList<T> disasterVictims) {
        HashMap<Integer, T> hashMap = new HashMap<>();
        for (int i = 0; i < disasterVictims.size(); i++) {
            hashMap.put(i, disasterVictims.get(i));
        }
        return hashMap;
    }

    private <T> HashMap<Integer, T> hashMapHashSet(HashSet<T> disasterVictims) {
        HashMap<Integer, T> hashMap = new HashMap<>();
        int i = 0;
        for (T disasterVictim : disasterVictims) {
            hashMap.put(i, disasterVictim);
            i++;
        }
        return hashMap;
    }

    private void printDisasterVictims(HashMap<Integer, DisasterVictim> hashMap) {
        for (int i = 0; i < hashMap.size(); i++) {
            System.out.println(i + ". " + hashMap.get(i).getFirstName() + " " + hashMap.get(i).getLastName());
        }
    }

    private <T> T getChoiceFromHashMap(HashMap<Integer, T> hashMap) {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                while (choice < 0 || choice >= hashMap.size()) {
                    System.out.println("Invalid choice provided");
                    System.out.println("Please choose a valid option: ");
                    choice = Integer.parseInt(scanner.nextLine());
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input provided.\n" + e.getMessage());
            }
        }
        return hashMap.get(choice);
    }

    /**
     * Method to create a HashMap from a list of DisasterVictims
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
        System.out.println("Welcome Local Worker!\nThis is the new Disaster Victim Command Line Interface.\nYour Location: " + MainApplication.locationWorkerLocation.getName() +
                "\nAddress: " + MainApplication.locationWorkerLocation.getAddress() + "\n\n");
        System.out.println("Please select an option from the following: ");
        System.out.println("1. Create a disaster victim");
        System.out.println("2. Assign relationships to a disaster victim");
        System.out.println("3. Add medical records to a disaster victim");
        System.out.println("4. View all disaster victims");
        System.out.println("5. Exit Application");
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

        // Add closing message
        System.out.println("Disaster victim successfully created!\nView other options in the menu if you wish to add more information.");
    }

    public void assignRelationships() {
        FamilyRelation familyRelation = new FamilyRelation();

        ArrayList<DisasterVictim> allDisasterVictims = MainApplication.locationWorkerLocation.getOccupants();
        if (allDisasterVictims.isEmpty()) {
            System.out.println("No disaster victims found in your location.\nPlease add a disaster victim first.\nExiting...");
            return;
        }
        System.out.println("Which Disaster Victim would you like to assign as the first person in the relationship?\nSelect a number from the following options: ");

        // Use functions
        HashMap<Integer, DisasterVictim> allDisasterVictimsMap = hashMapArrayList(allDisasterVictims);
        printDisasterVictims(allDisasterVictimsMap);
        DisasterVictim disasterVictim = getChoiceFromHashMap(allDisasterVictimsMap);

        familyRelation.setPersonOne(disasterVictim);

        System.out.println("What type of relationship would you like to assign to the disaster victim?\nSelect a number from the following options: ");

        // Use relationshipMap
        for (int i = 1; i <= relationshipMap.size(); i++) {
            System.out.println(i + ". " + relationshipMap.get(i));
        }

        String relationship = getChoiceFromHashMap(relationshipMap);
        familyRelation.setRelationshipTo(relationship);

        System.out.println("Which Disaster Victim would you like to assign as the second person in the relationship?\nSelect a number from the following options: ");

        // Use functions
        printDisasterVictims(allDisasterVictimsMap);
        disasterVictim = getChoiceFromHashMap(allDisasterVictimsMap);

        familyRelation.setPersonTwo(disasterVictim);

        // Add closing message
        System.out.println("Relationship successfully assigned!\nView other options in the menu if you wish to add more information.");
    }

    public void addMedicalRecords() {

    }
}