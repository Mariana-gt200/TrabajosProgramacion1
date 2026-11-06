package co.edu.uniquindio.poo.model;

public class Cajero extends Empleado {
    private String numeroCaja;

    public Cajero(String nombre, String documento, String codigo, int minutosDisponibles, String numeroCaja) {
        super(nombre, documento, codigo, minutosDisponibles);
        this.numeroCaja = numeroCaja;
    }

    public String getNumeroCaja() {
        return numeroCaja;
    }

    public void setNumeroCaja(String numeroCaja) {
        this.numeroCaja = numeroCaja;
    }
}