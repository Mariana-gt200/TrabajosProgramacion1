package co.edu.uniquindio.poo.model;

public abstract class Dispositivo {
    public static final String CATEGORIA_PORTATIL = "Computador portátil";
    public static final String CATEGORIA_ESCRITORIO = "Computador de escritorio";
    public static final String CATEGORIA_TELEFONO = "Teléfono inteligente";

    protected String codigoRegistro;
    protected String numeroSerie;
    protected String marca;
    protected String modelo;
    protected int anioFabricacion;
    protected String descripcionEstadoFisico;

    public Dispositivo(String codigoRegistro, String numeroSerie, String marca,
                             String modelo, int anioFabricacion, String descripcionEstadoFisico) {
        this.codigoRegistro = codigoRegistro;
        this.numeroSerie = numeroSerie;
        this.marca = marca;
        this.modelo = modelo;
        this.anioFabricacion = anioFabricacion;
        this.descripcionEstadoFisico = descripcionEstadoFisico;
    }

    // Categoría de equipo (portátil, escritorio, teléfono inteligente, etc.)
    public abstract String getCategoria();

    // Especialidad de técnico que corresponde a este tipo de equipo.
    public abstract String getEspecialidadRequerida();

    public String getCodigoRegistro() { return codigoRegistro; }
    public String getNumeroSerie() { return numeroSerie; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getAnioFabricacion() { return anioFabricacion; }
    public String getDescripcionEstadoFisico() { return descripcionEstadoFisico; }

    public void setNumeroSerie(String numeroSerie) { this.numeroSerie = numeroSerie; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setAnioFabricacion(int anioFabricacion) { this.anioFabricacion = anioFabricacion; }
    public void setDescripcionEstadoFisico(String descripcionEstadoFisico) {
        this.descripcionEstadoFisico = descripcionEstadoFisico;
    }

    @Override
    public String toString() {
        return "[" + codigoRegistro + "] " + getCategoria() + " " + marca + " " + modelo
                + " (serie " + numeroSerie + ", " + anioFabricacion + ") - Estado: " + descripcionEstadoFisico;
    }
}
