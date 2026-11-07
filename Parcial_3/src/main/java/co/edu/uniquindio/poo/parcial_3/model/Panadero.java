package co.edu.uniquindio.poo.parcial_3.model;

public abstract class Panadero extends Empleado implements IProduccion {

    public Panadero(String nombre, String documento, String codigo, int minutosDisponibles) {
        super(nombre, documento, codigo, minutosDisponibles);
    }

    // Un panadero puede atender el pedido si le alcanzan los minutos para el pedido completo
    @Override
    public boolean puedeAtender(Pedido pedido) {
        int minutosNecesarios = calcularMinutos(pedido.getTipoPan(), pedido.getCantidadUnidades());
        return getMinutosDisponibles() >= minutosNecesarios;
    }
}