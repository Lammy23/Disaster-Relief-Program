/* Olamikun Aluko */

package edu.ucalgary.oop;

import org.junit.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class PersonTest {

    private Person testPerson;
    private String expectedFirstName = "John";
    private String expectedLastName = "Doe";

    public PersonTest() {

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
    public void testSetAndGetGender() {
        /* Check that gender corresponds with at least one of the genders found in genderOptions.txt */

        testPerson.setGender("Boy");

        BufferedReader inputStream;
        boolean genderFound = false;
        try {
            inputStream = new BufferedReader(new FileReader("genderOptions.txt"));

            String line;
            /* Read the file line by line */
            while ((line = inputStream.readLine()) != null) {
                if (line.equals(testPerson.getGender())) {
                    genderFound = true;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
        assertTrue("Invalid gender", genderFound);
    }

    @Test
    public void testSetAndGetDietaryRestrictions() {
        testPerson.setDietaryRestrictions(DietaryRestrictions.AVML);
        assertEquals("setDietaryRestrictions should update the dietary restrictions", DietaryRestrictions.AVML, testPerson.getDietaryRestrictions());
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
