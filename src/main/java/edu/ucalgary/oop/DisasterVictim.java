package edu.ucalgary.oop;

import java.util.*;

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

    // TODO: Replace printStackTrace methods with more 'user-friendly messages' as per her requirements

    // TODO: Consider line endings \n vs \r\n for text I/O

    // TODO: Modify throwing of exceptions so the users don't see unadulterated execption text.

    // TODO: Implement functionality to automatically generate ASSIGNED_ID and ENTRY_DATE

    public DisasterVictim(String ENTRY_DATE) throws IllegalArgumentException {
        this.ASSIGNED_SOCIAL_ID = ++counter;
        if (ApplicationUtils.isValidPastDate(ENTRY_DATE)) this.ENTRY_DATE = ApplicationUtils.parseDate(ENTRY_DATE);
        else throw new IllegalArgumentException("Invalid date format or future entry day provided");
    }

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
        if (ApplicationUtils.isValidPastDate(ENTRY_DATE)) this.ENTRY_DATE = ApplicationUtils.parseDate(ENTRY_DATE);
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
            if (ApplicationUtils.isValidPastDate(dateOfBirth))
                this.dateOfBirth = ApplicationUtils.parseDate(dateOfBirth);
            else throw new IllegalArgumentException("Invalid date format or future entry date provided.");
        }
    }

    /**
     * Sets approximate age if dateOfBirth isn't set yet
     *
     * @param approximateAge the approximate age to set
     */
    public void setApproximateAge(int approximateAge) throws IllegalStateException, IllegalArgumentException {
        // Checking if `dateOfBirth` is set
        if (this.dateOfBirth != null) {
            throw new IllegalStateException("The date of birth has already been set");
        } else {
            // TODO: Validate possible approximate ages
            if (isValidApproximateAge(approximateAge)) this.approximateAge = approximateAge;
            else throw new IllegalArgumentException("Invalid approximate age provided");
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
        if (ApplicationUtils.isValidGender(gender)) this.gender = gender.toLowerCase();
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
     * Adds a family connection to the victim and fixes any inconsistencies
     *
     * @param familyConnection the family connection to add
     */
    public void addFamilyConnection(FamilyRelation familyConnection) throws IllegalArgumentException {
        // Checking for self-connection
        if (familyConnection.getPersonOne().equals(familyConnection.getPersonTwo())) {
            throw new IllegalArgumentException("Invalid family connection provided: Self relation not allowed");
        }

        // Checking if the connection already exists
        Optional<FamilyRelation> existingConnection = familyConnections.stream().filter((connection) -> (
                connection.getPersonOne().equals(familyConnection.getPersonOne()) && connection.getPersonTwo().equals(familyConnection.getPersonTwo())
        )).findFirst();
        if (existingConnection.isPresent()) {
            return;
        }

        // Checking if the connection is a sibling
        if (familyConnection.getRelationshipTo().equals("sibling")) {
            // Checking if the connection already exists
            Optional<FamilyRelation> siblingConnection = familyConnections.stream().filter((connection) -> (
                    connection.getPersonOne().equals(familyConnection.getPersonTwo()) && connection.getPersonTwo().equals(familyConnection.getPersonOne())
            )).findFirst();
            if (siblingConnection.isPresent()) {
                return;
            }

            familyConnections.add(familyConnection);
            familyConnection.recursiveSiblingAdder(new HashSet<>());

        } else if (familyConnection.getRelationshipTo().equals("parent")) { // Checking if the connection is a parent

        } else if (familyConnection.getRelationshipTo().equals("child")) {

        } else if (familyConnection.getRelationshipTo().equals("spouse")) {

        } else {
            throw new IllegalArgumentException("Invalid family connection provided: Invalid relationship type");
        }

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

    /**
     * Removes a family connection from the victim and fixes any inconsistencies
     *
     * @param familyConnection the family connection to remove
     */
    public void removeFamilyConnection(FamilyRelation familyConnection) {

        // If siblings are disconnected, it cascades effectively everywhere
        // Getting all siblings
        HashSet<DisasterVictim> siblings = familyConnection.recursiveRemoverGlance(new HashSet<>());

        // Removing "siblings" relationships
        siblings.forEach((sibling) -> {
            sibling.getFamilyConnections().forEach((connection) -> {
                if (connection.getRelationshipTo().equals("sibling")) {
                    sibling.removeFamilyConnection(connection, false);
                }
            });
        });
    }

    /**
     * Removes a family connection from the victim
     *
     * @param familyConnection the family connection to remove
     * @param withGlance       whether to recurse through the family connection to fix any inconsistencies
     */
    public void removeFamilyConnection(FamilyRelation familyConnection, boolean withGlance) {
        familyConnections.remove(familyConnection);
        if (withGlance) familyConnection.recursiveRemoverGlance(new HashSet<>());
    }

    /**
     * Adds a dietary restriction to the victim
     *
     * @param dietaryRestriction the dietary restriction to add
     */
    public void addDietaryRestriction(DietaryRestriction dietaryRestriction) {
        dietaryRestrictions.add(dietaryRestriction);
    }

    /**
     * Removes a dietary restriction from the victim
     *
     * @param dietaryRestriction the dietary restriction to remove
     */
    public void removeDietaryRestriction(DietaryRestriction dietaryRestriction) {
        dietaryRestrictions.remove(dietaryRestriction);
    }

    /**
     * Adds a medical record to the victim
     *
     * @param medicalRecord the medical record to add
     */
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        // TODO: make sure all dates are set with the same freaking delimiter to make sure this code works
        if (medicalRecords.contains(medicalRecord)) {
            return;
        }
        medicalRecords.add(medicalRecord);
    }

    public void removeMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecords.remove(medicalRecord);
    }

    // REQ 3: Supply Consistency

    public void addPersonalBelonging(Supply supply) {
        // If a victim gets a supply, decrease stock in source
        supply.getSource().removeSupply(supply);

        Optional<Supply> existingSupply = personalBelongings.stream().filter((item) -> item.getType().equals(supply.getType())).findFirst();

        if (existingSupply.isPresent()) {
            Supply targetSupply = existingSupply.get();
            int oldQuantity = targetSupply.getQuantity();
            int newQuantity = supply.getQuantity();
            targetSupply.setQuantity(oldQuantity + newQuantity);
        } else {
            personalBelongings.add(supply);
        }
    }

    public void removePersonalBelonging(Supply supply) {
        Optional<Supply> existingSupply = personalBelongings.stream().filter(item -> item.getType().equals(supply.getType())).findFirst();

        if (existingSupply.isPresent()) {
            Supply targetSupply = existingSupply.get();
            int oldQuantity = targetSupply.getQuantity();
            int newQuantity = supply.getQuantity();

            if (newQuantity >= oldQuantity) {
                // Remove supplies entirely
                personalBelongings.remove(supply);
            } else {
                targetSupply.setQuantity(oldQuantity - newQuantity);
            }
        }
    }

    /*------------------Interface Methods--------------------*/

    // REQ 6: Implementing VictimEntryInterface's functions

    @Override
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
        this.enterFamilyConnectionsInfo();
    }

    @Override
    public void enterFamilyConnectionsInfo() {
        Scanner scanner = new Scanner(System.in);
        // Relationships HashMap
        HashMap<Integer, String> relationshipMap = new HashMap<>();
        relationshipMap.put(1, "sibling");
        relationshipMap.put(2, "parent");
        relationshipMap.put(3, "child");
        relationshipMap.put(4, "spouse");

        // Figure out the DisasterVictim that `this` is related to
        System.out.println("Please enter info relevant to the disaster victim relative");
        // Function that returns DisasterVictim object
//        DisasterVictim relative = searchDisasterVictim();

        System.out.println("We support four types of relationships. Choose a relationship type.\n1. 'sibling'\n2. 'parent'\n3. 'child'\n4. 'spouse'\n\nEnter a number: ");
        String relationType = relationshipMap.get(Integer.parseInt(scanner.nextLine()));

        // Add connection
//        this.addFamilyConnection(new FamilyRelation(this, relationType, relative));


    }

}