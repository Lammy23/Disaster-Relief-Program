package edu.ucalgary.oop;

import java.sql.*;
import java.util.*;

/**
 * Class that represents a relief service for disaster victims
 *
 * @author Olamikun Aluko
 * <a href="mailto:lammyaluko@gmail.com">Email me</a> for any comments
 * @version 1.1
 * @since 1.0
 */
public class ReliefServiceDB {
    /* Database Fields */
    // TODO: revoke privilege of creating databases from oop.
    // TODO: update all tests. Update ReliefServiceTest too
    private final String RELIEF_DB_URL = "jdbc:postgresql://localhost/disaster_relief";         // Hardcoded database URL
    private final String USERNAME = "oop";                                                      // Hardcoded database username
    private final String PASSWORD = "ucalgary";                                                 // Hardcoded database password
    private Connection dbConnect;                                                               // Connection object for the database
    //    private ResultSet results;                                                                // ResultSet object for the database
    private Stack<ResultSet> resultsStack = new Stack<>();                                        // Stack of ResultSet objects for the database
    // TODO: Try to save to db, if fail, save to txt or some other object file.

    /* OOP Fields */
    private HashSet<Location> locations;
    private HashSet<Inquirer> inquirers;
    // TODO: add an option in CLI to display victims in order of priority. In GUI - additional stats option to view why they are in priority

    /*---------------Constructor---------------*/

