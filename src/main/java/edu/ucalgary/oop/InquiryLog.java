package edu.ucalgary.oop;

/**
 * Class that represents a log of an inquiry for a missing person
 */

// TODO: Provide tests for this class
public class InquiryLog {
    private DisasterVictim missingPerson;                        // The same brilliant function required
    private String dateOfInquiry;                                // The calldate field in the sql table
    private String infoProvided;                                 // the details field in the sql table
    private Location lastKnownLocation;                          // We provide this, we get the location of the DisasterVictim

    /*---------------Constructor---------------*/

    /**
     * Constructor for the `InquiryLog` class
     *
     * @param missingPerson     The missing person being inquired about
     * @param dateOfInquiry     The date of the inquiry
     * @param infoProvided      The information provided by the inquirer
     * @param lastKnownLocation The last known location of the missing person (provided by ReliefSystem)
     */
    public InquiryLog(DisasterVictim missingPerson, String dateOfInquiry, String infoProvided, Location lastKnownLocation) {
        this.missingPerson = missingPerson;
        if (ApplicationUtils.isValidPastDate(dateOfInquiry))
            this.dateOfInquiry = ApplicationUtils.parseDate(dateOfInquiry);
        this.infoProvided = infoProvided;
        this.lastKnownLocation = lastKnownLocation;
    }

    /*---------------Getters---------------*/

    /**
     * Getter for the missing person
     *
     * @return The missing person
     */
    public DisasterVictim getMissingPerson() {
        return missingPerson;
    }

    /**
     * Getter for the date of the inquiry
     *
     * @return The date of the inquiry
     */
    public String getDateOfInquiry() {
        return dateOfInquiry;
    }

    /**
     * Getter for the information provided
     *
     * @return The information provided
     */
    public String getInfoProvided() {
        return infoProvided;
    }

    /**
     * Getter for the last known location
     *
     * @return The last known location
     */
    public Location getLastKnownLocation() {
        return lastKnownLocation;
    }

    /*---------------Setters---------------*/

    /**
     * Setter for the missing person
     *
     * @param missingPerson The missing person
     */
    public void setMissingPerson(DisasterVictim missingPerson) {
        this.missingPerson = missingPerson;
    }

    /**
     * Setter for the date of the inquiry
     *
     * @param dateOfInquiry The date of the inquiry
     */
    public void setDateOfInquiry(String dateOfInquiry) {
        // Validate dateOfInquiry
        if (ApplicationUtils.isValidPastDate(dateOfInquiry))
            this.dateOfInquiry = ApplicationUtils.parseDate(dateOfInquiry);
    }

    /**
     * Setter for the information provided
     *
     * @param infoProvided The information provided
     */
    public void setInfoProvided(String infoProvided) {
        this.infoProvided = infoProvided;
    }

    /**
     * Setter for the last known location
     *
     * @param lastKnownLocation The last known location
     */
    public void setLastKnownLocation(Location lastKnownLocation) {
        this.lastKnownLocation = lastKnownLocation;
    }
}