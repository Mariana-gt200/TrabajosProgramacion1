package co.edu.uniquindio.poo.model;

public class PanaderoArtesanal extends Panadero {
    private static final int CAPACIDAD_BANDEJA = 12;

    public PanaderoArtesanal(String nombre, String documento, String codigo, int minutosDisponibles) {
        super(nombre, documento, codigo, minutosDisponibles);
    }
}