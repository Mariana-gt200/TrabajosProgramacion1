package co.edu.uniquindio.poo.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class PrincipalViewController {

    @FXML
    private TabPane tabPane;

    @FXML
    private ClienteViewController vistaClienteController;
    @FXML
    private MonederoViewController vistaMonederoController;
    @FXML
    private OperacionViewController vistaOperacionController;
    @FXML
    private HistorialViewController vistaHistorialController;
    @FXML
    private PuntosViewController vistaPuntosController;
    @FXML
    private ProgramadasViewController vistaProgramadasController;
    @FXML
    private ReportesViewController vistaReportesController;

    @FXML
    void initialize() {
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
                case 4:
                    vistaPuntosController.refrescar();
                    break;
                case 5:
                    vistaProgramadasController.refrescar();
                    break;
                case 6:
                    vistaReportesController.refrescar();
                    break;
            }
        });
    }
}