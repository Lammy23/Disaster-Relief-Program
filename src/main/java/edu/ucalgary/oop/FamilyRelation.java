package edu.ucalgary.oop;

import java.util.HashSet;

/**
 * Class that represents a family relationship of a `DisasterVictim`
 */
public class FamilyRelation {

    private DisasterVictim personOne;
    private DisasterVictim personTwo;
    private String relationshipTo;

    /**
     * Recursive method that adds all people connected by the same relationship to a set.
     * It makes sure that the proper connections are made according to the relationship.
     *
     * @param fellows The set of people who are siblings
     */
    public void recursiveAdderGlance(HashSet<DisasterVictim> fellows) {

        // Adding personOne and personTwo to the set of people who are connected by the same relationship
        // TODO: restrict "relationshipTo" to just "siblings"
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
                                connectionsToAdd.add(targetConnection);
                            } else for (FamilyRelation targetConnection : otherPerson.getFamilyConnections()) {
                                // Ensuring no redundancy according to REQ 2
                                if (targetConnection.personOne.equals(person) || targetConnection.personTwo.equals(person)) {
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

    /**
     * Recursive method that removes all people connected by the same relationship from a set.
     * It makes sure that the proper connections are removed according to the relationship.
     *
     * @param fellows The set of people who are siblings
     * @return The set of people who are siblings
     */
    public HashSet<DisasterVictim> recursiveRemoverGlance(HashSet<DisasterVictim> fellows) {
        // Gathering all siblings

        boolean p1NotExisting = fellows.add(personOne);
        if (p1NotExisting) {
            for (FamilyRelation connection : personOne.getFamilyConnections()) {
                if (connection.getRelationshipTo().equals("sibling"))
                    fellows = connection.recursiveRemoverGlance(fellows);
            }
        }

        boolean p2NotExisting = fellows.add(personTwo);
        if (p2NotExisting) {
            for (FamilyRelation connection : personTwo.getFamilyConnections()) {
                if (connection.getRelationshipTo().equals("sibling"))
                    fellows = connection.recursiveRemoverGlance(fellows);
            }
        }

        return fellows;
    }

    /*-----------Constructor----------*/

    /**
     * Constructor for the `FamilyRelation` class
     *
     * @param personOne The first person in the family relationship
     * @param relationshipTo The relationship between the two people
     * @param personTwo The second person in the family relationship
     */
    public FamilyRelation(DisasterVictim personOne, String relationshipTo, DisasterVictim personTwo) {
        this.personOne = personOne;
        this.relationshipTo = relationshipTo;
        this.personTwo = personTwo;
    }

    /*------Getters--------*/

    /**
     * Getter for the first person in the family relationship
     * @return The first person in the family relationship
     */
    public DisasterVictim getPersonOne() {
        return personOne;
    }

    /**
     * Getter for the relationship between the two people
     * @return The relationship between the two people
     */
    public String getRelationshipTo() {
        return relationshipTo;
    }

    /**
     * Getter for the second person in the family relationship
     * @return The second person in the family relationship
     */
    public DisasterVictim getPersonTwo() {
        return personTwo;
    }

    /*-------Setters---------*/

    /**
     * Setter for the first person in the family relationship
     * @param personOne The first person in the family relationship
     */
    public void setPersonOne(DisasterVictim personOne) {
        this.personOne = personOne;
    }

    /**
     * Setter for the relationship between the two people
     * @param relationshipTo The relationship between the two people
     */
    public void setRelationshipTo(String relationshipTo) {
        this.relationshipTo = relationshipTo;
    }

    /**
     * Setter for the second person in the family relationship
     * @param personTwo The second person in the family relationship
     */
    public void setPersonTwo(DisasterVictim personTwo) {
        this.personTwo = personTwo;
    }
}