package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class App extends Application {

    public static MonederoVirtual monederoVirtual = new MonederoVirtual(
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ServicioNotificacion(),
            new AnalizadorPatronesGastos()
    );

    @Override
    public void start(Stage stage) throws IOException {
        inicializarData();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/co/edu/uniquindio/ProyectoFinal/PrincipalView.fxml"));
        Scene scene = new Scene(loader.load(), 1020, 660);
        stage.setTitle("Monedero Virtual con Sistema de Puntos");
        stage.setScene(scene);
        stage.show();
    }

    private void inicializarData() {
        try {
            Cliente ana = new Cliente("1001", "Ana Gómez", "ana@correo.com", "3001112233", RangoCliente.BRONCE);
            Cliente luis = new Cliente("1002", "Luis Restrepo", "luis@correo.com", "3002223344", RangoCliente.BRONCE);
            Cliente sofia = new Cliente("1003", "Sofía Ruiz", "sofia@correo.com", "3003334455", RangoCliente.BRONCE);
            ana.setCanalPreferido(CanalNotificacion.WHATSAPP);
            luis.setCanalPreferido(CanalNotificacion.GMAIL);
            sofia.setCanalPreferido(CanalNotificacion.SMS);

            monederoVirtual.registrarCliente(ana);
            monederoVirtual.registrarCliente(luis);
            monederoVirtual.registrarCliente(sofia);

            Monedero anaAhorros = new Monedero("M-01", TipoMonedero.AHORROS, 500000, "1001");
            Monedero anaGastos = new Monedero("M-02", TipoMonedero.GASTOS_DIARIOS, 80000, "1001");
            Monedero luisAhorros = new Monedero("M-03", TipoMonedero.AHORROS, 300000, "1002");
            Monedero sofiaGastos = new Monedero("M-04", TipoMonedero.GASTOS_DIARIOS, 12000, "1003");

            monederoVirtual.registrarMonedero("1001", anaAhorros);
            monederoVirtual.registrarMonedero("1001", anaGastos);
            monederoVirtual.registrarMonedero("1002", luisAhorros);
            monederoVirtual.registrarMonedero("1003", sofiaGastos);

            // 9 retiros habituales de Ana (entre $90 y $120) para probar detección de atípicas
            double[] habituales = {100, 120, 90, 110, 105, 95, 115, 100, 108};
            for (int i = 0; i < habituales.length; i++) {
                monederoVirtual.registrarTransaccion(new Retiro("T-0" + (i + 1), LocalDate.now().minusDays(10 - i),
                        habituales[i], EstadoTransaccion.COMPLETADA, "1001", anaGastos));
            }

            // Puntos para rangos PLATA (Luis, 600 pts) y ORO (Sofía, 1200 pts)
            monederoVirtual.depositar("1002", "M-03", 60000);
            monederoVirtual.depositar("1003", "M-04", 120000);

            // Transacciones programadas de prueba
            LocalDate hoy = LocalDate.now();
            monederoVirtual.programarTransaccion("1001", "M-01", null, null, hoy.plusDays(7), FrecuenciaProgramada.MENSUAL, 200, TipoTransaccion.DEPOSITO);
            monederoVirtual.programarTransaccion("1001", "M-01", "1002", "M-03", hoy.plusDays(2), FrecuenciaProgramada.SEMANAL, 150, TipoTransaccion.TRANSFERENCIA);
            monederoVirtual.programarTransaccion("1003", "M-04", null, null, hoy.plusDays(1), FrecuenciaProgramada.DIARIA, 50, TipoTransaccion.RETIRO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}