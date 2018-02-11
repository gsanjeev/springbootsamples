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
@RequestMapping("/posts")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Cacheable(value = "post-single", key = "#id", unless = "#result.shares < 500")
    @GetMapping("/{id}")
    public Product getProductByID(@PathVariable String id) throws ProductNotFoundException {
        log.info("get post with id {}", id);
        return productService.getProductByID(id);
    }

    @CachePut(value = "post-single", key = "#post.id")
    @PutMapping("/update")
    public Product updateProductByID(@RequestBody Product post) throws ProductNotFoundException {
        log.info("update post with id {}", post.getId());
        productService.updateProduct(post);
        return post;
    }

    @CacheEvict(value = "post-single", key = "#id")
    @DeleteMapping("/delete/{id}")
    public void deleteProductByID(@PathVariable String id) throws ProductNotFoundException {
        log.info("delete post with id {}", id);
        productService.deleteProduct(id);
    }

    @Cacheable(value = "post-top")
    @GetMapping("/top")
    public List<Product> getTopProducts() {
        return productService.getTopProducts();
    }

    @CacheEvict(value = "post-top")
    @GetMapping("/top/evict")
    public void evictTopProducts() {
        log.info("Evict post-top");
    }

}

