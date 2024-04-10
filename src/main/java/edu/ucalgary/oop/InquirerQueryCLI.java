package edu.ucalgary.oop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

import static edu.ucalgary.oop.ApplicationUtils.*;

/**
 * This class is responsible for handling the command line interface for the inquirer queries
 */
public class InquirerQueryCLI {
    private final Scanner scanner;
    private HashSet<ReliefService> storedServices = new HashSet<>();
    private final String MAINURL = "jdbc:postgresql://localhost/postgres";                      // Hardcoded main URL
    private final String DBURL = "jdbc:postgresql://localhost/ensf380project";          // Hardcoded database URL
    private final String USERNAME = "oop";                                              // Hardcoded database username
    private final String PASSWORD = "ucalgary";                                         // Hardcoded database password

    private Connection dbConnect;                                                       // Connection object for the database
    private ResultSet results;                                                          // ResultSet object for the database
    private String sqlFile = "src/main/resources/project.sql";                   // SQL file to create the database


    // TODO: Add these functions to ApplicationUtils

    /**
     * Constructor for the InquirerQueryCLI class
     */
    public InquirerQueryCLI() {
        this.scanner = new Scanner(System.in);
    }

    public void close() {
        try {
            if (results != null) results.close();
            if (dbConnect != null) dbConnect.close();

        } catch (SQLException e) {
            System.out.println("Error closing the database connection.\n" + e.getMessage());
            System.out.println("Exiting...");
            System.exit(1);                                                     // Exit the program with an error code
        } catch (Exception e) {
            System.out.println("Error accessing database objects.\n" + e.getMessage());
            System.out.println("Exiting...");
            System.exit(1);                                                     // Exit the program with an error code
        }
    }

