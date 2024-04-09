package edu.ucalgary.oop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for DRY (Don't Repeat Yourself) implementation of helper functions
 */
public class ApplicationUtils {

    /**
     * Checks if a date is valid. Date could be future or past
     *
     * @param date the date to check
     * @return true if the date is valid, false otherwise
     */
    public static boolean isValidDate(String date) {
        // Checking that date resembles YYYY-MM-DD, YYYY/MM/DD, YYYY.MM.DD
        String regex = "\\d{4}[-./]\\d{2}[-./]\\d{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        boolean matches = matcher.matches();

        if (matches) {
            try {
                // Checking that the dates are not impossible
                // Parsing date in standard format
                String standardizedDate = parseDate(date);

                // Defining the date format
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                // Parsing the date
                LocalDate parsedDate = LocalDate.parse(standardizedDate, formatter);

                // If the date was successfully parsed, then it's valid
                return true;
            } catch (DateTimeParseException e) {
                // If the date couldn't be parsed, then it's not valid
                return false;
            }
        }
        return false;
    }

    /**
     * Checks if a date is in the past
     *
     * @param date the date to check
     * @return true if the date is in the past, false otherwise
     */
    public static boolean isValidPastDate(String date) {

        if (isValidDate(date)) {
            // Parsing date in standard format
            String standardizedDate = parseDate(date);

            // Defining the date format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Parsing the date
            LocalDate parsedDate = LocalDate.parse(standardizedDate, formatter);

            // Getting current date
            LocalDate currentDate = LocalDate.now();

            // If parsedDate is on or before currentDate, return true
            return (!parsedDate.isAfter(currentDate));
        }
        return false;
    }

    /**
     * Parses a date into a standard format
     *
     * @param date the date to parse
     * @return the parsed date
     */
    public static String parseDate(String date) {
        // Replacing any non-digit characters with a dash
        // TODO: Confirm that \\D is the right symbol
        return date.replaceAll("\\D", "-");
    }

    /*---------------Gender Utils---------------*/

    /**
     * Gets the valid genders from GenderOptions.txt
     * @return a HashSet of valid genders
     */
    public static HashSet<String> getValidGenders() {
        // Check the GenderOptions.txt file for the genders

        BufferedReader inputStream = null;
        HashSet<String> validGenders = new HashSet<>();

        try {
            inputStream = new BufferedReader(new FileReader(MainApplication.genderOptionsFilePath));
            String line;

            while ((line = inputStream.readLine()) != null) {
                validGenders.add(line.toLowerCase());                       // To make it case-insensitive
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return validGenders;
    }

    /**
     * Checks if a gender is valid
     * @param gender gender to check
     * @return `true` if gender is valid, `false` otherwise
     */
    public static Boolean isValidGender(String gender) {
        // Check MainApplication.validGenders to see if gender is on the list

        return MainApplication.validGenders.contains(gender);
    }

}