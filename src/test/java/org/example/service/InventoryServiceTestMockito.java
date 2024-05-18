package org.example.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.model.Part;
import org.example.model.Product;
import org.example.repository.InventoryRepository;
import org.example.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryServiceTestMockito {
    InventoryRepository repo;
    InventoryService serviceUnit;
    ObservableList<Part> partList;
    ObservableList<Product> products;

    @BeforeEach
    void setUp(){
        repo = mock(InventoryRepository.class);  //mock repository
        serviceUnit = new InventoryService(repo);
        partList = FXCollections.observableArrayList();
        products = FXCollections.observableArrayList();
        System.out.println("Inital length: "+ products.size());
    }

    @Test
    void serviceAddValidProduct() throws Exception {
        Part part = mock(Part.class);
        partList.add(part);
        System.out.println(partList.size());

        Product p = new Product(34, "TestProductMockito", 5.7, 5, 1, 7, partList);
        doNothing().when(repo).addProduct(p);
        serviceUnit.addProduct("TestProductMockito", 5.7, 5, 1, 7, partList);
        products.add(p);

        when(repo.getAllProducts()).thenReturn(products);
        assertEquals(1, serviceUnit.getAllProducts().size());
    }

    @Test
    void serviceAddInvalidProduct() {
        Part part = mock(Part.class);
        partList.add(part);
        System.out.println(partList.size());

        Product p = new Product(34, "TestProductMockito", -5.7, 5, 1, 7, partList);
        doNothing().when(repo).addProduct(p);

        assertThrows(Exception.class, () -> {
            serviceUnit.addProduct("TestProductMockito", -5.7, 5, 1, 7, partList);
        });

        when(repo.getAllProducts()).thenReturn(products);
        assertEquals(0, serviceUnit.getAllProducts().size());

        verify(repo, times(1)).getAllProducts();
    }
}