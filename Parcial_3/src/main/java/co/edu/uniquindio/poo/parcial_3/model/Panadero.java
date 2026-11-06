package co.edu.uniquindio.poo.model;

public abstract class Panadero extends Empleado implements IProduccion {

    public Panadero(String nombre, String documento, String codigo, int minutosDisponibles) {
        super(nombre, documento, codigo, minutosDisponibles);
    }
}