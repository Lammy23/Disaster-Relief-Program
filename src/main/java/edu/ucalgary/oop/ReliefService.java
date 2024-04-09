package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.Scanner;

public class ReliefService implements InquirerQueryEntryInterface {
    private Inquirer inquirer;
    private DisasterVictim missingPerson;
    private String dateOfInquiry;
    private String infoProvided;
    private Location lastKnownLocation;
    private static ArrayList<String> logs;             //REQ something: cool though

    /*---------------Constructor---------------*/

    public ReliefService(Inquirer inquirer, DisasterVictim missingPerson, String dateOfInquiry, String infoProvided, Location lastKnownLocation) throws IllegalArgumentException {
        this.inquirer = inquirer;
        this.missingPerson = missingPerson;
        if (ApplicationUtils.isValidPastDate(dateOfInquiry)) this.dateOfInquiry = ApplicationUtils.parseDate(dateOfInquiry);
        else throw new IllegalArgumentException("Invalid date format or future entry day provided");
        this.infoProvided = infoProvided;
        this.lastKnownLocation = lastKnownLocation;
    }

    /*---------------Getters---------------*/

    public Inquirer getInquirer() {
        return inquirer;
    }

    public DisasterVictim getMissingPerson() {
        return missingPerson;
    }

    public String getDateOfInquiry() {
        return dateOfInquiry;
    }

    public String getInfoProvided() {
        return infoProvided;
    }

    public Location getLastKnownLocation() {
        return lastKnownLocation;
    }

    /*---------------Setters---------------*/

    public void setInquirer(Inquirer inquirer) {
        this.inquirer = inquirer;
    }

    public void setMissingPerson(DisasterVictim missingPerson) {
        this.missingPerson = missingPerson;
    }

    public void setDateOfInquiry(String dateOfInquiry) throws IllegalArgumentException {
        if (ApplicationUtils.isValidPastDate(dateOfInquiry)) this.dateOfInquiry = ApplicationUtils.parseDate(dateOfInquiry);
        else throw new IllegalArgumentException("Invalid date format or future entry day provided");
    }

    public void setInfoProvided(String infoProvided) {
        this.infoProvided = infoProvided;
    }

    public void setLastKnownLocation(Location lastKnownLocation) {
        this.lastKnownLocation = lastKnownLocation;
    }

    /*---------------Interface Methods---------------*/

    // REQ 7: Implementing QueriesAndVictimsInterface's functions


    @Override
    public void logInquirerQuery() {
        // We get inquirers from the database.
        // We create an inquirer object, a reliefService object and fill them in appropriately
        // We generate a log, i.e. all the info .toString()
        // We store the log somewhere ?? // TODO: Figure the point out
    }

    @Override
    public DisasterVictim searchDisasterVictim() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the first name and last name of the victim. If you don't know the full first name or last name, you can type it in partially. We will" +
                "automatically search it up for you\nEnter here: ");
        String keyWord = scanner.nextLine();

        // Check if user is location based or from HQ
        if (MainApplication.workerType.equals("central")) {
            // Search inquirer objects

        }
        return new DisasterVictim("null", "null");
    }


}
