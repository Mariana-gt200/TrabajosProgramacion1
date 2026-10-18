package co.edu.uniquindio.poo.model;

public class DetalleServicio {
    private String codigoDetalle;
    private Dispositivo dispositivo;
    private Servicio servicio;
    private String descripcionFalla;
    private Tecnico tecnicoResponsable;
    private String observacionesTecnicas;
    private String estado;

    public DetalleServicio(String codigoDetalle, Dispositivo dispositivo, Servicio servicio, String descripcionFalla, Tecnico tecnicoResponsable, String observacionesTecnicas, String estado) {
        this.codigoDetalle = codigoDetalle;
        this.dispositivo = dispositivo;
        this.servicio = servicio;
        this.descripcionFalla = descripcionFalla;
        this.tecnicoResponsable = tecnicoResponsable;
        this.observacionesTecnicas = observacionesTecnicas;
        this.estado = estado;
    }

    public void asignarTecnico(Tecnico tecnico) {
        if (tecnico.estaActivo()) {
            this.tecnicoResponsable = tecnico;
        } else {
            System.out.println("Error: El técnico no se encuentra activo.");
        }
    }

    public void agregarObservacionTecnica(String observacion) {
        this.observacionesTecnicas += observacion + "\n";
    }

    public double calcularCostoDetalle() {
        return this.servicio.calcularCosto(this);
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigoDetalle() {
        return codigoDetalle;
    }

    public void setCodigoDetalle(String codigoDetalle) {
        this.codigoDetalle = codigoDetalle;
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public String getDescripcionFalla() {
        return descripcionFalla;
    }

    public void setDescripcionFalla(String descripcionFalla) {
        this.descripcionFalla = descripcionFalla;
    }

    public Tecnico getTecnicoResponsable() {
        return tecnicoResponsable;
    }

    public void setTecnicoResponsable(Tecnico tecnicoResponsable) {
        this.tecnicoResponsable = tecnicoResponsable;
    }

    public String getObservacionesTecnicas() {
        return observacionesTecnicas;
    }

    public void setObservacionesTecnicas(String observacionesTecnicas) {
        this.observacionesTecnicas = observacionesTecnicas;
    }
}
