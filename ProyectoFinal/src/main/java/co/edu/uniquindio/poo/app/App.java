package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class App extends Application {

    // Único sistema compartido por todas las vistas
    public static final MonederoVirtual monederoVirtual = new MonederoVirtual(
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
            new ServicioNotificacion(), new AnalizadorPatronesGastos());

    @Override
    public void start(Stage stage) throws IOException {
        inicializarData();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/co/edu/uniquindio/poo/view/principal.fxml"));
        Scene scene = new Scene(loader.load(), 900, 600);
        stage.setTitle("Monedero Virtual");
        stage.setScene(scene);
        stage.show();
    }

    // Datos de prueba para poder demostrar las funcionalidades
    private void inicializarData() {
        Cliente ana = new Cliente("1001", "Ana Gómez", "ana@correo.com", "3001112233", RangoCliente.BRONCE);
        Cliente luis = new Cliente("1002", "Luis Restrepo", "luis@correo.com", "3002223344", RangoCliente.BRONCE);
        ana.setCanalPreferido(CanalNotificacion.WHATSAPP);
        luis.setCanalPreferido(CanalNotificacion.GMAIL);
        monederoVirtual.registrarCliente(ana);
        monederoVirtual.registrarCliente(luis);

        monederoVirtual.registrarMonedero("1001", new Monedero("M-01", TipoMonedero.AHORROS, 500000, "1001"));
        monederoVirtual.registrarMonedero("1001", new Monedero("M-02", TipoMonedero.GASTOS_DIARIOS, 80000, "1001"));
        monederoVirtual.registrarMonedero("1002", new Monedero("M-03", TipoMonedero.AHORROS, 300000, "1002"));
    }

    public static void main(String[] args) {
        launch();
    }
}