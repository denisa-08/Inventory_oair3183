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

        assert(foundProduct.getName().equals("product2"));
        assert(foundProduct.getProductId() == 6);
        assert(foundProduct.getPrice() == 1.0);
        assert(foundProduct.getInStock() == 10);
        assert(foundProduct.getMin() == 5);
        assert(foundProduct.getMax() == 20);
        assert(foundProduct.getAssociatedParts().size() == 2);
    }

    @Test
    void testSearchProduct2() {
        String searchItem = "prd";
        Product foundProduct = service.lookupProduct(searchItem);

        assert(foundProduct == null);
    }

    @Test
    void testSearchProduct3() {
        Inventory inventory = new Inventory();
        repo.setInventory(inventory);
        service = new InventoryService(repo);

        Product foundProduct = service.lookupProduct("prd");
        assert(foundProduct.getName() == null);
        assert(foundProduct.getProductId() == 0);
        assert(foundProduct.getPrice() == 0.0);
        assert(foundProduct.getInStock() == 0);
        assert(foundProduct.getMin() == 0);
        assert(foundProduct.getMax() == 0);
        assert(foundProduct.getAssociatedParts() == null);

    }

    @Test
    void testSearchProduct4() {
        String searchItem = "Clock";
        Product foundProduct = service.lookupProduct(searchItem);

        assert(foundProduct.getProductId() == 1);
        assert(foundProduct.getPrice() == 7.45);
        assert(foundProduct.getInStock() == 7);
        assert(foundProduct.getMin() == 2);
        assert(foundProduct.getMax() == 20);
        assert(foundProduct.getAssociatedParts().size() == 3);
    }

    @Test
    void testSearchProduct5() {
        String searchItem = "products4";
        Product foundProduct = service.lookupProduct(searchItem);

        assert(foundProduct.getName().equals("products4"));
        assert(foundProduct.getProductId() == 4);
        assert(foundProduct.getPrice() == 11.0);
        assert(foundProduct.getInStock() == 2);
        assert(foundProduct.getMin() == 1);
        assert(foundProduct.getMax() == 10);
        assert(foundProduct.getAssociatedParts().size() == 4);
    }

    @Test
    void testSearchProduct6() {
        String searchItem = "10";
        Product foundProduct = service.lookupProduct(searchItem);

        assert(foundProduct.getName().equals("product5"));
        assert(foundProduct.getProductId() == 10);
        assert(foundProduct.getPrice() == 1.0);
        assert(foundProduct.getInStock() == 10);
        assert(foundProduct.getMin() == 5);
        assert(foundProduct.getMax() == 20);
        assert(foundProduct.getAssociatedParts().size() == 2);
    }

}