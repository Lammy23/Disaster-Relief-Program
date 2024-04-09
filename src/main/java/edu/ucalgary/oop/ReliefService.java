package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ReliefService implements ReliefServiceInterface {
    private Inquirer inquirer;                                                              // The main character, gotten from the sql directly
    private PriorityQueue<DisasterVictim> missingPersons = new PriorityQueue<>();           // Requires a brilliant function to get names (words) from the infoProvided (string) of inquirer and search em up (as both fname and lname) in locations
    private ArrayList<InquiryLog> logs = new ArrayList<>();                                 // REQ 5: To ensure multiple interaction with inquirer


    // TODO: Update tests for this class
    // TODO: Implement PriorityQueue with your own spice.

    /*---------------Constructor---------------*/

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

    /*---------------Interface Methods---------------*/

    // REQ 7: Implementing Interface's functions

    @Override
    public DisasterVictim searchDisasterVictim() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the first name and last name of the victim. If you don't know the full first name or last name, you can type it in partially. We will" +
                "automatically search it up for you\nEnter here: ");
        String keyWord = scanner.nextLine();

        // Check if user is location based or from HQ
        if (MainApplication.workerType.equals("central")) {
            // Search inquirer objects

        }
        return new DisasterVictim("null", "null");
    }

    @Override
    public void enterReliefServiceInfo() {

    }

    @Override
    public void createReliefService() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Creating a new Relief Service object");



    }

    @Override
    public void searchReliefService(ReliefService reliefService) {

    }

    @Override
    public void deleteFromDatabase() {

    }

    @Override
    public void storeToDatabase() {

    }


}
