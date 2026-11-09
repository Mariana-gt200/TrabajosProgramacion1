package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.app.App;
import co.edu.uniquindio.poo.controller.ClienteController;
import co.edu.uniquindio.poo.model.CanalNotificacion;
import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.RangoCliente;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ClienteViewController {

    private ClienteController clienteController;
    private final ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();
    private Cliente clienteSeleccionado;

    @FXML
    private TextField txtIdentificacion;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefono;
    @FXML
    private ComboBox<CanalNotificacion> cmbCanal;
    @FXML
    private TableView<Cliente> tblClientes;
    @FXML
    private TableColumn<Cliente, String> tbcIdentificacion;
    @FXML
    private TableColumn<Cliente, String> tbcNombre;
    @FXML
    private TableColumn<Cliente, String> tbcEmail;
    @FXML
    private TableColumn<Cliente, String> tbcTelefono;
    @FXML
    private TableColumn<Cliente, String> tbcRango;
    @FXML
    private TableColumn<Cliente, String> tbcCanal;

    @FXML
    void initialize() {
        clienteController = new ClienteController(App.monederoVirtual);

        tbcIdentificacion.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIdentificacion()));
        tbcNombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        tbcEmail.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEmail()));
        tbcTelefono.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTelefono()));
        tbcRango.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getRango())));
        tbcCanal.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getCanalPreferido())));

        cmbCanal.setItems(FXCollections.observableArrayList(CanalNotificacion.values()));
        cmbCanal.setValue(CanalNotificacion.SMS);

        tblClientes.setItems(listaClientes);
        tblClientes.getSelectionModel().selectedItemProperty().addListener((obs, anterior, nuevo) -> {
            clienteSeleccionado = nuevo;
            mostrarCliente(nuevo);
        });

        refrescar();
    }

    // Se llama al abrir la pestaña para mostrar los datos actuales
    public void refrescar() {
        // Se guarda el cliente seleccionado para volver a seleccionarlo después de recargar la tabla
        String idSeleccionado = null;
        if (clienteSeleccionado != null) {
            idSeleccionado = clienteSeleccionado.getIdentificacion();
        }
        listaClientes.setAll(clienteController.listar());
        tblClientes.refresh();
        for (Cliente cliente : listaClientes) {
            if (cliente.getIdentificacion().equals(idSeleccionado)) {
                tblClientes.getSelectionModel().select(cliente);
            }
        }
    }

    @FXML
    void onAgregar() {
        if (hayCamposVacios()) {
            VistaUtil.advertencia("Todos los campos son obligatorios.");
            return;
        }
        Cliente cliente = new Cliente(txtIdentificacion.getText().trim(), txtNombre.getText().trim(),
                txtEmail.getText().trim(), txtTelefono.getText().trim(), RangoCliente.BRONCE);
        cliente.setCanalPreferido(cmbCanal.getValue());

        String mensaje = clienteController.registrar(cliente);
        VistaUtil.info(mensaje);
        if (mensaje.contains("exitosamente")) {
            limpiar();
        }
        refrescar();
    }

    @FXML
    void onActualizar() {
        if (clienteSeleccionado == null) {
            VistaUtil.advertencia("Seleccione un cliente de la tabla.");
            return;
        }
        if (hayCamposVacios()) {
            VistaUtil.advertencia("Todos los campos son obligatorios.");
            return;
        }
        // La identificación no se modifica; se conservan el rango y los beneficios del cliente
        Cliente actualizado = new Cliente(clienteSeleccionado.getIdentificacion(), txtNombre.getText().trim(),
                txtEmail.getText().trim(), txtTelefono.getText().trim(), clienteSeleccionado.getRango());
        actualizado.setCanalPreferido(cmbCanal.getValue());
        actualizado.setPuntosCanjeados(clienteSeleccionado.getPuntosCanjeados());
        actualizado.setDescuentoComision(clienteSeleccionado.isDescuentoComision());
        actualizado.setSinCargosRetirosHasta(clienteSeleccionado.getSinCargosRetirosHasta());

        String mensaje = clienteController.actualizar(clienteSeleccionado.getIdentificacion(), actualizado);
        VistaUtil.info(mensaje);
        if (mensaje.contains("exitosamente")) {
            limpiar();
        }
        refrescar();
    }

    @FXML
    void onEliminar() {
        if (clienteSeleccionado == null) {
            VistaUtil.advertencia("Seleccione un cliente de la tabla.");
            return;
        }
        String mensaje = clienteController.eliminar(clienteSeleccionado.getIdentificacion());
        VistaUtil.info(mensaje);
        limpiar();
        refrescar();
    }

    @FXML
    void onLimpiar() {
        limpiar();
    }

    private boolean hayCamposVacios() {
        return txtIdentificacion.getText().trim().isEmpty()
                || txtNombre.getText().trim().isEmpty()
                || txtEmail.getText().trim().isEmpty()
                || txtTelefono.getText().trim().isEmpty()
                || cmbCanal.getValue() == null;
    }

    private void mostrarCliente(Cliente cliente) {
        if (cliente == null) {
            return;
        }
        txtIdentificacion.setText(cliente.getIdentificacion());
        txtNombre.setText(cliente.getNombre());
        txtEmail.setText(cliente.getEmail());
        txtTelefono.setText(cliente.getTelefono());
        cmbCanal.setValue(cliente.getCanalPreferido());
        txtIdentificacion.setDisable(true);
    }

    private void limpiar() {
        tblClientes.getSelectionModel().clearSelection();
        txtIdentificacion.clear();
        txtNombre.clear();
        txtEmail.clear();
        txtTelefono.clear();
        cmbCanal.setValue(CanalNotificacion.SMS);
        txtIdentificacion.setDisable(false);
    }
}