package com.example.laxtech.products;


import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

    @Service
    public class ProductService {

        private List<Product> products;

        @PostConstruct
        public void init() {
            products = new ArrayList<>();
            products.add(new Product("IDX001", "Cyberpunk is near", "Description", 675.00, "https://random-cdn.products/images/xvn84934fnls.jpg", new ProductLine("Anna")));
            products.add(new Product("IDX002", "Welcome aboard of the hype train", "Description", 675.00, "https://random-cdn.products/images/xvn84934fnls.jpg", new ProductLine("Josh")));
            products.add(new Product("IDX003", "How to improve programming skills ", "Description", 75.00, "https://random-cdn.products/images/xvn84934fnls.jpg", new ProductLine("Kobe")));
            products.add(new Product("IDX004", "Top exercises for IT people", "Description", 675.00, "https://random-cdn.products/images/xvn84934fnls.jpg", new ProductLine("Leo")));
            products.add(new Product("IDX005", "Case study of 75 years project", "Description", 675.00, "https://random-cdn.products/images/xvn84934fnls.jpg",  new ProductLine("Tom")));
            products.add(new Product("IDX006", "Machine Learning", "Description", 675.00, "https://random-cdn.products/images/xvn84934fnls.jpg", new ProductLine("Alexa")));
            products.add(new Product("IDX007", "Memory leaks, how to find them ", "Description", 675.00, "https://random-cdn.products/images/xvn84934fnls.jpg", new ProductLine("Frank")));
            products.add(new Product("IDX008", "Robots builds robots", "Description", 675.00, "https://random-cdn.products/images/xvn84934fnls.jpg", new ProductLine("Milagros")));
            products.add(new Product("IDX009", "Quantum algorithms, from the scratch", "Description", 75.00, "https://random-cdn.products/images/xvn84934fnls.jpg",  new ProductLine("Sarah")));
            products.add(new Product("IDX010", "Coding, coding, coding", "Description", 675.00, "https://random-cdn.products/images/xvn84934fnls.jpg",  new ProductLine("Adam")));
        }

        public void updateProduct(Product product) {
            products = products.stream().filter(p -> p.getId() != product.getId()).collect(Collectors.toList());
            products.add(product);
        }

        public void deleteProduct(String productID) {
            products = products.stream().filter(p -> !p.getId().equals(productID)).collect(Collectors.toList());
        }


        /**
         * Load product content from DB *Long running method
         */
        public Product getProductByID(String productID) throws ProductNotFoundException {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return products.stream()
                    .filter(p -> p.getId().equals(productID))
                    .findFirst()
                    .orElseThrow(() -> new ProductNotFoundException("Cannot find product with id:" + productID));
        }


        /**
         * Load top ten products from DB *Long running method
         */
        public List<Product> getTopProducts() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return products;
        }
    }

