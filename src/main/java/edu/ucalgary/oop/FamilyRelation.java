package edu.ucalgary.oop;

/*

## Attributes ##

- personOne: DisasterVictim
- relationshipTo: String
- personTwo: DisasterVictim

## Methods ##

+ FamilyRelation(personOne: DisasterVictim,
relationshipTo: String, personTwo: DisasterVictim)

+ getPersonOne(): DisasterVictim
+ getRelationshipTo(): String
+ getPersonTwo(): DisasterVictim

+ setPersonOne(personOne: DisasterVictim): void
+ setRelationshipTo(relationshipTo: String): void
+ setPersonTwo(personTwo: DisasterVictim): void
 */

import java.util.HashSet;

/**
 * Class that represents a family relationship of a `DisasterVictim`
 */
public class FamilyRelation {
    // TODO: Implement class properly

    private DisasterVictim personOne;
    private DisasterVictim personTwo;
    private String relationshipTo;

    // TODO: Overload function with null (or none) parameters
    public void gladeRecurse(HashSet<DisasterVictim> fellows) {
        boolean p1Necessary = fellows.add(personOne);
        boolean p2Necessary = fellows.add(personTwo);

        HashSet<DisasterVictim> nextPeople = new HashSet<>();
        if (p1Necessary) nextPeople.add(personTwo);
        if (p2Necessary) nextPeople.add(personTwo);

        HashSet<DisasterVictim> otherPeople = new HashSet<>();
        /* Add people in fellows who are not personOne or personTwo */
        for (DisasterVictim person : fellows) {
            if (!person.equals(personOne) && !person.equals(personTwo)) {
                otherPeople.add(person);
            }
        }

        for (DisasterVictim person : nextPeople) {
            if (person.getFamilyConnections() != null) {
                for (FamilyRelation connection : person.getFamilyConnections()) {
                    if (connection.equals(this)) {
                        // TODO: account for empty otherPeople list
                        for (DisasterVictim otherPerson : otherPeople) {
                            FamilyRelation newRelation = new FamilyRelation(person, this.relationshipTo, otherPerson);
                            person.addFamilyConnection(newRelation, false);
                        }
                    } else if (connection.relationshipTo.equals(this.relationshipTo)) {
                        connection.gladeRecurse(fellows);
                    }
                }
            } else {
                HashSet<DisasterVictim> otherThanMyself = new HashSet<>(fellows);
                otherThanMyself.remove(person);

                for (DisasterVictim otherPersonThanMyself : otherThanMyself) {
                    for (FamilyRelation connection: otherPersonThanMyself.getFamilyConnections()) {
                        // Ensuring no redundancy according to REQ 2
                        if (personOne.equals(person) && personTwo.equals(person)) {
                            person.addFamilyConnection(connection, false);
                            break;
                        }
                    }
                }
            }

        }

    }

    /*-----------Constructor----------*/

    public FamilyRelation(DisasterVictim personOne, String relationshipTo, DisasterVictim personTwo) {

    }

    /*------Getters--------*/

    public DisasterVictim getPersonOne() {
        return personOne;
    }

    public String getRelationshipTo() {
        return relationshipTo;
    }

    public DisasterVictim getPersonTwo() {
        return personTwo;
    }

    /*-------Setters---------*/

    public void setPersonOne(DisasterVictim personOne) {
        this.personOne = personOne;
    }

    public void setRelationshipTo(String relationshipTo) {
        this.relationshipTo = relationshipTo;
    }

    public void setPersonTwo(DisasterVictim personTwo) {
        this.personTwo = personTwo;
    }
}