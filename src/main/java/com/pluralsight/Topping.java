package com.pluralsight;

import java.util.Arrays;
import java.util.List;

public class Topping {
    private String name;
    private boolean isExtra;

    // Define topping categories
    private static final List<String> MEATS = Arrays.asList("steak", "ham", "salami", "roast beef", "chicken", "bacon");
    private static final List<String> CHEESES = Arrays.asList("american", "provolone", "cheddar", "swiss");
    private static final List<String> REGULAR_TOPPINGS = Arrays.asList("lettuce", "peppers", "onions", "tomatoes", "jalapeños", "cucumbers", "pickles", "guacamole", "mushrooms");
    private static final List<String> SAUCES = Arrays.asList("mayo", "mustard", "ketchup", "ranch", "bbq", "jerk", "vinaigrette");

    public Topping(String name, boolean isExtra) {
        this.name = name.toLowerCase();
        this.isExtra = isExtra;
    }

    public double getPrice(String sandwichSize) {
        double price = 0.0;

        switch (sandwichSize.toLowerCase()) {
            case "roll" -> {
                if (MEATS.contains(name)) price = 1.00;
                else if (CHEESES.contains(name)) price = 0.75;
            }
            case "hero" -> {
                if (MEATS.contains(name)) price = 2.00;
                else if (CHEESES.contains(name)) price = 1.50;
            }
            case "superhero" -> {
                if (MEATS.contains(name)) price = 3.00;
                else if (CHEESES.contains(name)) price = 2.25;
            }
        }

        // Extra cost for extra meat or extra cheese
        if (isExtra) {
            if (MEATS.contains(name)) {
                switch (sandwichSize.toLowerCase()) {
                    case "roll" -> price += 0.50;
                    case "hero" -> price += 1.00;
                    case "superhero" -> price += 1.50;
                }
            } else if (CHEESES.contains(name)) {
                switch (sandwichSize.toLowerCase()) {
                    case "roll" -> price += 0.30;
                    case "hero" -> price += 0.60;
                    case "superhero" -> price += 0.90;
                }
            }
        }

        // Regular toppings and sauces are free
        return price;
    }

    public String toString(String sandwichSize) {
        double price = getPrice(sandwichSize);
        String extraText;

        if (isExtra) {
            extraText = " (extra)";
        } else {
            extraText = "";
        }

        return String.format("%s%s - $%.2f", capitalize(name), extraText, price);
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }

    public String getName() {
        return name;
    }

    public boolean isExtra() {
        return isExtra;
    }
}