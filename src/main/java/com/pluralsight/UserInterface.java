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
        System.out.print("Do you want meat on this sandwich? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            while (true) {
                System.out.println("Meat Available : steak, ham, salami, roast beef, chicken, bacon");
                System.out.print("Type meat name,then type 'done' when finished: ");
                String meat = scanner.nextLine().trim().toLowerCase();
                if (meat.equals("done")) break;

                System.out.print("Extra meat? ⏸\uFE0F (yes/no): ");
                boolean extra = scanner.nextLine().equalsIgnoreCase("yes");
                sandwich.addTopping(new Topping(meat, extra));
                if(!extra) break; //breaks loop from asking again
            }
        }

        // CHEESES
        System.out.print("Do you want cheese? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            while (true) {
                System.out.println("Available cheeses: american, provolone, cheddar, swiss");
                System.out.print("Type cheese name, then type 'done' when finished: ");
                String cheese = scanner.nextLine().trim().toLowerCase();
                if (cheese.equals("done")) break;

                System.out.print("Extra cheese? (yes/no): ");
                boolean extra = scanner.nextLine().equalsIgnoreCase("yes");
                 sandwich.addTopping(new Topping(cheese, extra));
                if(!extra) break;  //added to break loop of asking for cheese after saying no
            }
        }

        // REGULAR TOPPINGS
        System.out.println("\nAdd regular toppings (lettuce, peppers, onions, tomatoes, jalapeños, cucumbers, pickles, guacamole, mushrooms). Type 'done' to finish.");
        while (true) {
            System.out.print("Topping: ");
            String topping = scanner.nextLine().trim().toLowerCase();
            if (topping.equals("done")) break;
            sandwich.addTopping(new Topping(topping, false));
        }

        // SAUCES
        System.out.println("\nAdd sauces (mayo, mustard, ketchup, ranch, bbq, jerk, vinaigrette). Type 'done' to finish.");
        while (true) {
            System.out.print("Sauce: ");
            String sauce = scanner.nextLine().trim().toLowerCase();
            if (sauce.equals("done")) break;
            sandwich.addTopping(new Topping(sauce, false));
        }

        return sandwich;
    }

    public Drink createDrink() {
        System.out.println("\n=== DRINK OPTIONS ===");
        System.out.println("1) Soda");
        System.out.println("2) Juice");
        System.out.println("3) Natural Fruit Smoothie");
        System.out.print("Choose your drink: ");
        String choice = scanner.nextLine();

        String type;
        String size;


        switch (choice) {
            case "1" -> type = "soda";
            case "2" -> type = "juice";
            case "3" -> type = "smoothie";
            default -> {
                System.out.println("Invalid choice, defaulting to soda.");
                type = "soda";
            }
        }

        // Set size options
        if (type.equals("smoothie")) {
            System.out.println("Smoothie sizes: regular ($6) or large ($8)");
            System.out.print("Enter size: ");
            size = scanner.nextLine().trim().toLowerCase();
            if (!size.equals("regular") && !size.equals("large")) {
                System.out.println("Invalid size, defaulting to regular.");
                size = "regular";
            }
        } else {
            System.out.print("Choose size (small, medium, large): ");
            size = scanner.nextLine().trim().toLowerCase();
            if (!size.equals("small") && !size.equals("medium") && !size.equals("large")) {
                System.out.println("Invalid size, defaulting to medium.");
                size = "medium";
            }
        }

        // Pick specific drink flavors
        String flavor = "";

        switch (type) {
            case "soda" -> {
                System.out.println("Available sodas: Sprite, Ginger Ale, Cream Soda, Coca-Cola, Pepsi, Seltzer Water");
                System.out.print("Choose your soda: ");
                flavor = scanner.nextLine().trim();
            }

            case "juice" -> {
                System.out.println("Available juices: Apple, Orange, Cranberry, Pineapple");
                System.out.print("Choose your juice: ");
                flavor = scanner.nextLine().trim();
            }

            case "smoothie" -> {
                System.out.println("Available fruits: Mango, Banana, Strawberry, Blueberry");
                System.out.println("Type fruit names one at a time (type 'done' when finished):");
                StringBuilder fruits = new StringBuilder();
                while (true) {
                    System.out.print("Fruit: ");
                    String fruit = scanner.nextLine().trim().toLowerCase();
                    if (fruit.equals("done")) break;
                    if (!fruit.isEmpty()) {
                        if (fruits.length() > 0) fruits.append(", ");
                        fruits.append(fruit);
                    }
                }

                System.out.println("Choose a base (name of juice,type of milk, or water/ice): ");
                String base = scanner.nextLine().trim().toLowerCase();

                flavor = fruits + " smoothie (base: " + base + ")";
            }
        }

        Drink drink = new Drink(type, size, flavor);
        System.out.println("Added: " + flavor + " - " + drink.getSummary());
        return drink;
    }
    public Fries createFries() {
        System.out.println("\n=== FRIES MENU ===");
        System.out.println("1) Regular Fries");
        System.out.println("2) Sweet Potato Fries (+$1.00)");
        System.out.print("Choose type: ");
        String typeChoice = scanner.nextLine().trim();

        String type;
        double extraCost = 0.0;

        if (typeChoice.equals("2")) {
            type = "sweet potato";
            extraCost += 1.00;
        } else {
            type = "regular";
        }

        System.out.println("Choose fry style:");
        System.out.println("1) Home Style");
        System.out.println("2) Waffle Fries (+$0.50)");
        System.out.print("Enter choice: ");
        String styleChoice = scanner.nextLine().trim();

        String style;
        if (styleChoice.equals("2")) {
            style = "waffle";
            extraCost += 0.50;
        } else {
            style = "home style";
        }

        System.out.print("Choose size (small, medium, large): ");
        String size = scanner.nextLine().trim().toLowerCase();

        // Create fries with type, style, and base size
        Fries fries = new Fries(size, type, style, extraCost);

        System.out.println(" Added: " + fries.getSummary());
        return fries;
    }
    public Scanner getScanner() {
        return scanner;
    }
}