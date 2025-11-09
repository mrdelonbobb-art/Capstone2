package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int nextId = 1000; // Auto-increment starting point
    private int id;                   // Unique order ID
    private List<Sandwich> sandwiches;
    private List<Drink> drinks;
    private List<Fries> fries;

    public Order() {
        this.id = nextId++; // Automatically assign unique ID
        sandwiches = new ArrayList<>();
        drinks = new ArrayList<>();
        fries = new ArrayList<>();
    }

    // Add items
    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void addFries(Fries fry) {
        fries.add(fry);
    }

    // Calculate total
    public double getTotal() {
        double total = 0.0;
        for (Sandwich s : sandwiches) total += s.getTotalPrice();
        for (Drink d : drinks) total += d.getPrice();
        for (Fries f : fries) total += f.getPrice();
        return total;
    }

    // Generate summary for screen/console display
    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n--- ORDER SUMMARY ---\n");
        for (Sandwich s : sandwiches) sb.append(s.getSummary()).append("\n");
        for (Drink d : drinks) sb.append(d.getSummary());
        for (Fries f : fries) sb.append(f.getSummary());
        sb.append("----------------------\n");
        sb.append(String.format("TOTAL: $%.2f%n", getTotal()));
        return sb.toString();
    }

    // Getters
    public int getId() {
        return id;
    }

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public List<Fries> getFries() {
        return fries;
    }


}