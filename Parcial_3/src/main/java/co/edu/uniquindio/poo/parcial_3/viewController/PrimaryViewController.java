package co.edu.uniquindio.poo.parcial_3.viewController;

import co.edu.uniquindio.poo.parcial_3.App;
import javafx.fxml.FXML;

public class PrimaryViewController {
    private App app;

    @FXML
    void onOpenCrudEmpleado() {
        app.openCrudEmpleado();
    }

    @FXML
    void onOpenCrudPedido() {
        app.openCrudPedido();
    }

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    void initialize() {

    }
}
