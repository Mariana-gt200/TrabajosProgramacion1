package co.edu.uniquindio.poo.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class PrincipalViewController {

    @FXML
    private TabPane tabPane;

    // Controladores de las vistas incluidas con fx:include (fx:id + "Controller")
    @FXML
    private ClienteViewController vistaClienteController;
    @FXML
    private MonederoViewController vistaMonederoController;
    @FXML
    private OperacionViewController vistaOperacionController;
    @FXML
    private HistorialViewController vistaHistorialController;

    @FXML
    void initialize() {
        // Al cambiar de pestaña se refrescan los datos de esa vista
        tabPane.getSelectionModel().selectedIndexProperty().addListener((obs, anterior, nuevo) -> {
            switch (nuevo.intValue()) {
                case 0:
                    vistaClienteController.refrescar();
                    break;
                case 1:
                    vistaMonederoController.refrescar();
                    break;
                case 2:
                    vistaOperacionController.refrescar();
                    break;
                case 3:
                    vistaHistorialController.refrescar();
                    break;
            }
        });
    }
}