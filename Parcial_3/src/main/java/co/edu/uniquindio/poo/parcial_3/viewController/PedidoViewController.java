package co.edu.uniquindio.poo.parcial_3.viewController;

import co.edu.uniquindio.poo.parcial_3.App;
import co.edu.uniquindio.poo.parcial_3.controller.PedidoController;
import co.edu.uniquindio.poo.parcial_3.model.EstadoPedido;
import co.edu.uniquindio.poo.parcial_3.model.Pedido;
import co.edu.uniquindio.poo.parcial_3.model.TipoPan;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class PedidoViewController {
    private App app;
    private PedidoController pedidoController;
    private ObservableList<Pedido> listPedidos = FXCollections.observableArrayList();
    private Pedido selectedPedido;

    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtCliente;
    @FXML
    private ComboBox<TipoPan> cmbTipoPan;
    @FXML
    private TextField txtCantidad;
    @FXML
    private Label lblPendientes;
    @FXML
    private Label lblAsignados;
    @FXML
    private Label lblCancelados;
    @FXML
    private TableView<Pedido> tblListPedido;
    @FXML
    private TableColumn<Pedido, String> tbcCodigo;
    @FXML
    private TableColumn<Pedido, String> tbcCliente;
    @FXML
    private TableColumn<Pedido, String> tbcTipoPan;
    @FXML
    private TableColumn<Pedido, Integer> tbcCantidad;
    @FXML
    private TableColumn<Pedido, String> tbcEstado;
    @FXML
    private TableColumn<Pedido, String> tbcAsignado;
    @FXML
    private TableColumn<Pedido, Integer> tbcReservados;
    @FXML
    private ListView<String> lstEstimaciones;
    @FXML
    private Label lblEstado;

    @FXML
    void onRegistrarPedido() {
        registrarPedido();
    }

    @FXML
    void onAsignarPedido() {
        asignarPedido();
    }

    @FXML
    void onCancelarPedido() {
        cancelarPedido();
    }

    @FXML
    void onVolver() {
        app.openViewPrincipal();
    }

    @FXML
    void initialize() {
        pedidoController = new PedidoController(App.panaderia);
        initView();
    }

    private void initView() {
        cmbTipoPan.setItems(FXCollections.observableArrayList(TipoPan.TRADICIONAL, TipoPan.INTEGRAL));
        cmbTipoPan.getSelectionModel().selectFirst();

        // Traer los datos del pedido a la tabla
        initDataBinding();

        // Obtiene la lista y la agrega a la tabla
        obtenerPedidos();
        tblListPedido.setItems(listPedidos);

        // Seleccionar elemento de la tabla
        listenerSelection();

        actualizarResumen();
        mostrarEstimaciones(null);
    }

    private void initDataBinding() {
        tbcCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        tbcCliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreCliente()));
        tbcTipoPan.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipoPan().toString()));
        tbcCantidad.setCellValueFactory(
                cellData -> new SimpleIntegerProperty(cellData.getValue().getCantidadUnidades()).asObject());
        tbcEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstado().toString()));
        tbcAsignado.setCellValueFactory(cellData -> new SimpleStringProperty(
                pedidoController.describirEmpleado(cellData.getValue().getCodigoEmpleadoAsignado())));
        tbcReservados.setCellValueFactory(
                cellData -> new SimpleIntegerProperty(cellData.getValue().getMinutosReservados()).asObject());

        colorearEstados();
    }

    // OPCIONAL (solo decoración): pinta el texto de la columna Estado según el estado del pedido.
    // Si no la quieren, borren este método y la línea que lo llama.
    private void colorearEstados() {
        tbcEstado.setCellFactory(columna -> new TableCell<Pedido, String>() {
            @Override
            protected void updateItem(String estado, boolean vacia) {
                super.updateItem(estado, vacia);
                getStyleClass().removeAll("estado-pendiente", "estado-asignado", "estado-cancelado");
                if (vacia || estado == null) {
                    setText(null);
                } else {
                    setText(estado);
                    getStyleClass().add("estado-" + estado.toLowerCase());
                }
            }
        });
    }

    private void obtenerPedidos() {
        listPedidos.addAll(pedidoController.obtenerListaPedidos());
    }

    private void listenerSelection() {
        tblListPedido.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedPedido = newSelection;
            mostrarEstimaciones(selectedPedido);
        });
    }

    // ---------------------------------------------------------------- acciones

    private void registrarPedido() {
        try {
            String codigo = txtCodigo.getText().trim();
            String cliente = txtCliente.getText().trim();
            int cantidad = leerEntero(txtCantidad, "Cantidad de unidades");

            Pedido pedido = pedidoController.registrarPedido(codigo, cliente, cmbTipoPan.getValue(), cantidad);

            listPedidos.add(pedido);
            limpiarCamposPedido();
            actualizarResumen();
            mostrarExito("Pedido " + codigo + " registrado en estado PENDIENTE.");
        } catch (RuntimeException e) {
            mostrarError(e);
        }
    }

    private void asignarPedido() {
        if (selectedPedido == null) {
            mostrarError(new IllegalArgumentException("Selecciona un pedido de la tabla para asignarlo."));
            return;
        }
        try {
            pedidoController.asignarPedido(selectedPedido.getCodigo());
            refrescar();
            mostrarExito("Pedido " + selectedPedido.getCodigo() + " asignado a "
                    + pedidoController.describirEmpleado(selectedPedido.getCodigoEmpleadoAsignado())
                    + ". Minutos reservados: " + selectedPedido.getMinutosReservados() + ".");
        } catch (RuntimeException e) {
            mostrarError(e);
        }
    }

    private void cancelarPedido() {
        if (selectedPedido == null) {
            mostrarError(new IllegalArgumentException("Selecciona un pedido de la tabla para cancelarlo."));
            return;
        }
        try {
            pedidoController.cancelarPedido(selectedPedido.getCodigo());
            refrescar();
            mostrarExito("Pedido " + selectedPedido.getCodigo() + " cancelado. Se conserva en el historial.");
        } catch (RuntimeException e) {
            mostrarError(e);
        }
    }

    // ---------------------------------------------------------------- auxiliares de la vista

    // Los pedidos cambian por dentro, así que se repinta la tabla, el resumen y las estimaciones
    private void refrescar() {
        tblListPedido.refresh();
        actualizarResumen();
        mostrarEstimaciones(selectedPedido);
    }

    private void actualizarResumen() {
        int pendientes = 0;
        int asignados = 0;
        int cancelados = 0;
        for (Pedido pedido : listPedidos) {
            if (pedido.getEstado() == EstadoPedido.PENDIENTE) {
                pendientes++;
            } else if (pedido.getEstado() == EstadoPedido.ASIGNADO) {
                asignados++;
            } else {
                cancelados++;
            }
        }
        lblPendientes.setText(String.valueOf(pendientes));
        lblAsignados.setText(String.valueOf(asignados));
        lblCancelados.setText(String.valueOf(cancelados));
    }

    private void mostrarEstimaciones(Pedido pedido) {
        List<String> lineas = new ArrayList<>();
        if (pedido == null) {
            lineas.add("Selecciona un pedido para ver cuánto tardaría cada panadero.");
        } else {
            lineas = pedidoController.estimarTiempos(pedido.getCodigo());
            if (lineas.isEmpty()) {
                lineas.add("No hay panaderos registrados.");
            }
        }
        lstEstimaciones.setItems(FXCollections.observableArrayList(lineas));
    }

    private int leerEntero(TextField campo, String nombreCampo) {
        try {
            return Integer.parseInt(campo.getText().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El campo '" + nombreCampo + "' debe ser un número entero.");
        }
    }

    private void limpiarCamposPedido() {
        txtCodigo.clear();
        txtCliente.clear();
        txtCantidad.clear();
    }

    private void mostrarExito(String mensaje) {
        lblEstado.getStyleClass().removeAll("mensaje-ok", "mensaje-error");
        lblEstado.getStyleClass().add("mensaje-ok");
        lblEstado.setText(mensaje);
    }

    private void mostrarError(RuntimeException e) {
        String mensaje = e.getMessage();
        if (mensaje == null) {
            mensaje = "Ocurrió un error inesperado.";
        }
        lblEstado.getStyleClass().removeAll("mensaje-ok", "mensaje-error");
        lblEstado.getStyleClass().add("mensaje-error");
        lblEstado.setText(mensaje);

        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("No se pudo completar la operación");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public void setApp(App app) {
        this.app = app;
    }
}
