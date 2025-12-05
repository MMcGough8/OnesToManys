package com.zipcode.sports_equipment.controller;

import com.zipcode.sports_equipment.entity.Brand;
import com.zipcode.sports_equipment.repository.BrandRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@CrossOrigin(origins = "*")
public class BrandController {

    private final BrandRepository brandRepository;

    public BrandController(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @GetMapping
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @GetMapping("/{id}")
    public Brand getBrandById(@PathVariable Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found: " + id));
    }

    @GetMapping("/active")
    public List<Brand> getActiveBrands() {
        return brandRepository.findByIsActive(true);
    }

        @GetMapping("/{id}/products")
    public ResponseEntity<?> getBrandProducts(@PathVariable Long id) {
        return brandRepository.findById(id)
                .map(brand -> ResponseEntity.ok(brand.getProducts()))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createBrand(@RequestBody Brand brand) {
        try {
            if (brand.getName() == null || brand.getName().trim().isEmpty()) {
                return ResponseEntity
                .badRequest()
                .body("Brand name is required");
            }
            if (brandRepository.findByName(brand.getName()).isPresent()) {
                return ResponseEntity
                .badRequest()
                .body("Brand with name '" + brand.getName() + "' already exists");
            }
            if (brand.getIsActive() == null) {
                brand.setIsActive(true);
            }
            Brand savedBrand = brandRepository.save(brand);
            
            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedBrand);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating brand: " + e.getMessage());
        }
    }
}
