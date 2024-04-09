package edu.ucalgary.oop;

import java.util.Scanner;

public class JustTesting {

    public static void main(String[] args) {
        // Test error handling
        Scanner scanner = new Scanner(System.in);

        // Slag use scanner.nextLine() to fix buffer issues

        while (true) {
            try {
                System.out.println("Enter a string: ");
                String string = scanner.nextLine();
                System.out.println("You entered: " + string);
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }

        while (true) {
            try {
                System.out.println("Enter a string: ");
                String string = scanner.nextLine();
                System.out.println("You entered: " + string);
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }
}
