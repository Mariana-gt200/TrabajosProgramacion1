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
        Optional<cliente> clienteEncontrado = buscarCliente(cliente.getNumeroDocumento());
        if(clienteEncontrado.isEmpty()){
            listaClientes.add(cliente);
            return "El cliente fue registrado exitosamente";
        }else return "No se puede registrar, ya existe un cliente con esa informacion registrado anteriormente.";
    }
//FUNCIONALIDAD 1 FINAL

    // FUNCIONALIDAD 2 COMIENZO
    public Optional<Equipo> buscarEquipo(String codigoRegistro) {
        for (Equipo equipo : listaEquipos) {
            if (codigoRegistro.equals(equipo.getcodigoRegistro())) {
                return Optional.of(equipo);
            }
        }
        return Optional.empty();
    }

    public String registrarEquipo(Equipo equipo) {
        Optional<Equipo> equipoEncontrado = buscarEquipo(equipo.getCodigoRegistro());
        if (equipoEncontrado.isEmpty()) {
            listaEquipos.add(equipo);
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
    public String agregarDetalleOrden(String codigoUnico, Dispositivo dispositivo, Servicio servicio, String descripcionFalla) {
        Optional<OrdenServicio> ordenEncontrada = buscarOrdenServicio(codigoUnico);

        if (ordenEncontrada.isPresent()) {
            OrdenServicio orden = ordenEncontrada.get();

            DetalleServicio nuevoDetalle = new DetalleServicio(equipo, servicio, descripcionFalla);

            orden.getListaDetalles().add(nuevoDetalle);
            return "Detalle agregado exitosamente a la orden.";
        } else {
            return "No se pudo agregar el detalle: la orden de servicio no existe.";
        }
    }
// FUNCIONALIDAD 6 FINAL
}
