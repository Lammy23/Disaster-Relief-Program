package edu.ucalgary.oop;

import org.junit.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;

public class DisasterVictimTest {

    private DisasterVictim testDisasterVictim;
    private final String expectedFirstName = "John";
    private final String expectedValidEntryDate = "2024/01/01";
    private final String expectedValidDateOfBirth = "2024-01-01";
    private final int expectedValidApproximateAge = 25;
    private final String expectedValidGender = getValidGender();
    private final String InvalidGender = getInvalidGender();


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
        DisasterVictim testDisasterVictim = new DisasterVictim(expectedFirstName, invalidDelimiter); // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidFormatEntryDate() {
        String invalidFormat = "01-01-2024";
        DisasterVictim testDisasterVictim = new DisasterVictim(expectedFirstName, invalidFormat);   // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorImpossibleEntryDate() {
        String impossibleDate = "2024-99-99";
        DisasterVictim testDisasterVictim = new DisasterVictim(expectedFirstName, impossibleDate);  // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorFutureEntryDate() {
        String futureDate = "2025-01-01";
        DisasterVictim testDisasterVictim = new DisasterVictim(expectedFirstName, futureDate);      // Expecting line to fail
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
    public void testSetAndGetMedicalRecords() {
        MedicalRecord medicalRecord1 = new MedicalRecord(new Location())
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
