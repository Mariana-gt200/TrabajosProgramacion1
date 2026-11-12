import co.edu.uniquindio.poo.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("monedero-view.fxml"));
        Scene scene = new Scene(loader.load(), 950, 620);
        stage.setTitle("Monedero Virtual");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

    // Datos de prueba para poder demostrar las funcionalidades
    private void inicializarData() {
        Cliente ana = new Cliente("1001", "Ana Gómez", "ana@correo.com", "3001112233", RangoCliente.BRONCE);
        Cliente luis = new Cliente("1002", "Luis Restrepo", "luis@correo.com", "3002223344", RangoCliente.BRONCE);
        ana.setCanalPreferido(CanalNotificacion.WHATSAPP);
        luis.setCanalPreferido(CanalNotificacion.GMAIL);
        MonederoVirtual.registrarCliente(ana);
        MonederoVirtual.registrarCliente(luis);

        MonederoVirtual.registrarMonedero("1001", new Monedero("M-01", TipoMonedero.AHORROS, 500000, "1001"));
        MonederoVirtual.registrarMonedero("1001", new Monedero("M-02", TipoMonedero.GASTOS_DIARIOS, 80000, "1001"));
        MonederoVirtual.registrarMonedero("1002", new Monedero("M-03", TipoMonedero.AHORROS, 300000, "1002"));
    }

    public static void main(String[] args) {
        launch();
    }