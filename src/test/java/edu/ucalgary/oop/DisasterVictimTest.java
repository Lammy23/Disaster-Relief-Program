package edu.ucalgary.oop;

import org.junit.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.*;

public class DisasterVictimTest {

    private DisasterVictim testDisasterVictim;
    private DisasterVictim testDisasterVictimTwo;
    private final String expectedFirstName = "John";
    private final String expectedValidEntryDate = "2024/01/01";
    private final String expectedValidDateOfBirth = "2024-01-01";
    private final int expectedValidApproximateAge = 25;

    private final HashSet<String> validGenders = ApplicationUtils.getValidGenders();
    private final String expectedValidGender = getRandomValidGender();
    private final String expectedInvalidGender = generateInvalidGender();

    private Location expectedLocation;


    private DisasterVictim x;
    private DisasterVictim y;
    private DisasterVictim z;

    /**
     * Gets a random valid gender from the set of valid genders
     *
     * @return a random valid gender
     */
    private String getRandomValidGender() {
        // Gets random valid gender from valid genders

        // Get random gender from the set
        int randomIndex = (int) (Math.random() * validGenders.size());
        return (String) validGenders.toArray()[randomIndex];
    }

    /**
     * Generates a `String` that is not in the set of valid genders
     *
     * @return an invalid gender
     */
    private String generateInvalidGender() {
        // Generates a String that is definitely not a gender
        StringBuilder invalidGender = new StringBuilder();

        // Loop through genders and get first character of each
        for (String validGender : validGenders) {
            char firstChar = validGender.charAt(0);

            // Incrementing ASCII value of first character
            char invalidChar = (char) (firstChar + 1);
            invalidGender.append(invalidChar);
        }

        return invalidGender.toString();
    }

    @Before
    public void setUp() {
        testDisasterVictim = new DisasterVictim(expectedFirstName, expectedValidEntryDate);
        testDisasterVictimTwo = new DisasterVictim(expectedValidEntryDate);

        // Testing Relationships
        x = new DisasterVictim("John", "2024/01/01");
        y = new DisasterVictim("Bobby", "2024/01/01");
        z = new DisasterVictim("Mack", "2024/01/01");

        expectedLocation = new Location("Location", "Address");
    }

    @Test
    public void testObjectCreation() {
        assertNotNull(testDisasterVictim);
    }

    /*-----------Testing Constructor-----------*/

