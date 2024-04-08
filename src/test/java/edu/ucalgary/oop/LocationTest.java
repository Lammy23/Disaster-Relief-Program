package edu.ucalgary.oop;

import org.junit.*;

import static org.junit.Assert.*;

public class LocationTest {

    private Location testLocation;
    private final String expectedName = "ShelterA";
    private final String expectedAddress = "140 8 Ave NW";

    // Testing occupants
    private final DisasterVictim x = new DisasterVictim("John", "2024/01/01");
    private final DisasterVictim y = new DisasterVictim("Bob", "2024/01/01");
    private final DisasterVictim z = new DisasterVictim("Mack", "2024/01/01");

    @Before
    public void setUp() {
        testLocation = new Location(expectedName, expectedAddress);
    }

    @Test
    public void testObjectCreation() {
        assertNotNull(testLocation);
    }

    /*--------Testing Constructor------------*/

    @Test
    public void testConstructorName() {
        assertEquals("Constructor should set the correct name", expectedName, testLocation.getName());
    }

    @Test
    public void testConstructorAddress() {
        assertEquals("Constructor should set the correct address", expectedAddress, testLocation.getAddress());
    }

    /*--------------Testing Getters/Setters------------*/

    @Test
    public void testSetAndGetName() {
        String newName = "ShelterB";
        testLocation.setName(newName);
        assertEquals("setName should update the name", newName, testLocation.getName());
    }

    @Test
    public void testSetAndGetAddress() {
        String newAddress = "150 8 Ave NW";
        testLocation.setAddress(newAddress);
        assertEquals("setAddress should update the address", newAddress, testLocation.getAddress());
    }

    @Test
    public void testSetAndGetOccupants() {

    }

    @Test
    public void testAddOccupant() {
        Person p1 = new Person("John");
        testLocation.addOccupant(p1);
        assertEquals("addOccupant should add the occupant", p1, testLocation.getOccupants().get(0));
    }

    @Test
    public void testRemoveOccupant() {
        Person p1 = new Person("John");
        testLocation.addOccupant(p1);
        testLocation.removeOccupant(p1);
        assertEquals("removeOccupant should remove the occupant", 0, testLocation.getOccupants().size());
    }

    @Test
    public void testRemoveOccupantWithMultipleOccupants() {
        Person p1 = new Person("John");
        Person p2 = new Person("Jane");
        testLocation.addOccupant(p1);
        testLocation.addOccupant(p2);
        testLocation.removeOccupant(p1);
        assertEquals("removeOccupant should remove the correct occupant", p2, testLocation.getOccupants().get(0));
    }

    @Test
    public void testRemoveOccupantWithNonExistentOccupant() {
        Person p1 = new Person("John");
        Person p2 = new Person("Jane");
        testLocation.addOccupant(p1);
        testLocation.removeOccupant(p2);
        assertEquals("removeOccupant should not remove any occupants", 1, testLocation.getOccupants().size());
    }
}