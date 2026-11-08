package co.edu.uniquindio.poo.parcial_3.viewController;

import co.edu.uniquindio.poo.parcial_3.model.Cajero;
import co.edu.uniquindio.poo.parcial_3.model.Panaderia;
import co.edu.uniquindio.poo.parcial_3.model.PanaderoArtesanal;
import co.edu.uniquindio.poo.parcial_3.model.PanaderoIndustrial;
import co.edu.uniquindio.poo.parcial_3.viewController.EmpleadoViewController;
import co.edu.uniquindio.poo.parcial_3.viewController.PedidoViewController;
import co.edu.uniquindio.poo.parcial_3.viewController.PrimaryViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private Stage primaryStage;
    public static Panaderia panaderia = new Panaderia("Panadería El Trigal");

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Panadería El Trigal");
        this.primaryStage.setMinWidth(1000);
        this.primaryStage.setMinHeight(660);
        inicializarData();
        openViewPrincipal();
    }



    public static void main(String[] args) {
        launch();
    }
}
