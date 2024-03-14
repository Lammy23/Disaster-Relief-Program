package edu.ucalgary.oop;


import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

/*
- name: String
- address: String
- occupants: ArrayList<Person>

+ Location(name:String, address:String)

+ getName(): String
+ getAddress(): String
+ getOccupants(): ArrayList<Person>

+ setName(name: String): void
+ setAddress(address: String): void
+ setOccupants(occupants: ArrayList<Person>): void

+ addOccupant(occupant:DisasterVictim)
+ removeOccupant(occupant: DisasterVictim)

 */
public class LocationTest {

    private Location testLocation;
    private String expectedName = "ShelterA";
    private String expectedAddress = "140 8 Ave NW";

    public LocationTest() {
    }

    @Before
    public void setUp() {
        testLocation = new Location(expectedName, expectedAddress);
    }

    @Test
    public void testObjectCreation() {
        assertNotNull(testLocation);
    }

    @Test
    public void testConstructorName() {
        assertEquals("Constructor should set the correct name", expectedName, testLocation.getName());
    }

    @Test
    public void testConstructorAddress() {
        assertEquals("Constructor should set the correct address", expectedAddress, testLocation.getAddress());
    }

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
        Person p1 = new Person("John");
        Person p2 = new Person("Jane");
        ArrayList<Person> occupants = new ArrayList<Person>(p1, p2);
        testLocation.setOccupants(occupants);
        assertEquals("setOccupants should update the occupants", occupants, testLocation.getOccupants());
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