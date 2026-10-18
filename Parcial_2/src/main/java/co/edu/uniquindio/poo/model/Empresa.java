package co.edu.uniquindio.poo.model;
import co.edu.uniquindio.poo.model.Dispositivo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Empresa {
    private final String nombre;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Tecnico> listaTecnicos;
    private ArrayList<Repuesto> listaRepuestos;
    private ArrayList<OrdenServicio> listaOrdenesServicio;
    private ArrayList<Dispositivo> listaDispositivos;

    public Empresa(String nombre, ArrayList<Cliente> listaClientes, ArrayList<Tecnico> listaTecnicos, ArrayList<Repuesto> listaRepuestos, ArrayList<OrdenServicio> listaOrdenesServicio) {
        this.nombre = nombre;
        this.listaClientes = listaClientes;
        this.listaTecnicos = listaTecnicos;
        this.listaRepuestos = listaRepuestos;
        this.listaOrdenesServicio = listaOrdenesServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public ArrayList<Tecnico> getListaTecnicos() {
        return listaTecnicos;
    }

    public void setListaTecnicos(ArrayList<Tecnico> listaTecnicos) {
        this.listaTecnicos = listaTecnicos;
    }

    public ArrayList<Repuesto> getListaRepuestos() {
        return listaRepuestos;
    }

    public void setListaRepuestos(ArrayList<Repuesto> listaRepuestos) {
        this.listaRepuestos = listaRepuestos;
    }

    public ArrayList<OrdenServicio> getListaOrdenesServicio() {
        return listaOrdenesServicio;
    }

    public void setListaOrdenesServicio(ArrayList<OrdenServicio> listaOrdenesServicio) {
        this.listaOrdenesServicio = listaOrdenesServicio;
    }

    // FUNCIONALIDAD 1 COMIENZO
    public Optional<Cliente> buscarCliente(String numeroDocumento) {
        for (Cliente cliente :listaClientes){
            if (numeroDocumento.equals(cliente.getNumeroDocumento())){
                return Optional.of(cliente);
            }
        }
        return Optional.empty();
    }
    public String registrarCliente(Cliente cliente){
        Optional<Cliente> clienteEncontrado = buscarCliente(cliente.getNumeroDocumento());
        if(clienteEncontrado.isEmpty()){
            listaClientes.add(cliente);
            return "El cliente fue registrado exitosamente";
        }else return "No se puede registrar, ya existe un cliente con esa informacion registrado anteriormente.";
    }
//FUNCIONALIDAD 1 FINAL

    // FUNCIONALIDAD 2 COMIENZO
    public Optional<Dispositivo> buscarEquipo(String codigoRegistro) {
        for (Dispositivo equipo : listaDispositivos) {
            if (codigoRegistro.equals(equipo.getCodigoRegistro())) {
                return Optional.of(equipo);
            }
        }
        return Optional.empty();
    }

    public String registrarEquipo(Dispositivo equipo) {
        Optional<Dispositivo> equipoEncontrado = buscarEquipo(equipo.getCodigoRegistro());
        if (equipoEncontrado.isEmpty()) {
            listaDispositivos.add(equipo);
            return "El equipo fue registrado exitosamente";
        } else {
            return "No se puede registrar, ya existe un equipo con ese código";
        }
    }
// FUNCIONALIDAD 2 FINAL

// FUNCIONALIDAD 3 COMIENZO
    public Optional<Tecnico> buscarTecnico(String documentoIdentificacion) {
        for (Tecnico tecnico : listaTecnicos) {
            if (documentoIdentificacion.equals(tecnico.getDocumentoIdentificacion())) {
                return Optional.of(tecnico);
            }
        }
        return Optional.empty();
    }

    public String registrarTecnico(Tecnico tecnico) {
        Optional<Tecnico> tecnicoEncontrado = buscarTecnico(tecnico.getDocumentoIdentificacion());
        if (tecnicoEncontrado.isEmpty()) {
            listaTecnicos.add(tecnico);
            return "El técnico fue registrado exitosamente";
        } else {
            return "No se puede registrar, ya existe un técnico con ese documento";
        }
    }
// FUNCIONALIDAD 3 FINAL

// FUNCIONALIDAD 4 COMIENZO
    public Optional<Repuesto> buscarRepuesto(String codigo) {
        for (Repuesto repuesto : listaRepuestos) {
            if (codigo.equalsIgnoreCase(repuesto.getCodigo())) {
                return Optional.of(repuesto);
            }
        }
        return Optional.empty();
    }

    public String registrarRepuesto(Repuesto repuesto) {
        Optional<Repuesto> repuestoEncontrado = buscarRepuesto(repuesto.getCodigo());
        if (repuestoEncontrado.isEmpty()) {
            listaRepuestos.add(repuesto);
            return "El repuesto fue registrado exitosamente en el inventario";
        } else {
            return "No se puede registrar, ya existe un repuesto con ese código";
        }
    }
// FUNCIONALIDAD 4 FINAL

    // FUNCIONALIDAD 5 COMIENZO
    public Optional<OrdenServicio> buscarOrdenServicio(String codigoUnico) {
        for (OrdenServicio orden : listaOrdenesServicio) {
            if (codigoUnico.equals(orden.getCodigoUnico())) {
                return Optional.of(orden);
            }
        }
        return Optional.empty();
    }

    public String registrarOrdenServicio(OrdenServicio orden) {
        Optional<OrdenServicio> ordenEncontrada = buscarOrdenServicio(orden.getCodigoUnico());
        if (ordenEncontrada.isEmpty()) {
            listaOrdenesServicio.add(orden);
            return "La prden de servicio fue registrada exitosamente";
        } else {
            return "No se puede registrar, ya existe una orden con ese código";
        }
    }
// FUNCIONALIDAD 5 FINAL

// FUNCIONALIDAD 6 COMIENZO
    public String agregarDetalleOrden(String codigoDetalle, Dispositivo dispositivo, Servicio servicio, String descripcionFalla, Tecnico tecnicoResponsable, String observacionesTecnicas, String estado) {
        Optional<OrdenServicio> ordenEncontrada = buscarOrdenServicio(codigoDetalle);

        if (ordenEncontrada.isPresent()) {
            OrdenServicio orden = ordenEncontrada.get();

            DetalleServicio nuevoDetalle = new DetalleServicio(codigoDetalle, dispositivo, servicio, descripcionFalla, tecnicoResponsable, observacionesTecnicas, estado);

            orden.getListaDetalles().add(nuevoDetalle);
            return "Detalle agregado exitosamente a la orden.";
        } else {
            return "No se pudo agregar el detalle: la orden de servicio no existe.";
        }
    }
// FUNCIONALIDAD 6 FINAL
}
