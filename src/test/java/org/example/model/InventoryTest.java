package org.example.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    @Test
    void addProduct() {
        ObservableList<Part> parts = FXCollections.observableArrayList();
        Product newProduct = new Product(17, "product1", 14, 4, 1, 10,parts);
        Inventory inv = new Inventory();
        inv.addProduct(newProduct);
        assert (inv.getProducts().size() == 1);
    }

    @Test
    void updateProduct() {
        ObservableList<Part> parts = FXCollections.observableArrayList();
        Product newProduct = new Product(17, "product1", 14, 4, 1, 10,parts);
        Inventory inv = new Inventory();
        inv.addProduct(newProduct);

        Product updatedProduct = new Product(17, "product1", 20, 4, 1, 10,parts);
        inv.updateProduct(0, updatedProduct);

        assert(inv.getProducts().get(0).getPrice() == 20);
    }
}