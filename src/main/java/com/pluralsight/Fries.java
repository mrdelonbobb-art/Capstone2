package com.pluralsight;

public class Fries {
    private String size; // small, medium, large
    private double price;

    public Fries(String size) {
        this.size = size.toLowerCase();
        setPrice();
    }

    private void setPrice() {
        switch (size) {
            case "small" -> price = 2.00;
            case "medium" -> price = 3.00;
            case "large" -> price = 4.00;
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
        return String.format("Fries (%s) - $%.2f", size, price);
    }

    @Override
    public String toString() {
        return getSummary();
    }
}