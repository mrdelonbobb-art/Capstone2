package com.pluralsight;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class receiptWriter {

    private static final String MASTER_FILE = "receipts/receipt.csv"; // master CSV
    private static final String HEADER = "OrderID,Timestamp,Item,Details,Price";
    private static int nextOrderId = 1; // simple incremental order ID
    private ArrayList<Order> orders = new ArrayList<>();

    // Save order to master CSV (all orders in one file)
    public void saveOrder(Order order) {
        if (order == null) {
            System.out.println("⚠️ Cannot save: order is null.");
            return;
        }

        File folder = new File("receipts");
        if (!folder.exists()) folder.mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MASTER_FILE, true))) {
            File file = new File(MASTER_FILE);
            if (file.length() == 0) { // write header if file empty
                writer.write(HEADER);
                writer.newLine();
            }

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            int orderId = nextOrderId++;//add icriment on order id

            // Write sandwiches
            for (Sandwich s : order.getSandwiches()) {
                writer.write(orderId + "," + timestamp + ",Sandwich," +
                        s.toString().replace(",", ";") + "," + String.format("%.2f", s.getTotalPrice()));
                writer.newLine();
            }

            // Write drinks
            for (Drink d : order.getDrinks()) {
                writer.write(orderId + "," + timestamp + ",Drink," +
                        d.toString().replace(",", ";") + "," + String.format("%.2f", d.getPrice()));
                writer.newLine();
            }

            // Write fries
            for (Fries f : order.getFries()) {
                writer.write(orderId + "," + timestamp + ",Fries," +
                        f.toString().replace(",", ";") + "," + String.format("%.2f", f.getPrice()));
                writer.newLine();
            }

            // Write total
            writer.write(orderId + "," + timestamp + ",TOTAL,," + String.format("%.2f", order.getTotal()));
            writer.newLine();

            orders.add(order);
            System.out.println("Order saved to master CSV: " + MASTER_FILE);

        } catch (IOException e) {
            System.out.println("Error saving order: " + e.getMessage());
        }
    }

    // Create a separate CSV file for a single order with timestamp
    public void writeReceipt(Order order) {
        if (order == null) {
            System.out.println("⚠️ Cannot save: order is null.");
            return;
        }
    }
}