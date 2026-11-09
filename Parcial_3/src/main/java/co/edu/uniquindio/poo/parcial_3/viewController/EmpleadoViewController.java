package co.edu.uniquindio.poo.parcial_3.viewController;

import co.edu.uniquindio.poo.parcial_3.controller.EmpleadoController;
import co.edu.uniquindio.poo.parcial_3.model.Empleado;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class EmpleadoViewController {

    private static final String ARTESANAL = "Panadero artesanal";
    private static final String INDUSTRIAL = "Panadero industrial";
    private static final String CAJERO = "Cajero";

    private App app;
    private EmpleadoController empleadoController;
    private ObservableList<Empleado> listEmpleados = FXCollections.observableArrayList();

    @FXML private ComboBox<String> cmbTipoEmpleado;
    @FXML private TextField txtNombre;
    @FXML private TextField txtDocumento;
    @FXML private TextField txtCodigo;
    @FXML private TextField txtMinutos;
    @FXML private TextField txtCaja;
    @FXML private VBox boxCaja;
    @FXML private TableView<Empleado> tblListEmpleado;
    @FXML private TableColumn<Empleado, String> tbcCodigo;
    @FXML private TableColumn<Empleado, String> tbcNombre;
    @FXML private TableColumn<Empleado, String> tbcDocumento;
    @FXML private TableColumn<Empleado, String> tbcTipo;
    @FXML private TableColumn<Empleado, Integer> tbcMinutos;
    @FXML private Label lblEstado;

    @FXML
    void onTipoEmpleadoCambiado() {
        boolean esCajero = CAJERO.equals(cmbTipoEmpleado.getValue());
        if (boxCaja != null) {
            boxCaja.setVisible(esCajero);
            boxCaja.setManaged(esCajero);
        }
    }

    @FXML
    void onRegistrarEmpleado() {
        registrarEmpleado();
    }

    @FXML
    void onVolver() {
        if (app != null) {
            app.openViewPrincipal();
        }
    }

    @FXML
    void initialize() {
        empleadoController = new EmpleadoController(App.panaderia);
        initView();
    }

    private void initView() {
        cmbTipoEmpleado.setItems(FXCollections.observableArrayList(ARTESANAL, INDUSTRIAL, CAJERO));
        cmbTipoEmpleado.getSelectionModel().selectFirst();
        onTipoEmpleadoCambiado();

        initDataBinding();
        obtenerEmpleados();
        tblListEmpleado.setItems(listEmpleados);
    }

    private void initDataBinding() {
        tbcCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        tbcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tbcDocumento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDocumento()));
        tbcTipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipo()));
        tbcMinutos.setCellValueFactory(
                cellData -> new SimpleIntegerProperty(cellData.getValue().getMinutosDisponibles()).asObject());
    }

    private void obtenerEmpleados() {
        listEmpleados.clear();
        listEmpleados.addAll(empleadoController.obtenerListaEmpleados());
    }

    private void registrarEmpleado() {
        try {
            String tipo = cmbTipoEmpleado.getValue();
            String nombre = txtNombre.getText().trim();
            String documento = txtDocumento.getText().trim();
            String codigo = txtCodigo.getText().trim();
            int minutos = leerEntero(txtMinutos, "Minutos disponibles");

            Empleado empleado;
            if (ARTESANAL.equals(tipo)) {
                empleado = empleadoController.registrarPanaderoArtesanal(nombre, documento, codigo, minutos);
            } else if (INDUSTRIAL.equals(tipo)) {
                empleado = empleadoController.registrarPanaderoIndustrial(nombre, documento, codigo, minutos);
            } else {
                empleado = empleadoController.registrarCajero(nombre, documento, codigo, minutos, txtCaja.getText().trim());
            }

            listEmpleados.add(empleado);
            limpiarCamposEmpleado();
            mostrarExito("Empleado " + nombre + " registrado correctamente.");
        } catch (RuntimeException e) {
            mostrarError(e);
        }
    }

    private int leerEntero(TextField campo, String nombreCampo) {
        try {
            return Integer.parseInt(campo.getText().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El campo '" + nombreCampo + "' debe ser un número entero.");
        }
    }

    private void limpiarCamposEmpleado() {
        txtNombre.clear();
        txtDocumento.clear();
        txtCodigo.clear();
        txtMinutos.clear();
        if (txtCaja != null) txtCaja.clear();
    }

    private void mostrarExito(String mensaje) {
        if (lblEstado != null) {
            lblEstado.getStyleClass().removeAll("mensaje-ok", "mensaje-error");
            lblEstado.getStyleClass().add("mensaje-ok");
            lblEstado.setText(mensaje);
        }
    }

    private void mostrarError(RuntimeException e) {
        String mensaje = e.getMessage() != null ? e.getMessage() : "Ocurrió un error inesperado.";
        if (lblEstado != null) {
            lblEstado.getStyleClass().removeAll("mensaje-ok", "mensaje-error");
            lblEstado.getStyleClass().add("mensaje-error");
            lblEstado.setText(mensaje);
        }

        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error de Operación");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public void setApp(App app) {
        this.app = app;
    }
}