package co.edu.uniquindio.poo.parcial_3.controller;

import co.edu.uniquindio.poo.parcial_3.model.Empleado;
import co.edu.uniquindio.poo.parcial_3.model.Panaderia;
import co.edu.uniquindio.poo.parcial_3.model.Pedido;
import co.edu.uniquindio.poo.parcial_3.model.TipoPan;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PedidoController {
    private Panaderia panaderia;

    public PedidoController(Panaderia panaderia) {
        this.panaderia = panaderia;
    }

    public Pedido registrarPedido(String codigo, String nombreCliente, TipoPan tipoPan, int cantidad) {
        return panaderia.registrarPedido(codigo, nombreCliente, tipoPan, cantidad);
    }

    public void asignarPedido(String codigoPedido) {
        panaderia.asignarPedido(codigoPedido);
    }

    public void cancelarPedido(String codigoPedido) {
        panaderia.cancelarPedido(codigoPedido);
    }

    public List<String> estimarTiempos(String codigoPedido) {
        return panaderia.estimarTiempos(codigoPedido);
    }

    public List<Pedido> obtenerListaPedidos() {
        return new ArrayList<>(panaderia.getPedidos());
    }

    // Texto del empleado asignado a un pedido
    public String describirEmpleado(String codigoEmpleado) {
        if (codigoEmpleado == null) {
            return "—";
        }
        Optional<Empleado> empleado = panaderia.buscarEmpleado(codigoEmpleado);
        if (empleado.isEmpty()) {
            return codigoEmpleado;
        }
        return empleado.get().getNombre() + " (" + codigoEmpleado + ")";
    }
}
