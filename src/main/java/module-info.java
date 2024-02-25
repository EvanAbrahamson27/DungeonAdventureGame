module com.controller.dungeonadventure {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.controller to javafx.fxml;
    opens com.model to javafx.fxml;
    opens com.view to javafx.fxml;

    exports com.controller;
    exports com.model;
    exports com.view;
}