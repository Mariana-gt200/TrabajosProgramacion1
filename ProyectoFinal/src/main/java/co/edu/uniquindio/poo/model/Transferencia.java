package co.edu.uniquindio.poo.model;

import java.time.LocalDate;

public class Transferencia extends Transaccion {

    private Monedero monederoOrigen;
    private Monedero monederoDestino;
    private double comision;

    public Transferencia(String idTransaccion, LocalDate fecha, double monto, EstadoTransaccion estado, String idCliente, Monedero monederoOrigen, Monedero monederoDestino, double comision) {
        super(idTransaccion, fecha, monto, estado, idCliente);
        this.monederoOrigen = monederoOrigen;
        this.monederoDestino = monederoDestino;
        this.comision = comision;
    }

    @Override
    public String nombreTipo() {
        return "Transferencia";
    }
    @Override
    public int calcularPuntosGenerados() {
        return (int) (getMonto() / 100) * 3;
    }


    //Getter y setter

    public Monedero getMonederoOrigen() {
        return monederoOrigen;
    }

    public void setMonederoOrigen(Monedero monederoOrigen) {
        this.monederoOrigen = monederoOrigen;
    }

    public Monedero getMonederoDestino() {
        return monederoDestino;
    }

    public void setMonederoDestino(Monedero monederoDestino) {
        this.monederoDestino = monederoDestino;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }
}