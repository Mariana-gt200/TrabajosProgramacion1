package co.edu.uniquindio.poo.model;

import java.util.ArrayList;
import java.util.List;

public class DetalleServicio {
    private String codigoDetalle;
    private Dispositivo dispositivo;
    private Servicio servicio;
    private String descripcionFalla;
    private Tecnico tecnicoResponsable;
    private double horasTrabajo;
    private String observacionesTecnicas;
    private EstadoOrden estado;
    private List<RepuestoUtilizado> repuestosUtilizados;

    public DetalleServicio(String codigoDetalle, Dispositivo dispositivo, Servicio servicio, String falla, Tecnico tecnicoResponsable, String observacionesTecnicas, String descripcionFalla) {
        this.codigoDetalle = codigoDetalle;
        this.dispositivo = dispositivo;
        this.servicio = servicio;
        this.descripcionFalla = descripcionFalla;
        this.horasTrabajo = 0;
        this.observacionesTecnicas = "";
        this.estado = EstadoOrden.ABIERTA;
        this.repuestosUtilizados = new ArrayList<>();
    }

    // Funcionalidad 7: técnico activo y autorizado para el tipo de equipo
    public void asignarTecnico(Tecnico tecnico) {
        if (!tecnico.estaActivo()) {
            throw new IllegalArgumentException("El técnico " + tecnico.getNombre() + " no se encuentra activo.");
        }
        if (!tecnico.puedeAtender(dispositivo)) {
            throw new IllegalArgumentException("El técnico " + tecnico.getNombre() + " (" + tecnico.getEspecialidad()
                    + ") no está autorizado para intervenir un equipo de categoría " + dispositivo.getCategoria() + ".");
        }
        this.tecnicoResponsable = tecnico;
    }

    public void agregarObservacionTecnica(String observacion) {
        this.observacionesTecnicas += observacion + "\n";
    }

    // Las horas de trabajo solo aplican a la reparación correctiva
    public void registrarHorasTrabajo(double horas) {
        if (!(servicio instanceof ReparacionCorrectiva)) {
            throw new IllegalArgumentException("Solo la reparación correctiva registra horas de trabajo.");
        }
        if (horas < 0) {
            throw new IllegalArgumentException("Las horas de trabajo no pueden ser negativas.");
        }
        this.horasTrabajo = horas;
    }

    // Funcionalidad 8: el constructor de RepuestoUtilizado valida y descuenta el inventario
    public void agregarRepuesto(Repuesto repuesto, int cantidad) {
        if (!(servicio instanceof ReparacionCorrectiva)) {
            throw new IllegalArgumentException("Solo la reparación correctiva utiliza repuestos.");
        }
        repuestosUtilizados.add(new RepuestoUtilizado(repuesto, cantidad));
    }

    public double calcularCostoRepuestos() {
        double total = 0;
        for (RepuestoUtilizado ru : repuestosUtilizados) {
            total += ru.calcularSubtotal();
        }
        return total;
    }

    // Funcionalidad 9
    public double calcularCostoDetalle() {
        return this.servicio.calcularCosto(this);
    }

    public boolean estaPendiente() {   // pendiente = todavía sin finalizar
        return estado == EstadoOrden.ABIERTA || estado == EstadoOrden.EN_PROCESO;
    }

    @Override
    public String toString() {
        return "[" + codigoDetalle + "] " + dispositivo.getCodigoRegistro() + " " + dispositivo.getMarca() + " "
                + dispositivo.getModelo() + " | " + servicio.getNombre() + " | " + estado
                + " | Técnico: " + (tecnicoResponsable == null ? "sin asignar" : tecnicoResponsable.getNombre());
    }

    public EstadoOrden getEstado() { return estado; }
    public void setEstado(EstadoOrden estado) { this.estado = estado; }
    public String getCodigoDetalle() { return codigoDetalle; }
    public Dispositivo getDispositivo() { return dispositivo; }
    public Servicio getServicio() { return servicio; }
    public String getDescripcionFalla() { return descripcionFalla; }
    public Tecnico getTecnicoResponsable() { return tecnicoResponsable; }
    public double getHorasTrabajo() { return horasTrabajo; }
    public String getObservacionesTecnicas() { return observacionesTecnicas; }
    public List<RepuestoUtilizado> getRepuestosUtilizados() { return repuestosUtilizados; }
    public void setDescripcionFalla(String descripcionFalla) { this.descripcionFalla = descripcionFalla; }
}