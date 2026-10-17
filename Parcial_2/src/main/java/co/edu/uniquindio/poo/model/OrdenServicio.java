package co.edu.uniquindio.poo.model;

public class OrdenServicio {
    private String codigoUnico;
    private Date fechaCreacion;
    private Cliente cliente;
    private String observacionesGenerales; //Esto sale de cuando se pase todo a la interfaz
    private EstadoOrden estado;
    private List<DetalleServicio> detalles;

    public OrdenServicio(String codigoUnico, Cliente cliente, String observacionesGenerales) {
        this.codigoUnico = codigoUnico;
        this.fechaCreacion = new Date();
        this.cliente = cliente;
        this.observacionesGenerales = observacionesGenerales;
        this.estado = EstadoOrden.ABIERTA;
        this.detalles = new ArrayList<>();
    }

    public void agregarDetalle(DetalleServicio detalle) {
        this.detalles.add(detalle);
    }

    public void eliminarDetalle(String codigoDetalle) {
        detalles.removeIf(d -> d.getCodigoDetalle().equals(codigoDetalle));
    }

    public double calcularCostoTotal() {
        double total = 0;
        for (DetalleServicio detalle : detalles) {
            total += detalle.calcularCostoDetalle();
        }
        return total;
    }

    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getObservacionesGenerales() {
        return observacionesGenerales;
    }

    public void setObservacionesGenerales(String observacionesGenerales) {
        this.observacionesGenerales = observacionesGenerales;
    }

    public void setEstado(EstadoOrden estado) {
        this.estado = estado;
    }

    public List<DetalleServicio> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleServicio> detalles) {
        this.detalles = detalles;
    }

    public String getCodigoUnico() { return codigoUnico; }
    public String getEstado() { return estado; }
}
