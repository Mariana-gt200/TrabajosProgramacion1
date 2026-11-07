module co.edu.uniquindio.poo.parcial_3 {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.uniquindio.poo.parcial_3.viewController to javafx.fxml;

    exports co.edu.uniquindio.poo.parcial_3.model;
    exports co.edu.uniquindio.poo.parcial_3.controller;
    exports co.edu.uniquindio.poo.parcial_3.viewController;
}