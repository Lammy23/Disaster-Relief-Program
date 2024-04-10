package edu.ucalgary.oop;

/**
 * Class that represents a log of an inquiry for a missing person
 */

// TODO: Provide tests for this class
public class InquiryLog {
    private String dateOfInquiry;                                // The calldate field in the sql table
    private String infoProvided;                                 // the details field in the sql table
    /*---------------Constructor---------------*/


    public InquiryLog(String dateOfInquiry, String infoProvided) throws IllegalArgumentException {
        if (ApplicationUtils.isValidPastDate(dateOfInquiry))
            this.dateOfInquiry = ApplicationUtils.parseDate(dateOfInquiry);
        else {
            throw new IllegalArgumentException("Invalid date of inquiry");
        }
        this.infoProvided = infoProvided;
    }

    /*---------------Getters---------------*/

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


    /*---------------Setters---------------*/

    /**
     * Setter for the date of the inquiry
     *
     * @param dateOfInquiry The date of the inquiry
     */
    public void setDateOfInquiry(String dateOfInquiry) throws IllegalArgumentException {
        // Validate dateOfInquiry
        if (ApplicationUtils.isValidPastDate(dateOfInquiry))
            this.dateOfInquiry = ApplicationUtils.parseDate(dateOfInquiry);
        else {
            throw new IllegalArgumentException("Invalid date of inquiry");
        }
    }

    /**
     * Setter for the information provided
     *
     * @param infoProvided The information provided
     */
    public void setInfoProvided(String infoProvided) {
        this.infoProvided = infoProvided;
    }

}