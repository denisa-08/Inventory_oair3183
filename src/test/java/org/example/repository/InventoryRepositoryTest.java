package org.example.repository;

import org.example.model.InhousePart;
import org.example.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.example.model.Inventory;
import org.example.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryRepositoryTest {

    @Test
    public void testAddProduct() {
        // Arrange
        InventoryRepository repository = new InventoryRepository();
        Inventory mockInventory = Mockito.mock(Inventory.class);
        Product product = new Product(1, "Test Product", 100.0, 10, 1, 20, FXCollections.observableArrayList(new InhousePart(14, "part1", 0.5, 4, 2, 10, 1))); // Define a test product

        // Stubbing mock behavior
        Mockito.when(mockInventory.getAutoProductId()).thenReturn(1); // Stub auto product ID
        Mockito.when(mockInventory.getProducts()).thenReturn(FXCollections.observableArrayList()); // Stub getProducts() to return an empty ObservableList
        Mockito.doNothing().when(mockInventory).addProduct(product); // Stub addProduct() to do nothing

        ObservableList<Product> expectedProducts = FXCollections.observableArrayList(product);
        assertEquals(expectedProducts, mockInventory.getProducts()); // Verify that the product is present in the inventory's pro

        repository.setInventory(mockInventory); // Set mock inventory in repository

        // Act
        repository.addProduct(product);

        // Assert
        Mockito.verify(mockInventory, times(1)).addProduct(product); // Verify that addProduct method of mock inventory was called once with any Product object
        //Mockito.verify(repository, times(1)).writeAll(); // Verify that writeAll method of repository was called once

        //ObservableList<Product> expectedProducts = FXCollections.observableArrayList(product);
        //assertEquals(expectedProducts, mockInventory.getProducts()); // Verify that the product is present in the inventory's product list
    }
}