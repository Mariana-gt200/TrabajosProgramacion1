package co.edu.uniquindio.poo.controller;

import co.edu.uniquindio.poo.model.FrecuenciaProgramada;
import co.edu.uniquindio.poo.model.MonederoVirtual;
import co.edu.uniquindio.poo.model.TipoTransaccion;
import co.edu.uniquindio.poo.model.TransaccionProgramada;

import java.time.LocalDate;
import java.util.List;

public class ProgramadaController {

    private final MonederoVirtual monederoVirtual;

    public ProgramadaController(MonederoVirtual monederoVirtual) {
        this.monederoVirtual = monederoVirtual;
    }

    public String programar(String idCliente, String idMonedero, String idClienteDestino,
                            String idMonederoDestino, LocalDate fechaInicio,
                            FrecuenciaProgramada frecuencia, double monto, TipoTransaccion tipo) throws Exception {
        return monederoVirtual.programarTransaccion(idCliente, idMonedero, idClienteDestino,
                idMonederoDestino, fechaInicio, frecuencia, monto, tipo);
    }

    public List<TransaccionProgramada> listar(String idCliente) {
        return monederoVirtual.consultarProgramadas(idCliente);
    }

    public String cancelar(String idProgramacion) throws Exception {
        return monederoVirtual.cancelarProgramada(idProgramacion);
    }

    public List<String> ejecutarHasta(LocalDate fechaCorte) {
        return monederoVirtual.ejecutarProgramadas(fechaCorte);
    }

    public int enviarRecordatorios(LocalDate hoy) {
        return monederoVirtual.enviarRecordatoriosProgramadas(hoy);
    }
}
