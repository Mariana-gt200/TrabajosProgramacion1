package co.edu.uniquindio.poo.controller;

import co.edu.uniquindio.poo.model.ClienteNoEncontradoException;
import co.edu.uniquindio.poo.model.MonederoVirtual;
import co.edu.uniquindio.poo.model.Transaccion;

import java.util.List;

public class PuntosController {

    private final MonederoVirtual monederoVirtual;

    public PuntosController(MonederoVirtual monederoVirtual) {
        this.monederoVirtual = monederoVirtual;
    }

    public int puntosAcumulados(String idCliente) {
        return monederoVirtual.calcularPuntos(idCliente);
    }

    public int puntosDisponibles(String idCliente) throws ClienteNoEncontradoException {
        return monederoVirtual.consultarPuntosDisponibles(idCliente);
    }

    public List<Transaccion> historialPuntos(String idCliente) {
        return monederoVirtual.consultarHistorialPuntos(idCliente);
    }

    public String canjearDescuentoComision(String idCliente) throws Exception {
        return monederoVirtual.canjearDescuentoComision(idCliente);
    }

    public String canjearMesSinCargos(String idCliente) throws Exception {
        return monederoVirtual.canjearMesSinCargos(idCliente);
    }

    public String canjearBonoSaldo(String idCliente, String idMonedero) throws Exception {
        return monederoVirtual.canjearBonoSaldo(idCliente, idMonedero);
    }
}
