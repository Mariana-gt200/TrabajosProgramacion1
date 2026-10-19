package co.edu.uniquindio.poo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class Empresa {
    private final String nombre;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Tecnico> listaTecnicos;
    private ArrayList<Repuesto> listaRepuestos;
    private ArrayList<OrdenServicio> listaOrdenesServicio;

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

    // ---------- AUXILIARES ----------
    private boolean vacio(String texto) {
        return texto == null || texto.isEmpty();
    }

    // Busca un detalle a partir del código de la orden y el código del detalle
    private Optional<DetalleServicio> buscarDetalle(String codigoOrden, String codigoDetalle) {
        Optional<OrdenServicio> orden = buscarOrdenServicio(codigoOrden);
        if (orden.isEmpty()) {
            return Optional.empty();
        }
        return orden.get().buscarDetalle(codigoDetalle);
    }

    // FUNCIONALIDAD 1 COMIENZO
    public Optional<Cliente> buscarCliente(String numeroDocumento) {
        for (Cliente cliente : listaClientes) {
            if (numeroDocumento.equals(cliente.getNumeroDocumento())) {
                return Optional.of(cliente);
            }
        }
        return Optional.empty();
    }

    public String registrarCliente(Cliente cliente) {
        if (vacio(cliente.getNumeroDocumento()) || vacio(cliente.getNombreCompleto())
                || vacio(cliente.getNumeroTelefono()) || vacio(cliente.getCorreoElectronico())
                || vacio(cliente.getDireccionResidencia())) {
            return "Todos los datos del cliente son obligatorios.";
        }
        if (!cliente.getCorreoElectronico().contains("@")) {
            return "El correo electrónico no es válido.";
        }
        Optional<Cliente> clienteEncontrado = buscarCliente(cliente.getNumeroDocumento());
        if (clienteEncontrado.isEmpty()) {
            listaClientes.add(cliente);
            return "El cliente fue registrado exitosamente";
        } else return "No se puede registrar, ya existe un cliente con esa informacion registrado anteriormente.";
    }
    // FUNCIONALIDAD 1 FINAL

    // FUNCIONALIDAD 2 COMIENZO
    // El código de registro es único en todo el sistema: se busca en los equipos de todos los clientes
    public Optional<Dispositivo> buscarEquipo(String codigoRegistro) {
        for (Cliente cliente : listaClientes) {
            Optional<Dispositivo> equipo = cliente.buscarEquipo(codigoRegistro);
            if (equipo.isPresent()) {
                return equipo;
            }
        }
        return Optional.empty();
    }

    // Valida los atributos según la categoría del equipo
    private String validarEquipo(Dispositivo equipo) {
        if (vacio(equipo.getCodigoRegistro()) || vacio(equipo.getNumeroSerie()) || vacio(equipo.getMarca())
                || vacio(equipo.getModelo()) || vacio(equipo.getDescripcionEstadoFisico())) {
            return "Los datos básicos del equipo son obligatorios.";
        }
        if (equipo.getAnioFabricacion() <= 0 || equipo.getAnioFabricacion() > LocalDate.now().getYear()) {
            return "El año de fabricación no es válido.";
        }
        if (equipo instanceof ComputadorPortatil) {
            ComputadorPortatil portatil = (ComputadorPortatil) equipo;
            if (portatil.getTamanoPantallaPulgadas() <= 0 || portatil.getMemoriaRAM() <= 0
                    || portatil.getCapacidadAlmacenamiento() <= 0) {
                return "Pantalla, RAM y almacenamiento del portátil deben ser mayores que cero.";
            }
        } else if (equipo instanceof ComputadorEscritorio) {
            ComputadorEscritorio escritorio = (ComputadorEscritorio) equipo;
            if (vacio(escritorio.getTipoProcesador()) || escritorio.getMemoriaRAM() <= 0
                    || escritorio.getCapacidadAlmacenamiento() <= 0) {
                return "Procesador, RAM y almacenamiento del computador de escritorio son obligatorios.";
            }
        } else if (equipo instanceof TelefonoInteligente) {
            TelefonoInteligente telefono = (TelefonoInteligente) equipo;
            if (vacio(telefono.getSistemaOperativo()) || telefono.getCapacidadAlmacenamiento() <= 0
                    || telefono.getCapacidadBateriaMah() <= 0) {
                return "Sistema operativo, almacenamiento y batería del teléfono son obligatorios.";
            }
        }
        return null;
    }

    public String registrarEquipo(String documentoCliente, Dispositivo equipo) {
        Optional<Cliente> cliente = buscarCliente(documentoCliente);
        if (cliente.isEmpty()) {
            return "No existe un cliente con ese documento.";
        }
        String error = validarEquipo(equipo);
        if (error != null) {
            return error;
        }
        if (buscarEquipo(equipo.getCodigoRegistro()).isPresent()) {
            return "No se puede registrar, ya existe un equipo con ese código";
        }
        for (Cliente c : listaClientes) {
            for (Dispositivo d : c.getEquipos()) {
                if (d.getNumeroSerie().equalsIgnoreCase(equipo.getNumeroSerie())) {
                    return "No se puede registrar, ya existe un equipo con ese número de serie";
                }
            }
        }
        cliente.get().agregarEquipo(equipo);
        return "El equipo fue registrado exitosamente";
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
        if (vacio(tecnico.getCodigo()) || vacio(tecnico.getNombre()) || vacio(tecnico.getDocumentoIdentificacion())) {
            return "Código, nombre y documento del técnico son obligatorios.";
        }
        if (!Tecnico.esEspecialidadValida(tecnico.getEspecialidad())) {
            return "La especialidad no es válida.";
        }
        for (Tecnico t : listaTecnicos) {
            if (t.getCodigo().equalsIgnoreCase(tecnico.getCodigo())) {
                return "No se puede registrar, ya existe un técnico con ese código";
            }
        }
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
        if (vacio(repuesto.getCodigo()) || vacio(repuesto.getNombre()) || vacio(repuesto.getDescripcion())
                || vacio(repuesto.getCategoriaCompatibilidad())) {
            return "Código, nombre, descripción y categoría del repuesto son obligatorios.";
        }
        if (repuesto.getPrecioUnitario() <= 0) {
            return "El precio unitario debe ser mayor que cero.";
        }
        if (repuesto.getCantidadDisponible() < 0) {
            return "Las existencias iniciales no pueden ser negativas.";
        }
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

    // Crea una orden abierta para un cliente que ya existe
    public String crearOrdenServicio(String codigoUnico, String documentoCliente, String observacionesGenerales) {
        if (vacio(codigoUnico)) {
            return "El código de la orden es obligatorio.";
        }
        Optional<Cliente> cliente = buscarCliente(documentoCliente);
        if (cliente.isEmpty()) {
            return "No se puede crear la orden: el cliente no existe.";
        }
        if (buscarOrdenServicio(codigoUnico).isPresent()) {
            return "No se puede registrar, ya existe una orden con ese código";
        }
        listaOrdenesServicio.add(new OrdenServicio(codigoUnico, cliente.get(), observacionesGenerales));
        return "La orden de servicio fue creada exitosamente";
    }
    // FUNCIONALIDAD 5 FINAL

    // FUNCIONALIDAD 6 COMIENZO
    public String agregarDetalleOrden(String codigoOrden, String codigoDetalle, String codigoEquipo,
                                      Servicio servicio, String descripcionFalla) {
        Optional<OrdenServicio> ordenEncontrada = buscarOrdenServicio(codigoOrden);
        if (ordenEncontrada.isEmpty()) {
            return "No se pudo agregar el detalle: la orden de servicio no existe.";
        }
        OrdenServicio orden = ordenEncontrada.get();
        if (orden.getEstado() != EstadoOrden.ABIERTA && orden.getEstado() != EstadoOrden.EN_PROCESO) {
            return "No se pueden agregar detalles a una orden " + orden.getEstado() + ".";
        }
        if (vacio(codigoDetalle) || vacio(descripcionFalla)) {
            return "El código del detalle y la falla reportada son obligatorios.";
        }
        if (orden.buscarDetalle(codigoDetalle).isPresent()) {
            return "Ya existe un detalle con ese código dentro de la orden.";
        }
        Optional<Dispositivo> equipo = orden.getCliente().buscarEquipo(codigoEquipo);
        if (equipo.isEmpty()) {
            return "El equipo no está registrado a nombre del cliente de la orden.";
        }
        orden.agregarDetalle(new DetalleServicio(codigoDetalle, equipo.get(), servicio, descripcionFalla));
        return "Detalle agregado exitosamente a la orden.";
    }
    // FUNCIONALIDAD 6 FINAL

    // FUNCIONALIDAD 7 COMIENZO
    public String asignarTecnico(String codigoOrden, String codigoDetalle, String documentoTecnico) {
        Optional<DetalleServicio> detalle = buscarDetalle(codigoOrden, codigoDetalle);
        if (detalle.isEmpty()) {
            return "No se encontró el detalle (revise el código de la orden y del detalle).";
        }
        Optional<Tecnico> tecnico = buscarTecnico(documentoTecnico);
        if (tecnico.isEmpty()) {
            return "No existe un técnico con ese documento.";
        }
        return detalle.get().asignarTecnico(tecnico.get());
    }
    // FUNCIONALIDAD 7 FINAL

    // FUNCIONALIDAD 8 COMIENZO
    public String registrarHorasTrabajo(String codigoOrden, String codigoDetalle, double horas) {
        Optional<DetalleServicio> detalle = buscarDetalle(codigoOrden, codigoDetalle);
        if (detalle.isEmpty()) {
            return "No se encontró el detalle (revise el código de la orden y del detalle).";
        }
        return detalle.get().registrarHorasTrabajo(horas);
    }

    public String registrarRepuestoUtilizado(String codigoOrden, String codigoDetalle, String codigoRepuesto, int cantidad) {
        Optional<DetalleServicio> detalle = buscarDetalle(codigoOrden, codigoDetalle);
        if (detalle.isEmpty()) {
            return "No se encontró el detalle (revise el código de la orden y del detalle).";
        }
        Optional<Repuesto> repuesto = buscarRepuesto(codigoRepuesto);
        if (repuesto.isEmpty()) {
            return "No existe un repuesto con ese código.";
        }
        return detalle.get().agregarRepuesto(repuesto.get(), cantidad);
    }
    // FUNCIONALIDAD 8 FINAL

    // FUNCIONALIDAD 9 COMIENZO
    public String calcularValorDetalle(String codigoOrden, String codigoDetalle) {
        Optional<DetalleServicio> detalle = buscarDetalle(codigoOrden, codigoDetalle);
        if (detalle.isEmpty()) {
            return "No se encontró el detalle (revise el código de la orden y del detalle).";
        }
        return "Valor del detalle " + codigoDetalle + ": $" + detalle.get().calcularCostoDetalle();
    }
    // FUNCIONALIDAD 9 FINAL

    // FUNCIONALIDAD 10 COMIENZO
    public String calcularValorTotalOrden(String codigoOrden) {
        Optional<OrdenServicio> orden = buscarOrdenServicio(codigoOrden);
        if (orden.isEmpty()) {
            return "La orden no existe.";
        }
        return "Valor total de la orden " + codigoOrden + ": $" + orden.get().calcularCostoTotal();
    }
    // FUNCIONALIDAD 10 FINAL

    // FUNCIONALIDAD 11 COMIENZO
    public String actualizarEstadoDetalle(String codigoOrden, String codigoDetalle, EstadoOrden nuevoEstado) {
        Optional<OrdenServicio> orden = buscarOrdenServicio(codigoOrden);
        if (orden.isEmpty()) {
            return "La orden no existe.";
        }
        Optional<DetalleServicio> detalle = orden.get().buscarDetalle(codigoDetalle);
        if (detalle.isEmpty()) {
            return "El detalle no existe en esa orden.";
        }
        detalle.get().setEstado(nuevoEstado);
        // Si la orden estaba abierta y empieza el trabajo, la orden pasa a "en proceso"
        if (orden.get().getEstado() == EstadoOrden.ABIERTA && nuevoEstado == EstadoOrden.EN_PROCESO) {
            orden.get().cambiarEstado(EstadoOrden.EN_PROCESO);
        }
        return "Estado del detalle actualizado a " + nuevoEstado + ".";
    }
    // FUNCIONALIDAD 11 FINAL

    // FUNCIONALIDAD 12 COMIENZO
    public String finalizarOrden(String codigoOrden) {
        Optional<OrdenServicio> orden = buscarOrdenServicio(codigoOrden);
        if (orden.isEmpty()) {
            return "La orden no existe.";
        }
        if (!orden.get().todosLosDetallesFinalizados()) {
            return "No se puede finalizar: todos los detalles deben estar finalizados (y la orden debe tener detalles).";
        }
        orden.get().cambiarEstado(EstadoOrden.FINALIZADA);
        return "La orden fue finalizada exitosamente.";
    }
    // FUNCIONALIDAD 12 FINAL

    // FUNCIONALIDAD 13 COMIENZO
    public String consultarOrdenesCliente(String documentoCliente) {
        Optional<Cliente> cliente = buscarCliente(documentoCliente);
        if (cliente.isEmpty()) {
            return "No existe un cliente con ese documento.";
        }
        String resultado = "ÓRDENES DE " + cliente.get().getNombreCompleto() + "\n";
        boolean hayOrdenes = false;
        for (OrdenServicio orden : listaOrdenesServicio) {
            if (orden.getCliente() == cliente.get()) {
                hayOrdenes = true;
                resultado += "\n" + orden + " | Total: $" + orden.calcularCostoTotal() + "\n";
                for (DetalleServicio detalle : orden.getListaDetalles()) {
                    resultado += "   " + detalle + " | Valor: $" + detalle.calcularCostoDetalle() + "\n";
                }
            }
        }
        if (!hayOrdenes) {
            return "El cliente no tiene órdenes registradas.";
        }
        return resultado;
    }
    // FUNCIONALIDAD 13 FINAL

    // FUNCIONALIDAD 14 COMIENZO
    public String consultarTrabajosTecnico(String documentoTecnico) {
        Optional<Tecnico> tecnico = buscarTecnico(documentoTecnico);
        if (tecnico.isEmpty()) {
            return "No existe un técnico con ese documento.";
        }
        String abiertas = "";
        String enProceso = "";
        String finalizadas = "";
        String entregadas = "";
        String canceladas = "";
        for (OrdenServicio orden : listaOrdenesServicio) {
            for (DetalleServicio detalle : orden.getListaDetalles()) {
                if (detalle.getTecnicoResponsable() == tecnico.get()) {
                    String linea = "   Orden " + orden.getCodigoUnico() + " - " + detalle + "\n";
                    if (detalle.getEstado() == EstadoOrden.ABIERTA) {
                        abiertas += linea;
                    } else if (detalle.getEstado() == EstadoOrden.EN_PROCESO) {
                        enProceso += linea;
                    } else if (detalle.getEstado() == EstadoOrden.FINALIZADA) {
                        finalizadas += linea;
                    } else if (detalle.getEstado() == EstadoOrden.ENTREGADA) {
                        entregadas += linea;
                    } else {
                        canceladas += linea;
                    }
                }
            }
        }
        String resultado = "";
        if (!abiertas.isEmpty()) {
            resultado += "\nABIERTA:\n" + abiertas;
        }
        if (!enProceso.isEmpty()) {
            resultado += "\nEN PROCESO:\n" + enProceso;
        }
        if (!finalizadas.isEmpty()) {
            resultado += "\nFINALIZADA:\n" + finalizadas;
        }
        if (!entregadas.isEmpty()) {
            resultado += "\nENTREGADA:\n" + entregadas;
        }
        if (!canceladas.isEmpty()) {
            resultado += "\nCANCELADA:\n" + canceladas;
        }
        if (resultado.isEmpty()) {
            return "El técnico no tiene detalles asignados.";
        }
        return "TRABAJOS DE " + tecnico.get().getNombre() + "\n" + resultado;
    }
    // FUNCIONALIDAD 14 FINAL

    // FUNCIONALIDAD 15 COMIENZO
    public String consultarEquiposPendientes() {
        String resultado = "";
        for (Cliente cliente : listaClientes) {
            for (Dispositivo equipo : cliente.getEquipos()) {
                boolean pendiente = false;
                for (OrdenServicio orden : listaOrdenesServicio) {
                    for (DetalleServicio detalle : orden.getListaDetalles()) {
                        if (detalle.getDispositivo() == equipo && detalle.estaPendiente()) {
                            pendiente = true;
                        }
                    }
                }
                if (pendiente) {
                    resultado += equipo + " - Cliente: " + cliente.getNombreCompleto() + "\n";
                }
            }
        }
        if (resultado.isEmpty()) {
            return "No hay equipos con intervenciones pendientes.";
        }
        return "EQUIPOS CON INTERVENCIONES PENDIENTES\n\n" + resultado;
    }
    // FUNCIONALIDAD 15 FINAL

    // FUNCIONALIDAD 16 COMIENZO
    // Cuenta los detalles no cancelados: cantidad por tipo de servicio y total por categoría de equipo
    public String generarReporteServicios() {
        int diagnosticos = 0;
        int mantenimientos = 0;
        int reparaciones = 0;
        double totalPortatiles = 0;
        double totalEscritorios = 0;
        double totalTelefonos = 0;
        for (OrdenServicio orden : listaOrdenesServicio) {
            for (DetalleServicio detalle : orden.getListaDetalles()) {
                if (detalle.getEstado() != EstadoOrden.CANCELADA) {
                    if (detalle.getServicio() instanceof Diagnostico) {
                        diagnosticos++;
                    } else if (detalle.getServicio() instanceof MantenimientoPreventivo) {
                        mantenimientos++;
                    } else if (detalle.getServicio() instanceof ReparacionCorrectiva) {
                        reparaciones++;
                    }
                    double valor = detalle.calcularCostoDetalle();
                    if (detalle.getDispositivo() instanceof ComputadorPortatil) {
                        totalPortatiles += valor;
                    } else if (detalle.getDispositivo() instanceof ComputadorEscritorio) {
                        totalEscritorios += valor;
                    } else if (detalle.getDispositivo() instanceof TelefonoInteligente) {
                        totalTelefonos += valor;
                    }
                }
            }
        }
        return "REPORTE DE SERVICIOS\n\nCantidad por tipo de servicio:\n"
                + "   Diagnóstico: " + diagnosticos + "\n"
                + "   Mantenimiento preventivo: " + mantenimientos + "\n"
                + "   Reparación correctiva: " + reparaciones + "\n\n"
                + "Total facturado por categoría de equipo:\n"
                + "   " + Dispositivo.CATEGORIA_PORTATIL + ": $" + totalPortatiles + "\n"
                + "   " + Dispositivo.CATEGORIA_ESCRITORIO + ": $" + totalEscritorios + "\n"
                + "   " + Dispositivo.CATEGORIA_TELEFONO + ": $" + totalTelefonos + "\n";
    }
    // FUNCIONALIDAD 16 FINAL

    // FUNCIONALIDAD 17 COMIENZO
    public String generarReporteTecnicos() {
        if (listaTecnicos.isEmpty()) {
            return "No hay técnicos registrados.";
        }
        String resultado = "REPORTE DE TÉCNICOS\n\n";
        for (Tecnico tecnico : listaTecnicos) {
            int finalizadas = 0;
            double valorAtendido = 0;
            for (OrdenServicio orden : listaOrdenesServicio) {
                for (DetalleServicio detalle : orden.getListaDetalles()) {
                    if (detalle.getTecnicoResponsable() == tecnico && detalle.getEstado() == EstadoOrden.FINALIZADA) {
                        finalizadas++;
                        valorAtendido += detalle.calcularCostoDetalle();
                    }
                }
            }
            resultado += tecnico.getNombre() + " [" + tecnico.getCodigo() + "] - Finalizadas: " + finalizadas
                    + " - Valor atendido: $" + valorAtendido + "\n";
        }
        return resultado;
    }
    // FUNCIONALIDAD 17 FINAL

    // FUNCIONALIDAD 18 COMIENZO
    public String consultarInventarioCritico() {
        String resultado = "";
        for (Repuesto repuesto : listaRepuestos) {
            if (repuesto.esCritico()) {
                resultado += repuesto + "\n";
            }
        }
        if (resultado.isEmpty()) {
            return "No hay repuestos con existencias inferiores a 5 unidades.";
        }
        return "INVENTARIO CRÍTICO (menos de 5 unidades)\n\n" + resultado;
    }
    // FUNCIONALIDAD 18 FINAL
}
