package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.app.App;
import co.edu.uniquindio.poo.controller.ClienteController;
import co.edu.uniquindio.poo.controller.MonederoController;
import co.edu.uniquindio.poo.controller.TransaccionController;
import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.Monedero;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class OperacionViewController {

    private ClienteController clienteController;
    private MonederoController monederoController;
    private TransaccionController transaccionController;

    @FXML
    private ComboBox<Cliente> cmbCliente;
    @FXML
    private ComboBox<Monedero> cmbMonedero;
    @FXML
    private Label lblSaldo;
    @FXML
    private TextField txtMonto;
    @FXML
    private ComboBox<Cliente> cmbClienteDestino;
    @FXML
    private ComboBox<Monedero> cmbMonederoDestino;

    @FXML
    void initialize() {
        clienteController = new ClienteController(App.monederoVirtual);
        monederoController = new MonederoController(App.monederoVirtual);
        transaccionController = new TransaccionController(App.monederoVirtual);
        refrescar();
    }

    // Se llama al abrir la pestaña para mostrar los datos actuales
    public void refrescar() {
        VistaUtil.recargarClientes(cmbCliente, clienteController.listar());
        VistaUtil.recargarClientes(cmbClienteDestino, clienteController.listar());
        cargarMonederos(cmbCliente, cmbMonedero);
        cargarMonederos(cmbClienteDestino, cmbMonederoDestino);
    }

    @FXML
    void onClienteOrigen() {
        cargarMonederos(cmbCliente, cmbMonedero);
    }

    @FXML
    void onClienteDestino() {
        cargarMonederos(cmbClienteDestino, cmbMonederoDestino);
    }

    @FXML
    void onMonedero() {
        mostrarSaldo();
    }

    @FXML
    void onDepositar() {
        Cliente cliente = cmbCliente.getValue();
        Monedero monedero = cmbMonedero.getValue();
        if (cliente == null || monedero == null) {
            VistaUtil.advertencia("Seleccione el cliente y el monedero.");
            return;
        }
        try {
            double monto = Double.parseDouble(txtMonto.getText().trim());
            String mensaje = transaccionController.depositar(cliente.getIdentificacion(), monedero.getIdMonedero(), monto);
            VistaUtil.info(mensaje);
            txtMonto.clear();
        } catch (NumberFormatException e) {
            VistaUtil.error("Ingrese un monto numérico válido.");
        } catch (Exception e) {
            VistaUtil.error(e.getMessage());
        }
        mostrarSaldo();
    }

    @FXML
    void onRetirar() {
        Cliente cliente = cmbCliente.getValue();
        Monedero monedero = cmbMonedero.getValue();
        if (cliente == null || monedero == null) {
            VistaUtil.advertencia("Seleccione el cliente y el monedero.");
            return;
        }
        try {
            double monto = Double.parseDouble(txtMonto.getText().trim());
            String mensaje = transaccionController.retirar(cliente.getIdentificacion(), monedero.getIdMonedero(), monto);
            VistaUtil.info(mensaje);
            txtMonto.clear();
        } catch (NumberFormatException e) {
            VistaUtil.error("Ingrese un monto numérico válido.");
        } catch (Exception e) {
            VistaUtil.error(e.getMessage());
        }
        mostrarSaldo();
    }

    @FXML
    void onTransferir() {
        Cliente origen = cmbCliente.getValue();
        Monedero monederoOrigen = cmbMonedero.getValue();
        Cliente destino = cmbClienteDestino.getValue();
        Monedero monederoDestino = cmbMonederoDestino.getValue();
        if (origen == null || monederoOrigen == null || destino == null || monederoDestino == null) {
            VistaUtil.advertencia("Seleccione el cliente y monedero de origen y de destino.");
            return;
        }
        try {
            double monto = Double.parseDouble(txtMonto.getText().trim());
            String mensaje = transaccionController.transferir(origen.getIdentificacion(), monederoOrigen.getIdMonedero(),
                    destino.getIdentificacion(), monederoDestino.getIdMonedero(), monto);
            VistaUtil.info(mensaje);
            txtMonto.clear();
        } catch (NumberFormatException e) {
            VistaUtil.error("Ingrese un monto numérico válido.");
        } catch (Exception e) {
            VistaUtil.error(e.getMessage());
        }
        mostrarSaldo();
    }

    private void cargarMonederos(ComboBox<Cliente> comboCliente, ComboBox<Monedero> comboMonedero) {
        Cliente cliente = comboCliente.getValue();
        List<Monedero> monederos = new ArrayList<>();
        if (cliente != null) {
            monederos.addAll(monederoController.listar(cliente.getIdentificacion()));
        }
        VistaUtil.recargarMonederos(comboMonedero, monederos);
        mostrarSaldo();
    }

    private void mostrarSaldo() {
        Monedero monedero = cmbMonedero.getValue();
        if (monedero == null) {
            lblSaldo.setText("Saldo: -");
        } else {
            lblSaldo.setText("Saldo: $" + monedero.getSaldo());
        }
    }
}