package supermarket.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import supermarket.exception.InvalidProductException;
import supermarket.model.Product;
import supermarket.util.ComparatorUtil;
import supermarket.util.ValidationUtil;

public class ProductService {

    // List
        private final List<Product> products = new ArrayList<>();

    // Map
        private final Map<Integer, Product> productMap = new HashMap<>();


    // Add product
    public void addProduct(Product product) {

        ValidationUtil.validateProduct(product);

        if (productMap.containsKey(product.getProductId())) {
            throw new InvalidProductException(
                    "A product with ID " + product.getProductId() + " already exists."
            );
        }

        products.add(product);

        productMap.put(
                product.getProductId(),
                product
        );

        System.out.println("Product added successfully.");
    }


    // View all products
    public void viewProducts() {

        if (products.isEmpty()) {

            System.out.println("No products available.");

            return;
        }

        for (Product product : products) {

            System.out.println(product);
        }
    }


    // Find product using Map and Optional
    public Optional<Product> findProductById(int productId) {

        return Optional.ofNullable(
                productMap.get(productId)
        );
    }


    // Return product list
    public List<Product> getProducts() {

        return products;
    }


    // Stream API - category search
    public void findProductsByCategory(String category) {

        products.stream()
                .filter(product ->
                        product.getCategory()
                                .equalsIgnoreCase(category))
                .forEach(System.out::println);
    }


    // Stream API - price filter
    public void findProductsBelowPrice(double price) {

        products.stream()
                .filter(product ->
                        product.getPrice() < price)
                .forEach(System.out::println);
    }


    // Sort by name
    public void sortByName() {

        products.sort(
                ComparatorUtil.sortByName()
        );

        System.out.println(
                "\nProducts sorted by name:"
        );

        products.forEach(
                System.out::println
        );
    }


    // Sort by price
    public void sortByPrice() {

        products.sort(
                ComparatorUtil.sortByPrice()
        );

        System.out.println(
                "\nProducts sorted by price:"
        );

        products.forEach(
                System.out::println
        );
    }


    // Sort by quantity
    public void sortByQuantity() {

        products.sort(
                ComparatorUtil.sortByQuantity()
        );

        System.out.println(
                "\nProducts sorted by quantity:"
        );

        products.forEach(
                System.out::println
        );
    }


    // Sort by price descending
    public void sortByPriceDescending() {

        products.sort(
                ComparatorUtil.sortByPriceDescending()
        );

        System.out.println(
                "\nProducts sorted by price descending:"
        );

        products.forEach(
                System.out::println
        );
    }


    // Set - show unique categories
    public void showCategories() {

        Set<String> categories =
                new LinkedHashSet<>();

        for (Product product : products) {

            categories.add(
                    product.getCategory()
            );
        }

        System.out.println(
                "\n<------------ UNIQUE CATEGORIES --------------->"
        );

        for (String category : categories) {

            System.out.println(category);
        }
    }
}