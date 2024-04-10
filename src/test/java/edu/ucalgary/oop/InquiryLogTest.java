package edu.ucalgary.oop;


import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.PriorityQueue;

import static org.junit.Assert.*;

public class InquiryLogTest {

    private InquiryLog testinquiryLog;
    private final String expectedDateOfInquiry = "2024/02/02";
    private final String expectedInfoProvided = "Looking for family";

    private final String expectedValidDateOfInquiry = "2023.01.01";

    @Before
    public void setUp() {
        testinquiryLog = new InquiryLog(expectedDateOfInquiry, expectedInfoProvided);
    }

    @Test
    public void testObjectCreation() {
        assertNotNull(testinquiryLog);
    }

    /*-----------Testing Constructor----------*/

    @Test
    public void testConstructorValidDateOfInquiry() {
        assertEquals("Constructor should set the correct date of inquiry", ApplicationUtils.parseDate(expectedDateOfInquiry), testinquiryLog.getDateOfInquiry());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidDelimiterDateOfInquiry() {
        String invalidDelimiter = "2024!01)01";
        new InquiryLog(invalidDelimiter, expectedInfoProvided);               // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidFormatDateOfInquiry() {
        String invalidFormat = "01.01.2024";
        new InquiryLog(invalidFormat, expectedInfoProvided);               // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorImpossibleDateOfInquiry() {
        String impossibleDate = "2022.99.99";
        new InquiryLog(impossibleDate, expectedInfoProvided);               // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorFutureDateOfInquiry() {
        String futureDate = "2025.01.01";
        new InquiryLog(futureDate, expectedInfoProvided);               // Expecting line to fail
    }

    @Test
    public void testConstructorInfoProvided() {
        assertEquals("Constructor should set the correct information provided", expectedInfoProvided, testinquiryLog.getInfoProvided());
    }

    /*-----------Testing Setters & Getters----------*/

    @Test
    public void testSetAndGetValidDateOfInquiry() {
        testinquiryLog.setDateOfInquiry(expectedValidDateOfInquiry);
        assertEquals("Setter should set the correct date of inquiry", ApplicationUtils.parseDate(expectedValidDateOfInquiry), testinquiryLog.getDateOfInquiry());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidDelimiterDateOfInquiry() {
        String invalidDelimiter = "2024!01)01";
        testinquiryLog.setDateOfInquiry(invalidDelimiter);               // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidFormatDateOfInquiry() {
        String invalidFormat = "01.01.2024";
        testinquiryLog.setDateOfInquiry(invalidFormat);               // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetImpossibleDateOfInquiry() {
        String impossibleDate = "2022.99.99";
        testinquiryLog.setDateOfInquiry(impossibleDate);              // Expecting line to fail
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetFutureDateOfInquiry() {
        String futureDate = "2025.01.01";
        testinquiryLog.setDateOfInquiry(futureDate);                 // Expecting line to fail
    }

    @Test
    public void testSetAndGetInfoProvided() {
        String newInfoProvided = "Looking for friends";
        testinquiryLog.setInfoProvided(newInfoProvided);
        assertEquals("Setter should set the correct information provided", newInfoProvided, testinquiryLog.getInfoProvided());
    }
}