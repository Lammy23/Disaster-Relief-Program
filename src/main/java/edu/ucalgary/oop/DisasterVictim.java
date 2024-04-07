package edu.ucalgary.oop;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.HashSet;

/* TODO: update this UML representation

## Attributes ##

- firstName: String
- lastName: String
- dateOfBirth: String
- approximateAge: int
- comments: String
- familyConnections: HashSet<FamilyRelation>
- gender: String
- dietaryRestrictions: HashSet<DietaryRestriction>
- ASSIGNED_SOCIAL_ID: int
- medicalRecords: ArrayList<MedicalRecord>
- ENTRY_DATE: String
- personalBelongings: ArrayList<Supply>0
- __counter__: int

## Methods ##

- isValidAge(age: int): Boolean
- isValidPastDate(date: String): Boolean
- parseDate(date: String): String

+ DisasterVictim(firstName: String, ENTRY_DATE: String)

+ getFirstName(): String
+ getLastName(): String
+ getDateOfBirth(): String
+ getApproximateAge(): int
+ getComments(): String
+ getFamilyConnections: HashSet<FamilyRelation>
+ getGender(): String
+ getDietaryRestrictions(): HashSet<DietaryRestriction>
+ getAssignedSocialID(): int
+ getMedicalRecords(): ArrayList<MedicalRecord>
+ getEntryDate(): String
+ getPersonalBelongings: ArrayList<Supply>

+ setFirstName(FirstName: String): void
+ setLastName(lastName: String): void
+ setDateOfBirth(dateOfBirth: String): void
+ setApproximateAge(approximateAge: int): void
+ setComments(comments: String): void
+ setFamilyConnections(familyConnections: HashSet<FamilyRelation>): void
+ setGender(gender: String): void
+ setDietaryRestrictions(dietaryRestrictions: HashSet<DietaryRestriction>): void
+ setMedicalRecords(medicalRecords: ArrayList<MedicalRecord>): void
+ setPersonalBelongings(supplies: ArrayList<Supply>)

+ addFamilyConnection(familyConnection: FamilyRelation): void
+ removeFamilyConnection(familyConnection: FamilyRelation): void
+ addDietaryRestriction(dietaryRestriction: DietaryRestriction): void
+ removeDietaryRestriction(dietaryRestriction: DietaryRestriction): void
+ addMedicalRecord(medicalRecord:MedicalRecord): void
+ removeMedicalRecord(medicalRecord: MedicalRecord): void
+ addPersonalBelonging(supply: Supply): void
+ removePersonalBelonging(supply: Supply): void
 */

/**
 * Class that represents a victim of a disaster
 */
public class DisasterVictim {                                           // TODO: extend `Person` class
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private int approximateAge;
    private String comments;
    private HashSet<FamilyRelation> familyConnections;
    private String gender;
    private HashSet<DietaryRestriction> dietaryRestrictions;
    private final int ASSIGNED_SOCIAL_ID;                               // Social ID is assigned once and never changed
    private ArrayList<MedicalRecord> medicalRecords;                    // It may be useful to know what the earlier entries are, so we use ArrayList
    private final String ENTRY_DATE;                                    // A victim can only have one date of entry
    private ArrayList<Supply> personalBelongings;                       // It may be useful to know the earlier supplies received, so we use ArrayList
    private static int counter;

