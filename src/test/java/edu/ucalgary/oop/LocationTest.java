package edu.ucalgary.oop;

import org.junit.*;

import javax.swing.text.html.Option;
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

    // Testing supplies
    private final Supply a = new Supply("Water", 1);
    private final Supply b = new Supply("Food", 1);
    private final Supply c = new Supply("Blanket", 1);

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
        assertEquals("removeOccupant should remove the correct occupant", 0, testLocation.getOccupants().size());
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
        assertEquals("removeOccupant should not remove any occupants", 0, testLocation.getOccupants().size());
    }

    @Test
    public void testAddOneSupplyToEmpty() {
        testLocation.addSupply(a);
        assertTrue("addSupply should add the supply", testLocation.getSupplies().contains(a));
    }

    @Test
    public void testAddMultipleSupplies() {
        testLocation.addSupply(a);
        testLocation.addSupply(b);
        testLocation.addSupply(c);

        assertEquals("addSupply should add all three supplies", 3, testLocation.getSupplies().size());
    }

    @Test
    public void testAddDuplicateSupplies() {
        // Checking if the quantity of the supply is updated
        Supply supply = new Supply("Water", 1);

        testLocation.addSupply(supply);
        testLocation.addSupply(supply);

        Optional<Supply> actualSupply = testLocation.getSupplies().stream().filter(item -> item.getType().equals(supply.getType())).findFirst();
        // Assert that actualSupply has quantity of 2
        assertTrue("addSupply should add the supply", actualSupply.isPresent());
        assertEquals("Supply quantities should be increased", 2, actualSupply.get().getQuantity());
    }
}