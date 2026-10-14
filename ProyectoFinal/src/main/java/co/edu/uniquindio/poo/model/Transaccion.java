package co.edu.uniquindio.poo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Transaccion {
    private String idTransaccion;
    private LocalDate fecha;
    private double monto;
    private TipoTransaccion tipo;
    private EstadoTransaccion estado;
    private boolean esAtipica;
    private String idCliente;

    private final ArrayList<Monedero> listaMonederos = new ArrayList<>();
    private final ArrayList<TransaccionProgramada> ListaTransaccionesProgramadas = new ArrayList<>();
    private final ArrayList<Transferencia> listaTransferencias = new ArrayList<>();
    private final ArrayList<Retiro> listaRetiros = new ArrayList<>();
    private final ArrayList<Deposito> listaDepositos = new ArrayList<>();

    //Constructor
    public Transaccion(String idTransaccion, LocalDate fecha, double monto, TipoTransaccion tipo, EstadoTransaccion estado, boolean esAtipica, String idCliente) {
        this.idTransaccion = idTransaccion;
        this.fecha = fecha;
        this.monto = monto;
        this.tipo = tipo;
        this.estado = estado;
        this.esAtipica = esAtipica;
        this.idCliente = idCliente;
    }

    //Get y set
    public ArrayList<Deposito> getListaDepositos() {
        return listaDepositos;
    }

    public ArrayList<Retiro> getListaRetiros() {
        return listaRetiros;
    }

    public ArrayList<Transferencia> getListaTransferencias() {
        return listaTransferencias;
    }

    public ArrayList<TransaccionProgramada> getListaTransaccionesProgramadas() {
        return ListaTransaccionesProgramadas;
    }

    public ArrayList<Monedero> getListaMonederos() {
        return listaMonederos;
    }

    public boolean isEsAtipica() {
        return esAtipica;
    }

    public void setEsAtipica(boolean esAtipica) {
        this.esAtipica = esAtipica;
    }

    public EstadoTransaccion getEstado() {
        return estado;
    }

    public void setEstado(EstadoTransaccion estado) {
        this.estado = estado;
    }

    public TipoTransaccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransaccion tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }
}