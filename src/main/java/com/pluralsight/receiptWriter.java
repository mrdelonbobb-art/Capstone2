package com.pluralsight;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class receiptWriter {
    public static void writeReceipt(Order order) {
        try {
            //Create folder if missing
            File folder = new File("receipts");
            if (!folder.exists()) folder.mkdir();

            // File name uses timestamp
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
            File file = new File(folder, "receipt-" + timestamp + ".csv");

            // Write CSV content
            try (FileWriter writer = new FileWriter(file)) {
                // Write CSV header
                writer.write("OrderID,Date,Item,Price\n");

                // Date for file
                String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                // Write sandwiches
                for (Sandwich s : order.getSandwiches()) {
                    writer.write(order + "," + date + ",Sandwich - " +"," +
                            String.format("%.2f", s.getTotalPrice()) + "\n");
                }

                // Write drinks
                for (Drink d : order.getDrinks()) {
                    writer.write(order + "," + date + ",Drink - " +
                            d.getSize() + "," +
                            String.format("%.2f", d.getPrice()) + "\n");
                }

                // Write fries
                for (Fries f : order.getFries()) {
                    writer.write(order + "," + date + ",Fries - " +
                            f.getSize() + "," +
                            String.format("%.2f", f.getPrice()) + "\n");
                }

                // Write total
                writer.write(order + "," + date + ",TOTAL," +
                        String.format("%.2f", order.getTotal()) + "\n");
            }

            System.out.println("✅ Receipt saved to CSV: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("❌ Error writing receipt: " + e.getMessage());
        }
    }
}