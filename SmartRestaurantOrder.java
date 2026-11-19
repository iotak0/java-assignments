// TODO: 
//  Roll Number: 202510346

import java.util.InputMismatchException;
import java.util.Scanner;

public class SmartRestaurantOrder {
    static final double DRINK_PRICE = 15.50;
    static final double STUDENT_DISCOUNT = 0.10;
    static final double SENIOR_DISCOUNT = 0.05;
    static final double TIP_RATE = 0.10;

    public static void main(String[] args) {

        // Take order details from the user
        try (Scanner scanner = new Scanner(System.in)) {

            // Get customer inputs
            System.out.print("Enter customer's name: ");
            String customerName = scanner.nextLine();

            System.out.print("Enter customer's age: ");
            int age = scanner.nextInt();

            System.out.print("Enter price of the meal: ");
            double mealPrice = scanner.nextDouble();

            System.out.print("Enter number of drinks ordered: ");
            int numberOfDrinks = scanner.nextInt();

            System.out.print("Is the customer a student (true/false): ");
            boolean isStudent = scanner.nextBoolean();

            System.out.print("Enter service rating (1 to 5): ");
            int rating = scanner.nextInt();

            // Validate rating and calculate bill
            if (rating < 1 || rating > 5) {
                System.out.println("Error: Service rating must be between 1 and 5.");
            } else {
                double drinksCost = numberOfDrinks * DRINK_PRICE;
                double subtotal = mealPrice + drinksCost;

                // Determine discount rate
                double discountRate;
                if (isStudent && age >= 60) {
                    discountRate = STUDENT_DISCOUNT;
                } else if (isStudent) {
                    discountRate = STUDENT_DISCOUNT;
                } else if (age >= 60) {
                    discountRate = SENIOR_DISCOUNT;
                } else {
                    discountRate = 0.0;
                }

                double discountAmount = subtotal * discountRate;

                // Calculate tip
                double tipAmount = 0.0;
                if (rating == 4 || rating == 5) {
                    tipAmount = subtotal * TIP_RATE;
                }

                double finalBill = subtotal - discountAmount + tipAmount;
                int wholeAmount = (int) finalBill;

                // Print receipt
                System.out.println("\nSMART RESTAURANT");
                System.out.println("Customer: " + customerName);
                System.out.printf("Meal: $%.2f%n", mealPrice);
                System.out.printf("Drinks: $%.2f%n", drinksCost);
                System.out.printf("Discount: $%.2f%n", discountAmount);
                System.out.printf("Tip: $%.2f%n", tipAmount);
                System.out.printf("Final bill: $%.2f%n", finalBill);
                System.out.println("Whole amount: $" + wholeAmount);
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input.");
        }
    }
}