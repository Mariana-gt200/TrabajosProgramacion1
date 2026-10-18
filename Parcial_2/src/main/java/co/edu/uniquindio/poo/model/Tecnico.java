package co.edu.uniquindio.poo.model;

public class Tecnico {
    public static final String COMPUTADORES = "Computadores";
    public static final String SOPORTE_GENERAL = "Soporte general";
    public static final String DISPOSITIVOS_MOVILES = "Dispositivos móviles";
    public static final String[] ESPECIALIDADES = {COMPUTADORES, DISPOSITIVOS_MOVILES, SOPORTE_GENERAL};

    private String codigo;
    private String nombre;
    private String documentoIdentificacion;
    private String especialidad;
    private boolean activo;   // estado laboral: true = activo, false = inactivo

    public Tecnico(String codigo, String nombre, String documentoIdentificacion,
                   String especialidad, boolean activo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.documentoIdentificacion = documentoIdentificacion;
        this.especialidad = especialidad;
        this.activo = activo;
    }

    public boolean estaActivo() { return activo; }

    // Determina si el técnico puede atender un dispositivo según su especialidad
    public boolean puedeAtender(Dispositivo dispositivo) {
        return especialidad.equals(SOPORTE_GENERAL)
                || especialidad.equals(dispositivo.getEspecialidadRequerida());
    }

    public static boolean esEspecialidadValida(String especialidad) {
        for (String e : ESPECIALIDADES) {
            if (e.equals(especialidad)) {
                return true;
            }
        }
        return false;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getDocumentoIdentificacion() { return documentoIdentificacion; }
    public String getEspecialidad() { return especialidad; }
    public boolean isActivo() { return activo; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDocumentoIdentificacion(String d) { this.documentoIdentificacion = d; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return "[" + codigo + "] " + nombre + " (Doc: " + documentoIdentificacion + ") - "
                + especialidad + " - " + (activo ? "Activo" : "Inactivo");
    }
}
