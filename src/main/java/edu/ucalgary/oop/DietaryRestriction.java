package edu.ucalgary.oop;

/**
 * Enumerates the dietary restrictions that can be requested by a passenger.
 *
 * @author Olamikun Aluko
 * <a href="mailto:lammyaluko@gmail.com">Email me</a> for any comments
 * @version 1.1
 * @since 1.0
 */
public enum DietaryRestriction {
    AVML("Asian vegetarian meal"),
    DBML("Diabetic meal"),
    GFML("Gluten intolerant meal"),
    KSML("Kosher meal"),
    LSML("Low salt meal"),
    MOML("Muslim meal"),
    PFML("Peanut-free meal"),
    VGML("Vegan meal"),
    VJML("Vegetarian Jain meal");

    private final String description;

    /**
     * Constructor for the dietary restriction.
     * @param description The description of the dietary restriction.
     */
    DietaryRestriction(String description) {
        this.description = description;
    }

    /**
     * Returns the description of the dietary restriction.
     * @return The description of the dietary restriction.
     */
    public String getDescription() {
        return description;
    }
}