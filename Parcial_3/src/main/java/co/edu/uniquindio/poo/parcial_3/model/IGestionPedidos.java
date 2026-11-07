package co.edu.uniquindio.poo.parcial_3.model;

public interface IGestionPedidos {
    Pedido registrarPedido(String codigo, String nombreCliente, TipoPan tipoPan, int cantidad);
    void asignarPedido(String codigoPedido);
    void cancelarPedido(String codigoPedido);
}