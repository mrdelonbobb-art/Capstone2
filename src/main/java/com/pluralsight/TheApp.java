package com.pluralsight;

public class TheApp {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();

        System.out.println("====================================");
        System.out.println("  YUUUUUR! Welcome to The Deli  ");
        System.out.println("====================================");

        boolean running = true;

        while (running) {
            int homeChoice = ui.showHomeScreen();

            if (homeChoice == 0) {
                System.out.println("Thanks for Coming! See You Later!");
                break;
            }

            // Start a new order
            Order order = new Order();
            boolean ordering = true; // creates loop

            while (ordering) {
                int choice = ui.showOrderMenu();

                switch (choice) {
                    case 1 -> {
                        // Build a sandwich
                        Sandwich sandwich = ui.createSandwich();
                        order.addSandwich(sandwich);
                        System.out.println("Sandwich added!");
                    }
                    case 2 -> {
                        // Add a drink
                        Drink drink = ui.createDrink();
                        order.addDrink(drink);
                        System.out.println("Drink added!");
                    }
                    case 3 -> {
                        // Add fries
                        Fries fries = ui.createFries();
                        order.addFries(fries);
                        System.out.println("Fries added!");
                    }
                    case 4 -> {
                        // Checkout
                        System.out.println("\n====================================");
                        System.out.println("           ORDER CHECKOUT           ");
                        System.out.println("====================================");

                        // Print order summary
                        System.out.println(order.getSummary());
                        System.out.printf("TOTAL DUE: $%.2f%n", order.getTotal()); //$%.2f%n  2 decimal places
                        System.out.println("====================================");

                        // Save receipt
                        receiptWriter.writeReceipt(order);

                        System.out.println("\nThank you for your order!");
                        System.out.println("Your receipt has been saved.\n");

                        ordering = false;
                    }
                    default -> System.out.println("Invalid choice, please try again.");
                }
            }

            // Ask if they want to place another order
            System.out.print("\nWould you like to place another order? (yes/no): ");
            String again = ui.getScanner().nextLine();
            if (!again.equalsIgnoreCase("yes")) {
                running = false;
                System.out.println("Goodbye! Come again soon!");
            }
        }
    }
}