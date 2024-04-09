package edu.ucalgary.oop;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
- location: Location
- treatmentDetails: String
- dateOfTreatment: String

+ MedicalRecord(location: Location, treatmentDetails:
string, dateOfTreatment: String)

+ parseDate(date: String, for: String): String

- isValidDate(date: String): boolean
- parseDate(date: String): String

+ getLocation(): Location
+ getTreatmentDetails(): String
+ getDateOfTreatment(): String

+ setTreatmentDetails(treatmentDetails: String): void
+ setLocation(location: Location): void
+ setDateOfTreatment(dateOfTreatment: String): void
*/

/**
 * Class that represents a `DisasterVictim`'s medical record
 */
public class MedicalRecord {
    private Location location;
    private String treatmentDetails;
    private String dateOfTreatment;

    /*---------------Constructor---------------*/

    /**
     * Constructor for `MedicalRecord`
     *
     * @param location         the location of the treatment
     * @param treatmentDetails the details of the treatment
     * @param dateOfTreatment  the date of the treatment
     */
    public MedicalRecord(Location location, String treatmentDetails, String dateOfTreatment) throws IllegalArgumentException {
        this.location = location;
        this.treatmentDetails = treatmentDetails;
        if (ApplicationUtils.isValidDate(dateOfTreatment)) this.dateOfTreatment = ApplicationUtils.parseDate(dateOfTreatment);
        else throw new IllegalArgumentException("Invalid date format provided");
    }

    /*---------------Getters---------------*/

    /**
     * Gets the location of the treatment
     * @return the location of the treatment
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Gets the details of the treatment
     * @return the details of the treatment
     */
    public String getTreatmentDetails() {
        return treatmentDetails;
    }

    /**
     * Gets the date of the treatment
     * @return the date of the treatment
     */
    public String getDateOfTreatment() {
        return dateOfTreatment;
    }

    /*---------------Setters---------------*/

    /**
     * Sets the location of the treatment
     * @param location the location of the treatment
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Sets the details of the treatment
     * @param treatmentDetails the details of the treatment
     */
    public void setTreatmentDetails(String treatmentDetails) {
        this.treatmentDetails = treatmentDetails;
    }

    /**
     * Sets the date of the treatment if date is valid
     * @param dateOfTreatment the date of the treatment
     */
    public void setDateOfTreatment(String dateOfTreatment) throws IllegalArgumentException {
        if (ApplicationUtils.isValidDate(dateOfTreatment)) this.dateOfTreatment = ApplicationUtils.parseDate(dateOfTreatment);
        else throw new IllegalArgumentException("Invalid date format provided");
    }
}
