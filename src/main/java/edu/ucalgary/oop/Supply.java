package edu.ucalgary.oop;

// TODO: Use the below comment as an example to write the Javadoc for classes

// TODO: Last thing todo, add null exception for strings ("") and negative numbers

/**
 * Class to represent a supply
 *
 * @author Olamikun Aluko
 * <a href="mailto:lammyaluko@gmail.com">Email me</a> for any comments
 * @version 1.1
 * @since 1.0
 */
public class Supply {
    private String type;
    private int quantity;
    private Location source;

    /*---------------Constructor---------------*/

    /**
     * Constructor for the Supply class
     *
     * @param type     the type of supply
     * @param quantity the quantity of the supply
     */
    public Supply(String type, int quantity, Location source) {
        this.type = type;
        this.quantity = quantity;
        this.source = source;

        // Attached supply to location
        source.addSupply(this);
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

    /**
     * Getter for the source of the supply
     *
     * @return the source of the supply
     */
    public Location getSource() {
        return source;
    }
    /*---------------Setters---------------*/

    /**
     * Setter for the type of supply
     *
     * @param type the type of supply
     */
    public void setType(String type) {
        if (type.isEmpty()) {
            throw new IllegalArgumentException("Type cannot be empty");
        }
        this.type = type;
    }

    /**
     * Setter for the quantity of the supply (cannot be zero or negative)
     *
     * @param quantity the quantity of the supply
     */
    public void setQuantity(int quantity) throws IllegalArgumentException {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be zero or negative");
        }
        this.quantity = quantity;
    }

    /**
     * Setter for the source of the supply
     *
     * @param source the source of the supply
     */
    public void setSource(Location source) {
        this.source = source;
    }
}
