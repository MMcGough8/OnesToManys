package com.zipcode.sports_equipment.controller;

import com.zipcode.sports_equipment.entity.Brand;
import com.zipcode.sports_equipment.repository.BrandRepository;
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
}