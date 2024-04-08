package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

/* TODO: perfect this UML Diagram
- name: String
- address: String
- occupants: ArrayList<DisasterVictim>                      // Might be useful to know who arrived first at location, we use ArrayList
- supplies: HashSet<Supply>

+ Location(name:String, address:String)

+ getName(): String
+ getAddress(): String
+ getOccupants(): ArrayList<DisasterVictim>
+ getSupplies(): HashSet<Supply>

+ setName(name: String): void
+ setAddress(address: String): void
+ setOccupants(occupants: ArrayList<DisasterVictim>): void
+ setSupplies(supplies: HashSet<Supply>): void

+ addOccupant(occupant: DisasterVictim): void
+ removeOccupant(occupant: DisasterVictim): void
+ addSupply(supply: Supply): void
+ removeSupply(supply: Supply): void
*/

/**
 * Class that represents a location that shelters `DisasterVictim`s
 */
public class Location {
    private String name;
    private String address;
    private ArrayList<DisasterVictim> occupants;
    private HashSet<Supply> supplies;

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

    /*
    + addOccupant(occupant: DisasterVictim): void
    + removeOccupant(occupant: DisasterVictim): void
    + addSupply(supply: Supply): void
    + removeSupply(supply: Supply): void
     */

    public void addOccupant(DisasterVictim occupant) {
        // Check if occupant is already in the list
        if (this.occupants.contains(occupant)) {
            return;
        }
        this.occupants.add(occupant);
    }

    public void removeOccupant(DisasterVictim occupant) {
        this.occupants.remove(occupant);
    }

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

    public void removeSupply(Supply supply) {
        Optional<Supply> existingSupply = supplies.stream().filter(item -> item.getType().equals(supply.getType())).findFirst();

        if (existingSupply.isPresent()) {
            Supply targetSupply = existingSupply.get();
            int oldQuantity = targetSupply.getQuantity();
            int newQuantity = supply.getQuantity();

            if (newQuantity >= oldQuantity) {
                // Remove supplies entirely
                supplies.remove(supply);
            } else {
                targetSupply.setQuantity(oldQuantity - newQuantity);
            }
        }
    }
}