package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ReliefService {
    private Inquirer inquirer;                                                              // The main character, gotten from the sql directly
    private PriorityQueue<DisasterVictim> missingPersons = new PriorityQueue<>(
            Comparator.comparingInt(DisasterVictim::getPriority).reversed()
    );           // Requires a brilliant function to get names (words) from the infoProvided (string) of inquirer and search em up (as both fname and lname) in locations
    // TODO: Actually, you can imagine that a human would tokenize the logInfo and simply want to search the name up on all locations
    // Heres the order: show log, ask for name, search name, show names along side their locations, tell them to choose a name, set the name as the missing person
    private ArrayList<InquiryLog> logs = new ArrayList<>();                                 // REQ 5: To ensure multiple interaction with inquirer


    // TODO: Update tests for this class
    // TODO: Implement PriorityQueue with your own spice.

    /*---------------Constructor---------------*/

    public ReliefService() {

    }

    public ReliefService(Inquirer inquirer, PriorityQueue<DisasterVictim> missingPersons) throws IllegalArgumentException {
        this.inquirer = inquirer;
        this.missingPersons = missingPersons;
    }

    /*---------------Getters---------------*/

    public Inquirer getInquirer() {
        return inquirer;
    }

    public PriorityQueue<DisasterVictim> getMissingPersons() {
        return this.missingPersons;
    }

    public DisasterVictim getMissingPerson() {
        return this.missingPersons.peek();
    }

    public ArrayList<InquiryLog> getLogs() {
        return logs;
    }

    /*---------------Setters---------------*/

    public void setInquirer(Inquirer inquirer) {
        this.inquirer = inquirer;
    }

    public void setMissingPersons(PriorityQueue<DisasterVictim> missingPersons) {
        this.missingPersons = missingPersons;
    }

    public void setLogs(ArrayList<InquiryLog> logs) {
        this.logs = logs;
    }

    /*---------------Adders and Removers---------------*/

    public void addMissingPerson(DisasterVictim missingPerson) {
        this.missingPersons.add(missingPerson);
    }

    public void addLog(InquiryLog log) {
        this.logs.add(log);
    }

    public void removeMissingPerson(DisasterVictim missingPerson) {
        this.missingPersons.remove(missingPerson);
    }

    public void removeHighPriorityMissingPerson() {
        this.missingPersons.poll();
    }

    public void removeLog(InquiryLog log) {
        this.logs.remove(log);
    }

}
