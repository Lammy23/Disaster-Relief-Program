package edu.ucalgary.oop;

import java.util.HashSet;

/**
 * Class that represents a family relationship of a `DisasterVictim`
 *
 * @author Olamikun Aluko
 * <a href="mailto:lammyaluko@gmail.com">Email me</a> for any comments
 * @version 1.1
 * @since 1.0
 */
public class FamilyRelation {

    private DisasterVictim personOne;
    private DisasterVictim personTwo;
    private String relationshipTo;

    /**
     * Method to get all the siblings of the two people in the family relationship
     * @param siblings The set of siblings to add the siblings to
     * @return The set of siblings
     */
    public HashSet<DisasterVictim> getAllSiblings(HashSet<DisasterVictim> siblings) {

        boolean p1NotExisting = siblings.add(personOne);
        if (p1NotExisting) {
            for (FamilyRelation connection : personOne.getFamilyConnections()) {
                if (connection.getRelationshipTo().equals("sibling"))
                    connection.getAllSiblings(siblings);
            }
        }

        boolean p2NotExisting = siblings.add(personTwo);
        if (p2NotExisting) {
            for (FamilyRelation connection : personTwo.getFamilyConnections()) {
                if (connection.getRelationshipTo().equals("sibling"))
                    connection.getAllSiblings(siblings);
            }
        }

        return siblings;
    }

    /*-----------Constructor----------*/

    /**
     * Default constructor for the `FamilyRelation` class
     */
    public FamilyRelation() {

    }

    /**
     * Constructor for the `FamilyRelation` class
     *
     * @param personOne      The first person in the family relationship
     * @param relationshipTo The relationship between the two people
     * @param personTwo      The second person in the family relationship
     */
    public FamilyRelation(DisasterVictim personOne, String relationshipTo, DisasterVictim personTwo) {
        this.personOne = personOne;
        this.relationshipTo = relationshipTo;
        this.personTwo = personTwo;
    }

    /*------Getters--------*/

    /**
     * Getter for the first person in the family relationship
     *
     * @return The first person in the family relationship
     */
    public DisasterVictim getPersonOne() {
        return personOne;
    }

    /**
     * Getter for the relationship between the two people
     *
     * @return The relationship between the two people
     */
    public String getRelationshipTo() {
        return relationshipTo;
    }

    /**
     * Getter for the second person in the family relationship
     *
     * @return The second person in the family relationship
     */
    public DisasterVictim getPersonTwo() {
        return personTwo;
    }

    /*-------Setters---------*/

    /**
     * Setter for the first person in the family relationship
     *
     * @param personOne The first person in the family relationship
     */
    public void setPersonOne(DisasterVictim personOne) {
        this.personOne = personOne;
    }

    /**
     * Setter for the relationship between the two people
     *
     * @param relationshipTo The relationship between the two people
     */
    public void setRelationshipTo(String relationshipTo) {
        this.relationshipTo = relationshipTo;
    }

    /**
     * Setter for the second person in the family relationship
     *
     * @param personTwo The second person in the family relationship
     */
    public void setPersonTwo(DisasterVictim personTwo) {
        this.personTwo = personTwo;
    }
}