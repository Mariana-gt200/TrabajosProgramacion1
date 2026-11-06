package co.edu.uniquindio.poo.model;

public class PanaderoIndustrial extends Panadero {
    private static final int MINUTOS_PREPARACION = 20;
    private static final int CAPACIDAD_LOTE = 30;

    public PanaderoIndustrial(String nombre, String documento, String codigo, int minutosDisponibles) {
        super(nombre, documento, codigo, minutosDisponibles);
    }
}