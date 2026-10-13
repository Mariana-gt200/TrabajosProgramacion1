
package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.Transaccion;
import java.util.List;
import java.util.Optional;
import java.util.Collections;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MonederoVirtual {
    private final List<Cliente> listaClientes;
    private final List<Monedero> listaMonederos;
    private final List<Transaccion> listaTransacciones;
    private final ServicioNotificacion servicioNotificacion;
    private final AnalizadorPatronesGastos analizadorPatronesGastos;

    //Constructor
    public MonederoVirtual(List<Cliente> listaClientes, List<Monedero> listaMonederos, List<Transaccion> listaTransacciones, ServicioNotificacion servicioNotificacion, AnalizadorPatronesGastos analizadorPatronesGastos) {
        this.listaClientes = listaClientes;
        this.listaMonederos = listaMonederos;
        this.listaTransacciones = listaTransacciones;
        this.servicioNotificacion = servicioNotificacion;
        this.analizadorPatronesGastos = analizadorPatronesGastos;
    }

    //Get
    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public List<Monedero> getListaMonederos() {
        return listaMonederos;
    }

    public List<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }

    public ServicioNotificacion getServicioNotificacion() {
        return servicioNotificacion;
    }

    public AnalizadorPatronesGastos getAnalizadorPatronesGastos() {
        return analizadorPatronesGastos;
    }

    //Metodos

    //CRUD clientes-----------------------------------------------------------------------
    //Registrar cliente
    public String registrarCliente(Cliente cliente) {
        return buscarCliente(cliente.getIdentificacion())
                .map(c -> "El cliente ya está registrado.")
                .orElseGet(() -> {
                    listaClientes.add(cliente);
                    return "Cliente registrado exitosamente.";
                });
    }

    //Buscar cliente
    public Optional<Cliente> buscarCliente(String identificacion) {
        return listaClientes.stream()
                .filter(c -> c.getIdentificacion().equals(identificacion))
                .findFirst();
    }

    //Obtener clientes
    public List<Cliente> obtenerClientes() {
        return Collections.unmodifiableList(listaClientes);
    }

    //Actualizar cliente
    public String actualizarCliente(String idCliente, Cliente clienteActualizado) {
        if (!idCliente.equals(clienteActualizado.getIdentificacion())) {
            return "No se puede actualizar, la identificacion del cliente no puede modificarse.";
        }

        return buscarCliente(idCliente)
                .map(c -> {
                    listaClientes.set(listaClientes.indexOf(c), clienteActualizado);
                    return "El cliente fue actualizado exitosamente";
                })
                .orElse("No se puede actualizar, no existe un cliente con esa identificación.");
    }

    //Eliminar cliente
    public String eliminarCliente(String idCliente) {
        return buscarCliente(idCliente)
                .map(c -> {
                    listaClientes.remove(c);
                    return "El cliente fue eliminado exitosamente";
                })
                .orElse("No se puede eliminar, no existe un cliente con esa identificacion.");
    }
    //---------------------------------------------------------------------------------------

    //CRUD Monederos-------------------------------------------------------------------------
    //Registrar monedero
    public String registrarMonedero(String idCliente, Monedero monedero) {
        if (buscarCliente(idCliente).isEmpty()) {
            return "No se puede registrar el monedero porque el cliente no existe.";
        } else if ((buscarMonedero(idCliente, monedero.getIdMonedero()).isPresent())) {
            return "Ya existe un monedero con ese id para ese cliente.";
        } else {
            listaMonederos.add(monedero);
            return "El monedero fue registrado exitosamente";
        }
    }

    //Buscar monedero
    public Optional<Monedero> buscarMonedero(String idCliente, String idMonedero) {
        return listaMonederos.stream()
                .filter(m -> m.getIdentificacionCliente().equals(idCliente)
                && m.getIdMonedero().equals(idMonedero))
                .findFirst();
    }

    //Obtener monederos
    public List<Monedero> obtenerMonederos(String idCliente) {
        if (buscarCliente(idCliente).isEmpty()) {
            return Collections.emptyList();
        }
        List<Monedero> monederos = Collections.unmodifiableList(
                listaMonederos.stream()
                        .filter(m -> m.getIdentificacionCliente().equals(idCliente))
                        .collect(Collectors.toList())
        );
        return monederos;
    }

    //Actualizar monedero
    public String actualizarMonedero(String idCliente, String idMonedero, Monedero monederoActualizado) {
        if (!idCliente.equals(monederoActualizado.getIdentificacionCliente()) || !idMonedero.equals(monederoActualizado.getIdMonedero())) {
            return "No se puede actualizar, la identificacion del cliente o el id del monedero no puede modificarse.";
        }
        return buscarMonedero(idCliente, idMonedero)
                .map(m -> {
                    listaMonederos.set(listaMonederos.indexOf(m), monederoActualizado);
                    return "El monedero fue actualizado exitosamente";
                })
                .orElse("No se puede actualizar, no existe un monedero con esa identificación.");
    }

    //Eliminar monedero
    public String eliminarMonedero(String idCliente, String idMonedero) {
        return buscarMonedero(idCliente, idMonedero)
                .map(m -> {
                    listaMonederos.remove(m);
                    return "El monedero fue eliminado exitosamente";
                })
                .orElse("No se puede eliminar, no existe un monedero con esa identificacion.");
    }
    //--------------------------------------------------------------------------------------------

    //CRUD Transacciones--------------------------------------------------------------------------
    //Registrar transaccion
    public String registrarTransaccion(Transaccion transaccion) {
        if (buscarTransaccion(transaccion.getIdTransaccion()).isPresent()) {
            return "No se puede registrar, ya existe una transacción con ese id.";
        }

        listaTransacciones.add(transaccion);
        return "La transacción fue registrada exitosamente";
    }

    //Buscar transaccion
    public Optional<Transaccion> buscarTransaccion(String idTransaccion) {
        return listaTransacciones.stream()
                .filter(t -> t.getIdTransaccion().equals(idTransaccion))
                .findFirst();
    }

    //Obtener transacciones
    public List<Transaccion> obtenerHistorialCliente(String idCliente) {
        return listaTransacciones.stream()
                .filter(t -> t.getIdCliente().equals(idCliente))
                .toList();
    }

    //Eliminar transaccion
    public String eliminarTransaccion(String idTransaccion) {
        return buscarTransaccion(idTransaccion)
                .map(t -> {
                    listaTransacciones.remove(t);
                    return "La transacción fue eliminada exitosamente";
                })
                .orElse("No se puede eliminar, no existe una transacción con esa identificación.");
    }
    //--------------------------------------------------------------------------------------------


}