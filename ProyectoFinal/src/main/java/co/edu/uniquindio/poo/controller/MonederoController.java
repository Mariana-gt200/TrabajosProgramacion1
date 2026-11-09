package co.edu.uniquindio.poo.controller;

import co.edu.uniquindio.poo.model.Monedero;
import co.edu.uniquindio.poo.model.MonederoVirtual;

import java.util.List;

public class MonederoController {

    private final MonederoVirtual monederoVirtual;

    public MonederoController(MonederoVirtual monederoVirtual) {
        this.monederoVirtual = monederoVirtual;
    }

    public String registrar(String idCliente, Monedero monedero) {
        return monederoVirtual.registrarMonedero(idCliente, monedero);
    }

    public String eliminar(String idCliente, String idMonedero) {
        return monederoVirtual.eliminarMonedero(idCliente, idMonedero);
    }

    public List<Monedero> listar(String idCliente) {
        return monederoVirtual.obtenerMonederos(idCliente);
    }

    public double saldoTotal(String idCliente) {
        return monederoVirtual.consultarSaldoTotal(idCliente);
    }
}