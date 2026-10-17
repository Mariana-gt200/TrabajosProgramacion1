package co.edu.uniquindio.poo.model;

public class DetalleServicio {
    private String codigoDetalle;
    private EquipoTecnologico equipo;
    private Servicio servicio;
    private String descripcionFalla;
    private Tecnico tecnicoResponsable;
    private String observacionesTecnicas;
    private String estado;

    public DetalleServicio(String codigoDetalle, EquipoTecnologico equipo, Servicio servicio, String descripcionFalla) {
        this.codigoDetalle = codigoDetalle;
        this.equipo = equipo;
        this.servicio = servicio;
        this.descripcionFalla = descripcionFalla;
        this.estado = "Abierta";
    }

    public void asignarTecnico(Tecnico tecnico) {
        if (tecnico.estaActivo()) {
            this.tecnicoResponsable = tecnico;
        } else {
            System.out.println("Error: El técnico no se encuentra activo.");
        }
    }

    public double calcularCostoDetalle() {
        return this.servicio.calcularCosto();
    }

    public void setCodigoDetalle(String codigoDetalle) {
        this.codigoDetalle = codigoDetalle;
    }

    public EquipoTecnologico getEquipo() {
        return equipo;
    }

    public void setEquipo(EquipoTecnologico equipo) {
        this.equipo = equipo;
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
