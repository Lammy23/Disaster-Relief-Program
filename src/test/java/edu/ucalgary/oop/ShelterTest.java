package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ShelterTest {

    private String expectedName = "ShelterA";
    private String expectedAddress = "140 8 Ave NW";
    private Shelter testShelter;

    public ShelterTest() {
    }

    @Before
    public void setUp() {
        testShelter = new Shelter(expectedName, expectedAddress);
    }

    @Test
    public void testObjectCreation() {
        assertNotNull(testShelter);
    }

    @Test
    public void testConstructorName() {
        assertEquals("Shelter should have the correct name", expectedName, testShelter.getName());
    }

    @Test
    public void testConstructorAddress() {
        assertEquals("Shelter should have the correct address", expectedAddress, testShelter.getAddress());
    }

    @Test
    public void testSetAndGetSupplies() {
        ArrayList<Supply> expectedSupplies = new ArrayList<Supply>();
        expectedSupplies.add(new Supply("Water", 100));
        expectedSupplies.add(new Supply("Food", 200));
        testShelter.setSupplies(expectedSupplies);
        assertEquals("getSupplies should return the correct supplies", expectedSupplies, testShelter.getSupplies());
    }

    @Test
    public void testGetAndSetOccupants() {
        ArrayList<DisasterVictim> expectedOccupants = new ArrayList<DisasterVictim>();
        expectedOccupants.add(new DisasterVictim("John", "2024-01-01"));
        expectedOccupants.add(new DisasterVictim("Jane", "2024-01-02"));
        testShelter.setOccupants(expectedOccupants);
        assertEquals("getOccupants should return the correct occupants", expectedOccupants, testShelter.getOccupants());
    }

    @Test
    public void testAddSupply() {
        ArrayList<Supply> expectedSupplies = new ArrayList<Supply>();
        expectedSupplies.add(new Supply("Water", 100));
        expectedSupplies.add(new Supply("Food", 200));
        testShelter.setSupplies(expectedSupplies);
        testShelter.addSupply(new Supply("Blanket", 50));
        assertEquals("addSupply should add a supply to the list of supplies", 3, testShelter.getSupplies().size());
    }

    @Test
    public void testRemoveSupply() {
        ArrayList<Supply> expectedSupplies = new ArrayList<Supply>();
        expectedSupplies.add(new Supply("Water", 100));
        expectedSupplies.add(new Supply("Food", 200));
        testShelter.setSupplies(expectedSupplies);
        testShelter.removeSupply(new Supply("Water", 100));
        assertEquals("removeSupply should remove a supply from the list of supplies", 1, testShelter.getSupplies().size());
    }

    @Test
    public void testRemoveSupplyWithNonExistentSupply() {
        ArrayList<Supply> expectedSupplies = new ArrayList<Supply>();
        expectedSupplies.add(new Supply("Water", 100));
        expectedSupplies.add(new Supply("Food", 200));
        testShelter.setSupplies(expectedSupplies);
        testShelter.removeSupply(new Supply("Blanket", 50));
        assertEquals("removeSupply should not remove a supply from the list of supplies if it does not exist", 2, testShelter.getSupplies().size());
    }

    @Test
    public void testRemoveSupplyWithEmptySupplies() {
        testShelter.setSupplies(new ArrayList<Supply>());
        testShelter.removeSupply(new Supply("Blanket", 50));
        assertEquals("removeSupply should not remove a supply from the list of supplies if it is empty", 0, testShelter.getSupplies().size());
    }
}
