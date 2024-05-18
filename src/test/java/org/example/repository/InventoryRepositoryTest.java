package org.example.repository;

import org.example.model.InhousePart;
import org.example.model.Part;
import org.example.repository.InventoryRepository;
import org.example.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.example.model.Inventory;
import org.example.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class InventoryRepositoryTest {
    Product product;
    InventoryRepository repo;
    ObservableList<Part> partList;
    ObservableList<Product> products;

    @BeforeEach
    void setUp(){
        product = mock(Product.class);
        repo = new InventoryRepository();
        partList = FXCollections.observableArrayList();
        products = FXCollections.observableArrayList();
        repo.getInventory().setAllParts(partList);
        repo.getInventory().setProducts(products);

        System.out.println("Inital length: "+ products.size());
    }
    @Test
    public void testAddProduct() {
        repo.addProduct(product);

        assertEquals(1, repo.getAllProducts().size());
    }

    @Test
    public void testDeleteProduct() {
        repo.addProduct(product);
        assertEquals(1, repo.getAllProducts().size());

        repo.deleteProduct(product);
        assertEquals(0, repo.getAllProducts().size());
    }
}