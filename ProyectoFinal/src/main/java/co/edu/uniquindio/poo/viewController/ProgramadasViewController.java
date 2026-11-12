package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.app.App;
import co.edu.uniquindio.poo.controller.ClienteController;
import co.edu.uniquindio.poo.controller.MonederoController;
import co.edu.uniquindio.poo.controller.ProgramadaController;
import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.FrecuenciaProgramada;
import co.edu.uniquindio.poo.model.Monedero;
import co.edu.uniquindio.poo.model.TipoTransaccion;
import co.edu.uniquindio.poo.model.TransaccionProgramada;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProgramadasViewController {

    private ClienteController clienteController;
    private MonederoController monederoController;
    private ProgramadaController programadaController;
    private final ObservableList<TransaccionProgramada> listaProgramadas = FXCollections.observableArrayList();

    @FXML
    private ComboBox<Cliente> cmbCliente;
    @FXML
    private ComboBox<TipoTransaccion> cmbTipo;
    @FXML
    private ComboBox<FrecuenciaProgramada> cmbFrecuencia;
    @FXML
    private ComboBox<Monedero> cmbMonederoOrigen;
    @FXML
    private TextField txtMonto;
    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private ComboBox<Cliente> cmbClienteDestino;
    @FXML
    private ComboBox<Monedero> cmbMonederoDestino;
    @FXML
    private DatePicker dpFechaCorte;
    @FXML
    private TableView<TransaccionProgramada> tblProgramadas;
    @FXML
    private TableColumn<TransaccionProgramada, String> tbcId;
    @FXML
    private TableColumn<TransaccionProgramada, String> tbcTipo;
    @FXML
    private TableColumn<TransaccionProgramada, String> tbcMonto;
    @FXML
    private TableColumn<TransaccionProgramada, String> tbcFrecuencia;
    @FXML
    private TableColumn<TransaccionProgramada, String> tbcProximaFecha;
    @FXML
    private TableColumn<TransaccionProgramada, String> tbcMonederos;

    @FXML
    void initialize() {
        clienteController = new ClienteController(App.monederoVirtual);
        monederoController = new MonederoController(App.monederoVirtual);
        programadaController = new ProgramadaController(App.monederoVirtual);

        tbcId.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getIdProgramacion()));
        tbcTipo.setCellValueFactory(p -> new SimpleStringProperty(String.valueOf(p.getValue().getTipo())));
        tbcMonto.setCellValueFactory(p -> new SimpleStringProperty("$" + p.getValue().getMonto()));
        tbcFrecuencia.setCellValueFactory(p -> new SimpleStringProperty(String.valueOf(p.getValue().getFrecuencia())));
        tbcProximaFecha.setCellValueFactory(p -> new SimpleStringProperty(String.valueOf(p.getValue().getFechaProximaEjecucion())));
        tbcMonederos.setCellValueFactory(p -> {
            TransaccionProgramada tp = p.getValue();
            String origen = tp.getMonederoOrigen() != null ? tp.getMonederoOrigen().getIdMonedero() : "-";
            String destino = tp.getMonederoDestino() != null ? tp.getMonederoDestino().getIdMonedero() : "-";
            return new SimpleStringProperty(origen + " -> " + destino);
        });

        cmbTipo.setItems(FXCollections.observableArrayList(TipoTransaccion.values()));
        cmbTipo.setValue(TipoTransaccion.DEPOSITO);
        cmbFrecuencia.setItems(FXCollections.observableArrayList(FrecuenciaProgramada.values()));
        cmbFrecuencia.setValue(FrecuenciaProgramada.MENSUAL);

        dpFechaInicio.setValue(LocalDate.now().plusDays(1));
        dpFechaCorte.setValue(LocalDate.now().plusDays(7));

        tblProgramadas.setItems(listaProgramadas);
        onTipoCambiado();
        refrescar();
    }

    public void refrescar() {
        VistaUtil.recargarClientes(cmbCliente, clienteController.listar());
        VistaUtil.recargarClientes(cmbClienteDestino, clienteController.listar());
        cargarMonederos(cmbCliente, cmbMonederoOrigen);
        cargarMonederos(cmbClienteDestino, cmbMonederoDestino);
        cargarProgramadas();
    }

    @FXML
    void onClienteSeleccionado() {
        cargarMonederos(cmbCliente, cmbMonederoOrigen);
        cargarProgramadas();
    }

    @FXML
    void onClienteDestinoSeleccionado() {
        cargarMonederos(cmbClienteDestino, cmbMonederoDestino);
    }

    @FXML
    void onTipoCambiado() {
        boolean esTransferencia = cmbTipo.getValue() == TipoTransaccion.TRANSFERENCIA;
        cmbClienteDestino.setDisable(!esTransferencia);
        cmbMonederoDestino.setDisable(!esTransferencia);
    }

    @FXML
    void onProgramar() {
        Cliente cliente = cmbCliente.getValue();
        Monedero monedero = cmbMonederoOrigen.getValue();
        if (cliente == null || monedero == null || cmbTipo.getValue() == null || cmbFrecuencia.getValue() == null) {
            VistaUtil.advertencia("Complete el cliente, tipo, frecuencia y monedero.");
            return;
        }
        double monto;
        try {
            monto = Double.parseDouble(txtMonto.getText().trim());
        } catch (NumberFormatException e) {
            VistaUtil.error("Ingrese un monto numérico válido.");
            return;
        }

        String idClienteDest = null;
        String idMonederoDest = null;
        if (cmbTipo.getValue() == TipoTransaccion.TRANSFERENCIA) {
            if (cmbClienteDestino.getValue() == null || cmbMonederoDestino.getValue() == null) {
                VistaUtil.advertencia("Para transferencias programadas seleccione cliente y monedero destino.");
                return;
            }
            idClienteDest = cmbClienteDestino.getValue().getIdentificacion();
            idMonederoDest = cmbMonederoDestino.getValue().getIdMonedero();
        }

        try {
            String msg = programadaController.programar(cliente.getIdentificacion(), monedero.getIdMonedero(),
                    idClienteDest, idMonederoDest, dpFechaInicio.getValue(),
                    cmbFrecuencia.getValue(), monto, cmbTipo.getValue());
            VistaUtil.info(msg);
            txtMonto.clear();
            cargarProgramadas();
        } catch (Exception e) {
            VistaUtil.error(e.getMessage());
        }
    }

    @FXML
    void onCancelar() {
        TransaccionProgramada seleccionada = tblProgramadas.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            VistaUtil.advertencia("Seleccione una programación de la tabla.");
            return;
        }
        try {
            VistaUtil.info(programadaController.cancelar(seleccionada.getIdProgramacion()));
            cargarProgramadas();
        } catch (Exception e) {
            VistaUtil.error(e.getMessage());
        }
    }

    @FXML
    void onEjecutarHasta() {
        LocalDate corte = dpFechaCorte.getValue();
        if (corte == null) {
            VistaUtil.advertencia("Seleccione una fecha de corte.");
            return;
        }
        List<String> resultados = programadaController.ejecutarHasta(corte);
        if (resultados.isEmpty()) {
            VistaUtil.info("No hay transacciones programadas vencidas hasta " + corte + ".");
        } else {
            VistaUtil.info("Resultados de ejecución:\n" + String.join("\n", resultados));
        }
        cargarProgramadas();
    }

    @FXML
    void onEnviarRecordatorios() {
        int enviados = programadaController.enviarRecordatorios(LocalDate.now());
        VistaUtil.info("Recordatorios enviados para mañana (" + LocalDate.now().plusDays(1) + "): " + enviados
                + "\n(Verifique la salida del canal en consola).");
    }

    private void cargarMonederos(ComboBox<Cliente> comboCliente, ComboBox<Monedero> comboMonedero) {
        Cliente cliente = comboCliente.getValue();
        List<Monedero> monederos = new ArrayList<>();
        if (cliente != null) {
            monederos.addAll(monederoController.listar(cliente.getIdentificacion()));
        }
        VistaUtil.recargarMonederos(comboMonedero, monederos);
    }

    private void cargarProgramadas() {
        Cliente cliente = cmbCliente.getValue();
        if (cliente == null) {
            listaProgramadas.clear();
            return;
        }
        listaProgramadas.setAll(programadaController.listar(cliente.getIdentificacion()));
        tblProgramadas.refresh();
    }
}
