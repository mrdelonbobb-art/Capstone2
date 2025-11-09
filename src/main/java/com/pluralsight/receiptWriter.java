package com.pluralsight;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class receiptWriter {
    private String filePath; // not static — instance variable

    public receiptWriter(String filePath) {
        this.filePath = filePath;
    }

    public void writeReceipt(Order order) {
        File file = new File(filePath);

        try {
            // ✅ Create parent folder if it doesn't exist
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            boolean fileExists = file.exists();

            // ✅ Append mode
            try (FileWriter writer = new FileWriter(file, true)) {
                // Write CSV header only once if new file
                if (!fileExists) {
                    writer.write("OrderID,Date,Item,Price\n");
                }

                String date = LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                );

                // ✅ Write sandwiches
                for (Sandwich s : order.getSandwiches()) {
                    writer.write(order.getId() + "," + date + ",Sandwich - " +
                            s.getSummary().replace(",", " ") + "," +
                            String.format("%.2f", s.getTotalPrice()) + "\n");
                }

                // ✅ Write drinks
                for (Drink d : order.getDrinks()) {
                    writer.write(order.getId() + "," + date + ",Drink - " +
                            d.getSize() + "," +
                            String.format("%.2f", d.getPrice()) + "\n");
                }

                // ✅ Write fries
                for (Fries f : order.getFries()) {
                    writer.write(order.getId() + "," + date + ",Fries - " +
                            f.getSize() + "," +
                            String.format("%.2f", f.getPrice()) + "\n");
                }

                // ✅ Write total
                writer.write(order.getId() + "," + date + ",TOTAL," +
                        String.format("%.2f", order.getTotal()) + "\n");
            }

            System.out.println("✅ Transaction saved to CSV: " + file.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("❌ Error writing receipt: " + e.getMessage());
        }
    }
}
