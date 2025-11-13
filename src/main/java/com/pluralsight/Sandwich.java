package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    private String breadType;
    private String size; // "Roll", "Hero", "SuperHero"
    private boolean toasted;

    private List<Topping> meats;
    private List<Topping> cheeses;
    private List<Topping> regularToppings;
    private List<Topping> sauces;

    private double basePrice;

    public Sandwich(String breadType, String size, boolean toasted) {
        this.breadType = breadType;
        this.size = size;
        this.toasted = toasted;

        this.meats = new ArrayList<>();
        this.cheeses = new ArrayList<>();
        this.regularToppings = new ArrayList<>();
        this.sauces = new ArrayList<>();

        setBasePrice();
    }

    private void setBasePrice() {
        switch (size.toLowerCase()) {
            case "roll" -> basePrice = 5.50;
            case "hero" -> basePrice = 7.00;
            case "superhero" -> basePrice = 8.50;
            default -> basePrice = 0.0;
        }
    }

    // Add topping to proper category
    public void addTopping(Topping topping) {
        String name = topping.getName().toLowerCase();
        if (isMeat(name)) meats.add(topping);
        else if (isCheese(name)) cheeses.add(topping);
        else if (isSauce(name)) sauces.add(topping);
        else regularToppings.add(topping);
    }

    private boolean isMeat(String topping) {
        String[] meats = {"steak", "ham", "salami", "roast beef", "chicken", "bacon","eggs"};
        for (String m : meats) if (m.equals(topping)) return true;
        return false;
    }

    private boolean isCheese(String topping) {
        String[] cheeses = {"american", "provolone", "cheddar", "swiss"};
        for (String c : cheeses) if (c.equals(topping)) return true;
        return false;
    }

    private boolean isSauce(String topping) {
        String[] sauces = {"mayo", "mustard", "ketchup", "ranch", "bbq", "jerk", "vinaigrette"};
        for (String s : sauces) if (s.equals(topping)) return true;
        return false;
    }

    public double getTotalPrice() {
        double total = basePrice;

        for (Topping t : meats) total += t.getPrice(size);
        for (Topping t : cheeses) total += t.getPrice(size);
        for (Topping t : regularToppings) total += t.getPrice(size);
        for (Topping t : sauces) total += t.getPrice(size);

        return total;
    }

    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Sandwich (%s) on %s bread%s\n",
                size, breadType, toasted ? " [Toasted]" : ""));
        sb.append("Toppings:\n");

        for (Topping t : meats) sb.append("  - ").append(t.toString(size)).append("\n");
        for (Topping t : cheeses) sb.append("  - ").append(t.toString(size)).append("\n");
        for (Topping t : regularToppings) sb.append("  - ").append(t.toString(size)).append("\n");
        for (Topping t : sauces) sb.append("  - ").append(t.toString(size)).append("\n");

        sb.append(String.format("  Sandwich Price: $%.2f\n", getTotalPrice()));
        return sb.toString();
    }
}
