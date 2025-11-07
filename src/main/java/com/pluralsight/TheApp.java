package com.pluralsight;

public class TheApp {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        Order order = new Order();

        int homeChoice = ui.showHomeScreen();
        if (homeChoice == 0) {
            System.out.println("Goodbye!");
            return;
        }

        while (true) {
            int choice = ui.showOrderMenu();
            switch (choice) {
                case 1 -> order.addSandwich(ui.createSandwich());
                case 2 -> order.addDrink(ui.createDrink());
                case 3 -> order.addFries(ui.createFries());
                case 4 -> {
                    System.out.println(order.getSummary());
                    receiptWriter.writeReceipt(order);
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}