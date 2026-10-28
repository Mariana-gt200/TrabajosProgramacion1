
package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.Transaccion;
import java.util.List;
import java.util.Optional;
import java.util.Collections;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.UUID;

public class MonederoVirtual {
    private final List<Cliente> listaClientes;
    private final List<Monedero> listaMonederos;
    private final List<Transaccion> listaTransacciones;
    private final ServicioNotificacion servicioNotificacion;
    private final AnalizadorPatronesGastos analizadorPatronesGastos;
    private static final double SALDO_MINIMO = 10000;

    private final List<TransaccionProgramada> listaProgramadas = new ArrayList<>();
    private static final int COSTO_DESCUENTO_COMISION = 100;
    private static final int COSTO_MES_SIN_CARGOS = 500;
    private static final int COSTO_BONO_SALDO = 1000;
    private static final double VALOR_BONO = 50;

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

    //Obtener Cliente
    private Cliente obtenerCliente(String idCliente) throws ClienteNoEncontradoException {
        return buscarCliente(idCliente)
                .orElseThrow(() -> new ClienteNoEncontradoException("No existe el cliente con id " + idCliente));
    }

    public List<Cliente> obtenerClientes() {
        return Collections.unmodifiableList(listaClientes);
    }


    //Buscar cliente
    public Optional<Cliente> buscarCliente(String identificacion) {
        return listaClientes.stream()
                .filter(c -> c.getIdentificacion().equals(identificacion))
                .findFirst();
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

    //Calcular comisión según el rango del cliente
    private double calcularComision(RangoCliente rango, double monto) {
        double porcentaje;
        switch (rango) {
            case BRONCE:  porcentaje = 0.02;  break;
            case PLATA:   porcentaje = 0.015; break;
            case ORO:     porcentaje = 0.01;  break;
            default:      porcentaje = 0.005; break;
        }
        return monto * porcentaje;
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

    //Obtener monedero
    private Monedero obtenerMonedero(String idCliente, String idMonedero)
            throws ClienteNoEncontradoException, MonederoNoEncontradoException {
        obtenerCliente(idCliente);
        return buscarMonedero(idCliente, idMonedero)
                .orElseThrow(() -> new MonederoNoEncontradoException("No existe el monedero " + idMonedero + " para ese cliente"));
    }

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

    //Validaciones---------------------------------------------------------------------------------------
    //Validar monto
    private void validarMonto(double monto) throws MontoInvalidoException {
        if (monto <= 0) {
            throw new MontoInvalidoException("El monto debe ser mayor a cero.");
        }
    }

    //Validar Transaccion
    private String generarIdTransaccion() {
        return "TRX-" + UUID.randomUUID().toString().substring(0, 8);
    }

    //ALERTAS--------------------------------------------------------------------------------------
    //Revisar si una transacción es atípica
    private void revisarTransaccionAtipica(Transaccion transaccion, Cliente cliente) {
        List<Transaccion> historial = obtenerHistorialCliente(cliente.getIdentificacion()).stream()
                .filter(t -> t.nombreTipo().equals(transaccion.nombreTipo()))
                .filter(t -> t.getEstado() != EstadoTransaccion.REVERTIDA)
                .toList();
        if (analizadorPatronesGastos.detectarTransaccionAtipica(transaccion, historial)) {
            transaccion.setEstado(EstadoTransaccion.SOSPECHOSA);
            servicioNotificacion.enviarNotificacion(
                    "Alerta: se detectó una transacción atípica por $" + transaccion.getMonto(), cliente);
        }
    }
    //Revisar si el saldo quedó bajo
    private void revisarSaldoBajo(Cliente cliente, Monedero monedero) {
        if (monedero.getSaldo() < SALDO_MINIMO) {
            servicioNotificacion.enviarNotificacion(
                    "Alerta: saldo bajo en el monedero " + monedero.getIdMonedero(), cliente);
        }
    }
    //--------------------------------------------------------------------------------------------
    //Obtiene el monedero actual de la lista a partir de uno de referencia
    private Monedero monederoActual(Monedero referencia) throws MonederoNoEncontradoException {
        return buscarMonedero(referencia.getIdentificacionCliente(), referencia.getIdMonedero())
                .orElseThrow(() -> new MonederoNoEncontradoException(
                        "El monedero " + referencia.getIdMonedero() + " ya no existe"));
    }

    //Acciones de transacciones--------------------------------------------------------------------
    //Deposito
    public String depositar(String idCliente, String idMonedero, double monto)
            throws MontoInvalidoException, ClienteNoEncontradoException, MonederoNoEncontradoException {
        validarMonto(monto);
        Cliente cliente = obtenerCliente(idCliente);
        Monedero monedero = obtenerMonedero(idCliente, idMonedero);

        Deposito deposito = new Deposito(generarIdTransaccion(), monto, EstadoTransaccion.COMPLETADA, idCliente, monedero);
        revisarTransaccionAtipica(deposito, cliente);   // <-- ALERTA

        monedero.setSaldo(monedero.getSaldo() + monto);
        registrarTransaccion(deposito);
        actualizarRangoCliente(idCliente);
        return "Depósito realizado exitosamente.";
    }

    //Retiro
    public String retirar(String idCliente, String idMonedero, double monto)
            throws MontoInvalidoException, ClienteNoEncontradoException,
            MonederoNoEncontradoException, SaldoInsuficienteException {
        validarMonto(monto);
        Cliente cliente = obtenerCliente(idCliente);
        Monedero monedero = obtenerMonedero(idCliente, idMonedero);

        if (monedero.getSaldo() < monto) {
            throw new SaldoInsuficienteException("Saldo insuficiente para retirar $" + monto);
        }

        Retiro retiro = new Retiro(generarIdTransaccion(), LocalDate.now(), monto,
                EstadoTransaccion.COMPLETADA, idCliente, monedero);
        //ALERTA 1: antes de registrar
        revisarTransaccionAtipica(retiro, cliente);

        monedero.setSaldo(monedero.getSaldo() - monto);
        registrarTransaccion(retiro);
        //ALERTA 2: después de restar el saldo
        revisarSaldoBajo(cliente, monedero);
        actualizarRangoCliente(idCliente);
        return "Retiro realizado exitosamente.";
    }

    //Transferencia
    public String transferir(String idClienteOrigen, String idMonederoOrigen,
                             String idClienteDestino, String idMonederoDestino, double monto)
            throws MontoInvalidoException, ClienteNoEncontradoException, MonederoNoEncontradoException,
            SaldoInsuficienteException, TransferenciaInvalidaException {
        validarMonto(monto);
        if (idClienteOrigen.equals(idClienteDestino) && idMonederoOrigen.equals(idMonederoDestino)) {
            throw new TransferenciaInvalidaException("No se puede transferir al mismo monedero.");
        }
        Cliente cliente = obtenerCliente(idClienteOrigen);
        Monedero origen = obtenerMonedero(idClienteOrigen, idMonederoOrigen);
        Monedero destino = obtenerMonedero(idClienteDestino, idMonederoDestino);

        double comision = calcularComision(cliente.getRango(), monto);
        if (cliente.isDescuentoComision()) {
            comision = comision * 0.9;
        }
        if (origen.getSaldo() < monto + comision) {
            throw new SaldoInsuficienteException("Saldo insuficiente (monto + comisión de $" + comision + ")");
        }

        Transferencia transferencia = new Transferencia(generarIdTransaccion(), LocalDate.now(), monto,
                EstadoTransaccion.COMPLETADA, idClienteOrigen, origen, destino, comision);
        //ALERTA 1: antes de registrar
        revisarTransaccionAtipica(transferencia, cliente);

        origen.setSaldo(origen.getSaldo() - monto - comision);
        destino.setSaldo(destino.getSaldo() + monto);
        registrarTransaccion(transferencia);
        //ALERTA 2: monedero de origen
        revisarSaldoBajo(cliente, origen);
        actualizarRangoCliente(idClienteOrigen);
        return "Transferencia realizada exitosamente.";
    }

    //CONSULTAS------------------------------------------------------------------------------------
    //Consultar el saldo de un monedero
    public double consultarSaldo(String idCliente, String idMonedero)
            throws ClienteNoEncontradoException, MonederoNoEncontradoException {
        return obtenerMonedero(idCliente, idMonedero).getSaldo();
    }

    //Consultar el saldo sumado de todos los monederos del cliente
    public double consultarSaldoTotal(String idCliente) {
        return obtenerMonederos(idCliente).stream()
                .mapToDouble(m -> m.getSaldo())
                .sum();
    }

    //Consultar el historial (propias y transferencias recibidas), de la más reciente a la más antigua
    public List<Transaccion> consultarHistorial(String idCliente) {
        return listaTransacciones.stream()
                .filter(t -> t.getIdCliente().equals(idCliente)
                        || (t instanceof Transferencia
                        && ((Transferencia) t).getMonederoDestino()
                        .getIdentificacionCliente().equals(idCliente)))
                .sorted((a, b) -> b.getFecha().compareTo(a.getFecha()))
                .toList();
    }
    //--------------------------------------------------------------------------------------------

    //PUNTOS Y RANGOS------------------------------------------------------------------------------
    //Calcular los puntos acumulados (las transacciones revertidas no cuentan)
    public int calcularPuntos(String idCliente) {
        return listaTransacciones.stream()
                .filter(t -> t.getIdCliente().equals(idCliente))
                .filter(t -> t.getEstado() != EstadoTransaccion.REVERTIDA)
                .mapToInt(t -> t.calcularPuntosGenerados())
                .sum();
    }

    //Calcular el rango según los puntos
    public RangoCliente calcularRango(int puntos) {
        if (puntos <= 500) {
            return RangoCliente.BRONCE;
        } else if (puntos <= 1000) {
            return RangoCliente.PLATA;
        } else if (puntos <= 5000) {
            return RangoCliente.ORO;
        }
        return RangoCliente.PLATINO;
    }

    //Actualizar el rango del cliente con sus puntos actuales
    public void actualizarRangoCliente(String idCliente) throws ClienteNoEncontradoException {
        Cliente cliente = obtenerCliente(idCliente);
        cliente.setRango(calcularRango(calcularPuntos(idCliente)));
    }
    //--------------------------------------------------------------------------------------------

    //REVERSIÓN------------------------------------------------------------------------------------
    public String revertirTransaccion(String idTransaccion)
            throws TransaccionNoEncontradaException, TransaccionNoRevertibleException,
            MonederoNoEncontradoException, SaldoInsuficienteException, ClienteNoEncontradoException {
        Transaccion transaccion = buscarTransaccion(idTransaccion)
                .orElseThrow(() -> new TransaccionNoEncontradaException(
                        "No existe la transacción " + idTransaccion));

        if (transaccion.getEstado() == EstadoTransaccion.REVERTIDA) {
            throw new TransaccionNoRevertibleException("La transacción ya fue revertida.");
        }

        if (transaccion instanceof Deposito) {
            Monedero destino = monederoActual(((Deposito) transaccion).getMonederoDestino());
            if (destino.getSaldo() < transaccion.getMonto()) {
                throw new SaldoInsuficienteException(
                        "No se puede revertir el depósito: el monedero ya no tiene saldo suficiente.");
            }
            destino.setSaldo(destino.getSaldo() - transaccion.getMonto());

        } else if (transaccion instanceof Retiro) {
            Monedero origen = monederoActual(((Retiro) transaccion).getMonederoOrigen());
            origen.setSaldo(origen.getSaldo() + transaccion.getMonto());

        } else if (transaccion instanceof Transferencia) {
            Transferencia transferencia = (Transferencia) transaccion;
            Monedero origen = monederoActual(transferencia.getMonederoOrigen());
            Monedero destino = monederoActual(transferencia.getMonederoDestino());
            if (destino.getSaldo() < transferencia.getMonto()) {
                throw new SaldoInsuficienteException(
                        "No se puede revertir la transferencia: el monedero destino ya no tiene ese saldo.");
            }
            destino.setSaldo(destino.getSaldo() - transferencia.getMonto());
            origen.setSaldo(origen.getSaldo() + transferencia.getMonto() + transferencia.getComision());
        }

        transaccion.setEstado(EstadoTransaccion.REVERTIDA);
        actualizarRangoCliente(transaccion.getIdCliente());
        return "La transacción " + idTransaccion + " fue revertida exitosamente.";
    }
    //--------------------------------------------------------------------------------------------

    //CANJE DE PUNTOS------------------------------------------------------------------------------
    //Puntos acumulados menos los ya canjeados
    public int consultarPuntosDisponibles(String idCliente) throws ClienteNoEncontradoException {
        Cliente cliente = obtenerCliente(idCliente);
        int disponibles = calcularPuntos(idCliente) - cliente.getPuntosCanjeados();
        if (disponibles < 0) {
            return 0;
        }
        return disponibles;
    }

    //Descuenta los puntos de un canje, validando que alcancen
    private void descontarPuntos(Cliente cliente, int costo)
            throws ClienteNoEncontradoException, CanjeInvalidoException {
        if (consultarPuntosDisponibles(cliente.getIdentificacion()) < costo) {
            throw new CanjeInvalidoException("Puntos insuficientes: se necesitan " + costo + " puntos.");
        }
        cliente.setPuntosCanjeados(cliente.getPuntosCanjeados() + costo);
    }

    //100 puntos: 10% menos en la comisión de transferencias
    public String canjearDescuentoComision(String idCliente)
            throws ClienteNoEncontradoException, CanjeInvalidoException {
        Cliente cliente = obtenerCliente(idCliente);
        if (cliente.isDescuentoComision()) {
            throw new CanjeInvalidoException("El cliente ya tiene activo el descuento en la comisión.");
        }
        descontarPuntos(cliente, COSTO_DESCUENTO_COMISION);
        cliente.setDescuentoComision(true);
        return "Beneficio activado: 10% de descuento en la comisión de transferencias.";
    }

    //500 puntos: un mes sin cargos por retiros
    public String canjearMesSinCargos(String idCliente)
            throws ClienteNoEncontradoException, CanjeInvalidoException {
        Cliente cliente = obtenerCliente(idCliente);
        if (cliente.getSinCargosRetirosHasta() != null
                && cliente.getSinCargosRetirosHasta().isAfter(LocalDate.now())) {
            throw new CanjeInvalidoException("El cliente ya tiene activo un mes sin cargos por retiros.");
        }
        descontarPuntos(cliente, COSTO_MES_SIN_CARGOS);
        cliente.setSinCargosRetirosHasta(LocalDate.now().plusMonths(1));
        return "Beneficio activado: sin cargos por retiros hasta " + cliente.getSinCargosRetirosHasta() + ".";
    }

    //1000 puntos: bono de 50 unidades en un monedero
    public String canjearBonoSaldo(String idCliente, String idMonedero)
            throws ClienteNoEncontradoException, MonederoNoEncontradoException, CanjeInvalidoException {
        Cliente cliente = obtenerCliente(idCliente);
        Monedero monedero = obtenerMonedero(idCliente, idMonedero);
        descontarPuntos(cliente, COSTO_BONO_SALDO);

        monedero.setSaldo(monedero.getSaldo() + VALOR_BONO);
        Deposito bono = new Deposito(generarIdTransaccion(), VALOR_BONO,
                EstadoTransaccion.COMPLETADA, idCliente, monedero);
        registrarTransaccion(bono);
        return "Bono de $" + VALOR_BONO + " acreditado en el monedero " + idMonedero + ".";
    }

    //Historial de las transacciones que generaron puntos
    public List<Transaccion> consultarHistorialPuntos(String idCliente) {
        return listaTransacciones.stream()
                .filter(t -> t.getIdCliente().equals(idCliente))
                .filter(t -> t.getEstado() != EstadoTransaccion.REVERTIDA)
                .filter(t -> t.calcularPuntosGenerados() > 0)
                .sorted((a, b) -> b.getFecha().compareTo(a.getFecha()))
                .toList();
    }
    //--------------------------------------------------------------------------------------------

    //TRANSACCIONES PROGRAMADAS--------------------------------------------------------------------
    //Metodo auxiliar para generar un id único para cada programación
    private String generarIdProgramacion() {
        return "PRG-" + UUID.randomUUID().toString().substring(0, 8);
    }

    //Programar una transacción recurrente
    public String programarTransaccion(String idCliente, String idMonedero, String idClienteDestino,
                                       String idMonederoDestino, LocalDate fechaInicio, FrecuenciaProgramada frecuencia,
                                       double monto, TipoTransaccion tipo)
            throws MontoInvalidoException, ClienteNoEncontradoException, MonederoNoEncontradoException,
            ProgramacionInvalidaException, TransferenciaInvalidaException {
        validarMonto(monto);
        if (fechaInicio == null || fechaInicio.isBefore(LocalDate.now())) {
            throw new ProgramacionInvalidaException("La fecha de inicio es obligatoria y no puede estar en el pasado.");
        }

        Monedero origen = null;
        Monedero destino = null;
        if (tipo == TipoTransaccion.DEPOSITO) {
            destino = obtenerMonedero(idCliente, idMonedero);
        } else if (tipo == TipoTransaccion.RETIRO) {
            origen = obtenerMonedero(idCliente, idMonedero);
        } else {
            if (idClienteDestino == null || idMonederoDestino == null) {
                throw new ProgramacionInvalidaException("La transferencia programada necesita un monedero destino.");
            }
            origen = obtenerMonedero(idCliente, idMonedero);
            destino = obtenerMonedero(idClienteDestino, idMonederoDestino);
        }

        TransaccionProgramada programada = new TransaccionProgramada(generarIdProgramacion(), idCliente,
                fechaInicio, frecuencia, monto, tipo, origen, destino);
        listaProgramadas.add(programada);
        return "Transacción programada exitosamente. Id: " + programada.getIdProgramacion();
    }
    //Buscar una programada
    public Optional<TransaccionProgramada> buscarProgramada(String idProgramacion) {
        return listaProgramadas.stream()
                .filter(p -> p.getIdProgramacion().equals(idProgramacion))
                .findFirst();
    }

    //Cancelar una programada (queda inactiva, no se borra)
    public String cancelarProgramada(String idProgramacion) throws TransaccionNoEncontradaException {
        TransaccionProgramada programada = buscarProgramada(idProgramacion)
                .orElseThrow(() -> new TransaccionNoEncontradaException(
                        "No existe la programación " + idProgramacion));
        programada.setActiva(false);
        return "La transacción programada fue cancelada.";
    }

    //Consultar las programadas activas de un cliente, ordenadas por la fecha de ejecución
    public List<TransaccionProgramada> consultarProgramadas(String idCliente) {
        return listaProgramadas.stream()
                .filter(p -> p.getIdCliente().equals(idCliente))
                .filter(p -> p.isActiva())
                .sorted()
                .toList();
    }

    //Ejecutar las programadas que ya vencieron, de la más antigua a la más reciente
    public List<String> ejecutarProgramadas(LocalDate fechaCorte) {
        List<String> resultados = new ArrayList<>();

        List<TransaccionProgramada> pendientes = listaProgramadas.stream()
                .filter(p -> p.debeEjecutarse(fechaCorte))
                .sorted()
                .toList();

        for (TransaccionProgramada p : pendientes) {
            try {
                switch (p.getTipo()) {
                    case DEPOSITO:
                        depositar(p.getIdCliente(), p.getMonederoDestino().getIdMonedero(), p.getMonto());
                        break;
                    case RETIRO:
                        retirar(p.getIdCliente(), p.getMonederoOrigen().getIdMonedero(), p.getMonto());
                        break;
                    case TRANSFERENCIA:
                        transferir(p.getIdCliente(), p.getMonederoOrigen().getIdMonedero(),
                                p.getMonederoDestino().getIdentificacionCliente(),
                                p.getMonederoDestino().getIdMonedero(), p.getMonto());
                        break;
                }
                p.avanzarProximaEjecucion();
                resultados.add("Ejecutada " + p.getIdProgramacion());
            } catch (Exception e) {
                resultados.add("Falló " + p.getIdProgramacion() + ": " + e.getMessage());
                buscarCliente(p.getIdCliente()).ifPresent(c -> servicioNotificacion.enviarNotificacion(
                        "No se pudo ejecutar tu transacción programada " + p.getIdProgramacion()
                                + ": " + e.getMessage(), c));
            }
        }
        return resultados;
    }

    //Recordar con un día de anticipación las programadas que se ejecutan mañana
    public int enviarRecordatoriosProgramadas(LocalDate hoy) {
        List<TransaccionProgramada> proximas = listaProgramadas.stream()
                .filter(p -> p.isActiva())
                .filter(p -> p.getFechaProximaEjecucion().equals(hoy.plusDays(1)))
                .toList();

        for (TransaccionProgramada p : proximas) {
            buscarCliente(p.getIdCliente()).ifPresent(c -> servicioNotificacion.enviarNotificacion(
                    "Recordatorio: mañana se ejecuta tu transacción programada " + p.getIdProgramacion()
                            + " por $" + p.getMonto(), c));
        }
        return proximas.size();
    }
//--------------------------------------------------------------------------------------------

}