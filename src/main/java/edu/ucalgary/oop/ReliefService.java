package edu.ucalgary.oop;

/*

## Attributes ##

- inquirer: Inquirer
- missingPerson: DisasterVictim
- dateOfInquiry: String
- infoProvided: String
- lastKnownLocation: Location

## Methods ##

+ ReliefService(inquirer: Inquirer, missingPerson: DisasterVictim,
dateOfInquiry:String, infoProvided: String , lastKnownLocation: Location )

+ getInquirer():Inquirer
+ getMissingPerson(): DisasterVictim
+ getDateOfInquiry(): String
+ getInfoProvided(): String
+ getLastKnownLocation(): Location
+ getLogDetails(): String

+ setInquirer(inquirer: Inquirer)
+ setMissingPerson(missingPerson: DisasterVictim)
+ setDateOfInquiry(dateOfInquiry: String)
+ setInfoProvided(infoProvided: String)
+ setLastKnownLocation(lastKnownLocation: Location)

 */

import jdk.tools.jmod.Main;

import java.util.Scanner;

public class ReliefService implements QueriesAndVictimsInterface {
    private Inquirer inquirer;
    private DisasterVictim missingPerson;
    private String dateOfInquiry;
    private String infoProvided;
    private Location lastKnownLocation;

    /*---------------Constructor---------------*/

    public ReliefService(Inquirer inquirer, DisasterVictim missingPerson, String dateOfInquiry, String infoProvided, Location lastKnownLocation) {
        this.inquirer = inquirer;
        this.missingPerson = missingPerson;
        this.dateOfInquiry = dateOfInquiry;
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

    public void setDateOfInquiry(String dateOfInquiry) {
        this.dateOfInquiry = dateOfInquiry;
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

    }

    @Override
    public DisasterVictim searchDisasterVictim() {
        // TODO: Implement function
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the first name and last name of the victim. If you don't know the full first name or last name, you can type it in partially. We will" +
                "automatically search it up for you\nEnter here: ");
        String keyWord = scanner.nextLine();

       // Check if user is location based or from HQ
        if (MainApplication.workerType.equals("central")) {
            // Search all locations
            for (Location storedLocation : MainApplication.storedLocations) {
                storedLocation.getOccupants();
            }
        }

    }


}
