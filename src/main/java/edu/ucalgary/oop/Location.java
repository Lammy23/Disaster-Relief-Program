package edu.ucalgary.oop;

import java.util.ArrayList;

/* //TODO: fix this
- name: String
- address: String
- occupants: ArrayList<T>

+ Location(name:String, address:String)

+ getName(): String
+ getAddress(): String
+ getOccupants(): ArrayList<T>

+ setName(name: String): void
+ setAddress(address: String): void
+ setOccupants(occupants: ArrayList<T>): void

+ addOccupant(occupant: T)
+ removeOccupant(occupant: T)
*/

public class Location {
    private String name;
    private String address;
    private ArrayList<DisasterVictim> occupants;

    public Location(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<DisasterVictim> getOccupants() {
        return occupants;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOccupants(ArrayList<DisasterVictim> occupants) {
        this.occupants = occupants;
    }
}
