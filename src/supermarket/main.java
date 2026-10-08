package supermarket;

import java.util.Optional;
import supermarket.exception.InvalidProductException;
import supermarket.model.Product;
import supermarket.service.ProductService;
import supermarket.util.DataStore;
import supermarket.util.FileUtil;

public class main {

    public static void main(String[] args) {

        ProductService service =
                new ProductService();


        // ================= PRODUCTS =================

        Product p1 =
                new Product(
                        101,
                        "Rice",
                        "Grocery",
                        60,
                        20
                );

        Product p2 =
                new Product(
                        102,
                        "Milk",
                        "Dairy",
                        40,
                        15
                );

        Product p3 =
                new Product(
                        103,
                        "Bread",
                        "Bakery",
                        45,
                        10
                );

        Product p4 =
                new Product(
                        104,
                        "Ice Cream",
                        "Frozen Foods",
                        80,
                        1
                );


        // ================= ADD PRODUCTS =================

        service.addProduct(p1);
        service.addProduct(p2);
        service.addProduct(p3);
        service.addProduct(p4);


        // ================= VIEW PRODUCTS =================

        System.out.println(
                "\n<------------ ALL PRODUCTS----------->"
        );

        service.viewProducts();


        // ================= SEARCH PRODUCT =================

        System.out.println(
                "\n<------ PRODUCT LOOKUP (MAP + OPTIONAL) ------>"
        );

        Optional<Product> foundProduct = service.findProductById(102);
        if (foundProduct.isPresent()) {
            System.out.println(
                    "Found: " + foundProduct.get()
            );
        } else {
            System.out.println("Product not found.");
        }


        // ================= VALIDATION =================

        System.out.println(
                "\n<----------- VALIDATION TEST ---------->"
        );

        try {

            Product invalidProduct =
                    new Product(
                            105,
                            "Shampoo",
                            "Personal Care",
                            -100,
                            10
                    );

            service.addProduct(
                    invalidProduct
            );

        } catch (InvalidProductException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }


        // ================= STREAM API =================

        System.out.println(
                "\n<-----------Stream Testing--------->"
        );

        service.findProductsBelowPrice(50);

        System.out.println(
                "\n<----------- SORT BY PRICE --------------->"
        );

        service.sortByPrice();


        // ================= SET =================

        service.showCategories();


        // ================= GENERICS =================

        DataStore<Product> productStore =
                new DataStore<>();

        productStore.add(p1);
        productStore.add(p2);
        productStore.add(p3);
        productStore.add(p4);

        System.out.println(
                "\n<---------- GENERIC DATA STORE ------------->"
        );

        for (Product product :
                productStore.getAll()) {

            System.out.println(product);
        }

        System.out.println(
                "Total products: " +
                        productStore.size()
        );


        // ================= FILE I/O =================

        System.out.println(
                "\n<---------- FILE I/O ------------->"
        );

        FileUtil.saveProducts(
                service.getProducts()
        );

        System.out.println(
                "\n<---------- PRODUCTS LOADED FROM FILE ---------->"
        );

        for (Product product : FileUtil.loadProducts()) {
            System.out.println(product);
        }
    }
}