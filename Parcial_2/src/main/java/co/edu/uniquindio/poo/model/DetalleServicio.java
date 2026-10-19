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

    public DetalleServicio(String codigoDetalle, Dispositivo dispositivo, Servicio servicio, String descripcionFalla) {
        this.codigoDetalle = codigoDetalle;
        this.dispositivo = dispositivo;
        this.servicio = servicio;
        this.descripcionFalla = descripcionFalla;
        this.horasTrabajo = 0;
        this.observacionesTecnicas = "";
        this.estado = EstadoOrden.ABIERTA;
        this.repuestosUtilizados = new ArrayList<>();
    }

    public String asignarTecnico(Tecnico tecnico) {
        if (!tecnico.estaActivo()) {
            return "El técnico " + tecnico.getNombre() + " no se encuentra activo.";
        }
        if (!tecnico.puedeAtender(dispositivo)) {
            return "El técnico " + tecnico.getNombre() + " (" + tecnico.getEspecialidad()
                    + ") no está autorizado para intervenir un equipo de categoría " + dispositivo.getCategoria() + ".";
        }
        this.tecnicoResponsable = tecnico;
        return "Técnico asignado exitosamente.";
    }

    public void agregarObservacionTecnica(String observacion) {
        this.observacionesTecnicas += observacion + "\n";
    }

    public String registrarHorasTrabajo(double horas) {
        if (!(servicio instanceof ReparacionCorrectiva)) {
            return "Solo la reparación correctiva registra horas de trabajo.";
        }
        if (horas < 0) {
            return "Las horas de trabajo no pueden ser negativas.";
        }
        this.horasTrabajo = horas;
        return "Horas de trabajo registradas.";
    }

    public String agregarRepuesto(Repuesto repuesto, int cantidad) {
        if (!(servicio instanceof ReparacionCorrectiva)) {
            return "Solo la reparación correctiva utiliza repuestos.";
        }
        if (cantidad <= 0) {
            return "La cantidad debe ser mayor que cero.";
        }
        if (!repuesto.verificarDisponibilidad(cantidad)) {
            return "No hay suficientes unidades de '" + repuesto.getNombre() + "' (disponibles: "
                    + repuesto.getCantidadDisponible() + ").";
        }
        repuestosUtilizados.add(new RepuestoUtilizado(repuesto, cantidad));
        return "Repuesto registrado y descontado del inventario.";
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

    // Pendiente = todavía sin finalizar
    public boolean estaPendiente() {
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