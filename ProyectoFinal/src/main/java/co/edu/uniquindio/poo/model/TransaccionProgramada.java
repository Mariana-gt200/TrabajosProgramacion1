package co.edu.uniquindio.poo.model;

import java.util.Date;

public class TransaccionProgramada {
    private String idProgramacion;
    private Date fechaInicio;
    private Date fechaProximaEjecucion;
    private FrecuenciaProgramada frecuencia;
    private double monto;
    private TipoTransaccion tipo;

    public void TransaccionProgramada(String idProgramacion, Date fechaInicio, Date fechaProximaEjecucion, FrecuenciaProgramada frecuencia, double monto, TipoTransaccion tipo) {
        this.idProgramacion = idProgramacion;
        this.fechaInicio = fechaInicio;
        this.fechaProximaEjecucion = fechaProximaEjecucion;
        this.frecuencia = frecuencia;
        this.monto = monto;
        this.tipo = tipo;
    }

    // Getters y Setters
    public String getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(String idProgramacion) {
        this.idProgramacion = idProgramacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaProximaEjecucion() {
        return fechaProximaEjecucion;
    }

    public void setFechaProximaEjecucion(Date fechaProximaEjecucion) {
        this.fechaProximaEjecucion = fechaProximaEjecucion;
    }

    public FrecuenciaProgramada getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(FrecuenciaProgramada frecuencia) {
        this.frecuencia = frecuencia;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public TipoTransaccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransaccion tipo) {
        this.tipo = tipo;
    }
}
