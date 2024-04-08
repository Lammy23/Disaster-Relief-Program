package edu.ucalgary.oop;

import org.junit.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DisasterVictimTest {

    private DisasterVictim testDisasterVictim;
    private final String expectedFirstName = "John";
    private final String expectedValidEntryDate = "2024/01/01";
    private final String expectedValidDateOfBirth = "2024-01-01";
    private final int expectedValidApproximateAge = 25;
    private final String expectedValidGender = getValidGender();
    private final String InvalidGender = getInvalidGender();

    private DisasterVictim x;
    private DisasterVictim y;
    private DisasterVictim z;

    private String getValidGender() {
        String validGender = "";
        BufferedReader inputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader("./src/main/java/edu/ucalgary/oop/GenderOptions.txt"));
            validGender = inputStream.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return validGender;
    }

    private String getInvalidGender() {
        StringBuilder invalidGender = new StringBuilder();
        BufferedReader inputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader("./src/main/java/edu/ucalgary/oop/GenderOptions.txt"));
            String line;
            while ((line = inputStream.readLine()) != null) {
                char firstChar = line.charAt(0);
                // Increment the ASCII value of the first character
                char newChar = (char) (firstChar + 1);
                invalidGender.append(newChar);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return invalidGender.toString();
    }

    @Before
    public void setUp() {
        testDisasterVictim = new DisasterVictim(expectedFirstName, expectedValidEntryDate);

        // Testing Relationships
        x = new DisasterVictim("John", "2024/01/01");
        y = new DisasterVictim("Bobby", "2024/01/01");
        z = new DisasterVictim("Mack", "2024/01/01");
    }

    @Test
    public void testObjectCreation() {
        assertNotNull(testDisasterVictim);
    }

    /*-----------Testing Constructor-----------*/

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
        new DisasterVictim(expectedFirstName, invalidDelimiter); // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidFormatEntryDate() {
        String invalidFormat = "01-01-2024";
        new DisasterVictim(expectedFirstName, invalidFormat);   // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorImpossibleEntryDate() {
        String impossibleDate = "2024-99-99";
        new DisasterVictim(expectedFirstName, impossibleDate);  // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorFutureEntryDate() {
        String futureDate = "2025-01-01";
        new DisasterVictim(expectedFirstName, futureDate);      // Expecting line to fail
    }

    /*------------Testing Getters/Setters-------------*/

    @Test
    public void testSetAndGetFirstName() {
        String firstName = "Bob";
        testDisasterVictim.setFirstName(firstName);
        assertEquals("setFirstName should update the first name", firstName, testDisasterVictim.getFirstName());
    }

    @Test
    public void testSetAndGetLastName() {
        String lastName = "Doe";
        testDisasterVictim.setLastName(lastName);
        assertEquals("setLastName should update the last name", lastName, testDisasterVictim.getLastName());
    }

    @Test
    public void testSetAndGetValidDateOfBirth() {
        testDisasterVictim.setDateOfBirth(expectedValidDateOfBirth);
        assertEquals("setDateOfBirth should update the date of birth", expectedValidDateOfBirth, testDisasterVictim.getDateOfBirth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidDelimiterDateOfBirth() {
        String invalidDelimiter = "2024!01!01";
        testDisasterVictim.setDateOfBirth(invalidDelimiter);        // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidFormatDateOfBirth() {
        String invalidFormat = "01/01/2024";
        testDisasterVictim.setDateOfBirth(invalidFormat);           // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetImpossibleDateOfBirth() {
        String impossibleDate = "2024/99/99";
        testDisasterVictim.setDateOfBirth(impossibleDate);          // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetFutureDateOfBirth() {
        String futureDate = "2025/01/01";
        testDisasterVictim.setDateOfBirth(futureDate);              // Expecting line to fail
    }

    @Test
    public void testSetAndGetValidApproximateAge() {
        testDisasterVictim.setApproximateAge(expectedValidApproximateAge);
        assertEquals("setApproximateAge should update the approximate age", expectedValidApproximateAge, testDisasterVictim.getApproximateAge());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNegativeApproximateAge() {
        int negativeApproximateAge = -15;
        testDisasterVictim.setApproximateAge(negativeApproximateAge);   // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetExtremelyUnlikelyApproximateAge() {
        int extremelyUnlikelyApproximateAge = 160;
        testDisasterVictim.setApproximateAge(extremelyUnlikelyApproximateAge);  // Expecting line to fail
    }

    /* REQ 8: Testing approximate age and birthday states */

    @Test(expected = IllegalStateException.class)
    public void testSetDateOfBirthWithApproximateAgeAlreadySet() {
        testDisasterVictim.setDateOfBirth(expectedValidDateOfBirth);
        testDisasterVictim.setApproximateAge(expectedValidApproximateAge);           // Expecting line to fail
    }

    @Test(expected = IllegalStateException.class)
    public void testSetApproximateAgeWithDateOfBirthAlreadySet() {
        testDisasterVictim.setApproximateAge(expectedValidApproximateAge);
        testDisasterVictim.setDateOfBirth(expectedValidDateOfBirth);                 // Expecting line to fail
    }

    /* End of REQ */

    @Test
    public void testSetAndGetComments() {
        String expectedComments = "I'm alright";
        testDisasterVictim.setComments(expectedComments);
        assertEquals("setComments should update the comments", expectedComments, testDisasterVictim.getComments());
    }

    @Test
    public void testSetAndGetFamilyConnections() {
        // TODO: Implement function. Plan FamilyRelation class
    }

    /* REQ 9: Testing GenderOptions.txt */

    @Test
    public void testSetAndGetValidGender() {
        testDisasterVictim.setGender(expectedValidGender);
        assertEquals("setGender should update the gender", expectedValidGender, testDisasterVictim.getGender());
    }

    @Test
    public void testSetAndGetUpperCaseGender() {
        testDisasterVictim.setGender(expectedValidGender.toUpperCase());
        assertEquals("setGender shouldn't care that it's upper-case", expectedValidGender.toLowerCase(), testDisasterVictim.getGender());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidGender() {
        testDisasterVictim.setGender(InvalidGender);                // Expecting line to fail
    }

    /* End of REQ */

    @Test
    public void testSetAndGetDietaryRestrictions() {
        // TODO: Implement function. Plan DietaryRestriction list checks
    }

    @Test
    public void testSetAndGetValidMedicalRecords() {
        MedicalRecord medicalRecord1 = new MedicalRecord(new Location("Location1", " Address 1"), "Bandage", "2024/01/01");
        MedicalRecord medicalRecord2 = new MedicalRecord(new Location("Location2", "Address 2"), "Eye-drop", "2023.01.01");

        ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();
        medicalRecords.add(medicalRecord1);
        medicalRecords.add(medicalRecord2);

        testDisasterVictim.setMedicalRecords(medicalRecords);

        assertEquals("SetMedicalRecords should update the medical records", medicalRecords, testDisasterVictim.getMedicalRecords());
    }

    @Test
    public void testSetEmptyMedicalRecords() {
        ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();

        testDisasterVictim.setMedicalRecords(medicalRecords);

        assertTrue("Medical records list should be empty", testDisasterVictim.getMedicalRecords().isEmpty());
    }

    @Test
    public void testSetAndGetPersonalBelongings() {
        Supply supply1 = new Supply("Supply1", 5);
        Supply supply2 = new Supply("Supply2", 5);

        ArrayList<Supply> supplies = new ArrayList<>();
        supplies.add(supply1);
        supplies.add(supply2);

        testDisasterVictim.setPersonalBelongings(supplies);

        assertEquals("setPersonalBelongings should update the personal belongings", supplies, testDisasterVictim.getPersonalBelongings());
    }

    @Test
    public void testSetEmptyPersonalBelongings() {
        ArrayList<Supply> supplies = new ArrayList<>();
        testDisasterVictim.setPersonalBelongings(supplies);

        assertTrue("Personal belongings list should be empty", testDisasterVictim.getPersonalBelongings().isEmpty());
    }

    /*-----------Testing Adders/Removers------------*/

    @Test
    public void testAddValidFamilyConnectionToEmpty() {
        FamilyRelation r1 = new FamilyRelation(x, "sibling", y);
        x.addFamilyConnection(r1);

        assertTrue("addFamilyConnection should add the relation", x.getFamilyConnections().contains(r1));
    }

    @Test
    public void testAddValidFamilyConnectionToPopulated() {
        FamilyRelation r1 = new FamilyRelation(x, "sibling", y);
        FamilyRelation r2 = new FamilyRelation(x, "sibling", z);

        x.addFamilyConnection(r1);
        x.addFamilyConnection(r2);

        // Creating a collection
        ArrayList<FamilyRelation> siblings = new ArrayList<>();
        siblings.add(r1);
        siblings.add(r2);

        assertTrue("addFamilyConnection should add the both relations", x.getFamilyConnections().containsAll(siblings));
    }

    @Test
    public void testAddDuplicateToSamePersonFamilyConnections() {
        // FamilyRelation already exists (duplicate test)
        FamilyRelation r1 = new FamilyRelation(x, "sibling", y);
        FamilyRelation r2 = new FamilyRelation(y, "sibling", x);        // Duplicate essentially

        x.addFamilyConnection(r1);
        x.addFamilyConnection(r2);

        assertEquals("addFamilyConnection should ignore the duplicate", 1, x.getFamilyConnections().size());
    }

    @Test
    public void testAddDuplicateToRelativeFamilyConnections() {
        FamilyRelation r1 = new FamilyRelation(x, "sibling", y);
        FamilyRelation r2 = new FamilyRelation(y, "sibling", x);

        x.addFamilyConnection(r1);
        y.addFamilyConnection(r2);

        assertFalse("addFamilyConnection for y should ignore the duplicate (r2)", y.getFamilyConnections().contains(r2));
    }

    @Test
    public void testRemoveNonExistentFamilyConnectionFromEmpty() {
        FamilyRelation r1 = new FamilyRelation(x, "sibling", y);
        x.removeFamilyConnection(r1);

        assertTrue("removeFamilyConnection should ignore non-existent relation", x.getFamilyConnections().isEmpty());
    }

    @Test
    public void testRemoveValidFamilyConnectionFromPopulated() {
        FamilyRelation r1 = new FamilyRelation(x, "sibling", y);
        FamilyRelation r2 = new FamilyRelation(x, "parent", z);

        x.addFamilyConnection(r1);
        x.addFamilyConnection(r2);

        x.removeFamilyConnection(r2);

        assertFalse("removeFamilyConnection should remove the relation", x.getFamilyConnections().contains(r2));
    }

    @Test
    public void testRemoveNonExistentFamilyConnectionFromPopulated() {
        FamilyRelation r1 = new FamilyRelation(x, "sibling", y);
        FamilyRelation r2 = new FamilyRelation(x, "parent", z);

        x.addFamilyConnection(r1);
        x.removeFamilyConnection(r2);

        assertEquals("removeFamilyConnection should ignore non-existent relation", 1, x.getFamilyConnections().size());
    }

    @Test
    public void testCompletionOfTwoSidedFamilyConnections() {
        // Symmetric Property
        // if x R y, then y R x
        FamilyRelation r1 = new FamilyRelation(x, "sibling", y);
        x.addFamilyConnection(r1);

        assertTrue("Relation r1 should be present in y", y.getFamilyConnections().contains(r1));
    }

    @Test
    public void testCompletionOfThreeSidedFamilyConnections() {
        // Transitive Property
        // If x R y and y R z, then x R z, where R => Relates to
        FamilyRelation r1 = new FamilyRelation(x, "sibling", y);
        FamilyRelation r2 = new FamilyRelation(y, "sibling", z);

        x.addFamilyConnection(r1);
        y.addFamilyConnection(r2);

        // Test that x relates to z
        assertTrue("z should relate to y", z.getFamilyConnections().contains(r2));
    }

    @Test
    public void testCompletionOfComplexFamilyConnections() {
        // A fringe example with 5 interconnected familyConnections
        // TODO: Implement function
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelfToSelfFamilyConnection() {
        // Reflexive Property
        // x R x should not be possible
        FamilyRelation r1 = new FamilyRelation(x, "sibling", x);

        x.addFamilyConnection(r1);                      // Expected to fail
    }

    @Test
    public void testDeletingFromOneSideOfTwoSidedFamilyConnections() {
        FamilyRelation r1 = new FamilyRelation(x, "sibling", y);

        x.addFamilyConnection(r1);

        y.removeFamilyConnection(r1);

        assertTrue("Removing relation from y should affect x", x.getFamilyConnections().isEmpty());
    }

    @Test
    public void testDeletingFromOneSideOfThreeSidedFamilyConnections() {
        // Transitive but x no longer relates to z.
        FamilyRelation r1 = new FamilyRelation(x, "sibling", y);
        FamilyRelation r2 = new FamilyRelation(y, "sibling", z);

        x.addFamilyConnection(r1);
        y.addFamilyConnection(r2);

        z.removeFamilyConnection(r2);

        // All connections should be gone
        assertFalse("Removing relation from z should break x <-> y relationship", x.getFamilyConnections().contains(r1));
    }

    @Test
    public void testDeletingFromOneSideOfComplexFamilyConnections() {
        // TODO: Implement function
    }

    @Test
    public void testInvalidThreeSidedFamilyConnections() {
        // If x R y but y notR z, then x notR z
        FamilyRelation r1 = new FamilyRelation(x, "sibling", y);
        FamilyRelation r2 = new FamilyRelation(y, "parent", z);

        x.addFamilyConnection(r1);
        y.addFamilyConnection(r2);

        assertEquals("x should not be related to z. I.e. it should have only one relationship", 1, x.getFamilyConnections().size());
    }

//    @Test
//    public void testAddPersonalBelonging() {
//        Supply supply = new Supply("Water", 10);
//        testDisasterVictim.addPersonalBelonging(supply);
//        assertEquals("addPersonalBelonging should add the personal belonging", supply, testDisasterVictim.getPersonalBelongings().get(0));
//    }
//
//    @Test
//    public void testAddAndRemovePersonalBelonging() {
//        Supply supply = new Supply("Water", 10);
//        testDisasterVictim.addPersonalBelonging(supply);
//        testDisasterVictim.removePersonalBelonging(supply);
//        assertEquals("removePersonalBelonging should remove the personal belonging", 0, testDisasterVictim.getPersonalBelongings().size());
//    }
//
//    @Test
//    public void testAddMedicalRecord() {
//        MedicalRecord medicalRecord = new MedicalRecord("Calgary", "Broken Leg", "2021-10-10");
//        testDisasterVictim.addMedicalRecord(medicalRecord);
//        assertEquals("addMedicalRecord should add the medical record", medicalRecord, testDisasterVictim.getMedicalRecords().get(0));
//    }
//
//    @Test
//    public void testAddAndRemoveMedicalRecord() {
//        MedicalRecord medicalRecord = new MedicalRecord("Calgary", "Broken Leg", "2021-10-10");
//        testDisasterVictim.addMedicalRecord(medicalRecord);
//        testDisasterVictim.removeMedicalRecord(medicalRecord);
//        assertEquals("removeMedicalRecord should remove the medical record", 0, testDisasterVictim.getMedicalRecords().size());
//    }
}