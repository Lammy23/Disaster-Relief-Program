package edu.ucalgary.oop;

import java.util.ArrayList;

/* //TODO: fix this
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
+

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
