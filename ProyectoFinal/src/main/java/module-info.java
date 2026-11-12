module co.edu.uniquindio.poo {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.uniquindio.poo.app to javafx.fxml;
    opens co.edu.uniquindio.poo.viewController to javafx.fxml;
    opens co.edu.uniquindio.poo.model to javafx.base;
    opens co.edu.uniquindio.ProyectoFinal to javafx.fxml;

    exports co.edu.uniquindio.poo.app;
    exports co.edu.uniquindio.poo.model;
    exports co.edu.uniquindio.poo.controller;
    exports co.edu.uniquindio.poo.viewController;
}