    /**
     * This method is responsible for importing the storedReliefServices from the database
     */
    public void importDB() {
        // Registration
        try {
            // Connect to the general database
            dbConnect = DriverManager.getConnection(MAINURL, "postgres", "appleseed"); // TODO: Tell the user to put their own password here

            // Database we're concerned with is the ensf380project. We drop it if it exists and then create it
            String dropDB = "DROP DATABASE IF EXISTS ensf380project;";

            // Create the database
            String createDB = "CREATE DATABASE ensf380project;";

            // Transfer ownership of the database to the user
            String transferDB = "ALTER DATABASE ensf380project OWNER TO oop;";

            // Grant all privileges to the user
            String grantPrivileges = "GRANT ALL PRIVILEGES ON DATABASE ensf380project TO oop;";

            // Execute the SQL commands
            PreparedStatement stmt1 = dbConnect.prepareStatement(dropDB + createDB + transferDB + grantPrivileges);
            stmt1.executeUpdate();

            // close the connection
            close();

            // Connect to the target database
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);

            // Read the SQL file
            String sql = new String(Files.readAllBytes(Paths.get(sqlFile)));

            // Create a statement
            Statement stmt2 = dbConnect.createStatement();

            // Execute the SQL script
            stmt2.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("Error connecting to the database.\n" + e.getMessage());
            System.out.println("Exiting...");
            System.exit(1);                                                     // Exit the program with an error code
        } catch (IOException e) {
            System.out.println("Error reading the SQL file.\n" + e.getMessage());
            System.out.println("Exiting...");
            System.exit(1);                                                     // Exit the program with an error code
        }
    }

    public void createInquirersFromDB() {
        // Import the storedReliefServices from the database
        try {
            // To construct the Inquirer
            // Select: firstname, lastname, details, phonenumber
            String getInquirer = "SELECT * FROM inquirer";

            // Create a statement
            PreparedStatement stmt = dbConnect.prepareStatement(getInquirer);

            // Execute the query
            results = stmt.executeQuery();                 // Results stored in ResultsSet object

            // Iterate through the results
            while (results.next()) {
                String firstName = results.getString("firstname");
                String lastName = results.getString("lastname");
                String servicesPhone = results.getString("phonenumber");

                // Create a new Inquirer object
                Inquirer inquirer = new Inquirer(firstName, lastName, servicesPhone);

                // Add the Inquirer object to the storedServices set
                ReliefService reliefService = new ReliefService();
                reliefService.setInquirer(inquirer);

                storedServices.add(reliefService);
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database.\n" + e.getMessage());
            System.out.println("Exiting...");
            System.exit(1);                                                     // Exit the program with an error code
        } catch (Exception e) {
            System.out.println("Error creating inquirers object.\n" + e.getMessage());
            System.out.println("Exiting...");
            System.exit(1);                                                     // Exit the program with an error code
        }
    }

    public void assignInquirerLogsFromDB() {
        try {
            // To construct the InquiryLog
            // Select: calldate, details

            String getInquirerLogs = "SELECT firstname, lastname, calldate, details FROM inquirer JOIN inquiry_log ON inquirer.id = inquiry_log.inquirer";

            // Create a statement
            PreparedStatement stmt = dbConnect.prepareStatement(getInquirerLogs);

            // Execute the query
            results = stmt.executeQuery();                 // Results stored in ResultsSet object

            // Iterate through the results

            while (results.next()) {
                String firstName = results.getString("firstname");
                String lastName = results.getString("lastname");
                String callDate = results.getString("calldate");
                String details = results.getString("details");

                InquiryLog inquiryLog = new InquiryLog(callDate, details);

                // Find the Inquirer object
                for (ReliefService reliefService : storedServices) {
                    Inquirer targetInquirer = reliefService.getInquirer();

                    boolean firstNameMatch = Objects.equals(firstName, targetInquirer.getFirstName());
                    boolean lastNameMatch = Objects.equals(lastName, targetInquirer.getLastName());

                    if (firstNameMatch && lastNameMatch) {
                        reliefService.addLog(inquiryLog);
                    }

                }
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database.\n" + e.getMessage());
            System.out.println("Exiting...");
            System.exit(1);                                                     // Exit the program with an error code
        } catch (Exception e) {
            System.out.println("Error creating inquirers object.\n" + e.getMessage());
            System.out.println("Exiting...");
            System.exit(1);                                                     // Exit the program with an error code
        }
    }

    /**
     * This method is responsible for running the InquirerQueryCLI
     */
    public void run() {
        // Import the storedReliefServices from the database
        importDB();

        // Create the Inquirers from the database
        createInquirersFromDB();

        // For each Inquirer, add their logs
        assignInquirerLogsFromDB();

        // Display the menu
        int choice;
        while (true) {
            displayMenu();
            while (true) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    while (choice < 1 || choice > 7) {
                        System.out.println("Invalid choice provided");
                        displayMenu();
                        choice = Integer.parseInt(scanner.nextLine());
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input provided.\n" + e.getMessage());
                }
            }

            if (choice == 7) {
                // Begin process of storing the storedInquirers in the database
                exportDB();

                System.out.println("Exiting...");
                System.exit(0);
            }

            // Run the choice
            HashMap<Integer, Runnable> choiceMap = new HashMap<>();
            choiceMap.put(1, this::createInquirer);
            choiceMap.put(2, this::deleteInquirer);
            choiceMap.put(3, this::viewInquirerLogs);
            choiceMap.put(4, this::logInquires);
            choiceMap.put(5, this::searchMissingPerson);
            choiceMap.put(6, this::addMissingPersonToServiceWorker);
            choiceMap.get(choice).run();
        }
    }

    private void displayMenu() {
        System.out.println("\n\nWelcome to the Inquirer Query Command Line Interface.\nYour Location: Central Office\n\n");
        System.out.println("Please choose an option from the following:");
        System.out.println("1. Create an inquirer");                        // Create a whole new Inquirer and add it to the storedInquirers set. Ensure this change is reflected in the database
        System.out.println("2. Delete an inquirer");                        // Delete an Inquirer from the storedInquirers set. Ensure this change is reflected in the database
        System.out.println("3. View and inquirer's logs");                  // Show Inquirer's Name, then just all it's log in reverse chronological order
        System.out.println("4. Log inquires by an Inquirer");               // AKA addInquirerLogs. Add the logs to the inquirer's ArrayList of logs. Add the respective disaster victim to the log and also PriorityQueue.
        System.out.println("5. Search missing Person");                     // takes a keyword and searches
        System.out.println("6. Add missing Person to Service Worker Queue"); // takes a keyword and searches and then adds
        System.out.println("7. Exit");
    }

    /**
     * This method is responsible for creating an inquirer and adding it to the storedInquirers set
     */
    public void createInquirer() {
        System.out.println("Enter the first name of the inquirer: ");
        String firstName = scanner.nextLine();

        System.out.println("Enter the last name of the inquirer: ");
        String lastName = scanner.nextLine();

        System.out.println("Enter the phone number of the inquirer: ");
        String phoneNumber = scanner.nextLine();

        Inquirer inquirer = new Inquirer(firstName, lastName, phoneNumber);
        boolean isAssigned = false;         // Flag to check if the inquirer has been assigned to a service

        System.out.println("Looking for a service to assign the inquirer to...");
        // Add the Inquirer object to the storedServices set
        for (ReliefService reliefService : storedServices) {
            if (Objects.equals(reliefService.getInquirer().getFirstName(), inquirer.getFirstName()) && Objects.equals(reliefService.getInquirer().getLastName(), inquirer.getLastName())) {
                System.out.println("Inquirer already exists in the database.");
                return;
            }
            if (reliefService.getInquirer() == null) {
                reliefService.setInquirer(inquirer);
                System.out.println("Inquirer added to service.");
                isAssigned = true;
                break;
            }
        }

        if (!isAssigned) {
            System.out.println("No service available to assign the inquirer to. Creating a new service...");
            ReliefService reliefService = new ReliefService();
            reliefService.setInquirer(inquirer);
            storedServices.add(reliefService);
            System.out.println("Inquirer added to service.");
        }

        // Add closing message
        System.out.println("Inquirer created successfully!\nView other options in the menus if you with to perform more actions.");
    }

    /**
     * This method is responsible for deleting an inquirer from the storedInquirers set
     */
    public void deleteInquirer() {
        // Show all inquirers, ask user to choose
        System.out.println("Choose an inquirer to delete.\nEnter the number of the inquirer you wish to view logs for: ");

        HashMap<Integer, ReliefService> reliefMap = hashMapHashSet(storedServices);
        printReliefServices(reliefMap);
        ReliefService chosenService = getChoiceFromHashMap(reliefMap, scanner);

        System.out.println("Deleting Inquirer...");
        chosenService.setInquirer(null);
        System.out.println("Inquirer deleted from service.");


        // Add closing message
        System.out.println("Inquirer deleted successfully!\nView other options in the menus if you with to perform more actions.");
    }

    public void viewInquirerLogs() {
        // Show all inquirers, ask user to choose
        System.out.println("Choose an inquirer to view logs for.\nEnter the number of the inquirer you wish to view logs for: ");

        HashMap<Integer, ReliefService> reliefMap = hashMapHashSet(storedServices);
        printReliefServices(reliefMap);
        ReliefService chosenService = getChoiceFromHashMap(reliefMap, scanner);

        // Display the logs
        System.out.println("Inquirer's logs: \n");
        for (InquiryLog log : chosenService.getLogs()) {
            System.out.println("Call Date: " + log.getDateOfInquiry());
            System.out.println("Details: " + log.getInfoProvided());
            System.out.println();
        }
    }

    public void logInquires() {
        // Show all inquirers, ask user to choose
        System.out.println("Choose an inquirer to log.\nEnter the number of the inquirer you wish to view logs for: ");

        HashMap<Integer, ReliefService> reliefMap = hashMapHashSet(storedServices);
        printReliefServices(reliefMap);
        ReliefService chosenService = getChoiceFromHashMap(reliefMap, scanner);

        // Add the logs
        System.out.println("Enter the date of the inquiry: ");
        String dateOfInquiry = scanner.nextLine();

        System.out.println("Enter the details of the inquiry: ");
        String details = scanner.nextLine();

        InquiryLog inquiryLog = new InquiryLog(dateOfInquiry, details);
        chosenService.addLog(inquiryLog);

        // Add closing message
        System.out.println("Inquirer logs added successfully!\nView other options in the menus if you with to perform more actions.");
        System.out.println("Press any key to continue..."); // TODO: Implement this everywhere
        scanner.nextLine();
    }

    public DisasterVictim searchMissingPerson() {
        // Loop through the storedServices and find the missing person
        System.out.println("Enter the keyword to search for the missing person: ");
        String keyword = scanner.nextLine();

        boolean isFound = false;                                            // Flag to check if the missing person has been found in the service
        HashSet<DisasterVictim> possibleVictims = new HashSet<>();         // Map to store the possible victims and their locations

        System.out.println("Searching for missing person with keyword: " + keyword);

        // Search for the missing person
        for (Location location : MainApplication.storedLocations) {
            for (DisasterVictim victim : location.getOccupants()) {
                if (victim.getFirstName().contains(keyword) || victim.getLastName().contains(keyword)) {
                    possibleVictims.add(victim);
                    isFound = true;
                }
            }
        }

        if (!isFound) {
            System.out.println("No matching missing person found in the database.\nReturning to the main menu...");
            return null;
        }

        // Display the possible victims
        System.out.println("Possible missing persons:");

        HashMap<Integer, DisasterVictim> hashMap = hashMapHashSet(possibleVictims);
        printDisasterVictims(hashMap);

        return getChoiceFromHashMap(hashMap, scanner);
    }

    public void addMissingPersonToServiceWorker() {
        // search for the missing person
        DisasterVictim missingPerson = searchMissingPerson();

        if (missingPerson == null) {
            return;
        }

        // Adding DisasterVictim to the Service priority queue
        // Show all inquirers, ask user to choose
        System.out.println("Choose the inquirer who is interested in this missing person.\nEnter the number of the inquirer you wish to view logs for: ");

        HashMap<Integer, ReliefService> reliefMap = hashMapHashSet(storedServices);
        printReliefServices(reliefMap);
        ReliefService chosenService = getChoiceFromHashMap(reliefMap, scanner);


        // Add the missing person to the service
        chosenService.addMissingPerson(missingPerson);
    }


    public void exportDB() {
        // TODO: Fix this method
        try {
            // Connect to the database
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);

            // Start a transaction
            dbConnect.setAutoCommit(false);

            // Clear the inquirer and inquiry_log tables
            try (PreparedStatement stmt1 = dbConnect.prepareStatement("DELETE FROM inquirer;")) {
                stmt1.executeUpdate();
            }

            try (PreparedStatement stmt1 = dbConnect.prepareStatement("DELETE FROM inquiry_log;")) {
                stmt1.executeUpdate();
            }

            // Add the storedServices to the database
            for (ReliefService reliefService : storedServices) {
                Inquirer inquirer = reliefService.getInquirer();
                if (inquirer != null) {
                    // Insert the inquirer
                    try (PreparedStatement stmt2 = dbConnect.prepareStatement("INSERT INTO inquirer (firstname, lastname, phonenumber) VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS)) {
                        stmt2.setString(1, inquirer.getFirstName());
                        stmt2.setString(2, inquirer.getLastName());
                        stmt2.setString(3, inquirer.getServicesPhone());
                        stmt2.executeUpdate();

                        // Get the inquirer's ID
                        try (ResultSet generatedKeys = stmt2.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                int inquirerID = generatedKeys.getInt(1);

                                // Insert the inquiry logs
                                for (InquiryLog log : reliefService.getLogs()) {
                                    try (PreparedStatement stmt4 = dbConnect.prepareStatement("INSERT INTO inquiry_log (calldate, details, inquirer) VALUES (?, ?, ?);")) {
                                        stmt4.setString(1, log.getDateOfInquiry());
                                        stmt4.setString(2, log.getInfoProvided());
                                        stmt4.setInt(3, inquirerID);
                                        stmt4.executeUpdate();
                                    }
                                }
                            } else {
                                throw new SQLException("Creating inquirer failed, no ID obtained.");
                            }
                        }
                    }
                }
            }

            // Commit the transaction
            dbConnect.commit();

        } catch (SQLException e) {
            // Rollback the transaction in case of an error
            if (dbConnect != null) {
                try {
                    dbConnect.rollback();
                } catch (SQLException ex) {
                    System.out.println("Error rolling back the transaction.\n" + ex.getMessage());
                }
            }
            System.out.println("Error connecting to the database.\n" + e.getMessage());
            System.out.println("Exiting...");
            System.exit(1); // Exit the program with an error code
        } finally {
            // Set auto-commit back to true
            if (dbConnect != null) {
                try {
                    dbConnect.setAutoCommit(true);
                } catch (SQLException ex) {
                    System.out.println("Error setting auto-commit to true.\n" + ex.getMessage());
                }
            }
        }
    }
}