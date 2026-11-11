package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.app.App;
import co.edu.uniquindio.poo.controller.ClienteController;
import co.edu.uniquindio.poo.controller.MonederoController;
import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.Monedero;
import co.edu.uniquindio.poo.model.TipoMonedero;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class MonederoViewController {

    private ClienteController clienteController;
    private MonederoController monederoController;
    private final ObservableList<Monedero> listaMonederos = FXCollections.observableArrayList();

    @FXML
    private ComboBox<Cliente> cmbCliente;
    @FXML
    private TextField txtIdMonedero;
    @FXML
    private ComboBox<TipoMonedero> cmbTipo;
    @FXML
    private TextField txtSaldoInicial;
    @FXML
    private Label lblSaldoTotal;
    @FXML
    private TableView<Monedero> tblMonederos;
    @FXML
    private TableColumn<Monedero, String> tbcIdMonedero;
    @FXML
    private TableColumn<Monedero, String> tbcTipo;
    @FXML
    private TableColumn<Monedero, String> tbcSaldo;

    @FXML
    void initialize() {
        clienteController = new ClienteController(App.monederoVirtual);
        monederoController = new MonederoController(App.monederoVirtual);

        tbcIdMonedero.setCellValueFactory(m -> new SimpleStringProperty(m.getValue().getIdMonedero()));
        tbcTipo.setCellValueFactory(m -> new SimpleStringProperty(String.valueOf(m.getValue().getTipo())));
        tbcSaldo.setCellValueFactory(m -> new SimpleStringProperty(String.valueOf(m.getValue().getSaldo())));

        cmbTipo.setItems(FXCollections.observableArrayList(TipoMonedero.values()));
        cmbTipo.setValue(TipoMonedero.AHORROS);
        tblMonederos.setItems(listaMonederos);

        refrescar();
    }

    // Se llama al abrir la pestaña para mostrar los datos actuales
    public void refrescar() {
        VistaUtil.recargarClientes(cmbCliente, clienteController.listar());
        cargarMonederos();
    }

    @FXML
    void onClienteSeleccionado() {
        cargarMonederos();
    }

    @FXML
    void onAgregar() {
        Cliente cliente = cmbCliente.getValue();
        if (cliente == null) {
            VistaUtil.advertencia("Seleccione un cliente.");
            return;
        }
        String idMonedero = txtIdMonedero.getText().trim();
        if (idMonedero.isEmpty() || cmbTipo.getValue() == null) {
            VistaUtil.advertencia("El id y el tipo del monedero son obligatorios.");
            return;
        }
        double saldo;
        try {
            saldo = Double.parseDouble(txtSaldoInicial.getText().trim());
        } catch (NumberFormatException e) {
            VistaUtil.error("Ingrese un saldo inicial numérico válido.");
            return;
        }
        if (saldo < 0) {
            VistaUtil.error("El saldo inicial no puede ser negativo.");
            return;
        }

        Monedero monedero = new Monedero(idMonedero, cmbTipo.getValue(), saldo, cliente.getIdentificacion());
        String mensaje = monederoController.registrar(cliente.getIdentificacion(), monedero);
        VistaUtil.info(mensaje);
        if (mensaje.contains("exitosamente")) {
            txtIdMonedero.clear();
            txtSaldoInicial.clear();
        }
        cargarMonederos();
    }

    @FXML
    void onEliminar() {
        Cliente cliente = cmbCliente.getValue();
        Monedero seleccionado = tblMonederos.getSelectionModel().getSelectedItem();
        if (cliente == null || seleccionado == null) {
            VistaUtil.advertencia("Seleccione un cliente y un monedero de la tabla.");
            return;
        }
        String mensaje = monederoController.eliminar(cliente.getIdentificacion(), seleccionado.getIdMonedero());
        VistaUtil.info(mensaje);
        cargarMonederos();
    }

    private void cargarMonederos() {
        Cliente cliente = cmbCliente.getValue();
        if (cliente == null) {
            listaMonederos.clear();
            lblSaldoTotal.setText("Saldo total: -");
            return;
        }
        listaMonederos.setAll(new ArrayList<>(monederoController.listar(cliente.getIdentificacion())));
        tblMonederos.refresh();
        lblSaldoTotal.setText("Saldo total: $" + monederoController.saldoTotal(cliente.getIdentificacion()));
    }
}