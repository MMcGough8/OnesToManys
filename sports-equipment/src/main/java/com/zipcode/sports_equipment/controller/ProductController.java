package com.zipcode.sports_equipment.controller;

import com.zipcode.sports_equipment.entity.Brand;
import com.zipcode.sports_equipment.entity.Product;
import com.zipcode.sports_equipment.repository.ProductRepository;
import com.zipcode.sports_equipment.repository.BrandRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;

    public ProductController(ProductRepository productRepository, BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found: " + id));
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
        return productRepository.findBySku(sku).orElseThrow(() -> new RuntimeException("Product not found with SKU: " + sku));
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Map<String, Object> productRequest) {
        try {
       
            if (!productRequest.containsKey("brandId")) {
            return ResponseEntity.badRequest().body("brandId is required");
            }

            Long brandId = Long.valueOf(productRequest.get("brandId").toString());
            Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new RuntimeException("Brand not found with id: " + brandId));

            Product product = new Product();

            product.setBrand(brand);
            Product savedProduct = productRepository.save(product);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating product: " + e.getMessage());
        }
    }
    
}