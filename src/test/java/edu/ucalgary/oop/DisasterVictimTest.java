package edu.ucalgary.oop;

import org.junit.*;

import static org.junit.Assert.*;

public class DisasterVictimTest {

    private DisasterVictim testDisasterVictim;
    private String expectedFirstName = "John";
    private String expectedEntryDate = "2021-10-10";


    public DisasterVictimTest() {
    }

    @Before
    public void setUp() {
        testDisasterVictim = new DisasterVictim(expectedFirstName, expectedEntryDate);
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
    public void testConstructorEntryDate() {
        assertEquals("Constructor should set the correct entry date", expectedEntryDate, testDisasterVictim.getEntryDate());
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
