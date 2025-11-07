package com.pluralsight;

import java.util.Scanner;


public class UserInterface {
    private Scanner scanner = new Scanner(System.in);

    public int showHomeScreen() {
        System.out.println("\n=== Welcome to Deli Order System ===");
        System.out.println("1) New Order");
        System.out.println("0) Exit");
        System.out.print("Enter choice: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int showOrderMenu() {
        System.out.println("\n--- Order Menu ---");
        System.out.println("1) Add Sandwich");
        System.out.println("2) Add Drink");
        System.out.println("3) Add Fries");
        System.out.println("4) Checkout");
        System.out.print("Enter choice: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public Sandwich createSandwich() {
        System.out.print("Bread type (white, wheat, rye, wrap): ");
        String bread = scanner.nextLine();

        System.out.print("Size (4, 8, 12): ");
        int size = Integer.parseInt(scanner.nextLine());

        System.out.print("Toasted? (yes/no): ");
        boolean toasted = scanner.nextLine().equalsIgnoreCase("yes");

        Sandwich sandwich = new Sandwich(bread, size, toasted);

        // Add toppings
        while (true) {
            System.out.print("Add topping (steak, ham, bacon, etc) or 'done': ");
            String toppingName = scanner.nextLine();
            if (toppingName.equalsIgnoreCase("done")) break;

            System.out.print("Extra? (yes/no): ");
            boolean extra = scanner.nextLine().equalsIgnoreCase("yes");

            sandwich.addTopping(new Topping(toppingName, extra));
        }

        return sandwich;
    }

    public Drink createDrink() {
        System.out.print("Drink size (small, medium, large): ");
        String size = scanner.nextLine();
        return new Drink(size);
    }

    public Fries createFries() {
        System.out.print("Fries size (small, medium, large): ");
        String size = scanner.nextLine();
        return new Fries(size);
    }
}