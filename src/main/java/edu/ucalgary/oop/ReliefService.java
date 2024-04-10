package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Class that represents a relief service for disaster victims
 *
 *
 */
public class ReliefService {
    private Inquirer inquirer;                                                              // The main character, gotten from the sql directly
    private PriorityQueue<DisasterVictim> missingPersons = new PriorityQueue<>(
            Comparator.comparingInt(DisasterVictim::getPriority).reversed()
    );
    private ArrayList<InquiryLog> logs = new ArrayList<>();                                 // REQ 5: To ensure multiple interaction with inquirer

    /*---------------Constructor---------------*/

    /**
     * Constructor for the ReliefService class
     */
    public ReliefService() {

    }

    /**
     * Constructor for the ReliefService class
     */
    public ReliefService(Inquirer inquirer, PriorityQueue<DisasterVictim> missingPersons, ArrayList<InquiryLog> logs) throws IllegalArgumentException {
        this.inquirer = inquirer;
        this.missingPersons = missingPersons;
        this.logs = logs;
    }

    /*---------------Getters---------------*/

    /**
     * Getter for the inquirer
     *
     * @return The inquirer
     */
    public Inquirer getInquirer() {
        return inquirer;
    }

    /**
     * Getter for the missing persons
     *
     * @return The missing persons
     */
    public PriorityQueue<DisasterVictim> getMissingPersons() {
        return this.missingPersons;
    }

    /**
     * Getter for the missing person
     *
     * @return The missing person
     */
    public DisasterVictim getMissingPerson() {
        return this.missingPersons.peek();
    }

    /**
     * Getter for the logs
     *
     * @return The logs
     */
    public ArrayList<InquiryLog> getLogs() {
        return logs;
    }

    /*---------------Setters---------------*/

    /**
     * Setter for the inquirer
     *
     * @param inquirer The inquirer
     */
    public void setInquirer(Inquirer inquirer) {
        this.inquirer = inquirer;
    }

    /**
     * Setter for the missing persons
     *
     * @param missingPersons The missing persons
     */
    public void setMissingPersons(PriorityQueue<DisasterVictim> missingPersons) {
        this.missingPersons = missingPersons;
    }

    /**
     * Setter for the logs
     *
     * @param logs The logs
     */
    public void setLogs(ArrayList<InquiryLog> logs) {
        this.logs = logs;
    }

    /*---------------Adders and Removers---------------*/

    /**
     * Add a missing person to the missing persons list
     * @param missingPerson The missing person to add
     */
    public void addMissingPerson(DisasterVictim missingPerson) {
        this.missingPersons.add(missingPerson);
    }

    /**
     * Add a log to the logs list
     * @param log The log to add
     */
    public void addLog(InquiryLog log) {
        this.logs.add(log);
    }

    /**
     * Remove a missing person from the missing persons list
     * @param missingPerson The missing person to remove
     */
    public void removeMissingPerson(DisasterVictim missingPerson) {
        this.missingPersons.remove(missingPerson);
    }

    /**
     * Remove the highest priority missing person from the missing persons list
     */
    public void removeHighPriorityMissingPerson() {
        this.missingPersons.poll();
    }

    /**
     * Remove a log from the logs list
     * @param log The log to remove
     */
    public void removeLog(InquiryLog log) {
        this.logs.remove(log);
    }

}
