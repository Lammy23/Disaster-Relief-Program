package edu.ucalgary.oop;

/*

## Attributes ##

- FIRST_NAME: String
- LAST_NAME: String
- INFO: String
- SERVICES_PHONE: String

## Methods ##

+ Inquirer(FIRST_NAME: String, LAST_NAME: String, SERVICES_PHONE:String, INFO:String)
+ getFirstName(): String
+ getLastName(): String
+ getInfo():String
+ getServicesPhone():String
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Inquirer {
    private final String FIRST_NAME;
    private final String LAST_NAME;
    private final String INFO;
    private final String SERVICES_PHONE;

    private Connection dbConnect;
    private ResultSet results;

    public Inquirer(String FIRST_NAME, String LAST_NAME, String INFO, String SERVICES_PHONE) {
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.INFO = INFO;
        this.SERVICES_PHONE = SERVICES_PHONE;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public String getSERVICES_PHONE() {
        return SERVICES_PHONE;
    }

    public String getINFO() {
        return INFO;
    }

    public String getLogDetails() {
        /* Reading log details from postgreSQL database */
        StringBuffer logDetails = new StringBuffer();

        try {
            String query = "SELECT inquiry_log.details, inquiry_log.calldate FROM inquirer RIGHT OUTER JOIN inquiry_log ON inquirer.id = inquiry_log.inquirer WHERE (inquirer.firstname = ? AND inquirer.lastname = ?)";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setString(1, this.FIRST_NAME);
            myStmt.setString(2, this.LAST_NAME);
            results = myStmt.executeQuery();

            while (results.next()) {
                logDetails.append(results.getString("calldate")).append(results.getString("details"));
                logDetails.append("\n\n");
            }
            myStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logDetails.toString();
    }
}