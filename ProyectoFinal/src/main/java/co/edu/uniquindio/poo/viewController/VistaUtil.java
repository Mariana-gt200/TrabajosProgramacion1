package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.Monedero;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.util.List;

// Métodos auxiliares compartidos por los viewController
public final class VistaUtil {

    private VistaUtil() {
    }

    public static void info(String mensaje) {
        mostrar(Alert.AlertType.INFORMATION, "Información", mensaje);
    }

    public static void advertencia(String mensaje) {
        mostrar(Alert.AlertType.WARNING, "Atención", mensaje);
    }

    public static void error(String mensaje) {
        mostrar(Alert.AlertType.ERROR, "Error", mensaje);
    }

    private static void mostrar(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Recarga los clientes de un ComboBox y conserva el cliente seleccionado (si sigue existiendo)
    public static void recargarClientes(ComboBox<Cliente> combo, List<Cliente> clientes) {
        String id = null;
        if (combo.getValue() != null) {
            id = combo.getValue().getIdentificacion();
        }
        combo.getItems().setAll(clientes);
        Cliente encontrado = null;
        for (Cliente cliente : clientes) {
            if (cliente.getIdentificacion().equals(id)) {
                encontrado = cliente;
            }
        }
        combo.setValue(encontrado);
    }

    // Recarga los monederos de un ComboBox y conserva el monedero seleccionado (si sigue existiendo)
    public static void recargarMonederos(ComboBox<Monedero> combo, List<Monedero> monederos) {
        String id = null;
        if (combo.getValue() != null) {
            id = combo.getValue().getIdMonedero();
        }
        combo.getItems().setAll(monederos);
        Monedero encontrado = null;
        for (Monedero monedero : monederos) {
            if (monedero.getIdMonedero().equals(id)) {
                encontrado = monedero;
            }
        }
        combo.setValue(encontrado);
    }
}