package com.pluralsight;

public class Drink extends Sandwich {
    private String size;
    private double price;

    public Drink(String size) {
        super("none", 0, false);
        this.size = size.toLowerCase();
        setPrice();
    }

    private void setPrice() {
        switch (size) {
            case "small" -> price = 1.50;
            case "medium" -> price = 2.00;
            case "large" -> price = 2.50;
            default -> price = 0.0;
        }
    }

    public double getPrice() {
        return price;
    }

    public String getSummary() {
        return String.format("Drink (%s) - $%.2f\n", size, price);
    }
}
