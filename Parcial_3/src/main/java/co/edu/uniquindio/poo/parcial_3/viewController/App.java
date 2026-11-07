package co.edu.uniquindio.poo.parcial_3.viewController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                App.class.getResource("/co/edu/uniquindio/poo/parcial_3/view/panaderia-view.fxml"));
        Scene scene = new Scene(loader.load(), 1180, 740);
        scene.getStylesheets().add(
                App.class.getResource("/co/edu/uniquindio/poo/parcial_3/view/estilos.css").toExternalForm());
        stage.setTitle("Panadería El Trigal");
        stage.setMinWidth(1000);
        stage.setMinHeight(660);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
