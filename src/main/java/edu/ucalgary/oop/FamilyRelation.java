package edu.ucalgary.oop;

import java.util.HashSet;
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

/**
 * Class that represents a family relationship of a `DisasterVictim`
 */
public class FamilyRelation {

    private DisasterVictim personOne;
    private DisasterVictim personTwo;
    private String relationshipTo;

    public void recursiveAdderGlance(HashSet<DisasterVictim> fellows) {

        // Adding personOne and personTwo to the set of people who are connected by the same relationship
        //
        boolean p1Necessary = fellows.add(personOne);
        boolean p2Necessary = fellows.add(personTwo);

        HashSet<DisasterVictim> otherPeople = new HashSet<>(fellows);
        otherPeople.remove(personOne);
        otherPeople.remove(personTwo);

        HashSet<DisasterVictim> nextPeople = new HashSet<>();
        if (p1Necessary) nextPeople.add(personOne);
        if (p2Necessary) nextPeople.add(personTwo);

        for (DisasterVictim person : nextPeople) {
            // Preventing ConcurrentModificationException
            HashSet<FamilyRelation> connectionsToAdd = new HashSet<>();

            if (!person.getFamilyConnections().isEmpty()) {
                for (FamilyRelation connection : person.getFamilyConnections()) {
                    if (connection.equals(this)) {
                        for (DisasterVictim otherPerson : otherPeople) {
                            if (otherPerson.getFamilyConnections() == null) {
                                FamilyRelation targetConnection = new FamilyRelation(person, relationshipTo, otherPerson);
//                                person.addFamilyConnection(targetConnection, false);
                                connectionsToAdd.add(targetConnection);
                            } else for (FamilyRelation targetConnection : otherPerson.getFamilyConnections()) {
                                // Ensuring no redundancy according to REQ 2
                                if (targetConnection.personOne.equals(person) || targetConnection.personTwo.equals(person)) {
//                                    person.addFamilyConnection(targetConnection, false);
                                    connectionsToAdd.add(targetConnection);
                                    break;
                                }
                            }
                        }
                    } else if (connection.relationshipTo.equals(this.relationshipTo)) {
                        connection.recursiveAdderGlance(fellows);
                    }
                }

                for (FamilyRelation connectionToAdd : connectionsToAdd) {
                    person.addFamilyConnection(connectionToAdd, false);
                }

            } else {
                HashSet<DisasterVictim> otherThanMyself = new HashSet<>(fellows);
                otherThanMyself.remove(person);

                for (DisasterVictim otherPersonThanMyself : otherThanMyself) {
                    if (otherPersonThanMyself.getFamilyConnections().isEmpty()) {
                        FamilyRelation targetConnection = new FamilyRelation(person, relationshipTo, otherPersonThanMyself);
                        person.addFamilyConnection(targetConnection, false);
                    } else for (FamilyRelation targetConnection : otherPersonThanMyself.getFamilyConnections()) {
                        // Ensuring no redundancy according to REQ 2
                        if (targetConnection.personOne.equals(person) || targetConnection.personTwo.equals(person)) {
                            person.addFamilyConnection(targetConnection, false);
                            break;
                        }
                    }
                }
            }
        }
    }

    public void recursiveRemoverGlance(HashSet<DisasterVictim> fellows) {
        // TODO: Implement function
    }

    /*-----------Constructor----------*/

    public FamilyRelation(DisasterVictim personOne, String relationshipTo, DisasterVictim personTwo) {
        this.personOne = personOne;
        this.relationshipTo = relationshipTo;
        this.personTwo = personTwo;
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