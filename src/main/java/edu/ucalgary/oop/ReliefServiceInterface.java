package edu.ucalgary.oop;

public interface ReliefServiceInterface {
    // IMPORTANT: I have decided that ReliefService objects (along with Locations) will be persisted until program end
    // However, upon program end, it will store the values back into the database

    public DisasterVictim searchDisasterVictim();
    public void enterReliefServiceInfo();           // Create ReliefService objects (using CLI). If info doesn't already exist in database,
                                                    // immediately add it to the database as a new entry, else update database

    // Helpers
    public void createReliefService();                                // Creates the ReliefService object (using CLI)
    public void searchReliefService(ReliefService reliefService);   // Checks if relief service already exists in Database
    public void deleteFromDatabase();                                 //
    public void storeToDatabase();                                   // Stores object to database
}
