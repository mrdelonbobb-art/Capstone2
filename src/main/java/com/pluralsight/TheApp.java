package com.pluralsight;

public class TheApp {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        receiptWriter writer = new receiptWriter("receipts/receipt.csv");
        Order order = null;
        writer.saveOrder(order);

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
            order = new Order();
            boolean ordering = true;

            while (ordering) {
                int choice = ui.showOrderMenu();

                switch (choice) {
                    case 1 -> {
                        Sandwich sandwich = ui.createSandwich();
                        order.addSandwich(sandwich);
                        System.out.println("Sandwich added!");
                    }
                    case 2 -> {
                        Drink drink = ui.createDrink();
                        order.addDrink(drink);
                        System.out.println("Drink added!");
                    }
                    case 3 -> {
                        Fries fries = ui.createFries();
                        order.addFries(fries);
                        System.out.println(" Fries added!");
                    }
                    case 4 -> {
                        // Checkout
                        System.out.println("\n====================================");
                        System.out.println("           ORDER CHECKOUT           ");
                        System.out.println("====================================");

                        System.out.println(order.getSummary());
                        System.out.printf("TOTAL DUE: $%.2f%n", order.getTotal());
                        System.out.println("====================================");

                        //  Save receipt to CSV using instance writer
                        writer.writeReceipt(order);

                        System.out.println("\n Thank you for your order!");
                        System.out.println("Your receipt has been saved to CSV.\n");

                        ordering = false;
                    }
                    default -> System.out.println(" Invalid choice, please try again.");
                }
            }

            // Ask if user wants another order
            System.out.print("\nWould you like anything else? (yes/no): ");
            String again = ui.getScanner().nextLine();
            if (!again.equalsIgnoreCase("yes")) {
                running = false;
                System.out.println("Later! Come again soon!");
            }
        }
    }
}