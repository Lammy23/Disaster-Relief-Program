## Brainstorm
* GenderOptions.txt file path
* Dietary restrictions
* How locations are stored (persist for the program only)
* Instructions on how to run javadoc
* You don't have to include postresql and junit and hamcrest but tell them how to get it
* keeps tests and main separate but instruct on how to run tests and run main
* press ctrl + c to force quit
* where the sql file is stored. how to setup the database.

# User Manual

Welcome to the User Manual for the OOP Project!
This manual will guide you through the process of setting up and running the program.

## Table of Contents

1. [Setting Up the Program](#setting-up-the-program)
2. [Running the Program](#running-the-program)
3. [Running the Tests](#running-the-tests)
4. [Exiting the Program](#exiting-the-program)
5. [Troubleshooting](#troubleshooting)
6. [Additional Information](#additional-information)
7. [Contact Information](#contact-information)

## Setting Up the Program

To set up the program, you will need to have the following installed on your computer:
* Java 11
* PostgreSQL
* JUnit
* Hamcrest

To install Java 11, follow the instructions on the [Java website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

To install PostgreSQL, follow the instructions on the [PostgreSQL website](https://www.postgresql.org/download/).

To install JUnit and Hamcrest, follow the instructions on the [JUnit website](https://junit.org/junit5/).

Once you have installed the necessary software, you can proceed to the next section.

## Running the Program

To run the program, follow these steps:

1. Clone the repository to your local machine.
2. Open a terminal window and navigate to the project directory.
3. Run the following command to compile the program:
```
javac -cp .:postgresql-42.2.23.jar Main.java
```
4. Run the following command to run the program:
```
java -cp .:postgresql-42.2.23.jar Main
```
5. Follow the on-screen instructions to use the program.
6. Press `Ctrl + C` to force quit the program.
7. To exit the program, follow the instructions in the next section.
8. To view the Javadoc documentation, follow the instructions in the [Additional Information](#additional-information) section.
9. To set up the database, follow the instructions in the [Additional Information](#additional-information) section.
10. To run the tests, follow the instructions in the [Running the Tests](#running-the-tests) section.
11. For additional information, refer to the [Additional Information](#additional-information) section.

## Running the Tests

To run the tests, follow these steps:

1. Open a terminal window and navigate to the project directory.
2. Run the following command to compile the tests:
```
javac -cp .:junit-platform-console-standalone-1.8.2.jar:.:postgresql-42.2.23.jar TestRunner.java
```
3. Run the following command to run the tests:
```
java -jar junit-platform-console-standalone-1.8.2.jar --class-path .:postgresql-42.2.23.jar --scan-class-path
```
4. View the test results in the terminal window.
5. For additional information, refer to the [Additional Information](#additional-information) section.

## Exiting the Program

To exit the program, follow these steps:

1. Press `Ctrl + C` to force quit the program.
2. Follow the instructions in the [Running the Program](#running-the-program) section to run the program again.
3. For additional information, refer to the [Additional Information](#additional-information) section.
4. If you encounter any issues, refer to the [Troubleshooting](#troubleshooting) section.

## Troubleshooting

If you encounter any issues while setting up or running the program, follow these steps:

1. Check that you have installed the necessary software (Java 11, PostgreSQL, JUnit, Hamcrest).
2. Check that you have followed the instructions in the [Setting Up the Program](#setting-up-the-program) section.
3. Check that you have followed the instructions in the [Running the Program](#running-the-program) section.
4. Check that you have followed the instructions in the [Running the Tests](#running-the-tests) section.
5. If you are still experiencing issues, contact the [project team](#contact-information) for assistance.

## Additional Information

For additional information about the program, refer to the following sections: