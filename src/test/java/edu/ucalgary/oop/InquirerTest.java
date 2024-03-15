package edu.ucalgary.oop;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*
extends Person

- INFO: String
- SERVICES_PHONE: String

+ Inquirer(firstName: String, lastName: String, SERVICES_PHONE: String, INFO: String)

+ getInfo(): String
+ getServicesPhone():String
 */

public class InquirerTest {

    private Inquirer testInquirer;
    private String expectedFirstName = "John";
    private String expectedLastName = "Doe";
    private String expectedSERVICES_PHONE = "403-123-4567";
    private String expectedINFO = "I am looking for my missing family members.";


    public InquirerTest() {

    }

    @Before
    public void setUp() {
        testInquirer = new Inquirer(expectedFirstName, expectedLastName, expectedSERVICES_PHONE, expectedINFO);
    }

    @Test
    public void testObjectCreation() {
        assertNotNull(testInquirer);
    }

    @Test
    public void testConstructorFirstName() {
        assertEquals("Constructor should set the correct first name", expectedFirstName, testInquirer.getFirstName());
    }

    @Test
    public void testConstructorLastName() {
        assertEquals("Constructor should set the correct last name", expectedLastName, testInquirer.getLastName());
    }

    @Test
    public void testConstructorSERVICES_PHONE() {
        assertEquals("Constructor should set the correct SERVICES_PHONE", expectedSERVICES_PHONE, testInquirer.getServicesPhone());
    }

    @Test
    public void testConstructorINFO() {
        assertEquals("Constructor should set the correct INFO", expectedINFO, testInquirer.getInfo());
    }

    @Test
    public void testSetAndGetInfo() {
        String newInfo = "I am looking for my missing pet.";
        testInquirer.setInfo(newInfo);
        assertEquals("setInfo should update the INFO", newInfo, testInquirer.getInfo());
    }

    @Test
    public void testGetServicesPhone() {
        assertEquals("getServicesPhone should return the correct SERVICES_PHONE", expectedSERVICES_PHONE, testInquirer.getServicesPhone());
    }
}
