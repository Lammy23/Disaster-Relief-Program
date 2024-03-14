package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*
- occupants: ArrayList<DisasterVictim>
- supplies: ArrayList<Supply>

+ Shelter(name:String, address:String)

+ getSupplies(): ArrayList<Supply>
+ setSupplies(supplies: ArrayList<Supply>): void

+ addSupply(supply: Supply): void
+ removeSupply(supply: Supply): void
 */

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

}
