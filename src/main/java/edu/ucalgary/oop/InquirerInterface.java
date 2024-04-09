package edu.ucalgary.oop;

public interface InquirerInterface {
    public void createInquirer();                               // Create a new inquirer and add it to the persistent MainApplication object

    public void deleteInquirer();                               // Delete an inquirer from the persistent MainApplication object

    public Inquirer searchInquirer();                           // Search for an inquirer in the persistent MainApplication object
}