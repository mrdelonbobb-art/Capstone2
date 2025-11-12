package com.pluralsight;

public class Fries {
    private String size;   // small, medium, large
    private String type;   // regular or sweet potato
    private String style;  // home style or waffle
    private double price;

    public Fries(String size, String type, String style, double extraCost) {
        this.size = size.toLowerCase();
        this.type = type.toLowerCase();
        this.style = style.toLowerCase();
        setPrice(extraCost);
    }

    private void setPrice(double extraCost) {
        switch (size) {
            case "small" -> price = 2.00;
            case "medium" -> price = 3.00;
            case "large" -> price = 4.00;
            default -> price = 3.00;
        }
        price += extraCost; // add any style/type upcharge
    }

    public String getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public String getSummary() {
        return String.format("%s %s %s fries - $%.2f",
                size.substring(0, 1).toUpperCase() + size.substring(1),
                style,
                type,
                price);
    }

    @Override
    public String toString() {
        return getSummary();
    }
}