package edu.ucalgary.oop;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class InquirerQueryCLI {
    private Scanner scanner;

    public InquirerQueryCLI() {
        this.scanner = new Scanner(System.in);
    }

    public void createInquirer() {
        System.out.println("Enter the first name of the inquirer: ");
        String firstName = scanner.nextLine();

        System.out.println("Enter the last name of the inquirer: ");
        String lastName = scanner.nextLine();

        System.out.println("Enter the information provided by the inquirer: ");
        String info = scanner.nextLine();

        System.out.println("Enter the phone number of the inquirer: ");
        String phone = scanner.nextLine();

        Inquirer inquirer = new Inquirer(firstName, lastName, info, phone);
        MainApplication.storedInquirers.add(inquirer);
    }

    public void deleteInquirer() {
        System.out.println("If known, enter the first name of the inquirer you would like to delete: ");
        String firstName = scanner.nextLine();

        System.out.println("If known, enter the last name of the inquirer you would like to delete: ");
        String lastName = scanner.nextLine();

        // Find the inquirer in the storedInquirers set
        for (Inquirer inquirer : MainApplication.storedInquirers) {
            if (inquirer.getFirstName().equals(firstName) || inquirer.getLastName().equals(lastName)) {
                MainApplication.storedInquirers.remove(inquirer);
                break;
            }
        }
    }

    public Inquirer searchInquirer() {
        System.out.println("Enter a search keyword.\nIt should be at least three characters.\nWe'll handle the search for you: ");
        String keyword = scanner.nextLine();

        // Find and display all inquirers that match the keyword

        // Create a set to store the matching inquirers
        HashSet<Inquirer> matchingInquirers = new HashSet<>();

        for (Inquirer inquirer : MainApplication.storedInquirers) {
            if (inquirer.getFirstName().contains(keyword) || inquirer.getLastName().contains(keyword)) {
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
        int choice = scanner.nextInt();

        return inquirerChoiceMap.get(choice);
    }
}
