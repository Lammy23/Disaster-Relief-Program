package edu.ucalgary.oop;

import org.junit.*;

import static org.junit.Assert.*;

/*
- type: String
- quantity: int

+ Supply(type: String, quantity: int)

+ getType(): String
+ getQuantity(): int

+ setType(type: string): void
+ setQuantity(quantity: int): void
 */

public class SupplyTest {

    private Supply testSupply;
    private String expectedType = "Water";
    private int expectedQuantity = 10;

    public SupplyTest() {
    }

    @Before
    public void setUp() {
        testSupply = new Supply(expectedType, expectedQuantity);
    }

    @Test
    public void testObjectCreation() {
        assertNotNull(testSupply);
    }

    @Test
    public void testConstructorType() {
        assertEquals("Constructor should set the correct type", expectedType, testSupply.getType());
    }

    @Test
    public void testConstructorQuantity() {
        assertEquals("Constructor should set the correct quantity", expectedQuantity, testSupply.getQuantity());
    }

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
    public void testSetQuantityNegative() {
        testSupply.setQuantity(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTypeEmpty() {
        testSupply.setType("");
    }

}
