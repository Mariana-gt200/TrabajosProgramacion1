package co.edu.uniquindio.poo.controller;

import co.edu.uniquindio.poo.model.AnalizadorPatronesGastos;
import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.EstadoTransaccion;
import co.edu.uniquindio.poo.model.MonederoVirtual;
import co.edu.uniquindio.poo.model.ReporteGasto;
import co.edu.uniquindio.poo.model.Transaccion;

import java.util.List;

public class ReporteController {

    private final MonederoVirtual monederoVirtual;

    public ReporteController(MonederoVirtual monederoVirtual) {
        this.monederoVirtual = monederoVirtual;
    }

    public ReporteGasto generarReporte(Cliente cliente) {
        return monederoVirtual.getAnalizadorPatronesGastos()
                .generarReporte(cliente, monederoVirtual.getListaTransacciones());
    }

    public List<Transaccion> transaccionesCliente(String idCliente) {
        return monederoVirtual.obtenerHistorialCliente(idCliente);
    }

    public String analizarTransaccion(Transaccion transaccion) {
        List<Transaccion> referencia = monederoVirtual.obtenerHistorialCliente(transaccion.getIdCliente()).stream()
                .filter(x -> !x.getIdTransaccion().equals(transaccion.getIdTransaccion()))
                .filter(x -> x.nombreTipo().equals(transaccion.nombreTipo()))
                .filter(x -> x.getEstado() != EstadoTransaccion.SOSPECHOSA
                        && x.getEstado() != EstadoTransaccion.REVERTIDA)
                .toList();

        AnalizadorPatronesGastos analizador = monederoVirtual.getAnalizadorPatronesGastos();
        double promedio = analizador.calcularPromedio(referencia);
        double desviacion = analizador.calcularDesviacionEstandar(referencia);
        double umbral = promedio + 2 * desviacion;
        boolean atipica = analizador.detectarTransaccionAtipica(transaccion, referencia);

        return "Transacción evaluada: " + transaccion.getIdTransaccion() + " (" + transaccion.nombreTipo() + " por $" + transaccion.getMonto() + ")\n"
                + "Transacciones históricas de referencia: " + referencia.size() + "\n"
                + "Promedio histórico: $" + String.format("%.2f", promedio) + "\n"
                + "Desviación estándar: $" + String.format("%.2f", desviacion) + "\n"
                + "Umbral atípico (promedio + 2 × desviación): $" + String.format("%.2f", umbral) + "\n"
                + "Resultado: " + (atipica
                ? "ATÍPICA (supera el umbral estadístico — Estado: " + transaccion.getEstado() + ")"
                : "NORMAL (dentro del comportamiento habitual — Estado: " + transaccion.getEstado() + ")");
    }
}
