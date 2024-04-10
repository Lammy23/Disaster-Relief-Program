package edu.ucalgary.oop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import static edu.ucalgary.oop.ApplicationUtils.*;

/**
 * This class is responsible for handling the command line interface for the inquirer queries
 *
 * @author Olamikun Aluko
 * <a href="mailto:lammyaluko@gmail.com">Email me</a> for any comments
 * @version 1.1
 * @since 1.0
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
            System.out.println("Press any key to continue...");
            scanner.nextLine();
            System.exit(1);                                                     // Exit the program with an error code
        }
    }

    public void createDB(String userPassword) {
        try {
            // Connect to the general database
            dbConnect = DriverManager.getConnection(MAINURL, "postgres", userPassword);

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
            System.out.println("Error creating the database.\n" + e.getMessage());
            System.out.println("Exiting...");
            System.out.println("Press any key to continue...");
            scanner.nextLine();
            System.exit(1);                                                     // Exit the program with an error code
        } catch (IOException e) {
            System.out.println("Error reading the SQL file.\n" + e.getMessage());
            System.out.println("Exiting...");
            System.out.println("Press any key to continue...");
            scanner.nextLine();
            System.exit(1);                                                     // Exit the program with an error code
        }
    }

    public void connectDB() {
        try {
            // Connect to the target database
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error connecting to the database.\n" + e.getMessage());
            System.out.println("Exiting...");
            System.out.println("Press any key to continue...");
            scanner.nextLine();
            System.exit(1);                                                     // Exit the program with an error code
        }
    }

    /**
     * This method is responsible for importing the storedReliefServices from the database
     */
    public void importDB() {
        // Registration

        String userPassword;
        System.out.println("Enter the password for your admin database: ");
        userPassword = scanner.nextLine();


        System.out.println("Do you want to create a new database?\nThis will have the default values from the SQL file. (yes/no)");

        String choice;
        while (true) {
            choice = scanner.nextLine();
            if (choice.equals("yes") || choice.equals("no")) {
                break;
            }
            System.out.println("Invalid input provided. Please enter 'yes' or 'no'.");
        }

        // If the user wants to create a new database
        if (choice.equals("yes")) {
            createDB(userPassword);
        } else {
            connectDB();
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
            System.out.println("Press any key to continue...");
            scanner.nextLine();
            System.exit(1);                                                     // Exit the program with an error code
        } catch (Exception e) {
            System.out.println("Error creating inquirers object.\n" + e.getMessage());
            System.out.println("Exiting...");
            System.out.println("Press any key to continue...");
            scanner.nextLine();
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
            System.out.println("Press any key to continue...");
            scanner.nextLine();
            System.exit(1);                                                     // Exit the program with an error code
        } catch (Exception e) {
            System.out.println("Error creating inquirers object.\n" + e.getMessage());
            System.out.println("Exiting...");
            System.out.println("Press any key to continue...");
            scanner.nextLine();
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
                System.out.println("Press any key to continue...");
                scanner.nextLine();
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
        System.out.println("Press any key to continue...");
        scanner.nextLine();
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
        System.out.println("Press any key to continue...");
        scanner.nextLine();
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
        System.out.println("Press any key to continue...");
        scanner.nextLine();
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
        System.out.println("Press any key to continue...");
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
        System.out.println("Press any key to continue...");
        scanner.nextLine();
    }


    public void exportDB() {
        System.out.println("Do you want to save your changes to the database? (yes/no)");

        String choice;
        while (true) {
            choice = scanner.nextLine();
            if (choice.equals("yes") || choice.equals("no")) {
                if (choice.equals("no")) {
                    return;
                }
                break;
            }
            System.out.println("Invalid input provided. Please enter 'yes' or 'no'.");
        }


        try {

            for (ReliefService service : storedServices) {

                // Ensure that ReliefService is not already in the database
                String checkService = "SELECT * FROM inquirer WHERE firstName = ? OR lastName = ?";

                try (PreparedStatement pstmt = dbConnect.prepareStatement(checkService)) {
                    pstmt.setString(1, service.getInquirer().getFirstName());
                    pstmt.setString(2, service.getInquirer().getLastName());
                    results = pstmt.executeQuery();
                    if (results.next()) {
                        continue;
                    }
                } catch (SQLException ex) {
                    System.out.println("Error checking if the ReliefService is already in the database.\n" + ex.getMessage());
                }

                // Get MAX(id)
                String getMaxID = "SELECT MAX(id) FROM inquirer";
                int inquirerID = 0;

                try (PreparedStatement pstmt = dbConnect.prepareStatement(getMaxID)) {
                    results = pstmt.executeQuery();
                    if (results.next()) {
                        inquirerID = results.getInt(1) + 1;
                    }
                } catch (SQLException ex) {
                    System.out.println("Error getting the MAX(id) from the inquirer table.\n" + ex.getMessage());
                }

                // Insert into INQUIRER table
                String sql = "INSERT INTO INQUIRER (id, firstName, lastName, phoneNumber) VALUES (?, ?, ?, ?)";
                try (PreparedStatement pstmt = dbConnect.prepareStatement(sql)) {

                    pstmt.setInt(1, inquirerID);
                    pstmt.setString(2, service.getInquirer().getFirstName());
                    pstmt.setString(3, service.getInquirer().getLastName());
                    pstmt.setString(4, service.getInquirer().getServicesPhone());

                    pstmt.executeUpdate();
                }

                // Insert into INQUIRY_LOG table
                for (InquiryLog log : service.getLogs()) {

                    // Ensure that InquiryLog is not already in the database
                    String checkLog = "SELECT * FROM INQUIRY_LOG WHERE callDate = ? AND details = ?";

                    try (PreparedStatement pstmt = dbConnect.prepareStatement(checkLog)) {
                        pstmt.setDate(1, Date.valueOf(log.getDateOfInquiry()));
                        pstmt.setString(2, log.getInfoProvided());
                        results = pstmt.executeQuery();
                        if (results.next()) {
                            continue;
                        }
                    } catch (SQLException ex) {
                        System.out.println("Error checking if the InquiryLog is already in the database.\n" + ex.getMessage());
                    }

                    // Get MAX(id)
                    String getMaxLogID = "SELECT MAX(id) FROM inquiry_log";
                    int logID = 0;

                    try (PreparedStatement pstmt = dbConnect.prepareStatement(getMaxLogID)) {
                        results = pstmt.executeQuery();
                        if (results.next()) {
                            logID = results.getInt(1) + 1;
                        }
                    } catch (SQLException ex) {
                        System.out.println("Error getting the MAX(id) from the inquiry_log table.\n" + ex.getMessage());
                    }

                    // Insert into INQUIRY_LOG table
                    sql = "INSERT INTO INQUIRY_LOG (id, inquirer, callDate, details) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement pstmt = dbConnect.prepareStatement(sql)) {
                        pstmt.setInt(1, logID);
                        pstmt.setInt(2, inquirerID);
                        pstmt.setDate(3, Date.valueOf(log.getDateOfInquiry()));
                        pstmt.setString(4, log.getInfoProvided());
                        pstmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error exporting the storedInquirers to the database.\n" + ex.getMessage());
            System.out.println("Press any key to continue...");
            scanner.nextLine();
        }

        close();

        // Add closing message
        System.out.println("Inquirers saved to database successfully!");
        System.out.println("Press any key to continue...");
        scanner.nextLine();
    }

}