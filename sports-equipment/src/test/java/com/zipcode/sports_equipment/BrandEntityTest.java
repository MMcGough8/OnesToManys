package com.zipcode.sports_equipment;

import com.zipcode.sports_equipment.entity.Brand;
import com.zipcode.sports_equipment.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Brand Entity Tests")
class BrandEntityTest {

    private Brand brand;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        brand = new Brand();
        brand.setId(1L);
        brand.setName("Nike");
        brand.setFoundingYear(1964);
        brand.setWebsite("www.nike.com");
        brand.setDescription("Just Do It");
        brand.setIsActive(true);
        brand.setProducts(new ArrayList<>());

        product1 = new Product();
        product1.setId(1L);
        product1.setName("Air Max");
        product1.setSku("NIKE-AM-001");
        product1.setPrice(new BigDecimal("159.99"));
        product1.setSportCategory("Running");
        product1.setProductType("Shoes");

        product2 = new Product();
        product2.setId(2L);
        product2.setName("Air Jordan");
        product2.setSku("NIKE-AJ-001");
        product2.setPrice(new BigDecimal("199.99"));
        product2.setSportCategory("Basketball");
        product2.setProductType("Shoes");
    }

    @Test
    @DisplayName("Should create brand with all fields")
    void testBrandCreation() {
        assertNotNull(brand);
        assertEquals(1L, brand.getId());
        assertEquals("Nike", brand.getName());
        assertEquals(1964, brand.getFoundingYear());
        assertEquals("www.nike.com", brand.getWebsite());
        assertEquals("Just Do It", brand.getDescription());
        assertTrue(brand.getIsActive());
        assertNotNull(brand.getProducts());
    }

    @Test
    @DisplayName("Should add product to brand")
    void testAddProduct() {
        // When
        brand.addProduct(product1);

        // Then
        assertEquals(1, brand.getProducts().size());
        assertTrue(brand.getProducts().contains(product1));
        assertEquals(brand, product1.getBrand());
    }

    @Test
    @DisplayName("Should add multiple products to brand")
    void testAddMultipleProducts() {
        // When
        brand.addProduct(product1);
        brand.addProduct(product2);

        // Then
        assertEquals(2, brand.getProducts().size());
        assertTrue(brand.getProducts().contains(product1));
        assertTrue(brand.getProducts().contains(product2));
        assertEquals(brand, product1.getBrand());
        assertEquals(brand, product2.getBrand());
    }

    @Test
    @DisplayName("Should remove product from brand")
    void testRemoveProduct() {

        brand.addProduct(product1);
        brand.addProduct(product2);

        brand.removeProduct(product1);

        assertEquals(1, brand.getProducts().size());
        assertFalse(brand.getProducts().contains(product1));
        assertTrue(brand.getProducts().contains(product2));
        assertNull(product1.getBrand());
        assertEquals(brand, product2.getBrand());
    }

    @Test
    @DisplayName("Should use default isActive value")
    void testDefaultIsActive() {
        Brand newBrand = new Brand();
        assertTrue(newBrand.getIsActive());
    }

    @Test
    @DisplayName("Should create brand with no-args constructor")
    void testNoArgsConstructor() {
        Brand emptyBrand = new Brand();
        assertNotNull(emptyBrand);
        assertNull(emptyBrand.getId());
        assertNull(emptyBrand.getName());
    }

    @Test
    @DisplayName("Should create brand with all-args constructor")
    void testAllArgsConstructor() {
        Brand fullBrand = new Brand(
            1L, 
            "Adidas", 
            1949, 
            "logo.png", 
            "www.adidas.com", 
            "Impossible is Nothing", 
            true, 
            new ArrayList<>()
        );
        
        assertNotNull(fullBrand);
        assertEquals("Adidas", fullBrand.getName());
        assertEquals(1949, fullBrand.getFoundingYear());
    }

    @Test
    @DisplayName("Should test equals and hashCode")
    void testEqualsAndHashCode() {
        Brand brand1 = new Brand();
        brand1.setId(1L);
        brand1.setName("Nike");

        Brand brand2 = new Brand();
        brand2.setId(1L);
        brand2.setName("Nike");

        assertEquals(brand1, brand2);
        assertEquals(brand1.hashCode(), brand2.hashCode());
    }

    @Test
    @DisplayName("Should test toString")
    void testToString() {
        String brandString = brand.toString();
        assertNotNull(brandString);
        assertTrue(brandString.contains("Nike"));
        assertTrue(brandString.contains("1964"));
    }
}