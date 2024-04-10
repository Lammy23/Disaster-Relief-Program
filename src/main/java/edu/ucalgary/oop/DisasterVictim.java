package edu.ucalgary.oop;

import java.util.*;

/**
 * Class that represents a victim of a disaster
 */
public class DisasterVictim implements Comparable<DisasterVictim> {
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

    public boolean familyConnectionAlreadyExists(FamilyRelation familyConnection, String type) {
        if (type.equals("sibling")) {
            // Checking if the connection already exists
            Optional<FamilyRelation> siblingConnection = familyConnections.stream().filter((connection) -> (
                    connection.getPersonOne().equals(familyConnection.getPersonTwo()) && connection.getPersonTwo().equals(familyConnection.getPersonOne())
                            || connection.getPersonOne().equals(familyConnection.getPersonOne()) && connection.getPersonTwo().equals(familyConnection.getPersonTwo())
            )).findFirst();
            return siblingConnection.isPresent();

        } else {
            // Checking if the connection already exists
            Optional<FamilyRelation> existingConnection = familyConnections.stream().filter((connection) -> (
                    connection.getPersonOne().equals(familyConnection.getPersonOne()) && connection.getPersonTwo().equals(familyConnection.getPersonTwo())
            )).findFirst();
            return existingConnection.isPresent();
        }
    }

    // TODO: Replace printStackTrace methods with more 'user-friendly messages' as per her requirements

    // TODO: Consider line endings \n vs \r\n for text I/O

    // TODO: Modify throwing of exceptions so the users don't see unadulterated execption text.

    // TODO: Implement functionality to automatically generate ASSIGNED_ID and ENTRY_DATE

    /*-----------------Constructors-----------------*/

    // TODO: What if entry date is not known, then put todays date.

    /**
     * Constructor for `DisasterVictim`
     */
    public DisasterVictim() {
        this.ASSIGNED_SOCIAL_ID = ++counter;
        this.ENTRY_DATE = ApplicationUtils.getCurrentDate();  // Set the entry date to the current date
    }

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
            else throw new IllegalArgumentException("Invalid approximate age provided: Age must be between 0 and 150");
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
        gender = gender.toLowerCase();
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

        // Check if the connection is relevant to the victim
        if (!familyConnection.getPersonOne().equals(this) && !familyConnection.getPersonTwo().equals(this)) {
            throw new IllegalArgumentException("Invalid family connection provided: Connection not relevant to the victim");
        }

        // Checking for self-connection
        if (familyConnection.getPersonOne().equals(familyConnection.getPersonTwo())) {
            throw new IllegalArgumentException("Invalid family connection provided: Self relation not allowed");
        }

        // Checking if the connection already exists
        familyConnectionAlreadyExists(familyConnection, familyConnection.getRelationshipTo());

