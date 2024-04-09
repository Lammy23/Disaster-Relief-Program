package edu.ucalgary.oop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class DisasterVictim implements VictimEntryInterface {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private Integer approximateAge;
    private String comments;
    private HashSet<FamilyRelation> familyConnections = new HashSet<>();
    private String gender;
    private HashSet<DietaryRestriction> dietaryRestrictions = new HashSet<>();            // A victim may have more than one dietary restriction
    private final Integer ASSIGNED_SOCIAL_ID;                               // Social ID is assigned once and never changed
    private ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();                    // It may be useful to know what the earlier entries are, so we use ArrayList
    private final String ENTRY_DATE;                                    // A victim can only have one date of entry
    private ArrayList<Supply> personalBelongings = new ArrayList<>();                       // It may be useful to know the earlier supplies received, so we use ArrayList
    private static Integer counter;

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

    /**
     * Checks if gender is valid then returns a boolean
     *
     * @param gender the gender to check
     * @return `true` if gender is valid, else returns `false`
     */
    private boolean isValidGender(String gender) {
        // Check GenderOptions.txt to see if the gender exists
        BufferedReader inputStream = null;
        boolean genderFound = false;

        try {
            inputStream = new BufferedReader(new FileReader("./src/main/java/edu/ucalgary/oop/GenderOptions.txt"));
            String line;
            // Reading the file line by line
            while ((line = inputStream.readLine()) != null) {
                // Accounting for case
                if (line.toLowerCase().equals(gender.toLowerCase())) {
                    genderFound = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return genderFound;
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
        if (this.approximateAge != null) {
            throw new IllegalStateException("The approximate age has already been set.");
        } else {
            if (isValidPastDate(dateOfBirth)) this.dateOfBirth = dateOfBirth;
            else throw new IllegalArgumentException("Invalid date format or future entry date provided.");
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
            // TODO: Validate possible approximate ages
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
        if (isValidGender(gender)) this.gender = gender.toLowerCase();
        else throw new IllegalArgumentException("Invalid gender provided");
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
    public void addFamilyConnection(FamilyRelation familyConnection) throws IllegalArgumentException {
        Optional<FamilyRelation> existingConnection = familyConnections.stream().filter((connection) -> {
            boolean isPresent;
            return (connection.getPersonOne().equals(familyConnection.getPersonOne()) && connection.getPersonTwo().equals(familyConnection.getPersonTwo())) || (connection.getPersonOne().equals(familyConnection.getPersonTwo()) && connection.getPersonTwo().equals(familyConnection.getPersonOne()));
        }).findFirst();

        if (existingConnection.isPresent()) {
            return;
        }

        // checking for self-connection
        if (familyConnection.getPersonOne().equals(familyConnection.getPersonTwo())) {
            throw new IllegalArgumentException("Invalid family connection provided: Self relation not allowed");
        }


        familyConnections.add(familyConnection);
        familyConnection.recursiveAdderGlance(new HashSet<>());
    }

    /**
     * Adds a family connection to the victim
     *
     * @param familyConnection the family connection to add
     * @param withGlance       whether to recurse through the family connection to fix any inconsistencies
     */
    public void addFamilyConnection(FamilyRelation familyConnection, boolean withGlance) {
        familyConnections.add(familyConnection);
        if (withGlance) familyConnection.recursiveAdderGlance(new HashSet<>());
    }

    public void removeFamilyConnection(FamilyRelation familyConnection) {
        familyConnections.remove(familyConnection);
        familyConnection.recursiveRemoverGlance(new HashSet<>());
    }

    public void removeFamilyConnection(FamilyRelation familyConnection, boolean withGlance) {
        familyConnections.remove(familyConnection);
        if (withGlance) familyConnection.recursiveRemoverGlance(new HashSet<>());
    }

    public void addMedicalRecord(MedicalRecord medicalRecord) {
        // TODO: Implement function
    }

    public void removeMedicalRecord(MedicalRecord medicalRecord) {
        // TODO: Implement function
    }

    public void addPersonalBelonging(Supply supply) {
        // TODO: Implement function
    }

    public void removePersonalBelonging(Supply supply) {
        // TODO: Implement function
    }

    /*------------------Interface Methods--------------------*/

    // REQ 6: Implementing InterfaceDisasterVictim's functions

    public void enterDisasterVictimInfo() {

        // TODO: Implement optional entries

        // Instantiate common keywords in HashMap
        HashMap<String, Boolean> cliKeywords = new HashMap<>();
        cliKeywords.put("yes", true);
        cliKeywords.put("y", true);
        cliKeywords.put("no", false);
        cliKeywords.put("n", false);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Disaster Victim Information");

        // Collecting user data
        System.out.println("Enter first name: ");
        this.setFirstName(scanner.nextLine());

        System.out.println("Enter last name: ");
        this.setLastName(scanner.nextLine());

        // Enter either approximate age or date of birth
        System.out.println("Do you know the victim's date of birth?\nEnter yes/no: ");
        Boolean birthDateKnown = cliKeywords.get(scanner.nextLine().toLowerCase());
        if (birthDateKnown) {
            System.out.println("Enter date of birth.\nValid Formats are: " + "\nYYYY-MM-DD\nYYYY/MM/DD\nYYYY.MM.DD\n\n");
            this.setDateOfBirth(scanner.nextLine());
        } else {
            System.out.println("Enter the approximate age: ");
            this.setApproximateAge(Integer.parseInt(scanner.nextLine()));
        }

        System.out.println("Enter comments: ");
        this.setComments(scanner.nextLine());

        // Creating family connections. Note to self: Java likely passes objects by reference
        this.enterFamilyConnectionsInfo
    }

    public void createFamilyConnectionsInfo() {
        Scanner scanner = new Scanner(System.in);
        // Relationships HashMap
        HashMap<Integer, String> relationshipMap = new HashMap<>();
        relationshipMap.put(1, "sibling");
        relationshipMap.put(2, "parent");
        relationshipMap.put(3, "child");
        relationshipMap.put(4, "spouse");

        // Figure out the DisasterVictim that `this` is related to
        System.out.println("Enter the first name and last name of the relative. If you don't know the full first name or last name, you can type it in partially. We will" +
                "automatically search it up for you\nEnter here: ");
        String keyWord = scanner.nextLine();

        // Function that returns DisasterVictim object


        System.out.println("We support four types of relationships. Choose a relationship type.\n1. 'sibling'\n2. 'parent'\n3. 'child'\n4. 'spouse'\n\nEnter a number: ");
        String relationType = relationshipMap.get(Integer.parseInt(scanner.nextLine()));

        // Add connection
        this.addFamilyConnection(new FamilyRelation(this, relationType, relative));


    }

}