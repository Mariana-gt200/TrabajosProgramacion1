package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.app.App;
import co.edu.uniquindio.poo.controller.ClienteController;
import co.edu.uniquindio.poo.controller.TransaccionController;
import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.Transaccion;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HistorialViewController {

    private ClienteController clienteController;
    private TransaccionController transaccionController;
    private final ObservableList<Transaccion> listaTransacciones = FXCollections.observableArrayList();

    @FXML
    private ComboBox<Cliente> cmbCliente;
    @FXML
    private Label lblPuntos;
    @FXML
    private Label lblRango;
    @FXML
    private TableView<Transaccion> tblTransacciones;
    @FXML
    private TableColumn<Transaccion, String> tbcId;
    @FXML
    private TableColumn<Transaccion, String> tbcTipo;
    @FXML
    private TableColumn<Transaccion, String> tbcMonto;
    @FXML
    private TableColumn<Transaccion, String> tbcFecha;
    @FXML
    private TableColumn<Transaccion, String> tbcEstado;

    @FXML
    void initialize() {
        clienteController = new ClienteController(App.monederoVirtual);
        transaccionController = new TransaccionController(App.monederoVirtual);

        tbcId.setCellValueFactory(t -> new SimpleStringProperty(t.getValue().getIdTransaccion()));
        tbcTipo.setCellValueFactory(t -> new SimpleStringProperty(t.getValue().nombreTipo()));
        tbcMonto.setCellValueFactory(t -> new SimpleStringProperty(String.valueOf(t.getValue().getMonto())));
        tbcFecha.setCellValueFactory(t -> new SimpleStringProperty(String.valueOf(t.getValue().getFecha())));
        tbcEstado.setCellValueFactory(t -> new SimpleStringProperty(String.valueOf(t.getValue().getEstado())));
        tblTransacciones.setItems(listaTransacciones);

        refrescar();
    }

    // Se llama al abrir la pestaña para mostrar los datos actuales
    public void refrescar() {
        VistaUtil.recargarClientes(cmbCliente, clienteController.listar());
        cargarHistorial();
    }

    @FXML
    void onClienteSeleccionado() {
        cargarHistorial();
    }

    @FXML
    void onRevertir() {
        Transaccion seleccionada = tblTransacciones.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            VistaUtil.advertencia("Seleccione una transacción de la tabla.");
            return;
        }
        try {
            String mensaje = transaccionController.revertir(seleccionada.getIdTransaccion());
            VistaUtil.info(mensaje);
        } catch (Exception e) {
            VistaUtil.error(e.getMessage());
        }
        cargarHistorial();
    }

    private void cargarHistorial() {
        Cliente cliente = cmbCliente.getValue();
        if (cliente == null) {
            listaTransacciones.clear();
            lblPuntos.setText("Puntos acumulados: -");
            lblRango.setText("Rango: -");
            return;
        }
        listaTransacciones.setAll(transaccionController.historial(cliente.getIdentificacion()));
        tblTransacciones.refresh();
        lblPuntos.setText("Puntos acumulados: " + transaccionController.puntos(cliente.getIdentificacion()));
        lblRango.setText("Rango: " + transaccionController.rango(cliente.getIdentificacion()));
    }
}