        switch (familyConnection.getRelationshipTo()) {     // Checking if the connection is a sibling

            case "sibling":
                HashSet<DisasterVictim> siblingsHash = familyConnection.getAllSiblings(new HashSet<>());

                // Convert HashSet to ArrayList
                ArrayList<DisasterVictim> siblings = new ArrayList<>(siblingsHash);

                // Adding siblings to each other
                for (int i = 0; i < siblings.size(); i++) {
                    for (int j = i + 1; j < siblings.size(); j++) {
                        // Check if connection already exists
                        DisasterVictim person1 = siblings.get(i);
                        DisasterVictim person2 = siblings.get(j);

                        FamilyRelation pendingSiblingConnection = new FamilyRelation(person1, "sibling", person2);

                        // Check if person1 already has connection
                        if (person1.familyConnectionAlreadyExists(pendingSiblingConnection, "sibling")) {
                            continue;
                        }

                        person1.familyConnections.add(pendingSiblingConnection);
                        person2.familyConnections.add(pendingSiblingConnection);
                    }
                }
                break;
            case "parent": { // Checking if the connection is a parent
                // Enforce a strict two-sided relationship
                DisasterVictim parent = this;
                DisasterVictim child = familyConnection.getPersonTwo();

                FamilyRelation pendingParentConnection = new FamilyRelation(parent, "parent", child);
                FamilyRelation pendingChildConnection = new FamilyRelation(child, "child", parent);

                // Check if child connection already exists
                if (!child.familyConnectionAlreadyExists(pendingChildConnection, "child"))
                    child.familyConnections.add(pendingChildConnection);

                parent.familyConnections.add(pendingParentConnection);


                break;
            }
            case "child": { // Checking if the connection is a child
                // Enforce a strict two-sided relationship
                DisasterVictim child = this;
                DisasterVictim parent = familyConnection.getPersonTwo();

                FamilyRelation pendingParentConnection = new FamilyRelation(parent, "parent", child);
                FamilyRelation pendingChildConnection = new FamilyRelation(child, "child", parent);

                // Check if parent connection already exists
                if (!parent.familyConnectionAlreadyExists(pendingParentConnection, "parent"))
                    parent.addFamilyConnection(pendingParentConnection);

                child.familyConnections.add(pendingChildConnection);

                break;
            }
            case "spouse": {// Checking if the connection is a spouse
                // Enforce a strict two-sided relationship
                DisasterVictim spouse1 = this;
                DisasterVictim spouse2 = familyConnection.getPersonTwo();

                FamilyRelation pendingSpouseConnection = new FamilyRelation(spouse1, "spouse", spouse2);
                FamilyRelation alternateSpouseConnection = new FamilyRelation(spouse2, "spouse", spouse1);

                // Check if spouse connection already exists
                if (!spouse1.familyConnectionAlreadyExists(pendingSpouseConnection, "spouse"))
                    spouse1.familyConnections.add(pendingSpouseConnection);

                if (!spouse2.familyConnectionAlreadyExists(alternateSpouseConnection, "spouse"))
                    spouse2.familyConnections.add(alternateSpouseConnection);

                break;
            }
            default:
                throw new IllegalArgumentException("Invalid family connection provided: Invalid relationship type");
        }

    }

    /**
     * Removes a family connection from the victim and fixes any inconsistencies
     *
     * @param familyConnection the family connection to remove
     */
    public void removeFamilyConnection(FamilyRelation familyConnection) {

        // Check if the connection is relevant to the victim
        if (!familyConnection.getPersonOne().equals(this) && !familyConnection.getPersonTwo().equals(this)) {
            throw new IllegalArgumentException("Invalid family connection provided: Connection not relevant to the victim");
        }

        switch (familyConnection.getRelationshipTo()) {
            case "sibling":              // If siblings are disconnected, it cascades effectively everywhere

                // Getting all siblings
                HashSet<DisasterVictim> siblings = familyConnection.getAllSiblings(new HashSet<>());

                HashMap<DisasterVictim, FamilyRelation> connectionsToRemove = new HashMap<>();

                // Removing "siblings" relationships
                siblings.forEach((sibling) -> {
                    sibling.getFamilyConnections().forEach((connection) -> {
                        if (connection.getRelationshipTo().equals("sibling")) {
                            // Add to remove list
                            connectionsToRemove.put(sibling, connection);
                        }
                    });
                });

                // Removing all sibling connections
                connectionsToRemove.forEach((sibling, connection) -> {
                    sibling.familyConnections.remove(connection);
                });
                break;
            case "spouse":
                DisasterVictim spouse1 = familyConnection.getPersonOne();
                DisasterVictim spouse2 = familyConnection.getPersonTwo();

                // Removing all spouse connections (null safe)
                spouse1.familyConnections.forEach((connection) -> {
                    if (connection.getPersonOne().equals(spouse1) && connection.getPersonTwo().equals(spouse2)) {
                        spouse1.familyConnections.remove(connection);
                    }
                });

                spouse2.familyConnections.forEach((connection) -> {
                    if (connection.getPersonOne().equals(spouse2) && connection.getPersonTwo().equals(spouse1)) {
                        spouse2.familyConnections.remove(connection);
                    }
                });

                break;
            case "parent": {
                DisasterVictim parent = familyConnection.getPersonOne();
                DisasterVictim child = familyConnection.getPersonTwo();


                // Removing all parent-child connections (null safe)
                child.familyConnections.forEach((connection) -> {
                    if (connection.getPersonOne().equals(child) && connection.getPersonTwo().equals(parent)) {
                        child.familyConnections.remove(connection);
                    }
                });

                parent.familyConnections.forEach((connection) -> {
                    if (connection.getPersonOne().equals(parent) && connection.getPersonTwo().equals(child)) {
                        parent.familyConnections.remove(connection);
                    }
                });
                break;
            }
            case "child": {
                DisasterVictim parent = familyConnection.getPersonOne();
                DisasterVictim child = familyConnection.getPersonTwo();

                // Removing all parent-child connections (null safe)
                parent.familyConnections.forEach((connection) -> {
                    if (connection.getPersonOne().equals(parent) && connection.getPersonTwo().equals(child)) {
                        parent.familyConnections.remove(connection);
                    }
                });

                child.familyConnections.forEach((connection) -> {
                    if (connection.getPersonOne().equals(child) && connection.getPersonTwo().equals(parent)) {
                        child.familyConnections.remove(connection);
                    }
                });

                break;
            }
        }
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

    public void addPersonalBelonging(Supply supply) throws IllegalArgumentException {
        if (supply.getSource() == null) {
            throw new IllegalArgumentException("Supply does not have a source: It doesn't exist in the system.");
        }
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

    /**
     * Gets the priority of the victim
     *
     * @return the priority of the victim
     */
    public int getPriority() {
        // Calculate priority based on the entry date and the age of the victim

        int priorityWeight = 0;

        // Calculate the difference in days between the entry date and today: daysSinceEntry
        double daysSinceEntry = ApplicationUtils.getDaysSince(ENTRY_DATE);

        // Very old people and very young people have high priority
        if (approximateAge < 5 || approximateAge > 65) {
            priorityWeight += 20;
        }

        // People who have been in the system for a long time have high priority
        if (daysSinceEntry > 30) {
            priorityWeight += 5;
        }

        // People with dietary restrictions have high priority
        if (!dietaryRestrictions.isEmpty()) {
            priorityWeight += 1;
        }

        // People with medical records have high priority
        if (!medicalRecords.isEmpty()) {
            priorityWeight += 10;
        }

        return priorityWeight;
    }

    @Override
    public int compareTo(DisasterVictim o) {
        return 0;
    }
}