    @Test
    public void testConstructorOneValidEntryDate() {
        assertEquals("Constructor should set the correct entry date", ApplicationUtils.parseDate(expectedValidEntryDate), testDisasterVictimTwo.getEntryDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorOneInvalidDelimiterEntryDate() {
        String invalidDelimiter = "2024!01!01";
        new DisasterVictim(invalidDelimiter); // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorOneInvalidFormatEntryDate() {
        String invalidFormat = "01.01.2024";
        new DisasterVictim(invalidFormat);   // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorOneImpossibleEntryDate() {
        String impossibleDate = "2024.99.99";
        new DisasterVictim(impossibleDate);  // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorOneFutureEntryDate() {
        String futureDate = "2025.01.01";
        new DisasterVictim(futureDate);      // Expecting line to fail
    }

    @Test
    public void testConstructorTwoFirstName() {
        assertEquals("Constructor should set the correct first name", expectedFirstName, testDisasterVictim.getFirstName());
    }

    @Test
    public void testConstructorTwoValidEntryDate() {
        assertEquals("Constructor should set the correct entry date", ApplicationUtils.parseDate(expectedValidEntryDate), testDisasterVictim.getEntryDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorTwoInvalidDelimiterEntryDate() {
        String invalidDelimiter = "2024*01*01";
        new DisasterVictim(expectedFirstName, invalidDelimiter); // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorTwoInvalidFormatEntryDate() {
        String invalidFormat = "01-01-2024";
        new DisasterVictim(expectedFirstName, invalidFormat);   // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorTwoImpossibleEntryDate() {
        String impossibleDate = "2024-99-99";
        new DisasterVictim(expectedFirstName, impossibleDate);  // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorTwoFutureEntryDate() {
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


    @Test
    public void testSetAndGetComments() {
        String expectedComments = "I'm alright";
        testDisasterVictim.setComments(expectedComments);
        assertEquals("setComments should update the comments", expectedComments, testDisasterVictim.getComments());
    }

    @Test
    public void testSetAndGetFamilyConnections() {
        FamilyRelation r1 = new FamilyRelation(x, "sibling", y);
        FamilyRelation r2 = new FamilyRelation(x, "sibling", z);

        HashSet<FamilyRelation> siblings = new HashSet<>();
        siblings.add(r1);
        siblings.add(r2);

        testDisasterVictim.setFamilyConnections(siblings);

        assertEquals("setFamilyConnections should update the family connections", siblings, testDisasterVictim.getFamilyConnections());
    }

    @Test
    public void testSetAndGetValidGender() {
        testDisasterVictim.setGender(expectedValidGender);
        assertEquals("setGender should update the gender", expectedValidGender.toLowerCase(), testDisasterVictim.getGender());
    }

    @Test
    public void testSetAndGetUpperCaseGender() {
        testDisasterVictim.setGender(expectedValidGender.toUpperCase());
        assertEquals("setGender shouldn't care that it's upper-case", expectedValidGender.toLowerCase(), testDisasterVictim.getGender());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidGender() {
        testDisasterVictim.setGender(expectedInvalidGender);                // Expecting line to fail
    }

    @Test
    public void testSetAndGetDietaryRestrictions() {
        DietaryRestriction d1 = DietaryRestriction.AVML;
        DietaryRestriction d2 = DietaryRestriction.DBML;

        HashSet<DietaryRestriction> dietaryRestrictions = new HashSet<>();
        dietaryRestrictions.add(d1);
        dietaryRestrictions.add(d2);

        testDisasterVictim.setDietaryRestrictions(dietaryRestrictions);
        assertEquals("setDietaryRestrictions should update the dietary restrictions", dietaryRestrictions, testDisasterVictim.getDietaryRestrictions());
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
        Supply supply1 = new Supply("Supply1", 5, expectedLocation);
        Supply supply2 = new Supply("Supply2", 5, expectedLocation);

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
    public void testAddValidSiblingToEmpty() {
        FamilyRelation r1 = new FamilyRelation(x, "sibling", y);
        x.addFamilyConnection(r1);

        assertTrue("addFamilyConnection should add the relation", x.familyConnectionAlreadyExists(r1, "sibling"));
    }

    @Test
    public void testAddValidSiblingToPopulated() {
        FamilyRelation r1 = new FamilyRelation(x, "sibling", y);
        FamilyRelation r2 = new FamilyRelation(x, "sibling", z);

        x.addFamilyConnection(r1);
        x.addFamilyConnection(r2);

        // Creating a collection
        ArrayList<FamilyRelation> siblings = new ArrayList<>();
        siblings.add(r1);
        siblings.add(r2);

        assertTrue("addFamilyConnection should add both the relations",
                x.familyConnectionAlreadyExists(r1, "sibling") && x.familyConnectionAlreadyExists(r2, "sibling"
                ));
    }

    @Test
    public void testAddDuplicateSiblingToSamePerson() {
        // Sibling already exists (duplicate test)
        FamilyRelation r1 = new FamilyRelation(x, "sibling", y);
        FamilyRelation r2 = new FamilyRelation(y, "sibling", x);        // Duplicate essentially

        x.addFamilyConnection(r1);
        x.addFamilyConnection(r2);

        assertEquals("addFamilyConnection should ignore the duplicate", 1, x.getFamilyConnections().size());
    }


    @Test
    public void testRemoveNonExistentSiblingFromEmpty() {
        FamilyRelation r1 = new FamilyRelation(x, "sibling", y);
        x.removeFamilyConnection(r1);

        assertTrue("removeFamilyConnection should ignore non-existent relation", x.getFamilyConnections().isEmpty());
    }

    @Test
    public void testRemoveValidSiblingFromPopulated() {
        FamilyRelation r1 = new FamilyRelation(x, "sibling", y);
        FamilyRelation r2 = new FamilyRelation(x, "sibling", z);

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
    public void testCompletionOfTwoSidedSiblings() {
        // Symmetric Property
        // if x R y, then y R x
        FamilyRelation r1 = new FamilyRelation(x, "sibling", y);
        x.addFamilyConnection(r1);

        assertTrue("Relation r1 should be present in y", y.familyConnectionAlreadyExists(r1, "sibling"));
    }

    @Test
    public void testCompletionOfThreeSidedSiblings() {
        // Transitive Property
        // If x R y and y R z, then x R z, where R => Relates to
        FamilyRelation r1 = new FamilyRelation(x, "sibling", y);
        FamilyRelation r2 = new FamilyRelation(y, "sibling", z);

        x.addFamilyConnection(r1);
        y.addFamilyConnection(r2);

        // Test that x relates to z
        assertTrue("z should relate to y", z.familyConnectionAlreadyExists(r2, "sibling"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNonRelatedSibling() {
        // Sibling relation that has nothing to do with the DisasterVictim
        FamilyRelation r2 = new FamilyRelation(y, "sibling", z);

        x.addFamilyConnection(r2);                      // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonRelatedSibling() {
        // Sibling relation that has nothing to do with the DisasterVictim
        FamilyRelation r2 = new FamilyRelation(y, "sibling", z);

        x.removeFamilyConnection(r2);                   // Expecting line to fail
    }

    @Test
    public void testCompletionOfComplexFamilyConnections() {
        // A fringe example with 5 interconnected familyConnections
        DisasterVictim a = new DisasterVictim("A", "2024/01/01");
        DisasterVictim b = new DisasterVictim("B", "2024/01/01");
        DisasterVictim c = new DisasterVictim("C", "2024/01/01");

        FamilyRelation r1 = new FamilyRelation(a, "sibling", b);
        FamilyRelation r2 = new FamilyRelation(b, "sibling", c);
        FamilyRelation r3 = new FamilyRelation(c, "sibling", x);
        FamilyRelation r4 = new FamilyRelation(x, "sibling", y);
        FamilyRelation r5 = new FamilyRelation(y, "sibling", z);

        // Adding all relations
        a.addFamilyConnection(r1);
        b.addFamilyConnection(r2);
        c.addFamilyConnection(r3);
        x.addFamilyConnection(r4);
        y.addFamilyConnection(r5);

        // Testing that one of the relations is present
        assertTrue("a should relate to z", a.familyConnectionAlreadyExists(new FamilyRelation(a, "sibling", z), "sibling"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelfToSelfSibling() {
        // Reflexive Property
        // x R x should not be possible
        FamilyRelation r1 = new FamilyRelation(x, "sibling", x);

        x.addFamilyConnection(r1);                      // Expected to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelfToSelfParent() {
        // Reflexive Property
        // x R x should not be possible
        FamilyRelation r1 = new FamilyRelation(x, "parent", x);

        x.addFamilyConnection(r1);                      // Expected to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelfToSelfChild() {
        // Reflexive Property
        // x R x should not be possible
        FamilyRelation r1 = new FamilyRelation(x, "child", x);

        x.addFamilyConnection(r1);                      // Expected to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelfToSelfSpouse() {
        // Reflexive Property
        // x R x should not be possible
        FamilyRelation r1 = new FamilyRelation(x, "spouse", x);

        x.addFamilyConnection(r1);                      // Expected to fail
    }

    @Test
    public void testDeletingFromOneSideOfTwoSidedSiblings() {
        FamilyRelation r1 = new FamilyRelation(x, "sibling", y);

        x.addFamilyConnection(r1);

        y.removeFamilyConnection(r1);

        assertEquals("Removing relation from y should affect x", 0, x.getFamilyConnections().size());
    }

    @Test
    public void testDeletingFromOneSideOfTwoSidedParentChild() {
        FamilyRelation r1 = new FamilyRelation(x, "parent", y);
        FamilyRelation r2 = new FamilyRelation(y, "child", x);

        x.addFamilyConnection(r1);
        y.removeFamilyConnection(r2);

        assertEquals("Removing relation from y should affect x", 0, x.getFamilyConnections().size());
    }

    @Test
    public void testDeletingFromOneSideOfTwoSidedSpouses() {
        FamilyRelation r1 = new FamilyRelation(x, "spouse", y);
        FamilyRelation r2 = new FamilyRelation(y, "spouse", x);

        x.addFamilyConnection(r1);
        y.removeFamilyConnection(r2);

        assertEquals("Removing relation from y should affect x", 0, x.getFamilyConnections().size());
    }

    @Test
    public void testDeletingFromOneSideOfThreeSidedSiblings() {
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
    public void testNonTransitiveThreeSidedFamilyConnection() {
        // If x R y but y notR z, then x notR z
        FamilyRelation r1 = new FamilyRelation(x, "sibling", y);
        FamilyRelation r2 = new FamilyRelation(y, "parent", z);

        x.addFamilyConnection(r1);
        y.addFamilyConnection(r2);

        assertEquals("x should not be related to z. I.e. it should have only one relationship", 1, x.getFamilyConnections().size());
    }

    @Test
    public void testAddOnlyPersonalBelongingToEmpty() {
        Supply supply = new Supply("Water", 1, expectedLocation);
        // Ensuring Location has supply
        expectedLocation.addSupply(supply);

        testDisasterVictim.addPersonalBelonging(supply);
        assertTrue("addPersonalBelonging should add the personal belonging", testDisasterVictim.getPersonalBelongings().contains(supply));
    }

    @Test
    public void testSourceSupplyEffect() {
        Supply supply = new Supply("Water", 1, expectedLocation);
        // Ensuring Location has supply
        expectedLocation.addSupply(supply);

        testDisasterVictim.addPersonalBelonging(supply);
        assertFalse("Source of supply should lose all stock", expectedLocation.getSupplies().contains(supply));
    }

    @Test
    public void testAddAllPersonalBelongingsToEmpty() {
        Supply supply1 = new Supply("Water", 10, expectedLocation);
        Supply supply2 = new Supply("Food", 5, expectedLocation);
        // Ensuring Location has supply
        expectedLocation.addSupply(supply1);
        expectedLocation.addSupply(supply2);

        testDisasterVictim.addPersonalBelonging(supply1);
        testDisasterVictim.addPersonalBelonging(supply2);
        assertTrue("addPersonalBelonging should add the personal belongings", testDisasterVictim.getPersonalBelongings().contains(supply1) && testDisasterVictim.getPersonalBelongings().contains(supply2));
    }

    @Test
    public void testAddDuplicatePersonalBelonging() {

        new Supply("Water", 10, expectedLocation);
        Supply supply1 = new Supply("Water", 1, expectedLocation);
        Supply supply5 = new Supply("Water", 5, expectedLocation);

        testDisasterVictim.addPersonalBelonging(supply1);
        testDisasterVictim.addPersonalBelonging(supply5);

        // Finding the supply in victim
        Optional<Supply> existingSupply = testDisasterVictim.getPersonalBelongings().stream().filter(supply ->
                supply.getType().equals("Water")
        ).findFirst();

        if (existingSupply.isPresent()) {
            Supply targetSupply = existingSupply.get();
            // Checking if the quantity is 6
            assertEquals("addPersonalBelonging should add the personal belongings in the right quantity", 6, targetSupply.getQuantity());
        } else {
            fail("Supply not found in personal belongings list");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNonExistentPersonalBelonging() {
        Supply nonExistentSupply = new Supply("Food", 5, expectedLocation);

        // Remove supply
        expectedLocation.removeSupply(nonExistentSupply);

        // Adding non-existent supply
        testDisasterVictim.addPersonalBelonging(nonExistentSupply);                             // Expecting line to fail
    }

    @Test
    public void testRemovePersonalBelonging() {
        Supply supply = new Supply("Water", 1, expectedLocation);

        testDisasterVictim.addPersonalBelonging(supply);
        testDisasterVictim.removePersonalBelonging(supply);
        assertFalse("removePersonalBelonging should remove the personal belonging", testDisasterVictim.getPersonalBelongings().contains(supply));
    }

    @Test
    public void testRemoveNonExistentPersonalBelonging() {

        Supply supply = new Supply("Water", 1, expectedLocation);
        Supply nonExistentSupply = new Supply("Food", 5, expectedLocation);

        testDisasterVictim.addPersonalBelonging(supply);
        testDisasterVictim.removePersonalBelonging(nonExistentSupply);
        assertEquals("removePersonalBelonging should ignore non-existent personal belonging", 1, testDisasterVictim.getPersonalBelongings().size());
    }

    @Test
    public void testRemoveAllPersonalBelongings() {
        Supply supply1 = new Supply("Water", 10, expectedLocation);
        Supply supply2 = new Supply("Food", 5, expectedLocation);

        testDisasterVictim.addPersonalBelonging(supply1);
        testDisasterVictim.addPersonalBelonging(supply2);

        testDisasterVictim.removePersonalBelonging(supply1);
        testDisasterVictim.removePersonalBelonging(supply2);

        assertTrue("removePersonalBelonging should remove all personal belongings", testDisasterVictim.getPersonalBelongings().isEmpty());
    }

    @Test
    public void testAddMedicalRecord() {
        MedicalRecord medicalRecord = new MedicalRecord(expectedLocation, "Broken Leg", "2021-01-01");
        testDisasterVictim.addMedicalRecord(medicalRecord);

        assertTrue("addMedicalRecord should add the medical record", testDisasterVictim.getMedicalRecords().contains(medicalRecord));
    }

    @Test
    public void testAddDuplicateMedicalRecord() {
        MedicalRecord medicalRecord1 = new MedicalRecord(expectedLocation, "Broken Leg", "2021-01-01");
        MedicalRecord medicalRecord2 = new MedicalRecord(expectedLocation, "Broken Arm", "2022-01-01");
        testDisasterVictim.addMedicalRecord(medicalRecord1);
        testDisasterVictim.addMedicalRecord(medicalRecord2);

        assertTrue("addMedicalRecord should add the medical records",
                testDisasterVictim.getMedicalRecords().contains(medicalRecord1) &&
                        testDisasterVictim.getMedicalRecords().contains(medicalRecord2)
        );

    }

    @Test
    public void testRemoveMedicalRecord() {
        MedicalRecord medicalRecord = new MedicalRecord(expectedLocation, "Broken Leg", "2021-01-01");
        testDisasterVictim.addMedicalRecord(medicalRecord);
        testDisasterVictim.removeMedicalRecord(medicalRecord);

        assertFalse("removeMedicalRecord should remove the medical record", testDisasterVictim.getMedicalRecords().contains(medicalRecord));
    }

    @Test
    public void testRemoveNonExistentMedicalRecord() {
        MedicalRecord medicalRecord = new MedicalRecord(expectedLocation, "Broken Leg", "2021-01-01");
        MedicalRecord nonExistentMedicalRecord = new MedicalRecord(expectedLocation, "Broken Arm", "2022-01-01");
        testDisasterVictim.addMedicalRecord(medicalRecord);
        testDisasterVictim.removeMedicalRecord(nonExistentMedicalRecord);

        assertEquals("removeMedicalRecord should ignore non-existent medical record", 1, testDisasterVictim.getMedicalRecords().size());

    }
}