package co.edu.uniquindio.poo.model;

import java.util.ArrayList;
import java.util.List;

public class Panaderia implements IGestionPedidos {
    private String nombre;
    private List<Empleado> empleados;
    private List<Pedido> pedidos;

    public Panaderia(String nombre) {
        this.nombre = nombre;
        this.empleados = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}