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

    /**
     * Checks if date is valid and returns a boolean
     *
     * @param date The date to validate
     * @return `true` if the date is valid else `false`
     */
    private boolean isValidDate(String date) {
        // Checking that date resembles YYYY-MM-DD, YYYY/MM/DD, YYYY.MM.DD
        String regex = "\\d{4}[.-/]\\d{2}[.-/]\\d{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        boolean matches = matcher.matches();

        if (matches) {
            try {
                // Checking that the dates are not impossible
                // Parsing date in standard format
                String standardizedDate = parseDate(date);

                // Defining the date format
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                // Parsing the date
                LocalDate parsedDate = LocalDate.parse(standardizedDate, formatter);

                // If the date was successfully parsed, then it's valid
                return true;
            } catch (DateTimeParseException e) {
                // If the date couldn't be parsed, then it's not valid
                return false;
            }
        }
        return false;
    }

    /**
     * Parses valid date into a standard format YYYY-MM-DD
     *
     * @param date The date to parse
     * @return The date in the standard format
     */
    private String parseDate(String date) {
        // Replacing any non-digit characters with a dash
        // TODO: Confirm that \\D is the right symbol
        return date.replaceAll("\\D", "-");
    }

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
        if (isValidDate(dateOfTreatment)) this.dateOfTreatment = dateOfTreatment;
        else throw new IllegalArgumentException("Invalid date format provided");
    }

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
        if (isValidDate(dateOfTreatment)) this.dateOfTreatment = dateOfTreatment;
        else throw new IllegalArgumentException("Invalid date format provided");
    }
}
