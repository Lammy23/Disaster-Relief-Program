package edu.ucalgary.oop;


import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;


public class MissingPersonReliefServiceTest {

    private MissingPersonReliefService testService;
    private Inquirer expectedInquirer = new Inquirer("John", "Doe", "403-123-4567", "I am looking for my family");

    private Person p1 = new Person("Robert", "Hess");
    private Person p2 = new Person("GM", "Hikaru");
    private PriorityQueue<DisasterVictim> expectedMissingPersons = new PriorityQueue<DisasterVictim>(p1, p2);
    private String expectedDateOfInquiry = "2021-10-10";
    private String expectedInfoProvided = "I am looking for my family";

    public MissingPersonReliefServiceTest() {
    }

    @Before
    public void setUp() {
        testService = new MissingPersonReliefService(expectedInquirer, expectedMissingPersons, expectedDateOfInquiry, expectedInfoProvided);
    }

    @Test
    public void testObjectCreation() {
        assertNotNull(testService);
    }

    @Test
    public void testConstructorInquirer() {
        assertEquals("Constructor should set the correct inquirer", expectedInquirer, testService.getInquirer());
    }

    @Test
    public void testConstructorMissingPersons() {
        assertEquals("Constructor should set the correct missing persons", expectedMissingPersons, testService.getMissingPersons());
    }

    @Test
    public void testConstructorDateOfInquiry() {
        assertEquals("Constructor should set the correct date of inquiry", expectedDateOfInquiry, testService.getDateOfInquiry());
    }

    @Test
    public void testConstructorInfoProvided() {
        assertEquals("Constructor should set the correct info provided", expectedInfoProvided, testService.getInfoProvided());
    }

    @Test
    public void testSetAndGetInquirer() {
        Inquirer newInquirer = new Inquirer("Jane", "Doe", "403-123-4567", "I am looking for my mother");
        testService.setInquirer(newInquirer);
        assertEquals("setInquirer should update the inquirer", newInquirer, testService.getInquirer());
    }

    @Test
    public void testSetAndGetMissingPersons() {
        Person p3 = new Person("Bobby", "Doe");
        Person p4 = new Person("Jimmy", "Doe");
        PriorityQueue<DisasterVictim> newMissingPersons = new PriorityQueue<DisasterVictim>(p3, p4);
        testService.setMissingPersons(newMissingPersons);
        assertEquals("setMissingPersons should update the missing persons", newMissingPersons, testService.getMissingPersons());
    }

    @Test
    public void testSetAndGetDateOfInquiry() {
        String newDateOfInquiry = "2021-10-11";
        testService.setDateOfInquiry(newDateOfInquiry);
        assertEquals("setDateOfInquiry should update the date of inquiry", newDateOfInquiry, testService.getDateOfInquiry());
    }

    @Test
    public void testSetAndGetInfoProvided() {
        String newInfoProvided = "I am looking for my brother";
        testService.setInfoProvided(newInfoProvided);
        assertEquals("setInfoProvided should update the info provided", newInfoProvided, testService.getInfoProvided());
    }

    @Test
    public void testGetLogDetails() {
        String expectedLogDetails = "Inquirer: " + expectedInquirer + "\n" + "Date of Inquiry: " + expectedDateOfInquiry + "\n" + "Info Provided: " + expectedInfoProvided + "\n" + "Missing Persons: " + expectedMissingPersons;
        assertEquals("getLogDetails should return the correct log details", expectedLogDetails, testService.getLogDetails());
    }

    @Test
    public void testAddMissingPerson() {
        Person p5 = new Person("Papier", "Marche");
        testService.addMissingPerson(p5);
        assertTrue("addMissingPerson should add the missing person", testService.getMissingPersons().contains(p5));
    }

    @Test
    public void testRemoveMissingPerson() {
        testService.removeMissingPerson(p1);
        assertFalse("removeMissingPerson should remove the missing person", testService.getMissingPersons().contains(p1));
    }

    @Test
    public void testRemoveMissingPersonWithNonExistentPerson() {
        Person p6 = new Person("Papier", "Marche");
        testService.removeMissingPerson(p6);
        assertFalse("removeMissingPerson should not remove the missing person if it does not exist", testService.getMissingPersons().contains(p6));
    }

    @Test
    public void testRemoveMissingPersonWithEmptyMissingPersons() {
        testService.setMissingPersons(new PriorityQueue<DisasterVictim>());
        testService.removeMissingPerson(p1);
        assertEquals("removeMissingPerson should not remove the missing person if it is empty", 0, testService.getMissingPersons().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseDateForDateOfInquiryInvalidLogic() {
        /* date cannot be in the future */
        /* Throws an exception if date of birth is in the future */

        /* Generate future date */
        LocalDate futureDate = LocalDate.now().plusDays(1);

        /* Convert future date to string in the form yyyy-mm-dd */
        String futureDateOfInquiry = futureDate.toString();

        /* Set future date as date of inquiry */
        testService.parseDate(futureDateOfInquiry);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseDateForDateOfInquiryInvalidFormat() {
        /* Does not parse dd-mm-yyyy format */
        /* Throws an exception if date is not in yyyy-mm-dd format */

        String invalidDateOfInquiry = "11-10-2021";
        testService.parseDate(invalidDateOfInquiry);
    }

}
