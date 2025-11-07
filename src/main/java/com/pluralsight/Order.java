package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Sandwich> sandwiches;
    private List<Drink> drinks;
    private List<Fries> fries;

    public Order() {
        sandwiches = new ArrayList<>();
        drinks = new ArrayList<>();
        fries = new ArrayList<>();
    }

    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void addFries(Fries fry) {
        fries.add(fry);
    }

    public double getTotal() {
        double total = 0.0;
        for (Sandwich s : sandwiches) total += s.getTotalPrice();
        for (Drink d : drinks) total += d.getPrice();
        for (Fries f : fries) total += f.getPrice();
        return total;
    }

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
}
