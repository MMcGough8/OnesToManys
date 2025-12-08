package com.zipcode.sports_equipment;

import com.zipcode.sports_equipment.entity.Product;
import com.zipcode.sports_equipment.entity.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Product Entity Tests")
class ProductEntityTest {

    private Product product;
    private Brand brand;

    @BeforeEach
    void setUp() {
        brand = new Brand();
        brand.setId(1L);
        brand.setName("Nike");

        product = new Product();
        product.setId(1L);
        product.setName("Air Max");
        product.setSku("NIKE-AM-001");
        product.setPrice(new BigDecimal("159.99"));
        product.setSportCategory("Running");
        product.setProductType("Shoes");
        product.setDescription("Classic running shoes");
        product.setStockQuantity(100);
        product.setIsAvailable(true);
        product.setReleaseDate(LocalDate.of(2024, 1, 15));
        product.setBrand(brand);
    }

    @Test
    @DisplayName("Should create product with all fields")
    void testProductCreation() {
        assertNotNull(product);
        assertEquals(1L, product.getId());
        assertEquals("Air Max", product.getName());
        assertEquals("NIKE-AM-001", product.getSku());
        assertEquals(new BigDecimal("159.99"), product.getPrice());
        assertEquals("Running", product.getSportCategory());
        assertEquals("Shoes", product.getProductType());
        assertEquals("Classic running shoes", product.getDescription());
        assertEquals(100, product.getStockQuantity());
        assertTrue(product.getIsAvailable());
        assertEquals(LocalDate.of(2024, 1, 15), product.getReleaseDate());
        assertEquals(brand, product.getBrand());
    }

    @Test
    @DisplayName("Should use default stockQuantity value")
    void testDefaultStockQuantity() {
        Product newProduct = new Product();
        assertEquals(0, newProduct.getStockQuantity());
    }

    @Test
    @DisplayName("Should use default isAvailable value")
    void testDefaultIsAvailable() {
        Product newProduct = new Product();
        assertTrue(newProduct.getIsAvailable());
    }

    @Test
    @DisplayName("Should set and get brand")
    void testBrandRelationship() {
        Brand adidas = new Brand();
        adidas.setId(2L);
        adidas.setName("Adidas");

        product.setBrand(adidas);

        assertEquals(adidas, product.getBrand());
        assertEquals("Adidas", product.getBrand().getName());
    }

    @Test
    @DisplayName("Should create product with no-args constructor")
    void testNoArgsConstructor() {
        Product emptyProduct = new Product();
        assertNotNull(emptyProduct);
        assertNull(emptyProduct.getId());
        assertNull(emptyProduct.getName());
    }

    @Test
    @DisplayName("Should test equals and hashCode")
    void testEqualsAndHashCode() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setSku("TEST-001");

        Product product2 = new Product();
        product2.setId(1L);
        product2.setSku("TEST-001");

        assertEquals(product1, product2);
        assertEquals(product1.hashCode(), product2.hashCode());
    }
}