package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

public class DisasterVictimTest {

    private DisasterVictim testDisasterVictim;
    private final String expectedFirstName = "John";
    private final String expectedValidEntryDate = "2024/01/01";


    @Before
    public void setUp() {
        testDisasterVictim = new DisasterVictim(expectedFirstName, expectedValidEntryDate);
    }

    @Test
    public void testObjectCreation() {
        assertNotNull(testDisasterVictim);
    }

    @Test
    public void testConstructorFirstName() {
        assertEquals("Constructor should set the correct first name", expectedFirstName, testDisasterVictim.getFirstName());
    }

    @Test
    public void testConstructorValidEntryDate() {
        assertEquals("Constructor should set the correct entry date", expectedValidEntryDate, testDisasterVictim.getEntryDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidDelimiterEntryDate() {
        String invalidDelimiter = "2024*01*01";
        DisasterVictim testDisasterVictim = new DisasterVictim(expectedFirstName, invalidDelimiter); // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidFormatEntryDate() {
        String invalidFormat = "01-01-2024";
        DisasterVictim testDisasterVictim = new DisasterVictim(expectedFirstName, invalidFormat);   // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorImpossibleEntryDate() {
        String impossibleDate = "99-99-2024";
        DisasterVictim testDisasterVictim = new DisasterVictim(expectedFirstName, impossibleDate);  // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorFutureEntryDate() {
        String futureDate = "2025-01-01";
        DisasterVictim testDisasterVictim = new DisasterVictim(expectedFirstName, futureDate);      // Expecting line to fail
    }

    @Test
    public void testSetAndGetMedicalRecords() {
        MedicalRecord medicalRecord = new MedicalRecord("Calgary", "Broken Leg", "2021-10-10");
        testDisasterVictim.addMedicalRecord(medicalRecord);
        assertEquals("setMedicalRecords should update the medical records", medicalRecord, testDisasterVictim.getMedicalRecords().get(0));
    }

    @Test
    public void testSetAndGetPersonalBelongings() {
        Supply supply = new Supply("Water", 10);
        testDisasterVictim.addPersonalBelonging(supply);
        assertEquals("setPersonalBelongings should update the personal belongings", supply, testDisasterVictim.getPersonalBelongings().get(0));
    }

    @Test
    public void testSetAndGetMissingStatus() {
        testDisasterVictim.setMissingStatus(true);
        assertEquals("setMissingStatus should update the missing status", true, testDisasterVictim.getMissingStatus());
    }

    @Test
    public void testSetAndGetLastKnownLocation() {
        Location location = new Location("ShelterA", "140 8 Ave NW");
        testDisasterVictim.setLastKnownLocation(location);
        assertEquals("setLastKnownLocation should update the last known location", location, testDisasterVictim.getLastKnownLocation());
    }


    @Test
    public void testAddPersonalBelonging() {
        Supply supply = new Supply("Water", 10);
        testDisasterVictim.addPersonalBelonging(supply);
        assertEquals("addPersonalBelonging should add the personal belonging", supply, testDisasterVictim.getPersonalBelongings().get(0));
    }

    @Test
    public void testAddAndRemovePersonalBelonging() {
        Supply supply = new Supply("Water", 10);
        testDisasterVictim.addPersonalBelonging(supply);
        testDisasterVictim.removePersonalBelonging(supply);
        assertEquals("removePersonalBelonging should remove the personal belonging", 0, testDisasterVictim.getPersonalBelongings().size());
    }

    @Test
    public void testAddMedicalRecord() {
        MedicalRecord medicalRecord = new MedicalRecord("Calgary", "Broken Leg", "2021-10-10");
        testDisasterVictim.addMedicalRecord(medicalRecord);
        assertEquals("addMedicalRecord should add the medical record", medicalRecord, testDisasterVictim.getMedicalRecords().get(0));
    }

    @Test
    public void testAddAndRemoveMedicalRecord() {
        MedicalRecord medicalRecord = new MedicalRecord("Calgary", "Broken Leg", "2021-10-10");
        testDisasterVictim.addMedicalRecord(medicalRecord);
        testDisasterVictim.removeMedicalRecord(medicalRecord);
        assertEquals("removeMedicalRecord should remove the medical record", 0, testDisasterVictim.getMedicalRecords().size());
    }
}
