package edu.ucalgary.oop;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Class that represents an inquirer for a missing person
 *
 * @author Olamikun Aluko
 * <a href="mailto:lammyaluko@gmail.com">Email me</a> for any comments
 * @version 1.1
 * @since 1.0
 */
public class Inquirer {
    private final String FIRST_NAME;
    private final String LAST_NAME;
    private final String PHONE_NUMBER;
    private ArrayList<InquiryLog> logs = new ArrayList<>();
    // Each Inquirer may have a missing person of interest

    /**
     * Constructor for the Inquirer class
     *
     * @param FIRST_NAME     The first name of the inquirer
     * @param LAST_NAME      The last name of the inquirer
     * @param SERVICES_PHONE The services phone number
     */
    public Inquirer(String FIRST_NAME, String LAST_NAME, String SERVICES_PHONE, ArrayList<InquiryLog> logs) {
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.PHONE_NUMBER = SERVICES_PHONE;
        this.logs = logs;
    }

    /**
     * Constructor for the Inquirer class
     *
     * @return The first name of the inquirer
     */
    public String getFirstName() {
        return FIRST_NAME;
    }

    /**
     * Getter for the last name of the inquirer
     *
     * @return The last name of the inquirer
     */
    public String getLastName() {
        return LAST_NAME;
    }

    /**
     * Getter for the phone number
     *
     * @return The phone number
     */
    public String getPhoneNumber() {
        return PHONE_NUMBER;
    }

    /**
     * Getter for the logs
     *
     * @return The logs
     */
    public ArrayList<InquiryLog> getLogs() {
        return logs;
    }

    public void setLogs(ArrayList<InquiryLog> logs) {
        this.logs = logs;
    }

    /*-----------Adders & Removers------------*/

    public void addLog(InquiryLog log) {
        // TODO: Check if log already exists
        this.logs.add(log);
    }

    public void removeLog(InquiryLog log) {
        // TODO: Check if log doesn't exist
        this.logs.remove(log);
    }

}