    /**
     * Constructor for the ReliefServiceDB class
     */
    public ReliefServiceDB() {
        // Connecting to disaster_relief database
        try {
            dbConnect = DriverManager.getConnection(RELIEF_DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error connecting to database:\n" + e.getMessage());
        }
    }

    /**
     * Constructor with parameters for the ReliefServiceDB class
     */
    public ReliefServiceDB(HashSet<Location> locations, HashSet<Inquirer> inquirers) {
        this();
        this.locations = locations;
        this.inquirers = inquirers;
    }

    /*---------------Find Methods---------------*/

    /**
     * Find a victim by their ID
     *
     * @param victimID The ID of the victim
     * @return The victim
     */
    public DisasterVictim findVictim(int victimID) {
        for (Location location : locations) {
            for (DisasterVictim victim : location.getOccupants()) {
                if (victim.getVictimID() == victimID) {
                    return victim;
                }
            }
        }
        return null;
    }

    /**
     * Find a location by its ID
     *
     * @param locationID The ID of the location
     * @return The location
     */
    public Location findLocation(int locationID) {
        for (Location location : locations) {
            if (location.getLocationID() == locationID) {
                return location;
            }
        }
        return null;
    }

    /*---------------DB Load Methods----------------*/

    public void loadInquirers() {
        try {
            String inquirerQuery = "SELECT * FROM inquirer";
            Statement stmt = dbConnect.createStatement();
            resultsStack.push(stmt.executeQuery(inquirerQuery));
            try (ResultSet results = resultsStack.pop()) {
                while (results.next()) {
                    Inquirer inquirer = new Inquirer(results.getString("first_name"), results.getString("last_name"), results.getString("phone_number"), new ArrayList<>());
                    String inquiryLogQuery = "SELECT * FROM inquiry_log WHERE inq_id = ? ORDER BY date_of_inquiry ASC";
                    PreparedStatement stmt2 = dbConnect.prepareStatement(inquiryLogQuery);
                    stmt2.setInt(1, results.getInt("inq_id"));
                    resultsStack.push(stmt2.executeQuery());
                    try (ResultSet results2 = resultsStack.pop()) {
                        while (results2.next()) {
                            inquirer.addLog(new InquiryLog(results2.getString("date_of_inquiry"), results2.getString("details")));
                        }
                    }
                    this.addInquirer(inquirer);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error loading inquirers from database:\n" + e.getMessage());
        }
    }

    public void loadLocations() {
        try {
            String locationQuery = "SELECT * FROM location";
            Statement stmt = dbConnect.createStatement();
            resultsStack.push(stmt.executeQuery(locationQuery));
            try (ResultSet results = resultsStack.pop()) {
                while (results.next()) {
                    Location location = new Location(
                            results.getInt("l_id"),
                            results.getString("name"),
                            results.getString("address"),
                            new ArrayList<>(),
                            new HashSet<>()
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error loading locations from database:\n" + e.getMessage());
        }
    }

    public void loadDisasterVictims() {
        // TODO: Dietary restrictions in DB should have an acronym section and a description column.
        try {
            String victimQuery = "SELECT * FROM disaster_victim";
            Statement stmt = dbConnect.createStatement();
            resultsStack.push(stmt.executeQuery(victimQuery));
            try (ResultSet results = resultsStack.pop()) {
                while (results.next()) {
                    Location location = findLocation(results.getInt("l_id"));
                    location.addOccupant(
                            new DisasterVictim(
                                    results.getInt("p_id"),
                                    results.getString("entry_date"),
                                    results.getString("first_name"),
                                    results.getString("last_name"),
                                    results.getString("date_of_birth"),
                                    results.getInt("approximate_age"),
                                    results.getString("comments"),
                                    new HashSet<>(),
                                    results.getString("gender"),
                                    new HashSet<>(),
                                    new ArrayList<>(),
                                    new ArrayList<>()
                            )
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error loading disaster victims from database:\n" + e.getMessage());
        }
    }

    public void loadSupplies() {
        try {
            String supplyQuery = "SELECT * FROM supply";
            Statement stmt = dbConnect.createStatement();
            resultsStack.push(stmt.executeQuery(supplyQuery));
            try (ResultSet results = resultsStack.pop()) {
                while (results.next()) {
                    Location l = findLocation(results.getInt("l_id"));
                    l.addSupply(
                            new Supply(
                                    results.getString("type"),
                                    results.getInt("quantity_in_stock"),
                                    l
                            )
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error loading supplies from database:\n" + e.getMessage());
        }
    }

    public void loadFamilyConnections() {
        try {
            String familyQuery = "SELECT * FROM family_relation";
            Statement stmt = dbConnect.createStatement();
            resultsStack.push(stmt.executeQuery(familyQuery));
            try (ResultSet results = resultsStack.pop()) {
                while (results.next()) {
                    DisasterVictim v1 = findVictim(results.getInt("p1_id"));
                    DisasterVictim v2 = findVictim(results.getInt("p2_id"));
                    String relationship = results.getString("relationship");
                    FamilyRelation r = new FamilyRelation(
                            v1,
                            relationship,
                            v2
                    );
                    if (Objects.equals(relationship, "sibling")) {
                        // Add the first connection
                        v1.addFamilyConnection(r);
                        // Add the second connection
                        v2.addFamilyConnection(r);
                    } else {
                        // Add just the first connection
                        v1.addFamilyConnection(
                                new FamilyRelation(
                                        v1,
                                        relationship,
                                        v2
                                )
                        );
                    }

                }
            }
        } catch (SQLException e) {
            System.out.println("Error loading family connections from database:\n" + e.getMessage());
        }
    }

    public void loadDietaryRestrictions() {
        try {
            String dietaryQuery = "SELECT * FROM dietary_restriction";
            Statement stmt = dbConnect.createStatement();
            resultsStack.push(stmt.executeQuery(dietaryQuery));
            try (ResultSet results = resultsStack.pop()) {
                while (results.next()) {
                    DisasterVictim victim = findVictim(results.getInt("p_id"));
                    // Add the dietary restriction
                    victim.addDietaryRestriction(
                            DietaryRestriction.valueOf(results.getString("restriction"))
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error loading dietary restrictions from database:\n" + e.getMessage());
        }
    }

    public void loadMedicalRecords() {
        try {
            String medicalQuery = "SELECT * FROM medical_record";
            Statement stmt = dbConnect.createStatement();
            resultsStack.push(stmt.executeQuery(medicalQuery));
            try (ResultSet results = resultsStack.pop()) {
                while (results.next()) {
                    Location l = findLocation(results.getInt("l_id"));
                    DisasterVictim victim = findVictim(results.getInt("p_id"));
                    // Add the medical record
                    victim.addMedicalRecord(
                            new MedicalRecord(
                                    l,
                                    results.getString("treatment_details"),
                                    results.getString("date_of_treatment")
                            )
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error loading medical records from database:\n" + e.getMessage());
        }
    }

    public void loadPersonalBelongings() {
        // Get all supplies
        HashSet<Supply> supplyPool = new HashSet<>();
        for (Location location : locations) {
            supplyPool.addAll(location.getSupplies());
        }
        try {
            String belongingsQuery = "SELECT * FROM personal_belonging";
            Statement stmt = dbConnect.createStatement();
            resultsStack.push(stmt.executeQuery(belongingsQuery));
            try (ResultSet results = resultsStack.pop()) {
                while (results.next()) {
                    DisasterVictim v = findVictim(results.getInt("p_id"));
                    // get the location by matching the supply type
                    Location l = null;
                    for (Supply s : supplyPool) {
                        if (s.getType().equals(results.getString("type"))) {
                            l = s.getSource();
                            break;
                        }
                    }
                    // Add the personal belonging
                    assert l != null;
                    v.addPersonalBelonging(
                            new Supply(
                                    results.getString("type"),
                                    results.getInt("amount_in_posession"),
                                    l
                            )
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error loading personal belongings from database:\n" + e.getMessage());
        }
    }

    public void emptyDatabase() {
        try {
            String[] tables = {"personal_belonging", "medical_record", "dietary_restriction", "family_relation", "disaster_victim", "supply", "location", "inquirer"};
            for (String table : tables) {
                String query = "DELETE FROM " + table;
                Statement stmt = dbConnect.createStatement();
                stmt.executeUpdate(query);
            }
        } catch (SQLException e) {
            System.out.println("Error emptying database:\n" + e.getMessage());
        }
    }

    public void saveInquirers() {
        // TODO: find where you assign id by finding max and adding one and instead find the hashcode of the objects
        // Save to an already emptied database
        try {
            String inquirerQuery = "INSERT INTO inquirer (first_name, last_name, phone_number) VALUES (?, ?, ?)";
            PreparedStatement stmt = dbConnect.prepareStatement(inquirerQuery);
            for (Inquirer inquirer : inquirers) {
                stmt.setString(1, inquirer.getFirstName());
                stmt.setString(2, inquirer.getLastName());
                stmt.setString(3, inquirer.getPhoneNumber());
                stmt.executeUpdate();
                // Save the logs
                int inqID = inquirer.hashCode();    // Get the hashcode of the inquirer object (Brilliant)
                String logQuery = "INSERT INTO inquiry_log (inq_id, date_of_inquiry, info_provided) VALUES (?, ?, ?)";
                PreparedStatement stmt2 = dbConnect.prepareStatement(logQuery);
                for (InquiryLog log : inquirer.getLogs()) {
                    stmt2.setInt(1, inqID);
                    stmt2.setString(2, log.getDateOfInquiry());
                    stmt2.setString(3, log.getInfoProvided());
                    stmt2.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error saving inquirers to database:\n" + e.getMessage());
        }
    }

    public void saveLocation() {

    }

    public void saveDisasterVictims() {
        try {
            String victimQuery = "INSERT INTO disaster_victim (p_id, entry_date, first_name, last_name, date_of_birth, approximate_age, comments, gender, l_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = dbConnect.prepareStatement(victimQuery);
            for (Location location : locations) {
                for (DisasterVictim victim : location.getOccupants()) {
                    stmt.setInt(1, victim.getVictimID());
                    stmt.setString(2, victim.getEntryDate());
                    stmt.setString(3, victim.getFirstName());
                    stmt.setString(4, victim.getLastName());
                    stmt.setString(5, victim.getDateOfBirth());
                    stmt.setInt(6, victim.getApproximateAge());
                    stmt.setString(7, victim.getComments());
                    stmt.setString(8, victim.getGender());
                    stmt.setInt(9, location.getLocationID());
                    stmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error saving disaster victims to database:\n" + e.getMessage());
        }
    }

    public void saveSupplies() {
        try {
            String supplyQuery = "INSERT INTO supply (l_id, type, quantity_in_stock) VALUES (?, ?, ?)";
            PreparedStatement stmt = dbConnect.prepareStatement(supplyQuery);
            for (Location location : locations) {
                for (Supply supply : location.getSupplies()) {
                    stmt.setInt(3, location.getLocationID());
                    stmt.setString(1, supply.getType());
                    stmt.setInt(2, supply.getQuantity());
                    stmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error saving supplies to database:\n" + e.getMessage());
        }
    }

    public void saveFamilyConnections() {
        // Sibling pairs that are already assigned
        HashSet<FamilyRelation> siblingRelations = new HashSet<>();
        try {
            String familyQuery = "INSERT INTO family_relation (p1_id, relationship_to, p2_id) VALUES (?, ?, ?)";
            PreparedStatement stmt = dbConnect.prepareStatement(familyQuery);
            for (Location location : locations) {
                for (DisasterVictim victim : location.getOccupants()) {
                    for (FamilyRelation relation : victim.getFamilyConnections()) {
                        // Check if relationship is sibling
                        if (!siblingRelations.contains(relation)) {
                            stmt.setInt(1, relation.getPersonOne().getVictimID());
                            stmt.setString(2, relation.getRelationshipTo());
                            stmt.setInt(3, relation.getPersonOne().getVictimID());
                            stmt.executeUpdate();
                            if (Objects.equals(relation.getRelationshipTo(), "sibling")) {
                                siblingRelations.add(relation);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error saving family connections to database:\n" + e.getMessage());
        }
    }

    public void saveDietaryRestrictions() {
        try {
            String dietaryQuery = "INSERT INTO dietary_restriction (p_id, diet_restriction) VALUES (?, ?)";
            PreparedStatement stmt = dbConnect.prepareStatement(dietaryQuery);
            for (Location location : locations) {
                for (DisasterVictim victim : location.getOccupants()) {
                    for (DietaryRestriction restriction : victim.getDietaryRestrictions()) {
                        stmt.setInt(1, victim.getVictimID());
                        stmt.setString(2, restriction.toString());
                        stmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error saving dietary restrictions to database:\n" + e.getMessage());
        }
    }

    public void saveMedicalRecords() {
        try {
            String medicalQuery = "INSERT INTO medical_record (p_id, l_id, treatment_details, date_of_treatment) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = dbConnect.prepareStatement(medicalQuery);
            for (Location location : locations) {
                for (DisasterVictim victim : location.getOccupants()) {
                    for (MedicalRecord record : victim.getMedicalRecords()) {
                        stmt.setInt(1, victim.getVictimID());
                        stmt.setInt(2, record.getLocation().getLocationID());
                        stmt.setString(3, record.getTreatmentDetails());
                        stmt.setString(4, record.getDateOfTreatment());
                        stmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error saving medical records to database:\n" + e.getMessage());
        }
    }

    public void savePersonalBelongings() {
        try {
            String belongingsQuery = "INSERT INTO personal_belonging (s_id, p_id, amount_in_possession) VALUES (?, ?, ?)";
            PreparedStatement stmt = dbConnect.prepareStatement(belongingsQuery);
            for (Location location : locations) {
                for (DisasterVictim victim : location.getOccupants()) {
                    for (Supply belonging : victim.getPersonalBelongings()) {
                        // Find supply with the same type in location
                        Supply s = null;
                        for (Supply supply : location.getSupplies()) {
                            if (supply.getType().equals(belonging.getType())) {
                                s = supply;
                                break;
                            }
                        }
                        // Get it's id from the db
                        String supplyQuery = "SELECT s_id FROM supply WHERE l_id = ? AND type = ?";
                        PreparedStatement stmt2 = dbConnect.prepareStatement(supplyQuery);
                        stmt2.setInt(1, location.getLocationID());
                        stmt2.setString(2, belonging.getType());
                        ResultSet results = stmt2.executeQuery();
                        int sID = results.getInt("s_id");

                        stmt.setInt(1, sID);
                        stmt.setInt(2, victim.getVictimID());
                        stmt.setInt(3, belonging.getQuantity());
                        stmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error saving personal belongings to database:\n" + e.getMessage());
        }
    }

    /*---------------DB Close Method---------------*/

    public void close() {
        try {
            dbConnect.close();
        } catch (SQLException e) {
            System.out.println("Error closing database connection:\n" + e.getMessage());
        }
    }

    /*---------------OOP Getters---------------*/

    /**
     * Getter for the inquirer
     *
     * @return The inquirer
     */
    public HashSet<Inquirer> getInquirers() {
        return inquirers;
    }

    /**
     * Getter for the locations
     *
     * @return The locations
     */
    public HashSet<Location> getLocations() {
        return locations;
    }

    /*---------------OOP Setters---------------*/

    /**
     * Setter for the inquirer
     *
     * @param inquirers The inquirer
     */
    public void setInquirers(HashSet<Inquirer> inquirers) {
        this.inquirers = inquirers;
    }

    /**
     * Setter for the locations
     *
     * @param locations The locations
     */
    public void setLocations(HashSet<Location> locations) {
        this.locations = locations;
    }

    /*---------------OOP Adders and Removers---------------*/

    public void addInquirer(Inquirer inquirer) {
        // TODO: write tests and add defining logic
        this.inquirers.add(inquirer);
    }

    public void removeInquirer(Inquirer inquirer) {
        // TODO: writes tests and add defining logic
        this.inquirers.remove(inquirer);
    }

    /**
     * Add a location to the locations list
     */
    public void addLocation(Location location) {
        this.locations.add(location);
    }

    /**
     * Remove a location from the locations list
     */
    public void removeLocation(Location location) {
        this.locations.remove(location);
    }
}