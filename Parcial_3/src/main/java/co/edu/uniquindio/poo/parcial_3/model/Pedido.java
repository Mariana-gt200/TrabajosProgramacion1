package co.edu.uniquindio.poo.model;

public class Pedido {
    private String codigo;
    private String nombreCliente;
    private TipoPan tipoPan;
    private int cantidadUnidades;
    private EstadoPedido estado;
    private String codigoEmpleadoAsignado;
    private int minutosReservados;

    public Pedido(String codigo, String nombreCliente, TipoPan tipoPan, int cantidadUnidades) {
        this.codigo = codigo;
        this.nombreCliente = nombreCliente;
        this.tipoPan = tipoPan;
        this.cantidadUnidades = cantidadUnidades;
        this.estado = EstadoPedido.PENDIENTE;
        this.codigoEmpleadoAsignado = null;
        this.minutosReservados = 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public TipoPan getTipoPan() {
        return tipoPan;
    }

    public void setTipoPan(TipoPan tipoPan) {
        this.tipoPan = tipoPan;
    }

    public int getCantidadUnidades() {
        return cantidadUnidades;
    }

    public void setCantidadUnidades(int cantidadUnidades) {
        this.cantidadUnidades = cantidadUnidades;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public String getCodigoEmpleadoAsignado() {
        return codigoEmpleadoAsignado;
    }

    public void setCodigoEmpleadoAsignado(String codigoEmpleadoAsignado) {
        this.codigoEmpleadoAsignado = codigoEmpleadoAsignado;
    }

    public int getMinutosReservados() {
        return minutosReservados;
    }

    public void setMinutosReservados(int minutosReservados) {
        this.minutosReservados = minutosReservados;
    }
}