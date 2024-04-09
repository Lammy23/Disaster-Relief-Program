package edu.ucalgary.oop;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * This class is responsible for handling the command line interface for the inquirer queries
 */
public class InquirerQueryCLI {
    private final Scanner scanner;

    /**
     * Constructor for the InquirerQueryCLI class
     */
    public InquirerQueryCLI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * This method is responsible for creating an inquirer and adding it to the storedInquirers set
     */
    public void createInquirer() {
        System.out.println("Enter the first name of the inquirer: ");
        String firstName;
        while (true) {
            try {
                firstName = scanner.nextLine();
                while (firstName.trim().isEmpty()) {
                    System.out.println("First name cannot be empty");
                    System.out.println("Enter the first name of the inquirer: ");
                    firstName = scanner.nextLine();
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input provided.\n" + e.getMessage());
            }
        }

        System.out.println("Enter the last name of the inquirer: ");
        String lastName;
        while (true) {
            try {
                lastName = scanner.nextLine();
                while (lastName.trim().isEmpty()) {
                    System.out.println("Last name cannot be empty");
                    System.out.println("Enter the last name of the inquirer: ");
                    lastName = scanner.nextLine();
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input provided.\n" + e.getMessage());
            }
        }

        System.out.println("Enter the information provided by the inquirer: ");
        String info;
        while (true) {
            try {
                info = scanner.nextLine();
                while (info.trim().isEmpty()) {
                    System.out.println("Information cannot be empty");
                    System.out.println("Enter the information provided by the inquirer: ");
                    info = scanner.nextLine();
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input provided.\n" + e.getMessage());
            }
        }

        System.out.println("Enter the phone number of the inquirer: ");
        String phone;
        while (true) {
            try {
                phone = scanner.nextLine();
                while (phone.trim().isEmpty()) {
                    System.out.println("Phone number cannot be empty");
                    System.out.println("Enter the phone number of the inquirer: ");
                    phone = scanner.nextLine();
                }
                break;
            } catch (Exception e) {
                // REQ something: to handle the exception in a graceful manner
                System.out.println("Invalid input provided.\n" + e.getMessage());
            }
        }

        Inquirer inquirer = new Inquirer(firstName, lastName, info, phone);
        MainApplication.storedInquirers.add(inquirer);

    }

    /**
     * This method is responsible for deleting an inquirer from the storedInquirers set
     */
    public void deleteInquirer() {
        System.out.println("If known, enter the first name of the inquirer you would like to delete: ");
        String firstName;
        while (true) {
            try {
                firstName = scanner.nextLine();
                while (firstName.trim().isEmpty()) {
                    System.out.println("First name cannot be empty");
                    System.out.println("Enter the first name of the inquirer you would like to delete: ");
                    firstName = scanner.nextLine();
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input provided.\n" + e.getMessage());
            }
        }

        System.out.println("If known, enter the last name of the inquirer you would like to delete: ");
        String lastName;
        while (true) {
            try {
                lastName = scanner.nextLine();
                while (lastName.trim().isEmpty()) {
                    System.out.println("Last name cannot be empty");
                    System.out.println("Enter the last name of the inquirer you would like to delete: ");
                    lastName = scanner.nextLine();
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input provided.\n" + e.getMessage());
            }
        }

        // Find the inquirer in the storedInquirers set
        for (Inquirer inquirer : MainApplication.storedInquirers) {
            if (inquirer.getFirstName().equals(firstName) || inquirer.getLastName().equals(lastName)) {
                MainApplication.storedInquirers.remove(inquirer);
                break;
            }
        }
    }

    /**
     * This method is responsible for searching for an inquirer in the storedInquirers set
     * @return Inquirer
     */
    public Inquirer searchInquirer() {
        System.out.println("Enter a search keyword.\nIt should be at least three characters.\nWe'll handle the search for you: ");
        String keyword;
        while (true) {
            try {
                keyword = scanner.nextLine().toLowerCase();
                while (keyword.trim().length() < 3) {
                    System.out.println("Keyword should be at least three characters");
                    System.out.println("Enter a search keyword: ");
                    keyword = scanner.nextLine();
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input provided.\n" + e.getMessage());
            }
        }

        // Create a set to store the matching inquirers
        HashSet<Inquirer> matchingInquirers = new HashSet<>();

        for (Inquirer inquirer : MainApplication.storedInquirers) {
            if (inquirer.getFirstName().toLowerCase().contains(keyword) || inquirer.getLastName().toLowerCase().contains(keyword)) {
                matchingInquirers.add(inquirer);
            }
        }

        // Create a map to store the inquirer and the number representing the inquirer
        HashMap<Integer, Inquirer> inquirerChoiceMap = new HashMap<>();

        System.out.println("Matching inquirers: " + matchingInquirers.size());
        System.out.println("Please choose the number representing the inquirer you are looking for:");

        // Display the matching inquirers and simultaneously store them in the map
        int i = 1;
        for (Inquirer inquirer : matchingInquirers) {
            System.out.println(i + ". " + inquirer.getFirstName() + " " + inquirer.getLastName());
            inquirerChoiceMap.put(i, inquirer);
            i++;
        }

        // Get the user's choice
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                while (choice < 1 || choice > matchingInquirers.size()) {
                    System.out.println("Invalid choice provided");
                    System.out.println("Please choose the number representing the inquirer you are looking for:");
                    choice = Integer.parseInt(scanner.nextLine());
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input provided.\n" + e.getMessage());
            }
        }

        return inquirerChoiceMap.get(choice);
    }
}