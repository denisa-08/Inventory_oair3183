package org.example.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.model.Part;
import org.example.model.Product;
import org.example.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class TestERS {
    Product product;
    Part part;
    InventoryRepository repo;
    InventoryService service;
    ObservableList<Part> partList;
    ObservableList<Product> products;

    @BeforeEach
    void setUp() {
        part = mock(Part.class);
        product = new Product(1, "p",13, 9, 1, 10, FXCollections.observableArrayList(part));
        repo = new InventoryRepository();
        partList = FXCollections.observableArrayList(part);
        products = FXCollections.observableArrayList();
        repo.getInventory().setAllParts(partList);
        repo.getInventory().setProducts(products);
        service = new InventoryService(repo);
    }

    @Test
    void testAddValidProduct() {
        assertEquals("p", product.getName());
        assertEquals(13, product.getPrice());

        repo.addProduct(product);
        assertEquals(1, repo.getAllProducts().size());

        service.addProduct(product.getName(), product.getPrice(), product.getInStock(), product.getMin(), product.getMax(), product.getAssociatedParts());
        assertEquals(2, service.getAllProducts().size());
    }

    @Test
    void testAddInvalidProduct() {
        product.setPrice(-10.0);
        assertEquals("p", product.getName());
        assertEquals(-10.0, product.getPrice());

        repo.addProduct(product);
        assertEquals(1, repo.getAllProducts().size());

        assertThrows(Exception.class, () -> {
            service.addProduct(product.getName(), product.getPrice(), product.getInStock(), product.getMin(), product.getMax(), product.getAssociatedParts());
        });

        assertEquals(1, service.getAllProducts().size());
    }

}
