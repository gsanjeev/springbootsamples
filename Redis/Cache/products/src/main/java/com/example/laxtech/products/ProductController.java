package com.example.laxtech.products;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Cacheable(value = "product-single", key = "#id", unless = "#result.price.doubleValue() < 675.00")
    @GetMapping("/{id}")
    public Product getProductByID(@PathVariable String id) throws ProductNotFoundException {
        log.info("get product with id {}", id);
        return productService.getProductByID(id);
    }

    @CachePut(value = "product-single", key = "#product.id")
    @PutMapping("/update")
    public Product updateProductByID(@RequestBody Product product) throws ProductNotFoundException {
        log.info("update product with id {}", product.getId());
        productService.updateProduct(product);
        return product;
    }

    @CacheEvict(value = "product-single", key = "#id")
    @DeleteMapping("/delete/{id}")
    public void deleteProductByID(@PathVariable String id) throws ProductNotFoundException {
        log.info("delete product with id {}", id);
        productService.deleteProduct(id);
    }

    @Cacheable(value = "product-top") // sanjeev : this should not be cached or how to cache this kind of result?
    @GetMapping("/top")
    public List<Product> getTopProducts() {
        return productService.getTopProducts();
    }

    @CacheEvict(value = "product-top")
    @GetMapping("/top/evict")
    public void evictTopProducts() {
        log.info("Evict product-top");
    }

}

