package com.pluralsight;

public class Drink {
    private String type; // soda, juice, smoothie
    private String size; // small, medium, large, regular
    private String flavor; // e.g. "Coca-Cola", "Mango + Banana Smoothie (Orange Base)"
    private double price;

    // Updated constructor
    public Drink(String type, String size) {
        this.type = type.toLowerCase();
        this.size = size.toLowerCase();
        this.flavor = ""; // default, can be set later
        setPrice();
    }

    // Overloaded constructor with flavor
    public Drink(String type, String size, String flavor) {
        this.type = type.toLowerCase();
        this.size = size.toLowerCase();
        this.flavor = flavor;
        setPrice();
    }

    // Set drink prices based on type and size
    private void setPrice() {
        switch (type) {
            case "smoothie" -> {
                if (size.equals("large")) price = 8.00;
                else price = 6.00; // default regular
            }
            case "juice" -> {
                switch (size) {
                    case "small" -> price = 2.50;
                    case "medium" -> price = 3.00;
                    case "large" -> price = 3.50;
                    default -> price = 3.00;
                }
            }
            case "soda" -> {
                switch (size) {
                    case "small" -> price = 1.50;
                    case "medium" -> price = 2.00;
                    case "large" -> price = 2.50;
                    default -> price = 2.00;
                }
            }
            default -> price = 0.0;
        }
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public double getPrice() {
        return price;
    }

    public String getSummary() {
        String flavorText = flavor.isEmpty() ? "" : " (" + flavor + ")";
        return String.format("%s %s%s - $%.2f",
                size.substring(0, 1).toUpperCase() + size.substring(1),
                type.substring(0, 1).toUpperCase() + type.substring(1),
                flavorText, price);
    }

    @Override
    public String toString() {
        return getSummary();
    }
}