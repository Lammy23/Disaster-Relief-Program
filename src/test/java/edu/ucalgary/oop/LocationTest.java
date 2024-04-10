package edu.ucalgary.oop;

import org.junit.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.*;

public class LocationTest {

    private Location testLocation;
    private final String expectedName = "ShelterA";
    private final String expectedAddress = "140 8 Ave NW";

    // Testing occupants
    private final DisasterVictim x = new DisasterVictim("John", "2024/01/01");
    private final DisasterVictim y = new DisasterVictim("Bob", "2024/01/01");
    private final DisasterVictim z = new DisasterVictim("Mack", "2024/01/01");
    private int expectedSupplySize;
    // Testing supplies
    private Supply a;
    private Supply b;
    private Supply c;


    @Before
    public void setUp() {
        testLocation = new Location(expectedName, expectedAddress);

        // Testing supplies
        a = new Supply("Water", 1, testLocation);
        b = new Supply("Food", 1, testLocation);
        c = new Supply("Blanket", 1, testLocation);

        expectedSupplySize = 3;
    }

    @Test
    public void testObjectCreation() {
        assertNotNull(testLocation);
    }

    // TODO: Remove multiple assert statements from test. Only one assert per test is allowed (basically).

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
        ArrayList<DisasterVictim> expectedOccupants = new ArrayList<>();
        expectedOccupants.add(x);
        expectedOccupants.add(y);
        expectedOccupants.add(z);

        testLocation.setOccupants(expectedOccupants);
        assertEquals("setOccupants should update the occupants list", expectedOccupants, testLocation.getOccupants());
    }

    @Test
    public void testSetAndGetSupplies() {
        HashSet<Supply> expectedSupplies = new HashSet<>();
        expectedSupplies.add(a);
        expectedSupplies.add(b);
        expectedSupplies.add(c);

        testLocation.setSupplies(expectedSupplies);
        assertEquals("setSupplies should update the supplies list", expectedSupplies, testLocation.getSupplies());
    }

    /*--------------Testing Adders/Removers------------*/

    @Test
    public void testAddOneOccupantToEmpty() {
        testLocation.addOccupant(x);
        assertTrue("addOccupant should add the occupant", testLocation.getOccupants().contains(x));
    }

    @Test
    public void testAddMultipleOccupants() {
        testLocation.addOccupant(x);
        testLocation.addOccupant(y);
        testLocation.addOccupant(z);

        assertEquals("addOccupant should add all three occupants", 3, testLocation.getOccupants().size());
    }

    @Test
    public void testAddDuplicateOccupants() {
        testLocation.addOccupant(x);
        testLocation.addOccupant(x);

        assertEquals("addOccupant should not ignore duplicates", 1, testLocation.getOccupants().size());
    }

    @Test
    public void testRemoveOneOccupantFromPopulated() {
        testLocation.addOccupant(x);
        testLocation.addOccupant(y);

        testLocation.removeOccupant(x);
        assertFalse("removeOccupant should remove the correct occupant", testLocation.getOccupants().contains(x));
    }

    @Test
    public void testRemoveNonExistentOccupant() {
        testLocation.addOccupant(x);
        testLocation.removeOccupant(y);
        assertEquals("removeOccupant should not remove any occupants", 1, testLocation.getOccupants().size());
    }

    @Test
    public void testRemoveOccupantFromEmpty() {
        testLocation.removeOccupant(x);
        assertEquals("removeOccupant should not care for the non-existent occupant", 0, testLocation.getOccupants().size());
    }

    // REQ 3: Supply consistency

    @Test
    public void testAddOneSupplyToEmpty() {
        testLocation.addSupply(a);
        assertTrue("addSupply should add the supply", testLocation.getSupplies().contains(a));
    }

    @Test
    public void testAddSupplySourceEffect() {
        // Checking if the source will be reassigned
        testLocation.addSupply(a);
        assertEquals("supply should belong to the location", testLocation, a.getSource());
    }

    @Test
    public void testAddMultipleSupplies() {
        testLocation.addSupply(a);
        testLocation.addSupply(b);
        testLocation.addSupply(c);

        assertTrue("addSupply should add all three supplies", testLocation.getSupplies().contains(a) && testLocation.getSupplies().contains(b) && testLocation.getSupplies().contains(c));
    }

    @Test
    public void testAddSameSupplies() {
        // Checking if the quantity of the supply is updated
        testLocation.addSupply(a);

        Optional<Supply> actualSupply = testLocation.getSupplies().stream().filter(item -> item.getType().equals(a.getType())).findFirst();

        // Assert that actualSupply has quantity of 2
        assertTrue("addSupply should add the supply", actualSupply.isPresent());
        assertEquals("Supply quantities should be increased", 2, actualSupply.get().getQuantity());
    }

    @Test
    public void testRemoveOneSupplyFromPopulated() {
        testLocation.addSupply(a);
        testLocation.addSupply(b);
        testLocation.removeSupply(a);
        assertFalse("removeSupply should remove the correct supply", testLocation.getSupplies().contains(a));
    }

    @Test
    public void testRemoveNonExistentSupply() {
        testLocation.addSupply(a);
        testLocation.addSupply(b);

        testLocation.removeSupply(c);

        assertEquals("removeSupply should ignore the non-existent supply", 2, testLocation.getSupplies().size());
    }

    @Test
    public void testRemoveSupplyFromEmpty() {
        testLocation.removeSupply(a);
        testLocation.removeSupply(b);
        testLocation.removeSupply(c);

        testLocation.removeSupply(a);
        assertTrue("removeSupply should ignore the non-existent supply", testLocation.getSupplies().isEmpty());
    }
}