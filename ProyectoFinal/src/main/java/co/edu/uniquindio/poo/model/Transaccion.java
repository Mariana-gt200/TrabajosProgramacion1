package co.edu.uniquindio.poo.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class Transaccion {
    private String idTransaccion;
    private Date fecha;
    private double monto;
    private TipoTransaccion tipo;
    private EstadoTransaccion estado;
    private boolean esAtipica;

    private final ArrayList<Monedero> listaMonederos = new ArrayList<>();
    private final ArrayList<TransaccionProgramada> ListaTransaccionesProgramadas = new ArrayList<>();
    private final ArrayList<Transferencia> listaTransferencias = new ArrayList<>();
    private final ArrayList<Retiro> listaRetiros = new ArrayList<>();
    private final ArrayList<Deposito> listaDepositos = new ArrayList<>();


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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }


}

