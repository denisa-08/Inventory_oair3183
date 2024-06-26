package org.example.service;
import static org.junit.jupiter.api.Assertions.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.model.InhousePart;
import org.example.model.Part;
import org.example.model.Product;
import org.example.repository.InventoryRepository;
import org.junit.jupiter.api.*;

class InventoryServiceTest {
    private InventoryService service;
    private InventoryRepository repo;

    @BeforeEach
    void setUp() {
        repo = new InventoryRepository();
        //repo.addPart(new InhousePart(14, "part1", 0.5, 4, 2, 10, 1));
        //repo.addPart(new InhousePart(15, "part2", 0.5, 5, 3, 8, 2));
        service = new InventoryService(repo);
    }

    @Test
    void testAddValidProduct() {
        // Arrange
        String name = "product1";
        double price = 15;
        int inStock = 5;
        int min = 2;
        int max = 10;

        ObservableList<Part> addParts = FXCollections.observableArrayList();
        addParts.add(repo.getAllParts().get(12));
        addParts.add(repo.getAllParts().get(13));

        int size = service.getAllProducts().size();
        // Act
        service.addProduct(name, price, inStock, min, max, addParts);

        // Assert
        assertEquals(size + 1, service.getAllProducts().size());
        Product addedProduct = service.lookupProduct("product1");
        assertEquals(15, addedProduct.getPrice());
        assertEquals(2, addedProduct.getMin());
        assertEquals(10, addedProduct.getMax());
        assertEquals(5, addedProduct.getInStock());
    }


    @Test
    void testAddValidProduct2() {
        // Arrange
        String name = "product5";
        double price = 1;
        int inStock = 10;
        int min = 5;
        int max = 20;

        ObservableList<Part> addParts = FXCollections.observableArrayList();
        addParts.add(repo.getAllParts().get(12));
        addParts.add(repo.getAllParts().get(13));

        int size = service.getAllProducts().size();

        service.addProduct(name, price, inStock, min, max, addParts);

        // Assert
        assertEquals(size+1, service.getAllProducts().size());
        Product addedProduct = service.lookupProduct("product5");
        assertEquals(1, addedProduct.getPrice());
        assertEquals(5, addedProduct.getMin());
        assertEquals(20, addedProduct.getMax());
        assertEquals(10, addedProduct.getInStock());
    }

    @Test
    void testAddValidProduct3() {
        // Arrange
        String name = "product7";
        double price = 10;
        int inStock = 15;
        int min = 2;
        int max = 15;

        ObservableList<Part> addParts = FXCollections.observableArrayList();
        addParts.add(repo.getAllParts().get(12));
        addParts.add(repo.getAllParts().get(13));

        int size = service.getAllProducts().size();

        service.addProduct(name, price, inStock, min, max, addParts);

        // Assert
        assertEquals(size+1, service.getAllProducts().size());
        Product addedProduct = service.lookupProduct("product7");
        assertEquals(10, addedProduct.getPrice());
        assertEquals(2, addedProduct.getMin());
        assertEquals(15, addedProduct.getMax());
        assertEquals(15, addedProduct.getInStock());
    }

    @Test
    void testAddInvalidProduct() {
        // Arrange
        String name = "product2";
        double price = 10;
        int inStock = -2;
        int min = 0;
        int max = 20;

        ObservableList<Part> addParts = FXCollections.observableArrayList();
        addParts.add(repo.getAllParts().get(12));
        addParts.add(repo.getAllParts().get(13));

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.addProduct(name, price, inStock, min, max, addParts);
        });

        assertEquals("Inventory level is lower than minimum value. ", exception.getMessage());
    }

    @Test
    void testAddInvalidProduct2() {
        // Arrange
        String name = "product3";
        double price = 0;
        int inStock = 3;
        int min = -5;
        int max = 10;

        ObservableList<Part> addParts = FXCollections.observableArrayList();
        addParts.add(repo.getAllParts().get(12));
        addParts.add(repo.getAllParts().get(13));

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.addProduct(name, price, inStock, min, max, addParts);
        });

        assertEquals("The inventory level must be greater than 0. The price must be greater than $0. Product price must be greater than cost of parts. ", exception.getMessage());
    }

    @Test
    void testAddInvalidProduct3() {
        // Arrange
        String name = "product4";
        double price = 0;
        int inStock = 5;
        int min = 2;
        int max = 10;

        ObservableList<Part> addParts = FXCollections.observableArrayList();
        addParts.add(repo.getAllParts().get(12));
        addParts.add(repo.getAllParts().get(13));

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.addProduct(name, price, inStock, min, max, addParts);
        });

        assertEquals("The price must be greater than $0. Product price must be greater than cost of parts. ", exception.getMessage());
    }

    @Test
    void testAddInvalidProduct4() {
        // Arrange
        String name = "product6";
        double price = 7;
        int inStock = 2;
        int min = 3;
        int max = 8;

        ObservableList<Part> addParts = FXCollections.observableArrayList();
        addParts.add(repo.getAllParts().get(12));
        addParts.add(repo.getAllParts().get(13));

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.addProduct(name, price, inStock, min, max, addParts);
        });

        assertEquals("Inventory level is lower than minimum value. ", exception.getMessage());
    }

}