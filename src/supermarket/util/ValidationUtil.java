package supermarket.util;

import supermarket.exception.InvalidProductException;
import supermarket.model.Product;

public class ValidationUtil {

    public static void validateProduct(Product product) {

        if (product == null) {

            throw new InvalidProductException(
                    "Product cannot be null."
            );
        }

        if (product.getProductId() <= 0) {

            throw new InvalidProductException(
                    "Product ID must be greater than 0."
            );
        }

        if (product.getProductName() == null ||
                product.getProductName().trim().isEmpty()) {

            throw new InvalidProductException(
                    "Product name cannot be empty."
            );
        }

        if (product.getCategory() == null ||
                product.getCategory().trim().isEmpty()) {

            throw new InvalidProductException(
                    "Category cannot be empty."
            );
        }

        if (product.getPrice() < 0) {

            throw new InvalidProductException(
                    "Price cannot be negative."
            );
        }

        if (product.getQuantity() < 0) {

            throw new InvalidProductException(
                    "Quantity cannot be negative."
            );
        }
    }
}