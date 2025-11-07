package com.pluralsight;

public class Drink {
    private String size; // small, medium, large
    private double price;

    public Drink(String size) {
        this.size = size.toLowerCase();
        setPrice();
    }

    private void setPrice() {
        switch (size) {
            case "small" -> price = 1.50;
            case "medium" -> price = 2.00;
            case "large" -> price = 2.50;
            default -> price = 0.0; // unknown size
        }
    }

    public double getPrice() {
        return price;
    }

    public String getSize() {
        return size;
    }

    public String getSummary() {
        return String.format("Drink (%s) - $%.2f", size, price);
    }

    @Override
    public String toString() {
        return getSummary();
    }
}