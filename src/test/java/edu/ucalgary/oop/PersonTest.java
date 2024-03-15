/* Olamikun Aluko */

package edu.ucalgary.oop;

import org.junit.*;

import static org.junit.Assert.*;

/*- firstName: String
- lastName: String
- dateOfBirth: String
- comments: String
- family: Family
- gender: Gender
- dietaryRestrictions: DietaryRestrictions

+ Person(firstName: String)
+ Person(firstName: String, lastName: String)

+ parseDate(date: String, for: String): String

+ getFirstName(): String
+ getLastName(): String
+ getDateOfBirth(): String
+ getComments(): String
+ getFamily(): Family
+ getParent(): Person
+ getGender(): Gender
+ getDietaryRestrictions(): DietaryRestrictions

+ setFirstName(firstName: String): void
+ setLastName(lastName: String): void
+ setDateOfBirth(dateOfBirth: String): void
+ setComments(comments: String): void
+ setDietaryRestrictions(restrictions: DietaryRestrictions): void

+ setFamily(family: Family): void
+ setGender(gender: String): void

+ addFamiIyConnection(famiIyConnection: FamilyReIation): void
+ removeFamilyConnection(familyConnection: FamilyRelation): void

 */
public class PersonTest {

    private Person testPerson;
    private String expectedFirstName = "John";
    private String expectedLastName = "Doe";

    public PersonTest() {

    }

    @Before
    public void setUp() {
        testPerson = new Person(expectedFirstName, expectedLastName);
    }

    @Test
    public void testObjectCreation() {
        assertNotNull(testPerson);
    }

    @Test
    public void testConstructorFirstName() {
        assertEquals("Constructor should set the first name", expectedFirstName, testPerson.getFirstName());
    }

    @Test
    public void testConstructorLastName() {
        assertEquals("Constructor should set the last name", expectedLastName, testPerson.getLastName());
    }

    @Test
    public void testGetFirstName() {
        assertEquals("getFirstName should return the correct first name", expectedFirstName, testPerson.getFirstName());
    }

    @Test
    public void testSetFirstName() {
        String newFirstName = "Jane";
        testPerson.setFirstName(newFirstName);
        assertEquals("setFirstName should update the first name", newFirstName, testPerson.getFirstName());
    }

    @Test
    public void testSetAndGetLastName() {
        String newLastName = "Smith";
        testPerson.setLastName(newLastName);
        assertEquals("setLastName should update the last name", newLastName, testPerson.getLastName());
    }

    @Test
    public void testSetAndGetDateOfBirth() {
        String newDateOfBirth = "2021-10-10";
        testPerson.setDateOfBirth(newDateOfBirth);
        assertEquals("setDateOfBirth should update the date of birth", newDateOfBirth, testPerson.getDateOfBirth());
    }

    @Test
    public void testSetAndGetComments() {
        String newComments = "This is a test comment";
        testPerson.setComments(newComments);
        assertEquals("setComments should update the comments", newComments, testPerson.getComments());
    }

    @Test
    public void testSetAndGetFamily() {
        Family newFamily = new Family();
        testPerson.setFamily(newFamily);
        assertEquals("setFamily should update the family", newFamily, testPerson.getFamily());
    }

    @Test

    @Test
    public void testSetAndGetGender() {
        /* TODO: Implement testSetAndGetGender */

    }

    @Test
    public void testSetAndGetDietaryRestrictions() {
        /* TODO: Implement testSetAndGetDietaryRestrictions */
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseDateForDateOfBirthInvalidLogic() {
        /* date cannot be in the future */
        /* Throws an exception if date of birth is in the future */

        /* Generate future date */
        LocalDate futureDate = LocalDate.now().plusDays(1);

        /* Convert future date to string in the form yyyy-mm-dd */
        String futureDateOfBirth = futureDate.toString();

        testPerson.setDateOfBirth(futureDateOfBirth);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseDateForDateOfBirthInvalidFormat() {
        /* Does not parse dd-mm-yyyy format */
        /* Throws an exception if date is not in yyyy-mm-dd format */

        String invalidDateOfBirth = "11-10-2021";
        testPerson.setDateOfBirth(invalidDateOfBirth);
    }


    @Test
    public void testSiblingsLoseSameParent() {
        Person john = new Person("John");
        Person jane = new Person("Jane");

        /* Create a family */
        Family smithFamily = new Family("Smith", new ArrayList<Person>(john, jane), new ArrayList<Person>());

        /* Set the family for both siblings */
        john.setFamily(smithFamily);
        jane.setFamily(smithFamily);

        /* Remove a parent from the family */
        smithFamily.removeParent(john);

        /* Check if both siblings have the same family */
        assertEquals("Both siblings should have the same family", john.getFamily(), jane.getFamily());
    }

}
