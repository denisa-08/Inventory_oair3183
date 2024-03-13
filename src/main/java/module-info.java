module org.example {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    opens org.example.model to javafx.base;
    exports org.example.model;
    opens org.example to javafx.fxml;
    exports org.example;
    opens org.example.controller to javafx.fxml;
    exports org.example.controller;
}