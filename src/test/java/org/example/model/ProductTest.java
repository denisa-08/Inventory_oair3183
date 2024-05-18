package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {
    Product product;
    ObservableList<Part> partList;
    @BeforeEach
    void setUp(){
        partList = FXCollections.observableArrayList();
        product = new Product(1, "p", 10, 5, 1, 10, partList);
    }
    @Test
    public void testGetName() {
        assertEquals("p", product.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(10, product.getPrice());
    }
}
