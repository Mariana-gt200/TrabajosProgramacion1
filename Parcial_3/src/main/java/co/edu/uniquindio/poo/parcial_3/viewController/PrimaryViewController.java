package co.edu.uniquindio.poo.parcial_3.viewController;

import javafx.fxml.FXML;

public class PrimaryViewController {

    private App app;

    @FXML
    void onOpenCrudEmpleado() {
        if (app != null) {
            app.openCrudEmpleado();
        }
    }

    @FXML
    void onOpenCrudPedido() {
        if (app != null) {
            app.openCrudPedido();
        }
    }

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    void initialize() {
    }
}