    /**
     * Checks if approximateAge is valid and returns a boolean
     *
     * @param approximateAge The age to validate
     * @return `true` if age is valid else `false`
     */
    private boolean isValidApproximateAge(int approximateAge) {

        // If age is within zero and one-hundred and fifty, return `true`
        return (approximateAge >= 0 && approximateAge < 150);
    }

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
     * Checks that the birthdate is valid and returns a boolean
     *
     * @param date the date to check
     * @return `true` if date is valid else return `false`
     */
    private boolean isValidPastDate(String date) {
        if (isValidDate(date)) {
            // Parsing date in standard format
            String standardizedDate = parseDate(date);

            // Defining the date format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Parsing the date
            LocalDate parsedDate = LocalDate.parse(standardizedDate, formatter);

            // Getting current date
            LocalDate currentDate = LocalDate.now();

            // If parsedDate is on or before currentDate, return true
            return (!parsedDate.isAfter(currentDate));
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

    // TODO: Export `checkDate` and `checkBirthDate` functions if necessary

    // TODO: Implement functionality to automatically generate ASSIGNED_ID and ENTRY_DATE

    /**
     * Constructor for `DisasterVictim`
     *
     * @param firstName  the first name of the victim
     * @param ENTRY_DATE the date person became a victim
     * @throws IllegalArgumentException If `ENTRY_DATE` is invalid
     */
    public DisasterVictim(String firstName, String ENTRY_DATE) throws IllegalArgumentException {
        this.firstName = firstName;
        this.ASSIGNED_SOCIAL_ID = ++counter;
        if (isValidPastDate(ENTRY_DATE)) this.ENTRY_DATE = ENTRY_DATE;
        else throw new IllegalArgumentException("Invalid date format or future entry date provided.");
    }

    /*-----------------Getters-----------------*/

    /**
     * Gets the first name of the victim
     *
     * @return the first name of the victim
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the victim
     *
     * @return the last name of the victim
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the date of birth of the victim
     *
     * @return the date of birth of the victim
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Gets the approximate age of the victim
     *
     * @return the approximate age of the victim
     */
    public int getApproximateAge() {
        return approximateAge;
    }

    /**
     * Gets the comments of the victim
     *
     * @return the comments of the victim
     */
    public String getComments() {
        return comments;
    }

    /**
     * Gets the family connections of the victim
     *
     * @return the family connections of the victim
     */
    public HashSet<FamilyRelation> getFamilyConnections() {
        return familyConnections;
    }

    /**
     * Gets the gender of the victim
     *
     * @return the gender of the victim
     */
    public String getGender() {
        return gender;
    }

    /**
     * Gets the dietary restrictions of the victim
     *
     * @return the dietary restrictions of the victim
     */
    public HashSet<DietaryRestriction> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    /**
     * Gets the assigned social ID of the victim
     *
     * @return the assigned social ID of the victim
     */
    public int getAssignedSocialID() {
        return ASSIGNED_SOCIAL_ID;
    }

    /**
     * Gets the medical records of the victim
     *
     * @return the medical records of the victim
     */
    public ArrayList<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    /**
     * Gets the entry date of the victim
     *
     * @return the entry date of the victim
     */
    public String getEntryDate() {
        return ENTRY_DATE;
    }

    /**
     * Gets the personal belongings of the victim
     *
     * @return the personal belongings of the victim
     */
    public ArrayList<Supply> getPersonalBelongings() {
        return personalBelongings;
    }

    /*-----------------Setters-----------------*/

    /**
     * Sets the first name of the victim
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the last name of the victim
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets date of birth if approximateAge isn't set yet
     *
     * @param dateOfBirth the date of birth to set
     */
    public void setDateOfBirth(String dateOfBirth) throws IllegalStateException {
        // TODO: add functionality to remove approximateAge and add dateOfBirth instead (if possible)
        // Checking if `approximateAge` has been set.
        if (this.approximateAge != 0) {
            throw new IllegalStateException("The approximate age has already been set.");
        } else {
            this.dateOfBirth = dateOfBirth;
        }
    }

    /**
     * Sets approximate age if dateOfBirth isn't set yet
     *
     * @param approximateAge the approximate age to set
     */
    public void setApproximateAge(int approximateAge) throws IllegalStateException {
        // Checking if `dateOfBirth` is set
        if (this.dateOfBirth != null) {
            throw new IllegalStateException("The date of birth has already been set");
        } else {
            this.approximateAge = approximateAge;
        }
    }

    /**
     * Sets the comments of the victim
     *
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Sets the family connections of the victim
     *
     * @param familyConnections the family connections to set
     */
    public void setFamilyConnections(HashSet<FamilyRelation> familyConnections) {
        this.familyConnections = familyConnections;
    }

    /**
     * Sets the gender of the victim
     *
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;                           // TODO: Implement error checking - check whether gender is valid according to the gender.txt file
    }

    /**
     * Sets the dietary restrictions of the victim
     *
     * @param dietaryRestrictions the dietary restrictions to set
     */
    public void setDietaryRestrictions(HashSet<DietaryRestriction> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    /**
     * Sets the medical records of the victim
     *
     * @param medicalRecords the medical records to set
     */
    public void setMedicalRecords(ArrayList<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    /**
     * Sets the personal belongings of the victim
     *
     * @param supplies the personal belongings to set
     */
    public void setPersonalBelongings(ArrayList<Supply> supplies) {
        this.personalBelongings = supplies;
    }

    /*-----------------Adders/Removers-----------------*/

    /**
     * Adds a family connection to the victim
     *
     * @param familyConnection the family connection to add
     */
    public void addFamilyConnection(FamilyRelation familyConnection) {
        // TODO: Implement function
    }

    // more...
}