package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

// TODO: Add version and author to every single class

/**
 * Class that represents a location that shelters `DisasterVictim`s
 */
public class Location {
    private String name;
    private String address;
    private ArrayList<DisasterVictim> occupants = new ArrayList<>();                            // Might be useful to know who arrived first at location, we use ArrayList
    private HashSet<Supply> supplies = new HashSet<>();

    /*---------Constructor------------*/

    /**
     * Constructor for `Location`
     *
     * @param name    the name of the location
     * @param address the address of the location
     */
    public Location(String name, String address) {
        this.name = name;
        this.address = address;
    }

    /*---------Getters------------*/

    /**
     * Gets the name of the location
     *
     * @return the name of the location
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the address of the location
     *
     * @return the address of the location
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the occupants of the location
     *
     * @return the occupants of the location
     */
    public ArrayList<DisasterVictim> getOccupants() {
        return occupants;
    }

    /**
     * Gets the supplies of the location
     *
     * @return the supplies of the location
     */
    public HashSet<Supply> getSupplies() {
        return supplies;
    }

    /*---------Setters------------*/

    /**
     * Sets the name of the location
     *
     * @param name the name of the location
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the address of the location
     *
     * @param address the address of the location
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the occupants of the location
     *
     * @param occupants the occupants of the location
     */
    public void setOccupants(ArrayList<DisasterVictim> occupants) {
        this.occupants = occupants;
    }

    /**
     * Sets the supplies of the location
     *
     * @param supplies the supplies of the location
     */
    public void setSupplies(HashSet<Supply> supplies) {
        this.supplies = supplies;
    }

    /*---------Adders/Removers------------*/

    /**
     * Adds an occupant to the location
     *
     * @param occupant the occupant to add
     */
    public void addOccupant(DisasterVictim occupant) {
        // Check if occupant is already in the list
        if (this.occupants.contains(occupant)) {
            return;
        }
        this.occupants.add(occupant);
    }

    /**
     * Removes an occupant from the location
     *
     * @param occupant the occupant to remove
     */
    public void removeOccupant(DisasterVictim occupant) {
        this.occupants.remove(occupant);
    }

    /**
     * Adds a supply to the location
     *
     * @param supply the supply to add
     */
    public void addSupply(Supply supply) {
        Optional<Supply> existingSupply = supplies.stream().filter((item) -> item.getType().equals(supply.getType())).findFirst();

        if (existingSupply.isPresent()) {
            Supply targetSupply = existingSupply.get();
            int oldQuantity = targetSupply.getQuantity();
            int newQuantity = supply.getQuantity();
            targetSupply.setQuantity(oldQuantity + newQuantity);
        } else {
            supplies.add(supply);
        }
    }

    /**
     * Removes a supply from the location
     *
     * @param supply the supply to remove
     */
    public void removeSupply(Supply supply) {
        Optional<Supply> existingSupply = supplies.stream().filter(item -> item.getType().equals(supply.getType())).findFirst();

        if (existingSupply.isPresent()) {
            Supply targetSupply = existingSupply.get();
            int oldQuantity = targetSupply.getQuantity();
            int newQuantity = supply.getQuantity();

            if (newQuantity >= oldQuantity) {
                // Remove supplies entirely
                targetSupply.setSource(null);
                supplies.remove(targetSupply);
            } else {
                targetSupply.setQuantity(oldQuantity - newQuantity);
            }
        }
    }
}