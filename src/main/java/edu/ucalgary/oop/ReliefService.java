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

import java.sql.*;

public class ReliefService implements LogInquirerQueries {
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
    @Override
    public void logInquirerQuery() {

    }

    @Override
    public DisasterVictim searchDisasterVictim(String keyword) {
        // TODO: Implement function
        return new DisasterVictim("null", "null");
    }


}
