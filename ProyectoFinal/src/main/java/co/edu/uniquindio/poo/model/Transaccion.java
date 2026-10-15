package co.edu.uniquindio.poo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public abstract class Transaccion {
    private String idTransaccion;
    private LocalDate fecha;
    private double monto;
    private EstadoTransaccion estado;
    private String idCliente;

    //Constructor
    public Transaccion(String idTransaccion, LocalDate fecha, double monto, EstadoTransaccion estado, String idCliente) {
        this.idTransaccion = idTransaccion;
        this.fecha = fecha;
        this.monto = monto;
        this.estado = estado;
        this.idCliente = idCliente;
    }

    public abstract String nombreTipo();

    //Get y set
    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public EstadoTransaccion getEstado() {
        return estado;
    }

    public void setEstado(EstadoTransaccion estado) {
        this.estado = estado;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public abstract int calcularPuntosGenerados();
}