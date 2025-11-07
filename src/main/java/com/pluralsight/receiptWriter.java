package com.pluralsight;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class receiptWriter {
    public static void writeReceipt(Order order) {
        try {
            File folder = new File("receipts");
            if (!folder.exists()) folder.mkdir();

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
            File file = new File(folder, timestamp + ".txt");

            try (FileWriter writer = new FileWriter(file)) {
                writer.write(order.getSummary());
            }

            System.out.println("Receipt saved: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error writing receipt: " + e.getMessage());
        }
    }
}