package co.edu.uniquindio.poo.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdenServicio {
    private String codigoUnico;
    private LocalDate fechaCreacion;
    private Cliente cliente;
    private String observacionesGenerales;
    private EstadoOrden estado;
    private List<DetalleServicio> listaDetalles;

    public OrdenServicio(String codigoUnico, Cliente cliente, String observacionesGenerales) {
        this.codigoUnico = codigoUnico;
        this.fechaCreacion = LocalDate.now();
        this.cliente = cliente;
        this.observacionesGenerales = observacionesGenerales;
        this.estado = EstadoOrden.ABIERTA;
        this.listaDetalles = new ArrayList<>();
    }

    public void agregarDetalle(DetalleServicio detalle) {
        this.listaDetalles.add(detalle);
    }

    public void eliminarDetalle(String codigoDetalle) {
        listaDetalles.removeIf(d -> d.getCodigoDetalle().equals(codigoDetalle));
    }

    public double calcularCostoTotal() {
        double total = 0;
        for (DetalleServicio detalle : listaDetalles) {
            total += detalle.calcularCostoDetalle();
        }
        return total;
    }

    public void cambiarEstado(EstadoOrden nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
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

    public List<DetalleServicio> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(List<DetalleServicio> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }

    public String getCodigoUnico() { return codigoUnico; }
    public String getEstado() { return estado.toString(); }

}
