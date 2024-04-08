package edu.ucalgary.oop;

/*
## Attributes ##
- type: String
- quantity: int

## Methods ##
+ Supply(type: String, quantity: int)

+ getType(): String
+ getQuantity(): int

+ setType(type: string): void
+ setQuantity(quantity: int): void
 */

/**
 * Class to represent a supply
 */
public class Supply {
    private String type;
    private int quantity;

    /*---------------Constructor---------------*/

    /**
     * Constructor for the Supply class
     *
     * @param type     the type of supply
     * @param quantity the quantity of the supply
     */
    public Supply(String type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    /*---------------Getters---------------*/

    /**
     * Getter for the type of supply
     *
     * @return the type of supply
     */
    public String getType() {
        return type;
    }

    /**
     * Getter for the quantity of the supply
     *
     * @return the quantity of the supply
     */
    public int getQuantity() {
        return quantity;
    }

    /*---------------Setters---------------*/

    /**
     * Setter for the type of supply
     *
     * @param type the type of supply
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Setter for the quantity of the supply
     *
     * @param quantity the quantity of the supply
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
