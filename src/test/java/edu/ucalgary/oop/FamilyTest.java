package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class FamilyTest {

    Person john = new Person("John");
    Person jane = new Person("Jane");

    Person robert = new Person("Robert");
    Person mary = new Person("mary");

    ArrayList<Person> siblings;
    ArrayList<Person> parents;

    Family testFamily;

    public FamilyTest() {
    }

    @Before
    public void setUp() {
        siblings = new ArrayList<Person>(john, jane);
        parents = new ArrayList<Person>(robert, mary);
        testFamily = new Family("Smith", siblings, parents);
    }

    @Test
    public void testObjectCreation() {
        assertNotNull(testFamily);
    }

    @Test
    public void testGetFamilyName() {
        assertEquals("getFamilyName should return the correct family name", "Smith", testFamily.getFamilyName());
    }

    @Test
    public void testGetSiblings() {
        assertEquals("getSiblings should return the correct siblings", siblings, testFamily.getSiblings());
    }

    @Test
    public void testGetParents() {
        assertEquals("getParents should return the correct parents", parents, testFamily.getParents());
    }

    @Test
    public void testGetSiblingsOf() {
        ArrayList<Person> expectedSiblings = new ArrayList<Person>(john);
        assertEquals("getSiblingsOf should return the correct siblings of the child", expectedSiblings, testFamily.getSiblingsOf(jane));
    }

    @Test
    public void testGetChildrenOf() {
        ArrayList<Person> expectedChildren = new ArrayList<Person>(jane, john);
        assertEquals("getChildrenOf should return the correct children of the parent", expectedChildren, testFamily.getChildrenOf(robert));
    }

    @Test
    public void testAddSibling() {
        Person newSibling = new Person("Jack");
        testFamily.addSibling(newSibling);
        assertEquals("addSibling should add a new sibling to the family", newSibling, testFamily.getSiblings().get(2));
    }

    @Test
    public void testRemoveSibling() {
        testFamily.removeSibling(jane);
        assertEquals("removeSibling should remove a sibling from the family", 1, testFamily.getSiblings().size());
    }

    @Test
    public void testAddParent() {
        Person newParent = new Person("Jack");
        testFamily.addParent(newParent);
        assertEquals("addParent should add a new parent to the family", newParent, testFamily.getParents().get(2));
    }

    @Test
    public void testRemoveParent() {
        testFamily.removeParent(mary);
        assertEquals("removeParent should remove a parent from the family", 1, testFamily.getParents().size());
    }

    @Test
    public void testSiblingsHaveSameFamily() {
        /* Set the family for both siblings */
        john.setFamily(testFamily);
        jane.setFamily(testFamily);

        /* Check if both siblings have the same family */
        assertEquals("Both siblings should have the same family", john.getFamily(), jane.getFamily());
    }

    @Test
    public void testParentsHaveSameFamily() {
        /* Set the family for both parents */
        robert.setFamily(testFamily);
        mary.setFamily(testFamily);

        /* Check if both parents have the same family */
        assertEquals("Both parents should have the same family", robert.getFamily(), mary.getFamily());
    }

    @Test
    public void testSiblingsLoseSameParent() {
        /* Create a family */
        Family smithFamily = new Family("Smith", new ArrayList<Person>(john, jane), new ArrayList<Person>(robert, mary));

        /* Set the family for both siblings */
        john.setFamily(smithFamily);
        jane.setFamily(smithFamily);

        /* Remove a parent from the family */
        smithFamily.removeParent(john);

        /* Check if both siblings have the same family */
        assertEquals("Both siblings should have the same family", john.getFamily(), jane.getFamily());
    }
}
