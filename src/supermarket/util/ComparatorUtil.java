package supermarket.util;

import supermarket.model.Product;

import java.util.Comparator;

public class ComparatorUtil {

    public static Comparator<Product> sortByName() {

        return Comparator.comparing(
                Product::getProductName
        );
    }

    public static Comparator<Product> sortByPrice() {

        return Comparator.comparingDouble(
                Product::getPrice
        );
    }

    public static Comparator<Product> sortByQuantity() {

        return Comparator.comparingInt(
                Product::getQuantity
        );
    }

    public static Comparator<Product> sortByPriceDescending() {

        return Comparator.comparingDouble(
                Product::getPrice
        ).reversed();
    }
}