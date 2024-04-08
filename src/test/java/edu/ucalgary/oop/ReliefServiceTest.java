package edu.ucalgary.oop;


import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;


public class ReliefServiceTest {

    private ReliefService testReliefService;
    private final Inquirer expectedInquirer = new Inquirer("John", "Doe", "Looking for children", "403-555-666");
    private final DisasterVictim expectedMissingPerson = new DisasterVictim("Bobby", "2024/01/01");
    private final String expectedDateOfInquiry = "2024/02/02";
    private final String expectedInfoProvided = "Looking for family";
    private final Location expectedLastKnownLocation = new Location("Location A", "123 Location lane");

    private final String expectedValidDateOfInquiry = "2023.01.01";

    @Before
    public void setUp() {
        testReliefService = new ReliefService(expectedInquirer, expectedMissingPerson, expectedDateOfInquiry, expectedInfoProvided, expectedLastKnownLocation);
    }

    @Test
    public void testObjectCreation() {
        assertNotNull(testReliefService);
    }

    /*-----------Testing Constructor----------*/

    @Test
    public void testConstructorInquirer() {
        assertEquals("Constructor should set the correct inquirer", expectedInquirer, testReliefService.getInquirer());
    }

    @Test
    public void testConstructorMissingPersons() {
        assertEquals("Constructor should set the correct missing persons", expectedMissingPerson, testReliefService.getMissingPerson());
    }

    @Test
    public void testConstructorDateOfInquiry() {
        assertEquals("Constructor should set the correct date of inquiry", expectedDateOfInquiry, testReliefService.getDateOfInquiry());
    }

    @Test
    public void testConstructorInfoProvided() {
        assertEquals("Constructor should set the correct info provided", expectedInfoProvided, testReliefService.getInfoProvided());
    }

    @Test
    public void testConstructorLastKnownLocation() {
        assertEquals("Constructor should set the correct last known location", expectedLastKnownLocation, testReliefService.getLastKnownLocation());
    }

    /*-------------Testing Getters & Setters-----------------*/

    @Test
    public void testSetAndGetInquirer() {
        Inquirer newInquirer = new Inquirer("Jane", "Doe", "I am looking for my mother", "Looking for parents");

        testReliefService.setInquirer(newInquirer);
        assertEquals("setInquirer should update the inquirer", newInquirer, testReliefService.getInquirer());
    }

    @Test
    public void testSetAndGetMissingPerson() {
        DisasterVictim newVictim = new DisasterVictim("Bobby", "403-566-000");

        testReliefService.setMissingPerson(newVictim);
        assertEquals("setMissingPersons should update the missing person", newVictim, testReliefService.getMissingPerson());
    }

    @Test
    public void testSetAndGetValidDateOfInquiry() {
        testReliefService.setDateOfInquiry(expectedDateOfInquiry);
        assertEquals("setDateOfInquiry should update the date of inquiry", expectedDateOfInquiry, testReliefService.getDateOfInquiry());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidDelimiterDateOfInquiry() {
        String invalidDelimiter = "2024!01)01";
        testReliefService.setDateOfInquiry(invalidDelimiter);               // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidFormatDateOfInquiry() {
        String invalidFormat = "01.01.2024";
        testReliefService.setDateOfInquiry(invalidFormat);               // Expecting line to fail
    }

    @Test
    public void testSetImpossibleDateOfInquiry() {
        String impossibleDate = "2022.99.99";
        testReliefService.setDateOfInquiry(impossibleDate);              // Expecting line to fail
    }

    @Test
    public void testSetFutureDateOfInquiry() {
        String futureDate = "2025.01.01";
        testReliefService.setDateOfInquiry(futureDate);                 // Expecting line to fail
    }

    @Test
    public void testSetAndGetInfoProvided() {
        String newInfoProvided = "I am looking for my brother";

        testReliefService.setInfoProvided(newInfoProvided);
        assertEquals("setInfoProvided should update the info provided", newInfoProvided, testReliefService.getInfoProvided());
    }

    @Test
    public void testSetAndGetLastKnownLocation() {
        Location newLocation = new Location("Location B", "456 Location lane");

        testReliefService.setLastKnownLocation(newLocation);
        assertEquals("setLastKnownLocation should update the last known location", newLocation, testReliefService.getLastKnownLocation());
    }
}