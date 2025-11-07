package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    private String breadType;
    private int size;
    private boolean toasted;
    private List<Topping> toppings;
    private double basePrice;

    public Sandwich(String breadType, int size, boolean toasted) {
        this.breadType = breadType;
        this.size = size;
        this.toasted = toasted;
        this.toppings = new ArrayList<>();
        setBasePrice();
    }

    private void setBasePrice() {
        switch (size) {
            case 4 -> basePrice = 5.50;
            case 8 -> basePrice = 7.00;
            case 12 -> basePrice = 8.50;
            default -> basePrice = 0.0;
        }
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public double getTotalPrice() {
        double total = basePrice;
        for (Topping t : toppings) {
            total += t.getPrice(size);
        }
        return total;
    }

    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Sandwich (%d\") on %s bread%s\n", size, breadType, toasted ? " [Toasted]" : ""));
        sb.append("Toppings:\n");
        for (Topping t : toppings) {
            sb.append("  - ").append(t.toString(size)).append("\n");
        }
        sb.append(String.format("  Sandwich Price: $%.2f\n", getTotalPrice()));
        return sb.toString();
    }
}