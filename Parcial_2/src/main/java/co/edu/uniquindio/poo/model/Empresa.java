package co.edu.uniquindio.poo.model;
import co.edu.uniquindio.poo.model.Dispositivo;

public class Empresa {
    private final String nombre;
    private Arraylist<Cliente> listaClientes;
    private Arraylist<Tecnico> listaTecnicos;
    private Arraylist<Repuesto> listaRepuestos;
    private Arraylist<OrdenServicio> listaOrdenesServicio;

    public Empresa(String nombre, Arraylist<Tecnico> listaTecnicos, Arraylist<Cliente> listaClientes, Arraylist<Repuesto> listaRepuestos, Arraylist<OrdenServicio> listaOrdenesServicio) {
        this.nombre = nombre;
        this.listaTecnicos = listaTecnicos;
        this.listaClientes = listaClientes;
        this.listaRepuestos = listaRepuestos;
        this.listaOrdenesServicio = listaOrdenesServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public Arraylist<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(Arraylist<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public Arraylist<Tecnico> getListaTecnicos() {
        return listaTecnicos;
    }

    public void setListaTecnicos(Arraylist<Tecnico> listaTecnicos) {
        this.listaTecnicos = listaTecnicos;
    }

    public Arraylist<Repuesto> getListaRepuestos() {
        return listaRepuestos;
    }

    public void setListaRepuestos(Arraylist<Repuesto> listaRepuestos) {
        this.listaRepuestos = listaRepuestos;
    }

    public Arraylist<OrdenServicio> getListaOrdenesServicio() {
        return listaOrdenesServicio;
    }

    public void setListaOrdenesServicio(Arraylist<OrdenServicio> listaOrdenesServicio) {
        this.listaOrdenesServicio = listaOrdenesServicio;
    }
}
