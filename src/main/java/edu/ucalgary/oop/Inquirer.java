package edu.ucalgary.oop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Inquirer implements InquirerInterface {
    private final String FIRST_NAME;
    private final String LAST_NAME;
    private final String INFO;
    private final String SERVICES_PHONE;

    private Connection dbConnect;
    private ResultSet results;

    public Inquirer(String FIRST_NAME, String LAST_NAME, String INFO, String SERVICES_PHONE) {
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.INFO = INFO;
        this.SERVICES_PHONE = SERVICES_PHONE;
    }

    public String getFirstName() {
        return FIRST_NAME;
    }

    public String getLastName() {
        return LAST_NAME;
    }

    public String getServicesPhone() {
        return SERVICES_PHONE;
    }

    public String getInfo() {
        return INFO;
    }

    public String getLogDetails() {
        /* Reading log details from postgreSQL database */
        StringBuilder logDetails = new StringBuilder();

        try {
            String query = "SELECT inquiry_log.details, inquiry_log.calldate FROM inquirer RIGHT OUTER JOIN inquiry_log ON inquirer.id = inquiry_log.inquirer WHERE (inquirer.firstname = ? AND inquirer.lastname = ?)";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setString(1, this.FIRST_NAME);
            myStmt.setString(2, this.LAST_NAME);
            results = myStmt.executeQuery();

            while (results.next()) {
                logDetails.append(results.getString("calldate")).append(results.getString("details"));
                logDetails.append("\n\n");
            }
            myStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logDetails.toString();
    }

    /*---------------Interface Methods---------------*/


    @Override
    public void createInquirer() {
        Scanner scanner = new Scanner(System.in);

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

    @Override
    public void deleteInquirer() {
        Scanner scanner = new Scanner(System.in);

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

    @Override
    public Inquirer searchInquirer() {
        // Robust enough so that even if the user only knows 3 letters of the firstName or lastName, we can still get the inquirer
        Scanner scanner = new Scanner(System.in);

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

        // Return the inquirer that the user chose
        return inquirerChoiceMap.get(choice);
    }
}