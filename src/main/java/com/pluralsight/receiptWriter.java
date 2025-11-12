package com.pluralsight;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class receiptWriter {
    private String filePath; // path to receipt.csv file
    private ArrayList<Order> orders = new ArrayList<>();
    private static final String HEADER = "OrderID,Date,Item,Price";
    private static int nextOrderId = 1;
    private static final String NEWLINE = System.lineSeparator();

    public receiptWriter(String filePath) {
        this.filePath = filePath;
        createFileIfMissing();
        loadOrders();
    }

    private void createFileIfMissing() {
        Path path = Path.of(filePath);
        if (Files.exists(path)) return;

        try {
            File parent = path.toFile().getParentFile();
            if (parent != null && !parent.exists()) parent.mkdirs();

            try (BufferedWriter writer = Files.newBufferedWriter(Path.of(filePath), StandardOpenOption.APPEND))
            {

                writer.write(HEADER);
                writer.write(NEWLINE);
            }
            System.out.println("✅ Created new receipt.csv file.");
        } catch (IOException e) {
            System.out.println("⚠️ Error creating CSV: " + e.getMessage());
        }
    }

    private void loadOrders() {
        try {
            for (String line : Files.readAllLines(Path.of(filePath))) {
                if (line.startsWith("OrderID") || line.isBlank()) continue;
                nextOrderId++; // increment for each previous line
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error loading receipts: " + e.getMessage());
        }
    }

    public void saveOrder(Order order) {
        if (order == null) {
            System.out.println("⚠️ Cannot save: order is null.");
            return;
        }

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(filePath), StandardOpenOption.APPEND)) {
            String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            int orderId = nextOrderId++;

            // Sandwiches
            for (Sandwich s : order.getSandwiches()) {
                writer.write(NEWLINE);
            }

            // Drinks
            for (Drink d : order.getDrinks()) {
                writer.write(orderId + "," + date + ",Drink-" + d.getSize() + "," + String.format("%.2f", d.getPrice()));
                writer.write(NEWLINE);
            }

            // Fries
            for (Fries f : order.getFries()) {
                writer.write(orderId + "," + date + ",Fries-" + f.getSize() + "," + String.format("%.2f", f.getPrice()));
                writer.write(NEWLINE);
            }

            // Total
            writer.write(orderId + "," + date + ",TOTAL," + String.format("%.2f", order.getTotal()));
            writer.write(NEWLINE);

            orders.add(order);
            System.out.println("Order #" + orderId + " saved to " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving order: " + e.getMessage());
        }
    }

    public void displayOrders() {
        if (orders.isEmpty()) {
            System.out.println("No saved receipts yet.");
            return;
        }
        System.out.println("Saved Receipts:");
        for (Order o : orders) {
            System.out.println(o.getSummary());
        }
    }

    public void writeReceipt(Order order) {

    }
}