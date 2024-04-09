package edu.ucalgary.oop;

import org.junit.*;

import static org.junit.Assert.*;


public class SupplyTest {

    private Supply testSupply;
    private String expectedType = "Water";
    private int expectedQuantity = 10;
    private Location expectedSource = new Location("LocationA", "123 Location lane");

    public SupplyTest() {
    }

    @Before
    public void setUp() {
        testSupply = new Supply(expectedType, expectedQuantity, expectedSource);
    }

    @Test
    public void testObjectCreation() {
        assertNotNull(testSupply);
    }

    /*--------Testing Constructor-----------*/

    @Test
    public void testConstructorType() {
        assertEquals("Constructor should set the correct type", expectedType, testSupply.getType());
    }

    @Test
    public void testConstructorQuantity() {
        assertEquals("Constructor should set the correct quantity", expectedQuantity, testSupply.getQuantity());
    }

    @Test
    public void testConstructorSource() {
        assertEquals("Constructor should set the correct source", expectedSource, testSupply.getSource());
    }

    /*--------Testing Getters & Setters-----------*/

    @Test
    public void testSetAndGetType() {
        String newType = "Food";
        testSupply.setType(newType);
        assertEquals("setType should update the type", newType, testSupply.getType());
    }

    @Test
    public void testSetAndGetQuantity() {
        int newQuantity = 20;
        testSupply.setQuantity(newQuantity);
        assertEquals("setQuantity should update the quantity", newQuantity, testSupply.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetQuantityZero() {
        testSupply.setQuantity(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetQuantityNegative() {
        testSupply.setQuantity(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTypeEmpty() {
        testSupply.setType("");
    }

    @Test
    public void testSetAndGetSource() {
        Location newSource = new Location("University of Calgary", "2500 University Dr NW");
        testSupply.setSource(newSource);
        assertEquals("setSource should update the source", newSource, testSupply.getSource());
    }

}
