package com.pluralsight;

public class Fries extends Sandwich {
    private String size;
    private double price;

    public Fries(String size) {
        super("none", 0, false);
        this.size = size.toLowerCase();
        setPrice();
    }

    private void setPrice() {
        switch (size) {
            case "small" -> price = 2.00;
            case "medium" -> price = 3.00;
            case "large" -> price = 4.00;
            default -> price = 0.0;
        }
    }

    public double getPrice() {
        return price;
    }

    public String getSummary() {
        return String.format("Fries (%s) - $%.2f\n", size, price);
    }
}
