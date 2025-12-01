package com.zipcode.sports_equipment.repository;

import com.zipcode.sports_equipment.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    Optional<Product> findBySku(String sku);
    
    List<Product> findByBrandId(Long brandId);
    
    List<Product> findBySportCategory(String sportCategory);
    
    List<Product> findByProductType(String productType);
    
    List<Product> findByIsAvailable(Boolean isAvailable);
}