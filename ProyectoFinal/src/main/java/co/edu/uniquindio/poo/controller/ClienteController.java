package co.edu.uniquindio.poo.controller;

import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.MonederoVirtual;

import java.util.List;

public class ClienteController {

    private final MonederoVirtual monederoVirtual;

    public ClienteController(MonederoVirtual monederoVirtual) {
        this.monederoVirtual = monederoVirtual;
    }

    public String registrar(Cliente cliente) {
        return monederoVirtual.registrarCliente(cliente);
    }

    public String actualizar(String idCliente, Cliente cliente) {
        return monederoVirtual.actualizarCliente(idCliente, cliente);
    }

    public String eliminar(String idCliente) {
        return monederoVirtual.eliminarCliente(idCliente);
    }

    public List<Cliente> listar() {
        return monederoVirtual.obtenerClientes();
    }
}