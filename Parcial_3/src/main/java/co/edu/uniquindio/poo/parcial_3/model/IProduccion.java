package co.edu.uniquindio.poo.parcial_3.model;

public interface IProduccion {
    int calcularMinutos(TipoPan tipoPan, int cantidad);
    boolean puedeAtender(Pedido pedido);
}