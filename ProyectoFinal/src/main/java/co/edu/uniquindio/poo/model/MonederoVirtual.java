
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
        revisarTransaccionAtipica(retiro, cliente);      // <-- ALERTA 1: antes de registrar

        monedero.setSaldo(monedero.getSaldo() - monto);
        registrarTransaccion(retiro);
        revisarSaldoBajo(cliente, monedero);             // <-- ALERTA 2: después de restar el saldo
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
        if (origen.getSaldo() < monto + comision) {
            throw new SaldoInsuficienteException("Saldo insuficiente (monto + comisión de $" + comision + ")");
        }

        Transferencia transferencia = new Transferencia(generarIdTransaccion(), LocalDate.now(), monto,
                EstadoTransaccion.COMPLETADA, idClienteOrigen, origen, destino, comision);
        revisarTransaccionAtipica(transferencia, cliente);   // <-- ALERTA 1: antes de registrar

        origen.setSaldo(origen.getSaldo() - monto - comision);
        destino.setSaldo(destino.getSaldo() + monto);
        registrarTransaccion(transferencia);
        revisarSaldoBajo(cliente, origen);                   // <-- ALERTA 2: monedero de origen
        actualizarRangoCliente(idClienteOrigen);
        return "Transferencia realizada exitosamente.";
    }

    

}