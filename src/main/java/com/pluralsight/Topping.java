package com.pluralsight;

public class Topping extends Sandwich {
    private String name;
    private boolean isExtra;

    // Meats pricing by sandwich size
    public Topping(String name, boolean isExtra) {
        super("none", 0, false);
        this.name = name;
        this.isExtra = isExtra;
    }

    public double getPrice(int sandwichSize) {
        double price = 0.0;
        // Only meats cost extra
        switch (name.toLowerCase()) {
            case "steak", "ham", "salami", "roast beef", "chicken", "bacon" -> {
                if (sandwichSize == 4) price = 1.00;
                else if (sandwichSize == 8) price = 2.00;
                else if (sandwichSize == 12) price = 3.00;
            }
        }

        // Extra toppings cost 50% more
        if (isExtra) price *= 1.5;
        return price;
    }

    public String toString(int sandwichSize) {
        double price = getPrice(sandwichSize);
        String extraText = isExtra ? " (extra)" : "";
        return String.format("%s%s - $%.2f", name, extraText, price);
    }
}