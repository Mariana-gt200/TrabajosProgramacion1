package co.edu.uniquindio.poo.model;

import java.time.LocalDate;

public class Retiro extends Transaccion {

    private Monedero monederoOrigen;

    //Constructor

    public Retiro(String idTransaccion, LocalDate fecha, double monto, EstadoTransaccion estado, String idCliente, Monedero monederoOrigen) {
        super(idTransaccion, fecha, monto, estado, idCliente);
        this.monederoOrigen = monederoOrigen;
    }

    @Override
    public String nombreTipo() {
        return "Retiro";
    }

    @Override
    public int calcularPuntosGenerados() {
        return (int) (getMonto() / 100) * 2;
    }
    //Get y set
    public Monedero getMonederoOrigen() {
        return monederoOrigen;
    }

    public void setMonederoOrigen(Monedero monederoOrigen) {
        this.monederoOrigen = monederoOrigen;
    }
}