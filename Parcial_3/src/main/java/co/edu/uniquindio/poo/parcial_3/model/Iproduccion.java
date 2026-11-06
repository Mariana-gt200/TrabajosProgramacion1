package co.edu.uniquindio.poo.model;

public interface IProduccion {
    int calcularMinutos(TipoPan tipoPan, int cantidad);
    boolean puedeAtender(Pedido pedido);
}