package com.zipcode.sports_equipment.controller;

import com.zipcode.sports_equipment.entity.Brand;
import com.zipcode.sports_equipment.entity.Product;
import com.zipcode.sports_equipment.repository.BrandRepository;
import com.zipcode.sports_equipment.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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

            if (productRequest.containsKey("name")) {
                product.setName(productRequest.get("name").toString());
            } else {
                return ResponseEntity.badRequest().body("Product name is required");
            }

            if (productRequest.containsKey("sku")) {
                String sku = productRequest.get("sku").toString();
                if (productRepository.findBySku(sku).isPresent()) {
                    return ResponseEntity.badRequest().body("SKU already exists");
                }
                product.setSku(sku);
            } else {
                return ResponseEntity.badRequest().body("SKU is required");
            }

            if (productRequest.containsKey("price")) {
                product.setPrice(new BigDecimal(productRequest.get("price").toString()));
            } else {
                return ResponseEntity.badRequest().body("Price is required");
            }

            if (productRequest.containsKey("sportCategory")) {
                product.setSportCategory(productRequest.get("sportCategory").toString());
            } else {
                return ResponseEntity.badRequest().body("Sport category is required");
            }

            if (productRequest.containsKey("productType")) {
                product.setProductType(productRequest.get("productType").toString());
            } else {
                return ResponseEntity.badRequest().body("Product type is required");
            }

            if (productRequest.containsKey("description")) {
                product.setDescription(productRequest.get("description").toString());
            }
            
            if (productRequest.containsKey("imageUrl")) {
                product.setImageUrl(productRequest.get("imageUrl").toString());
            }
            
            if (productRequest.containsKey("releaseDate")) {
                product.setReleaseDate(LocalDate.parse(productRequest.get("releaseDate").toString()));
            }
            
            if (productRequest.containsKey("stockQuantity")) {
                product.setStockQuantity(Integer.valueOf(productRequest.get("stockQuantity").toString()));
            } else {
                product.setStockQuantity(0);
            }
            
            if (productRequest.containsKey("isAvailable")) {
                product.setIsAvailable(Boolean.valueOf(productRequest.get("isAvailable").toString()));
            } else {
                product.setIsAvailable(true);
            }

            product.setBrand(brand);
            Product savedProduct = productRepository.save(product);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating product: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Map<String, Object> productRequest) {
        return productRepository.findById(id)
                .map(product -> {
                    try {
                        if (productRequest.containsKey("brandId")) {
                            Long brandId = Long.valueOf(productRequest.get("brandId").toString());
                            Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new RuntimeException("Brand not found"));
                            product.setBrand(brand);
                        }

                        if (productRequest.containsKey("name")) {
                            product.setName(productRequest.get("name").toString());
                        }
                        if (productRequest.containsKey("sku")) {
                            product.setSku(productRequest.get("sku").toString());
                        }
                        if (productRequest.containsKey("price")) {
                            product.setPrice(new BigDecimal(productRequest.get("price").toString()));
                        }
                        if (productRequest.containsKey("sportCategory")) {
                            product.setSportCategory(productRequest.get("sportCategory").toString());
                        }
                        if (productRequest.containsKey("productType")) {
                            product.setProductType(productRequest.get("productType").toString());
                        }
                        if (productRequest.containsKey("description")) {
                            product.setDescription(productRequest.get("description").toString());
                        }
                        if (productRequest.containsKey("imageUrl")) {
                            product.setImageUrl(productRequest.get("imageUrl").toString());
                        }
                        if (productRequest.containsKey("releaseDate")) {
                            product.setReleaseDate(LocalDate.parse(productRequest.get("releaseDate").toString()));
                        }
                        if (productRequest.containsKey("stockQuantity")) {
                            product.setStockQuantity(Integer.valueOf(productRequest.get("stockQuantity").toString()));
                        }
                        if (productRequest.containsKey("isAvailable")) {
                            product.setIsAvailable(Boolean.valueOf(productRequest.get("isAvailable").toString()));
                        }

                        Product updatedProduct = productRepository.save(product);
                        return ResponseEntity.ok(updatedProduct);
                        
                    } catch (Exception e) {
                        return ResponseEntity.badRequest().body("Error updating product: " + e.getMessage());
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return ResponseEntity.ok("Product deleted successfully");
                })
                .orElse(ResponseEntity.notFound().build());
    }
}