package co.edu.uniquindio.poo.parcial_3.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    //Empleados -------------------------------------------------------------------------------
    //Registrar empleados
    public void registrarEmpleado(Empleado empleado) {
        if (empleado == null) {
            throw new IllegalArgumentException("El empleado no puede ser nulo.");
        }
        if (buscarEmpleado(empleado.getCodigo()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un empleado con el código " + empleado.getCodigo());
        }
        empleados.add(empleado);
    }

    //Buscar empleados
    public Optional<Empleado> buscarEmpleado(String codigo) {
        for (Empleado empleado : empleados) {
            if (empleado.getCodigo().equals(codigo)) {
                return Optional.of(empleado);
            }
        }
        return Optional.empty();
    }

    //Pedidos -------------------------------------------------------------------------------
    public Optional<Pedido> buscarPedido(String codigo) {
        for (Pedido pedido : pedidos) {
            if (pedido.getCodigo().equals(codigo)) {
                return Optional.of(pedido);
            }
        }
        return Optional.empty();
    }

    // Busca el pedido o lanza IllegalArgumentException si no existe
    private Pedido obtenerPedido(String codigo) {
        Optional<Pedido> pedido = buscarPedido(codigo);
        if (pedido.isEmpty()) {
            throw new IllegalArgumentException("No existe un pedido con el código " + codigo);
        }
        return pedido.get();
    }

    // FUNCIONALIDADES ------------------------------------------------------------------------
    //1: registrar un pedido
    @Override
    public Pedido registrarPedido(String codigo, String nombreCliente, TipoPan tipoPan, int cantidad) {
        if (codigo == null || codigo.isEmpty()) {
            throw new IllegalArgumentException("El código del pedido es obligatorio.");
        }
        if (nombreCliente == null || nombreCliente.isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente es obligatorio.");
        }
        if (tipoPan == null) {
            throw new IllegalArgumentException("El tipo de pan es obligatorio.");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad de panes debe ser mayor que cero.");
        }
        // El codigo debe ser unico en la lista de pedidos
        if (buscarPedido(codigo).isPresent()) {
            throw new IllegalArgumentException("Ya existe un pedido con el código " + codigo);
        }
        Pedido pedido = new Pedido(codigo, nombreCliente, tipoPan, cantidad);
        pedidos.add(pedido);
        return pedido;
    }

    //2: asignar un pedido pendiente al panadero que lo haga en menos tiempo
    @Override
    public void asignarPedido(String codigoPedido) {
        Pedido pedido = obtenerPedido(codigoPedido);
        if (pedido.getEstado() != EstadoPedido.PENDIENTE) {
            throw new IllegalStateException("El pedido " + codigoPedido + " no está pendiente (estado actual: "
                    + pedido.getEstado() + ").");
        }
        // Primero se elige al panadero
        Panadero elegido = null;
        int minutosElegido = 0;
        for (Empleado empleado : empleados) {
            if (empleado instanceof Panadero) {
                Panadero panadero = (Panadero) empleado;
                if (panadero.puedeAtender(pedido)) {
                    int minutos = panadero.calcularMinutos(pedido.getTipoPan(), pedido.getCantidadUnidades());
                    // "<" estricto: si hay empate se queda el que aparece primero en la lista
                    if (elegido == null || minutos < minutosElegido) {
                        elegido = panadero;
                        minutosElegido = minutos;
                    }
                }
            }
        }
        if (elegido == null) {
            throw new IllegalStateException("Ningún panadero tiene tiempo suficiente para atender el pedido "
                    + codigoPedido + ".");
        }

        // Solo cuando hay un panadero se modifica el pedido y el empleado
        pedido.setCodigoEmpleadoAsignado(elegido.getCodigo());
        pedido.setMinutosReservados(minutosElegido);
        elegido.setMinutosDisponibles(elegido.getMinutosDisponibles() - minutosElegido);
        pedido.setEstado(EstadoPedido.ASIGNADO);
    }

    //3: cancelar un pedido
    @Override
    public void cancelarPedido(String codigoPedido) {
        Pedido pedido = obtenerPedido(codigoPedido);
        if (pedido.getEstado() == EstadoPedido.CANCELADO) {
            throw new IllegalStateException("El pedido " + codigoPedido + " ya está cancelado.");
        }
        if (pedido.getEstado() == EstadoPedido.ASIGNADO) {
            // Se devuelven al empleado exactamente los minutos que se reservaron
            Optional<Empleado> empleado = buscarEmpleado(pedido.getCodigoEmpleadoAsignado());
            if (empleado.isPresent()) {
                empleado.get().setMinutosDisponibles(
                        empleado.get().getMinutosDisponibles() + pedido.getMinutosReservados());
            }
        }
        pedido.setEstado(EstadoPedido.CANCELADO);
    }

    // Consulta cuánto tardaría cada panadero
    public List<String> estimarTiempos(String codigoPedido) {
        Pedido pedido = obtenerPedido(codigoPedido);
        List<String> estimaciones = new ArrayList<>();
        for (Empleado empleado : empleados) {
            if (empleado instanceof Panadero) {
                Panadero panadero = (Panadero) empleado;
                int minutos = panadero.calcularMinutos(pedido.getTipoPan(), pedido.getCantidadUnidades());
                String veredicto = "no tiene tiempo suficiente";
                if (panadero.puedeAtender(pedido)) {
                    veredicto = "puede atenderlo";
                }
                estimaciones.add(panadero.getNombre() + " (" + panadero.getTipo() + "): " + minutos
                        + " min - " + veredicto + " [disponibles: " + panadero.getMinutosDisponibles() + "]");
            }
        }
        return estimaciones;
    }

}