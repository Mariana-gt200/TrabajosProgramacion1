package co.edu.uniquindio.poo.controller;

import co.edu.uniquindio.poo.model.MonederoVirtual;
import co.edu.uniquindio.poo.model.Transaccion;

import java.util.List;

public class TransaccionController {

    private final MonederoVirtual monederoVirtual;

    public TransaccionController(MonederoVirtual monederoVirtual) {
        this.monederoVirtual = monederoVirtual;
    }

    // Las operaciones lanzan las excepciones del modelo; la vista las muestra al usuario
    public String depositar(String idCliente, String idMonedero, double monto) throws Exception {
        return monederoVirtual.depositar(idCliente, idMonedero, monto);
    }

    public String retirar(String idCliente, String idMonedero, double monto) throws Exception {
        return monederoVirtual.retirar(idCliente, idMonedero, monto);
    }

    public String transferir(String idClienteOrigen, String idMonederoOrigen,
                             String idClienteDestino, String idMonederoDestino, double monto) throws Exception {
        return monederoVirtual.transferir(idClienteOrigen, idMonederoOrigen, idClienteDestino, idMonederoDestino, monto);
    }

    public String revertir(String idTransaccion) throws Exception {
        return monederoVirtual.revertirTransaccion(idTransaccion);
    }

    public List<Transaccion> historial(String idCliente) {
        return monederoVirtual.consultarHistorial(idCliente);
    }

    public int puntos(String idCliente) {
        return monederoVirtual.calcularPuntos(idCliente);
    }

    public String rango(String idCliente) {
        return monederoVirtual.buscarCliente(idCliente)
                .map(c -> String.valueOf(c.getRango()))
                .orElse("-");
    }
}