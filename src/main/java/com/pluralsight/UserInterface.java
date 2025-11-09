package com.pluralsight;
import java.util.Scanner;

public class UserInterface {

    private Scanner scanner = new Scanner(System.in);

    public int showHomeScreen() {
        System.out.println("\n=== WHAT YOU WANNA DO? ===");
        System.out.println("1) Start Order");
        System.out.println("0) Exit Shop");
        System.out.print("Enter choice (1 or 0): ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int showOrderMenu() {
        System.out.println("\n--- HURRY UP N BUY! ---");
        System.out.println("1) Build a Sandwich");
        System.out.println("2) Add a Drink");
        System.out.println("3) Add Fries");
        System.out.println("4) Checkout");
        System.out.print("Enter choice: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public Sandwich createSandwich() {
        System.out.print("Bread type (white, wheat, rye, wrap): ");
        String bread = scanner.nextLine();

        System.out.print("Sandwich size (Roll, Hero, SuperHero): ");
        String size = scanner.nextLine();

        System.out.print("Toasted? (yes/no): ");
        boolean toasted = scanner.nextLine().equalsIgnoreCase("yes");

        Sandwich sandwich = new Sandwich(bread, size, toasted);

        System.out.println("\n=== Add Toppings ===");

        // MEATS
        System.out.print("Do you want meat? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            while (true) {
                System.out.println("Available meats: steak, ham, salami, roast beef, chicken, bacon");
                System.out.print("Type meat name or 'no' to finish: ");
                String meat = scanner.nextLine().trim().toLowerCase();
                if (meat.equals("no")) break;

                System.out.print("Extra meat? (yes/no): ");
                boolean extra = scanner.nextLine().equalsIgnoreCase("yes");

                sandwich.addTopping(new Topping(meat, extra));
            }
        }

        // CHEESES
        System.out.print("Do you want cheese? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            while (true) {
                System.out.println("Available cheeses: american, provolone, cheddar, swiss");
                System.out.print("Type cheese name or 'no' to finish: ");
                String cheese = scanner.nextLine().trim().toLowerCase();
                if (cheese.equals("no")) break;

                System.out.print("Extra cheese? (yes/no): ");
                boolean extra = scanner.nextLine().equalsIgnoreCase("yes");

                sandwich.addTopping(new Topping(cheese, extra));
            }
        }

        // REGULAR TOPPINGS
        System.out.println("\nAdd regular toppings (lettuce, peppers, onions, tomatoes, jalapeños, cucumbers, pickles, guacamole, mushrooms). Type 'done' to finish.");
        while (true) {
            System.out.print("Topping: ");
            String topping = scanner.nextLine().trim().toLowerCase();
            if (topping.equals("no")) break;
            sandwich.addTopping(new Topping(topping, false));
        }

        // SAUCES
        System.out.println("\nAdd sauces (mayo, mustard, ketchup, ranch, bbq, jerk, vinaigrette). Type 'no' to finish.");
        while (true) {
            System.out.print("Sauce: ");
            String sauce = scanner.nextLine().trim().toLowerCase();
            if (sauce.equals("no")) break;
            sandwich.addTopping(new Topping(sauce, false));
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

    // Helpers to identify topping types
    private boolean isMeat(String topping) {
        String[] meats = {"steak", "ham", "salami", "roast beef", "chicken", "bacon"};
        for (String meat : meats)
            if (meat.equalsIgnoreCase(topping)) return true;
        return false;
    }

    private boolean isCheese(String topping) {
        String[] cheeses = {"american", "provolone", "cheddar", "swiss"};
        for (String cheese : cheeses)
            if (cheese.equalsIgnoreCase(topping)) return true;
        return false;
    }

    public Scanner getScanner() {
        return scanner;
    }
}