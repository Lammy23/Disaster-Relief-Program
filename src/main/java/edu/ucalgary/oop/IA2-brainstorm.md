# Brainstorm

## Individual Assignment 2

Before we begin, take a look at the rubric

> - [ ] Tests exist with valid data for all methods
    > (getters and setters may be combined), including all
    > versions of polymorphic methods.
    > All exceptions which can be thrown are tested.
    > Where values are checked, tests with invalid data exist.
    > Edge cases are considered.
> - [ ] All tests have clear names and useful messages.
> - [ ] Tests are established using Arrange, Act, Assert.
    > @Before and @After are used appropriately.
> - [ ] Tests appear to have correct syntax. Annotations are used. Includes are used.
    > Package name is correct. Test files are appropriately named.

In this assignment, we are required to make unit tests for
classes, methods and more.

We should use the `edu.ucalgary.oop` package.

Our main reference for the tests is the UML Diagram we
created.

It is helpful to watch the three videos on testing: 18A, 18B
and 18C.

- [x] 18A
- [x] 18B
- [ ] 18C

After watching the videos, complete this table of tests

### Gender options is not an enum, we need to actually access the .txt file

### Could it be possible to test Composition, Aggregation, Association and more?

### Could it be possible to test that a class correctly inherits another class, ensure that a class is in fact abstract and ensure that a class implements an interface?

### Helper function called `dateParser` that I can test instead of testing dates in all the classes?

Got it. Test the dateParser for valid date formats, then perform standard tests of existence on all other classes.
So each class that uses it will have in it's UML:

__`dateParser(s: String): String`__

then you can write tests for their date parser based on the possible date values that they should have joor. E.g. entry
date
cannot be greater than todays date.

### PersonTest:

| Test Name                                      | Description                                                                             | Test Value(s)                           |
|------------------------------------------------|-----------------------------------------------------------------------------------------|-----------------------------------------|
| testPersonConstructorFirstName                 | Constructor should correctly set firstName                                              | 'Olamikun'                              |
| testPersonConstructorLastName                  | Constructor should correctly set lastName                                               | firstName: 'Olamikun' lastName: 'Aluko' |
| testPersonSetAndGetFirstName                   | method should modify firstName                                                          | 'Batman'                                |
| testPersonSetAndGetLastName                    | method should modify lastName                                                           | 'Gotham'                                |
| testPersonSetAndGetDateOfBirth                 | method should modify date of birth                                                      | '2024-12-01'                            |
| testPersonParseDateForDateOfBirthInvalidLogic  | date of birth cannot be too far back (farther than 120 years) or negative (less than 0) | '1900-01-01' '2025-01-01'               |
| testPersonParseDateForDateOfBirthInvalidFormat | should not parse 'DD MM YYYY'                                                           | '2024-01-01'                            |
| testPersonSetAndGetComments                    | method should modify comments                                                           | 'Just got here'                         |
| testPersonSetAndGetGender                      | method should modify gender                                                             | Gender.BOY                              |
| testPersonSetAndGetDietaryRestriction          | method should modify dietary restrictions                                               | DietaryRestrictions.AVML                |

### DisasterVictimTest:

| Test Name                                        | Description                                 | Test Value(s)     |
|--------------------------------------------------|---------------------------------------------|-------------------|
| testDisasterVictimConstructorFirstName           | Constructor should correctly set firstName  | 'Olamikun'        |
| testDisasterVictimConstructorEntryDate           | Constructor should correctly set entry date | '2024-01-01'      |
| testDisasterVictimSetAndGetMedicalRecords        | method should modify medical records        | new MedicalRecord |
| testDisasterVictimImplementUpdateVictimInterface | class should implement 'UpdateVictim'       | d                 |

Here are some **Personal Tips**

* Throw a __new__ kind of exception
* Pay attention to getters, setters and constructors. Write detailed tests for all classes, and everything.
* Typically, we only test __public__ methods and data members.

### Common Assert Methods

* assertEquals
* assertNotNull
* assertSame
* assertTrue
* assertFalse