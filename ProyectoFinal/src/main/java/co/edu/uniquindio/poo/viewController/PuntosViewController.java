package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.app.App;
import co.edu.uniquindio.poo.controller.ClienteController;
import co.edu.uniquindio.poo.controller.MonederoController;
import co.edu.uniquindio.poo.controller.PuntosController;
import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.Monedero;
import co.edu.uniquindio.poo.model.Transaccion;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;

public class PuntosViewController {

    private ClienteController clienteController;
    private MonederoController monederoController;
    private PuntosController puntosController;
    private final ObservableList<Transaccion> listaPuntos = FXCollections.observableArrayList();

    @FXML
    private ComboBox<Cliente> cmbCliente;
    @FXML
    private Label lblPuntosAcumulados;
    @FXML
    private Label lblPuntosDisponibles;
    @FXML
    private Label lblRango;
    @FXML
    private Label lblBeneficios;
    @FXML
    private ComboBox<Monedero> cmbMonederoBono;
    @FXML
    private TableView<Transaccion> tblHistorialPuntos;
    @FXML
    private TableColumn<Transaccion, String> tbcId;
    @FXML
    private TableColumn<Transaccion, String> tbcTipo;
    @FXML
    private TableColumn<Transaccion, String> tbcMonto;
    @FXML
    private TableColumn<Transaccion, String> tbcFecha;
    @FXML
    private TableColumn<Transaccion, String> tbcPuntos;

    @FXML
    void initialize() {
        clienteController = new ClienteController(App.monederoVirtual);
        monederoController = new MonederoController(App.monederoVirtual);
        puntosController = new PuntosController(App.monederoVirtual);

        tbcId.setCellValueFactory(t -> new SimpleStringProperty(t.getValue().getIdTransaccion()));
        tbcTipo.setCellValueFactory(t -> new SimpleStringProperty(t.getValue().nombreTipo()));
        tbcMonto.setCellValueFactory(t -> new SimpleStringProperty("$" + t.getValue().getMonto()));
        tbcFecha.setCellValueFactory(t -> new SimpleStringProperty(String.valueOf(t.getValue().getFecha())));
        tbcPuntos.setCellValueFactory(t -> new SimpleStringProperty("+" + t.getValue().calcularPuntosGenerados()));

        tblHistorialPuntos.setItems(listaPuntos);
        refrescar();
    }

    public void refrescar() {
        VistaUtil.recargarClientes(cmbCliente, clienteController.listar());
        cargarDatosCliente();
    }

    @FXML
    void onClienteSeleccionado() {
        cargarDatosCliente();
    }

    @FXML
    void onCanjearDescuento() {
        Cliente cliente = cmbCliente.getValue();
        if (cliente == null) {
            VistaUtil.advertencia("Seleccione un cliente.");
            return;
        }
        try {
            VistaUtil.info(puntosController.canjearDescuentoComision(cliente.getIdentificacion()));
        } catch (Exception e) {
            VistaUtil.error(e.getMessage());
        }
        cargarDatosCliente();
    }

    @FXML
    void onCanjearMesSinCargos() {
        Cliente cliente = cmbCliente.getValue();
        if (cliente == null) {
            VistaUtil.advertencia("Seleccione un cliente.");
            return;
        }
        try {
            VistaUtil.info(puntosController.canjearMesSinCargos(cliente.getIdentificacion()));
        } catch (Exception e) {
            VistaUtil.error(e.getMessage());
        }
        cargarDatosCliente();
    }

    @FXML
    void onCanjearBono() {
        Cliente cliente = cmbCliente.getValue();
        Monedero monedero = cmbMonederoBono.getValue();
        if (cliente == null || monedero == null) {
            VistaUtil.advertencia("Seleccione el cliente y el monedero donde recibirá el bono.");
            return;
        }
        try {
            VistaUtil.info(puntosController.canjearBonoSaldo(cliente.getIdentificacion(), monedero.getIdMonedero()));
        } catch (Exception e) {
            VistaUtil.error(e.getMessage());
        }
        cargarDatosCliente();
    }

    private void cargarDatosCliente() {
        Cliente cliente = cmbCliente.getValue();
        if (cliente == null) {
            listaPuntos.clear();
            cmbMonederoBono.getItems().clear();
            lblPuntosAcumulados.setText("Acumulados: -");
            lblPuntosDisponibles.setText("Disponibles: -");
            lblRango.setText("Rango: -");
            lblBeneficios.setText("Beneficios activos: -");
            return;
        }
        try {
            int acumulados = puntosController.puntosAcumulados(cliente.getIdentificacion());
            int disponibles = puntosController.puntosDisponibles(cliente.getIdentificacion());
            lblPuntosAcumulados.setText("Acumulados: " + acumulados);
            lblPuntosDisponibles.setText("Disponibles: " + disponibles);
            lblRango.setText("Rango: " + cliente.getRango());

            String desc = cliente.isDescuentoComision() ? "10% desc. comisión" : "Sin desc. comisión";
            String retiros = cliente.getSinCargosRetirosHasta() != null
                    ? "Sin cargos retiros hasta " + cliente.getSinCargosRetirosHasta()
                    : "Sin pase de retiros";
            lblBeneficios.setText("Beneficios activos: " + desc + " | " + retiros);

            List<Monedero> monederos = new ArrayList<>(monederoController.listar(cliente.getIdentificacion()));
            VistaUtil.recargarMonederos(cmbMonederoBono, monederos);

            listaPuntos.setAll(puntosController.historialPuntos(cliente.getIdentificacion()));
            tblHistorialPuntos.refresh();
        } catch (Exception e) {
            VistaUtil.error(e.getMessage());
        }
    }
}
