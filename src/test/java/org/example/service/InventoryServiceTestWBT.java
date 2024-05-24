package org.example.service;

import org.example.model.InhousePart;
import org.example.model.Inventory;
import org.example.model.Product;
import org.example.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTestWBT {
    private InventoryService service;
    private InventoryRepository repo;

    @BeforeEach
    void setUp() {
        repo = new InventoryRepository();
        service = new InventoryService(repo);
    }


    @Test
    void testSearchProduct1() {
        String searchItem = "product2";
        Product foundProduct = service.lookupProduct(searchItem);

        assertEquals("product2", foundProduct.getName());
        assertEquals(2, foundProduct.getProductId());
        assertEquals(12.34, foundProduct.getPrice());
        assertEquals(12, foundProduct.getInStock());
        assertEquals(1, foundProduct.getMin());
        assertEquals(12, foundProduct.getMax());
        assertEquals(1, foundProduct.getAssociatedParts().size());
    }

    @Test
    void testSearchProduct2() {
        String searchItem = "prd";
        Product foundProduct = service.lookupProduct(searchItem);

        assertNull(foundProduct);
    }

    @Test
    void testSearchProduct3() {
        Inventory inventory = new Inventory();
        repo.setInventory(inventory);
        service = new InventoryService(repo);

        Product foundProduct = service.lookupProduct("prd");
        assertNull(foundProduct.getName());
        assertEquals(0, foundProduct.getProductId());
        assertEquals(0.0 ,foundProduct.getPrice());
        assertEquals(0, foundProduct.getInStock());
        assertEquals(0, foundProduct.getMin());
        assertEquals(0, foundProduct.getMax());
        assertNull(foundProduct.getAssociatedParts());

    }

    @Test
    void testSearchProduct4() {
        String searchItem = "Clock";
        Product foundProduct = service.lookupProduct(searchItem);

        assertEquals(1, foundProduct.getProductId());
        assertEquals(7.45, foundProduct.getPrice());
        assertEquals(7, foundProduct.getInStock());
        assertEquals(2, foundProduct.getMin());
        assertEquals(20, foundProduct.getMax());
        assertEquals(3, foundProduct.getAssociatedParts().size());
    }

    @Test
    void testSearchProduct5() {
        String searchItem = "products4";
        Product foundProduct = service.lookupProduct(searchItem);

        assertEquals("products4", foundProduct.getName());
        assertEquals(4, foundProduct.getProductId());
        assertEquals(11.0, foundProduct.getPrice());
        assertEquals(2, foundProduct.getInStock());
        assertEquals(1, foundProduct.getMin());
        assertEquals(10, foundProduct.getMax());
        assertEquals(4, foundProduct.getAssociatedParts().size());
    }

    @Test
    void testSearchProduct6() {
        String searchItem = "10";
        Product foundProduct = service.lookupProduct(searchItem);

        assertEquals("product5", foundProduct.getName());
        assertEquals(10, foundProduct.getProductId());
        assertEquals(1.0, foundProduct.getPrice());
        assertEquals(10, foundProduct.getInStock());
        assertEquals(5, foundProduct.getMin());
        assertEquals(20, foundProduct.getMax());
        assertEquals(2, foundProduct.getAssociatedParts().size());
    }

}