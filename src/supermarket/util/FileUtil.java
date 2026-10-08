package supermarket.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import supermarket.exception.InvalidProductException;
import supermarket.model.Product;

public class FileUtil {


    // Save products into Project.txt
    public static void saveProducts(
            List<Product> products) {

        try (FileWriter writer = new FileWriter("Project.txt")) {

            for (Product product : products) {

                writer.write(
                        product.getProductId() + "|" +
                                product.getProductName() + "|" +
                                product.getCategory() + "|" +
                                product.getPrice() + "|" +
                                product.getQuantity() +
                                "\n"
                );
            }

            System.out.println(
                    "Products saved to file successfully."
            );

        } catch (IOException e) {

            System.out.println(
                    "Error while saving products: " + e.getMessage()
            );
        }
    }


    // Read and validate products from Project.txt
    public static List<Product> loadProducts() {

        List<Product> products = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader("Project.txt"))) {

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {

                lineNumber++;
                String[] fields = line.split("\\|", -1);

                if (fields.length != 5) {
                    System.out.println(
                            "Skipping invalid record at line " + lineNumber + "."
                    );
                    continue;
                }

                try {
                    Product product = new Product(
                            Integer.parseInt(fields[0].trim()),
                            fields[1].trim(),
                            fields[2].trim(),
                            Double.parseDouble(fields[3].trim()),
                            Integer.parseInt(fields[4].trim())
                    );
                    ValidationUtil.validateProduct(product);
                    products.add(product);
                } catch (NumberFormatException | InvalidProductException e) {
                    System.out.println(
                            "Skipping invalid record at line " + lineNumber +
                                    ": " + e.getMessage()
                    );
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Error while reading products: " + e.getMessage()
            );
        }

        return products;
    }
}