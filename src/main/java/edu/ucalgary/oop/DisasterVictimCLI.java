package edu.ucalgary.oop;

import java.util.HashMap;
import java.util.Scanner;


public class DisasterVictimCLI {
    // TODO: Null handling can be done in the CLI classes
    private final Scanner scanner;

    public DisasterVictimCLI() {
        scanner = new Scanner(System.in);
    }

    public void createDisasterVictim() {
        // Instantiate common keywords in HashMap
        HashMap<String, Boolean> cliKeywords = new HashMap<>();
        cliKeywords.put("yes", true);
        cliKeywords.put("y", true);
        cliKeywords.put("no", false);
        cliKeywords.put("n", false);

        System.out.println("Enter the first name of the disaster victim: ");
        // Try the input and catch any thrown exceptions
        while (true) {
            try {
                String firstName = scanner.nextLine();
                while (firstName.trim().isEmpty()) {
                    System.out.println("First name cannot be empty");
                    System.out.println("Enter the first name of the disaster victim: ");
                    firstName = scanner.nextLine();
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input provided");
            }
        }

        System.out.println("Enter the last name of the disaster victim: ");
        String lastName = scanner.nextLine();
        while (lastName.trim().isEmpty()) {
            System.out.println("Last name cannot be empty");
            System.out.println("Enter the last name of the disaster victim: ");
            lastName = scanner.nextLine();
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
            String dob = scanner.nextLine();
            while (dob.trim().isEmpty()) {
                System.out.println("Date of birth cannot be empty");
                System.out.println("Enter the date of birth of the disaster victim: ");
                dob = scanner.nextLine();
            }
        } else {
            System.out.println("Enter the approximate age of the disaster victim: ");
            String age = scanner.nextLine();
        }

        System.out.println("Enter comments about the disaster victim: ");
        String comments = scanner.nextLine();

        // For later: familyConnections, dietaryRestrictions, medicalRecords, personalBelongings

        System.out.println("Enter the gender of the disaster victim: ");
        String gender = scanner.nextLine();

        // Validate gender
        while (!ApplicationUtils.isValidGender(gender)) {
            System.out.println("Invalid gender provided");
            System.out.println("Enter valid gender");
        }

    }
}
