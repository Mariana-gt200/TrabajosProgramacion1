package co.edu.uniquindio.poo.parcial_3.viewController;

import co.edu.uniquindio.poo.parcial_3.model.Cajero;
import co.edu.uniquindio.poo.parcial_3.model.Panaderia;
import co.edu.uniquindio.poo.parcial_3.model.PanaderoArtesanal;
import co.edu.uniquindio.poo.parcial_3.model.PanaderoIndustrial;
import co.edu.uniquindio.poo.parcial_3.model.TipoPan;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

    private void inicializarData() {
        PanaderoArtesanal ana = new PanaderoArtesanal("Ana López", "111", "E1", 120);
        PanaderoIndustrial carlos = new PanaderoIndustrial("Carlos Ruiz", "222", "E2", 150);
        Cajero lucia = new Cajero("Lucía Gómez", "333", "E3", 480, "Caja 1");

        panaderia.registrarEmpleado(ana);
        panaderia.registrarEmpleado(carlos);
        panaderia.registrarEmpleado(lucia);

        panaderia.registrarPedido("P01", "Café Central", TipoPan.TRADICIONAL, 25);
    }

    public void openViewPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/parcial_3/primary.fxml"));
            Parent rootLayout = loader.load();
            PrimaryViewController controller = loader.getController();
            controller.setApp(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            openCrudEmpleado();
        }
    }

    public void openCrudEmpleado() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/parcial_3/crudEmpleado.fxml"));
            Parent rootLayout = loader.load();
            EmpleadoViewController controller = loader.getController();
            controller.setApp(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openCrudPedido() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/parcial_3/crudPedido.fxml"));
            Parent rootLayout = loader.load();
            PedidoViewController controller = loader.getController();
            controller.setApp(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch();
    }
}