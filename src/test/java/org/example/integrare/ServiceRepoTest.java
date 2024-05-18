package org.example.integrare;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.model.Part;
import org.example.model.Product;
import org.example.repository.InventoryRepository;
import org.example.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ServiceRepoTest {
    Product product;
    Part part;
    InventoryRepository repo;
    InventoryService service;
    ObservableList<Part> partList;
    ObservableList<Product> products;

    @BeforeEach
    void setUp() {
        part = mock(Part.class);
        product = mock(Product.class);
        repo = new InventoryRepository();
        partList = FXCollections.observableArrayList();
        products = FXCollections.observableArrayList();
        repo.getInventory().setAllParts(partList);
        repo.getInventory().setProducts(products);
        service = new InventoryService(repo);
    }

    @Test
    void testAddValidProduct() {
        ObservableList<Part> parts = FXCollections.observableArrayList();
        parts.add(part);
        when(product.getAssociatedParts()).thenReturn(parts);

        repo.addProduct(product);
        assertEquals(1, repo.getAllProducts().size());

        when(product.getName()).thenReturn("product");
        when(product.getPrice()).thenReturn(10.0);
        when(product.getInStock()).thenReturn(5);
        when(product.getMin()).thenReturn(1);
        when(product.getMax()).thenReturn(10);

        service.addProduct(product.getName(), product.getPrice(), product.getInStock(), product.getMin(), product.getMax(), product.getAssociatedParts());
        assertEquals(2, service.getAllProducts().size());
    }

    @Test
    void testAddInvalidProduct() {
        ObservableList<Part> parts = FXCollections.observableArrayList();
        parts.add(part);
        when(product.getAssociatedParts()).thenReturn(parts);

        repo.addProduct(product);
        assertEquals(1, repo.getAllProducts().size());

        when(product.getName()).thenReturn("product");
        when(product.getPrice()).thenReturn(-5.7);
        when(product.getInStock()).thenReturn(5);
        when(product.getMin()).thenReturn(1);
        when(product.getMax()).thenReturn(10);

        assertThrows(Exception.class, () -> {
            service.addProduct(product.getName(), product.getPrice(), product.getInStock(), product.getMin(), product.getMax(), product.getAssociatedParts());
        });

        assertEquals(1, service.getAllProducts().size());
    }

}
