package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class MedicalRecordTest {

    private MedicalRecord testMedicalRecord;
    private final Location expectedLocation = new Location("ShelterA", "140 8 Ave NW ");
    private final String expectedTreatmentDetails = "Broken Arm";
    private final String expectedDateOfTreatment = "2021-01-01";

    @Before
    public void setUp() {
        testMedicalRecord = new MedicalRecord(expectedLocation, expectedTreatmentDetails, expectedDateOfTreatment);
    }

    @Test
    public void testObjectCreation() {
        assertNotNull(testMedicalRecord);
    }

    @Test
    public void testConstructorLocation() {
        assertEquals("Constructor should set the correct Location", expectedLocation, testMedicalRecord.getLocation());
    }

    @Test
    public void testConstructorTreatmentDetails() {
        assertEquals("Constructor should set the correct treatment details", expectedTreatmentDetails, testMedicalRecord.getTreatmentDetails());
    }

    @Test
    public void testConstructorDateOfTreatment() {
        assertEquals("Constructor should set the correct date of treatment", expectedDateOfTreatment, testMedicalRecord.getDateOfTreatment());
    }

    @Test
    public void testSetAndGetLocation() {
        Location newLocation = new Location("ShelterB", "150 8 Ave NW");
        testMedicalRecord.setLocation(newLocation);
        assertEquals("setLocation should update the Location", newLocation, testMedicalRecord.getLocation());
    }

    @Test
    public void testSetAndGetTreatmentDetails() {
        String newTreatmentDetails = "No surgery required";
        testMedicalRecord.setTreatmentDetails(newTreatmentDetails);
        assertEquals("setTreatmentDetails should update the treatment details", newTreatmentDetails, testMedicalRecord.getTreatmentDetails());
    }

    @Test
    public void testSetAndGetDateOfTreatment() {
        String newDateOfTreatment = "2021-10-11";
        testMedicalRecord.setDateOfTreatment(newDateOfTreatment);
        assertEquals("setDateOfTreatment should update date of treatment", newDateOfTreatment, testMedicalRecord.getDateOfTreatment());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseDateOfTreatmentInvalidFormat() {
        /* Does not parse dd-mm-yyyy format */
        /* Throws an exception if date is not in yyyy-mm-dd format */

        String invalidDateOfTreatment = "11-10-2021";
        testMedicalRecord.setDateOfTreatment(invalidDateOfTreatment);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseDateWithANonDate() {
        /* Throws an exception if date is not in yyyy-mm-dd format */
        String nonDate = "Not a date";
        testMedicalRecord.setDateOfTreatment(nonDate);
    }
}
