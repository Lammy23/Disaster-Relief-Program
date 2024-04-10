package edu.ucalgary.oop;

import java.sql.ResultSet;


public class Inquirer {
    private final String FIRST_NAME;
    private final String LAST_NAME;
    private final String SERVICES_PHONE;

    /**
     * Constructor for the Inquirer class
     *
     * @param FIRST_NAME     The first name of the inquirer
     * @param LAST_NAME      The last name of the inquirer
     * @param SERVICES_PHONE The services phone number
     */
    public Inquirer(String FIRST_NAME, String LAST_NAME, String SERVICES_PHONE) {
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.SERVICES_PHONE = SERVICES_PHONE;
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
     * Getter for the services phone number
     *
     * @return The services phone number
     */
    public String getServicesPhone() {
        return SERVICES_PHONE;
    }

}