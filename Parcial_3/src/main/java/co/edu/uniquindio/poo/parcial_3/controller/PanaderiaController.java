package co.edu.uniquindio.poo.parcial_3.controller;

import co.edu.uniquindio.poo.parcial_3.model.Cajero;
import co.edu.uniquindio.poo.parcial_3.model.Empleado;
import co.edu.uniquindio.poo.parcial_3.model.Panaderia;
import co.edu.uniquindio.poo.parcial_3.model.PanaderoArtesanal;
import co.edu.uniquindio.poo.parcial_3.model.PanaderoIndustrial;
import co.edu.uniquindio.poo.parcial_3.model.Pedido;
import co.edu.uniquindio.poo.parcial_3.model.TipoPan;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PanaderiaController {
    private final Panaderia panaderia;

    public PanaderiaController() {
        this.panaderia = new Panaderia("Panadería El Trigal");
    }

    // Empleados -------------------------------------------------------------------------------

    public void registrarPanaderoArtesanal(String nombre, String documento, String codigo, int minutos) {
        validarEmpleado(nombre, documento, codigo, minutos);
        panaderia.registrarEmpleado(new PanaderoArtesanal(nombre, documento, codigo, minutos));
    }

    public void registrarPanaderoIndustrial(String nombre, String documento, String codigo, int minutos) {
        validarEmpleado(nombre, documento, codigo, minutos);
        panaderia.registrarEmpleado(new PanaderoIndustrial(nombre, documento, codigo, minutos));
    }

    public void registrarCajero(String nombre, String documento, String codigo, int minutos, String numeroCaja) {
        validarEmpleado(nombre, documento, codigo, minutos);
        if (numeroCaja == null || numeroCaja.isEmpty()) {
            throw new IllegalArgumentException("El número de caja es obligatorio para el cajero.");
        }
        panaderia.registrarEmpleado(new Cajero(nombre, documento, codigo, minutos, numeroCaja));
    }

    private void validarEmpleado(String nombre, String documento, String codigo, int minutos) {
        if (nombre == null || nombre.isEmpty() || documento == null || documento.isEmpty()
                || codigo == null || codigo.isEmpty()) {
            throw new IllegalArgumentException("Nombre, documento y código del empleado son obligatorios.");
        }
        if (minutos < 0) {
            throw new IllegalArgumentException("Los minutos disponibles no pueden ser negativos.");
        }
    }

    public List<Empleado> obtenerEmpleados() {
        return new ArrayList<>(panaderia.getEmpleados());
    }

    // Texto para mostrar al empleado asignado a un pedido
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

    // Pedidos -------------------------------------------------------------------------------

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

    public List<Pedido> obtenerPedidos() {
        return new ArrayList<>(panaderia.getPedidos());
    }

    // Demostración ------------------------------------------------------------------------

    // Carga empleados y pedidos de ejemplo para probar rápido la aplicación
    public void cargarDatosEjemplo() {
        if (!panaderia.getEmpleados().isEmpty() || !panaderia.getPedidos().isEmpty()) {
            throw new IllegalStateException("Los datos de ejemplo solo se pueden cargar con el sistema vacío.");
        }
        registrarPanaderoArtesanal("Ana", "1001", "E-01", 100);
        registrarPanaderoArtesanal("Diana", "1002", "E-02", 80);
        registrarPanaderoIndustrial("Carlos", "1003", "E-03", 120);
        registrarCajero("Lucía", "1004", "E-04", 120, "Caja 1");
        registrarPedido("P01", "Café Central", TipoPan.TRADICIONAL, 25);
        registrarPedido("P02", "Restaurante La Plaza", TipoPan.INTEGRAL, 13);
    }
}
