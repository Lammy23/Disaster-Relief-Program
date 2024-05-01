package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.PriorityQueue;

import static org.junit.Assert.*;


public class ReliefServiceTest {

    private ReliefServiceDB testReliefServiceDB;
    private final Inquirer expectedInquirer = new Inquirer("John", "Doe", "403-555-666");
    private final PriorityQueue<DisasterVictim> expectedMissingPersons = new PriorityQueue<>();
    private final ArrayList<InquiryLog> expectedInquiryLogs = new ArrayList<>();

    // Setup inquiry log
    private final InquiryLog inquiryLog1 = new InquiryLog("2024.01.01", "Looking for family");
    private final InquiryLog inquiryLog2 = new InquiryLog("2024.02.02", "Looking for friends");

    // Setup missing persons
    private final DisasterVictim missingPerson1 = new DisasterVictim("Jane", "2024.01.01");
    private final DisasterVictim missingPerson2 = new DisasterVictim("John", "2024.02.02");


    @Before
    public void setUp() {
        expectedInquiryLogs.add(inquiryLog1);
        expectedInquiryLogs.add(inquiryLog2);

        expectedMissingPersons.add(missingPerson1);
        expectedMissingPersons.add(missingPerson2);

        testReliefServiceDB = new ReliefServiceDB(expectedInquirer, expectedMissingPersons, expectedInquiryLogs);
    }

    @Test
    public void testObjectCreation() {
        assertNotNull(testReliefServiceDB);
    }

    /*-----------Testing Constructor----------*/

    @Test
    public void testConstructorInquirer() {
        assertEquals("Constructor should set the correct inquirer", expectedInquirer, testReliefServiceDB.getInquirer());
    }

    @Test
    public void testConstructorMissingPersons() {
        assertEquals("Constructor should set the correct missing persons", expectedMissingPersons, testReliefServiceDB.getMissingPersons());
    }

    @Test
    public void testConstructorInquiryLogs() {
        assertEquals("Constructor should set the correct inquiry logs", expectedInquiryLogs, testReliefServiceDB.getLogs());
    }

    /*-------------Testing Getters & Setters-----------------*/

    @Test
    public void testSetAndGetInquirer() {
        Inquirer newInquirer = new Inquirer("Jane", "Doe", "403-560-8474");

        testReliefServiceDB.setInquirer(newInquirer);
        assertEquals("setInquirer should update the inquirer", newInquirer, testReliefServiceDB.getInquirer());
    }

    @Test
    public void testSetAndGetMissingPersons() {
        PriorityQueue<DisasterVictim> newQueue = new PriorityQueue<>();

        DisasterVictim newVictim = new DisasterVictim("Jane", "2024.01.01");
        newQueue.add(newVictim);

        testReliefServiceDB.setMissingPersons(newQueue);
        assertEquals("setMissingPersons should update the missing person", newQueue, testReliefServiceDB.getMissingPersons());
    }

    @Test
    public void testSetAndGetInquiryLogs() {
        ArrayList<InquiryLog> newLogs = new ArrayList<>();

        InquiryLog newLog = new InquiryLog("2024.01.01", "Looking for family");
        newLogs.add(newLog);

        testReliefServiceDB.setLogs(newLogs);
        assertEquals("setInquiryLogs should update the inquiry logs", newLogs, testReliefServiceDB.getLogs());
    }

    /*-------------Testing Adders and Removers -----------------*/

}