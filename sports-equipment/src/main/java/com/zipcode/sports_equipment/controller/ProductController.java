package com.zipcode.sports_equipment.controller;

import com.zipcode.sports_equipment.entity.Product;
import com.zipcode.sports_equipment.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found: " + id));
    }

    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category) {
        return productRepository.findBySportCategory(category);
    }

    @GetMapping("/brand/{brandId}")
    public List<Product> getByBrand(@PathVariable Long brandId) {
        return productRepository.findByBrandId(brandId);
    }

    @GetMapping("/sku/{sku}")
    public Product getBySku(@PathVariable String sku) {
        return productRepository.findBySku(sku)
                .orElseThrow(() -> new RuntimeException("Product not found with SKU: " + sku));
    }
}