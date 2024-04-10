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

## About the Program

...

## Setting Up the Program

To set up the program, you will need to have the following installed on your computer:
* Java 11

For installation of Java, follow the instructions on the [Java website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

You will also need to have the following .jar files in the project directory:
* postgresql-42.7.2.jar
* junit-4.13.2.jar
* hamcrest-core-1.3.jar

These files can be downloaded from the following links:
* [PostgreSQL JDBC Driver](https://jdbc.postgresql.org/download.html)
* [JUnit](https://junit.org/junit4/)
* [Hamcrest](http://hamcrest.org/JavaHamcrest/)

The file structure for this project (Maven) ensures that the main program and the tests are kept separate. 
The main program is located in the `src/main/java/edu/ucalgary/oop` directory, while the tests are located in the `src/test/java/edu/ucalgary/oop` directory.

Ensure you have the GenderOptions.txt file in the project directory. It should be in the `src/main/resources` directory.


This program interfaces with the PostgreSQL database, so you will need to have PostgreSQL installed on your computer.
You can download PostgreSQL from the [PostgreSQL website](https://www.postgresql.org/download/). After downloading and installing PostgreSQL,
open the PostgreSQL Shell, login with:

```
Server [localhost]:
Database [postgres]:
Port [5432]:
Username [postgres]:
Password for user postgres:         // Enter your password
```

Once you have logged in as admin, create a new user with the following command:


```
CREATE USER oop PASSWORD 'ucalgary';
```

Now that you have created a new user, you're ready to run the program. In the program, we 
handle the creation of the database for you.

## Running the Program

To run the program, follow these steps:

As mentioned earlier, ensure you have the following folder structure:

Open a terminal window and navigate to the project directory.
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