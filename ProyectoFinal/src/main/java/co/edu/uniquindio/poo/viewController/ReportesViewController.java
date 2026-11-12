package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.app.App;
import co.edu.uniquindio.poo.controller.ClienteController;
import co.edu.uniquindio.poo.controller.ReporteController;
import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.ReporteGasto;
import co.edu.uniquindio.poo.model.Transaccion;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class ReportesViewController {

    public static class FilaReporte {
        private final String categoria;
        private final String total;

        public FilaReporte(String categoria, String total) {
            this.categoria = categoria;
            this.total = total;
        }
    }

    private ClienteController clienteController;
    private ReporteController reporteController;
    private final ObservableList<FilaReporte> listaDesglose = FXCollections.observableArrayList();
    private final ObservableList<Transaccion> listaTransacciones = FXCollections.observableArrayList();

    @FXML
    private ComboBox<Cliente> cmbCliente;
    @FXML
    private Label lblFechaReporte;
    @FXML
    private Label lblTotalGasto;
    @FXML
    private TableView<FilaReporte> tblDesglose;
    @FXML
    private TableColumn<FilaReporte, String> tbcCategoria;
    @FXML
    private TableColumn<FilaReporte, String> tbcTotalCategoria;
    @FXML
    private TableView<Transaccion> tblTransaccionesCliente;
    @FXML
    private TableColumn<Transaccion, String> tbcTrxId;
    @FXML
    private TableColumn<Transaccion, String> tbcTrxTipo;
    @FXML
    private TableColumn<Transaccion, String> tbcTrxMonto;
    @FXML
    private TableColumn<Transaccion, String> tbcTrxFecha;
    @FXML
    private TableColumn<Transaccion, String> tbcTrxEstado;
    @FXML
    private TextArea txtResultadoAnalisis;

    @FXML
    void initialize() {
        clienteController = new ClienteController(App.monederoVirtual);
        reporteController = new ReporteController(App.monederoVirtual);

        tbcCategoria.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().categoria));
        tbcTotalCategoria.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().total));
        tblDesglose.setItems(listaDesglose);

        tbcTrxId.setCellValueFactory(t -> new SimpleStringProperty(t.getValue().getIdTransaccion()));
        tbcTrxTipo.setCellValueFactory(t -> new SimpleStringProperty(t.getValue().nombreTipo()));
        tbcTrxMonto.setCellValueFactory(t -> new SimpleStringProperty("$" + t.getValue().getMonto()));
        tbcTrxFecha.setCellValueFactory(t -> new SimpleStringProperty(String.valueOf(t.getValue().getFecha())));
        tbcTrxEstado.setCellValueFactory(t -> new SimpleStringProperty(String.valueOf(t.getValue().getEstado())));
        tblTransaccionesCliente.setItems(listaTransacciones);

        tblTransaccionesCliente.getSelectionModel().selectedItemProperty().addListener((obs, ant, nueva) -> {
            if (nueva != null) {
                txtResultadoAnalisis.setText(reporteController.analizarTransaccion(nueva));
            }
        });

        refrescar();
    }

    public void refrescar() {
        VistaUtil.recargarClientes(cmbCliente, clienteController.listar());
        generarReporteYTabla();
    }

    @FXML
    void onClienteSeleccionado() {
        generarReporteYTabla();
    }

    @FXML
    void onAnalizarSeleccionada() {
        Transaccion seleccionada = tblTransaccionesCliente.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            VistaUtil.advertencia("Seleccione una transacción de la tabla inferior para analizar.");
            return;
        }
        txtResultadoAnalisis.setText(reporteController.analizarTransaccion(seleccionada));
    }

    private void generarReporteYTabla() {
        Cliente cliente = cmbCliente.getValue();
        txtResultadoAnalisis.clear();
        if (cliente == null) {
            listaDesglose.clear();
            listaTransacciones.clear();
            lblFechaReporte.setText("Fecha: -");
            lblTotalGasto.setText("Total gastado (retiros + transferencias): -");
            return;
        }
        ReporteGasto reporte = reporteController.generarReporte(cliente);
        lblFechaReporte.setText("Fecha del reporte: " + reporte.getFecha());
        lblTotalGasto.setText("Total gastado (retiros + transferencias): $" + String.format("%.2f", reporte.getTotalGasto()));

        listaDesglose.clear();
        reporte.getDesglosePorCategoria().forEach((categoria, total) ->
                listaDesglose.add(new FilaReporte(categoria, "$" + String.format("%.2f", total))));
        tblDesglose.refresh();

        listaTransacciones.setAll(reporteController.transaccionesCliente(cliente.getIdentificacion()));
        tblTransaccionesCliente.refresh();
    }
}