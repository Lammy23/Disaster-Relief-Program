package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static edu.ucalgary.oop.ApplicationUtils.*;

/**
 * This class is responsible for handling the command line interface for the disaster victims
 *
 * @author Olamikun Aluko
 * <a href="mailto:lammyaluko@gmail.com">Email me</a> for any comments
 * @version 1.1
 * @since 1.0
 */
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
                    while (choice < 1 || choice > 7) {
                        System.out.println("Invalid choice provided");
                        System.out.println("Please choose a valid option: ");
                        choice = Integer.parseInt(scanner.nextLine());
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input provided.\n" + e.getMessage());
                }
            }
            if (choice == 7) {
                System.out.println("Exiting...");
                System.out.println("Press any key to continue...");
                scanner.nextLine();
                System.exit(0);
            }
            // Run the choice
            HashMap<Integer, Runnable> choiceMap = new HashMap<>();
            choiceMap.put(1, this::createDisasterVictim);
            choiceMap.put(2, this::assignRelationships);
            choiceMap.put(3, this::addMedicalRecords);
            choiceMap.put(4, this::viewAll);
            choiceMap.put(5, this::addSupplies);
            choiceMap.put(6, this::viewDisasterVictimInfo);
            choiceMap.get(choice).run();
        }
    }

    private void displayMenu() {
        System.out.println("\n\nWelcome Local Worker!\nThis is the new Disaster Victim Command Line Interface.\nYour Location: " + MainApplication.locationWorkerLocation.getName() + "\nAddress: " + MainApplication.locationWorkerLocation.getAddress() + "\n\n");
        System.out.println("Please select an option from the following: ");
        System.out.println("1. Create a disaster victim");
        System.out.println("2. Assign relationships to a disaster victim");
        System.out.println("3. Add medical records to a disaster victim");
        System.out.println("4. View all disaster victims");
        System.out.println("5. Add supplies to a disaster victim");
        System.out.println("6. View specific Disaster Victim Information");
        System.out.println("7. Exit Application");
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
            System.out.println("Enter the date of birth of the disaster victim.\nValid Formats are: " + "\nYYYY-MM-DD\nYYYY/MM/DD\nYYYY.MM.DD\n\nEnter here: ");
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

        // Use functions
        HashMap<Integer, String> genderMap = hashMapHashSet(MainApplication.validGenders);
        printHashMap(genderMap);
        String gender = getChoiceFromHashMap(genderMap, scanner);

        // Assign choice to disasterVictim
        disasterVictim.setGender(gender);

        // Add disaster victim to a workerLocation
        MainApplication.locationWorkerLocation.addOccupant(disasterVictim);

        // Add closing message
        System.out.println("Disaster victim successfully created!\nView other options in the menu if you wish to add more information.");
        System.out.println("Press any key to continue...");
        scanner.nextLine();
    }

    public void assignRelationships() {
        FamilyRelation familyRelation = new FamilyRelation();

        ArrayList<DisasterVictim> allDisasterVictims = MainApplication.locationWorkerLocation.getOccupants();
        if (allDisasterVictims.isEmpty()) {
            System.out.println("No disaster victims found in your location.\nPlease add a disaster victim first.\nReturning to Menu...");
            return;
        } else if (allDisasterVictims.size() == 1) {
            System.out.println("Only one disaster victim found in your location.\nPlease add another disaster victim first.\nReturning to Menu...");
            return;
        }
        System.out.println("Which Disaster Victim would you like to assign as the first person in the relationship?\nSelect a number from the following options: ");

        // Use functions
        HashMap<Integer, DisasterVictim> allDisasterVictimsMap = hashMapArrayList(allDisasterVictims);
        printDisasterVictims(allDisasterVictimsMap);
        DisasterVictim disasterVictim = getChoiceFromHashMap(allDisasterVictimsMap, scanner);

        familyRelation.setPersonOne(disasterVictim);

        System.out.println("What type of relationship would you like to assign to the disaster victim?\nSelect a number from the following options: ");

        // Use relationshipMap
        for (int i = 1; i <= relationshipMap.size(); i++) {
            System.out.println(i + ". " + relationshipMap.get(i));
        }

        String relationship = getChoiceFromHashMap(relationshipMap, scanner);
        familyRelation.setRelationshipTo(relationship);

        System.out.println("Which Disaster Victim would you like to assign as the second person in the relationship?\nSelect a number from the following options: ");

        // Use functions
        printDisasterVictims(allDisasterVictimsMap);
        DisasterVictim disasterVictim2 = getChoiceFromHashMap(allDisasterVictimsMap, scanner);

        familyRelation.setPersonTwo(disasterVictim2);

        disasterVictim.addFamilyConnection(familyRelation);

        // Add closing message
        System.out.println("Relationship successfully assigned!\nView other options in the menu if you wish to add more information.");
        System.out.println("Press any key to continue...");
        scanner.nextLine();
    }

    public void addMedicalRecords() {

        ArrayList<DisasterVictim> allDisasterVictims = MainApplication.locationWorkerLocation.getOccupants();
        if (allDisasterVictims.isEmpty()) {
            System.out.println("No disaster victims found in your location.\nPlease add a disaster victim first.\nReturning to Menu...");
            return;
        }

        System.out.println("Which Disaster Victim would you like to add medical records to?\nSelect a number from the following options: ");

        // Use functions
        HashMap<Integer, DisasterVictim> allDisasterVictimsMap = hashMapArrayList(allDisasterVictims);
        printDisasterVictims(allDisasterVictimsMap);
        DisasterVictim disasterVictim = getChoiceFromHashMap(allDisasterVictimsMap, scanner);

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setLocation(MainApplication.locationWorkerLocation);

        System.out.println("Enter the treatment details.\nWhat treatment was provided to the disaster victim?");
        while (true) {
            try {
                medicalRecord.setTreatmentDetails(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Invalid input provided.\n" + e.getMessage());
            }
        }

        System.out.println("Enter the date of treatment");
        while (true) {
            try {
                medicalRecord.setDateOfTreatment(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Invalid input provided.\n" + e.getMessage());
            }
        }

        disasterVictim.addMedicalRecord(medicalRecord);

        // Add closing message
        System.out.println("Medical records successfully added!\nView other options in the menu if you wish to add more information.");
        System.out.println("Press any key to continue...");
        scanner.nextLine();
    }

    public void viewAll() {

        ArrayList<DisasterVictim> allDisasterVictims = MainApplication.locationWorkerLocation.getOccupants();
        if (allDisasterVictims.isEmpty()) {
            System.out.println("No disaster victims found in your location.\nPlease add a disaster victim first.\nReturning to Menu...");
            return;
        }

        System.out.println("All disaster victims in your location: ");
        HashMap<Integer, DisasterVictim> allDisasterVictimsMap = hashMapArrayList(allDisasterVictims);
        printDisasterVictims(allDisasterVictimsMap);
        System.out.println("Press any key to continue...");
        scanner.nextLine();
    }

    public void addSupplies() {

        ArrayList<DisasterVictim> allDisasterVictims = MainApplication.locationWorkerLocation.getOccupants();
        if (allDisasterVictims.isEmpty()) {
            System.out.println("No disaster victims found in your location.\nPlease add a disaster victim first.\nReturning to Menu...");
            return;
        }

        System.out.println("Which Disaster Victim would you like to add supplies to?\nSelect a number from the following options: ");

        // Use functions
        HashMap<Integer, DisasterVictim> allDisasterVictimsMap = hashMapArrayList(allDisasterVictims);
        printDisasterVictims(allDisasterVictimsMap);
        DisasterVictim disasterVictim = getChoiceFromHashMap(allDisasterVictimsMap, scanner);

        // Check if there are any supplies in your location
        if (MainApplication.locationWorkerLocation.getSupplies().isEmpty()) {
            System.out.println("No supplies found in your location.\nReturning to Menu...");
            return;
        }

        System.out.println("Which supply would you like to add from your location.\nSelect a number from the following options: ");

        // Use functions
        HashMap<Integer, Supply> allSuppliesMap = hashMapHashSet(MainApplication.locationWorkerLocation.getSupplies());
        printHashMap(allSuppliesMap);
        Supply supply = getChoiceFromHashMap(allSuppliesMap, scanner);

        Supply newSupply = new Supply();
        newSupply.setType(supply.getType());

        System.out.println("Enter the quantity of " + newSupply.getType() + " you would like to add: ");
        while (true) {
            try {
                newSupply.setQuantity(Integer.parseInt(scanner.nextLine()));
                break;
            } catch (Exception e) {
                System.out.println("Invalid input provided.\n" + e.getMessage());
            }
        }

        newSupply.setSource(MainApplication.locationWorkerLocation);
        disasterVictim.addPersonalBelonging(newSupply);

        // Add closing message
        System.out.println("Supplies successfully added!\nView other options in the menu if you wish to add more information.");
        System.out.println("Press any key to continue...");
        scanner.nextLine();
    }

    public void addDietaryRestrictions() {

        ArrayList<DisasterVictim> allDisasterVictims = MainApplication.locationWorkerLocation.getOccupants();
        if (allDisasterVictims.isEmpty()) {
            System.out.println("No disaster victims found in your location.\nPlease add a disaster victim first.\nReturning to Menu...");
            return;
        }

        System.out.println("Which Disaster Victim would you like to add dietary restrictions to?\nSelect a number from the following options: ");

        // Use functions
        HashMap<Integer, DisasterVictim> allDisasterVictimsMap = hashMapArrayList(allDisasterVictims);
        printDisasterVictims(allDisasterVictimsMap);
        DisasterVictim disasterVictim = getChoiceFromHashMap(allDisasterVictimsMap, scanner);

        System.out.println("What dietary restriction would you like to add to the disaster victim?\nSelect a number from the following options:");
        // Use functions
        HashMap<Integer, DietaryRestriction> dietaryRestrictionMap = hashMapArray(DietaryRestriction.values());
        printDietaryRestrictions(dietaryRestrictionMap);
        DietaryRestriction dietaryRestriction = getChoiceFromHashMap(dietaryRestrictionMap, scanner);

        disasterVictim.addDietaryRestriction(dietaryRestriction);

        // Add closing message
        System.out.println("Dietary restrictions successfully added!\nView other options in the menu if you wish to add more information.");
        System.out.println("Press any key to continue...");
        scanner.nextLine();
    }

    public void viewDisasterVictimInfo() {

        ArrayList<DisasterVictim> allDisasterVictims = MainApplication.locationWorkerLocation.getOccupants();
        if (allDisasterVictims.isEmpty()) {
            System.out.println("No disaster victims found in your location.\nPlease add a disaster victim first.\nReturning to Menu...");
            return;
        }

        System.out.println("Which Disaster Victim would you like to view information for?\nSelect a number from the following options: ");

        // Use functions
        HashMap<Integer, DisasterVictim> allDisasterVictimsMap = hashMapArrayList(allDisasterVictims);
        printDisasterVictims(allDisasterVictimsMap);
        DisasterVictim disasterVictim = getChoiceFromHashMap(allDisasterVictimsMap, scanner);

        System.out.println("Disaster Victim Information: ");
        System.out.println("First Name: " + disasterVictim.getFirstName());
        System.out.println("Last Name: " + disasterVictim.getLastName());
        System.out.println("Assigned Social ID: " + disasterVictim.getAssignedSocialID());
        System.out.println("Entry Date: " + disasterVictim.getEntryDate());
        if (disasterVictim.getDateOfBirth() == null) {
            System.out.println("Approximate Age: " + disasterVictim.getApproximateAge());
        } else {
            System.out.println("Date of Birth: " + disasterVictim.getDateOfBirth());
        }

        System.out.println("Comments: " + disasterVictim.getComments());
        System.out.println("Family Connections: ");
        disasterVictim.getFamilyConnections().forEach((connection) -> {
            System.out.println(connection + " of " + connection.getPersonTwo().getFirstName() + " " + connection.getPersonTwo().getLastName());
        });

        System.out.println("Dietary Restrictions: ");
        disasterVictim.getDietaryRestrictions().forEach((restriction) -> {
            System.out.println(restriction.getDescription());
        });

        System.out.println("Medical Records: ");
        disasterVictim.getMedicalRecords().forEach((record) -> {
            System.out.println("Treatment: " + record.getTreatmentDetails() + "\nDate of Treatment: " + record.getDateOfTreatment());
        });

        System.out.println("Personal Belongings: ");
        disasterVictim.getPersonalBelongings().forEach((belonging) -> {
            System.out.println("Type: " + belonging.getType() + "\nQuantity: " + belonging.getQuantity());
        });
        System.out.println("Press any key to continue...");
        scanner.nextLine();
    }
}