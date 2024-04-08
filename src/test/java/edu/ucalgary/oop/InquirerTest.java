package edu.ucalgary.oop;

import org.junit.*;

import static org.junit.Assert.*;

public class InquirerTest {

    private Inquirer testInquirer;
    private final String expectedFirstName = "John";
    private final String expectedLastName = "Doe";
    private final String expectedServicesPhone = "403-123-4567";
    private final String expectedInfo = "Looking for dog";


    @Before
    public void setUp() {
        testInquirer = new Inquirer(expectedFirstName, expectedLastName, expectedServicesPhone, expectedInfo);
    }

    @Test
    public void testObjectCreation() {
        assertNotNull(testInquirer);
    }

    /*--------------Testing Constructor-----------------*/

    @Test
    public void testConstructorFirstName() {
        assertEquals("Constructor should set the correct first name", expectedFirstName, testInquirer.getFirstName());
    }

    @Test
    public void testConstructorLastName() {
        assertEquals("Constructor should set the correct last name", expectedLastName, testInquirer.getLastName());
    }

    @Test
    public void testConstructorServicesPhone() {
        assertEquals("Constructor should set the correct services phone", expectedServicesPhone, testInquirer.getServicesPhone());
    }

    @Test
    public void testConstructorInfo() {
        assertEquals("Constructor should set the correct info", expectedInfo, testInquirer.getInfo());
    }

    /*--------------Testing Methods-----------------*/

    @Test
    public void testGetLogDetails() {
        // TODO: Implement function
    }
}
